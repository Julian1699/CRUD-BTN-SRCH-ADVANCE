package com.gestion.productos.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestion.productos.entidades.Producto;
import com.gestion.productos.repositorio.ProductoRepositorio;

@Service
public class ProductoServicio {
	
	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	public List<Producto> listAll(String palabraClave){
	    if(palabraClave != null) {
			
	    	return productoRepositorio.findAll(palabraClave);
	    }
	    	
		return productoRepositorio.findAll();
	}
	
	public void save(Producto producto) {
		productoRepositorio.save(producto);
	}
	
	public Producto get(Long id) {
		return productoRepositorio.findById(id).get();
	}
	
	public void delete(Long id) {
		productoRepositorio.deleteById(id);
	}
}
