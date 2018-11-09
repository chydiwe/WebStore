package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.ConfirmationToken;

public interface ConfirmationTokenRepository {

    void delete(ConfirmationToken token);

    void save(ConfirmationToken token);

    ConfirmationToken getConfirmationTokenById(int id);

    ConfirmationToken getConfirmationTokenByToken(String token);

}
