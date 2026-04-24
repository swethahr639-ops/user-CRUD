package com.atlas.userservice.service.mapper;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.atlas.userservice.exception.BadRequestException;
import com.atlas.userservice.repository.entity.UserEntity;
import com.atlas.userservice.service.dto.UserRequestDTO;
import com.atlas.userservice.service.dto.UserResponseDTO;

@Component
public class UserMapper {
	public UserEntity toEntity(UserRequestDTO userRequestDTO) {
		if (userRequestDTO == null) {
			throw new BadRequestException("User Request must not be null");
		}
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName(userRequestDTO.getFirstName());
		userEntity.setLastName(userRequestDTO.getLastName());
		userEntity.setContactnumber(userRequestDTO.getContactNumber());
		userEntity.setEmailId(userRequestDTO.getEmailId());
		userEntity.setDateOfBirth(Date.valueOf(userRequestDTO.getDateOfBirth()));

		return userEntity;

	}

	public UserResponseDTO toDto(UserEntity userEntity) {
		if (userEntity != null) {
			UserResponseDTO userResponseDTO = new UserResponseDTO();
			userResponseDTO.setFirstName(userEntity.getFirstName());
			userResponseDTO.setLastName(userEntity.getLastName());
			userResponseDTO.setContactNumber(userEntity.getContactnumber());
			userResponseDTO.setEmailId(userEntity.getEmailId());
			userResponseDTO.setCreatedAt(userEntity.getCreatedAt());
			userResponseDTO.setUpdatedAt(userEntity.getUpdatedAt());
			userResponseDTO.setPassword(userEntity.getPassword());
			userResponseDTO.setStatus(userEntity.getStatus());
			userResponseDTO.setLastlogin(userEntity.getLastlogin());
			
			if (userEntity.getDateOfBirth() != null) {
				userResponseDTO.setDateOfBirth(userEntity.getDateOfBirth().toLocalDate());

			}
			return userResponseDTO;

		}
		return null;

	}
 
	public void updateEntity(UserResponseDTO userResponseDTO,UserEntity userEntity) {
		if (userResponseDTO !=null&&userEntity!=null ) {
			userEntity.setFirstName(userResponseDTO.getFirstName());
			userEntity.setLastName(userResponseDTO.getLastName());
			userEntity.setContactnumber(userResponseDTO.getContactNumber());
			userEntity.setEmailId(userResponseDTO.getEmailId());
			userEntity.setDateOfBirth(Date.valueOf(userResponseDTO.getDateOfBirth()));
			
			
		}
	}
}
