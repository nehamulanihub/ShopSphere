package com.shopsphere.rmi.client;

import com.shopsphere.model.Product;
import com.shopsphere.rmi.remote.InventoryService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {

    public static void main(String[] args) {

        try {

            Registry registry =
                    LocateRegistry.getRegistry(
                            "localhost", 1099);

            InventoryService service =
                    (InventoryService) registry.lookup(
                            "InventoryService");

            Product product =
                    service.getProduct(1);

            if (product != null) {

                System.out.println(
                        "Product Name: "
                                + product.getName());

                System.out.println(
                        "Price: "
                                + product.getPrice());

                System.out.println(
                        "Stock: "
                                + service.getStock(
                                        product.getProductId()));

            } else {

                System.out.println(
                        "Product not found.");
            }

        } catch (Exception e) {

            System.out.println(
                    "RMI Client error: "
                            + e.getMessage());
        }
    }
}