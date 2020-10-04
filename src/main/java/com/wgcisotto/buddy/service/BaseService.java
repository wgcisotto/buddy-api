package com.wgcisotto.buddy.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BaseService<T> {

    Mono<T> save(T t);

    Mono<T> update(T t);

    void delete(T t);

    Flux<T> findById(String id);

    Flux<T> findAll();

}
