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
		
		//Este funcionó pero no es el deber ser
		/*@Query("SELECT p FROM Producto p")
		public List<Producto> findAll();
		*/
		//Este no funciona
		/*
		@Query("SELECT * FROM p WHERE p.nombre LIKE '%O%';")
		public List<Producto> findAll();
		*/
		/*
		@Query("SELECT c FROM Producto where p1_0.id")
		public List<Producto> findAll();
		*/

}
