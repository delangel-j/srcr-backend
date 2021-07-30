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

import mca.srcr.Entidades.Restaurante;
import mca.srcr.Repositorios.RestauranteRepositorio;

@RestController
@RequestMapping(path = "/api/srcr")
public class RestauranteControlador {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestauranteControlador.class);

    @Autowired
    RestauranteRepositorio repoRestaurante;


    @GetMapping("/restaurantes")
    public List<Restaurante> getRestaurantes() {
        return repoRestaurante.findAll();
    }


    @PostMapping("/restaurante/agregar")
    public ResponseEntity<Object> agregarRestaurante(@RequestBody @Valid Restaurante restaurante, Errors errores) {
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
            repoRestaurante.save(restaurante);

            URI urlNuevoRestaurante = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .build(restaurante.getIdRestaurante());
            return ResponseEntity
                    .created(urlNuevoRestaurante)
                    .build();
        } catch (Exception ex) {
            LOGGER.warn("Error al guardar restaurante", ex);
            return ResponseEntity
                    .status(505)
                    .header("ERROR", ex.getMessage())
                    .build();
        }
    }

    @PutMapping("/restaurante/modificar/{id}")
    public ResponseEntity<Object> modificarRestaurante(
            @PathVariable("id") Integer id,
            @RequestBody Restaurante restaurante) {
        try {
            restaurante.setIdRestaurante(id);
            repoRestaurante.save(restaurante);
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

    @DeleteMapping("/restaurante/eliminar/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable("id") Integer id) {
        try {
            repoRestaurante.deleteById(id);
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
