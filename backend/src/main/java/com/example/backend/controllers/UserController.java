package com.example.backend.controllers;


import com.example.backend.daos.UserDao;
import com.example.backend.dtos.UserDto;
import com.example.backend.models.User;

import com.example.backend.repositories.UserRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    //Test this code to more fun
    public static Logger logger = LoggerFactory.getLogger(UserController.class);
    //

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDao userDao;

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> listUser = userRepository.findAll();
        if (listUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listUser, HttpStatus.OK);
    }

    @PatchMapping("/password/{userId}")
    public User updateUserPassword(
            @PathVariable("userId") Integer userId,
            @RequestBody UserDto userDto
    ) {
        return userDao.updateUserPassword(userId, userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody User requestUser) {
        String username = requestUser.getUsername();
        String password = requestUser.getPassword();

        User user = userDao.getUserByUsername(username);

        if (user == null || !password.equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        // Add JWT token generation logic here (optional).
        // Return token or additional user information as needed.
        return ResponseEntity.ok().body(user);
    }

    @RequestMapping(value = "/user/{username}=", method = RequestMethod.GET)
    public User findUserByUsername(@PathVariable("username") String username){
        User user = userRepository.findByUsername(username);
        if (user == null) {
            ResponseEntity.notFound().build();
        }
        return user;
    }

    @PostMapping
    public ResponseEntity<User> addNewUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(userDao.addNewUser(userDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        User updatedUser = userDao.updateUser(id, userDto);
        return updatedUser != null ? ResponseEntity.ok().body(updatedUser) : null;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PATCH)
    public User updatePatchUser (@PathVariable(value = "id") int userId,
                                 @Valid @RequestBody Map<String, Object> updates){
        User user = userRepository.findByUserId(userId);
        updates.forEach((key, value) -> {
            switch (key) {
                case "firstname" -> user.setFirstname((String) value);
                case "lastname" -> user.setLastname((String) value);
                case "username" -> user.setUsername((String) value);
                case "password" -> user.setPassword((String) value);
                case "email" -> user.setEmail((String) value);
                default -> throw new IllegalStateException("Unexpected value: " + key);
            }
        });
        return userRepository.save(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") int id) {
        User user = userRepository.findByUserId(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }
}
