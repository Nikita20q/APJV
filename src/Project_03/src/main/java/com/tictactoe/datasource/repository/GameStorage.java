package com.tictactoe.datasource.repository;

import com.tictactoe.datasource.model.GameEntity;

import java.util.UUID;

public interface GameStorage {
    void saveGame(GameEntity game);
    GameEntity getGame(UUID gameId);
}
