package es.uc3m.tiw.controllers;

import java.util.List;

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


@SessionAttributes({"producto", "lista_productos","error", "usuario", "productos_usuario"})
@Controller
public class ControladorProducto {

	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value="wallapoptiw/PPrincipal")
	public String redirigirPPrincipal(Model model, @ModelAttribute Usuario usuario, @ModelAttribute String error){
		List<Producto> p = restTemplate.postForObject("http://localhost:8020/listar_productos", null, List.class);
		model.addAttribute("lista_productos", p);
		return "PaginaPrincipal"; 
	}

	
	//Cuando pinchemos en el logo
	@RequestMapping(value="wallapoptiw/PPrincipal2")
	public String redirigirPPrincipal2(Model model, @ModelAttribute Usuario usuario, @ModelAttribute String error){
		model.addAttribute("error", "");
		List<Producto> p = restTemplate.postForObject("http://localhost:8020/listar_productos", null, List.class);
		model.addAttribute("lista_productos", p);
		return "PaginaPrincipal"; 
	}
	@RequestMapping(value="wallapoptiw/Producto" , method = RequestMethod.GET)
	public String redirigirProducto(Model model, @ModelAttribute Usuario usuario, @RequestParam("idProducto") int idProducto){
		model.addAttribute("error", "");
		Producto p = restTemplate.postForObject("http://localhost:8020/buscar_id", idProducto, Producto.class);
		model.addAttribute("producto", p);
		return "Producto"; 
	}
	
	@RequestMapping(value="wallapoptiw/MisProductos")
	public String redirigirMisProductos(Model model, @ModelAttribute Usuario usuario, @ModelAttribute String error){
		model.addAttribute("productos_usuario", "");
		List<Producto> p = null;
		p = restTemplate.postForObject("http://localhost:8020/productos_usuario", usuario, List.class);
		model.addAttribute("productos_usuario", p);
		return "MisProductos"; 
	}
	//Cuando pinchemos en el icono
	@RequestMapping(value="wallapoptiw/MisProductos2")
	public String redirigirMisProductos2(Model model, @ModelAttribute Usuario usuario, @ModelAttribute String error){
		model.addAttribute("productos_usuario", "");
		List<Producto> p = null;
		p = restTemplate.postForObject("http://localhost:8020/productos_usuario", usuario, List.class);
		model.addAttribute("productos_usuario", p);
		return "MisProductos"; 
	}

	
	/*Añadir producto*/
	@RequestMapping (value="wallapoptiw/anyadir_producto", method = RequestMethod.POST)
	public String anyadirProducto(Model model, Usuario usuario, @RequestParam("NombreProducto") String nombre,@RequestParam("Categoria") String categoria, @RequestParam("Descripcion") String descripcion,  @RequestParam("Precio") double precio,  @RequestParam("Estado") String estado ){
			model.addAttribute("error", "");
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
	
	
	@RequestMapping(value="wallapoptiw/MiProductoEditar2", method = RequestMethod.POST)
	public String ProductoEditar(Model model, @ModelAttribute Usuario usuario, @ModelAttribute Producto producto, @RequestParam("NombreProducto") String nombre,@RequestParam("Categoria") String categoria, @RequestParam("Descripcion") String descripcion,  @RequestParam("Precio") double precio,  @RequestParam("Estado") String estado ){
		model.addAttribute("error", "");
		if(nombre.equals("")||categoria.equals("")||descripcion.equals("")||estado.equals("")){
			model.addAttribute("error", "Existen campos vacíos. Rellene todos, por favor.");
			return "MisProductos"; 		}
		else{
		Producto p = new Producto();
		
		p.setIdProducto(producto.getIdProducto());
		p.setTitulo(nombre);
		p.setCategoria(categoria);
		p.setDescripcion(descripcion);
		p.setPrecio(precio);	
		p.setEstado(estado);
		p.setUsuario(usuario.getId());
		restTemplate.postForObject("http://localhost:8020/modificar_producto", p, Producto.class);
		model.addAttribute("error", "Los datos se han modificado correctamente.");
		return "redirect:/wallapoptiw/MisProductos"; 			
		}
	}
	
	@RequestMapping(value="wallapoptiw/EliminarProducto", method = RequestMethod.GET)
	public String EliminarProducto(Model model, @ModelAttribute Usuario usuario, @RequestParam("idProducto") int idProducto){
		model.addAttribute("error", "");
		restTemplate.postForObject("http://localhost:8020/eliminar_producto", idProducto, Producto.class);
		return "redirect:/wallapoptiw/MisProductos";
	}
	
	
	
	//Coger el Producto de la vista
	@RequestMapping(value="wallapoptiw/EditarProducto", method = RequestMethod.GET)
	public String redirigirEditarProducto(Model model, @ModelAttribute Usuario usuario, @RequestParam("idProducto") int idProducto){
		Producto p = restTemplate.postForObject("http://localhost:8020/buscar_id", idProducto, Producto.class);
		model.addAttribute("producto", p);
		return "redirect:/wallapoptiw/PEditar"; 
	}
	
	//Ir a editarProducto
	@RequestMapping(value="wallapoptiw/PEditar")
	public String redirigirPEditar(Model model, @ModelAttribute Usuario usuario, @ModelAttribute Producto producto){
		return "EditarProducto"; 
	}	
	
	
}
