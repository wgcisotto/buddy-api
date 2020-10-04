package com.wgcisotto.buddy.controllers.movements;

import com.wgcisotto.buddy.controllers.MovementsApi;
import com.wgcisotto.buddy.entity.Movement;
import com.wgcisotto.buddy.enums.AverageBy;
import com.wgcisotto.buddy.google.sheets.model.enums.MovementType;
import com.wgcisotto.buddy.service.MovementService;
import com.wgcisotto.buddy.vo.AverageFilterVO;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/v1")
@Slf4j
public class MovementsApiController implements MovementsApi {

    public static final String CATEGORY = "category";
    private MovementService movementService;

    public MovementsApiController(MovementService movementService) {
        this.movementService = movementService;
    }

    @PostMapping(value = "/movement", produces = {"application/json"}, consumes = { "application/json"})
    public ResponseEntity<Mono<Movement>> save(@ApiParam(value = "Pet object that needs to be added to your account" ,required=true )  @Valid @RequestBody Movement movement) {
        return new ResponseEntity<>(movementService.save(movement), HttpStatus.OK);
    }

//    TODO: review this headers. it's confusing.
    @GetMapping(value = "/movement", produces = {"application/json"})
    public ResponseEntity<?> findMovements(@Valid @RequestParam(value = "month",  required = false) Integer month,
                                                        @Valid @RequestParam(value = "year",  required = false) Integer year,
                                                        @Valid @RequestParam(value = "groupby",  required = false) String groupBy,
                                                        @Valid @RequestParam(value = "type", required = false) MovementType type,
                                                        @Valid @RequestParam(value = "average",  required = false) boolean average,
                                                        @Valid @RequestParam(value = "averageby",  required = false) AverageBy averageBy,
                                                        @Valid @RequestParam(value = "limit",  required = false) Optional<Integer> limit) {

        if(average){
            AverageFilterVO averageFilter = AverageFilterVO.builder()
                    .averageBy(averageBy)
                    .limit(limit.orElse(0))
                    .type(type)
                    .build();
            return new ResponseEntity<>(movementService.averageGroupedByCategory(averageFilter), HttpStatus.OK);
        }

        if(Objects.nonNull(month) && Objects.nonNull(year)){
            if(Objects.nonNull(groupBy) && CATEGORY.equals(groupBy)){
                return ResponseEntity.ok(movementService.findByMonthAndYearGroupByCategory(month, year, type));
            }else{
                return ResponseEntity.ok(movementService.findByMonthAndYear(month, year));
            }
        }
        return new ResponseEntity<>(movementService.findAll(), HttpStatus.OK);
    }
}