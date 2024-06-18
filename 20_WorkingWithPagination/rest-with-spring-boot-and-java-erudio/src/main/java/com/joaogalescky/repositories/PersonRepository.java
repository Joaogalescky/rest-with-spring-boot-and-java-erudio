package com.joaogalescky.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.joaogalescky.model.Person;

@Repository // Opcional a partir da versão Spring Boot 3.0.1
public interface PersonRepository extends JpaRepository<Person, Long> {
	@Modifying
	@Query("UPDATE Person p SET p.enabled = false WHERE p.id =:id")
	void disablePerson(@Param("id") Long id);
}