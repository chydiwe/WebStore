package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.ConfirmationToken;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = ConfirmationToken.class, idClass = Integer.class)
public interface ConfirmationTokenRepository {

    void delete(ConfirmationToken token);

    void save(ConfirmationToken token);

    ConfirmationToken getConfirmationTokenById(int id);

    ConfirmationToken getConfirmationTokenByToken(String token);

}
