package com.tictactoe.domain.model;

public enum Player {
    NONE(0),
    CROSS(1),
    ZERO(2);

    private final int code;

    Player(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Player fromCode(int code) {
        if (code == 0) {
            return NONE;
        }

        if (code == 1) {
            return CROSS;
        }

        if (code == 2) {
            return ZERO;
        }

        throw new IllegalArgumentException("Некорректный код игрока: " + code);
    }
}