package com.dekra.service;

import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", password = "admin")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class IntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	void integrationTest_initAll() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get("/productos").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(4)));
	}

	@Test
	@Order(2)
	void integrationTest_byId() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get("/productos/{id}", Long.valueOf(1)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Long.valueOf(1)));
	}

	@Test
	@Order(3)
	void integrationTest_add() throws Exception {

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/productos").contentType(MediaType.APPLICATION_JSON).content(
						new ObjectMapper().writeValueAsString(Product.maker().name("test name").description("test description").price(12.5).build())))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	@Order(4)
	void integrationTest_allAfterInsert() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get("/productos").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(5)));
	}

	@Test
	@Order(5)
	void integrationTest_nameLike() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get("/productos/find").param("name", "test").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("test description"));
	}

	@Test
	@Order(6)
	void integrationTest_update() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/productos/{id}", Long.valueOf(1)).contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(
								Product.maker().id(Long.valueOf(1)).name("test name updated").description("test description").price(10.0).build())))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}

	@Test
	@Order(7)
	void integrationTest_byIdCheckUpdate() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get("/productos/{id}", Long.valueOf(1)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(Double.valueOf(10)));
	}

	@Test
	@Order(8)
	void integrationTest_delete() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.delete("/productos/{id}", Long.valueOf(1)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isAccepted());
	}

	@Test
	@Order(9)
	void integrationTest_endAll() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get("/productos").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(4)));
	}

}
