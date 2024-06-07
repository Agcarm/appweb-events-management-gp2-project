package com.gp2.appwebeventsmanagementgp2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gp2.appwebeventsmanagementgp2.dto.UserDto;
import com.gp2.appwebeventsmanagementgp2.models.User;
import com.gp2.appwebeventsmanagementgp2.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(UserDto userDto) {
    if (userDto.getRole() == null || userDto.getRole().isEmpty()) {
        userDto.setRole("ADMIN"); // Set to admin if not provided
    }
		User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()) , userDto.getRole(), userDto.getFullname());
		return userRepository.save(user);
	}

}
