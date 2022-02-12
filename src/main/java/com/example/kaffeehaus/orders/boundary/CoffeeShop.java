package com.example.kaffeehaus.orders.boundary;

import com.example.kaffeehaus.orders.control.OrderRepository;
import com.example.kaffeehaus.orders.control.OriginRepository;
import com.example.kaffeehaus.orders.entity.CoffeeType;
import com.example.kaffeehaus.orders.entity.Order;
import com.example.kaffeehaus.orders.entity.Origin;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class CoffeeShop {

    @Inject
    OrderRepository orderRepository;

    @Inject
    OriginRepository originRepository;

    public Set<CoffeeType> getCoffeeTypes() {
        return EnumSet.of(CoffeeType.ESPRESSO, CoffeeType.MELANGE, CoffeeType.FILTER);
    }

    public Set<Origin> getOrigins(CoffeeType type) {
        return originRepository.listForCoffeeType(type);
    }

    public Origin getOrigin(String name) {
        return originRepository.findById(name);
    }

    public void createOrder(Order order) {
        orderRepository.persist(order);
    }

    public Order getOrder(UUID id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrders() {
        return orderRepository.listAll();
    }

    public void updateOrder(UUID id, Order order) {
        Order managedOrder = orderRepository.findById(id);
        managedOrder.setType(order.getType());
        managedOrder.setOrigin(order.getOrigin());
    }

}
