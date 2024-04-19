package com.capgemini.movieticketbooking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.capgemini.movieticketbooking.model.User;
import com.capgemini.movieticketbooking.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("inside loadbyusername");
		
		User user = userRepo.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User with name=" +username+" not found."));
		
		if(user == null)
			throw new UsernameNotFoundException(username);
		
		return new CustomUserDetails(user);
	}

}
