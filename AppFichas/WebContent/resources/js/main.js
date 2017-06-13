/**
 * 
 */

function OpenWindow(id){
	$("#modal_"+id).show();
	$("#window_"+id).show();	
}

function CloseWindow(id){
	$("#window_"+id).hide();
	$("#modal_"+id).hide();
}