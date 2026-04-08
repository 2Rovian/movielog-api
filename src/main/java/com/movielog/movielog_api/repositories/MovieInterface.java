package com.movielog.movielog_api.repositories;

import com.movielog.movielog_api.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieInterface extends JpaRepository<MovieEntity, Long> {
}
