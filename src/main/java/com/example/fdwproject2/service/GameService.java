package com.example.fdwproject2.service;

import com.example.fdwproject2.DTO.*;
import com.example.fdwproject2.model.*;
import com.example.fdwproject2.model.enums.GameStatus;
import com.example.fdwproject2.repository.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GameService {
    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private GameMapper gameMapper;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    public boolean isGameAvailable() {
        return gameSessionRepository.isGameAvailable();
    }

    public Player addNewPlayer() {
        if (gameSessionRepository.getCurrentSession().getStatus() != GameStatus.WAITING) {
            return null;
        }

        Player player = playerRepository.createPlayer();
        if (player != null) {
            GameSession session = gameSessionRepository.getCurrentSession();
            session.addPlayer(player);
            gameSessionRepository.saveSession(session);
        }
        return player;
    }

    public boolean shouldStartGame() {
        GameSession session = gameSessionRepository.getCurrentSession();
        return session.isFull() && session.areAllPlayersReady();
    }

    public void startGame() {
        GameSession session = gameSessionRepository.getCurrentSession();
        session.setStatus(GameStatus.IN_PROGRESS);
        session.setCurrentRound(1);
        session.setCurrentChallenge(challengeService.getNextChallenge());
        session.setRoundStartTime(System.currentTimeMillis());
        gameSessionRepository.saveSession(session);

        messagingTemplate.convertAndSend("/topic/game-updates", getCurrentGameState());
        messagingTemplate.convertAndSend("/topic/challenge", session.getCurrentChallenge());
    }

    public void processSubmission(Submission submission) {
        if (submission == null) {
            return;
        }

        GameSession session = gameSessionRepository.getCurrentSession();
        if (session.getStatus() != GameStatus.IN_PROGRESS) {
            return;
        }

        Player player = playerRepository.getPlayer(submission.getPlayerId());
        if (player == null) {
            return;
        }

        if (challengeService.verifySubmission(submission)) {
            int timeBonus = calculateTimeBonus(session.getRoundStartTime());
            int score = session.getCurrentChallenge().getPoints() + timeBonus;
            player.setScore(player.getScore() + score);
            playerRepository.updatePlayer(player);
        }
    }

    private int calculateTimeBonus(long startTime) {
        long currentTime = System.currentTimeMillis();
        long secondsTaken = (currentTime - startTime) / 1000;
        return Math.max(0, 30 - (int)secondsTaken);
    }

    public GameSessionDTO getCurrentGameState() {
        return gameMapper.toGameSessionDTO(gameSessionRepository.getCurrentSession());
    }

    public void handlePlayerDisconnect(Long playerId) {
        GameSession session = gameSessionRepository.getCurrentSession();
        if (session.getStatus() == GameStatus.IN_PROGRESS) {
            session.setStatus(GameStatus.FINISHED);
            gameSessionRepository.saveSession(session);
        } else {
            session.getPlayers().removeIf(p -> p.getId().equals(playerId));
            playerRepository.reset();
            gameSessionRepository.saveSession(session);
        }
    }
    public GameSession getCurrentSession() {
        return gameSessionRepository.getCurrentSession();
    }
    public boolean isGameInProgress() {
        GameSession session = gameSessionRepository.getCurrentSession();
        return session != null && session.getStatus() == GameStatus.IN_PROGRESS;
    }

    public int getRemainingTime() {
        GameSession session = gameSessionRepository.getCurrentSession();
        if (!isGameInProgress() || session.getCurrentChallenge() == null) {
            return 0;
        }

        long currentTime = System.currentTimeMillis();
        long startTime = session.getRoundStartTime();
        int timeLimit = session.getCurrentChallenge().getTimeLimit();

        long elapsedSeconds = (currentTime - startTime) / 1000;
        return Math.max(0, timeLimit - (int)elapsedSeconds);
    }

    public boolean isGameFinished() {
        GameSession session = gameSessionRepository.getCurrentSession();
        return session != null && session.getStatus() == GameStatus.FINISHED;
    }

    public GameSessionDTO getFinalResults() {
        if (!isGameFinished()) {
            return null;
        }
        return getCurrentGameState();
    }
    public void setPlayerReady(Long playerId) {
        Player player = playerRepository.getPlayer(playerId);
        if (player != null) {
            player.setReady(true);
            playerRepository.updatePlayer(player);
        }
    }

    public boolean areAllPlayersReady() {
        GameSession session = gameSessionRepository.getCurrentSession();
        return session != null &&
                session.getPlayers() != null &&
                !session.getPlayers().isEmpty() &&
                session.getPlayers().stream().allMatch(Player::isReady);
    }

    public Challenge getNextChallenge() {
        return challengeService.getNextChallenge();
    }
}