package com.bortolettotech.graphql.repository.impl;

import com.bortolettotech.graphql.model.Address;
import com.bortolettotech.graphql.model.User;
import com.bortolettotech.graphql.model.UserData;
import com.bortolettotech.graphql.repository.UserRepositoryI;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepositoryI {

    private final HashMap<String, User> userMap = new HashMap<>();

    @Override
    public Collection<User> getUsers() {
        return userMap.values();
    }

    @Override
    public Optional<User> getUserById(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public Optional<UserData> getUserDataByUserId(String id) {
        return userMap.values().stream()
                .filter(user -> user.getUserData() != null && user.getUserData().getUserId().equals(id))
                .map(User::getUserData)
                .findFirst();
    }

    @Override
    public Optional<Address> getAddressByUserId(String id) {
        return userMap.values().stream()
                .filter(user -> user.getUserData() != null && user.getUserData().getUserId().equals(id))
                .map(User::getUserData)
                .map(UserData::getAddress)
                .findFirst();
    }

    @Override
    public Collection<User> addUser(User userInput, UserData userDataInput) {
        if (userInput == null || userDataInput == null) {
            throw new IllegalArgumentException("User and UserData cannot be null");
        }

        if (userMap.values().stream()
                .anyMatch(user -> user.getSubKeycloak() != null && user.getSubKeycloak().equals(userInput.getSubKeycloak()))) {
            throw new IllegalArgumentException("User with the same Keycloak sub already exists");
        }

        if (userMap.values().stream()
                .anyMatch(user -> user.getTelephone() != null && user.getTelephone().equals(userInput.getTelephone()))) {
            throw new IllegalArgumentException("User with the same telephone number already exists");
        }

        if (userMap.values().stream()
                .anyMatch(user -> user.getUserData().getEmail() != null && user.getUserData().getEmail().equals(userDataInput.getEmail()))) {
            throw new IllegalArgumentException("User with the same email already exists");
        }

        if (userInput.getId() == null) {
            userInput.setId(UUID.randomUUID().toString());
        }

        if (userDataInput.getAddress() != null) {
            userDataInput.getAddress().setId(UUID.randomUUID().toString());
            userDataInput.getAddress().setUserId(userInput.getId());
        }

        userDataInput.setId(UUID.randomUUID().toString());
        userDataInput.setUserId(userInput.getId());
        userInput.setUserData(userDataInput);

        userMap.put(userInput.getId(), userInput);

        return userMap.values();
    }

    @Override
    public Collection<User> deleteUser(String id) {
        if (id == null || !userMap.containsKey(id)) {
            throw new IllegalArgumentException("User with the given ID does not exist");
        }
        userMap.remove(id);
        return userMap.values();
    }
}
