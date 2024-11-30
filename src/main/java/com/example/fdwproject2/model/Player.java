package com.example.fdwproject2.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private boolean isReady;

    private String currentAnswer;

    @ManyToOne
    @JoinColumn(name = "game_session_id")
    private GameSession gameSession;

    public Player(Long id) {
        this.id = id;
        this.score = 0;
        this.isReady = false;
    }
}