$(document).ready(function(){

	/*valriables gobales en este documento
marcaran las distintas expresiones reguladas utilizadas
	 */
	var exprCaracteres = /^([A-Z]{1}[a-zñáéíóú]+[\s]*)+$/;
	var exprEmail = /^[a-zA-z0-9_\.\-]+@[a-zA-z0-9\-]+\.[a-zA-z0-9\-\.]+$/;	
	var exprContraseña = /\w{5,255}/;


	/*Al enviar el formulario se realiza esta funcion*/
	$("#bRegistro").click(function(){

		var todoCorrecto=true;
		var enBlanco = "";
		var emailRegistro = $("#InputEmail").val();
		var nombreRegistro = $("#Nombre").val();
		var apellidosRegistro = $("#Apellidos").val();
		var ciudadRegistro = $("#Ciudad").val();
		var passRegistro = $("#Contrasenya").val();
		var passVerifRegistro = $("#VerificacionContrasenya").val();



		/*Comprobar que el valor de email sea correcto, expresion regular + campos en blanco*/
		if(!exprEmail.test(emailRegistro)|| emailRegistro == null || emailRegistro.length == 0 ) {
			$("#msgErrorRegistroMail").fadeIn("slow");
			    	todoCorrecto=false;
		}else{
			$("#msgErrorRegistroMail").fadeOut("slow");
		}


		/*Contraseña esta bien escrita*/
		if(!exprContraseña.test(passRegistro)|| passRegistro == null || passRegistro.length == 0 ) {
			$("#msgErrorRegistroPassword2").fadeIn("slow");
			    		todoCorrecto=false;
			if(passRegistro!=(passVerifRegistro)){
				$("#msgErrorRegistroVerif2").fadeIn("slow");
				todoCorrecto=false;
			}else{
				$("#msgErrorRegistroVerif2").fadeOut("slow");
			}

		}else{
			$("#msgErrorRegistroPassword2").fadeOut("slow");
			if(passRegistro!=(passVerifRegistro)){
				$("#msgErrorRegistroVerif2").fadeIn("slow");
				todoCorrecto=false;
			}else{
				$("#msgErrorRegistroVerif2").fadeOut("slow");
			}
		}


		/*Comprobamos campo nombre*/

		if( nombreRegistro == null || nombreRegistro.length == 0 || !exprCaracteres.test(nombreRegistro) ) {
			$("#msgErrorRegistroNombre").fadeIn("slow");
			todoCorrecto=false;
		}else{
			$("#msgErrorRegistroNombre").fadeOut("slow");
		}

		/*Comprobamos campo apellidos*/

		if( apellidosRegistro == null || apellidosRegistro.length == 0 || !exprCaracteres.test(apellidosRegistro) ) {
			$("#msgErrorRegistroApellidos").fadeIn("slow");
			todoCorrecto=false;
		}else{
			$("#msgErrorRegistroApellidos").fadeOut("slow");
		}

		/*Comprobamos campo ciudad*/

		if( ciudadRegistro == null || ciudadRegistro.length == 0 || !exprCaracteres.test(ciudadRegistro) ) {
			$("#msgErrorRegistroCiudad").fadeIn("slow");
			todoCorrecto=false;
		}else{
			$("#msgErrorRegistroCiudad").fadeOut("slow");
		}

		/*Algun campo incorrecto*/
		if(todoCorrecto==false){
			return false;
		}

		/*todo correcto*/
		document.formRegistro.submit();
		return todoCorrecto;

	});



	/*keyup*/
});