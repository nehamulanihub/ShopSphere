package com.shopsphere.service;

import java.util.List;

import com.shopsphere.dao.UserDAO;
import com.shopsphere.model.User;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public boolean registerUser(User user) {

    if (user == null ||
            user.getEmail() == null ||
            user.getEmail().trim().isEmpty()) {
        return false;
    }

    String email = user.getEmail().trim();
    user.setEmail(email);

    if (userDAO.emailExists(email)) {
        return false;
    }

    return userDAO.registerUser(user);
}

    public User loginUser(String email, String password) {
        return userDAO.loginUser(email, password);
    }
     
    public boolean updateUser(User user) {

    if (user == null ||
            user.getUserId() <= 0) {

        return false;
    }

    if (user.getName() == null ||
            user.getName().trim().isEmpty()) {

        return false;
    }

    if (user.getEmail() == null ||
            user.getEmail().trim().isEmpty()) {

        return false;
    }

    // Check whether another user already has this email
    if (userDAO.emailExistsForOtherUser(
            user.getEmail(),
            user.getUserId())) {

        return false;
    }

    return userDAO.updateUser(user);
}
public boolean changePassword(
        int userId,
        String currentPassword,
        String newPassword) {

    if (userId <= 0 ||
            currentPassword == null ||
            currentPassword.trim().isEmpty() ||
            newPassword == null ||
            newPassword.trim().isEmpty()) {

        return false;
    }

    return userDAO.changePassword(
            userId,
            currentPassword,
            newPassword);
}
public List<User> getAllUsers() {
    return userDAO.getAllUsers();
}
}