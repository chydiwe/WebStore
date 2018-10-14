package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = User.class, idClass = Integer.class)
public interface UserRepository {

    User getUserByEmailAndPassword(String email, String password);

    User getUserByEmail(String email);

    void save(User user);

}
