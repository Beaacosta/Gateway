package es.uc3m.tiw.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.dominios.Usuario;

@Controller
public class ControladorUsuario {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value="wallapoptiw/")
	public String devolverIndex(Model modelo){
		modelo.addAttribute("usuario", new Usuario());
		modelo.addAttribute("logged", false);
		return "Index"; 
	}

	@RequestMapping(value="wallapoptiw/Index")
	public String redirigirIndex(Model modelo){

		return "redirect:wallapoptiw/"; 
	}

	/*para hacer el login*/
	@RequestMapping (value="wallapoptiw/", method = RequestMethod.POST)
	public String iniciarSesion(Model model, @RequestParam("email") String mail, @RequestParam("password") String password ){

		Usuario usuario = new Usuario();
		usuario.setApellidos("aaa");
		usuario.setCiudad("bbb");
		usuario.setNombre("bea");
		usuario.setMail(mail);	
		usuario.setPassword(password);
		Usuario u = restTemplate.postForObject("http://localhost:8010/buscar_mail", usuario, Usuario.class);
		model.addAttribute("usuario", u);
		String nombre = u.getNombre();
		
		//login satisfactorio
		if(!u.equals(null)){
			if(u.getPassword().equals(password)){
				if(u.getMail().equals("admin@admin.com")){
					return "redirect:wallapoptiw/PaginaPrincipal_admin";	
				
				}
				else{
					return "PaginaPrincipal";						
				}
			}
			else{
				return "Index";
				
			}
			/*podria ser cliente o administrador*/
		}else{
			return "Index";
		}
		

	}

	/*para hacer el registro*/
	@RequestMapping (value="wallapoptiw/registrar", method = RequestMethod.POST)
	public String registrar(Model model, @RequestParam("InputEmail") String mail,@RequestParam("Nombre") String nombre, @RequestParam("Apellidos") String apellidos, @RequestParam("Contrasenya") String password,  @RequestParam("VerificacionContrasenya") String passwordVerif,  @RequestParam("Ciudad") String ciudad ){

		if(!password.equals(passwordVerif)){
			//Lanzar feedback no son iguales las contraseñas
			return "Index";
		}
		
		Usuario usuario = new Usuario();
		
		usuario.setApellidos(apellidos);
		usuario.setCiudad(ciudad);
		usuario.setNombre(nombre);
		usuario.setMail(mail);	
		usuario.setPassword(password);
		Usuario u = restTemplate.postForObject("http://localhost:8010/anyadir_usuario", usuario, Usuario.class);
		model.addAttribute("usuario", u);
		//registro satisfactorio
		return "PaginaPrincipal";
	}
	
	@RequestMapping(value="wallapoptiw/MiPerfilContrasenya")
	public String devolverMiPerfilContrasenya(Model modelo, @ModelAttribute Usuario usuario){
		return "MiPerfil-contrasenya"; 
	}

	@RequestMapping(value="wallapoptiw/MiPerfilEditar")
	public String devolverMiPerfilEditar(Model modelo, @ModelAttribute("usuario") Usuario usuario){
		String nombre=usuario.getNombre();
		System.out.println(nombre);
		return "MiPerfil-editar"; 
	}

	@RequestMapping(value="wallapoptiw/CerrarSesion")
	public String cerrarSesion(Model modelo, @ModelAttribute Usuario usuario){
		return "Index"; 
	}
	

}
