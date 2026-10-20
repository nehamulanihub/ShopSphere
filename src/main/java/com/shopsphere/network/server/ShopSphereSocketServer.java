package com.shopsphere.network.server;

import com.shopsphere.dao.ProductDAO;
import com.shopsphere.model.Product;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ShopSphereSocketServer {

    private static final int PORT = 5000;

    public static void main(String[] args) {

        ProductDAO productDAO = new ProductDAO();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("ShopSphere Socket Server started.");
            System.out.println("Waiting for client on port " + PORT);

            while (true) {

                try (Socket socket = serverSocket.accept();
                     ObjectOutputStream output =
                             new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream input =
                             new ObjectInputStream(socket.getInputStream())) {

                    output.flush();

                    String command = input.readUTF();

                    System.out.println("Command received: " + command);

                    switch (command) {

                        case "GET_PRODUCT":

                            int productId = input.readInt();

                            Product product =
                                    productDAO.getProductById(productId);

                            output.writeObject(product);
                            output.flush();

                            break;

                        case "GET_STOCK":

                            int stockProductId = input.readInt();

                            Product stockProduct =
                                    productDAO.getProductById(stockProductId);

                            if (stockProduct != null) {
                                output.writeInt(stockProduct.getStock());
                            } else {
                                output.writeInt(-1);
                            }

                            output.flush();

                            break;

                        case "SEARCH_PRODUCT":

                            String keyword = input.readUTF();

                            List<Product> products =
                                    productDAO.searchProducts(keyword);

                            output.writeObject(products);
                            output.flush();

                            break;

                        default:

                            output.writeObject(
                                    "Invalid command");

                            output.flush();
                    }

                } catch (Exception e) {

                    System.out.println(
                            "Error while handling client: "
                                    + e.getMessage());
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Socket server error: "
                            + e.getMessage());
        }
    }
}