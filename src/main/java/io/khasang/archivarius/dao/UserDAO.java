package io.khasang.archivarius.dao;

import io.khasang.archivarius.entity.Company;
import io.khasang.archivarius.entity.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    void deleteUserById(int id);

    User getUserById(int id);

    User getUserByName(String name);

    List<User> getUserList();
}
