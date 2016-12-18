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

@SessionAttributes({"listaUsuarios", "error", "producto", "usuario"})
@Controller
public class ControladorAdmin {

	@Autowired
	RestTemplate restTemplate;	
	
	@RequestMapping(value="wallapoptiw/PaginaPrincipalAdmin")
	public String devolverPaginaPrincipalAdmin(Model model){
		List<Usuario> listaUsuarios = restTemplate.postForObject("http://localhost:8010/listar_usuarios", null, List.class);
		model.addAttribute("listaUsuarios", listaUsuarios);
		return "PaginaPrincipal_admin"; 
	}
	
	@RequestMapping(value="wallapoptiw/EliminarUsuarioAdmin", method = RequestMethod.GET)
	public String EliminarUsuarioAdmin(Model model, @RequestParam("id") int id){
		Usuario usuario = new Usuario();
		usuario.setApellidos("aa");
		usuario.setCiudad("bbb");
		usuario.setMail("aa@aa.com");
		usuario.setNombre("ccc");
		usuario.setPassword("123");
		usuario.setId(id);
		Usuario u = restTemplate.postForObject("http://localhost:8010/buscar_id", usuario, Usuario.class);
		restTemplate.postForObject("http://localhost:8010/eliminar_usuario", u, Usuario.class);
		return "redirect:/wallapoptiw/PaginaPrincipalAdmin"; 
	}
	//Coger el Producto de la vista
	@RequestMapping(value="wallapoptiw/EditarUsuarioAdmin", method = RequestMethod.GET)
	public String redirigirEditarUsuario(Model model, @RequestParam("id") int id){
		Usuario usuario = new Usuario();
		usuario.setApellidos("aa");
		usuario.setCiudad("bbb");
		usuario.setMail("aa@aa.com");
		usuario.setNombre("ccc");
		usuario.setPassword("123");
		usuario.setId(id);
		Usuario u = restTemplate.postForObject("http://localhost:8010/buscar_id", usuario, Usuario.class);
		model.addAttribute("usuario", u);
		return "redirect:/wallapoptiw/UEditarAdmin"; 
	}
	
	//Ir a editarProducto
	@RequestMapping(value="wallapoptiw/UEditarAdmin")
	public String redirigirUEditar(Model model, @ModelAttribute Usuario usuario){
		return "EditarUsuarioAdmin"; 
	}
	
	@RequestMapping(value="wallapoptiw/UsuarioEditarAdmin", method = RequestMethod.POST)
	public String UsuarioEditar(Model model, @ModelAttribute Usuario usuario, @RequestParam("Nombre") String nombre, @RequestParam("Apellidos") String apellidos, @RequestParam("Mail") String mail,  @RequestParam("Password") String password,  @RequestParam("Ciudad") String ciudad){
		model.addAttribute("error", "");
		if(nombre.equals("")||apellidos.equals("")||mail.equals("")||password.equals("")||ciudad.equals("")){
			model.addAttribute("error", "Existen campos vacíos. Rellene todos, por favor.");
			return "redirect:/wallapoptiw/UsuarioAdmin"; 		
		}
		else{
		Usuario u = new Usuario();
		u.setApellidos(apellidos);
		u.setCiudad(ciudad);
		u.setMail(mail);
		u.setNombre(nombre);
		u.setPassword(password);
		u.setId(usuario.getId());
		restTemplate.postForObject("http://localhost:8010//modificar_usuario", u, Usuario.class);
		model.addAttribute("error", "Los datos se han modificado correctamente.");
		return "redirect:/wallapoptiw/PaginaPrincipalAdmin"; 
		}
	}
		
	//Producto
	@RequestMapping(value="wallapoptiw/ProductoAdmin")
	public String devolverProductoAdmin(Model model){
		List<Producto> listaProductos = restTemplate.postForObject("http://localhost:8020/listar_productos", null, List.class);
		model.addAttribute("listaProductos", listaProductos);
		return "Producto_admin"; 
	}
	
	@RequestMapping(value="wallapoptiw/EliminarProductoAdmin", method = RequestMethod.GET)
	public String EliminarProducto(Model model, @ModelAttribute Usuario usuario, @RequestParam("idProducto") int idProducto){
		restTemplate.postForObject("http://localhost:8020/eliminar_producto", idProducto, Producto.class);
		return "redirect:/wallapoptiw/ProductoAdmin";
	}
	
	//Coger el Producto de la vista
	@RequestMapping(value="wallapoptiw/EditarProductoAdmin", method = RequestMethod.GET)
	public String redirigirEditarProducto(Model model, @RequestParam("idProducto") int idProducto){
		Producto p = restTemplate.postForObject("http://localhost:8020/buscar_id", idProducto, Producto.class);
		model.addAttribute("producto", p);
		return "redirect:/wallapoptiw/PEditarAdmin"; 
	}
	
	//Ir a editarProducto
	@RequestMapping(value="wallapoptiw/PEditarAdmin")
	public String redirigirPEditar(Model model, @ModelAttribute Producto producto){
		return "EditarProductoAdmin"; 
	}
	
	@RequestMapping(value="wallapoptiw/MiProductoEditarAdmin", method = RequestMethod.POST)
	public String ProductoEditar(Model model, @ModelAttribute Producto producto, @RequestParam("NombreProducto") String nombre,@RequestParam("Categoria") String categoria, @RequestParam("Descripcion") String descripcion,  @RequestParam("Precio") double precio,  @RequestParam("Estado") String estado,  @RequestParam("Usuario") int usuario ){
		model.addAttribute("error", "");
		if(nombre.equals("")||categoria.equals("")||descripcion.equals("")||estado.equals("")){
			model.addAttribute("error", "Existen campos vacíos. Rellene todos, por favor.");
			return "redirect:/wallapoptiw/ProductoAdmin"; 		
		}
		else{
		Producto p = new Producto();
		p.setIdProducto(producto.getIdProducto());
		p.setTitulo(nombre);
		p.setCategoria(categoria);
		p.setDescripcion(descripcion);
		p.setPrecio(precio);	
		p.setEstado(estado);
		p.setUsuario(usuario);
		restTemplate.postForObject("http://localhost:8020/modificar_producto", p, Producto.class);
		model.addAttribute("error", "Los datos se han modificado correctamente.");
		return "redirect:/wallapoptiw/ProductoAdmin"; 			
		}
	}
	
}
