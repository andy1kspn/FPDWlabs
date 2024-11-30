package com.example.fdwproject2.repository;

import com.example.fdwproject2.model.*;
import com.example.fdwproject2.model.enums.*;
import org.springframework.stereotype.Repository;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;

@Repository
public class GameSessionRepository {
    private ConcurrentHashMap<String, GameSession> activeSessions;
    private GameSession currentSession;

    @PostConstruct
    public void init() {
        activeSessions = new ConcurrentHashMap<>();
        currentSession = new GameSession();
    }

    public GameSession getCurrentSession() {
        return currentSession;
    }

    public void saveSession(GameSession session) {
        if (session.getStatus() == GameStatus.FINISHED) {
            activeSessions.put(generateSessionId(), session);
            currentSession = new GameSession();
        } else {
            currentSession = session;
        }
    }

    public boolean isGameAvailable() {
        return !currentSession.isFull();
    }

    private String generateSessionId() {
        return "game_" + System.currentTimeMillis();
    }
}