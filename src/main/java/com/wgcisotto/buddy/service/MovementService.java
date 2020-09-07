package com.wgcisotto.buddy.service;

import com.wgcisotto.buddy.model.Movement;

import java.util.stream.Stream;

public interface MovementService {
    Stream<Movement> findAll();
}
