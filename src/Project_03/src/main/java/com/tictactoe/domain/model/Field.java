package com.tictactoe.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Field {
    private Player[][] field;
    private final int size;
    public Field(int size) {
        this.size = size;
        this.field = new Player[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = Player.NONE;
            }
        }
    }
    public Field copy() {
        Field copy = new Field(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copy.field[i][j] = field[i][j];
            }
        }
        return copy;
    }
}
