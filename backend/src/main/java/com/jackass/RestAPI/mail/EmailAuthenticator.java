package com.jackass.RestAPI.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

@Component
@PropertySource("mail.properties")
public class EmailAuthenticator extends Authenticator {

    @Value("${mail.email}")
    private String email;
    @Value("${mail.password}")
    private String password;

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(email, password);
    }

    public String getEmail() {
        return email;
    }
}