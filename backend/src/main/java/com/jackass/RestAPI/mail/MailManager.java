package com.jackass.RestAPI.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Component
@PropertySource("mail.properties")
public class MailManager {

    @Autowired
    private EmailAuthenticator authenticator;

    @Value("${mail.host}")
    private String host;
    @Value("${mail.port}")
    private String port;

    private Session session;

    @PostConstruct
    public void init() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        session = Session.getDefaultInstance(properties, authenticator);
        session.setDebug(false);
    }

    public void sendToken(String to, String token){
        try{
            InternetAddress emailFrom = new InternetAddress(authenticator.getEmail());
            InternetAddress emailTo   = new InternetAddress(to);
            Message message = new MimeMessage(session);
            message.setFrom(emailFrom);
            message.setRecipient(Message.RecipientType.TO, emailTo);
            message.setSubject("Magic Stationery confirming.");

            Multipart mmp = new MimeMultipart();
            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent("http://localhost:8080?token=" + token, "text/plain; charset=utf-8");
            mmp.addBodyPart(bodyPart);
            message.setContent(mmp);
            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}