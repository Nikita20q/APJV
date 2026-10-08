package com.tictactoe.web.mapper;

import com.tictactoe.domain.model.Field;
import com.tictactoe.domain.model.Player;
import com.tictactoe.web.model.FieldRequest;
import com.tictactoe.web.model.FieldResponse;

import java.util.Arrays;

public class FieldWebMapper {
    public static FieldResponse toFieldResponse(Field field) {
        int[][] intField = Arrays.stream(field.getField())
                .map(row -> Arrays.stream(row)
                        .mapToInt(Player::getCode)
                        .toArray())
                .toArray(int[][]::new);
        return new FieldResponse(intField);
    }

    public static Field requestToField(FieldRequest fieldRequest) {
        Player[][] playerField = Arrays.stream(fieldRequest.getField())
                .map(row -> Arrays.stream(row)
                        .mapToObj(Player::fromCode)
                        .toArray(Player[]::new))
                .toArray(Player[][]::new);
        Field field = new Field(playerField.length);
        field.setField(playerField);

        return field;
    }
}
