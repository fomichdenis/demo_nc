package com.example.demo_nc.service;

import com.example.demo_nc.CustomErrors;
import com.example.demo_nc.CustomException;
import com.example.demo_nc.model.User;
import com.example.demo_nc.model.UsernameAndPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private DBExample dbExample;

    @Override
    public User authUser(UsernameAndPassword usernameAndPassword) {
        List<User> users = this.dbExample.getCreatedUsers();
        for (User user: users) {
            if (user.getName().equals(usernameAndPassword.getUsername())
                    && user.getPassword().equals(usernameAndPassword.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User createUser(UsernameAndPassword usernameAndPassword) throws CustomException {
        if (usernameAndPassword.getPassword().length() < 7) {
            throw CustomErrors.PASSWORD_HAS_INVALID_LENGTH.getException();
        }
        User user = new User();
        user.setName(usernameAndPassword.getUsername());
        user.setPassword(usernameAndPassword.getPassword());
        return dbExample.createUser(user);
    }
}
