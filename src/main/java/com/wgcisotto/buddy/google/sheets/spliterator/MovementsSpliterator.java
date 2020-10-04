package com.wgcisotto.buddy.google.sheets.spliterator;


import com.wgcisotto.buddy.google.sheets.factory.MovementFactory;
import com.wgcisotto.buddy.google.sheets.model.Movement;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.MethodNotSupportedException;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

@Slf4j
public class MovementsSpliterator implements Spliterator<Movement> {

    private Spliterator<List<String>> linesSpliterator;
    private List<String> line;

    public MovementsSpliterator(Spliterator<List<String>> linesSpliterator) {
        this.linesSpliterator = linesSpliterator;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Movement> action) {
        if(this.linesSpliterator.tryAdvance(line -> this.line = line)){
            log.info("Creating movement: " + String.valueOf(this.line));
            action.accept(MovementFactory
                    .createMovement(line));
            return true;
        }
        return false;
    }

    @SneakyThrows
    @Override
    public Spliterator<Movement> trySplit() {
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
