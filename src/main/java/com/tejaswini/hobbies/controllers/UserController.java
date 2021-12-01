package com.tejaswini.hobbies.controllers;

import com.tejaswini.hobbies.models.User;
import com.tejaswini.hobbies.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users/login")
    public ResponseEntity<String> login(@RequestParam("email") String email,
                                        @RequestParam("password") String password){
        try {
            Boolean response = userService.loginUser(email, password);
            logger.info(String.valueOf(response));
            if (response)
                return new ResponseEntity<>("User successfully logged in", HttpStatus.OK);

            else return new ResponseEntity<>("User password incorrect",
                    HttpStatus.FORBIDDEN);
        }
        catch(IndexOutOfBoundsException e){
            logger.error(Arrays.toString(e.getStackTrace()));
            return new ResponseEntity<>("Email doesn't exist",
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/users/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            Boolean response = userService.registerUser(user.getEmail(), user.getFirstName(), user.getLastName(), user.getPassword());
            logger.info(String.valueOf(response));
            if (response)
                return new ResponseEntity<>("User successfully created", HttpStatus.CREATED);
            else return new ResponseEntity<>("User registration unsuccessful", HttpStatus.CONFLICT);
        } catch (Exception e) {
            logger.error(Arrays.toString(e.getStackTrace()));
            return new ResponseEntity<>("Something went wrong", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
