package com.tictactoe.datasource.repository;

import com.tictactoe.datasource.mapper.GameDatasourceMapper;
import com.tictactoe.datasource.model.GameEntity;
import com.tictactoe.domain.model.Game;
import lombok.AllArgsConstructor;
import java.util.UUID;

@AllArgsConstructor
public class GameRepository {
    private final GameStorageImpl gameStorageImpl;
    public void saveGame(Game game) {
        GameEntity gameEntity = GameDatasourceMapper.toGameEntity(game);
        gameStorageImpl.saveGame(gameEntity);
    }

    public Game getGame(UUID gameId) {
        return GameDatasourceMapper.toGame(gameStorageImpl.getGame(gameId));
    }
}
