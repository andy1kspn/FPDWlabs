package com.example.fdwproject2.service;

import com.example.fdwproject2.DTO.*;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class WebSocketService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void notifyGameStateChange(GameStateDTO gameState) {
        messagingTemplate.convertAndSend("/topic/game-state", gameState);
    }

    public void notifyNewChallenge(ChallengeDTO challenge) {
        messagingTemplate.convertAndSend("/topic/challenge", challenge);
    }

    public void notifyTimeRemaining(int seconds) {
        messagingTemplate.convertAndSend("/topic/timer", seconds);
    }

    public void notifyGameOver(GameSessionDTO session) {
        messagingTemplate.convertAndSend("/topic/game-over", session);
    }
}