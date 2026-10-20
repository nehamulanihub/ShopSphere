package com.shopsphere.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18nDemo {

    public static void main(String[] args) {

        ResourceBundle english =
                ResourceBundle.getBundle(
                        "messages",
                        Locale.ENGLISH);

        ResourceBundle hindi =
                ResourceBundle.getBundle(
                        "messages",
                        Locale.forLanguageTag("hi"));

        System.out.println("English:");
        System.out.println(english.getString("welcome.message"));
        System.out.println(english.getString("login"));

        System.out.println();

        System.out.println("Hindi:");
        System.out.println(hindi.getString("welcome.message"));
        System.out.println(hindi.getString("login"));
    }
}