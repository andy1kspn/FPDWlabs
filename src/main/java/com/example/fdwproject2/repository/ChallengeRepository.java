package com.example.fdwproject2.repository;

import com.example.fdwproject2.model.Challenge;
import com.example.fdwproject2.model.enums.ChallengeType;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    List<Challenge> findByType(ChallengeType type);
}