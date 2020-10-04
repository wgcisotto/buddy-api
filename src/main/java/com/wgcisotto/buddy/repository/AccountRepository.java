package com.wgcisotto.buddy.repository;

import com.wgcisotto.buddy.entity.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {

    Flux<Account> findByDeleteIsFalse(String id);

    Flux<Account> findAllByDeleteIsFalse();

    Flux<Account> findByName(String name);

}