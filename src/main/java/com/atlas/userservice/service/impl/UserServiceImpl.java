package com.atlas.userservice.service.impl;

import org.springframework.stereotype.Service;

import com.atlas.userservice.repository.UserEntityRepository;
import com.atlas.userservice.service.UserService;
import com.atlas.userservice.service.mapper.UserMapper;

/*
 *Implementation of User Service  
 */
@Service
public class UserServiceImpl implements UserService  { 

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

}
