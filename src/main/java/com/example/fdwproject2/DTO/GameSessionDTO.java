package com.example.fdwproject2.DTO;

import com.example.fdwproject2.model.enums.GameStatus;
import lombok.Data;
import lombok.Builder;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class GameSessionDTO {
    private List<PlayerDTO> players;
    private ChallengeDTO currentChallenge;
    private GameStatus status;
    private Map<Long, Integer> scores;
    private int currentRound;
    private int timeRemaining;

    public GameSessionDTO() {
    }

    public GameSessionDTO(List<PlayerDTO> players, ChallengeDTO currentChallenge, GameStatus status, Map<Long, Integer> scores, int currentRound, int timeRemaining) {
        this.players = players;
        this.currentChallenge = currentChallenge;
        this.status = status;
        this.scores = scores;
        this.currentRound = currentRound;
        this.timeRemaining = timeRemaining;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }

    public ChallengeDTO getCurrentChallenge() {
        return currentChallenge;
    }

    public void setCurrentChallenge(ChallengeDTO currentChallenge) {
        this.currentChallenge = currentChallenge;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public Map<Long, Integer> getScores() {
        return scores;
    }

    public void setScores(Map<Long, Integer> scores) {
        this.scores = scores;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }
}
