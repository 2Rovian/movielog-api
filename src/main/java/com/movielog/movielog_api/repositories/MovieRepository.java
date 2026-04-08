package com.movielog.movielog_api.repositories;

import com.movielog.movielog_api.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    public boolean existsByTitleContainingIgnoreCaseAndImdb(String title, BigDecimal imdb);
}
