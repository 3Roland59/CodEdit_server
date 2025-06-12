package com.coded.coded_server.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Value("spring.rabbitmq.uri")
    private String SECRET_URI;

    @Bean
public ConnectionFactory connectionFactory() {
    CachingConnectionFactory factory = new CachingConnectionFactory();
    factory.setUri("amqps://devjhapf:O2VMiGNvTArVNAjRgk7QeHO6mFM5EjLZ@moose.rmq.cloudamqp.com/devjhapf");
    return factory;
}

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    public Queue codeExecutionQueue() {
        return new Queue("code_execution_requests", true);
    }

    @Bean
public Queue codeExecutionResponseQueue() {
    return new Queue("code_execution_responses", true);
}

}
