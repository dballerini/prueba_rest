package com.findo.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.findo.prueba.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
