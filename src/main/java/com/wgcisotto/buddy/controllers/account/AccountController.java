package com.wgcisotto.buddy.controllers.account;

import com.wgcisotto.buddy.entity.Account;
import com.wgcisotto.buddy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping("/account")
    public ResponseEntity<Mono<Account>> save(@RequestBody Account account){
        return ResponseEntity.ok(service.save(account));
    }

    @GetMapping("/account")
    public ResponseEntity<Flux<Account>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

}