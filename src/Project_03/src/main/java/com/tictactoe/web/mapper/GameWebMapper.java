package com.tictactoe.web.mapper;

import com.tictactoe.domain.model.Field;
import com.tictactoe.domain.model.Game;
import com.tictactoe.web.model.GameRequest;
import com.tictactoe.web.model.GameResponse;

import java.util.UUID;

public class GameWebMapper {
    public static GameResponse toGameResponse(Game game) {
        return new GameResponse(game.getId(), FieldWebMapper.toFieldResponse(game.getField()));
    }

    public static Game requestToGame(UUID gameId, GameRequest gameRequest) {
        Field field =  FieldWebMapper.requestToField(gameRequest.getField());
        return new Game(gameId, field);
    }
}
