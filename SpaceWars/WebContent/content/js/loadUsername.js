$(document).ready(function() {

	$.ajax({
		url : "/SpaceWars/login",
		type : "GET",
		data : {
			"getUsername" : true
		},
		success : function(result) {
			window.location.href = "./" + result;
			clearData();
		}
	});
	data.username = getUrlParameter("username");
	$('#username').text("Commander " + data.username + " ist auf der Brücke")
});