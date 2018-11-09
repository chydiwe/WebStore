package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.ConfirmationToken;

public interface ConfirmationTokenRepository {

    void delete(String token);

    void save(ConfirmationToken token);

    ConfirmationToken getTokenById(int id);

}
