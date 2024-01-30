package com.qa.lib.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.lib.domain.Person;

public interface PersonRepo extends JpaRepository<Person, Integer> {

}
