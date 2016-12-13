package es.uc3m.tiw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorProducto {

	
	@RequestMapping(value="wallapoptiw/{PaginaPrincipal}")
	public String devolverPaginaPrincipal(Model modelo, @PathVariable String PaginaPrincipal){
		modelo.addAttribute("nombre_var", PaginaPrincipal); 
		return "PaginaPrincipal"; 
	}
	
	
	@RequestMapping(value="wallapoptiw/{MisProductos}")
	public String devolverMisProductos(Model modelo, @PathVariable String MisProductos){
		modelo.addAttribute("nombre_var", MisProductos); 
		return "MisProductos"; 
	}
	
	
	@RequestMapping(value="wallapoptiw/{Producto}")
	public String devolverProducto(Model modelo, @PathVariable String Producto){
		modelo.addAttribute("nombre_var", Producto); 
		return "Producto"; 
	}
}
