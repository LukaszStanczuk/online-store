package com.onlinestore.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundComponentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void notFoundComponentException(NotFoundComponentException exception) {
        log.error(exception.getMessage());
    }
}
