package com.mojdan.app.service.user;

import static java.util.Collections.emptyList;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mojdan.app.model.user.Customer;
import com.mojdan.app.model.user.Seller;
import com.mojdan.app.model.user.UserRepository;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository usuarioRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<com.mojdan.app.model.user.User> user = usuarioRepository.findByUsername(username);
		
		com.mojdan.app.model.user.User usuario = user.get();		
		if (usuario instanceof Customer) {
			System.out.println("customer");
		} else if (usuario instanceof Seller) {
			System.out.println("seller");
		}
		System.out.println("none");
		if(user.isPresent()) {
			return new User(usuario.getName(), usuario.getPassword(), emptyList());
		} else {
			throw new UsernameNotFoundException(username);
		}	
	}
}