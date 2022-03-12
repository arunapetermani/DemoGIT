package com.example.demo.service;

import com.example.demo.Entity.User;
import com.example.demo.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> createUsers(List<User> users) {
        List<User> returnUser = new ArrayList<>();

        List<User> newList = users.stream().filter(user -> {
            return !user.getAddress().equals("Raman");
        }).collect(Collectors.toList());

      List<User> newList1 = newList.stream().map(user -> {
           user.setName(user.getName().toUpperCase());
          return user;
      }).collect(Collectors.toList());

        newList1.forEach(user -> {
            returnUser.add(userRepository.save(user));
        });
        return returnUser;
    }


    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        User oldUser;
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            oldUser = optionalUser.get();
            oldUser.setName(user.getName());
            oldUser.setAddress(user.getAddress());
            userRepository.save(oldUser);
            return oldUser;
        } else {
            return new User();
        }

    }

    /**
     * @param id
     * @return
     */
    public String deleteUserById(int id) {
        userRepository.deleteById(id);
        return "User got deleted";
    }


}
