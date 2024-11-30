package com.example.fdwproject2.controller;

import com.example.fdwproject2.model.GameSession;
import com.example.fdwproject2.model.Submission;
import com.example.fdwproject2.service.GameService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class WebSocketController {

    @Autowired
    private GameService gameService;

    @MessageMapping("/player-ready")
    @SendTo("/topic/player-status")
    public void handlePlayerReady(Long playerId) {
        gameService.setPlayerReady(playerId);
    }

    @MessageMapping("/submit-answer")
    @SendTo("/topic/game-updates")
    public GameSession handleSubmission(Submission submission) {
        gameService.processSubmission(submission);
        return gameService.getCurrentSession();
    }
}