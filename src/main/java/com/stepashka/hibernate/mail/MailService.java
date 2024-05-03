package com.stepashka.hibernate.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private JavaMailSender emailSender;
    private final String SENDER_NAME = "Picasso.Goods@outlook.com";
    private final String MAIL_SUBJECT = "Picasso shop.";
    private final String MAIL_TEXT = "Your verification code for account activation: ";

    @Autowired
    public MailService(JavaMailSender javaMailSender){
        this.emailSender = javaMailSender;
    }

    public void sendSimpleMessage(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(SENDER_NAME);
        message.setTo(to);
        message.setSubject(MAIL_SUBJECT);
        message.setText(MAIL_TEXT + code);
        emailSender.send(message);
    }
}
