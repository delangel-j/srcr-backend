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

import mca.srcr.Entidades.Sucursal;
import mca.srcr.Repositorios.SucursalRepositorio;

@RestController
@RequestMapping(path = "/api/srcr")
public class SucursalControlador {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SucursalControlador.class);

    @Autowired
    SucursalRepositorio repoSucursal;

    @GetMapping("/sucursales")
    public List<Sucursal> getSucursales() {
        return repoSucursal.findAll();
    }


    @PostMapping("/sucursal/agregar")
    public ResponseEntity<Object> agregarSucursal(@RequestBody @Valid Sucursal sucursal, Errors errores) {
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
            repoSucursal.save(sucursal);

            URI urlNuevaSucursal = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .build(sucursal.getIdSucursal());
            return ResponseEntity
                    .created(urlNuevaSucursal)
                    .build();
        } catch (Exception ex) {
            LOGGER.warn("Error al guardar sucursal", ex);
            return ResponseEntity
                    .status(505)
                    .header("ERROR", ex.getMessage())
                    .build();
        }
    }

    @PutMapping("/sucursales/modificar/{id}")
    public ResponseEntity<Object> modificarSucursal(
            @PathVariable("id") Integer id,
            @RequestBody Sucursal sucursal) {
        try {
            sucursal.setIdSucursal(id);
            repoSucursal.save(sucursal);
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

    @DeleteMapping("/sucursales/eliminar/{id}")
    public ResponseEntity eliminarSucursal(@PathVariable("id") Integer id) {
        try {
            repoSucursal.deleteById(id);
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
