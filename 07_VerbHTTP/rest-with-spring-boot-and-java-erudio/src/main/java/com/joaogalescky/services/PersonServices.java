package com.joaogalescky.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.joaogalescky.model.Person;

@Service
public class PersonServices {

	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	public List<Person> findAll() {
		logger.info("Finding all people!");
		List<Person> persons = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		return persons;
	}

	public Person findById(String id) {
		logger.info("Finding one person!");
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Leandro");
		person.setLastName("Costa");
		person.setGender("Male");
		person.setAddress("Paranaguá - Paraná - Brasil");
		return person;
	}

	private Person mockPerson(int i) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name " + i);
		person.setLastName("Last name " + i);
		person.setGender("Male ");
		person.setAddress("Some address in Brazil " + i);
		return person;
	}
}