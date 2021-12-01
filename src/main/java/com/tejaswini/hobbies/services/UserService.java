package com.tejaswini.hobbies.services;

import com.tejaswini.hobbies.models.User;
import com.tejaswini.hobbies.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Boolean loginUser(String email, String password) throws IndexOutOfBoundsException{
        User user = userRepository.findUserByEmail(email).get(0);
        System.out.println(password);
        System.out.println(user.getPassword());
        System.out.println(passwordEncoder.encode(password));
        return passwordEncoder.matches(password, user.getPassword());
    }

    public Boolean registerUser(String email, String firstName, String lastName, String password){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        System.out.println(password);
        System.out.println(passwordEncoder.encode(password));
        try {
            userRepository.insert(user);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
