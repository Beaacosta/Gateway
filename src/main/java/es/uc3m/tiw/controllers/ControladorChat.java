package es.uc3m.tiw.controllers;

import java.util.Calendar;
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

import es.uc3m.tiw.dominios.Chat;
import es.uc3m.tiw.dominios.Conversacion;
import es.uc3m.tiw.dominios.Producto;
import es.uc3m.tiw.dominios.Usuario;

@SessionAttributes({"usuario", "receptor", "mensUsuario", "mensReceptor", "receptores"})
@Controller
public class ControladorChat {

	@Autowired
	RestTemplate restTemplate;

	//Obtener los mensajes en los que el usuario en sesion es el emisor
	@RequestMapping(value="wallapoptiw/Chat", method = RequestMethod.GET)
	public String devolverChat(Model model, @ModelAttribute Usuario usuario){
		List<Chat> receptores = restTemplate.postForObject("http://localhost:8030/listar_receptores", usuario, List.class);
		model.addAttribute("mensUsuario", receptores);
		return "ChatInicio"; 
	}
	
	//Obtener los mensajes en los que el usuario en sesion es el emisor
	@RequestMapping(value="wallapoptiw/Chat3", method = RequestMethod.GET)
	public String devolverChat3(Model model, @ModelAttribute Usuario usuario){
		List<Chat> receptores = restTemplate.postForObject("http://localhost:8030/listar_emisores", usuario, List.class);
		model.addAttribute("mensUsuario", receptores);
		return "ChatInicio2"; 
	}
	
	//Obtener los mensajes en los que el usuario en sesion es el emisor
	@RequestMapping(value="wallapoptiw/Chat2", method = RequestMethod.GET)
	public String devolverChat2(Model model, @ModelAttribute Usuario usuario, @RequestParam("receptor") int receptor){
		List<Chat> receptores = restTemplate.postForObject("http://localhost:8030/listar_receptores", usuario, List.class);
		model.addAttribute("receptores", receptores);
		Usuario emisor = usuario;
		Usuario receptor2 = new Usuario();
		receptor2.setApellidos("aaa");
		receptor2.setNombre("bbb");
		receptor2.setCiudad("ccc");
		receptor2.setMail("aa@aa.com");
		receptor2.setPassword("123");
		receptor2.setId(receptor);
		Usuario receptor3 = restTemplate.postForObject("http://localhost:8010/buscar_id", receptor2, Usuario.class);
		Conversacion conv = new Conversacion();
		conv.setEmisor(emisor);
		conv.setReceptor(receptor3);
		List<Chat> mensajes= restTemplate.postForObject("http://localhost:8030/buscar_mensaje", conv, List.class);
		model.addAttribute("receptor", receptor3);
		model.addAttribute("mensUsuario", mensajes);
		return "Chat"; 
	}
	
	
	//Obtener los mensajes en los que el usuario en sesion es el receptor
	@RequestMapping(value="wallapoptiw/ChatReceptor", method = RequestMethod.GET)
	public String devolverChatReceptor(Model model, @ModelAttribute Usuario usuario, @RequestParam("receptor") int receptor){
		List<Chat> receptores = restTemplate.postForObject("http://localhost:8030/listar_receptores", usuario, List.class);
		model.addAttribute("receptores", receptores);
		Usuario receptor2 = usuario;
		Usuario emisor2 = new Usuario();
		emisor2.setApellidos("aaa");
		emisor2.setNombre("bbb");
		emisor2.setCiudad("ccc");
		emisor2.setMail("aa@aa.com");
		emisor2.setPassword("123");
		emisor2.setId(receptor);
		Usuario emisor = restTemplate.postForObject("http://localhost:8010/buscar_id", emisor2, Usuario.class);
		Conversacion conv = new Conversacion();
		conv.setEmisor(emisor2);
		conv.setReceptor(receptor2);
		List<Chat> mensajes= restTemplate.postForObject("http://localhost:8030/buscar_mensaje", conv, List.class);
		model.addAttribute("mensReceptor", mensajes);
		return "Chat2"; 
	}

	
	@RequestMapping (value="wallapoptiw/anyadir_chat", method = RequestMethod.POST)
	public String anyadirChat(Model model, @ModelAttribute Usuario usuario, @RequestParam("Mensaje") String mensaje, @ModelAttribute("receptor") Usuario receptor){
			Chat chat = new Chat();
			chat.setEmisor(usuario.getId());
			chat.setReceptor(receptor.getId());
			chat.setMensaje(mensaje);
			restTemplate.postForObject("http://localhost:8030/anyadir_mensaje", chat, Chat.class);
			//model.addAttribute("error", "Su mensaje ha sido enviado.");
			return "redirect:/wallapoptiw/PPrincipal";
		
	}
	
	//Coger el Usuario del producto de la vista
	@RequestMapping(value="wallapoptiw/EnviarMensaje", method = RequestMethod.GET)
	public String enviarMensaje(Model model, @ModelAttribute Usuario usuario, @RequestParam("usuario") int receptor){
		Usuario u = new Usuario();
		u.setApellidos("ss");
		u.setCiudad("ciudad");
		u.setId(receptor);
		u.setMail("dgfhd");
		u.setNombre("dff");
		u.setPassword("def");
		Usuario r = restTemplate.postForObject("http://localhost:8010/buscar_id", u, Usuario.class);
		model.addAttribute("receptor", r);
		return "mail"; 
	}

}
