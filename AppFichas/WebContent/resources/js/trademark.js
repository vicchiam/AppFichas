function Init(){
	$("#f_name").on("click", function () {
	   $(this).select();
	});		
}

function Search(){
	var name=$("#f_name").val();
		
	var url="/AppFichas/Trademarks?action=list";	
	var data={
			"ajax":"true",
			"f_name":name
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

function DeleteTrademark(id){
	if(confirm("Seguro que lo deseas eliminar?")){
		var url="/AppFichas/Trademarks";	
		var data={
				"action":"deleteTrademark",
				"id":id
		};
			
		$.post(url,data,function(result){
			if(result=="ok"){
				alert("Eliminado correctamente");
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