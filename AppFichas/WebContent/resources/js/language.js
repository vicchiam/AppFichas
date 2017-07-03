function Init(){
	
	$("#f_state").checkboxradio({
		icon: false
	});
	
	$("#f_name").autocomplete({
		source: function( request, response ) {
			$.ajax( {
				url: "/AppFichas/Languages",
				dataType: "json",
				data: {
					"action":"autocompleteName",
					"f_name":$("#f_name").val(),					
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
}

function InitForm(){
	
}

function Search(){		
	var url="/AppFichas/Languages?action=list";	
	var data={
			"ajax":"true",
			"f_name":$("#f_name").val(),
			"f_state":(($("#f_state").is(':checked'))?0:1)
	};
		
	$.post(url,data,function(result){
		$("#content").html(result);	
	});
}

function ShowFormLanguage(id){
	var url="/AppFichas/Languages";	
	var data={
			"action":"showFormLanguage",
			"id":id
	};
		
	$.post(url,data,function(result){
		$("#dynamic_LAN").html(result);	
		OpenWindow("LAN");
	});	
}

function SaveLanguage(){
	var id=$("#_id").val();
	var name=$("#name").val();
	
	if(name==""){
		alert("Debes indicar un nombre");
		return;
	}
	
	var url="/AppFichas/Languages";	
	var data={
			"action":"saveLanguage",
			"id":id,
			"name":name
	};
		
	$.post(url,data,function(result){
		if(result=="ok"){
			alert("Marca guardada correctamente");
			Search();
			CloseWindow("TMK");
		}
		else{
			alert(result);
		}		
	});	
}

function ChangeStateTrademark(id){
	if(confirm("Seguro que deseas modificar el estado?")){
		var url="/AppFichas/Languages";	
		var data={
				"action":"changeStateLanguage",
				"id":id
		};
			
		$.post(url,data,function(result){
			if(result=="ok"){
				alert("Estado modificado correctamente");
				Search();
				CloseWindow("TMK");
			}
			else{
				alert(result);
			}			
		});	
	}
}

function ShowImageUpdater(id,path){
	var url="/AppFichas/Languages";	
	var data={
			"action":"showImageUpdater",
			"id":id,
			"path":path
	};
		
	$.post(url,data,function(result){
		$("#dynamic_IMG").html(result);	
		OpenWindow("IMG");
	});
}

function CallbackImageUpdater(result){
	if(result=="ok"){
		alert("Imagen guardada");
		Search();
		CloseWindow("IMG");
	}
	else{
		alert("result");
	}
}

function DeleteImage(id){
	if(confirm("¿Seguro que deseas eliminar la imagen?")){
		var url="/AppFichas/Languages";	
		var data={
				"action":"deleteImage",
				"id":id
		};
			
		$.post(url,data,function(result){
			if(result=="ok"){
				alert("Imagen eliminada");				
				Search();
			}
			else{
				alert(result);
			}
		});
	}
}