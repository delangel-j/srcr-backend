package mca.srcr.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mca.srcr.Entidades.Platillo;

public interface PlatilloRepositorio extends JpaRepository<Platillo, Integer>{
	
	@Query(value = "SELECT * from platillo where id_restaurante=?1" ,nativeQuery = true)
	List<Platillo> getPlatilloByIdRestaurante(int id);
	

}
