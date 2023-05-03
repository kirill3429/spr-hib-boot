package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao dao;

    @Autowired
    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public User getUserByID(Long id) {
        return dao.getUserByID(id);
    }

    @Override
    public User getUserByName(String name) {
        return dao.getUserByName(name);
    }

    @Override
    public void removeUserByID(Long id) {
        dao.removeUserByID(id);
    }

    @Override
    public List<User> getUsersList() {
        return dao.getUsersList();
    }

    @Override
    public void update(Long id, User user) {
        dao.update(id, user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), user.getAuthorities());
    }

}
