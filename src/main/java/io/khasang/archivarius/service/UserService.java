package io.khasang.archivarius.service;

import io.khasang.archivarius.entity.User;
import io.khasang.archivarius.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserById(int id) {
        return userRepository.findOne(id);
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByLogin(username);
    }
}
