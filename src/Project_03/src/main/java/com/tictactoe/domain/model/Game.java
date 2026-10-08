package com.tictactoe.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Data
public class Game {
    private UUID id;
    private Field field;

    public static Game create(int size) {
        Field field = new Field(size);
        Field lastField = field.copy();

        return new Game(UUID.randomUUID(), field);
    }

    public static Game restore(UUID id, Field field) {
        return new Game(id, field);
    }
}
