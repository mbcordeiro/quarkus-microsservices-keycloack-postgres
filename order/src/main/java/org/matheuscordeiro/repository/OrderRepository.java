package org.matheuscordeiro.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.matheuscordeiro.entity.Order;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {
}
