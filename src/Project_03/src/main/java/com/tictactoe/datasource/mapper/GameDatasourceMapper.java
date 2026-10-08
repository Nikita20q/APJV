package com.tictactoe.datasource.mapper;

import com.tictactoe.datasource.model.GameEntity;
import com.tictactoe.domain.model.Game;


public class GameDatasourceMapper {
    public static GameEntity toGameEntity(Game game) {
        FieldDatasourceMapper mapper = new FieldDatasourceMapper();
        return new GameEntity(game.getId(), mapper.toFieldEntity(game.getField()));
    }

    public static Game toGame(GameEntity gameEntity) {
        FieldDatasourceMapper mapper = new FieldDatasourceMapper();
        return new Game(gameEntity.getId(), mapper.toField(gameEntity.getCurrentField()));
    }
}
