package com.shopsphere.rmi.server;

import com.shopsphere.rmi.remote.InventoryService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

    public static void main(String[] args) {

        try {

            InventoryService service =
                    new InventoryServiceImpl();

            Registry registry =
                    LocateRegistry.createRegistry(1099);

            registry.rebind(
                    "InventoryService",
                    service);

            System.out.println(
                    "ShopSphere RMI Server started.");

            System.out.println(
                    "InventoryService is ready.");

        } catch (Exception e) {

            System.out.println(
                    "RMI Server error: "
                            + e.getMessage());
        }
    }
}