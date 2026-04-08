package com.movielog.movielog_api.dtos.response;

import java.time.LocalDateTime;

public record MovieResponseDTO(Long id, String title, Double imdb, LocalDateTime updatedAt, LocalDateTime createdAt) {
}
