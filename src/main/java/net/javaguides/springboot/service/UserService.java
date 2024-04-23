package net.javaguides.springboot.service;

import net.javaguides.springboot.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);
    User updateUser(Long id, User user);
    User loginUser(String username, String password);

}
