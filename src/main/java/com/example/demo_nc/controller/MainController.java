package com.example.demo_nc.controller;

import com.example.demo_nc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private List<User> users = new ArrayList<>();

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
        User user = new User();
        user.setId(id);
        user.setName(name);
        users.add(user);
        return user.toString();
    }

    @GetMapping("/create/user")
    @ResponseBody
    public String createUser2(@RequestParam("id") Integer id, @RequestParam("name") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        users.add(user);
        return user.toString();
    }

    @PostMapping("/create/user")
    @ResponseBody
    public String createUser3(@RequestBody User user) {
        users.add(user);
        return user.toString();
    }

    @GetMapping("/users")
    @ResponseBody
    public String getAllUsers() {
        return users.toString();
    }

    @GetMapping("/count")
    @ResponseBody
    public String countUsers() {
        return String.valueOf(users.size());
    }
}
