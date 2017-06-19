function Init(){
	$("#f_user").on("click", function () {
	   $(this).select();
	});
	
	$("#f_mail").on("click", function () {
	   $(this).select();
	});
	
	$("#f_type").selectmenu();
	
	$("#f_state").checkboxradio({
      icon: false
    });
	
	/*
	$("#f_user").autocomplete({
		source: function( request, response ) {
			$.post( {
				url: "/AppFichas/Users",
				dataType: "jsonp",
				data: {
					"action":"autocompleteUser",
					"f_user":$("#f_user").val(),
					"f_mail":$("#f_mail").val(),
					"f_type":$("#f_type").val(),
					"f_state":(($("#f_state").is(':checked'))?0:1)
				},
				success: function(data) {
					alert(data);
					response(data);					
				}
	        });
	      },
	      minLength: 3,
	      select: function( event, ui ) {
	        alert(ui.item.value+" / "+ui.item.id);
	      }
	});
	*/
	
	var projects = [
	                {
	                  value: "jquery"	                  
	                },
	                {
	                  value: "jquery-ui"	                  
	                }
	                ];
	
	$("#f_user").autocomplete({
		source:"/AppFichas/Users?action=autocompleteUser",
		//source:projects,
		minLength: 2,
	    select: function( event, ui ) {
	    	 log( "Selected: " + ui.item.value + " aka " + ui.item.id );
	    }
	});
}

function InitForm(){
	$("#type").selectmenu();	
}

function Search(){
	var user=$("#f_user").val();
	var mail=$("#f_mail").val();
	var type=$("#f_type").val();	
	var state=(($("#f_state").is(':checked'))?0:1);
	
	var url="/AppFichas/Users";	
	var data={
			"action":"list",
			"ajax":"true",
			"f_user":user,
			"f_mail":mail,
			"f_type":type,
			"f_state":state
	};
		
	$.post(url,data,function(result){
		$("#content").html(result);	
	});
}

function ShowNewUser(){
	var url="/AppFichas/Users";	
	var data={
			"action":"showNewUser"
	};
		
	$.post(url,data,function(result){
		$("#dynamic_USR").html(result);	
		OpenWindow("USR");
	});		
}

function ShowUpdateUser(id){
	var url="/AppFichas/Users";	
	var data={
			"action":"showUpdateUser",
			"id":id
	};
		
	$.post(url,data,function(result){
		$("#dynamic_USR").html(result);
		OpenWindow("USR");
	});		
}

function ShowChangePassword(id){
	var url="/AppFichas/Users";	
	var data={
			"action":"showChangePassword",
			"id":id
	};
		
	$.post(url,data,function(result){
		$("#dynamic_USR").html(result);
		OpenWindow("USR");
	});		
}

function ChangeStateUser(id){
	if(confirm("Seguro que deseas modificar el estado del usuario")){
		var url="/AppFichas/Users";	
		var data={
				"action":"changeStateUser",
				"id":id
		};
		
		$.post(url,data,function(result){
			if(result=="ok"){
				alert("Estado del usuario modificado correctamente");
				Search();
			}
			else{
				alert(result);
			}
			CloseWindow("USR");
		});
	}
}

function Save(){
	var id=$("#_id").val();
	var user=$("#user").val();
	var mail=$("#mail").val();
	var type=$("#type").val();
	
	if(user==""){
		alert("Debes indicar un usuario");
		return;
	}	
	
	var url="/AppFichas/Users";	
	var data={
			"action":"saveUser",
			"id":id,
			"user":user,
			"mail":mail,
			"type":type
	};
	
	$.post(url,data,function(result){
		if(result=="ok"){
			alert("Usuario guardado correctamente");
			Search();
		}
		else{
			alert(result);
		}
		CloseWindow("USR");
	});	
}

function SavePassword(){
	var id=$("#_id").val();
	var pass=$("#password").val();
	var rpass=$("#rpassword").val();
	
	if(pass.length<3){
		alert("Debes indicar una contraseña de al menos 3 carateres");
		return;
	}
	if(pass!=rpass){
		alert("Las contraseñas no coinciden");
		return;
	}
	
	var url="/AppFichas/Users";	
	var data={
			"action":"savePassword",
			"id":id,
			"password":pass
	};
	
	$.post(url,data,function(result){
		if(result=="ok"){
			alert("Contraseña modificada correctamente");
			Search();
		}
		else{
			alert(result);
		}
		CloseWindow("USR");
	});	
}

