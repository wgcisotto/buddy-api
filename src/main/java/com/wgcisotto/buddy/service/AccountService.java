package com.wgcisotto.buddy.service;

import com.wgcisotto.buddy.model.Account;

import java.util.stream.Stream;

public interface AccountService {

    Stream<Account> findAll();

    Account create(Account account);
}