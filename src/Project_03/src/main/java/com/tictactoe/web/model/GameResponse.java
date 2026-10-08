package com.tictactoe.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class GameResponse {
    private UUID gameId;
    private FieldResponse field;
}
