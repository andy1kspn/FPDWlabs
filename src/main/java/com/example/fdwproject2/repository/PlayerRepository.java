package com.example.fdwproject2.repository;

import com.example.fdwproject2.model.*;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PlayerRepository {
    private final Map<Long, Player> activePlayers;
    private final AtomicLong playerIdCounter;

    public PlayerRepository() {
        this.activePlayers = new ConcurrentHashMap<>();
        this.playerIdCounter = new AtomicLong(0);
    }

    public Player createPlayer() {
        long nextId = playerIdCounter.incrementAndGet();
        if (nextId > 3) {
            return null;
        }
        Player player = new Player(nextId);
        activePlayers.put(nextId, player);
        return player;
    }

    public Player getPlayer(Long id) {
        return activePlayers.get(id);
    }

    public void updatePlayer(Player player) {
        activePlayers.put(player.getId(), player);
    }

    public void reset() {
        activePlayers.clear();
        playerIdCounter.set(0);
    }

    public int getActivePlayersCount() {
        return activePlayers.size();
    }
}