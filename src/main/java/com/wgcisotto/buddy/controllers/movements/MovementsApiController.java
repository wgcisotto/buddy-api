package com.wgcisotto.buddy.controllers.movements;

import com.wgcisotto.buddy.controllers.MovementsApi;
import com.wgcisotto.buddy.model.Movement;
import com.wgcisotto.buddy.service.MovementService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.validation.Valid;


@RestController
@RequestMapping("/v1")
@Slf4j
public class MovementsApiController implements MovementsApi {

    private MovementService movementService;

    public MovementsApiController(MovementService movementService) {
        this.movementService = movementService;
    }

    @PostMapping(value = "accounts/{accountId}/movements", produces = { "application/xml", "application/json" }, consumes = { "application/json", "application/xml" })
    public ResponseEntity<Void> addMovement(@ApiParam(value = "Pet object that needs to be added to your account" ,required=true )  @Valid @RequestBody Movement body) {
//        String accept = request.getHeader("Accept");
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @GetMapping(value = "/accounts/{accountId}/movements")
    public ResponseEntity<Flux<Movement>> findMovements(@PathVariable("accountId") String accountId) {
        try {
            log.info(accountId);
            return new ResponseEntity<>(Flux.fromStream(movementService.findAll()), HttpStatus.OK);
        } catch (Exception e) {
            //TODO: Controllers error must be on exception handler
            log.error("Unexpected error occurs", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}