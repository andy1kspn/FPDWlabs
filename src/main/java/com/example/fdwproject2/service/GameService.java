package com.example.fdwproject2.service;

import com.example.fdwproject2.DTO.*;
import com.example.fdwproject2.model.*;
import com.example.fdwproject2.model.enums.GameStatus;
import com.example.fdwproject2.repository.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

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

    private Set<Long> submittedPlayers = new HashSet<>();

    public boolean isGameAvailable() {
        return gameSessionRepository.isGameAvailable();
    }

    public Player addNewPlayer() {
        if (gameSessionRepository.getCurrentSession().getStatus() != GameStatus.WAITING) {
            return null;
        }

        Player player = playerRepository.createPlayer();
        if (player != null) {
            player.setReady(true);

            GameSession session = gameSessionRepository.getCurrentSession();
            session.addPlayer(player);
            gameSessionRepository.saveSession(session);

            // Notifică toți clienții despre noul jucător
            messagingTemplate.convertAndSend("/topic/game-updates", getCurrentGameState());

            if (shouldStartGame()) {
                startGame();
                messagingTemplate.convertAndSend("/topic/game-status", "STARTED");
            }
        }
        return player;
    }

    public synchronized void processSubmission(Submission submission) {
        if (submittedPlayers.contains(submission.getPlayerId())) {
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

        submittedPlayers.add(submission.getPlayerId());

        if (submittedPlayers.size() == session.getPlayers().size()) {
            // Trimite rezultatele
            Map<Long, Integer> scores = session.getPlayers().stream()
                    .collect(Collectors.toMap(
                            Player::getId,
                            Player::getScore));

            messagingTemplate.convertAndSend("/topic/submissions-status",
                    Map.of("allSubmitted", true, "scores", scores));

            // Reset pentru următoarea rundă
            submittedPlayers.clear();

            // Dacă mai sunt întrebări, pregătește următoarea
            if (challengeService.hasNextChallenge()) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        session.setCurrentChallenge(challengeService.getNextChallenge());
                        session.setRoundStartTime(System.currentTimeMillis());
                        gameSessionRepository.saveSession(session);
                        messagingTemplate.convertAndSend("/topic/challenge",
                                session.getCurrentChallenge());
                    }
                }, 3000); // Așteaptă 3 secunde înainte de următoarea întrebare
            } else {
                // Jocul s-a terminat
                endGame(session);
            }
        }

        // Notifică toți clienții despre actualizarea scorurilor
        messagingTemplate.convertAndSend("/topic/game-updates", getCurrentGameState());
    }
    private void endGame(GameSession session) {
        session.setStatus(GameStatus.FINISHED);
        gameSessionRepository.saveSession(session);
        messagingTemplate.convertAndSend("/topic/game-status", "FINISHED");
        messagingTemplate.convertAndSend("/topic/game-updates", getCurrentGameState());
    }

    public boolean shouldStartGame() {
        GameSession session = gameSessionRepository.getCurrentSession();
        return session.isFull();
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
            endGame(session);
        } else {
            session.getPlayers().removeIf(p -> p.getId().equals(playerId));
            playerRepository.reset();
            gameSessionRepository.saveSession(session);
            messagingTemplate.convertAndSend("/topic/game-updates", getCurrentGameState());
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
    
}