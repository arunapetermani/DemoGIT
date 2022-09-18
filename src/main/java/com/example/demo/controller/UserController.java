
package com.example.demo.controller;

import com.example.demo.Entity.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;


    public UserController() throws IOException {
    }
//Pull request for local repository

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @PostMapping("/addUsers")
    public List<User> addUsers() throws IOException {
        ObjectMapper objMapper = new ObjectMapper();
        File fileName = new File("/Users/arunapeter/IdeaProjects/demo/target/User.json");
        List<User> userList = objMapper.readValue(fileName, new TypeReference<List<User>>() {
        });
        //userList.stream()   .forEach(user-> userService.createUser(user));
        return userService.createUsers(userList);
    }
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }
    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
    @DeleteMapping("/delete/{id}")
        public String DeleteUser(@PathVariable int id) {
        return userService.deleteUserById(id);
    }
}
