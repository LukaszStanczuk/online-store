package com.onlinestore.exception;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage badRequestHandler(BadRequestException exception) {
        log.error(exception.getMessage());
        return new ErrorMessage(List.of(exception.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage notFoundHandler(NotFoundException exception) {
        log.error(exception.getMessage());
        return new ErrorMessage(List.of(exception.getMessage()));
    }

    //    @ExceptionHandler(NotFoundComponentException.class)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage notFoundComponentException(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage());
        List<String> errors = exception.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return new ErrorMessage(errors);
    }

    //Exception.class

    @AllArgsConstructor
    static class ErrorMessage {
        private List<String> errors;
    }

}

