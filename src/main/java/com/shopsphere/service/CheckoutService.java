package com.shopsphere.service;

import com.shopsphere.dao.AddressDAO;
import com.shopsphere.dao.CartDAO;
import com.shopsphere.dao.CouponUsageDAO;
import com.shopsphere.dao.OrderDAO;
import com.shopsphere.dao.OrderItemDAO;
import com.shopsphere.dao.PaymentDAO;
import com.shopsphere.dao.ProductDAO;

import com.shopsphere.model.Address;
import com.shopsphere.model.CartItem;
import com.shopsphere.model.Coupon;
import com.shopsphere.model.CouponUsage;
import com.shopsphere.model.Order;
import com.shopsphere.model.OrderItem;
import com.shopsphere.model.Payment;
import com.shopsphere.model.Product;

import com.shopsphere.util.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class CheckoutService {

    private final CartDAO cartDAO =
            new CartDAO();

    private final OrderDAO orderDAO =
            new OrderDAO();

    private final OrderItemDAO orderItemDAO =
            new OrderItemDAO();

    private final PaymentDAO paymentDAO =
            new PaymentDAO();

    private final ProductDAO productDAO =
            new ProductDAO();

    private final CouponService couponService =
            new CouponService();

    private final CouponUsageDAO couponUsageDAO =
            new CouponUsageDAO();

    private final AddressDAO addressDAO =
            new AddressDAO();


    public int checkout(
            int userId,
            int addressId,
            String paymentMethod,
            String couponCode) {


        // ---------------------------------
        // STEP 1: BASIC VALIDATION
        // ---------------------------------

        if (userId <= 0) {
            return -1;
        }

        if (addressId <= 0) {
            return -1;
        }

        if (paymentMethod == null ||
                paymentMethod.trim().isEmpty()) {

            return -1;
        }

        paymentMethod =
                paymentMethod.trim().toUpperCase();


        if (!"COD".equals(paymentMethod) &&
                !"CARD".equals(paymentMethod) &&
                !"UPI".equals(paymentMethod)) {

            return -1;
        }


        // ---------------------------------
        // STEP 2: CHECK ADDRESS OWNERSHIP
        // ---------------------------------

        List<Address> userAddresses =
                addressDAO.getAddressesByUserId(
                        userId);

        boolean addressBelongsToUser = false;


        if (userAddresses != null) {

            for (Address address :
                    userAddresses) {

                if (address.getAddressId()
                        == addressId) {

                    addressBelongsToUser = true;
                    break;
                }
            }
        }


        if (!addressBelongsToUser) {
            return -1;
        }


        // ---------------------------------
        // STEP 3: START TRANSACTION
        // ---------------------------------

        try (Connection con =
                     DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try {


                // ---------------------------------
                // STEP 4: GET CART
                // ---------------------------------

                List<CartItem> cartItems =
                        cartDAO.getCartByUserId(
                                userId);


                if (cartItems == null ||
                        cartItems.isEmpty()) {

                    con.rollback();
                    return -1;
                }


                // ---------------------------------
                // STEP 5: CALCULATE TOTAL
                // ---------------------------------

                BigDecimal totalAmount =
                        BigDecimal.ZERO;

                Coupon appliedCoupon = null;


                for (CartItem item :
                        cartItems) {


                    if (item == null ||
                            item.getQuantity() <= 0) {

                        con.rollback();
                        return -1;
                    }


                    Product product =
                            productDAO.getProductById(
                                    item.getProductId());


                    if (product == null) {

                        con.rollback();
                        return -1;
                    }


                    // Check stock

                    if (item.getQuantity()
                            > product.getStock()) {

                        con.rollback();
                        return -1;
                    }


                    // Get current product price

                    BigDecimal itemPrice =
                            product.getPrice();


                    if (itemPrice == null ||
                            itemPrice.compareTo(
                                    BigDecimal.ZERO) < 0) {

                        con.rollback();
                        return -1;
                    }


                    BigDecimal itemTotal =
                            itemPrice.multiply(
                                    BigDecimal.valueOf(
                                            item.getQuantity()));


                    totalAmount =
                            totalAmount.add(
                                    itemTotal);
                }


                // ---------------------------------
                // STEP 6: APPLY COUPON
                // ---------------------------------

                if (couponCode != null &&
                        !couponCode.trim().isEmpty()) {


                    couponCode =
                            couponCode.trim()
                                    .toUpperCase();


                    appliedCoupon =
                            couponService.getCouponByCode(
                                    couponCode);


                    if (appliedCoupon == null) {

                        con.rollback();
                        return -1;
                    }


                    BigDecimal discountedAmount =
                            couponService.applyCoupon(
                                    totalAmount,
                                    couponCode);


                    if (discountedAmount == null ||
                            discountedAmount.compareTo(
                                    BigDecimal.ZERO) < 0) {

                        con.rollback();
                        return -1;
                    }


                    totalAmount =
                            discountedAmount;
                }


                // ---------------------------------
                // STEP 7: CREATE ORDER
                // ---------------------------------

                Order order =
                        new Order();

                order.setUserId(userId);

                order.setAddressId(addressId);

                order.setTotalAmount(
                        totalAmount);

                order.setStatus("PLACED");


                int orderId =
                        orderDAO.createOrder(
                                order,
                                con);


                if (orderId <= 0) {

                    con.rollback();
                    return -1;
                }


                // ---------------------------------
                // STEP 8: ADD ORDER ITEMS
                // + UPDATE STOCK
                // ---------------------------------

                for (CartItem cartItem :
                        cartItems) {


                    Product product =
                            productDAO.getProductById(
                                    cartItem.getProductId());


                    if (product == null) {

                        con.rollback();
                        return -1;
                    }


                    OrderItem orderItem =
                            new OrderItem();


                    orderItem.setOrderId(
                            orderId);


                    orderItem.setProductId(
                            cartItem.getProductId());


                    orderItem.setQuantity(
                            cartItem.getQuantity());


                    orderItem.setPrice(
                            product.getPrice());


                    boolean itemAdded =
                            orderItemDAO.addOrderItem(
                                    orderItem,
                                    con);


                    if (!itemAdded) {

                        con.rollback();
                        return -1;
                    }


                    // Reduce stock

                    int newStock =
                            product.getStock()
                                    - cartItem.getQuantity();


                    if (newStock < 0) {

                        con.rollback();
                        return -1;
                    }


                    product.setStock(
                            newStock);


                    boolean stockUpdated =
                            productDAO.updateProduct(
                                    product,
                                    con);


                    if (!stockUpdated) {

                        con.rollback();
                        return -1;
                    }
                }


                // ---------------------------------
                // STEP 9: CREATE PAYMENT
                // ---------------------------------

                Payment payment =
                        new Payment();


                payment.setOrderId(
                        orderId);


                payment.setPaymentMethod(
                        paymentMethod);


                payment.setStatus(
                        "SUCCESS");


                boolean paymentCreated =
                        paymentDAO.createPayment(
                                payment,
                                con);


                if (!paymentCreated) {

                    con.rollback();
                    return -1;
                }


                // ---------------------------------
                // STEP 10: SAVE COUPON USAGE
                // ---------------------------------

                if (appliedCoupon != null) {


                    CouponUsage usage =
                            new CouponUsage();


                    usage.setCouponId(
                            appliedCoupon.getCouponId());


                    usage.setUserId(
                            userId);


                    usage.setOrderId(
                            orderId);


                    boolean usageSaved =
                            couponUsageDAO.addCouponUsage(
                                    usage,
                                    con);


                    if (!usageSaved) {

                        con.rollback();
                        return -1;
                    }
                }


                // ---------------------------------
                // STEP 11: CLEAR CART
                // ---------------------------------

                boolean cartCleared =
                        cartDAO.clearCart(
                                userId,
                                con);


                if (!cartCleared) {

                    con.rollback();
                    return -1;
                }


                // ---------------------------------
                // STEP 12: COMMIT TRANSACTION
                // ---------------------------------

                con.commit();

                return orderId;


            } catch (Exception e) {

                try {

                    con.rollback();

                } catch (Exception rollbackException) {

                    rollbackException.printStackTrace();
                }


                e.printStackTrace();

                return -1;
            }


        } catch (Exception e) {

            e.printStackTrace();

            return -1;
        }
    }
}