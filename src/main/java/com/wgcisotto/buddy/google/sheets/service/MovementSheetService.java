package com.wgcisotto.buddy.google.sheets.service;

import com.wgcisotto.buddy.google.sheets.model.Movement;

import java.util.stream.Stream;

public interface MovementSheetService {
    Stream<Movement> findAll();
}
