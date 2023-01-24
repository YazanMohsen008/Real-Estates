package com.informationengineering.internetapplicationproject.Aop;


import com.informationengineering.internetapplicationproject.Models.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Aspect
@Component
public class Log {

    Logger log = LoggerFactory.getLogger(com.informationengineering.internetapplicationproject.Aop.Log.class);
    File logFile = new File("Logfile.txt");
    FileOutputStream logWriter;
    @Autowired
    private ServletWebServerApplicationContext webServerAppCtxt;

    public Log() {
        {
            try {
                logWriter = new FileOutputStream(logFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    @Pointcut("within(@org.springframework.stereotype.Service *)&& execution(* *..update*(..))&& execution(* *..delete*(..))")
    public void repositoryClassMethods() {
    }

    @Around("repositoryClassMethods()")
    public Object get(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("PORT:" + webServerAppCtxt.getWebServer().getPort());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            String logUser = "user: " + user.getUsername() + "\t\t\t\t";
            String logAction = "Action : " + joinPoint.getSignature().getName() + "\n";
            try {
                logWriter.write(logUser.getBytes());
                logWriter.write(logAction.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info(logUser);
            log.info(logAction);
        }
        Object value = null;
        try {
            value = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        }
        return value;
    }
}
