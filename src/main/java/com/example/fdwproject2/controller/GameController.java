package com.example.fdwproject2.controller;

import com.example.fdwproject2.model.GameSession;
import com.example.fdwproject2.model.Player;
import com.example.fdwproject2.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/game")
    public String startGame(Model model) {
        if (!gameService.isGameAvailable()) {
            return "waiting";
        }

        Player player = gameService.addNewPlayer();
        if (player != null) {
            model.addAttribute("playerId", player.getId());
        }

        if (gameService.shouldStartGame()) {
            gameService.startGame();
        }

        return "game";
    }

    @GetMapping("/waiting")
    public String waiting() {
        return "waiting";
    }

    @GetMapping("/game-status")
    @ResponseBody
    public GameSession getGameStatus() {
        return gameService.getCurrentSession();
    }
}