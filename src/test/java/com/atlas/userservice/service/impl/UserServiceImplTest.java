package com.atlas.userservice.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.RETURNS_DEFAULTS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.atlas.userservice.constants.ApplicationConstants;
import com.atlas.userservice.exception.BadRequestException;
import com.atlas.userservice.exception.InvalidUserException;
import com.atlas.userservice.exception.UserNotFoundException;
import com.atlas.userservice.repository.UserEntityRepository;
import com.atlas.userservice.repository.entity.UserEntity;
import com.atlas.userservice.service.dto.CommonResponseDTO;
import com.atlas.userservice.service.dto.UserRequestDTO;
import com.atlas.userservice.service.dto.UserResponseDTO;
import com.atlas.userservice.service.mapper.UserMapper;

@ExtendWith(MockitoExtension.class) //

public class UserServiceImplTest {
	@Mock
	private UserEntityRepository userEntityRepository;
	@Mock
	private UserMapper userMapper;

	@InjectMocks
	private UserServiceImpl usersServiceImpl;

	@Test
	void saveUserTest() {
		UserRequestDTO userRequestDTO = Mockito.mock(UserRequestDTO.class);
		UserEntity userEntity = Mockito.mock(UserEntity.class);
		Mockito.when(userMapper.toEntity(any(UserRequestDTO.class))).thenReturn(userEntity);
		UserEntity savedUserEntity = Mockito.mock(UserEntity.class);
		Mockito.when(savedUserEntity.getUserId()).thenReturn(1l);
		Mockito.when(userEntityRepository.save(any(UserEntity.class))).thenReturn(savedUserEntity);
		CommonResponseDTO<Long> response = usersServiceImpl.saveUser(userRequestDTO);
		assertNotNull(response);
		assertEquals(201, response.getStatus());
		assertEquals(1l, response.getData());

	}

	@Test
	void saveUserTest_null() {
		UserRequestDTO userRequestDTO = Mockito.mock(UserRequestDTO.class);
		UserEntity userEntity = Mockito.mock(UserEntity.class);
		Mockito.when(userMapper.toEntity(any(UserRequestDTO.class))).thenReturn(userEntity);
		UserEntity savedUserEntity = null;

		Mockito.when(userEntityRepository.save(any(UserEntity.class))).thenReturn(savedUserEntity);
		CommonResponseDTO<Long> response = usersServiceImpl.saveUser(userRequestDTO);
		assertNull(response);

	}

	@Test
	void getUserTest() {
		Long userId = 1l;
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(1l);
		userEntity.setFirstName("pushpa");
		userEntity.setLastName("Raj");
		userEntity.setContactnumber("1234567890");
		userEntity.setEmailId("pushparaj@gmail.com");
		userEntity.setPassword("Push@123");

		Optional<UserEntity> optionalUser = Optional.of(userEntity);
		Mockito.when(userEntityRepository.findById(anyLong())).thenReturn(optionalUser);
		UserResponseDTO userResponseDTO = Mockito.mock(UserResponseDTO.class);
		Mockito.when(userMapper.toDto(any(UserEntity.class))).thenReturn(userResponseDTO);
		CommonResponseDTO<UserResponseDTO> response = usersServiceImpl.getUser(userId);
		assertEquals(200, response.getStatus());
		assertNotNull(response.getData());

	}

	@Test
	void getUserTest_userNotFound() {
		Long userId = 1l;
		Optional<UserEntity> optionalUser = Optional.empty();
		Mockito.when(userEntityRepository.findById(anyLong())).thenReturn(optionalUser);
		UserNotFoundException ex = assertThrows(UserNotFoundException.class, () -> usersServiceImpl.getUser(userId));
		assertNotNull(ex);
		assertEquals(ApplicationConstants.USER_NOT_FOUND_MESSAGE + userId, ex.getMessage());
	}

	@Test
	void getUsersTest() {
		UserEntity userEntity1 = Mockito.mock(UserEntity.class);
		UserEntity userEntity2 = Mockito.mock(UserEntity.class);
		List<UserEntity> usersList = List.of(userEntity1, userEntity2);
		Mockito.when(userEntityRepository.findAll()).thenReturn(usersList);
		UserResponseDTO userResponseDTO = Mockito.mock(UserResponseDTO.class);
		Mockito.when(userMapper.toDto(any(UserEntity.class))).thenReturn(userResponseDTO);
		CommonResponseDTO<List<UserResponseDTO>> response = usersServiceImpl.getUsers();
		assertNotNull(response);
		assertEquals(200, response.getStatus());
		assertNotNull(response.getData());
		assertEquals(2, response.getData().size());
	}

