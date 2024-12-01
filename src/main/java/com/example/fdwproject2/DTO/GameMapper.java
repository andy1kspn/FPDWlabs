package com.example.fdwproject2.DTO;

import com.example.fdwproject2.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class GameMapper {
    public PlayerDTO toPlayerDTO(Player player) {
        return PlayerDTO.builder()
                .id(player.getId())
                .score(player.getScore())
                .isReady(player.isReady())
                .build();
    }

    public ChallengeDTO toChallengeDTO(Challenge challenge) {
        if (challenge == null) {
            return null;  // Returnăm null dacă challenge-ul este null
        }
        return ChallengeDTO.builder()
                .id(challenge.getId())
                .question(challenge.getQuestion())
                .type(challenge.getType())
                .timeLimit(challenge.getTimeLimit())
                .points(challenge.getPoints())
                .build();
    }

    public GameSessionDTO toGameSessionDTO(GameSession session) {
        if (session == null) {
            return null;
        }
        return GameSessionDTO.builder()
                .players(session.getPlayers() != null ?
                        session.getPlayers().stream()
                                .map(this::toPlayerDTO)
                                .collect(Collectors.toList()) :
                        new ArrayList<>())
                .currentChallenge(toChallengeDTO(session.getCurrentChallenge()))
                .status(session.getStatus())
                .scores(session.getScores())
                .currentRound(session.getCurrentRound())
                .timeRemaining(calculateTimeRemaining(session))
                .build();
    }

    private int calculateTimeRemaining(GameSession session) {
        if (session.getCurrentChallenge() == null) {
            return 0;
        }
        long currentTime = System.currentTimeMillis();
        long startTime = session.getRoundStartTime();
        int timeLimit = session.getCurrentChallenge().getTimeLimit();

        long elapsedSeconds = (currentTime - startTime) / 1000;
        return Math.max(0, timeLimit - (int)elapsedSeconds);
    }
}