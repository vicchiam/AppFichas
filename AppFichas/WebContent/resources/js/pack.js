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

function ShowNewPack(){
	var url="/AppFichas/Packs";	
	var data={
			"action":"showNewPack"
	};
		
	$.post(url,data,function(result){
		$("#dynamic_PACK").html(result);	
		OpenWindow("PACK");
	});		
}

function ShowUpdatePack(id){
	var url="/AppFichas/Packs";	
	var data={
			"action":"showUpdatePack",
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

function ShowNewPackWeight(){
	var url="/AppFichas/Packs";	
	var data={
			"action":"showFormPackWeight"
	};
		
	$.post(url,data,function(result){
		$("#dynamic_PCKWF").html(result);	
		OpenWindow("PCKWF");
	});		
}



/*
function AddWeight(){
	var ghost_select=document.getElementById("ghost_select");	
	var html="<tr><td><input type='hidden' value='0' /><input class='right' type='text' /></td>";
	html+="<td>"+ghost_select.innerHTML+"</td>";
	html+="<td class='right'><input type='button' class='button_green' value='Guardar' onclick='SaveWeight(this)' />";
	html+="<input type='button' class='button_red' value='Eliminar' onclick='DeleteWeight(this)' /></td></tr>";
	$("#weights").append(html);
}


function getWeightValues(e){
	var id_pack=$("#_id").val();
	var id_weight=0;
	var value=0;
	var id_weightUnit=0;
	$(e).parent().parent().find("input").each(function(index,element){
		if(index==0){
			id_weigth=$(this).val();
		}
		if(index==1){
			value=$(this).val();
		}
	});
	$(e).parent().parent().find("select").each(function(index,element){
		if(index==0){
			id_weightUnit=$(this).val();
		}
	});	
	
	var data={
		"id_pack":id_pack,
		"id_weight":id_weight,
		"id_weightUnit":id_weightUnit
	}
	return data;
}

function SaveWeight(e){
	var data=getWeightValues(e);
	data.action="savePackWeight";
	var url="/AppFichas/Packs";
		
	$.post(url,data,function(result){
		if(result=="ok"){
			
		}
		else{
			alert(result);
		}
	});		
}

function DeleteWeight(e){
	var id_pack=$("#_id").val();
	var id_weight=0;
	$(e).parent().parent().find("input").each(function(index,element){
		if(index==0){
			id_weigth=$(this).val();
		}		
	});
}
*/