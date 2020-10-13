package com.example.demo_nc.service;

import com.example.demo_nc.CustomException;
import com.example.demo_nc.model.User;
import com.example.demo_nc.model.UsernameAndPassword;

public interface IAuthService {
    User authUser(UsernameAndPassword usernameAndPassword);
    User createUser(UsernameAndPassword usernameAndPassword) throws CustomException;
}
