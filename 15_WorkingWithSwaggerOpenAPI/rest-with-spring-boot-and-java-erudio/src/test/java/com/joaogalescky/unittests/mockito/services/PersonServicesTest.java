package com.joaogalescky.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.joaogalescky.data.vo.v1.PersonVO;
import com.joaogalescky.exceptions.RequiredObjectIsNullException;
import com.joaogalescky.model.Person;
import com.joaogalescky.repositories.PersonRepository;
import com.joaogalescky.services.PersonServices;
import com.joaogalescky.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

	MockPerson input;

	@InjectMocks
	private PersonServices service;

	@Mock
	PersonRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		List<Person> list = input.mockEntityList();
		when(repository.findAll()).thenReturn((list));
		var people = service.findAll();
		assertNotNull(people);
		assertEquals(14, people.size());
		var PersonOne = people.get(1);
		assertNotNull(PersonOne);
		assertNotNull(PersonOne.getKey());
		assertNotNull(PersonOne.getLinks());
		assertTrue(PersonOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", PersonOne.getAddress());
		assertEquals("First Name Test1", PersonOne.getFirstName());
		assertEquals("Last Name Test1", PersonOne.getLastName());
		assertEquals("Female", PersonOne.getGender());
		var PersonFour = people.get(4);
		assertNotNull(PersonFour);
		assertNotNull(PersonFour.getKey());
		assertNotNull(PersonFour.getLinks());
		assertTrue(PersonFour.toString().contains("links: [</api/person/v1/4>;rel=\"self\"]"));
		assertEquals("Addres Test4", PersonFour.getAddress());
		assertEquals("First Name Test4", PersonFour.getFirstName());
		assertEquals("Last Name Test4", PersonFour.getLastName());
		assertEquals("Male", PersonFour.getGender());
		var PersonSeven = people.get(7);
		assertNotNull(PersonSeven);
		assertNotNull(PersonSeven.getKey());
		assertNotNull(PersonSeven.getLinks());
		assertTrue(PersonSeven.toString().contains("links: [</api/person/v1/7>;rel=\"self\"]"));
		assertEquals("Addres Test7", PersonSeven.getAddress());
		assertEquals("First Name Test7", PersonSeven.getFirstName());
		assertEquals("Last Name Test7", PersonSeven.getLastName());
		assertEquals("Female", PersonSeven.getGender());
		var PersonThirteen = people.get(13);
		assertNotNull(PersonThirteen);
		assertNotNull(PersonThirteen.getKey());
		assertNotNull(PersonThirteen.getLinks());
		assertTrue(PersonThirteen.toString().contains("links: [</api/person/v1/13>;rel=\"self\"]"));
		assertEquals("Addres Test13", PersonThirteen.getAddress());
		assertEquals("First Name Test13", PersonThirteen.getFirstName());
		assertEquals("Last Name Test13", PersonThirteen.getLastName());
		assertEquals("Female", PersonThirteen.getGender());
	}

	@Test
	void testFindById() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testCreate() {
		Person entity = input.mockEntity(1);
		Person persisted = entity;
		persisted.setId(1L);
		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);
		when(repository.save(entity)).thenReturn(persisted);
		var result = service.create(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testCreateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		String expectedMessage = "It's not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testUpdate() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		Person persisted = entity;
		persisted.setId(1L);
		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		var result = service.update(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testUpdateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null);
		});
		String expectedMessage = "It's not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testDelete() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		service.delete(1L);
	}
}