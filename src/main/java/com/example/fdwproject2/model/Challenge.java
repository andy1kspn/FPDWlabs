package com.example.fdwproject2.model;

import javax.persistence.*;
import lombok.Data;
import com.example.fdwproject2.model.enums.ChallengeType;

@Entity
@Table(name = "challenges")
@Data
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String correctAnswer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChallengeType type;

    @Column(nullable = false)
    private Integer timeLimit;

    @Column(nullable = false)
    private Integer points;
}