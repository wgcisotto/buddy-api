package com.wgcisotto.buddy.controllers;

import com.wgcisotto.buddy.model.Movement;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

@Api(value = "movements")
public interface MovementsApi {

    @ApiOperation(value = "Add movements to your account (not implemented)", nickname = "addMovement", tags={ "movements", })
    @ApiResponses(value = { 
        @ApiResponse(code = 405, message = "Invalid input") })
    ResponseEntity<Void> addMovement(@ApiParam(value = "Pet object that needs to be added to your account" ,required=true )  @Valid @RequestBody Movement body);


    @ApiOperation(value = "Finds all Movement", nickname = "findMovements", notes = "", response = Movement.class, responseContainer = "List", tags={ "movements", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Movement.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid status value") })
    ResponseEntity<Flux<Movement>> findMovements(@ApiParam("Identification of the account") String accountId);

}