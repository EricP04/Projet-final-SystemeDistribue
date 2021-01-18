package com.example.demo.Repository.Order;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Order;

import java.util.List;

public interface IOrderDAO {

    Order newOrder(Order order);
    Order getOrderById(Integer id);
    List<Order> getOrdersForCustomer(Customer customer);

}
