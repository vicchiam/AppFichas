function Init(){
	
	$("#f_description").on("click", function () {
		$(this).select();
	});

	$("#f_apt").selectmenu();
	
	$("#f_state").checkboxradio({
		icon: false
    });
	
	$("#f_description").autocomplete({
		source: function( request, response ) {
			$.ajax( {
				url: "/AppFichas/Packs",
				dataType: "json",
				data: {
					"action":"autocompleteDescription",
					"f_description":$("#f_description").val(),
					"f_apt":$("#f_apt").val(),
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
	$("#apt").checkboxradio({
		icon: false
	});
	
	$("#no_apt").checkboxradio({
		icon: false
	});
}

function Search(){		
	var url="/AppFichas/Packs?action=list";	
	var data={
			"ajax":"true",
			"f_description":$("#f_description").val(),
			"f_apt":$("#f_apt").val(),
			"f_state":(($("#f_state").is(':checked'))?0:1)
	};
		
	$.post(url,data,function(result){
		$("#content").html(result);	
	});
}

function ShowFormPack(id){
	var url="/AppFichas/Packs";	
	var data={
			"action":"showFormPack",
			"id":id
	};
		
	$.post(url,data,function(result){
		$("#dynamic_PACK").html(result);	
		OpenWindow("PACK");
	});		
}

function SavePack(){
	var id=$("#_id").val();
	var description=$("#description").val();
	var mesure=$("#mesure").val();
	
	if(description==""){
		alert("Debes indicar una descripcion");
		return null;
	}
	
	var apt="";
	if($("#apt").is(':checked')){
		apt="on";
	}
	else{
		apt="off";
	}

	var url="/AppFichas/Packs";	
	var data={
			"action":"savePack",
			"id":id,
			"description":description,
			"mesure":mesure,
			"apt":apt
	}
	
	$.post(url,data,function(result){
		if(result=="ok"){
			alert("Guardado correctamente");
			Search();
			CloseWindow("PACK");
		}
		else{
			alert(result);
		}		
	});	
}

function ChangeStatePack(id){
	if(confirm("¿Seguro que deseas modificar el estado del envase?")){
		var url="/AppFichas/Packs";	
		var data={
				"action":"changeStatePack",
				"id":id
		}
		
		$.post(url,data,function(result){
			if(result=="ok"){
				alert("Modificado correctamente");
				Search();				
			}
			else{
				alert(result);
			}		
		});		
	}
}

function ShowListPackWeight(id_pack){
	var url="/AppFichas/Packs";	
	var data={
			"action":"showListPackWeight",
			"id":id_pack
	};
		
	$.post(url,data,function(result){
		$("#dynamic_PCKW").html(result);	
		OpenWindow("PCKW");
	});	
}

function ReloadListPacks(){
	Search();
	CloseWindow("PCKW");
}

function SearchPackWeight(id_pack){
	var url="/AppFichas/Packs";	
	var data={
			"action":"showListPackWeight",
			"id":id_pack
	};
		
	$.post(url,data,function(result){
		$("#dynamic_PCKW").html(result);		
	});	
}

function ShowFormPackWeight(id){
	var url="/AppFichas/Packs";	
	var data={
			"action":"showFormPackWeight",
			"id":id
	};
		
	$.post(url,data,function(result){
		$("#dynamic_PCKWF").html(result);	
		OpenWindow("PCKWF");
	});		
}

function SavePackWeight(){
	var id_pack=$("#_idPack").val();
	var id_weight=$("#_idWeight").val();
	var value=$("#weightValue").val();
	value=value.replace(",",".");
	if(isNaN(value)){
		alert("Número incorrecto");
		return;
	}
	
	var id_weightUnit=$("#weightUnit").val();
	
	var url="/AppFichas/Packs";
	var data={
			"action":"savePackWeight",
			"id_pack":id_pack,
			"id_weight":id_weight,
			"value":value,
			"id_weightUnit":id_weightUnit
	}
		
	$.post(url,data,function(result){
		if(result=="ok"){
			alert("Guardado correctamente");
			SearchPackWeight(id_pack);
			CloseWindow("PCKWF");			
		}
		else{
			alert(result);
		}
	});	
}

function DeletePackWeight(id_weight){
	if(confirm("¿Seguro que lo deseas eliminar?")){
		var id_pack=$("#_idPack").val();
		
		var url="/AppFichas/Packs";
		var data={
				"action":"deletePackWeight",		
				"id_weight":id_weight
		}
			
		$.post(url,data,function(result){
			if(result=="ok"){
				alert("Eliminado correctamente");
				SearchPackWeight(id_pack);
				CloseWindow("PCKWF");			
			}
			else{
				alert(result);
			}
		});	
	}
}



