package com.shopsphere.rmi.server;

import com.shopsphere.dao.ProductDAO;
import com.shopsphere.model.Product;
import com.shopsphere.rmi.remote.InventoryService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class InventoryServiceImpl
        extends UnicastRemoteObject
        implements InventoryService {

    private ProductDAO productDAO;

    public InventoryServiceImpl() throws RemoteException {
        super();
        productDAO = new ProductDAO();
    }

    @Override
    public Product getProduct(int productId)
            throws RemoteException {

        return productDAO.getProductById(productId);
    }

    @Override
    public int getStock(int productId)
            throws RemoteException {

        Product product =
                productDAO.getProductById(productId);

        if (product == null) {
            return -1;
        }

        return product.getStock();
    }

    @Override
    public boolean updateStock(int productId, int quantity)
            throws RemoteException {

        Product product =
                productDAO.getProductById(productId);

        if (product == null) {
            return false;
        }

        int newStock = product.getStock() + quantity;

        if (newStock < 0) {
            return false;
        }

        product.setStock(newStock);

        return productDAO.updateProduct(product);
    }
}
