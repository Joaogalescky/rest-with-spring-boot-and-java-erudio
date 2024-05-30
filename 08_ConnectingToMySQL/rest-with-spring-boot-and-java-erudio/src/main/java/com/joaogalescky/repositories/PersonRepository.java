package com.joaogalescky.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaogalescky.model.Person;

@Repository // Opcional a partir da versão Spring Boot 3.0.1
public interface PersonRepository extends JpaRepository<Person, Long> {
}