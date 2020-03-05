package com.split.controller;

import com.split.entity.User;
import com.split.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/test")
@ResponseBody
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/get")
    public List<User> get(){
        return userService.findAll();
    }

    @GetMapping(value = "/add")
    public User add(){
        return userService.save();
    }
}
