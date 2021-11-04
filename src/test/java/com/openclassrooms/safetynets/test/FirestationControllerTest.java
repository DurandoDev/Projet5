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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FirestationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	//Tests Unitaires

	@Test
	public void testGetFirestations() throws Exception {
		mockMvc.perform(get("/firestations"))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteFirestation() throws Exception {
		mockMvc.perform(delete("/firestation/2"))
				.andExpect(status().isOk());
	}

	//Tests d'intégration

	@Test
	public void testGetFirestationsIT() throws Exception {
		mockMvc.perform(get("/firestations"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("834 Binoc Ave")));
	}

	@Test
	public void testGetFirestation1IT() throws Exception {
		mockMvc.perform(get("/firestation/1"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("1509 Culver St")));
	}

	@Test
	public void testPostFirestationIT() throws Exception {
		mockMvc.perform(post("/firestation")
				.content("{\"address\":\"Test\",\"station\":\"10\"}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Test")));
	}

	@Test
	public void testUpdateFirestationIT() throws Exception {
		mockMvc.perform(put("/firestation/4")
						.content("{\"address\":\"Test\",\"station\":\"10\"}")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.station").value("10"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.address").value("Test"));
	}

	@Test
	public void testphoneAlerttIT() throws Exception {

		mockMvc.perform(get("/phoneAlert")
						.param("firestation","4")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.phones[0].phone").value("841-874-9845"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.phones[1].phone").value("841-874-6874"));

	}

	@Test
	public void testfloodIT() throws Exception {

		mockMvc.perform(get("/flood")
						.param("station","4")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].persons[0].address").value("489 Manchester St"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].persons[0].age").value("27"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].persons[0].allergies[0].allergies").value("shellfish"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].persons[2].email").value("aly@imail.com"));

	}


}
