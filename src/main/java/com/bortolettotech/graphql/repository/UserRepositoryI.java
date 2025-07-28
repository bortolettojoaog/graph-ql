package com.bortolettotech.graphql.repository;

import com.bortolettotech.graphql.model.Address;
import com.bortolettotech.graphql.model.User;
import com.bortolettotech.graphql.model.UserData;

import java.util.Collection;
import java.util.Optional;

public interface UserRepositoryI {
    Collection<User> getUsers();
    Optional<User> getUserById(String id);
    Optional<UserData> getUserDataByUserId(String id);
    Optional<Address> getAddressByUserId(String id);
    Collection<User> addUser(User userInput, UserData userDataInput);
    Collection<User> deleteUser(String id);
}
