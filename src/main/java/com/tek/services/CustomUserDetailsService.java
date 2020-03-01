package com.tek.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tek.models.Register;
import com.tek.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public CustomUserDetailsService() {
		System.out.println(">>>>>>>>>>> CustomUserDetailsService constructor >>>>>>>");
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println(">>>>>>>>>>> method - CustomUserDetailsService loadUserByUsername() >>>>>>> " + userName);
		Register user = userRepository.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + userName);
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());
	}
	
	public Register saveUser(Register entity) {
		System.out.println(">>>>>>>>>> method - CustomUserDetailsService saveUser() >>>>>>>>>>");
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		Register savedUser = userRepository.save(entity);
		return savedUser;
	}

}
