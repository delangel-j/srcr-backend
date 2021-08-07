package mca.srcr.Servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mca.srcr.Entidades.Platillo;
import mca.srcr.Entidades.Cliente;
import mca.srcr.Entidades.Preferencia;
import mca.srcr.Repositorios.PlatilloRepositorio;
import mca.srcr.Repositorios.ClienteRepositorio;
import mca.srcr.Repositorios.PreferenciaRepositorio;

@Service
public class PlatilloService {
	
	@Autowired
	PlatilloRepositorio platilloRepo;
	@Autowired
	ClienteRepositorio clienteRepo;
	@Autowired
	PreferenciaRepositorio prefRepo;
	
	public List<Platillo> getPlatillosRecomendados(int id_rest,String id_usuario){
		System.out.println("Entrando al servicio... ");
		
		List<Platillo> platillosRecomendados = new ArrayList<Platillo>();
		
		List<Platillo> platillos = new ArrayList<Platillo>();
		List<Cliente> clientes = new ArrayList<Cliente>();
		List<Preferencia> preferencias = new ArrayList<Preferencia>();
		//Obtener todos los platillos
		platillos.addAll(platilloRepo.findAll());
		//Obtener el cliente por su correo
		clientes.add(clienteRepo.findByCorreo(id_usuario));
		//Obtener preferencias del usuario
		preferencias.addAll(prefRepo.findAll());
		//Total de calorias
		int caloriasTotales = 0;	
		int index_caloria = 0;

		
		Calendar now = Calendar.getInstance();
		int hora = now.get(Calendar.HOUR_OF_DAY);
		
		if(hora >11 && hora < 19) {
			
			index_caloria = 1;
		}
		
		if(hora >=19) {
			
			index_caloria = 2;
		}
		
		if(hora <=11) {
			
			index_caloria = 0;
		}
		
		
		System.out.println("Clientes en 0: "+clientes.get(0).getCorreo());
			//Si el correo que recibimos es igual al encontrado
			if(clientes.get(0).getCorreo().equals(id_usuario)) {
				System.out.println("Asignando calorias...");
				//Asignar calorias segun el momento del día, parametro enviado desde front
				switch (index_caloria){
				case 0: caloriasTotales = clientes.get(0).getCaloriasDesayuno();
					break;
				case 1: caloriasTotales = clientes.get(0).getCaloriasAlmuerzo();
				break;
				case 2: caloriasTotales = clientes.get(0).getCaloriasCena();
				break;
					
					
				}
			}
						
		
		System.out.println("Calorias totales: "+caloriasTotales);
		System.out.println("Usuario: "+id_usuario);
		System.out.println("Index caloria: "+index_caloria);
		
		for(Preferencia pref:preferencias) {
			
			if(pref.getCliente().getCorreo().equals(clientes.get(0).getCorreo())) {
			
			for(Platillo plat:platillos) {
				
				if(plat.getRestaurante().getIdRestaurante()== id_rest) {
				if(pref.getCategoria().getIdCategoria()== plat.getCategoria().getIdCategoria()) {
					
	
					
					if(plat.getCalorias()<= caloriasTotales) {
						
						System.out.println("Un platillo coincide");
						System.out.println(plat.getIdPlatillo());
						System.out.println(plat.getNombre());
						plat.getRestaurante().setImgUrl(null);
						plat.getRestaurante().setContrasenia(null);
						plat.getRestaurante().setUsuario(null);
						plat.getCategoria().setUrl(null);
						platillosRecomendados.add(plat);
					}
					
					
				}
				
			}
			}
			
			}
		}

				
		
		
	
		return platillosRecomendados;
		
	}

}
