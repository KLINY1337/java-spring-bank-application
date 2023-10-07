package com.example.demobank.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {

    @Value("${mail.configuration.server.host}")
    private static String MAIL_SERVER_HOST;

    @Value("${mail.configuration.server.port}")
    private static int MAIL_SERVER_PORT;

    @Value("${mail.configuration.server.protocol}")
    private static String MAIL_SERVER_PROTOCOL;

    @Value("${mail.configuration.server.smtps.auth}")
    private static boolean MAIL_SERVER_SMTPS_AUTH;

    @Value("${mail.configuration.server.debug}")
    private static boolean MAIL_SERVER_DEBUG;

    @Value("${mail.configuration.sender.name}")
    private static String MAIL_SENDER_NAME;

    @Value("${mail.configuration.sender.email}")
    private static String MAIL_SENDER_EMAIL;

    @Value("${mail.configuration.sender.password}")
    private static String MAIL_SENDER_PASSWORD;

    @Bean
    public static JavaMailSenderImpl getMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(MAIL_SERVER_HOST);
        mailSender.setPort(MAIL_SERVER_PORT);
        mailSender.setUsername(MAIL_SENDER_EMAIL);
        mailSender.setPassword(MAIL_SENDER_PASSWORD);
        mailSender.setProtocol(MAIL_SERVER_PROTOCOL);

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtps.auth", MAIL_SERVER_SMTPS_AUTH);
        properties.put("mail.debug", MAIL_SERVER_DEBUG);
        properties.put("mail.from", MAIL_SENDER_NAME);

        return mailSender;
    }
}
