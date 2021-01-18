package com.example.demo.Listener;

import com.example.demo.Entity.Order;
import com.example.demo.Message.OrderMessage;
import com.example.demo.Service.Order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Receiver {
    @Autowired
    private OrderService orderService;

    private static Logger log = LoggerFactory.getLogger(Receiver.class);

    @JmsListener(destination = "queueMainApp", containerFactory = "jmsContainerFactory", selector = "priority = 'high'")
    public void receiveMessage(OrderMessage message, @Headers Map<String, Object> headers) {
        System.out.println("Received <" + message + ">");
        log.info("- priority=" + String.valueOf(headers.get("priority")));
        Order order = orderService.getOrderById(message.getIdOrder());
        order.setStatus(message.getStatus());
        orderService.newOrder(order);
    }
}
