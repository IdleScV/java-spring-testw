package com.example.demo.service;

import com.example.demo.model.User;

import com.example.demo.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public User updateUser(Long id, User updatedUser) {
    Optional<User> optionalUser = userRepository.findById(id);
    if (optionalUser.isPresent()) {
        User user = optionalUser.get();
        
        // Check and update the name field if it is passed
        String updatedName = updatedUser.getName();
        if (updatedName != null) {
            user.setName(updatedName);
        }
        
        // Check and update the email field if it is passed
        String updatedEmail = updatedUser.getEmail();
        if (updatedEmail != null) {
            user.setEmail(updatedEmail);
        }
        
        // Update other fields as needed
        
        return userRepository.save(user);
    }
    return null;
}
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }
}
