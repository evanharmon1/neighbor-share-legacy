package org.evanharmon.neighborshare.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class EmailServiceImpl {

    @Autowired
    public JavaMailSender emailSender;

    public void sendSimpleMessage(
            String to, String from, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(from);
        message.setSubject(subject);
        message.setText(body);
        message.setReplyTo(from);
        emailSender.send(message);
    }

}
