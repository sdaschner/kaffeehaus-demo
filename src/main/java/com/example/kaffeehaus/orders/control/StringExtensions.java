package com.example.kaffeehaus.orders.control;

import io.quarkus.qute.TemplateExtension;

public class StringExtensions {

    @TemplateExtension(namespace = "string")
    public static String capitalize(String word) {
        return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
    }

}
