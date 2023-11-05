package com.example.user_management;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> allAllUsers() {
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") ObjectId _id) {
        return new ResponseEntity<Optional<User>>(userService.getUserById(_id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.OK);
    }

   @PatchMapping("/{id}")
    public ResponseEntity<User> partialUpdateUser(@PathVariable("id") ObjectId _id, @RequestBody User updatedUser) {
        Optional<User> existingUserOptional = userService.getUserById(_id);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            if (updatedUser.getUsername() != null) {
                existingUser.setUsername(updatedUser.getUsername());
            }
            if (updatedUser.getName() != null) {
                existingUser.setName(updatedUser.getName());
            }
            if (updatedUser.getSurname() != null) {
                existingUser.setSurname(updatedUser.getSurname());
            }
            if (updatedUser.getCountry() != null) {
                existingUser.setCountry(updatedUser.getCountry());
            }
            // Add similar checks for other fields as needed

            User savedUser = userService.saveUser(existingUser);
            return new ResponseEntity<User>(savedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
