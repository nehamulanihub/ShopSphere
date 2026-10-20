package com.shopsphere.service;

import com.shopsphere.dao.AddressDAO;
import com.shopsphere.model.Address;

import java.util.List;

public class AddressService {

    private AddressDAO addressDAO = new AddressDAO();

    public boolean addAddress(Address address) {
        return addressDAO.addAddress(address);
    }

    public List<Address> getAddressesByUserId(int userId) {
        return addressDAO.getAddressesByUserId(userId);
    }

    public boolean deleteAddress(
            int addressId,
            int userId) {

        return addressDAO.deleteAddress(
                addressId,
                userId);
    }
}