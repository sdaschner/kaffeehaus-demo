package com.example.kaffeehaus.orders.boundary;

import com.example.kaffeehaus.orders.control.EntityBuilder;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("types")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class TypesResource {

    @Inject
    CoffeeShop coffeeShop;

    @Inject
    EntityBuilder entityBuilder;

    @Context
    ResourceContext resourceContext;

    @Context
    HttpServletRequest request;

    @GET
    public JsonArray getCoffeeTypes() {
        return coffeeShop.getCoffeeTypes().stream()
                .map(t -> entityBuilder.buildType(t, request))
                .collect(Json::createArrayBuilder, JsonArrayBuilder::add, JsonArrayBuilder::add).build();
    }

    @Path("{type}/origins")
    public OriginsResource originsResource() {
        return resourceContext.getResource(OriginsResource.class);
    }

}
