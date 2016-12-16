/**
 * 
 */

$(document).ready(function(){

	var exprCaracteres = /^\s+$/;
	var exprEmail = /^[a-zA-z0-9_\.\-]+@[a-zA-z0-9\-]+\.[a-zA-z0-9\-\.]+$/;	
	var exprContraseña = /\w{5,255}/;

/*variables*/

var nombreUsuario = $("#NombreUsuario");
var apellidosUsuario = $("#ApellidosUsuario");
var ciudadUsuario = $("#ApellidosUsuario");
var emailUsuario = $("#CiudadUsuario");

$("#bMiPerfilEditar").click(function(){
	
	var todoCorrecto=true;

	
	if( nombreUsuario == null || nombreUsuario.length == 0 || exprCaracteres.test(nombreUsuario) ) {
  		$("#msgErrorEditarUsuarioNombre").fadeIn("slow");
  		todoCorrecto=false;
	}else{
		$("#msgErrorEditarUsuarioNombre").fadeOut("slow");
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