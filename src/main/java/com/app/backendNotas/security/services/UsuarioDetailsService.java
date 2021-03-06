package com.app.backendNotas.security.services;

import java.util.Map;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Map<String, String> usuarios = Map.of("usuario", "USER", "administrador", "ADMIN"); //Si tuviera una base de datos la traería de ahí
		
		var rol = usuarios.get(username);
		
		if (rol != null) {
			User.UserBuilder userBuilder = User.withUsername(username);
			// "secreto" => [BCrypt] => $2a$10$56VCAiApLO8NQYeOPiu2De/EBC5RWrTZvLl7uoeC3r7iXinRR1iiq
			// "Usuario01" => [BCrypt] => $2a$10$PPRj2W3m3mkGwHQQ.V5nL.gbDcmHGUulVemn/DwqcqevgDpoZmgVa
			String encryptedPassword = "$2a$10$PPRj2W3m3mkGwHQQ.V5nL.gbDcmHGUulVemn/DwqcqevgDpoZmgVa";
			userBuilder.password(encryptedPassword).roles(rol);
			return userBuilder.build();
		} else {
			throw new UsernameNotFoundException(username);
		}
	}
	
	

}
