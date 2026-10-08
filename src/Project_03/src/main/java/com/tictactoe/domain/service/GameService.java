package com.tictactoe.domain.service;

import com.tictactoe.domain.model.Game;
import com.tictactoe.domain.model.Player;

public interface GameService {
    Game createGame();
    Game makeNextMove(Game game);
    boolean validateField(Game game);
    boolean hasEmpty(Game game);
}
