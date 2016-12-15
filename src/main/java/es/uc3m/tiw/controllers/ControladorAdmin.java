package es.uc3m.tiw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorAdmin {

	
	
	@RequestMapping(value="wallapoptiw/PaginaPrincipalAdmin")
	public String devolverPaginaPrincipalAdmin(Model modelo, @PathVariable String PaginaPrincipalAdmin){
		modelo.addAttribute("nombre_var", PaginaPrincipalAdmin); 
		return "PaginaPrincipal_admin"; 
	}
	
	
	
	@RequestMapping(value="wallapoptiw/ProductoAdmin")
	public String devolverProductoAdmin(Model modelo, @PathVariable String ProductoAdmin){
		modelo.addAttribute("nombre_var", ProductoAdmin); 
		return "Producto_admin"; 
	}
	
}
