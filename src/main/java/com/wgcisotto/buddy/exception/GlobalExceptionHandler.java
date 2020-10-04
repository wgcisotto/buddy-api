package com.wgcisotto.buddy.exception;

import com.wgcisotto.buddy.exception.model.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> validationExceptionHandler(Exception exception){
        log.error("Exception: {}", exception.getMessage() , exception);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ResponseError.builder()
                .code("000")
                .message("Internal Server Error, please find for William G. Cisotto :" + exception.getMessage())
                .build());
    }

}
