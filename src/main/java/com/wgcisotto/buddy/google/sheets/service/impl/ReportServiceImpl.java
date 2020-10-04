package com.wgcisotto.buddy.google.sheets.service.impl;

import com.wgcisotto.buddy.google.sheets.client.GoogleAPIClient;
import com.wgcisotto.buddy.google.sheets.model.Movement;
import com.wgcisotto.buddy.google.sheets.model.enums.MovementType;
import com.wgcisotto.buddy.google.sheets.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    public static final String TOTAL_INCOME = "TOTAL_INCOME";
    public static final String TOTAL_EXPENSE = "TOTAL_EXPENSE";
    public static final String BALANCE = "BALANCE";
    public static final String SALARY = "SALARY";

    private static final DecimalFormat decimal = new DecimalFormat( "0.00" );

    private GoogleAPIClient googleAPIClient;

    private ReportServiceImpl(GoogleAPIClient googleAPIClient){
        this.googleAPIClient = googleAPIClient;
    }

    @Override
    public Map<Month, Map<String, Double>> movementsReport() {
        return movementsGroupedByMonthAndCategorySummarized(Optional.empty());
    }

    @Override
    public Map<Month, Map<String, String>> movementsReportInPercentage() {
        Map<Month, Map<String, Double>> movementsGrouped = movementsGroupedByMonthAndCategorySummarized(Optional.of(
                movement -> !MovementType.INCOME.equals(movement.getType())));

        Map<Month, Double> mapTotalByMonth = new HashMap<>();
        movementsGrouped.entrySet().stream().forEach(v->mapTotalByMonth.put(v.getKey(), v.getValue().values().stream().mapToDouble(d->d.doubleValue()).sum()));
        Map<Month, Map<String, String>> collectedPercentage = new HashMap<>();
        movementsGrouped.entrySet().stream().forEach(v->{
            Map<String, String> categoryDoubleMap = new HashMap<>();
            v.getValue().keySet().stream().forEach(categ -> {
                double totalByCateg = v.getValue().get(categ).doubleValue();
                double totalByMonth = mapTotalByMonth.get(v.getKey());
                categoryDoubleMap.put(categ, decimal.format(totalByCateg * 100 / totalByMonth));
            });
            collectedPercentage.put(v.getKey(), categoryDoubleMap);
        });

        return collectedPercentage;
    }

    @Override
    public Map<Month, Map<String, Double>> movementsReportCompact() {
        Map<Month, Map<String, Double>> movementsGrouped = movementsGroupedByMonthAndCategorySummarized(Optional.empty());

        Map<Month, Map<String, Double>> result = new HashMap<>();
        movementsGrouped.entrySet().stream().forEach(movement->{
            Map<String, Double> mapSummary = new HashMap<>();

            movement.getValue().entrySet().stream()
                    .filter(m-> m.getKey().contains(SALARY))
                    .forEach( inc -> sumInMap(mapSummary, TOTAL_INCOME, inc.getValue()));

            movement.getValue().entrySet().stream()
                    .filter(m-> !m.getKey().contains(SALARY))
                    .forEach( exp -> sumInMap(mapSummary, TOTAL_EXPENSE, exp.getValue()));

            double balance = mapSummary.get(TOTAL_INCOME) - mapSummary.get(TOTAL_EXPENSE);

            sumInMap(mapSummary, BALANCE, balance);

            result.put(movement.getKey(), mapSummary);
        });

        return result;
    }

    private Map<Month, Map<String, Double>> movementsGroupedByMonthAndCategorySummarized(Optional<Predicate<? super Movement>> filter) {
        return googleAPIClient.getAllMovements()
                .filter(filter.isPresent() ? filter.get() : x -> true)
                .collect(Collectors.groupingBy(d -> d.getDate().getMonth(),
                        Collectors.groupingBy(Movement::getCategory,
                                Collectors.summingDouble(Movement::getAmount)))
                );
    }

    private void sumInMap(Map<String, Double> map, String key, double amountToSum) {
        double totalAmount = 0;
        if(map.containsKey(key)){
            totalAmount = map.get(key);
        }
        map.put(key, totalAmount + amountToSum);
    }

}
