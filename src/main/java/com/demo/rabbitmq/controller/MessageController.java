package com.demo.rabbitmq.controller;

import com.demo.rabbitmq.producer.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final RabbitMQProducer rabbitMQProducer;

    public MessageController(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @GetMapping
    public ResponseEntity<String> produceMessage(@RequestParam("message") String message) {
        rabbitMQProducer.produceMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ");
    }

}
