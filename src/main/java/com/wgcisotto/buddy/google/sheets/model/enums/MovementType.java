package com.wgcisotto.buddy.google.sheets.model.enums;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum MovementType {

    EXPENSE("EXPENSE"),
    INCOME("INCOME");

    private String typeName;

    public static MovementType fromName(String typeName){
        return Stream.of(MovementType.values())
                .filter(movementType -> movementType.typeName.equals(typeName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
