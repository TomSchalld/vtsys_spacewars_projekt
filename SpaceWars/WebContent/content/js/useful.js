var data={
	"username":"",
	"logout":false,
	"createGame":false,
	"joinGame":false
};
function getUrlParameter(sParam) {
	var sPageURL = window.location.search.substring(1);
	var sURLVariables = sPageURL.split('&');
	for (var i = 0; i < sURLVariables.length; i++) {
		var sParameterName = sURLVariables[i].split('=');
		if (sParameterName[0] == sParam) {
			return sParameterName[1];
		}
	}
}
function createGame() {
	data.createGame=true;
	$.ajax({
		url : "/SpaceWars/login",
		type : "GET",
		data : data,
		success: function(result){
			window.location.href = "./menuMultiplayerCreate.html"+result;
		}
	});
}
function joinGame() {
	data.joinGame=true;
	$.ajax({
		url : "/SpaceWars/login",
		type : "GET",
		data : data,
		success: function(result){
			window.location.href = "./menuMultiplayerJoin.html"+result;
		}
	});
}
function submitUser() {
	data.username = $('#username').val();
	$.ajax({
		url : "/SpaceWars/login",
		type : "POST",
		data : data,
		success: function(result){
			window.location.href = "html/menuMain.html"+result;
		}
	});
}
function logout(){
	data.logout=true;
	$.ajax({
		url : "/SpaceWars/login",
		type : "GET",
		data : data,
		success: function(result){
			window.location.href = "../index.html";
		}
	});
}