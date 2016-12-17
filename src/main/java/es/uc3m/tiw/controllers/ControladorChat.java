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

@SessionAttributes({"usuario", "receptor"})
@Controller
public class ControladorChat {

	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value="wallapoptiw/Chat", method = RequestMethod.GET)
	public String devolverChat(Model model, @ModelAttribute Usuario usuario, @RequestParam("IdReceptor") int id){
		Usuario emisor = usuario;
		Usuario receptor = restTemplate.postForObject("http://localhost:8010/buscar_id", id, Usuario.class);
		Conversacion conv = new Conversacion();
		conv.setEmisor(emisor);
		conv.setReceptor(receptor);
		List<Chat> mensajes= restTemplate.postForObject("http://localhost:8030/buscar_mensaje", conv, List.class);
		model.addAttribute("mensajes", mensajes);
		return "Chat"; 
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
