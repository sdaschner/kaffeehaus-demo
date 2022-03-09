package com.example.kaffeehaus.orders.boundary;

import com.example.kaffeehaus.orders.entity.Order;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateExtension;
import io.quarkus.qute.TemplateInstance;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("index.html")
@Produces(MediaType.TEXT_HTML)
public class IndexController {

    @Inject
    CoffeeShop coffeeShop;

    @Location("index.html")
    Template index;

    @ConfigProperty(name = "kaffeehaus.greeting")
    String greeting;

    @GET
    public TemplateInstance index() {
        List<Order> orders = coffeeShop.getOrders();
        return index.data("orders", orders, "greeting", greeting);
    }

    @TemplateExtension(namespace = "instant")
    static String format(Instant instant) {
        return instant.atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd MMM uuuu 'at' HH:mm:ss"));
    }

}
