$(document).ready(function(){

/*valriables gobales en este documento
marcaran las distintas expresiones reguladas utilizadas
*/
	var exprCaracteres = /^\s+$/;
	var exprEmail = /^[a-zA-z0-9_\.\-]+@[a-zA-z0-9\-]+\.[a-zA-z0-9\-\.]+$/;	
	var exprContraseña = /\w{5,255}/;
	
	
	/*Al enviar el formulario se realiza esta funcion*/
	$("#btnIniciarSesion").click(function(){
		var todoCorrecto=true;

		
		/*Nos indica si se ha introducido contraseña sin ningun email*/
		if ($("#email").val().length == ""){
			$("#msgErrorLoginMail").fadeIn("slow");
			todoCorrecto=false;
			
			/*Mail vacio y contraseña vacia*/
			if ($("#password").val() == "" ){
				$("#msgErrorLoginPass2").fadeIn("slow");
	    		todoCorrecto=false;
			}else{
				$("#msgErrorLoginPass2").fadeOut("slow");
				
				/*Mail vacio y contraseña incorrecta*/
				if (!exprContraseña.test($("#password").val())){
					$("#msgErrorLoginPass1").fadeIn("slow");
		    		todoCorrecto=false;
				}else{
					$("#msgErrorLoginPass1").fadeOut("slow");
				}
			}
			
			

		}else{/*Nombre de usuario "si" contraseña "si"*/
			$("#msgErrorLoginMail").fadeOut("slow");
		}


		/*Comprueba que el formato de la contraseña es correcto cuando hay usuario creado*/
		if ($("#email").val() != ""){
			
			/*validamos si está bien escrito el mail*/
			if(!exprEmail.test($("#email").val())|| $("#email").val() == null || $("#email").val().length == 0 ) {
				$("#msgErrorLoginMail").fadeIn("slow");
	    		todoCorrecto=false;
			}else{
				$("#msgErrorLoginMail").fadeOut("slow");
			}

			/*Miramos si hay contraseña, si no hay -> Introduzca contraseña*/
			if ($("#password").val() == "" ){
				$("#msgErrorLoginPass2").fadeIn("slow");
	    		todoCorrecto=false;
			}else{
				$("#msgErrorLoginPass2").fadeOut("slow");
				/*Miramos si hay contraseña, si hay -> Verificamos*/
				if (!exprContraseña.test($("#password").val())){
					$("#msgErrorLoginPass1").fadeIn("slow");
		    		todoCorrecto=false;
				}else{
					$("#msgErrorLoginPass1").fadeOut("slow");
				}
			}
		
		}
		
		/*Algun campo incorrecto*/
		if(todoCorrecto==false){
			alert("Existen fallos en algunos campos");
			return false;
		}

		/*todo correcto*/
		document.formInicioSesion.submit();
		alert("Inicio de Sesion Satisfactorio")
		return todoCorrecto;

	});



	/*keyup*/
	});