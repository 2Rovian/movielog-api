package com.movielog.movielog_api.dtos.errors;

import java.time.LocalDateTime;

public record RestApiErrorDTO(
        int status,
        String error,
        String message,
        String location,
        LocalDateTime timestamp
) {
}
