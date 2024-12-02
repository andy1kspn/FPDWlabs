package com.example.fdwproject2.service;

import com.example.fdwproject2.model.LoginAttempt;
import com.example.fdwproject2.model.User;
import com.example.fdwproject2.repository.LoginAttemptRepository;
import com.example.fdwproject2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final LoginAttemptRepository loginAttemptRepository;

    public List<LoginAttempt> getCurrentUserLoginAttempts() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return loginAttemptRepository.findByUserOrderByTimestampDesc(user);
    }
    @Transactional
    public void changeUserRole(Long userId, String newRole) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(newRole);
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}