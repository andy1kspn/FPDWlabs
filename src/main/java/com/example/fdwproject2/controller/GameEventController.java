package com.example.fdwproject2.controller;

import com.example.fdwproject2.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class GameEventController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private GameService gameService;

    @Scheduled(fixedRate = 1000)
    public void sendTimeUpdate() {
        if (gameService.isGameInProgress()) {
            messagingTemplate.convertAndSend("/topic/timer",
                    gameService.getRemainingTime());
        }
    }

    @Scheduled(fixedRate = 500)
    public void checkGameStatus() {
        if (gameService.shouldStartGame()) {
            messagingTemplate.convertAndSend("/topic/game-status", "STARTING");
            gameService.startGame();
        }

        if (gameService.isGameFinished()) {
            messagingTemplate.convertAndSend("/topic/game-status", "FINISHED");
            messagingTemplate.convertAndSend("/topic/results",
                    gameService.getFinalResults());
        }
    }
}