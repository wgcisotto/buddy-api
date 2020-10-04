package com.wgcisotto.buddy.google.sheets.model.enums;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum Frequency {

    DAILY("DAILY"),
    WEEKLY("WEEKLY"),
    MONTHLY("MONTHLY"),
    YEARLY("YEARLY");

    private String frequency;

    public static Frequency fromName(String frequency) {
        return Stream.of(Frequency.values())
                .filter(frq -> frq.frequency.equals(frequency))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}