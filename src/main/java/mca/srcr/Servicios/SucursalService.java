package mca.srcr.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mca.srcr.Entidades.Sucursal;
import mca.srcr.Repositorios.SucursalRepositorio;

@Service
public class SucursalService {
	
	@Autowired
	SucursalRepositorio sucursalRepo;
	
	public List<Sucursal> getSucursalesPorUbicacion(double latitud, double longitud){
		System.out.println("Entrando al servicio: " + latitud);
		System.out.println("Entrando al servicio: " + longitud);
		List<Sucursal> sucursales = new ArrayList<Sucursal>();
		List<Sucursal> sucursalCerca = new ArrayList<Sucursal>();
		sucursales.addAll(sucursalRepo.findAll());
		for(Sucursal suc:sucursales) {
			double radioTierra = 6371;//en km
			double dLat = Math.toRadians(suc.getLatitud() - latitud );
			double dLng = Math.toRadians(suc.getLongitud() - longitud);
			double sindLat = Math.sin(dLat/2);
			double sindLng = Math.sin(dLng/2);
			double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
						* Math.cos(Math.toRadians(latitud)) * Math.cos(Math.toRadians(suc.getLatitud()));
			double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
			double distancia = radioTierra * va2;
			if(distancia <= 5)
				sucursalCerca.add(suc);
			System.out.println(distancia);
 		}
		return sucursalCerca;
		
	}

}
