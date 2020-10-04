package com.wgcisotto.buddy.service.impl;

import com.wgcisotto.buddy.entity.Account;
import com.wgcisotto.buddy.repository.AccountRepository;
import com.wgcisotto.buddy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    public Mono<Account> save(Account account){
        return repository.save(account);
    }

    @Override
    public Mono<Account> update(Account account) {
        return null;
    }

    @Override
    public void delete(Account account) {

    }

    @Override
    public Flux<Account> findById(String id) {
        return null;
    }

    public Flux<Account> findAll(){
        return repository.findAllByDeleteIsFalse();
    }

}
