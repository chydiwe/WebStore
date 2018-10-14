package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.ConfirmationToken;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = ConfirmationToken.class, idClass = Integer.class)
public interface ConfirmationTokenRepository {

    void deleteByToken(String token);

    void save(ConfirmationToken token);

}
