package com.tejaswini.hobbies.services;

import com.tejaswini.hobbies.models.User;
import com.tejaswini.hobbies.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User loginUser(String email, String password) throws IndexOutOfBoundsException{
        User user = userRepository.findUserByEmail(email).get(0);
        System.out.println(password);
        System.out.println(user.getPassword());
        System.out.println(passwordEncoder.encode(password));
        return passwordEncoder.matches(password, user.getPassword())? user: User.EMPTY;
    }

    public User registerUser(String email, String firstName, String lastName, String password)
    throws DuplicateKeyException {
        User user = new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(passwordEncoder.encode(password))
                .setEmptyLocation();
        System.out.println(password);
        System.out.println(passwordEncoder.encode(password));
        userRepository.insert(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
