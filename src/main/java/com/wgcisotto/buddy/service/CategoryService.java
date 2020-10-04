package com.wgcisotto.buddy.service;

import com.wgcisotto.buddy.entity.Category;
import com.wgcisotto.buddy.google.sheets.model.enums.MovementType;
import reactor.core.publisher.Flux;

public interface CategoryService extends BaseService<Category> {

    Flux<Category> findAllByType(MovementType type);

}
