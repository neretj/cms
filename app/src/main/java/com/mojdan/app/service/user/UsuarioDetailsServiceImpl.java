package com.mojdan.app.service.user;

import static java.util.Collections.emptyList;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mojdan.app.model.user.UserRepository;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository usuarioRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<com.mojdan.app.model.user.User> user = usuarioRepository.findByUsername(username);
		
		if(user.isPresent()) {
			return new User(user.get().getName(), user.get().getPassword(), emptyList());
		} else {
			throw new UsernameNotFoundException(username);
		}	
	}
}