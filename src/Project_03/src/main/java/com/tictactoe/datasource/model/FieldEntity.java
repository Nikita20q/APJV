package com.tictactoe.datasource.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldEntity {
    private final int size;
    private int[][] field;
}
