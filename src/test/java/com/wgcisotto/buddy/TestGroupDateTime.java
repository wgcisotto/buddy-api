package com.wgcisotto.buddy;

import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class TestGroupDateTime {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    public void testGroupDate(){

        TestClass testClass1 = TestClass.builder()
                .date(LocalDate.parse("01/04/2020", formatter))
                .category("CategoryA")
                .value(10D)
                .build();

        TestClass testClass2 = TestClass.builder()
                .date(LocalDate.parse("01/04/2020", formatter))
                .category("CategoryA")
                .value(30D)
                .build();

        TestClass testClass3 = TestClass.builder()
                .date(LocalDate.parse("01/04/2020", formatter))
                .category("CategoryB")
                .value(10D)
                .build();

        TestClass testClass4 = TestClass.builder()
                .date(LocalDate.parse("01/05/2020", formatter))
                .category("CategoryA")
                .value(10D)
                .build();

        TestClass testClass5 = TestClass.builder()
                .date(LocalDate.parse("01/06/2020", formatter))
                .category("CategoryB")
                .value(10D)
                .build();

        TestClass testClass6 = TestClass.builder()
                .date(LocalDate.parse("01/07/2020", formatter))
                .category("CategoryC")
                .value(40D)
                .build();

        TestClass testClass7 = TestClass.builder()
                .date(LocalDate.parse("01/07/2020", formatter))
                .category("CategoryA")
                .value(34.8D)
                .build();


        List<TestClass> testClasses = Arrays.asList(testClass1, testClass2, testClass3, testClass4, testClass5, testClass6, testClass7);

//
//        Map<Month, List<TestClass>> collect = testClasses.stream()
//                .collect(Collectors.groupingBy(d -> d.getDate().getMonth()));
//
//        collect.forEach((m,list) -> {
//            System.out.println(m  + " " +  list);
//        });


        Set<DoubleStream> collect = testClasses.stream()
                .collect(Collectors.groupingBy(d -> d.getDate().getMonth()))
                .entrySet().stream()
                            .map(i -> i.getValue().stream().mapToDouble(v -> v.getValue()))
                .collect(Collectors.toSet());

        collect.forEach((m) -> {
            System.out.println(m);
        });


        Map<Month, Double> collect1 = testClasses.stream()
                .collect(Collectors.groupingBy(d -> d.getDate().getMonth()))
                .entrySet().stream().collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().stream().mapToDouble(TestClass::getValue).sum()
                ));


        collect1.forEach((m,list) -> {
            System.out.println(m  + " " +  list);
        });


        Map<Month, Map<String, Double>> collect2 = testClasses.stream().sorted(Comparator.comparing(TestClass::getDate))
                .collect(Collectors.groupingBy(d -> d.getDate().getMonth(),
                        Collectors.groupingBy(v -> v.getCategory(),
                                Collectors.averagingDouble(d -> d.getValue())))
                );


        System.out.println(collect2);


    }


}

    @Data
    @Builder
    class TestClass {
        private LocalDate date;
        private String category;
        private Double value;

    }