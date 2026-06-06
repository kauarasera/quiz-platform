package com.quizplatform.quiz_platform.service;

import com.quizplatform.quiz_platform.entity.User;
import com.quizplatform.quiz_platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        return userRepository.save(user);
    }

    public User update(UUID id, String name) {
        User user = findById(id);
        user.setName(name);
        return userRepository.save(user);
    }

    public void delete(UUID id) {
        User user = findById(id);
        userRepository.delete(user);
    }
}