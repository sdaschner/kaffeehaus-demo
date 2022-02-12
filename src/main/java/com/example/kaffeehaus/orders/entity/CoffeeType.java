package com.example.kaffeehaus.orders.entity;

import java.util.stream.Stream;

public enum CoffeeType {

    ESPRESSO("Espresso"),
    FILTER("Filter"),
    MELANGE("Melange");

    private final String description;

    CoffeeType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static CoffeeType fromString(String string) {
        return Stream.of(CoffeeType.values())
                .filter(t -> t.name().equalsIgnoreCase(string))
                .findAny().orElse(null);
    }

}
