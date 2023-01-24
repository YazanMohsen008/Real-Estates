package com.informationengineering.internetapplicationproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class InternetApplicationProjectApplication extends ServletWebServerApplicationContext {

    public static void main(String[] args) {
        SpringApplication.run(InternetApplicationProjectApplication.class, args);
    }

}
