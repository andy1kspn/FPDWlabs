package com.example.fdwproject2.repository;

import com.example.fdwproject2.model.Submission;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByPlayerId(Long playerId);
    List<Submission> findByChallengeId(Long challengeId);
}