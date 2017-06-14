function Init(){
	$("#f_user").on("click", function () {
	   $(this).select();
	});
	
	$("#f_mail").on("click", function () {
	   $(this).select();
	});
}

function Search(){
	var user=$("#f_user").val();
	var mail=$("#f_mail").val();
	var type=$("#f_type").val();	
	var state=(($("#f_state").is(':checked'))?0:1);
	
	var url="/AppFichas/Users?action=list";	
	var data={
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

