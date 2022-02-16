package com.example.kaffeehaus;

import com.example.kaffeehaus.orders.control.EntityBuilder;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/")
public class RootResource {

    @Inject
    EntityBuilder entityBuilder;

    @Context
    HttpServletRequest request;

    @GET
    public JsonObject index() {
        return entityBuilder.buildIndex(request);
    }

}
