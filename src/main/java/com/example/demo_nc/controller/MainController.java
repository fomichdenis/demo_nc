package com.example.demo_nc.controller;

import com.example.demo_nc.model.User;
import com.example.demo_nc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.management.remote.JMXAuthenticator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private IUserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

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

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/registration")
    public String getRegistration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {
        System.out.println(bindingResult.toString());
        if (bindingResult.hasErrors()) {
            System.out.println("return1");
            return "registration";
        }
        if (!userService.saveUser(userForm)){
            System.out.println("return2");
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLogin(Model model, @AuthenticationPrincipal User authenticatedUser) {
        System.out.println(model.toString());
        if (Objects.nonNull(authenticatedUser)) {
            return "redirect:/user";
        }
        model.addAttribute("userForm", new User());
        return "login";
    }

    @PostMapping("/login")
    public String setLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("userForm") User user,
                           BindingResult result) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword(), user.getAuthorities());
        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        return "redirect:/user";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "/admin";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error";
    }

    @GetMapping("/")
    public String home1() {
        return "/home";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }
}
