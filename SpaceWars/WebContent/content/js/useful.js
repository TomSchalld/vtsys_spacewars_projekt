var data = {
	"username" : "",
	"logout" : false,
	"createGame" : false,
	"joinGame" : false,
	"getGames" : false,
	"gameData" : null,
	"gameName" : "",
	"gamePw" : "",
	"race" : "",
	"universeSize" : 0,
	"gameMode" : 2
};

function openShop() {
	$('#shop').show();

}
function closeShop() {
	$('#shop').hide();
}
function openReport() {
	$('#report').show();

}
function closeReport() {
	$('#report').hide();
}

function chooseUniverse(size) {
	data.universeSize = size;
	data.createGame = true;
	data.gameName = getUrlParameter("gameName");
	data.gameMode = 4;
	$.ajax({
		url : "/SpaceWars/login",
		type : "GET",
		data : data,
		success : function(result) {
			window.location.href = "./menuTeam.html" + result;
			clearData();
		}
	});
}
function startPVPC() {
	var date = new Date();
	data.createGame = true;
	data.gameMode = 1;
	data.gameName = data.username + date.getTime();
	data.universeSize = 3;
	$.ajax({
		url : "/SpaceWars/login",
		type : "GET",
		data : data,
		success : function(result) {

			window.location.href = "./gameSeven.html" + result;
			clearData();
		}
	});

}
function startPVP() {
	data.createGame = true;
	data.gameName = getUrlParameter("gameName");
	data.universeSize = getUrlParameter("universeSize");
	data.gameMode = 0;
	$.ajax({
		url : "/SpaceWars/login",
		type : "GET",
		data : data,
		success : function(result) {
			alert(result);
			window.location.href = "./" + result;
			clearData();
		}
	});

}

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
	data.gameName = $('#gameName').val();
	data.gameMode = 4
	$.ajax({
		url : "/SpaceWars/login",
		type : "GET",
		data : data,
		success : function(result) {
			window.location.href = "./menuKarte.html" + result;
			clearData();
		}
	});
}
function openCreateGame() {
	// data.createGame=true;

	window.location.href = "./menuMultiplayerCreate.html" + "?username="
			+ data.username;

}
function joinGame() {

	window.location.href = "./menuMultiplayerJoin.html" + "?username="
			+ data.username;
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
function getListOfOpenGames() {
	data.getGames = true;
	$.ajax({
		url : "/SpaceWars/login",
		type : "GET",
		data : data,
		success : function(result) {
			data.gameData = result;
			console.log(data.gameData);
			setListOfOpenGames(result);
		}
	});
}
function setListOfOpenGames(result) {
	
	$.each(result,function(key, val){
		$('#tableOfOpenGames')
		.append(
				"<tr><td>"+val.gameName+"</td><td>"+val.host+"</td><td>"+val.gameMode+"</td><td>"+val.universeSize+"</td>" +
				"<td><button class='btn btn-default btn-sm' type='button' onclick=\"joinIt('"+val.gameName+"')\">Beitreten</button></td></tr>");
	});
	
}
function joinIt(gameName){
	data.joinGame = true;
	data.gameName = gameName;
	$.ajax({
		url : "/SpaceWars/login",
		type : "GET",
		data : data,
		success : function(result) {
			window.location.href = "./gameSeven.html" + result;
			clearData();
		}
	});
}
function clearData() {
	data.logout = false;
	data.createGame = false;
	data.joinGame = false;
}
function chooseRace(race) {
	data.race = race;
	window.location.href = "./menuKarte.html" + "?username=" + data.username;

}
