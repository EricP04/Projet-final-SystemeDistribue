package com.example.demo.Repository.Order;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderDAO implements IOrderDAO {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public Order newOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Integer id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if(orderOptional.isPresent())
            return orderOptional.get();
        return null;
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
