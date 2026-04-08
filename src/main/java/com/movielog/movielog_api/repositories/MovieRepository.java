package com.movielog.movielog_api.repositories;

import com.movielog.movielog_api.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    public boolean existsByTitleIgnoreCaseAndImdb(String title, BigDecimal imdb);
}
