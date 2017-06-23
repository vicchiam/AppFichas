function Init(){
	$("#f_name").on("click", function () {
	   $(this).select();
	});		
	
	$("#f_state").checkboxradio({
      icon: false
    });
	
	$("#f_name").autocomplete({
		source: function( request, response ) {
			$.ajax( {
				url: "/AppFichas/Trademarks",
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

function Search(){		
	var url="/AppFichas/Trademarks?action=list";	
	var data={
			"ajax":"true",
			"f_name":$("#f_name").val(),
			"f_state":(($("#f_state").is(':checked'))?0:1)
	};
		
	$.post(url,data,function(result){
		$("#content").html(result);	
	});
}

function ShowNewTrademark(){
	var url="/AppFichas/Trademarks";	
	var data={
			"action":"showNewTrademark"
	};
		
	$.post(url,data,function(result){
		$("#dynamic_TMK").html(result);	
		OpenWindow("TMK");
	});		
}

function ShowUpdateTrademark(id){
	var url="/AppFichas/Trademarks";	
	var data={
			"action":"showUpdateTrademark",
			"id":id
	};
		
	$.post(url,data,function(result){
		$("#dynamic_TMK").html(result);	
		OpenWindow("TMK");
	});		
}

function SaveTrademark(){
	var id=$("#_id").val();
	var name=$("#name").val();
	
	var url="/AppFichas/Trademarks";	
	var data={
			"action":"saveTrademark",
			"id":id,
			"name":name
	};
		
	$.post(url,data,function(result){
		if(result=="ok"){
			alert("Marca guardada correctamente");
			Search();
		}
		else{
			alert(result);
		}
		CloseWindow("TMK");
	});	
}

function ChangeStateTrademark(id){
	if(confirm("Seguro que deseas modificar el estado?")){
		var url="/AppFichas/Trademarks";	
		var data={
				"action":"changeStateTrademark",
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
			CloseWindow("TMK");
		});	
	}
}

function ShowImageUpdater(id,path){
	var url="/AppFichas/Trademarks";	
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
		CloseWindow("IMG");
		Search();
	}
	else{
		alert("result");
	}
}

function DeleteImage(id){
	if(confirm("¿Seguro que deseas eliminar la imagen?")){
		var url="/AppFichas/Trademarks";	
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