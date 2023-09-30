package com.example.demobank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public static JavaMailSenderImpl getMailConfig(){
        JavaMailSenderImpl emailConfig = new JavaMailSenderImpl();

        Properties properties = emailConfig.getJavaMailProperties();
        //properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtps.auth", "true");
        //properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");
        properties.put("mail.from", "a12344321123@mail.ru");

        emailConfig.setHost("smtp.mail.ru");
        emailConfig.setPort(465);
        emailConfig.setUsername("a12344321123@mail.ru");
        emailConfig.setPassword("jWenBvbXbYBeuxsYvJSt");
        emailConfig.setProtocol("smtps");

        return emailConfig;
    }
}
