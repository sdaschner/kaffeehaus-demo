package com.example.kaffeehaus.orders.boundary;

import com.example.kaffeehaus.orders.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoffeeShopTest {

    private CoffeeShopTestDouble coffeeShop;

    @BeforeEach
    void setUp() {
        coffeeShop = new CoffeeShopTestDouble();
    }

    @Test
    void testCreateOrder() {
        Order order = new Order();
        coffeeShop.createOrder(order);
        coffeeShop.verifyCreateOrder(order);
    }

}
