package com.bortolettotech.graphql.service;

import com.bortolettotech.graphql.model.Address;
import com.bortolettotech.graphql.model.User;
import com.bortolettotech.graphql.model.UserData;
import com.bortolettotech.graphql.repository.impl.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    @Autowired
    private UserRepositoryImpl userRepository;

    public Collection<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUserById(String id) {
        return userRepository.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with the given ID does not exist"));
    }

    public UserData getUserDataByUserId(String id) {
        return userRepository.getUserDataByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException("User data for the given user ID does not exist"));
    }

    public Address getAddressByUserId(String id) {
        return userRepository.getAddressByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException("Address for the given user ID does not exist"));
    }

    public Collection<User> addUser(User user, UserData userData) {
        if (user == null || userData == null) {
            throw new IllegalArgumentException("User and UserData cannot be null");
        }
        return userRepository.addUser(user, userData);
    }

    public Collection<User> deleteUser(String id) {
        if (id == null || userRepository.getUserById(id).isEmpty()) {
            throw new IllegalArgumentException("User with the given ID does not exist");
        }
        return userRepository.deleteUser(id);
    }
}
