$(document).ready(function() {
	data.getUsername = true;
	$.ajax({
		url : "/SpaceWars/login",
		type : "GET",
		data : data,
		success : function(result) {
			
			window.location.href = "./" + result;
			clearData();
		}
	});
	data.username = getUrlParameter("username");
	$('#username').text("Commander " + data.username + " ist auf der Brücke")
});