package com.example.demo_nc.controller;

import com.example.demo_nc.model.User;
import com.example.demo_nc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private IUserService userService;

    @GetMapping("/hello/text")
    @ResponseBody
    public String getHelloAsString() {
        return "hello";
    }

    @GetMapping("/hello")
    public String getHelloAsString2() {
        return "hello";
    }

    @GetMapping("/create/user/{id}/{name}")
    @ResponseBody
    public String createUser(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        User user = userService.createUser(id, name);
        return Optional.ofNullable(user)
                .map(User::getName)
                .orElse("NULL");
    }

    @GetMapping("/create/user")
    @ResponseBody
    public String createUser2(@RequestParam("id") Integer id, @RequestParam("name") String name) {
        User user = userService.createUser(id, name);
        return Optional.ofNullable(user)
                .map(User::getName)
                .orElse("NULL");
    }

    @PostMapping("/create/user")
    @ResponseBody
    public String createUser3(@RequestBody User user) {
        userService.save(user);
        return user.toString();
    }

    @GetMapping("/users")
    @ResponseBody
    public String getAllUsers() {
        return userService.getAllUsers().toString();
    }

    @GetMapping("/count")
    @ResponseBody
    public String countUsers() {
        return String.valueOf(userService.getAllUsers().size());
    }
}
