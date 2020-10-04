package com.wgcisotto.buddy.service.impl;

import com.wgcisotto.buddy.entity.Category;
import com.wgcisotto.buddy.google.sheets.model.enums.MovementType;
import com.wgcisotto.buddy.repository.CategoryRepository;
import com.wgcisotto.buddy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Mono<Category> save(Category category){
        return repository.save(category);
    }

    @Override
    public Mono<Category> update(Category category) {
        return null;
    }

    @Override
    public void delete(Category category) {

    }

    @Override
    public Flux<Category> findById(String id) {
        return null;
    }

    @Override
    public Flux<Category> findAll() {
        return repository.findAllByDeleteIsFalse();
    }

    @Override
    public Flux<Category> findAllByType(MovementType type){
        return repository.findAllByDeleteIsFalse()
                .filter(c -> c.getType().equals(type));
    }

}
