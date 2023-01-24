package com.informationengineering.internetapplicationproject.Services;

import com.informationengineering.internetapplicationproject.Models.Email;
import com.informationengineering.internetapplicationproject.Models.Estate;
import com.informationengineering.internetapplicationproject.Repositories.EstateRepository;
import com.informationengineering.internetapplicationproject.Utils.Const;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendEmail(Email email) {
        String routingKey = "sender.send";
        String exchangeName = Const.EXCHANGE_NAME;
        rabbitTemplate.convertAndSend(exchangeName,routingKey,email);
    }

}
