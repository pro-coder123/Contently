package com.stackroute.usermanagement.service;


import com.stackroute.usermanagement.model.DTOUser;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${registry.rabbitmq.exchange}")
    private String exchange;

    @Value("${registry.rabbitmq.routingkey}")
    private String routingkey;


    public void sendRegistry(DTOUser user) {
        rabbitTemplate.convertAndSend(exchange, routingkey, user);
        System.out.println("Send msg = " + user.getUsername());
    }

}