package es.uc3m.tiw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.dominios.Usuario;

@Controller
public class ControladorProducto {

	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value="wallapoptiw/PPrincipal")
	public String devolverPaginaPrincipal(Model modelo){
		return "PaginaPrincipal"; 
	}
	

	@RequestMapping(value="wallapoptiw/MisProductos")
	public String devolverMisProductos(Model modelo, @ModelAttribute Usuario usuario){
		return "MisProductos"; 
	}
	
	
	@RequestMapping(value="wallapoptiw/{Producto}")
	public String devolverProducto(Model modelo, @PathVariable String Producto){
		modelo.addAttribute("nombre_var", Producto); 
		return "Producto"; 
	}
}
