package com.movielog.movielog_api.exceptions;

public class NotFoundMovieException extends RuntimeException {
    public NotFoundMovieException(String message) {
        super(message);
    }
}
