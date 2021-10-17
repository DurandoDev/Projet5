package com.openclassrooms.safetynets.test;

import com.openclassrooms.safetynets.controller.PersonController;
import com.openclassrooms.safetynets.dto.PersonDTO;
import com.openclassrooms.safetynets.model.Person;
import com.openclassrooms.safetynets.repository.PersonRepo;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

//	@MockBean
//	private PersonRepo personRepo;

//	@BeforeEach
//	public void mockDB() {
//
//		List<Person> listPerson = new ArrayList<>();
//		Person person = new Person();
//
//		person.setFirstName("John");
//		person.setLastName("Boyd");
//		person.setAddress("1509 Culver St");
//		person.setCity("Culver");
//		person.setId(1);
//
//		listPerson.add(person);
//
//		Mockito.when(personRepo.findAll()).thenReturn(listPerson);
//	}

	//Tests Unitaires

	@Test
	public void testGetPerson() throws Exception {
		mockMvc.perform(get("/person"))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeletePersonIT() throws Exception {
		mockMvc.perform(delete("/person/2"))
				.andExpect(status().isOk());
	}

	//Tests d'intégration

	@Test
	public void testGetPersonIT() throws Exception {
		mockMvc.perform(get("/person"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("John")));
	}
	@Test
	public void testGetPerson1IT() throws Exception {
		mockMvc.perform(get("/person/1"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("John")));
	}

	@Test
	public void testPostPersonIT() throws Exception {
		mockMvc.perform(post("/person")
				.content("{\"firstName\":\"Toto\",\"lastName\":\"Tutu\",\"address\":\"1509 Culver Treet\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
