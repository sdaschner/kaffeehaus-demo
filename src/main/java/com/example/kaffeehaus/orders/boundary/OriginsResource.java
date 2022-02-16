package com.example.kaffeehaus.orders.boundary;

import com.example.kaffeehaus.orders.control.EntityBuilder;
import com.example.kaffeehaus.orders.entity.CoffeeType;
import io.quarkus.arc.Unremovable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
@Unremovable // https://github.com/quarkusio/quarkus/issues/5314
public class OriginsResource {

    @Inject
    CoffeeShop coffeeShop;

    @PathParam("type")
    CoffeeType type;

    @Context
    HttpServletRequest request;

    @Inject
    EntityBuilder entityBuilder;

    @GET
    public JsonArray getOrigins() {
        return coffeeShop.getOrigins(type).stream()
                .map(o -> entityBuilder.buildOrigin(request, o, type))
                .collect(Json::createArrayBuilder, JsonArrayBuilder::add, JsonArrayBuilder::add).build();
    }

}
