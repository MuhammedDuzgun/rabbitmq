package com.demo.rabbitmq.controller;

import com.demo.rabbitmq.producer.RabbitMQJsonProducer;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final RabbitMQJsonProducer rabbitMQJsonProducer;

    public UserController(RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }

    @PostMapping
    public ResponseEntity<String> produceUser(@RequestBody User user) {
        rabbitMQJsonProducer.produceJsonMessage(user);
        return ResponseEntity.ok("user produced to rabbitmq");
    }

}
