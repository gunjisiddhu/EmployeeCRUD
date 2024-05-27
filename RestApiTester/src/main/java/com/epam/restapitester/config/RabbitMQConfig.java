package com.epam.restapitester.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {


    @Value("${messaging.queue.name}")
    public static String QUEUE_NAME;


    @Value("${messaging.topic.name}")
    public static String TOPIC_NAME;

    @Value("${messaging.routing.key}")
    public static String ROUTING_KEY;


    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }


    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_NAME);
    }


    @Bean
    public Binding binder() {
        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY);
    }


    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }



}
