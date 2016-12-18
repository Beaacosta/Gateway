/**
 * 
 */

$(document).ready(function(){






	/*Al enviar el formulario se realiza esta funcion*/
	$("#bAnadirProductos").click(function(){

		/*valriables gobales en este documento
		marcaran las distintas expresiones reguladas utilizadas
		 */
		var exprCaracteres = /^([A-Z]{1}[a-zñáéíóú]+[\s]*)+$/;
		var exprEmail = /^[a-zA-z0-9_\.\-]+@[a-zA-z0-9\-]+\.[a-zA-z0-9\-\.]+$/;	
		var exprContraseña = /\w{5,255}/;
		var exprPrecio3 = /([?1234567890][.][1234567890][1234567890]+([1234567890]+))?$/;
		var exprPrecio2 = /([?1234567890][.][1234567890][1234567890])+$/;
		var exprPrecio1 = /([?1234567890][.][1234567890])+$/;
		var exprPrecio0 = /([?1234567890])+$/;
		var exprBlanco = /^\s*$/;


		var todoCorrecto=true;


		var nombreProducto = $("#NombreProducto").val();
		var precioProducto = $("#PrecioProducto").val();
		var descripcionProducto = $("#DescripcionProducto").val();


		/*Comprobar que el valor del nombre de producto no sea nulo*/
		if(nombreProducto == null || nombreProducto.length == 0 || nombreProducto == "" || exprBlanco.test(nombreProducto)) {
			$("#msgErrorAnadirProductoNombre").fadeIn("slow");
			    	todoCorrecto=false;
		}else{
			$("#msgErrorAnadirProductoNombre").fadeOut("slow");
		}


		/*Comprobar que el valor del descrion de producto no sea nulo*/
		if(descripcionProducto == null || descripcionProducto.length == 0 || descripcionProducto == "" || exprBlanco.test(descripcionProducto)) {
			$("#msgErrorAnadirProductoDescripcion").fadeIn("slow");
			    	todoCorrecto=false;
		}else{
			$("#msgErrorAnadirProductoDescripcion").fadeOut("slow");
		}

		/*Precio positivo y como mucho con dos decimales*/
		if(precioProducto > 0){
			$("#msgErrorAnadirProductoPrecio1").fadeOut("slow");

			if((exprPrecio2.test(precioProducto) || exprPrecio1.test(precioProducto) || exprPrecio0.test(precioProducto))){
				
					var posPunto=precioProducto.indexOf(".");
					var parteDecimal=precioProducto.substr(posPunto,4);
					var longitudDecimal=parteDecimal.length;
					
					if(longitudDecimal>3){
						todoCorrecto=false;
						$("#msgErrorAnadirProductoPrecio2").fadeIn("slow");
					}else{
						$("#msgErrorAnadirProductoPrecio2").fadeOut("slow");			

					}				
			
			}else{
				todoCorrecto=false;
				$("#msgErrorAnadirProductoPrecio2").fadeIn("slow");
			}

		}else{
			$("#msgErrorAnadirProductoPrecio1").fadeIn("slow");
			$("#msgErrorAnadirProductoPrecio2").fadeOut("slow");
			todoCorrecto=false;

		}



		/*Algun campo incorrecto*/
		if(todoCorrecto==false){
			return false;
		}

		/*todo correcto*/
		document.formAnadirProducto.submit();
		return todoCorrecto;


	});



	/*keyup*/
});
