package com.example.estudosDro.Controllers;

import com.example.estudosDro.Entities.UserEntity;
import com.example.estudosDro.Services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @GetMapping
    public List<UserEntity> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserEntity user){
        logger.debug("Registering user: {}", user);
        if (userService.findByUsername(user.getUsername()).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }
        if (userService.findByEmail(user.getEmail()).isPresent()){
            return ResponseEntity.status((HttpStatus.CONFLICT)).body("Email already exists");
        }
        UserEntity savedUser = userService.registerUser(user);
        logger.debug("Saved user: {}", savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered with success");
    }
}
