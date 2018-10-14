package com.jackass.RestAPI.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Component
public class MailManager {

    @Value("${mail.host}")
    private String host;
    @Value("${mail.port}")
    private String port;
    @Value("${mail.email}")
    private String email;
    @Value("${mail.password}")
    private String password;

    private Session session;

    @PostConstruct
    public void init() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        session.setDebug(false);
    }

    public void sendToken(String to, String token){
        try{
            InternetAddress emailFrom = new InternetAddress(email);
            InternetAddress emailTo   = new InternetAddress(to);
            Message message = new MimeMessage(session);
            message.setFrom(emailFrom);
            message.setRecipient(Message.RecipientType.TO, emailTo);
            message.setSubject("Magic Stationery.");

            Multipart mmp = new MimeMultipart();
            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent("http://localhost:3000/confirming?token=" + token, "text/plain; charset=utf-8");
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