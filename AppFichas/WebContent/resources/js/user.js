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
	
	$("#f_user").autocomplete({
		source: function( request, response ) {
			$.ajax( {
				url: "/AppFichas/Users",
				dataType: "json",
				data: {
					"action":"autocompleteUser",
					"f_user":$("#f_user").val(),
					"f_mail":$("#f_mail").val(),
					"f_type":$("#f_type").val(),
					"f_state":(($("#f_state").is(':checked'))?0:1)
				},
				success: function(data) {
					response(data);					
				}
	        });
	      },
	      minLength: 3,
	      select: function(event,ui){}
	});	
	
	$("#f_mail").autocomplete({
		source: function( request, response ) {
			$.ajax( {
				url: "/AppFichas/Users",
				dataType: "json",
				data: {
					"action":"autocompleteMail",
					"f_user":$("#f_user").val(),
					"f_mail":$("#f_mail").val(),
					"f_type":$("#f_type").val(),
					"f_state":(($("#f_state").is(':checked'))?0:1)
				},
				success: function(data) {
					response(data);					
				}
	        });
	      },
	      minLength: 3,
	      select: function(event, ui){}
	});	
}

function InitFormUser(){
	$("#type").selectmenu();	
}

function InitFormUserTrademark(){
	$(".select_container li").click(function (e) {
		var li=e.target;
		if($(li).hasClass("selected")){
			$(li).removeClass("selected");
		}
		else{
			$(li).addClass("selected");
		}
	});
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

function ShowFormUser(id){
	var url="/AppFichas/Users";	
	var data={
			"action":"showFormUser",
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

function ShowUserTrademarks(id,type){
	if(type==1){
		alert("El tipo Administrador tiene acceso a todas las marcas");
		return;
	}
	if(type==2){
		alert("El tipo Usuario tiene acceso a todas las marcas");
		return;
	}	
	
	var url="/AppFichas/Users";	
	var data={
			"action":"showUserTrademarks",
			"id":id
	};
		
	$.post(url,data,function(result){
		$("#dynamic_USR_TMK").html(result);
		OpenWindow("USR_TMK");
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
				CloseWindow("USR");
			}
			else{
				alert(result);
			}			
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
			CloseWindow("USR");
		}
		else{
			alert(result);
		}		
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
			CloseWindow("USR");
		}
		else{
			alert(result);
		}		
	});	
}

function RemoveTrademark(){
	var id=$("#_id").val();
	var ids=new Array();
	$("#right .selected").each(function(){
		$("#left ul").append(this);
		$(this).removeClass("selected");
		$(this).find("input").each(function(){
			ids[ids.length]=$(this).val();
		});
	});
	var idsTrademark=ids.join("__");	
	SendSelector(id,idsTrademark,"removeUserTrademarks");
}

function AddTrademark(){
	var id=$("#_id").val();
	var ids=new Array();
	$("#left .selected").each(function(){
		$("#right ul").append(this);
		$(this).removeClass("selected");
		$(this).find("input").each(function(){
			ids[ids.length]=$(this).val();
		});
	});
	var idsTrademark=ids.join("__");
	SendSelector(id,idsTrademark,"addUserTrademarks");
}

function SendSelector(id,idsTrademark,action){
	var url="/AppFichas/Users";	
	var data={
			"action":action,
			"id":id,
			"idsTrademarks":idsTrademark
	};
	
	$.post(url,data,function(result){
		if(result!="ok"){
			alert(result);
		}		
	});	
}

