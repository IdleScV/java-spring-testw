package com.example.demo.seed;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataSeeder {

    private final UserRepository userRepository;

    public DataSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void seedData() {
        // Check if data already exists to avoid duplicating seed data
        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setName("John Doe");
            user1.setEmail("john@example.com");
            userRepository.save(user1);

            User user2 = new User();
            user2.setName("Jane Smith");
            user2.setEmail("jane@example.com");
            userRepository.save(user2);

            // Add more users as needed
        }
    }
}
