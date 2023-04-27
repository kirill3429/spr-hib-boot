package com.example.demo.dao;

import com.example.demo.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }


    @Transactional
    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByID(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    public void removeUserByID(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
    @Transactional(readOnly = true)
    @Override
    public List<User> getUsersList() {
        return entityManager.createQuery("SELECT users FROM User users", User.class).getResultList();
    }

    @Transactional
    @Override
    public void update(Long id, User user) {
        User userToUpdate = entityManager.find(User.class, id);
        userToUpdate.setName(user.getName());
        userToUpdate.setAge(user.getAge());
    }
}
