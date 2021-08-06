package mca.srcr.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mca.srcr.Entidades.Categoria;
import mca.srcr.Repositorios.CategoriaRepositorio;

@RestController
@RequestMapping(path = "/api/srcr")
public class CategoriaControlador {
	
	@Autowired
	CategoriaRepositorio categoriaRepo;
	
	@GetMapping(path = "/categorias")
	public List<Categoria> getCategorias(){
		return categoriaRepo.findAll();
	}

}
