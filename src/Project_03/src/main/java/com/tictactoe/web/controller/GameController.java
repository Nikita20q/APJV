package com.tictactoe.web.controller;

import com.tictactoe.domain.model.Game;
import com.tictactoe.domain.service.GameService;
import com.tictactoe.web.mapper.GameWebMapper;
import com.tictactoe.web.model.GameRequest;
import com.tictactoe.web.model.GameResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/game")
@Tag(name = "Tic-Tac-Toe")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    @PostMapping("/{uuid}")
    public GameResponse makeMove(@PathVariable UUID uuid, @RequestBody GameRequest request) {
        Game game = gameService.makeNextMove(GameWebMapper.requestToGame(uuid, request));
        return GameWebMapper.toGameResponse(game);
    }
    @PostMapping
    public GameResponse createGame() {
        Game game = gameService.createGame();
        return GameWebMapper.toGameResponse(game);
    }
}
