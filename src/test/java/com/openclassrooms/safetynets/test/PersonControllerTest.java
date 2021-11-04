package com.openclassrooms.safetynets.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	//Tests Unitaires

	@Test
	public void testGetPerson() throws Exception {
		mockMvc.perform(get("/person"))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeletePerson() throws Exception {
		mockMvc.perform(delete("/person/2"))
				.andExpect(status().isOk());
	}

	@Test
	public void testChildAlert() throws Exception {

		String address = "947 E. Rose Dr";

		mockMvc.perform(get("/childAlert")
				.param("address",address)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
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
				.content("{\"firstName\":\"Toto\",\"lastName\":\"Tutu\",\"address\":\"1509 Culver St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testUpdatePersonIT() throws Exception {
		mockMvc.perform(put("/person/1")
				.content("{\"address\":\"test\",\"city\":\"testCity\",\"zip\":\"11111\",\"phone\":\"001-001-0001\",\"email\":\"test@email.com\"}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.address").value("test"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.city").value("testCity"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.zip").value("11111"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("001-001-0001"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("test@email.com"));

	}

	@Test
	public void testChildAlertIT() throws Exception {
		
		String address = "947 E. Rose Dr";
		
		mockMvc.perform(get("/childAlert")
				.param("address",address)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Kendrik")));
	}

	@Test
	public void testFireIT() throws Exception {

		String address = "947 E. Rose Dr";

		mockMvc.perform(get("/fire")
						.param("address",address)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Kendrik")))
				.andExpect(jsonPath("$.persons[0].phoneNum").value("841-874-7784"))
				.andExpect(jsonPath("$.persons[1].email").value("ssanw@email.com"))
				.andExpect(jsonPath("$.persons[2].age").value("7"))
				.andExpect(jsonPath("$.persons").isArray())
				.andExpect(jsonPath("$.persons[0].allergies").isArray())
				.andExpect(jsonPath("$.persons[1].allergies").isArray())
				.andExpect(jsonPath("$.persons[2].allergies").exists());
	}




	@Test
	public void testPersonInfoIT() throws Exception {

		String firstName = "John";
		String lastName = "Boyd";
		MultiValueMap<String,String> requestParams = new LinkedMultiValueMap<>();
		requestParams.put("firstName", Collections.singletonList(firstName));
		requestParams.put("lastName", Collections.singletonList(lastName));

		mockMvc.perform(get("/personInfo")
						.params(requestParams)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.persons[0].phoneNum").exists())
				.andExpect(jsonPath("$.persons[1].address").value("1509 Culver St"))
				.andExpect(jsonPath("$.persons[2].email").value("tenz@email.com"));
	}

	@Test
	public void testcommunityEmailIT() throws Exception {

		String city = "Culver";

		mockMvc.perform(get("/communityEmail")
						.param("city",city)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("ward@email.com")))
				.andExpect(jsonPath("$.emails").exists());
	}
}
