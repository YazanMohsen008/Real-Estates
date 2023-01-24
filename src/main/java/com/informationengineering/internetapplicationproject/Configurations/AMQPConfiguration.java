package com.informationengineering.internetapplicationproject.Configurations;

import com.informationengineering.internetapplicationproject.Utils.Const;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AMQPConfiguration {

    @Bean
    Queue createQueue() {
        return new Queue(Const.QUEUE_NAME, false);
    }

    @Bean
    TopicExchange createExchanger() {
        return new TopicExchange(Const.EXCHANGE_NAME);
    }


    @Bean
    Binding createBinding(Queue queue, TopicExchange exchanger) {
        return BindingBuilder.bind(queue).to(exchanger).with("sender.*");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}
