package mca.srcr.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import mca.srcr.Entidades.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, String> {

	Cliente findByCorreoAndContrasena(String correo, String contrasena);

}
