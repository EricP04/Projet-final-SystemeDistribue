package com.example.demo.Repository.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDAO implements IOrderDAO {

    @Autowired
    private OrderRepository orderRepository;



}
