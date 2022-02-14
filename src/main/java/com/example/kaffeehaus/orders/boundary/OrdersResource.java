package com.example.kaffeehaus.orders.boundary;

import com.example.kaffeehaus.orders.control.EntityBuilder;
import com.example.kaffeehaus.orders.entity.Order;
import com.example.kaffeehaus.orders.entity.ValidOrder;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Path("orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrdersResource {

    @Inject
    CoffeeShop coffeeShop;

    @Inject
    EntityBuilder entityBuilder;

    @Context
    UriInfo uriInfo;

    @Context
    HttpServletRequest request;

    @GET
    public JsonArray getOrders() {
        List<Order> orders = coffeeShop.getOrders();
        return entityBuilder.buildOrders(orders, uriInfo, request);
    }

    @PUT
    @Path("{id}")
    public void updateOrder(@PathParam("id") UUID id, JsonObject jsonObject) {
        Order order = entityBuilder.buildOrder(jsonObject);
        coffeeShop.updateOrder(id, order);
    }

    @GET
    @Path("{id}")
    public JsonObject getOrder(@PathParam("id") UUID id) {
        final Order order = coffeeShop.getOrder(id);

        if (order == null)
            throw new NotFoundException();

        return entityBuilder.buildOrder(order);
    }

    @POST
    public Response createOrder(@Valid @ValidOrder JsonObject json) {
        final Order order = entityBuilder.buildOrder(json);

        coffeeShop.createOrder(order);

        return Response.created(buildUri(order)).build();
    }

    private URI buildUri(Order order) {
        return uriInfo.getBaseUriBuilder()
                .host(request.getServerName())
                .port(request.getServerPort())
                .path(OrdersResource.class)
                .path(OrdersResource.class, "getOrder")
                .build(order.getId());
    }

}