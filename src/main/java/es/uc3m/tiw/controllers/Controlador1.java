package es.uc3m.tiw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Controlador1 {
	
	
	
	/*Para ver la estructura*/
	/*@RequestMapping(value="vista/{parametro}")
	public String devolverVista(Model modelo, @PathVariable String parametro){
		modelo.addAttribute("nombre_var", parametro); 
		return "pagina"; 
	}*/
	
	@RequestMapping(value="wallapoptiw/")
	public String devolverIndex(Model modelo){
		modelo.addAttribute("nombre_var", ""); 
		return "Index"; 
	}
	
	@RequestMapping(value="wallapoptiw/{MiPerfilContrasenya}")
	public String devolverMiPerfilContrasenya(Model modelo, @PathVariable String MiPerfilContrasenya){
		modelo.addAttribute("nombre_var", MiPerfilContrasenya); 
		return "MiPerfil-contrasenya"; 
	}
	
	@RequestMapping(value="wallapoptiw/{MiPerfilEditar}")
	public String devolverMiPerfilEditar(Model modelo, @PathVariable String MiPerfilEditar){
		modelo.addAttribute("nombre_var", MiPerfilEditar); 
		return "MiPerfil-editar"; 
	}
	
	@RequestMapping(value="wallapoptiw/{MisProductos}")
	public String devolverMisProductos(Model modelo, @PathVariable String MisProductos){
		modelo.addAttribute("nombre_var", MisProductos); 
		return "MisProductos"; 
	}
	
	@RequestMapping(value="wallapoptiw/{PaginaPrincipalAdmin}")
	public String devolverPaginaPrincipalAdmin(Model modelo, @PathVariable String PaginaPrincipalAdmin){
		modelo.addAttribute("nombre_var", PaginaPrincipalAdmin); 
		return "PaginaPrincipal_admin"; 
	}
	
	@RequestMapping(value="wallapoptiw/{PaginaPrincipal}")
	public String devolverPaginaPrincipal(Model modelo, @PathVariable String PaginaPrincipal){
		modelo.addAttribute("nombre_var", PaginaPrincipal); 
		return "PaginaPrincipal"; 
	}
	
	@RequestMapping(value="wallapoptiw/{ProductoAdmin}")
	public String devolverProductoAdmin(Model modelo, @PathVariable String ProductoAdmin){
		modelo.addAttribute("nombre_var", ProductoAdmin); 
		return "Producto_admin"; 
	}
	
	@RequestMapping(value="wallapoptiw/{Producto}")
	public String devolverProducto(Model modelo, @PathVariable String Producto){
		modelo.addAttribute("nombre_var", Producto); 
		return "Producto"; 
	}
	
	
	
}
