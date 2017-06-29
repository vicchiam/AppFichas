function Init(){
	$("#f_state").checkboxradio({
	     icon: false
	});
}

function Search(){	
	var url="/AppFichas/WeightUnits?action=list";	
	var data={
			"ajax":"true",
			"f_state":(($("#f_state").is(':checked'))?0:1)
	};
		
	$.post(url,data,function(result){
		$("#content").html(result);	
	});
}

function ShowFormWeightUnit(id){
	var url="/AppFichas/WeightUnits";	
	var data={
			"action":"showFormWeightUnit",
			"id":id	
	};
		
	$.post(url,data,function(result){
		$("#dynamic_WUNT").html(result);	
		OpenWindow("WUNT");
	});		
}

function SaveWeightUnit(){
	var id=$("#_id").val();
	var name=$("#name").val();
	if(name==""){
		alert("Debes indicar un nombre");
		return;
	}
	var conversion=$("#conversion").val();
	if(conversion==""){
		alert("Debes indicar una conversión");
		return;
	}
	conversion=conversion.replace(",",".");
	if(isNaN(conversion)){
		alert("Número incorrecto");
		return;
	}
	
	var url="/AppFichas/WeightUnits";	
	var data={
			"action":"saveWeightUnit",
			"id":id,
			"name":name,
			"conversion":conversion
	};
		
	$.post(url,data,function(result){
		if(result=="ok"){
			alert("Guardado correctamente");
			Search();
			CloseWindow("WUNT");
		}	
		else{
			alert(result);
		}
		
	});	
}

function ChangeStateWeightUnit(id){
	if(confirm("¿Seguro que deseas modificar su estado?")){
		var url="/AppFichas/WeightUnits";	
		var data={
				"action":"changeStateWeightUnit",
				"id":id	
		};
		$.post(url,data,function(result){
			if(result=="ok"){
				alert("Estado modificado correctamente");
				Search();			
			}	
			else{
				alert(result);
			}		
		});	
	}
}