package com.atlas.userservice.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.atlas.userservice.exception.UserNotFoundException;
import com.atlas.userservice.repository.UserEntityRepository;
import com.atlas.userservice.repository.entity.UserEntity;
import com.atlas.userservice.service.UserService;
import com.atlas.userservice.service.dto.CommonResponseDTO;
import com.atlas.userservice.service.dto.UserRequestDTO;
import com.atlas.userservice.service.dto.UserResponseDTO;
import com.atlas.userservice.service.mapper.UserMapper;

/*
 *Implementation of User Service  
 */
@Service
public class UserServiceImpl implements UserService {

	private final UserEntityRepository userEntityRepository;
	private final UserMapper userMapper;

	/*
	 * Constructor injection
	 * 
	 * @param userEntityRepository
	 * 
	 * @param userMApper
	 */
	public UserServiceImpl(UserEntityRepository userEntityRepository, UserMapper userMapper) {
		super();
		this.userEntityRepository = userEntityRepository;
		this.userMapper = userMapper;
	}

	@Override
	public CommonResponseDTO<Long> saveUser(UserRequestDTO userRequestDTO) {
		UserEntity userEntity = userMapper.toEntity(userRequestDTO);
		UserEntity saveduserEntity = userEntityRepository.save(userEntity);

		if (saveduserEntity != null) {
			CommonResponseDTO<Long> response = new CommonResponseDTO<>("userdetails are successfully inserted ",
					HttpStatus.CREATED.value(), saveduserEntity.getUserId(), LocalDateTime.now());
			return response;
		}
		return null;
	}

	@Override
	public CommonResponseDTO<UserResponseDTO> getUser(Long userId) {
		Optional<UserEntity> optionalUser = userEntityRepository.findById(userId);
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("user details are not found fo given user id:" + userId);
		}
		UserEntity userEntity = optionalUser.get();

		return new CommonResponseDTO<UserResponseDTO>("user details successfully  fetched or retrived",
				HttpStatus.OK.value(), userMapper.toDto(userEntity), LocalDateTime.now());
	}

	@Override
	public CommonResponseDTO<List<UserResponseDTO>> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResponseDTO<UserResponseDTO> updateUser(Long userID, UserResponseDTO userResponseDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResponseDTO<Void> deletUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResponseDTO<UserResponseDTO> getUser(String emailId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
