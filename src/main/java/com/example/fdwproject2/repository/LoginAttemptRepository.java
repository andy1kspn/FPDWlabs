package com.example.fdwproject2.repository;

import com.example.fdwproject2.model.LoginAttempt;
import com.example.fdwproject2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {
    List<LoginAttempt> findByUserOrderByTimestampDesc(User user);
    List<LoginAttempt> findByUserIdOrderByTimestampDesc(Long userId);
}