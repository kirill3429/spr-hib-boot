package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;


public interface UserService extends UserDetailsService {
    void addUser(User user);
    User getUserByID(Long id);
    User getUserByName(String name);
    void removeUserByID(Long id);
    List<User> getUsersList();
    void update(Long id, User user);
}
