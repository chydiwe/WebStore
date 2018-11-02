package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.User;
import com.jackass.RestAPI.repository.UserRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(ConditionsConfig.InMemoryCondition.class)
public class InMemoryUserRepository extends InMemoryRepository<User> implements UserRepository {

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return table.stream().filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password)).findFirst().orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return table.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    @Override
    public User getUserById(int id) {
        return table.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    @Override
    public User save(User user) {
        user.setId(id++);
        table.add(user);
        return user;
    }

    @Override
    public User delete(User user) {
        table.remove(user);
        return user;
    }
}
