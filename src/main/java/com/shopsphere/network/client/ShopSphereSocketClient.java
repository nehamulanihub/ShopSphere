package com.shopsphere.network.client;

import com.shopsphere.model.Product;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ShopSphereSocketClient {

    private static final String HOST = "localhost";
    private static final int PORT = 5000;

    public static void main(String[] args) {

        getProduct(1);

        getStock(1);

        searchProduct("Laptop");
    }

    private static void getProduct(int productId) {

        try (Socket socket = new Socket(HOST, PORT);
             ObjectOutputStream output =
                     new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input =
                     new ObjectInputStream(socket.getInputStream())) {

            output.flush();

            output.writeUTF("GET_PRODUCT");
            output.writeInt(productId);
            output.flush();

            Product product =
                    (Product) input.readObject();

            System.out.println("\n--- GET_PRODUCT ---");

            if (product != null) {

                System.out.println(
                        "Product ID: "
                                + product.getProductId());

                System.out.println(
                        "Product Name: "
                                + product.getName());

                System.out.println(
                        "Price: "
                                + product.getPrice());

                System.out.println(
                        "Stock: "
                                + product.getStock());

            } else {

                System.out.println("Product not found.");
            }

        } catch (Exception e) {

            System.out.println(
                    "GET_PRODUCT error: "
                            + e.getMessage());
        }
    }

    private static void getStock(int productId) {

        try (Socket socket = new Socket(HOST, PORT);
             ObjectOutputStream output =
                     new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input =
                     new ObjectInputStream(socket.getInputStream())) {

            output.flush();

            output.writeUTF("GET_STOCK");
            output.writeInt(productId);
            output.flush();

            int stock =
                    input.readInt();

            System.out.println("\n--- GET_STOCK ---");

            if (stock >= 0) {
                System.out.println(
                        "Current Stock: " + stock);
            } else {
                System.out.println(
                        "Product not found.");
            }

        } catch (Exception e) {

            System.out.println(
                    "GET_STOCK error: "
                            + e.getMessage());
        }
    }

    private static void searchProduct(String keyword) {

        try (Socket socket = new Socket(HOST, PORT);
             ObjectOutputStream output =
                     new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input =
                     new ObjectInputStream(socket.getInputStream())) {

            output.flush();

            output.writeUTF("SEARCH_PRODUCT");
            output.writeUTF(keyword);
            output.flush();

            @SuppressWarnings("unchecked")
            List<Product> products =
                    (List<Product>) input.readObject();

            System.out.println("\n--- SEARCH_PRODUCT ---");

            if (products.isEmpty()) {

                System.out.println(
                        "No products found.");

            } else {

                for (Product product : products) {

                    System.out.println(
                            "ID: "
                                    + product.getProductId()
                                    + ", Name: "
                                    + product.getName()
                                    + ", Price: "
                                    + product.getPrice()
                                    + ", Stock: "
                                    + product.getStock());
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "SEARCH_PRODUCT error: "
                            + e.getMessage());
        }
    }
}