package com.wgcisotto.buddy.repository;

import com.wgcisotto.buddy.entity.Account;
import com.wgcisotto.buddy.entity.Movement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MovementRepository extends ReactiveMongoRepository<Movement, String> {

    Flux<Account> findByDeleteIsFalse(String id);

    Flux<Account> findAllByDeleteIsFalse();
}
