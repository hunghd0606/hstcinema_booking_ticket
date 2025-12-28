package com.example.booking.config;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.*;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue bookingQueue() {
        return new Queue("booking.queue");
    }
}
