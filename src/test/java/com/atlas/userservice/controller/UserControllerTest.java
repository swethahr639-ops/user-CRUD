package com.atlas.userservice.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.atlas.userservice.repository.UserEntityRepository;
import com.atlas.userservice.service.UserService;
import com.atlas.userservice.service.dto.CommonResponseDTO;
import com.atlas.userservice.service.dto.UserRequestDTO;
import com.atlas.userservice.service.dto.UserResponseDTO;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class) // Load only UserController

public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	@MockitoBean
	private UserEntityRepository userEntityRepository;

	@Test
	void saveUserTest() throws Exception {
		UserRequestDTO userRequestDTO = new UserRequestDTO();
		userRequestDTO.setFirstName("Pushpa");
		userRequestDTO.setLastName("Raj");
		userRequestDTO.setContactNumber("9234567890");
		userRequestDTO.setEmailId("pushparaj12@gmail.com");
		userRequestDTO.setPassword("Push@12345");
		userRequestDTO.setDateOfBirth(LocalDate.of(2000, 2, 2));

		String requestbody = objectMapper.writeValueAsString(userRequestDTO);
		CommonResponseDTO<Long> response = new CommonResponseDTO<>("UserDetails are succesfully inserted", 201, 1l,
				LocalDateTime.now());
		Mockito.when(userService.saveUser(any(UserRequestDTO.class))).thenReturn(response);

		mockMvc.perform(post("/api/v0/users").contentType(MediaType.APPLICATION_JSON).content(requestbody))
				.andExpect(status().isCreated());

	}

	@Test
	void getUserTest() throws Exception {

		Long userId = 1l;
		CommonResponseDTO<UserResponseDTO> response = new CommonResponseDTO<>("UserDetails are succesfully inserted",
				200, new UserResponseDTO(), LocalDateTime.now());
		Mockito.when(userService.getUser(anyLong())).thenReturn(response);
		mockMvc.perform(get("/api/v0/users/" + userId).contentType(MediaType.APPLICATION_JSON))
				.andExpectAll(status().isOk());

	}

	@Test
	void getAllUsersTest() throws Exception {
		CommonResponseDTO<List<UserResponseDTO>> response = new CommonResponseDTO<>(
				"UserDetails are succesfully fetched", 200, new ArrayList<>(), LocalDateTime.now());
		Mockito.when(userService.getUsers()).thenReturn(response);
		mockMvc.perform(get("/api/v0/users").contentType(MediaType.APPLICATION_JSON)).andExpectAll(status().isOk());

	}

@Test
	void updateUserTest() throws Exception{
		Long userId=1l;
		CommonResponseDTO<UserResponseDTO> response=new CommonResponseDTO<>("UserDetails are successfully updated ",200 , new UserResponseDTO(), LocalDateTime.now());
				UserRequestDTO userRequestDTO = new UserRequestDTO();
		userRequestDTO.setFirstName("Pushpa");
		userRequestDTO.setLastName("Raj");
		userRequestDTO.setContactNumber("9234567890");
		userRequestDTO.setEmailId("pushparaj12@gmail.com");
		userRequestDTO.setPassword("Push@12345");
		userRequestDTO.setDateOfBirth(LocalDate.of(2000, 2, 2));

		String requestbody = objectMapper.writeValueAsString(userRequestDTO);
		Mockito.when(userService.updateUser(anyLong(), any(UserRequestDTO.class))).thenReturn(response);
		mockMvc.perform(put("/api/v0/users/"+userId).contentType(MediaType.APPLICATION_JSON).content(requestbody))
		.andExpect(status().isOk());
	}

	void deletUserTest
}
