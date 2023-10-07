package com.example.demobank.util.mail_bot;

import com.example.demobank.configuration.MailConfiguration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class MailBot {

    public static void sendEmailMessage(String from, String to, String topic, String text) {

        JavaMailSender sender = MailConfiguration.getMailSender();
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper htmlMessage = new MimeMessageHelper(message, true);
            htmlMessage.setTo(to);
            htmlMessage.setFrom(from);
            htmlMessage.setSubject(topic);
            htmlMessage.setText(text, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        sender.send(message);
    }
}
