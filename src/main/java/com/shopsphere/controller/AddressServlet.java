package com.shopsphere.controller;

import com.shopsphere.model.Address;
import com.shopsphere.model.User;
import com.shopsphere.service.AddressService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/addresses")
public class AddressServlet extends HttpServlet {

    private AddressService addressService;

    @Override
    public void init() {
        addressService = new AddressService();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null ||
                session.getAttribute("loggedInUser") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login");
            return;
        }

        User user =
                (User) session.getAttribute(
                        "loggedInUser");

        request.setAttribute(
                "addresses",
                addressService.getAddressesByUserId(
                        user.getUserId()));

        request.getRequestDispatcher(
                "/addresses.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null ||
                session.getAttribute("loggedInUser") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login");
            return;
        }

        User user =
                (User) session.getAttribute(
                        "loggedInUser");

        String action =
                request.getParameter("action");

        if ("add".equals(action)) {

            Address address = new Address();

            address.setUserId(
                    user.getUserId());

            address.setAddressLine(
                    request.getParameter("addressLine"));

            address.setCity(
                    request.getParameter("city"));

            address.setState(
                    request.getParameter("state"));

            address.setPincode(
                    request.getParameter("pincode"));

            address.setCountry(
                    request.getParameter("country"));

            addressService.addAddress(address);

        } else if ("delete".equals(action)) {

            try {

                int addressId =
                        Integer.parseInt(
                                request.getParameter(
                                        "addressId"));

                addressService.deleteAddress(
                        addressId,
                        user.getUserId());

            } catch (NumberFormatException e) {

                e.printStackTrace();
            }
        }

        response.sendRedirect(
                request.getContextPath() + "/addresses");
    }
}