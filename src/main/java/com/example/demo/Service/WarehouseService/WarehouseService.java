package com.example.demo.Service.WarehouseService;

import com.example.demo.Message.OrderMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessageToWarehouse(OrderMessage orderMessage)
    {
        jmsTemplate.convertAndSend("queueWarehouse",orderMessage,message->{
            message.setStringProperty("priority","high");
            return message;
        });
    }
}
