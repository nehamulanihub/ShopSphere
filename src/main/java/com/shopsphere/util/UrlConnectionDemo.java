package com.shopsphere.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;

public class UrlConnectionDemo {

    public static void main(String[] args) {

        try {

            URL url =
        URI.create("https://example.com").toURL();

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode =
                    connection.getResponseCode();

            System.out.println("HTTP Response Code: "
                    + responseCode);

            connection.disconnect();

        } catch (IOException e) {

            System.out.println(
                    "URL connection failed.");

            System.out.println(
                    e.getMessage());
        }
    }
}