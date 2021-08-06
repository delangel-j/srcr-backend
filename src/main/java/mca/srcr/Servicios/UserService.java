/*package mca.srcr.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mca.srcr.Security.SecurityConfig;
import mca.srcr.Entidades.Cliente;
import mca.srcr.Repositorios.ClienteRepositorio;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private ClienteRepositorio usuariosRepositorio;
	
	private BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
	
	SecurityConfig sc;
	
	@Override
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
		Cliente us = new Cliente();  
			us = 	usuariosRepositorio.findByCorreo(correo);
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("ADMIN"));
		//roles.add(new SimpleGrantedAuthority(us.getRolUsuario().getNombreRol()));
		System.out.println(us.getCorreo());
		UserDetails userDetail = new User(us.getCorreo(), us.getContrasena() , roles);
		return userDetail;
	}
}*/
