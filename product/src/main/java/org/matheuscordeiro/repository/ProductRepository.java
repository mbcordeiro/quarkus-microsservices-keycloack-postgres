package org.matheuscordeiro.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.matheuscordeiro.entity.Product;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {
}
