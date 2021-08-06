package mca.srcr.Controladores;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import mca.srcr.Entidades.Platillo;
import mca.srcr.Repositorios.PlatilloRepositorio;



@RestController
@RequestMapping(path = "/api/srcr")
public class PlatilloControlador {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PlatilloControlador.class);

    @Autowired
    PlatilloRepositorio repoPlatillo;

    @GetMapping("/platillos")
    public List<Platillo> getPlatillos() {
        return repoPlatillo.findAll();
    }
    

    @GetMapping(value = "/platillos", params = {"id_restaurante"})
    public List<Platillo> getPlatilloPorId(@RequestParam(value = "id_restaurante") int id) {
       //Aqui de debe cambiar el repositorio de platillo por un
    	//servicio para comparar las calorias del usuario 
    	//y las calorias del platillo y mostrar solo las requeridas
        return repoPlatillo.getPlatilloByIdRestaurante(id);
    }


    @PostMapping("/platillos/agregar")
    public ResponseEntity<Object> agregarUsuario(@RequestBody @Valid Platillo platillo, Errors errores) {
        try {
            if (errores.hasFieldErrors()) {
                String mensaje = errores.getFieldErrors()
                        .stream()
                        .map(fe -> fe.getField() + " " + fe.getDefaultMessage())
                        .collect(Collectors.joining(","));
                LOGGER.warn(mensaje);
                return ResponseEntity
                        .badRequest()
                        .header("ERROR", mensaje)
                        .build();
            }
            repoPlatillo.save(platillo);

            URI urlNuevoPlatillo = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .build(platillo.getIdPlatillo());
            return ResponseEntity
                    .created(urlNuevoPlatillo)
                    .build();
        } catch (Exception ex) {
            LOGGER.warn("Error al guardar platillo", ex);
            return ResponseEntity
                    .status(505)
                    .header("ERROR", ex.getMessage())
                    .build();
        }
    }

    @PutMapping("/platillos/modificar/{id}")
    public ResponseEntity<Object> modificarPlatillo(
            @PathVariable("id") Integer id,
            @RequestBody Platillo platillo) {
        try {
            platillo.setIdPlatillo(id);
            repoPlatillo.save(platillo);
            return ResponseEntity.
                    ok()
                    .build();
        } catch (Exception ex) {
            return ResponseEntity
                    .status(505)
                    .header("ERROR", ex.getMessage())
                    .build();
        }
    }

    @DeleteMapping("/platillo/eliminar/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable("id") Integer id) {
        try {
            repoPlatillo.deleteById(id);
            return ResponseEntity
                    .ok()
                    .build();
        } catch (Exception ex) {
            return ResponseEntity
                    .status(500)
                    .header("ERROR", ex.getMessage())
                    .build();
        }
    }
	

}
