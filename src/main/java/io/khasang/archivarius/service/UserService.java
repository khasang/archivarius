package io.khasang.archivarius.service;

import io.khasang.archivarius.dao.UserDAO;
import io.khasang.archivarius.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UserService {
    @Autowired
    UserDAO userDAO;

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    public List<User> getUserList() {
        return userDAO.getUserList();
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    public void deleteUserById(int id) {
        userDAO.deleteUserById(id);
    }
}
