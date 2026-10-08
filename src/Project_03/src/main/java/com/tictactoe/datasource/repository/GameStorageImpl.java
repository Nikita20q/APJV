package com.tictactoe.datasource.repository;

import com.tictactoe.datasource.model.GameEntity;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


public class GameStorageImpl implements GameStorage {
    private final Map<UUID, GameEntity> games;
    public GameStorageImpl() {
        games = new ConcurrentHashMap<>();
    }

    @Override
    public void saveGame(GameEntity game) {
        games.put(game.getId(), game);
    }

    @Override
    public GameEntity getGame(UUID gameId) {
        GameEntity game = games.get(gameId);
        if (game == null) {
            throw new IllegalArgumentException("Игра: " + gameId + " не найдена");
        }
        return game;
    }

}
