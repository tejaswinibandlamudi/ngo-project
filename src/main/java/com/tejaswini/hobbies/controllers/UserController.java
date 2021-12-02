package com.tejaswini.hobbies.controllers;

import com.tejaswini.hobbies.models.User;
import com.tejaswini.hobbies.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
    public ResponseEntity<User> login(@RequestParam("email") String email,
                                        @RequestParam("password") String password){
        try {
            User user = userService.loginUser(email, password);
            logger.info(String.valueOf(user));
            if (!user.equals(User.EMPTY))
                return new ResponseEntity<>(user, HttpStatus.OK);

            else return new ResponseEntity<>(user,
                    HttpStatus.FORBIDDEN);
        }
        catch(IndexOutOfBoundsException e){
            logger.error(Arrays.toString(e.getStackTrace()));
            return new ResponseEntity<>(User.EMPTY,
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        try {
            User user1 = userService.registerUser(user.getEmail(), user.getFirstName(), user.getLastName(), user.getPassword());
            logger.info(String.valueOf(user1));
            return new ResponseEntity<>(user1, HttpStatus.CREATED);
        } catch (DuplicateKeyException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
            return new ResponseEntity<>(User.EMPTY, HttpStatus.CONFLICT);
        } catch (Exception e) {
            logger.error(Arrays.toString(e.getStackTrace()));
            System.out.println(Arrays.toString(e.getStackTrace()));
            return new ResponseEntity<>(User.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
