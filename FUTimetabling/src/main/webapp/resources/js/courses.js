$(document).ready(function() { 

	_init();
	
	$("#table-courses").dataTable( {
		"lengthChange": true,
		"searching": true,
		"paging": true,
		"pageLength": 10
    });
	
	$("#select-semester").on("change", function() {
		window.location = "courses?semesterId=" +$(this).find("option:selected").val();	
	});
	
	function _init() {
		$("#select-semester").find("option[value='" +_urlParam("semesterId") +"']").attr("selected", "selected");
	}
	
	function _urlParam(param) {
	    var url = $(location).attr('search').substring(1);
	    var parameters = url.split('&');
	    for (var i = 0; i < parameters.length; i++) 
	    {
	        var parameter = parameters[i].split('=');
	        if (parameter[0] == param) 
	        {
	            return parameter[1];
	        }
	    }
	}
	
	
});