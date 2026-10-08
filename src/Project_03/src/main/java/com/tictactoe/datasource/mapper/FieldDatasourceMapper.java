package com.tictactoe.datasource.mapper;


import com.tictactoe.datasource.model.FieldEntity;
import com.tictactoe.domain.model.Field;
import com.tictactoe.domain.model.Player;

import java.util.Arrays;

public class FieldDatasourceMapper {
    public static FieldEntity toFieldEntity(Field field) {
        int[][] intField = Arrays.stream(field.getField())
                .map(row -> Arrays.stream(row)
                        .mapToInt(Player::getCode)
                        .toArray())
                .toArray(int[][]::new);
        return new FieldEntity(field.getSize(), intField);
    }

    public static Field toField(FieldEntity fieldEntity) {
        Player[][] playerField = Arrays.stream(fieldEntity.getField())
                .map(row -> Arrays.stream(row)
                .mapToObj(Player::fromCode)
                .toArray(Player[]::new))
                .toArray(Player[][]::new);
        Field field = new Field(fieldEntity.getSize());
        field.setField(playerField);

        return field;
    }
}
