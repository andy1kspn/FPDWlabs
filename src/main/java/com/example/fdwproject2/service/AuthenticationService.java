package com.example.fdwproject2.service;

import com.example.fdwproject2.DTO.UserRegistrationDto;
import com.example.fdwproject2.model.ActionType;
import com.example.fdwproject2.model.LoginAttempt;
import com.example.fdwproject2.model.User;
import com.example.fdwproject2.repository.LoginAttemptRepository;
import com.example.fdwproject2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final LoginAttemptRepository loginAttemptRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(UserRegistrationDto dto, String ipAddress) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(encryptPassword(dto.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);

        logAttempt(user, ipAddress, ActionType.REGISTER, true);
    }

    private String encryptPassword(String password) {
        String noVowels = password.replaceAll("[aeiouAEIOU]", "x");
        String firstEncryption = noVowels;
        return passwordEncoder.encode(firstEncryption);
    }

    private void logAttempt(User user, String ipAddress, ActionType action, boolean successful) {
        LoginAttempt attempt = new LoginAttempt();
        attempt.setUser(user);
        attempt.setIpAddress(ipAddress);
        attempt.setAction(action);
        attempt.setSuccessful(successful);
        attempt.setTimestamp(LocalDateTime.now());
        attempt.setDetails("Action performed by: " + user.getUsername());
        loginAttemptRepository.save(attempt);
    }

    public boolean authenticate(String username, String password, String ipAddress) {
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String processedPassword = password.replaceAll("[aeiouAEIOU]", "x");
            boolean isValid = passwordEncoder.matches(processedPassword, user.getPassword());

            logAttempt(user, ipAddress,
                    isValid ? ActionType.LOGIN : ActionType.FAILED_ATTEMPT,
                    isValid);

            return isValid;
        } catch (Exception e) {
            return false;
        }
    }

    public void logout(String username, String ipAddress) {
        userRepository.findByUsername(username).ifPresent(user ->
                logAttempt(user, ipAddress, ActionType.LOGOUT, true));
    }
}