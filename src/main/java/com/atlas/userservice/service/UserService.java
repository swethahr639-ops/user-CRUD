package com.atlas.userservice.service;
/*
 * Service interface for User Operations 
 */

import java.util.List;

import com.atlas.userservice.service.dto.CommonResponseDTO;
import com.atlas.userservice.service.dto.UserRequestDTO;
import com.atlas.userservice.service.dto.UserResponseDTO;

public interface UserService {
	public CommonResponseDTO<Long> saveUser(UserRequestDTO userRequestDTO);

	public CommonResponseDTO<UserResponseDTO> getUser(Long userId);

	public CommonResponseDTO<List<UserResponseDTO>> getUsers();

	public CommonResponseDTO<UserResponseDTO> updateUser(Long userID, UserRequestDTO userRequestDTO);

	public CommonResponseDTO<Void> deletUser(Long userId);

	public CommonResponseDTO<UserResponseDTO> getUser(String emailId, String password);

}
