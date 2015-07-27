$(document).ready(function() {
	data.getUsername = true;
	$.ajax({
		url : "/SpaceWars/login",
		type : "GET",
		data : data,
		success : function(result) {
			
			data.username = result;
			$('#username').text("Commander " + data.username + " ist auf der Brücke");
			//window.location.href = "/" + result;
			clearData();
		},
		error : function(error){
			doError(error);
		}
	});
	//data.username = getUrlParameter("username");
	
});