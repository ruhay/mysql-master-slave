package com.chuanchiai.controller;

import com.chuanchiai.pojo.User;
import com.chuanchiai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chuanchiai on 2020/7/19
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/query")
    public User query(Integer id){
        return userService.queryById(id);
    }

    @PostMapping("/update")
    public int update(@RequestBody User user){
        return userService.updateUser(user);
    }
}
