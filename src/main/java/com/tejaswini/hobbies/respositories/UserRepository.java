package com.tejaswini.hobbies.respositories;

import com.tejaswini.hobbies.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ 'email' : ?0 }")
    List<User> findUserByEmail(String email);


}
