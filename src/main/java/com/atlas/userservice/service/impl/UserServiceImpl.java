package com.atlas.userservice.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.atlas.userservice.UserserviceApplication;
import com.atlas.userservice.constants.ApplicationConstants;
import com.atlas.userservice.exception.BadRequestException;
import com.atlas.userservice.exception.InvalidUserException;
import com.atlas.userservice.exception.UserNotFoundException;
import com.atlas.userservice.exception.handler.GlobalExceptionHandler;
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

	private final GlobalExceptionHandler globalExceptionHandler;

	private final UserserviceApplication userserviceApplication;

	private final UserEntityRepository userEntityRepository;
	private final UserMapper userMapper;

	/*
	 * Constructor injection
	 * 
	 * @param userEntityRepository
	 * 
	 * @param userMApper
	 */
	public UserServiceImpl(UserEntityRepository userEntityRepository, UserMapper userMapper,
			UserserviceApplication userserviceApplication, GlobalExceptionHandler globalExceptionHandler) {
		super();
		this.userEntityRepository = userEntityRepository;
		this.userMapper = userMapper;
		this.userserviceApplication = userserviceApplication;
		this.globalExceptionHandler = globalExceptionHandler;
	}

	@Override
	public CommonResponseDTO<Long> saveUser(UserRequestDTO userRequestDTO) {
		UserEntity userEntity = userMapper.toEntity(userRequestDTO);
		UserEntity saveduserEntity = userEntityRepository.save(userEntity);

		if (saveduserEntity != null) {
			CommonResponseDTO<Long> response = new CommonResponseDTO<>(ApplicationConstants.SAVE_USER_MESSAGE,
					HttpStatus.CREATED.value(), saveduserEntity.getUserId(), LocalDateTime.now());
			return response;
		}
		return null;
	}

	@Override
	public CommonResponseDTO<UserResponseDTO> getUser(Long userId) {
		Optional<UserEntity> optionalUser = userEntityRepository.findById(userId);
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException(ApplicationConstants.USER_NOT_FOUND_MESSAGE+ userId);
		}
		UserEntity userEntity = optionalUser.get();

		return new CommonResponseDTO<UserResponseDTO>(ApplicationConstants.FETCHED_USER_DETAILS,
				HttpStatus.OK.value(), userMapper.toDto(userEntity), LocalDateTime.now());
	}

	@Override
	public CommonResponseDTO<List<UserResponseDTO>> getUsers() {
		List<UserEntity> usersList = userEntityRepository.findAll();
		List<UserResponseDTO> users = new ArrayList<>();
		for (UserEntity userEntity : usersList) {
			UserResponseDTO userResponseDTO = userMapper.toDto(userEntity);
			users.add(userResponseDTO);
		}
		return new CommonResponseDTO<>(ApplicationConstants.FETCHED_USER_DETAILS, HttpStatus.OK.value(), users,
				LocalDateTime.now());
	}

	public CommonResponseDTO<UserResponseDTO> updateUser(Long userID, UserRequestDTO userRequestDTO) {
		if (userRequestDTO == null) {
			throw new BadRequestException("Request body mst be entered");
		}
		Optional<UserEntity> optionalUser = userEntityRepository.findById(userID);
		if (optionalUser.isPresent()) {
			UserEntity userEntity = optionalUser.get();
			userMapper.updateEntity(null, userEntity);
			UserEntity updatedUserEntity = userEntityRepository.save(userEntity);
			UserResponseDTO userResponseDTO = userMapper.toDto(updatedUserEntity);

			return new CommonResponseDTO<>(ApplicationConstants.UPDATE_USER_DETAILS, HttpStatus.OK.value(),
					userResponseDTO, LocalDateTime.now());
		}
		throw new UserNotFoundException("user details are not fount fr given userId:" + userID);
	}

	@Override
	public CommonResponseDTO<Void> deletUser(Long userId) {
		Optional<UserEntity> optionalUser = userEntityRepository.findById(userId);
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("user details are not found for given userId:" + userId);
		}
		userEntityRepository.delete(optionalUser.get());

		return new CommonResponseDTO<>(ApplicationConstants.DELETE_USER_DETAILS,
				HttpStatus.NO_CONTENT.value(), null, LocalDateTime.now());
	}


	@Override
	public CommonResponseDTO<UserResponseDTO> getUser(String emailId, String password) {
		Optional<UserEntity> optionalUser =userEntityRepository.findByEmailIdAndPassword(emailId,password);
		
		if(optionalUser.isEmpty())
		{
		throw new InvalidUserException("Invalid emailId and password");
		}
		
		return new CommonResponseDTO<>( ApplicationConstants.GET_USER_LOGIN , HttpStatus.OK.value(),userMapper.toDto(optionalUser.get()) , LocalDateTime.now());
	}

	@Override
	public CommonResponseDTO<UserResponseDTO> updateUser(Long userID, UserResponseDTO userResponseDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
