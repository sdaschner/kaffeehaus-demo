package com.example.kaffeehaus.orders.control;

import com.example.kaffeehaus.orders.entity.CoffeeType;
import com.example.kaffeehaus.orders.entity.Origin;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class OriginRepository implements PanacheRepositoryBase<Origin, String> {

    public Set<Origin> listForCoffeeType(CoffeeType type) {
        return streamAll()
                .filter(t -> t.getCoffeeTypes().contains(type))
                .collect(Collectors.toSet());
    }

}
