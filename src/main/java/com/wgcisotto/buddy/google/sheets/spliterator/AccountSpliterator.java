package com.wgcisotto.buddy.google.sheets.spliterator;

import com.wgcisotto.buddy.google.sheets.factory.AccountFactory;
import com.wgcisotto.buddy.google.sheets.model.AccountSheets;
import lombok.SneakyThrows;
import org.apache.http.MethodNotSupportedException;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class AccountSpliterator implements Spliterator<AccountSheets> {

    private Spliterator<List<String>> linesSpliterator;
    private List<String> line;

    public AccountSpliterator(Spliterator<List<String>> linesSpliterator) {
        this.linesSpliterator = linesSpliterator;
    }

    @Override
    public boolean tryAdvance(Consumer<? super AccountSheets> action) {
        if(this.linesSpliterator.tryAdvance(line -> this.line = line)){
            action.accept(AccountFactory
                    .createAccount(line));
            return true;
        }
        return false;
    }

    @SneakyThrows
    @Override
    public Spliterator<AccountSheets> trySplit() {
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