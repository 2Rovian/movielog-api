package com.movielog.movielog_api.infra;

import com.movielog.movielog_api.dtos.errors.RestApiErrorMessage;
import com.movielog.movielog_api.exceptions.DuplicateMovieException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateMovieException.class)
    public ResponseEntity<RestApiErrorMessage> duplicateMovie(
            DuplicateMovieException exception, HttpServletRequest request
    ) {
        RestApiErrorMessage dto = new RestApiErrorMessage(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.name(),
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(dto);
    }
}
