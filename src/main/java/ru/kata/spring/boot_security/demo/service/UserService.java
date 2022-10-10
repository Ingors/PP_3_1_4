package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers ();
    User getUserById (int id);
    void addUser (User user);
    void deleteUserById (int id);
    void updateUser (User user);
    User passwordCoder(User user);
}