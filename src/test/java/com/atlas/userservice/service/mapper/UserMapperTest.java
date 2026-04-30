package com.atlas.userservice.service.mapper;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.mockito.Mockito;

import com.atlas.userservice.exception.BadRequestException;
import com.atlas.userservice.repository.UserEntityRepository;
import com.atlas.userservice.repository.entity.UserEntity;
import com.atlas.userservice.service.dto.UserRequestDTO;
import com.atlas.userservice.service.dto.UserResponseDTO;

import jakarta.persistence.Id;

@ExtendWith(MockitoExtension.class) // enables Mockito in Junit5
public class UserMapperTest {

	@InjectMocks // Inject Mocks Automatically
	private UserMapper userMapper;

	@Test
	void toEntityTest() {
		UserRequestDTO userRequestDTO = new UserRequestDTO();
		userRequestDTO.setFirstName("Rahul");
		userRequestDTO.setLastName("Sharma");
		userRequestDTO.setContactNumber("1234567890");
		userRequestDTO.setEmailId("rahulsharma@gmail.com");
		userRequestDTO.setPassword("Rahul@12345");
		userRequestDTO.setDateOfBirth(LocalDate.of(1998, 6, 6));

		UserEntity userEntity = userMapper.toEntity(userRequestDTO);
		Assertions.assertNotNull(userEntity);
		assertEquals("Rahul", userEntity.getFirstName());
		assertEquals("Sharma", userEntity.getLastName());
		assertEquals("1234567890", userEntity.getContactnumber());
		assertEquals("rahulsharma@gmail.com", userEntity.getEmailId());
		assertEquals("Rahul@12345", userEntity.getPassword());
		assertEquals(LocalDate.of(1998, 6, 6).toString(), userEntity.getDateOfBirth().toString());

	}

	@Test
	void toEntityTest_badRequest() {
		assertThrows(BadRequestException.class, () -> userMapper.toEntity(null));
	}

	@Test
	void toDTOTest() {
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName("pushpa");
		userEntity.setLastName("Raj");
		userEntity.setContactnumber("1234567890");
		;
		userEntity.setEmailId("pushparaj123@gmail.com");
		userEntity.setPassword("pushpa@123");
		userEntity.setDateOfBirth(Date.valueOf(LocalDate.of(1999, 12, 12)));
		userEntity.setStatus("Active");
		userEntity.setUpdatedAt(LocalDateTime.now());
		userEntity.setUserId(1l);
		userEntity.setCreatedAt(LocalDateTime.now());

		UserResponseDTO userResponseDTO = userMapper.toDto(userEntity);
		assertNotNull(userResponseDTO);
		assertEquals("pushpa", userResponseDTO.getFirstName());
		assertEquals("Raj", userResponseDTO.getLastName());
		assertEquals("1234567890", userResponseDTO.getContactNumber());
		assertEquals("pushparaj123@gmail.com", userResponseDTO.getEmailId());

	}

	@Test
	void toDtoTest_nullVerify() {

		UserResponseDTO userResponseDTO = userMapper.toDto(null);
		assertNull(userResponseDTO);

	}

	@Test
	void toDTOTest_nullDob() {
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName("pushpa");
		userEntity.setLastName("Raj");
		userEntity.setContactnumber("1234567890");
		;
		userEntity.setEmailId("pushparaj123@gmail.com");
		userEntity.setPassword("pushpa@123");
		userEntity.setDateOfBirth(null);
		userEntity.setStatus("Active");
		userEntity.setUpdatedAt(LocalDateTime.now());
		userEntity.setUserId(1l);
		userEntity.setCreatedAt(LocalDateTime.now());

		UserResponseDTO userResponseDTO = userMapper.toDto(userEntity);
		assertNotNull(userResponseDTO);
		assertEquals("pushpa", userResponseDTO.getFirstName());
		assertEquals("Raj", userResponseDTO.getLastName());
		assertEquals("1234567890", userResponseDTO.getContactNumber());
		assertEquals("pushparaj123@gmail.com", userResponseDTO.getEmailId());

	}

	@Test
	void updateEntityTest() {

		UserRequestDTO userRequestDTO = Mockito.mock(UserRequestDTO.class);
		UserEntity userEntity = Mockito.mock(UserEntity.class);
		Mockito.when(userRequestDTO.getDateOfBirth()).thenReturn(LocalDate.of(1999, 12, 12));
		userMapper.updateEntity(userRequestDTO, userEntity);

	}

	@Test
	void updateEntityTest_nullUserEntity() {
		UserRequestDTO userRequestDTO = Mockito.mock(UserRequestDTO.class);
		UserEntity userEntity = null;
		userMapper.updateEntity(userRequestDTO, userEntity);

	}

	@Test
	void updateEntityTest_nullUserRequest() {
		UserRequestDTO userRequestDTO=null;
		UserEntity userEntity = Mockito.mock(UserEntity.class);
		userMapper.updateEntity(userRequestDTO, userEntity);
	}

}
