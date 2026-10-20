package com.shopsphere.service;

import com.shopsphere.dao.PaymentDAO;
import com.shopsphere.model.Payment;

public class PaymentService {

    private PaymentDAO paymentDAO = new PaymentDAO();

    public boolean createPayment(Payment payment) {
        return paymentDAO.createPayment(payment);
    }

    public Payment getPaymentByOrderId(int orderId) {
        return paymentDAO.getPaymentByOrderId(orderId);
    }
}