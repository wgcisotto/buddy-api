package com.wgcisotto.buddy.repository;

import com.wgcisotto.buddy.entity.Account;
import com.wgcisotto.buddy.entity.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {

    Flux<Category> findByDeleteIsFalse(String id);

    Flux<Category> findAllByDeleteIsFalse();

}
