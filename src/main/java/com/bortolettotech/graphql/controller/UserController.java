package com.bortolettotech.graphql.controller;

import com.bortolettotech.graphql.model.Address;
import com.bortolettotech.graphql.model.User;
import com.bortolettotech.graphql.model.UserData;
import com.bortolettotech.graphql.repository.impl.UserRepositoryImpl;
import com.bortolettotech.graphql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @QueryMapping
    public Collection<User> getUsers() {
        return userService.getUsers();
    }

    @QueryMapping
    public User getUserById(@Argument String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        return userService.getUserById(id);
    }

    @QueryMapping
    public UserData getUserDataByUserId(@Argument String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        return userService.getUserDataByUserId(id);
    }

    @QueryMapping
    public Address getAddressByUserId(@Argument String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        return userService.getAddressByUserId(id);
    }

    @MutationMapping
    public Collection<User> addUser(@Argument User userInput, @Argument UserData userDataInput) {
        if (userInput == null || userDataInput == null) {
            throw new IllegalArgumentException("User and UserData cannot be null");
        }
        return userService.addUser(userInput, userDataInput);
    }

    @MutationMapping
    public Collection<User> deleteUser(@Argument String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        return userService.deleteUser(id);
    }
}
