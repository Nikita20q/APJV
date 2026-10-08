package com.tictactoe.domain.service;

import com.tictactoe.datasource.repository.GameRepository;
import com.tictactoe.domain.model.Game;
import com.tictactoe.domain.model.Player;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameServiceImpl implements GameService {
    private GameRepository gameRepository;
    private boolean checkWin(Game game, Player player) {
        int counterX = 0, counterY= 0;
        int counterXY = 0, counterYX = 0;
        int fieldSize = game.getField().getSize();
        Player[][] fieldGame = game.getField().getField();

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (fieldGame[i][j] == player) {
                    counterX++;
                }
                if (fieldGame[j][i] == player) {
                    counterY++;
                }
            }
            if (fieldGame[i][i] == player) {
                counterXY++;
            }
            if (fieldGame[i][fieldSize - 1 - i] == player) {
                counterYX++;
            }
            if (counterX == fieldSize || counterY == fieldSize) {
                return true;
            }
            counterX = 0;
            counterY = 0;
        }
        if (counterXY == fieldSize ||  counterYX == fieldSize) {
            return true;
        }
        return false;
    }

    private int checkScore(Game game, Player player, int depth) {
        int size = game.getField().getSize();
        Player[][] fieldGame = game.getField().getField();
        if (checkWin(game, Player.CROSS)) {
            return depth - 10;
        }
        if (checkWin(game, Player.ZERO)) {
            return 10 - depth;
        }
        if (!hasEmpty(game)) {
            return 0;
        }

        int bestScore;

        if (player == Player.CROSS) {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < size * size; i++) {
                if (fieldGame[i / size][i % size] != Player.NONE) {
                    continue;
                }
                fieldGame[i / size][i % size] = player;
                int score = checkScore(game, Player.ZERO, depth + 1);
                fieldGame[i / size][i % size] = Player.NONE;
                bestScore = Math.min(bestScore, score);
            }
        } else {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < size * size; i++) {
                if (fieldGame[i / size][i % size] != Player.NONE) {
                    continue;
                }
                fieldGame[i / size][i % size] = player;
                int score = checkScore(game, Player.CROSS, depth + 1);
                fieldGame[i / size][i % size] = Player.NONE;
                bestScore = Math.max(bestScore, score);
            }
        }

        return bestScore;
    }

    @Override
    public boolean hasEmpty(Game game) {
        int size = game.getField().getSize();
        Player[][] fieldGame = game.getField().getField();
        for (int i = 0; i < size * size; i++) {
            if (fieldGame[i / size][i % size] == Player.NONE) {
                return true;
            }
        }
        return false;
    }

    private int miniMax(Game game, Player player) {
        int bestScore = Integer.MIN_VALUE;
        int bestCell = -1;
        Player[][] fieldGame = game.getField().getField();
        int size = game.getField().getSize();
        for (int i = 0; i < size * size; i++) {
            if (fieldGame[i/size][i%size] == Player.NONE) {
                fieldGame[i/size][i%size] = player;
                Player opponent = player == Player.CROSS ? Player.ZERO : Player.CROSS;
                int score = checkScore(game, opponent, 0);
                fieldGame[i/size][i%size] = Player.NONE;
                if (score > bestScore) {
                    bestScore = score;
                    bestCell = i;
                }
            }
        }
        return bestCell;
    }

    @Override
    public boolean validateField(Game game) {
        Player expectedPlayer = Player.CROSS;

        Player[][] fieldGame = game.getField().getField();
        Player[][] lastField = gameRepository.getGame(game.getId()).getField().getField();

        if (fieldGame.length != lastField.length) {
            return false;
        }

        int size = game.getField().getSize();
        int counter = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (fieldGame[i][j] == lastField[i][j]) {
                    continue;
                }
                if (lastField[i][j] != Player.NONE) {
                    return false;
                }
                if (fieldGame[i][j] != expectedPlayer) {
                    return false;
                }
                counter++;
            }
        }

        return counter == 1;
    }

    public Game makeNextMove(Game game) {
        if (!validateField(game)) {
            throw new IllegalArgumentException("Некорректное игровое поле");
        }
        if (checkWin(game, Player.CROSS) || checkWin(game, Player.ZERO)) {
            throw new IllegalArgumentException("Игрок уже победил");
        }
        Game result = new Game(game.getId(), game.getField().copy());
        int cell = miniMax(result, Player.ZERO);

        if (cell == -1) {
            throw new IllegalArgumentException("Игра закончилась ничьёй");
        }

        int size = result.getField().getSize();
        result.getField().getField()[cell / size][cell % size] = Player.ZERO;
        gameRepository.saveGame(result);

        return result;
    }

    @Override
    public Game createGame() {
        Game game = Game.create(3);
        gameRepository.saveGame(game);
        return game;
    }
}
