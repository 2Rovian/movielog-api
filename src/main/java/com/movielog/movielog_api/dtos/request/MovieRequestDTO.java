package com.movielog.movielog_api.dtos.request;

import java.math.BigDecimal;

public record MovieRequestDTO(String title, BigDecimal imdb) {
}
