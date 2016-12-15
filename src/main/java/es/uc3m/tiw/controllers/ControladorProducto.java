package es.uc3m.tiw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.dominios.Producto;
import es.uc3m.tiw.dominios.Usuario;


@SessionAttributes({"producto", "error"})
@Controller
public class ControladorProducto {

	@Autowired
	RestTemplate restTemplate;
	
		
	@RequestMapping(value="wallapoptiw/MisProductos")
	public String redirigirMisProductos(Model model, @ModelAttribute Usuario usuario){
		model.addAttribute("usuario", usuario);
		return "MisProductos"; 
	}
	
	
	/*Añadir producto*/
	@RequestMapping (value="wallapoptiw/anyadir_producto", method = RequestMethod.POST)
	public String anyadirProducto(Model model, Usuario usuario, @RequestParam("NombreProducto") String nombre,@RequestParam("Categoria") String categoria, @RequestParam("Descripcion") String descripcion,  @RequestParam("Precio") double precio,  @RequestParam("Estado") String estado ){

		
			Producto producto = new Producto();
			producto.setTitulo(nombre);
			producto.setCategoria(categoria);
			producto.setDescripcion(descripcion);
			producto.setPrecio(precio);	
			producto.setEstado(estado);
			producto.setUsuario(usuario);
			Producto p = restTemplate.postForObject("http://localhost:8020/anyadir_producto", producto, Producto.class);
			model.addAttribute("producto", p);
			//Se ha añadido el producto
			model.addAttribute("error", "Se ha añadido el producto correctamente.");
			return "PaginaPrincipal";
		
	}
	
	
	@RequestMapping(value="wallapoptiw/MiProductoEditar", method = RequestMethod.POST)
	public String devolverProductoEditar(Model model, @ModelAttribute Producto producto, @ModelAttribute Usuario usuario){
		model.addAttribute("producto", producto);
		model.addAttribute("usuario",usuario);
		return "MisProductos"; 
	}
	
	@RequestMapping(value="wallapoptiw/MiProductoEditar2", method = RequestMethod.POST)
	public String ProductoEditar(Model model, @ModelAttribute Usuario usuario,@ModelAttribute Producto prod, @RequestParam("NombreProducto") String nombre,@RequestParam("Categoria") String categoria, @RequestParam("Descripcion") String descripcion,  @RequestParam("Precio") double precio,  @RequestParam("Estado") String estado ){

		if(nombre.equals("")||categoria.equals("")||descripcion.equals("")||estado.equals("")){
			model.addAttribute("producto",prod);			
			model.addAttribute("error", "Existen campos vacíos. Rellene todos, por favor.");
			return "MisProductos"; 		}
		else{
		Producto producto = new Producto();
		producto.setTitulo(nombre);
		producto.setCategoria(categoria);
		producto.setDescripcion(descripcion);
		producto.setPrecio(precio);	
		producto.setEstado(estado);
		producto.setUsuario(usuario);
		restTemplate.postForObject("http://localhost:8020/modificar_producto", producto, Producto.class);
		model.addAttribute("producto",producto);
		model.addAttribute("usuario",usuario);
		model.addAttribute("error", "Los datos se han modificado correctamente.");
		return "MisProductos"; 			
		}
	}
	
	@RequestMapping(value="wallapoptiw/EliminarProducto", method = RequestMethod.POST)
	public String EliminarProducto(Model model, @ModelAttribute Usuario usuario,@ModelAttribute Producto producto){
		restTemplate.postForObject("http://localhost:8020/eliminar_producto", producto, Producto.class);
		model.addAttribute("producto",null);
		model.addAttribute("usuario",usuario);
		model.addAttribute("error", null);
		return "MisProductos"; 
	}
}
