package com.wgcisotto.buddy.controllers;

import com.wgcisotto.buddy.entity.Movement;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Api(value = "movements")
public interface MovementsApi {

    @ApiOperation(value = "Add movements to your account (not implemented)", nickname = "addMovement", tags = {"movements",})
    @ApiResponses(value = {
            @ApiResponse(code = 405, message = "Invalid input")})
    ResponseEntity<Mono<Movement>> save(@ApiParam(value = "Pet object that needs to be added to your account", required = true) @Valid @RequestBody Movement body);

}