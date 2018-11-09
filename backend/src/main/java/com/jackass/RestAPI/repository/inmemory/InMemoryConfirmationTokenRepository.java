package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.ConfirmationToken;
import com.jackass.RestAPI.repository.ConfirmationTokenRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(ConditionsConfig.InMemoryCondition.class)
public class InMemoryConfirmationTokenRepository extends InMemoryRepository<ConfirmationToken> implements ConfirmationTokenRepository {
    @Override
    public void delete(ConfirmationToken token) {
        table.remove(token);
    }

    @Override
    public void save(ConfirmationToken token) {
        table.add(token);
    }

    @Override
    public ConfirmationToken getConfirmationTokenById(int id) {
        return table.stream().filter(token -> token.getId() == id).findFirst().orElse(null);
    }

    @Override
    public ConfirmationToken getConfirmationTokenByToken(String token) {
        return table.stream().filter(tokenObj -> tokenObj.getToken().equals(token)).findFirst().orElse(null);
    }
}
