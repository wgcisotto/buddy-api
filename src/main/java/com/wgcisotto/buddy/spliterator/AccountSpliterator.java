package com.wgcisotto.buddy.spliterator;

import com.wgcisotto.buddy.factory.AccountFactory;
import com.wgcisotto.buddy.model.Account;
import lombok.SneakyThrows;
import org.apache.http.MethodNotSupportedException;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class AccountSpliterator implements Spliterator<Account> {

    private Spliterator<List<String>> linesSpliterator;
    private List<String> line;

    public AccountSpliterator(Spliterator<List<String>> linesSpliterator) {
        this.linesSpliterator = linesSpliterator;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Account> action) {
        if(this.linesSpliterator.tryAdvance(line -> this.line = line)){
            action.accept(AccountFactory
                    .createAccount(line));
            return true;
        }
        return false;
    }

    @SneakyThrows
    @Override
    public Spliterator<Account> trySplit() {
        //TODO: test this
        throw new MethodNotSupportedException("Parallels not supported");
    }

    @Override
    public long estimateSize() {
        return linesSpliterator.estimateSize();
    }

    @Override
    public int characteristics() {
        return linesSpliterator.characteristics();
    }
}