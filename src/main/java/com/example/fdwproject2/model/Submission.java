package com.example.fdwproject2.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "submissions")
@Data
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Column(name = "player_id", insertable = false, updatable = false)
    private Long playerId;

    @ManyToOne
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    @Column(name = "challenge_id", insertable = false, updatable = false)
    private Long challengeId;

    @Column(columnDefinition = "TEXT")
    private String answer;

    @Column(nullable = false)
    private LocalDateTime submittedAt;

    @Column(nullable = false)
    private boolean isCorrect;

    @Column(nullable = false)
    private int timeTaken;
}