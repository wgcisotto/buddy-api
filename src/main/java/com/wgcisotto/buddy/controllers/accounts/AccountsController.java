package com.wgcisotto.buddy.controllers.accounts;

import com.wgcisotto.buddy.controllers.AccountsAPI;
import com.wgcisotto.buddy.model.Account;
import com.wgcisotto.buddy.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class AccountsController implements AccountsAPI {

    private AccountService accountService;

    private AccountsController(AccountService accountService){
        this.accountService = accountService;
    }

    @Override
    @GetMapping(value = "/accounts")
    public ResponseEntity<Flux<Account>> getAllAccounts() {
        return new ResponseEntity<>(Flux.fromStream(accountService.findAll()), HttpStatus.OK);
    }

    @Override
    @PostMapping(value = "/accounts")
    public ResponseEntity<Mono<Account>> addAccount(@Valid @RequestBody Account account) {
        return new ResponseEntity<>(Mono.just(accountService.create(account)), HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    @PutMapping(value = "/accounts")
    public ResponseEntity<Mono<Account>> updateAccount() {
        return null;
    }

}