$(document).ready(function(){	
	
	var colors = ["checked-1", "checked-2", "checked-3", "checked-4", "checked-5"];
	
	var bcc = -1;
	
	$("button[id^='button-course-']").on("click", function() {
		if(this.id.indexOf('-1-') > -1) {
			bcc = 1;
		}
		else if(this.id.indexOf('-2-') > -1) {
			bcc = 2;
		}
		else if(this.id.indexOf('-3-') > -1) {
			bcc = 3;
		}
		else if(this.id.indexOf('-4-') > -1) {
			bcc = 4;
		}
		else if(this.id.indexOf('-5-') > -1) {
			bcc = 5;
		}
		
		
		if(!$(this).data("clicked")) {
			
			$("button[id^='button-course-']").each(function(){
				if($(this).data("clicked")) {
					$(this).removeClass("button-clicked");
					$(this).addClass("button-not-clicked");
					$(this).data('clicked', false);
				}
			});
			$(this).removeClass("button-not-clicked");
			$(this).addClass("button-clicked");
//			$(this).css("border-color", "#787878");
//			$(this).css("background", "#eeeeee");
			$(this).data('clicked', true);
			for(var x = 1; x < 7; x++) {
				for(var y = 1; y < 7; y++) {
					
					var id = "tt-" +x +"-" +y;
					if(!$("#" + id).hasClass("checked")) {
						$("#" + id).addClass("active");
					}
				}
			}
		} else {

			$(this).removeClass("button-clicked");
			$(this).addClass("button-not-clicked");
			$(this).data('clicked', false);
			for(var x = 1; x < 7; x++) {
				for(var y = 1; y < 7; y++) {
					var id = "tt-" +x +"-" +y;
					$("#" + id).removeClass("active");
				}
			}
			
			bcc = -1;
		}
	});
	
	$("td[id*='tt-']").on("click", function() {
		var checked = -1;
		if($(this).hasClass('checked-1')) {
			checked = 1;
		}
		else if($(this).hasClass('checked-2')) {
			checked = 2;
		}
		else if($(this).hasClass('checked-3')) {
			checked = 3;
		}
		else if($(this).hasClass('checked-4')) {
			checked = 4;
		}
		else if($(this).hasClass('checked-5')) {
			checked = 5;
		}
		
		console.log(checked);
		if(checked === -1 && $(this).hasClass("active")) { 
			$(this).removeClass("active");	
			if(bcc !== -1)
				$(this).addClass("checked-" +bcc);
		} 
		else if(checked === bcc && bcc !== -1) {
			$(this).removeClass("checked-" +checked);
			$(this).addClass("active");
		}
		else if(checked !== -1 && bcc === -1){
			$(this).removeClass("checked-" +checked);
		}
		else if(checked !== -1 && bcc !== -1) {
			$(this).removeClass("checked-" +checked);
			$(this).addClass("checked-" +bcc);	
		}
	}); 
	
	
	
});
