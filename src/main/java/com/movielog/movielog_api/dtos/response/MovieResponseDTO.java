package com.movielog.movielog_api.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovieResponseDTO(Long id, String title, BigDecimal imdb, LocalDateTime updatedAt, LocalDateTime createdAt) {
}
