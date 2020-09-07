package com.wgcisotto.buddy.clients;

import com.wgcisotto.buddy.model.Movement;

import java.util.stream.Stream;

public interface GoogleAPIClient {

    Stream<Movement> getAllMovements();

    Stream<Movement> getAllFixedMovements();

    Stream<Movement> getAllVariableMovements();

}