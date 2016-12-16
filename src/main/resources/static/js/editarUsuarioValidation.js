/**
 * 
 */

$(document).ready(function(){

	var exprCaracteres = /^([A-Z]{1}[a-zñáéíóú]+[\s]*)+$/;
	var exprEmail = /^[a-zA-z0-9_\.\-]+@[a-zA-z0-9\-]+\.[a-zA-z0-9\-\.]+$/;	
	var exprContraseña = /\w{5,255}/;



$("#bMiPerfilEditar").click(function(){
	
	var todoCorrecto=true;
	
	/*variables*/

	var nombreUsuario = $("#NombreUsuario").val();
	var apellidosUsuario = $("#ApellidosUsuario").val();
	var ciudadUsuario = $("#CiudadUsuario").val();
	var emailUsuario = $("#EmailUsuario").val();

	/*Comprobar que el valor de email sea correcto, expresion regular + campos en blanco*/
	if(!exprEmail.test(emailUsuario)|| emailUsuario == null || emailUsuario.length == 0 ) {
		$("#msgErrorEditarUsuarioMail").fadeIn("slow");
		    	todoCorrecto=false;
	}else{
		$("#msgErrorEditarUsuarioMail").fadeOut("slow");
	}
	
	
	/*Comprobamos campo nombre*/

	if( nombreUsuario == null || nombreUsuario.length == 0 || !exprCaracteres.test(nombreUsuario) ) {
		$("#msgErrorEditarUsuarioNombre").fadeIn("slow");
		todoCorrecto=false;
	}else{
		$("#msgErrorEditarUsuarioNombre").fadeOut("slow");
	}

	/*Comprobamos campo apellidos*/

	if( apellidosUsuario == null || apellidosUsuario.length == 0 || !exprCaracteres.test(apellidosUsuario) ) {
		$("#msgErrorEditarUsuarioApellidos").fadeIn("slow");
		todoCorrecto=false;
	}else{
		$("#msgErrorEditarUsuarioApellidos").fadeOut("slow");
	}

	/*Comprobamos campo ciudad*/

	if( ciudadUsuario == null || ciudadUsuario.length == 0 || !exprCaracteres.test(ciudadUsuario) ) {
		$("#msgErrorEditarUsuarioCiudad").fadeIn("slow");
		todoCorrecto=false;
	}else{
		$("#msgErrorEditarUsuarioCiudad").fadeOut("slow");
	}
	
	/*Algun campo incorrecto*/
	if(todoCorrecto==false){
		alert("Existen fallos en algunos campos");
		return false;
	}

	/*todo correcto*/
	document.formEditarUsuario.submit();
	alert("Inicio de Sesion Satisfactorio")
	return todoCorrecto;
	
})


});