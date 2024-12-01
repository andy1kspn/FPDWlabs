package com.example.fdwproject2.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.example.fdwproject2.model.enums.GameStatus;
@Entity
@Table(name = "game_sessions")
@Data
public class GameSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @JsonManagedReference
    @OneToMany(mappedBy = "gameSession", cascade = CascadeType.ALL)
    private List<Player> players = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "current_challenge_id")
    private Challenge currentChallenge;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameStatus status = GameStatus.WAITING;

    @Column(nullable = false)
    private int currentRound;

    @Column(nullable = false)
    private long roundStartTime;

    @ElementCollection
    @CollectionTable(name = "session_scores",
            joinColumns = @JoinColumn(name = "session_id"))
    @MapKeyColumn(name = "player_id")
    @Column(name = "score")
    private Map<Long, Integer> scores = new HashMap<>();


    public boolean areAllPlayersReady() {
        return players != null &&
                players.size() == 3 &&
                players.stream().allMatch(Player::isReady);
    }

    public boolean isFull() {
        return players != null && players.size() >= 3;
    }

    public void addPlayer(Player player) {
        if (players == null) {
            players = new ArrayList<>();
        }
        if (!isFull()) {
            player.setGameSession(this);
            players.add(player);
        }
    }

    public boolean canJoin() {
        return !isFull() && status == GameStatus.WAITING;
    }
}