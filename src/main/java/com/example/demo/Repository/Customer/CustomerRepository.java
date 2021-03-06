package com.example.demo.Repository.Customer;

import com.example.demo.Entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    @Override
    Iterable<Customer> findAll();
}
