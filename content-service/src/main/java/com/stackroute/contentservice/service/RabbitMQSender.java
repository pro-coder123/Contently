package com.stackroute.contentservice.service;


import com.stackroute.contentservice.model.Content;
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


    public void sendRegistry(Content content) {
        rabbitTemplate.convertAndSend(exchange, routingkey, content);
        System.out.println("Send msg = " + content.getId());
    }

}