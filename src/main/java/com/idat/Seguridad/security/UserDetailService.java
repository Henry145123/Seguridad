package com.idat.Seguridad.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//comparar profesor de la base de datos con el username del token
		if("profesor".equals(username)) {
			List<GrantedAuthority> listGranted = new ArrayList<>();
			GrantedAuthority grantes = new SimpleGrantedAuthority("QUERIDO_PROFESOR");
			listGranted.add(grantes);
			//usuario - contraseña - rol
			return new User("profesor", new BCryptPasswordEncoder().encode("123456"),listGranted);
		}else {
			throw new UsernameNotFoundException("Usuario no existe "+username);
		}
	}

}
