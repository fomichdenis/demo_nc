package com.example.demo_nc.controller;

import com.example.demo_nc.CustomException;
import com.example.demo_nc.model.User;
import com.example.demo_nc.model.UsernameAndPassword;
import com.example.demo_nc.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {
    @Autowired
    private IAuthService authService;

    @PostMapping("/signup")
    @ResponseBody
    public String signUpUser(@RequestBody UsernameAndPassword usernameAndPassword) {
        try {
            return authService.createUser(usernameAndPassword).toString();
        } catch (CustomException e) {
            switch (e.getErrorCode()) {
                case "CE-001": {
                    return e.getErrorDecription();
                }
                case "CE-002": {
                    return "FORMAT ERROR: " + e.getErrorDecription();
                }
                default: {
                    return "Some error";
                }
            }
        }
    }

    @PostMapping("/signin")
    @ResponseBody
    public String signInUser(@RequestBody UsernameAndPassword usernameAndPassword) {
        User user = authService.authUser(usernameAndPassword);
        if (user != null) {
            return user.toString();
        }
        return "NO USER FOUND";
    }
}
