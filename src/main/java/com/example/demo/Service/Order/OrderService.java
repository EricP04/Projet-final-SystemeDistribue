package com.example.demo.Service.Order;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Order;
import com.example.demo.Repository.Order.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public Order newOrder(Order order) {

        return orderDAO.newOrder(order);
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderDAO.getOrderById(id);
    }

    @Override
    public List<Order> getOrdersForCustomer(Customer customer) {
        return orderDAO.getOrdersForCustomer(customer);
    }
}
