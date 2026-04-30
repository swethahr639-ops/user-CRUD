package com.atlas.userservice.controller;



import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atlas.userservice.service.UserService;
import com.atlas.userservice.service.dto.CommonResponseDTO;
import com.atlas.userservice.service.dto.LoginRequestDTO;
import com.atlas.userservice.service.dto.UserRequestDTO;
import com.atlas.userservice.service.dto.UserResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v0/users")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<CommonResponseDTO<Long>> saveUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
		return new ResponseEntity<>(userService.saveUser(userRequestDTO), HttpStatus.CREATED);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<CommonResponseDTO<UserResponseDTO>> getUser(@PathVariable("userId") Long userId) {
		return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<CommonResponseDTO<List<UserResponseDTO>>> getAllUsers() {
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);

	}

	@PutMapping("/{userId}")
	public ResponseEntity<CommonResponseDTO<UserResponseDTO>> updateUser(@PathVariable("userId") Long userId,
			@RequestBody @Validated UserRequestDTO userRequestDTO) {
		return new ResponseEntity<>(userService.updateUser(userId, userRequestDTO), HttpStatus.OK);

	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<CommonResponseDTO<Void>> deletuser(@PathVariable("userId") Long userId) {
		return new ResponseEntity<>(userService.deletUser(userId), HttpStatus.NO_CONTENT);

	}

	@PostMapping("/login")
	public ResponseEntity<CommonResponseDTO<UserResponseDTO>> login(@RequestBody LoginRequestDTO loginRequestDTO) {
		return new ResponseEntity<>(userService.getUser(loginRequestDTO.getEmailId(), loginRequestDTO.getPassword()),
				HttpStatus.OK);

	}

}
