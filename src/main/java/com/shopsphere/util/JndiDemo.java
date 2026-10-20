package com.shopsphere.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JndiDemo {

    public static void main(String[] args) {

        try {

            InitialContext context = new InitialContext();

            Object result = context.lookup("java:comp/env");

            System.out.println("JNDI lookup successful.");
            System.out.println("Result: " + result);

        } catch (NamingException e) {

            System.out.println("JNDI lookup failed.");
            System.out.println(e.getMessage());
        }
    }
}
