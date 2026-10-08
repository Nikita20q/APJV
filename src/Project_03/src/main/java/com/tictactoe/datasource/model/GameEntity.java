package com.tictactoe.datasource.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class GameEntity {
    private UUID id;
    private FieldEntity currentField;
}
