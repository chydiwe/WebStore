package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.User;

public interface UserRepository {

    User getUserByEmailAndPassword(String email, String password);

    User getUserByEmail(String email);

    User getUserById(int id);

    User save(User user);

    void delete(User user);

}
