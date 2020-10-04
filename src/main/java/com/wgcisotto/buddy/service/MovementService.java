package com.wgcisotto.buddy.service;

import com.wgcisotto.buddy.entity.Movement;
import com.wgcisotto.buddy.google.sheets.model.enums.MovementType;
import com.wgcisotto.buddy.vo.AverageFilterVO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;


public interface MovementService extends BaseService<Movement>{
    Flux<Movement> findByMonthAndYear(int month, int year);
    Mono<Map<String, Double>> findByMonthAndYearGroupByCategory(int month, int year, MovementType type);
    Mono<Map<String, Double>> averageGroupedByCategory(AverageFilterVO averageFilter);
}
