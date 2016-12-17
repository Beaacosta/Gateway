package es.uc3m.tiw.controllers;


import static org.assertj.core.api.Assertions.useDefaultDateFormatsOnly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import antlr.collections.List;
import es.uc3m.tiw.dominios.Usuario;

@SessionAttributes({"usuario", "error"})
@Controller
public class ControladorUsuario {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value="wallapoptiw/")
	public String devolverIndex(Model modelo){
		modelo.addAttribute("usuario", new Usuario());
		modelo.addAttribute("error", "");
		return "Index"; 
	}

	@RequestMapping(value="wallapoptiw/Index")
	public String redirigirIndex(Model modelo){
		modelo.addAttribute("error", "");
		return "redirect:/wallapoptiw/"; 
	}

	/*para hacer el login*/
	@RequestMapping (value="wallapoptiw/", method = RequestMethod.POST)
	public String iniciarSesion(Model model, @RequestParam("email") String mail, @RequestParam("password") String password ){
		Usuario usuario = new Usuario();
		usuario.setApellidos("aaa");
		usuario.setCiudad("bbb");
		usuario.setNombre("bea");
		if(mail.equals("")||(password.equals(""))){
			model.addAttribute("error", "Existen campos vacíos. Rellene todos los campos, por favor.");
			return "Index";
		}
		else{
			usuario.setMail(mail);	
			usuario.setPassword(password);
			Usuario u = restTemplate.postForObject("http://localhost:8010/buscar_mail", usuario, Usuario.class);
			//login satisfactorio
			if(u.getId()!=usuario.getId()){
				if(u.getPassword().equals(password)){
					if(u.getMail().equals("admin@admin.com")){
						model.addAttribute("usuario",u);
						return "redirect:wallapoptiw/PaginaPrincipal_admin";	
					}
					else{
						model.addAttribute("usuario",u);
						model.addAttribute("error", "Has iniciado sesión correctamente");
						return "redirect:/wallapoptiw/PPrincipal";						
					}
				}
				else{
					model.addAttribute("error", "Los datos introducidos no existen o no son correctos");
					return "Index";
					
				}
				/*podria ser cliente o administrador*/
			}else{
				model.addAttribute("error", "Los datos introducidos no existen o no son correctos");
				return "Index";
			}
		}
	}

	/*para hacer el registro*/
	@RequestMapping (value="wallapoptiw/registrar", method = RequestMethod.POST)
	public String registrar(Model model, @RequestParam("InputEmail") String mail,@RequestParam("Nombre") String nombre, @RequestParam("Apellidos") String apellidos, @RequestParam("Contrasenya") String password,  @RequestParam("VerificacionContrasenya") String passwordVerif,  @RequestParam("Ciudad") String ciudad ){

		if(!password.equals(passwordVerif)){
			//Lanzar feedback no son iguales las contraseñas
			model.addAttribute("error", "Los contraeña introducida no coincide con la verificación de la misma");
			return "Index";
		}
		if(mail.equals("")||nombre.equals("")||apellidos.equals("")||password.equals("")||ciudad.equals("")){
			model.addAttribute("error", "Existen campos vacíos. Rellene todos los campos, por favor.");
			return "Index";
		}
		else{
			Usuario usuario = new Usuario();
			usuario.setApellidos(apellidos);
			usuario.setCiudad(ciudad);
			usuario.setNombre(nombre);
			usuario.setMail(mail);	
			usuario.setPassword(password);
			Usuario copi = restTemplate.postForObject("http://localhost:8010/buscar_mail", usuario, Usuario.class);
			if(copi!=null){
				model.addAttribute("error", "El email introducido ya existe, pruebe con otro.");
				return "Index";
			}
			else{
				Usuario u= restTemplate.postForObject("http://localhost:8010/anyadir_usuario", usuario, Usuario.class);
				model.addAttribute("usuario", u);
				//registro satisfactorio
				model.addAttribute("error", "El registro se ha realizado correctamente.");
				return "redirect:/wallapoptiw/PPrincipal";
			}
			
		}
	}
	
	@RequestMapping(value="wallapoptiw/MiPerfilContrasenya")
	public String devolverMiPerfilContrasenya(Model model, @ModelAttribute Usuario usuario){
		model.addAttribute("error", "");
		return "MiPerfil-contrasenya"; 
	}

	@RequestMapping(value="wallapoptiw/MiPerfilContrasenya2", method = RequestMethod.POST)
	public String Contrasena(Model model, @ModelAttribute Usuario usuario, @RequestParam("ContrasenyaActual") String actual, @RequestParam("NuevaContrasenya") String nueva, @RequestParam("VerificarContrasenya") String verif){
		if(nueva.equals(verif)){
			if(actual.equals(usuario.getPassword())){
				usuario.setPassword(nueva);
				restTemplate.postForObject("http://localhost:8010/modificar_usuario", usuario, Usuario.class);
				model.addAttribute("usuario", usuario);
				model.addAttribute("error", "Contraseña modificada con éxito.");
				return "MiPerfil-contrasenya";
			}
			else{
				model.addAttribute("error", "Las contraseñas actual introducida no es correcta.");
				return "MiPerfil-contrasenya";				
			}
		}
		model.addAttribute("error", "La contraseña nueva y la verificación de la misma no coinciden.");
		return "MiPerfil-contrasenya"; 
	}
	
	
	@RequestMapping(value="wallapoptiw/MiPerfilEditar")
	public String devolverMiPerfilEditar(Model model, @ModelAttribute Usuario usuario){
		model.addAttribute("error", "");
		return "MiPerfil-editar"; 
	}
	
	@RequestMapping(value="wallapoptiw/MiPerfilEditar2", method = RequestMethod.POST)
	public String PerfilEditar(Model model, @ModelAttribute Usuario usuario, @RequestParam("Email") String mail, @RequestParam("Nombre") String nombre, @RequestParam("Apellidos") String apellidos, @RequestParam("Ciudad") String ciudad){
		
		if(mail.equals("")||nombre.equals("")||apellidos.equals("")||ciudad.equals("")){
			model.addAttribute("usuario",usuario);			
			model.addAttribute("error", "Existen campos vacíos. Rellene todos, por favor.");
			return "MiPerfil-editar"; 		}
		else{
			usuario.setApellidos(apellidos);
			usuario.setNombre(nombre);
			usuario.setMail(mail);
			usuario.setCiudad(ciudad);
			restTemplate.postForObject("http://localhost:8010/modificar_usuario", usuario, Usuario.class);
			model.addAttribute("usuario",usuario);
			model.addAttribute("error", "Los datos se han modificado correctamente.");
			return "MiPerfil-editar"; 			
		}
	}
	
	@RequestMapping(value="wallapoptiw/EliminarUsuario", method = RequestMethod.POST)
	public String EliminarUsuario(Model model, @ModelAttribute Usuario usuario){
		restTemplate.postForObject("http://localhost:8010/eliminar_usuario", usuario, Usuario.class);
		model.addAttribute("usuario",null);
		model.addAttribute("error", "");
		return "Index"; 
	}

	@RequestMapping(value="wallapoptiw/CerrarSesion")
	public String cerrarSesion(Model model, @ModelAttribute Usuario usuario){
		model.addAttribute("error", "");
		return "Index"; 
	}
	

	

}
