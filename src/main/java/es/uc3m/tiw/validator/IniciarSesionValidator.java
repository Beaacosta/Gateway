package es.uc3m.tiw.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.uc3m.tiw.dominios.DatosInicioSesion;
import es.uc3m.tiw.dominios.Usuario;


@Component
public class IniciarSesionValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return DatosInicioSesion.class.equals(clazz);//clase del bean al que da soporte este validador
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		DatosInicioSesion datosInicioSesion = (DatosInicioSesion) target;
		
		
		//el email es obligatorio
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail", "Campo email requerido");
		
		//el email tiene que tener un formato correcto - Expresion regularç
		
		validarFormatoMail(datosInicioSesion.getMail(),errors);
				
		//la contraseña es obligatoria
		
		//la contraseña tiene que tener un formato correcto
		
		//email no encontrado en la bbdd
		
		//contraseña no correcta para email en la bbdd
		
		
	}
	
	
	private void validarFormatoMail(String mail, Errors errors){
		//valida el mail por expresion regular y si hay error lo añade a errors
		
		Pattern plantilla = Pattern.compile("^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$");
		Matcher resultado = plantilla.matcher(mail);
		if(resultado.find()==false){
			errors.rejectValue("mail", "field.mail.incorrecExpression", "Formato incorrecto");
		}
	}

}
