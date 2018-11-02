package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.ConfirmationToken;
import com.jackass.RestAPI.entity.User;
import org.springframework.data.repository.RepositoryDefinition;

public interface ConfirmationTokenRepository {

    void deleteByToken(String token);

    void save(ConfirmationToken token);

    ConfirmationToken getTokenById(int id);

}
