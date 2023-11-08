package com.example.user_management;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Optional<User> getUserById(ObjectId _id) {
        return userRepository.findById(_id);
    }

    public void deleteUserById(ObjectId _id) {
        userRepository.deleteById(_id);
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    public User saveUser(User user) {
        return mongoTemplate.save(user);
    }
}
