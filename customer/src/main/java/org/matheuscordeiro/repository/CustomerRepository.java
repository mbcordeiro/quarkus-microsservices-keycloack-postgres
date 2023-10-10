package org.matheuscordeiro.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.matheuscordeiro.entity.Customer;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
}
