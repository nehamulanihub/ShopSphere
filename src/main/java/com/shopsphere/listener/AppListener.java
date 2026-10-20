package com.shopsphere.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {

        System.out.println("ShopSphere application started successfully.");

    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

        System.out.println("ShopSphere application stopped.");

    }
}
