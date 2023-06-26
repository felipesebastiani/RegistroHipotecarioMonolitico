function showConfirmMessage(titulo, mensaje, funcion_posterior){
	    bootbox.confirm({
	        title: titulo,
	        message: mensaje,
	        //size:"large",
        buttons: {
            cancel: {
                label: '<i class="fa fa-times"></i> Cancel'
            },
            confirm: {
                label: '<i class="fa fa-check"></i><a href="javascript:irALHome()">O.K.</a>'
            }
        },
        callback: function (result) {
        	if(typeof console !== "undefined"){
        		console.log('This was logged in the callback: ' + result);
        	}
        	
            if(funcion_posterior != undefined && funcion_posterior != null){
            	funcion_posterior(result);
            }
        }
    });
}

function showAlertMessage(titulo, mensaje, funcion_posterior){
	mensaje = "<p><i class='fa fa-spin fa-spinner''></i> Loading...</p>" + mensaje;
	
    bootbox.dialog({
        title: titulo,
        message: mensaje,
        //size:"large",
        buttons: {
	        ok: {
	            label: "O.K.",
	            className: 'btn-info',
	            callback: function(){
	                console.log('Custom OK clicked');
	                if(funcion_posterior != undefined && funcion_posterior != null){
	                	funcion_posterior();
	                }
	            }
	        }
	    }
    });
}