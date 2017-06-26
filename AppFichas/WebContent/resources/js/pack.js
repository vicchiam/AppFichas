function Init(){

	$("#f_apt").checkboxradio({
	      icon: false
	    })
	
	$("#f_state").checkboxradio({
      icon: false
    });
	
	$("#f_description").autocomplete({
		source: function( request, response ) {
			$.ajax( {
				url: "/AppFichas/Pack",
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