package com.happy.java.happy.repositories;

import com.happy.java.happy.models.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByLastName(String lastName);

    List<Person> findByFirstName(String firstName);

    List<Person> findByLastNameAndFirstName(String lastName, String firstName);

    List<Person> findBySalaryGreaterThan(Double amount);

}
