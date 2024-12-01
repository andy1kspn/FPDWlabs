package com.example.fdwproject2.controller;

import com.example.fdwproject2.model.GameSession;
import com.example.fdwproject2.model.Player;
import com.example.fdwproject2.model.Submission;
import com.example.fdwproject2.service.GameService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;


@Controller
public class WebSocketController {

    @Autowired
    private GameService gameService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/join")
    @SendTo("/topic/player-status")
    public String handleJoin(Principal principal) {
        Player player = gameService.addNewPlayer();
        if (player != null) {
            return "Player joined successfully";
        }
        return "Game is full";
    }

    @MessageMapping("/player-ready")
    @SendTo("/topic/player-status")
    public void handlePlayerReady(Long playerId) {
        if (gameService.shouldStartGame()) {
            messagingTemplate.convertAndSend("/topic/challenge",
                    gameService.getCurrentSession().getCurrentChallenge());
        }
    }
}