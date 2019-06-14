package com.findo.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.findo.test.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
