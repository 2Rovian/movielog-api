package com.movielog.movielog_api.services;

import com.movielog.movielog_api.dtos.request.MovieRequestDTO;
import com.movielog.movielog_api.dtos.response.MovieResponseDTO;
import com.movielog.movielog_api.entities.MovieEntity;
import com.movielog.movielog_api.exceptions.DuplicateMovieException;
import com.movielog.movielog_api.exceptions.NotFoundMovieException;
import com.movielog.movielog_api.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieResponseDTO createMovie(MovieRequestDTO movieRequestDTO) {
        if(movieRepository.existsByTitleIgnoreCaseAndImdb(movieRequestDTO.title(), movieRequestDTO.imdb())) {
            throw new DuplicateMovieException("Movie already exists.");
        }

        MovieEntity movieEntity = new MovieEntity(movieRequestDTO.title(), movieRequestDTO.imdb());
        movieRepository.save(movieEntity);
        return new MovieResponseDTO(
                movieEntity.getId(),
                movieEntity.getTitle(),
                movieEntity.getImdb(),
                movieEntity.getUpdatedAt(),
                movieEntity.getCreatedAt());
    }

    public List<MovieResponseDTO> searchMovie(String title, BigDecimal imdb) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle(title);
        movieEntity.setImdb(imdb);
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnorePaths("id", "createdAt", "updatedAt")
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<MovieEntity> example = Example.of(movieEntity, matcher);
        return movieRepository.findAll(example)
                .stream()
                .map(movie -> new MovieResponseDTO(
                        movie.getId(),
                        movie.getTitle(),
                        movie.getImdb(),
                        movie.getUpdatedAt(),
                        movie.getCreatedAt())).toList();
    }

    public MovieResponseDTO searchMovieById(Long id) {
        MovieEntity movieEntity = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundMovieException("Movie not found."));
        return new MovieResponseDTO(
                movieEntity.getId(),
                movieEntity.getTitle(),
                movieEntity.getImdb(),
                movieEntity.getUpdatedAt(),
                movieEntity.getCreatedAt());
    }

    public void deleteMovieById(Long id) {
        if(!movieRepository.existsById(id)) {
            throw new NotFoundMovieException("Movie not found.");
        }
        movieRepository.deleteById(id);
    }

    public MovieResponseDTO updateMovieWithPut(Long id, MovieRequestDTO movieRequestDTO) {
        MovieEntity movieEntity = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundMovieException("Movie not found."));

        movieEntity.setTitle(movieRequestDTO.title());
        movieEntity.setImdb(movieRequestDTO.imdb());

        MovieEntity attMovie = movieRepository.save(movieEntity);

        return new MovieResponseDTO(
                attMovie.getId(),
                attMovie.getTitle(),
                attMovie.getImdb(),
                attMovie.getUpdatedAt(),
                attMovie.getCreatedAt());
    }
}
