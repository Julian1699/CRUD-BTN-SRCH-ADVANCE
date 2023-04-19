package com.gestion.productos.controlador;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gestion.productos.entidades.Producto;
import com.gestion.productos.servicio.ProductoServicio;

@Controller
public class ProductoControlador {
	@Autowired
	private ProductoServicio productoServicio;

	@RequestMapping("/")
	public String verPaginaDeInicio(Model modelo,@Param("palabraClave") String palabraClave){
		List<Producto> listaProductos = productoServicio.listAll(palabraClave);
		modelo.addAttribute("listaProductos", listaProductos);
		modelo.addAttribute("palabraClave", palabraClave);
		return "index";
	}

	@GetMapping("/nuevo")
	public String mostrarFormularioDeRegistrarProducto(Model modelo) {
		Producto producto = new Producto();
		modelo.addAttribute("producto",producto);
		return "nuevo_producto";
	}
	
	@PostMapping(value = "/guardar")
	public String guardarProducto(@ModelAttribute("producto") Producto producto) {
		productoServicio.save(producto);
		return "redirect:/";
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView mostrarFormularioDeEditarProducto(@PathVariable (name= "id")Long id){
		ModelAndView modelo = new ModelAndView("editar_producto");
		Producto producto = productoServicio.get(id);
		modelo.addObject("producto", producto);
		return modelo;
	}

	@RequestMapping("/eliminar/{id}")
	public String eliminarProducto(@PathVariable(name="id")Long id) {
		productoServicio.delete(id);
		return "redirect:/";
	}

	@GetMapping("/ver/{id}")
	public String verDetallesDelProducto(@PathVariable(value = "id") Long id,Map<String,Object> modelo,RedirectAttributes flash) {
		Producto producto = productoServicio.get(id);
		if(producto == null) {
			flash.addFlashAttribute("error", "El producto no existe en la base de datos");
			return "redirect:/index";
		}
		
		modelo.put("producto",producto);
		modelo.put("titulo", "Detalles del producto " + producto.getNombre());
		return "ver";
	}
}
