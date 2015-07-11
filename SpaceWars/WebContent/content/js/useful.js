var data = {
	"username" : "",
	"logout" : false,
	"createGame" : false,
	"joinGame" : false,
	"game":null
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
	data.createGame = true;
	var gameName =$('#gameName').val();
	var pw = $('#inputPassword').val();
	data.game = {
			"gameName":gameName,
			"password":pw
	};
	$.ajax({
		url : "/SpaceWars/login",
		type : "GET",
		data : data,
		success : function(result) {
			window.location.href = "./menuRasse.html" + result;
			clearData();
		}
	});
}
function openCreateGame() {
	// data.createGame=true;

	window.location.href = "./menuMultiplayerCreate.html" + "?username="+data.username;

}
function joinGame() {
	data.joinGame = true;
	
	$.ajax({
		url : "/SpaceWars/login",
		type : "GET",
		data : data,
		success : function(result) {
			window.location.href = "./menuMultiplayerJoin.html" + result;
			clearData();
		}
	});
}
function submitUser() {
	data.username = $('#username').val();
	$.ajax({
		url : "/SpaceWars/login",
		type : "POST",
		data : data,
		success : function(result) {
			window.location.href = "html/menuMain.html" + result;
			clearData();
		}
	});
}
function logout() {
	data.logout = true;
	$.ajax({
		url : "/SpaceWars/login",
		type : "GET",
		data : data,
		success : function(result) {
			window.location.href = "../index.html";
			clearData();
		}
	});
}
function clearData(){
	data.logout = false;
	data.createGame = false;
	data.joinGame=false;
}