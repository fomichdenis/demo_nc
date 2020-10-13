package com.example.demo_nc.service;

import com.example.demo_nc.CustomErrors;
import com.example.demo_nc.CustomException;
import com.example.demo_nc.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
/**
 * Please, create interface and implement it with class (@Service)
 */
public class DBExample {
    private List<User> createdUsers = new ArrayList<>();

    public List<User> getCreatedUsers() {
        return createdUsers;
    }

    public void setCreatedUsers(List<User> createdUsers) {
        this.createdUsers = createdUsers;
    }

    public User createUser(User user) throws CustomException {
        if (this.createdUsers.stream()
                .filter(u -> u.getName().equals(user.getName()))
                .findFirst()
                .orElse(null) == null) {
            user.setId(getId());
            createdUsers.add(user);
            return user;
        }
        else {
            throw CustomErrors.USER_WITH_SUCH_USERNAME_FOUND.getException();
        }
    }

    private Integer getId() {
        return this.createdUsers.size() + 1;
    }
}
