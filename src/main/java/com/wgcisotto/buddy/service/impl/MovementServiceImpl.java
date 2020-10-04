package com.wgcisotto.buddy.service.impl;

import com.wgcisotto.buddy.entity.Movement;
import com.wgcisotto.buddy.enums.AverageBy;
import com.wgcisotto.buddy.google.sheets.model.enums.MovementType;
import com.wgcisotto.buddy.repository.MovementRepository;
import com.wgcisotto.buddy.service.MovementService;
import com.wgcisotto.buddy.vo.AverageFilterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovementServiceImpl implements MovementService {

    public static final int DEFAULT_YEARS_FILTER = 1;
    public static final int DEFAULT_MONTHS_FILTER = 3;
    @Autowired
    private MovementRepository repository;

    @Override
    public Mono<Movement> save(Movement movement) {
        return repository.save(movement);
    }

    @Override
    public Mono<Movement> update(Movement movement) {
        return null;
    }

    @Override
    public void delete(Movement movement) {

    }

    @Override
    public Flux<Movement> findById(String id) {
        return null;
    }

    @Override
    public Flux<Movement> findAll() {
        return repository.findAll()
                .sort(Comparator.comparing(Movement::getDate).reversed());
    }

    @Override
    public Flux<Movement> findByMonthAndYear(int month, int year) {
        return repository.findAll()
                .sort(Comparator.comparing(Movement::getDate).reversed())
                .filter(m -> m.getDate().getMonth() == Month.of(month) && m.getDate().getYear() == year);
    }

    @Override
    public Mono<Map<String, Double>> findByMonthAndYearGroupByCategory(int month, int year, MovementType type) {
        return repository.findAll()
                .sort(Comparator.comparing(Movement::getDate).reversed())
                .filter(m -> m.getDate().getMonth() == Month.of(month) && m.getDate().getYear() == year)
                .filter(m -> m.getType() == type)
                .collect(Collectors.groupingBy(m -> m.getCategory().getName(),
                        Collectors.summingDouble(Movement::getAmount)));
    }

    @Override
    public Mono<Map<String, Double>> averageGroupedByCategory(AverageFilterVO averageFilter) {
        LocalDate endDate = buildEndDate(averageFilter);

        return repository.findAll()
                .sort(Comparator.comparing(Movement::getDate).reversed())
                .filter(m -> m.getDate().getYear() >= endDate.getYear())
                .filter(m -> m.getDate().getMonth().getValue() >= endDate.getMonth().getValue())
                .filter(m -> m.getType() == averageFilter.getType())
                .collect(Collectors.groupingBy(m -> m.getCategory().getName(),
                        Collectors.summingDouble(Movement::getAmount)));
    }

    private LocalDate buildEndDate(AverageFilterVO averageFilter){
        LocalDate endDate = LocalDate.now();
        if(AverageBy.MONTHS == averageFilter.getAverageBy()){
            if(averageFilter.getLimit() > 0){
                return endDate.minusMonths(averageFilter.getLimit());
            }else{
                return endDate.minusMonths(DEFAULT_MONTHS_FILTER);
            }
        }
        if(averageFilter.getLimit() > 0){
            return endDate.minusYears(averageFilter.getLimit());
        }else{
            return endDate.minusYears(DEFAULT_YEARS_FILTER);
        }
    }


}
