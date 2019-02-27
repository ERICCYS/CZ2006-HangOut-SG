package com.skyforce.SkyForceWebService.controller;

import com.skyforce.SkyForceWebService.model.User;
import com.skyforce.SkyForceWebService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    private AtomicLong nextId = new AtomicLong();

    @GetMapping("/user")
    public String userAll() {
        List<User> users = userService.findAll();
        return "there are a brunch of users " + users;
    }

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        return "find user by id: " + user;
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@Valid @RequestBody User user) {
        user.setId(nextId.incrementAndGet());
        return "Post success" + userService.save(user);
    }

    @PutMapping("/user/{id}")
    public String updateUserById(@PathVariable("id") Long id, @Valid @RequestBody User user) {

        User oldUser = userService.findUserById(id);

        oldUser.setDob(user.getDob());
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setGender(user.getGender());
        oldUser.setPhone(user.getPhone());

        User updatedUser = userService.save(oldUser);
        return "updated User" + updatedUser;
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long userId) {
        User user = userService.findUserById(userId);
        userService.delete(user);
        return ResponseEntity.ok().build();
    }
}
