package com.example.demo.Repository.Order;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDAO implements IOrderDAO {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public Order newOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersForCustomer(Customer customer) {
        List<Order> orders = new ArrayList<>();
        Iterable<Order> orderIterable=orderRepository.findAll();
        for(Order order : orderIterable)
        {
            if(order.getCustomer().equals(customer))
                orders.add(order);
        }
        if(orders.size()>0)
            return orders;
        return null;
    }
}
