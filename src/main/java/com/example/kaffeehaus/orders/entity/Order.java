package com.example.kaffeehaus.orders.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private UUID id;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private CoffeeType type;

    @ManyToOne(optional = false)
    private Origin origin;

    private Instant created;

    public Order() {
    }

    public Order(UUID id, CoffeeType type, Origin origin) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(type);
        Objects.requireNonNull(origin);
        this.id = id;
        this.type = type;
        this.origin = origin;
    }

    @PrePersist
    void updateCreated() {
        created = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CoffeeType getType() {
        return type;
    }

    public void setType(CoffeeType type) {
        this.type = type;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Order{" +
               "id='" + id + '\'' +
               ", type='" + type + '\'' +
               ", origin='" + origin + '\'' +
               ", created='" + created + '\'' +
               '}';
    }

}
