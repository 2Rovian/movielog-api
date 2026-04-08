package com.movielog.movielog_api.controllers;

import com.movielog.movielog_api.dtos.request.MovieRequestDTO;
import com.movielog.movielog_api.dtos.response.MovieResponseDTO;
import com.movielog.movielog_api.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) BigDecimal imdb
    ) {
        return ResponseEntity.ok(movieService.searchMovie(title, imdb));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.searchMovieById(id));
    }

    @PostMapping
    public ResponseEntity<MovieResponseDTO> postMovie(@RequestBody MovieRequestDTO movieRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.createMovie(movieRequestDTO));
    }
}
