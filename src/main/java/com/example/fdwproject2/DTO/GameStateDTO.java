package com.example.fdwproject2.DTO;

import com.example.fdwproject2.model.enums.GameStatus;
import lombok.Data;
import lombok.Builder;
import java.util.Map;

@Data
@Builder
public class GameStateDTO {
    private boolean isGameFull;
    private int connectedPlayers;
    private GameStatus status;
    private Map<Long, Integer> playerScores;
    private int timeRemaining;

    public GameStateDTO() {
    }

    public GameStateDTO(boolean isGameFull, int connectedPlayers, GameStatus status, Map<Long, Integer> playerScores, int timeRemaining) {
        this.isGameFull = isGameFull;
        this.connectedPlayers = connectedPlayers;
        this.status = status;
        this.playerScores = playerScores;
        this.timeRemaining = timeRemaining;
    }

    public boolean isGameFull() {
        return isGameFull;
    }

    public void setGameFull(boolean gameFull) {
        isGameFull = gameFull;
    }

    public int getConnectedPlayers() {
        return connectedPlayers;
    }

    public void setConnectedPlayers(int connectedPlayers) {
        this.connectedPlayers = connectedPlayers;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public Map<Long, Integer> getPlayerScores() {
        return playerScores;
    }

    public void setPlayerScores(Map<Long, Integer> playerScores) {
        this.playerScores = playerScores;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }
}