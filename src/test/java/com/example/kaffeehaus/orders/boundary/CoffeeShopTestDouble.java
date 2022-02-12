package com.example.kaffeehaus.orders.boundary;

import com.example.kaffeehaus.orders.control.OrderRepository;
import com.example.kaffeehaus.orders.control.OriginRepository;
import com.example.kaffeehaus.orders.entity.Order;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CoffeeShopTestDouble extends CoffeeShop {

    public CoffeeShopTestDouble() {
        orderRepository = mock(OrderRepository.class);
        originRepository = mock(OriginRepository.class);
    }

    public void verifyCreateOrder(Order order) {
        verify(orderRepository).persist(order);
    }
}
