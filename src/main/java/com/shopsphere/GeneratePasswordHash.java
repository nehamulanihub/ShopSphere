
package com.shopsphere;

import com.shopsphere.util.PasswordUtil;

public class GeneratePasswordHash {
    public static void main(String[] args) {
        String newPassword = "ChangeMe123!";
        System.out.println(PasswordUtil.hashPassword(newPassword));
    }
}
