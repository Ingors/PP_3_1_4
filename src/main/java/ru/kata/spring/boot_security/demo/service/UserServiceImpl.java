package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User passwordCoder(User user) {
        user.setPass(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getById(id);
    }

    public void addUser(User user) {
        userRepository.save(passwordCoder(user));
    }

    @Override
    public void updateUser(User user) {
        User userToBeUpdated = userRepository.getById(user.getId());
        userToBeUpdated.setLogin(user.getUsername());
        userToBeUpdated.setPass(user.getPassword());
        userToBeUpdated.setSurname(user.getSurname());
        userToBeUpdated.setAge(user.getAge());
        userToBeUpdated.setPhone(user.getPhone());
        userToBeUpdated.setRoles(user.getRoles());
        userRepository.flush();
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}