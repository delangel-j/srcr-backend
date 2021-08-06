package mca.srcr.Controladores;

import java.net.URI;
import java.util.Date;
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

import mca.srcr.Entidades.Cliente;
import mca.srcr.Entidades.Sucursal;
import mca.srcr.Repositorios.ClienteRepositorio;


@RestController
@RequestMapping(path = "/api/srcr")
public class ClienteControlador {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteControlador.class);

    @Autowired
    ClienteRepositorio repoClientes;




    @PostMapping("/clientes/agregar")
    public ResponseEntity<Object> agregarCliente(@RequestBody @Valid Cliente cliente, Errors errores) {
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
            //cliente.setContrasena(bCrypt.encode(cliente.getContrasena()));
            repoClientes.save(cliente);

            URI urlNuevoCliente = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .build(cliente.getCorreo());
            return ResponseEntity
                    .created(urlNuevoCliente)
                    .build();
        } catch (Exception ex) {
            LOGGER.warn("Error al guardar usuario", ex);
            return ResponseEntity
                    .status(505)
                    .header("ERROR", ex.getMessage())
                    .build();
        }
    }

    @PutMapping("/cliente/modificar/{correo}")
    public ResponseEntity<Object> modificarCliente(
            @PathVariable("correo") String correo,
            @RequestBody Cliente cliente) {
        try {
            cliente.setCorreo(correo);
            repoClientes.save(cliente);
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
    
    @GetMapping(value = "/login" , params = {"correo", "contrasena"})
    public Cliente getClientePorCorreoYContrasena(@RequestParam( value = "correo") String cadena
    									,@RequestParam(value = "contrasena") String cadena2 ) {
        return repoClientes.findByCorreoAndContrasena(cadena,cadena2);
    }
    

    @DeleteMapping("/clientes/eliminar/{correo}")
    public ResponseEntity eliminarCliente(@PathVariable("cliente") String correo) {
        try {
            repoClientes.deleteById(correo);
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
