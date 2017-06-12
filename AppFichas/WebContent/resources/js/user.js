function Search(){
	var user=$("#f_user").val();
	var mail=$("#f_mail").val();
	var type=$("#f_type").val();	
	
	var url="/AppFichas/Users?action=list";	
	var data={
		"ajax":"true",
		"f_user":user,
		"f_mail":mail,
		"f_type":type
	};
		
	$.post(url,data,function(result){
		$("#content").html(result);	
	});
}