	@Test
	void updateUserTest() {
		Long userId = 1l;
		UserRequestDTO userRequestDTO = Mockito.mock(UserRequestDTO.class);
		UserEntity userEntity = Mockito.mock(UserEntity.class);
		Optional<UserEntity> optionalUser = Optional.of(userEntity);
		Mockito.when(userEntityRepository.findById(anyLong())).thenReturn(optionalUser);
		Mockito.doNothing().when(userMapper).updateEntity(any(UserRequestDTO.class), any(UserEntity.class));
		UserEntity updatedUserEntity = Mockito.mock(UserEntity.class);
		Mockito.when(userEntityRepository.save(any(UserEntity.class))).thenReturn(updatedUserEntity);
		UserResponseDTO userResponseDTO = Mockito.mock(UserResponseDTO.class);
		Mockito.when(userMapper.toDto(any(UserEntity.class))).thenReturn(userResponseDTO);
		CommonResponseDTO<UserResponseDTO> response = usersServiceImpl.updateUser(userId, userRequestDTO);
		assertNotNull(response);
		assertEquals(200, response.getStatus());
		assertNotNull(response.getData());

	}

	@Test
	void updateUserTest_badRequest() {
		UserRequestDTO userRequestDTO = null;
		BadRequestException ex = assertThrows(BadRequestException.class,
				() -> usersServiceImpl.updateUser(1l, userRequestDTO));
		assertNotNull(ex);
		assertEquals("Request body mst be entered", ex.getMessage());
	}

	@Test
	void updateUserTest_userNotFound() {
		Long userId = 1l;
		UserRequestDTO userRequestDTO = Mockito.mock(UserRequestDTO.class);
		Optional<UserEntity> optionalUser = Optional.empty();
		Mockito.when(userEntityRepository.findById(anyLong())).thenReturn(optionalUser);
		UserNotFoundException ex = assertThrows(UserNotFoundException.class,
				() -> usersServiceImpl.updateUser(userId, userRequestDTO));
		assertNotNull(ex);
		assertEquals("user details are not fount fr given userId:" + userId, ex.getMessage());

	}

	@Test
	void deletUserTest() {
		Long userID = 1l;
		UserEntity userEntity = Mockito.mock(UserEntity.class);
		Optional<UserEntity> optionalUser = Optional.of(userEntity);
		Mockito.when(userEntityRepository.findById(anyLong())).thenReturn(optionalUser);
		Mockito.doNothing().when(userEntityRepository).delete(any(UserEntity.class));
		CommonResponseDTO<Void> response = usersServiceImpl.deletUser(userID);
		assertNotNull(response);
		assertEquals(204, response.getStatus());
	}

	@Test
	void deletUserTest_useNotFound() {

		Mockito.when(userEntityRepository.findById(anyLong())).thenReturn(Optional.empty());
		UserNotFoundException ex = assertThrows(UserNotFoundException.class, () -> usersServiceImpl.deletUser(1l));
		assertNotNull(ex);
		assertEquals("user details are not found for given userId:" + 1l, ex.getMessage());

	}

	@Test
	void getUserTest_login() {
		String emailId = "pushparaj@gmail.com";
		String password = "pushpa@123";
		Optional<UserEntity> optionalUser = Optional.of(Mockito.mock(UserEntity.class));
		Mockito.when(userEntityRepository.findByEmailIdAndPassword(anyString(), anyString())).thenReturn(optionalUser);
		Mockito.when(userMapper.toDto(any(UserEntity.class))).thenReturn(Mockito.mock(UserResponseDTO.class));
		CommonResponseDTO<UserResponseDTO> response = usersServiceImpl.getUser(emailId, password);
		assertNotNull(response);
		assertEquals(200, response.getStatus());
		assertNotNull(response.getData());

	}
     @Test
	void getUserTest_login_invalidUser() {

		//String emailId = "pushparaj@gmail.com";
		//String password = "pushpa@123";
		Mockito.when(userEntityRepository.findByEmailIdAndPassword(anyString(), anyString()))
				.thenReturn(Optional.empty());
		InvalidUserException ex = assertThrows(InvalidUserException.class,
				() -> usersServiceImpl.getUser("pushparaj@gmail.com", "pushpa@123"));
		assertNotNull(ex);
		assertEquals("Invalid emailId and password", ex.getMessage());
	}

}
