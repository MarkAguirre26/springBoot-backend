package com.javarun.springboot.service.impl;

import com.javarun.springboot.exception.ResourceNotFoundException;
import com.javarun.springboot.model.User;
import com.javarun.springboot.repository.UserRepository;
import com.javarun.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(this.getClass(),"User: getUserById", "id",id));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresentOrElse(
                user -> userRepository.deleteById(id),
                () -> {
                    throw new ResourceNotFoundException(this.getClass(), "User: deleteUser ", "id", id);
                }
        );
    }

    @Override
    public User updateUser(Long id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    user.setCn(id);
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ResourceNotFoundException(this.getClass(), "User: updateUser", "id", id));
    }

    @Override
    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && (Objects.equals(password, user.getPassword()))) {
            return user;
        } else {
            try {
                throw new AuthenticationException("Invalid username or password");
            } catch (AuthenticationException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
