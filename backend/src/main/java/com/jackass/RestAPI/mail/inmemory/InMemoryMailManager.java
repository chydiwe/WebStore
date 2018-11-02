package com.jackass.RestAPI.mail.inmemory;

import com.jackass.RestAPI.mail.MailManager;
import com.jackass.RestAPI.conf.ConditionsConfig;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(ConditionsConfig.InMemoryCondition.class)
public class InMemoryMailManager implements MailManager {
    @Override
    public void sendToken(String to, String token) {
    }
}
