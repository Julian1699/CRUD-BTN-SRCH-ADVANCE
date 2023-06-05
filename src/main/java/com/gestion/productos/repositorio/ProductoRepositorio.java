package com.gestion.productos.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion.productos.entidades.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Long>{
		//Este es el deber ser
		@Query("SELECT p FROM Producto p WHERE" 
				+ " CONCAT(p.id,p.nombre,p.marca,p.hechoEn,p.precio)"
				+ " LIKE %?1%")
		public List<Producto> findAll(String palabraClave);
}
