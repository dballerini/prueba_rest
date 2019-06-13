package com.findo.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.findo.test.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
