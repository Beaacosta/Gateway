package es.uc3m.tiw.dominios;
import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import static javax.persistence.CascadeType.ALL;


public class Chat{

	private int idChat;
	private String mensaje;
	private int emisor;
	private int receptor;

	
	public Chat(int idChat, String mensaje, int emisor, int receptor) {
		super();
		this.idChat = idChat;
		this.mensaje = mensaje;
		this.emisor = emisor;
		this.receptor = receptor;
	}
	
	public Chat() {
		super();
	}

	public int getIdChat() {
		return idChat;
	}
	public void setIdChat(int idChat) {
		this.idChat = idChat;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public int getEmisor() {
		return emisor;
	}
	public void setEmisor(int emisor) {
		this.emisor = emisor;
	}
	public int getReceptor() {
		return receptor;
	}
	public void setReceptor(int receptor) {
		this.receptor = receptor;
	}
	
	
	
}