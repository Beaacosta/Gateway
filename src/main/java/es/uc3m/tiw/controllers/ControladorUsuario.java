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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.dominios.Usuario;


@SessionAttributes(value={"loged","usuarioValido"})
@Controller
public class ControladorUsuario {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value="wallapoptiw/")
	public String devolverIndex(Model modelo){
		modelo.addAttribute("usuario", new Usuario());
		modelo.addAttribute("loged", false);

		return "Index"; 
	}

	@RequestMapping(value="wallapoptiw/Index")
	public String redirigirIndex(Model modelo){
		
		return "redirect:wallapoptiw/"; 
	}

	/*para hacer el login*/
	@RequestMapping (value="wallapoptiw/", method = RequestMethod.POST)
	public String iniciarSesion(Model model, @RequestParam("mail") String mail, @RequestParam("password") String password ){

		Usuario usuario = new Usuario();
		usuario.setMail(mail);
		usuario.setPassword(password);
		
		usuario = restTemplate.postForObject("http://localhost:8010/buscar_mail", mail, Usuario.class);
		//login satisfactorio
		if(true){

			/*podria ser cliente o administrador*/
			return "redirect:wallapoptiw/PaginaPrincipal";

		}else{

			return "Index";
		}
		

	}

	
	/*para hacer el registro*/
	
	@RequestMapping (value="wallapoptiw/", method = RequestMethod.POST)
	public String registrarUsuario(Model model, @RequestParam("mail") String mail, @RequestParam("password") String password,
			@RequestParam("nombre") String nombre, @RequestParam("apellidos") String apellidos,
			@RequestParam("ciudad") String ciudad, @RequestParam("passVerif") String passVerif){

		Usuario usuario = new Usuario();
		usuario.setApellidos(apellidos);
		usuario.setCiudad(ciudad);	
		usuario.setMail(mail);
		usuario.setNombre(nombre);
		usuario.setPassword(password);
		
		usuario = restTemplate.postForObject("http://localhost:8010/anyadir_usuario", usuario, Usuario.class);
		//login satisfactorio
		if(true){

			/*podria ser cliente o administrador*/
			return "redirect:wallapoptiw/PaginaPrincipal";

		}else{

			return "Index";
		}
		

	}

	
	
	@RequestMapping(value="wallapoptiw/MiPerfilContrasenya")
	public String devolverMiPerfilContrasenya(Model modelo, @ModelAttribute Usuario usuario){
		return "MiPerfil-contrasenya"; 
	}

	@RequestMapping(value="wallapoptiw/MiPerfilEditar")
	public String devolverMiPerfilEditar(Model modelo, @ModelAttribute Usuario usuario){
		return "MiPerfil-editar"; 
	}


	

}
