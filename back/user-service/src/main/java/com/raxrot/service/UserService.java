package com.raxrot.service;

import com.raxrot.exception.UserException;
import com.raxrot.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id) throws UserException;
    List<User> getAllUsers();
    User updateUser(Long id,User user) throws UserException;
    void deleteUser(Long id) throws UserException;
}
