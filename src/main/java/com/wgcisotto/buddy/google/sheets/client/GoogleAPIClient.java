package com.wgcisotto.buddy.google.sheets.client;

import com.wgcisotto.buddy.google.sheets.model.Movement;

import java.util.stream.Stream;

public interface GoogleAPIClient {

    Stream<Movement> getAllMovements();

    Stream<Movement> getAllFixedMovements();

    Stream<Movement> getAllVariableMovements();

}