package es.uc3m.tiw.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.dominios.Producto;
import es.uc3m.tiw.dominios.Usuario;


@SessionAttributes({"producto", "lista_productos","error", "usuario", "productos_usuario"})
@Controller
public class ControladorProducto {

	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value="wallapoptiw/PPrincipal")
	public String redirigirPPrincipal(Model model, @ModelAttribute Usuario usuario){
		List<Producto> p = restTemplate.postForObject("http://localhost:8020/listar_productos", null, List.class);
		model.addAttribute("lista_productos", p);
		return "PaginaPrincipal"; 
	}
	
	@RequestMapping(value="wallapoptiw/Producto" , method = RequestMethod.GET)
	public String redirigirProducto(Model model, @ModelAttribute Usuario usuario, @RequestParam("idProducto") int idProducto){
		Producto p = restTemplate.postForObject("http://localhost:8020/buscar_id", idProducto, Producto.class);
		model.addAttribute("producto", p);
		return "Producto"; 
	}
	
	@RequestMapping(value="wallapoptiw/MisProductos")
	public String redirigirMisProductos(Model model, @ModelAttribute Usuario usuario){
		model.addAttribute("productos_usuario", "");
		List<Producto> p = null;
		p = restTemplate.postForObject("http://localhost:8020/productos_usuario", usuario, List.class);
		int bea = usuario.getId();
		model.addAttribute("productos_usuario", p);
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
			producto.setUsuario(usuario.getId());
			Producto p = restTemplate.postForObject("http://localhost:8020/anyadir_producto", producto, Producto.class);
			//Se ha añadido el producto
			model.addAttribute("error", "Se ha añadido el producto correctamente.");
			return "redirect:/wallapoptiw/MisProductos";
		
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
		producto.setUsuario(usuario.getId());
		restTemplate.postForObject("http://localhost:8020/modificar_producto", producto, Producto.class);
		model.addAttribute("producto",producto);
		model.addAttribute("usuario",usuario);
		model.addAttribute("error", "Los datos se han modificado correctamente.");
		return "MisProductos"; 			
		}
	}
	
	@RequestMapping(value="wallapoptiw/EliminarProducto", method = RequestMethod.GET)
	public String EliminarProducto(Model model, @ModelAttribute Usuario usuario, @RequestParam("idProducto") int idProducto){
		int bea = usuario.getId();
		restTemplate.postForObject("http://localhost:8020/eliminar_producto", idProducto, Producto.class);
		int bea2 = usuario.getId();
		return "redirect:/wallapoptiw/MisProductos";
	}
	
	@RequestMapping(value="wallapoptiw/buscar", method = RequestMethod.POST)
	public String devolverBusqueda(Model model, @ModelAttribute Usuario usuario, @RequestParam("busqueda") String busqueda){
		model.addAttribute("error", "");
		List<Producto> p = null;
		p = restTemplate.postForObject("http://localhost:8020/productos_titulo", busqueda, List.class);
		return "redirect:/wallapoptiw/PPrincipal"; 
	}
	
}
