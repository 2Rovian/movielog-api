package com.movielog.movielog_api.infra;

import com.movielog.movielog_api.dtos.errors.RestApiErrorDTO;
import com.movielog.movielog_api.exceptions.DuplicateMovieException;
import com.movielog.movielog_api.exceptions.NotFoundMovieException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateMovieException.class)
    public ResponseEntity<RestApiErrorDTO> duplicateMovieController(
            DuplicateMovieException exception, HttpServletRequest request
    ) {
        RestApiErrorDTO dto = new RestApiErrorDTO(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.name(),
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(dto);
    }

    @ExceptionHandler(NotFoundMovieException.class)
    public ResponseEntity<RestApiErrorDTO> notFoundMovieController(
            NotFoundMovieException exception, HttpServletRequest request
    ) {
        RestApiErrorDTO dto = new RestApiErrorDTO(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_MODIFIED.name(),
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
    }
}
