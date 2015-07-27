var data = {
	"username" : "",
	"logout" : false,
	"createGame" : false,
	"getUsername" : false,
	"joinGame" : false,
	"getGames" : false,
	"gameData" : null,
	"highscore" : false,
	"gameName" : "",
	"gamePw" : "",
	"race" : "",
	"universeSize" : 0,
	"gameMode" : 2
};

function openShop() {
	centerPopup();
	$('#shop').show();

}
function closeShop() {
	$('#shop').hide();
}

function openWait() {
	$('#endRoundButton').attr('disabled', 'disabled');
	noFight();
	$('#wait').show();
}
function closeWait() {
	$('#wait').hide();
	$('#endRoundButton').removeAttr('disabled');
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
		},
		error : function(error) {
			doError(error);
		}
	});
}
function startPPVPC() {
	data.createGame = true;
	data.gameName = getUrlParameter("gameName");
	data.universeSize = getUrlParameter("universeSize");
	data.gameMode = 2;
	$.ajax({
		url : "/SpaceWars/login",
		type : "GET",
		data : data,
		success : function(result) {
			// alert(result);
			window.location.href = "./" + result;
			clearData();
		},
		error : function(error) {
			doError(error);
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

			// window.location.href = "./gameSeven.html" + result;
			window.location.href = "./" + result;
			clearData();
		},
		error : function(error) {
			doError(error);
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
			// alert(result);
			window.location.href = "./" + result;
			clearData();
		},
		error : function(error) {
			doError(error);
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
		},
		error : function(error) {
			doError(error);
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
function showManual() {

	window.location.href = "./menuManual.html" + "?username=" + data.username;
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
		},
		error : function(error) {
			doError(error);
		}
	});
}
function backToMenu() {

	window.location.href = "./menuMain.html";


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
		},
		error : function(error) {
			doError(error);
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
			clearData();

		},
		error : function(error) {
			doError(error);
		}
	});
}
function setListOfOpenGames(result) {

	$
			.each(
					result,
					function(key, val) {
						$('#tableOfOpenGames')
								.append(
										"<tr><td>"
												+ val.gameName
												+ "</td><td>"
												+ val.host
												+ "</td><td>"
												+ val.gameMode
												+ "</td><td>"
												+ val.universeSize
												+ "</td>"
												+ "<td><button class='btn btn-default btn-sm' type='button' onclick=\"joinIt('"
												+ val.gameName
												+ "')\">Beitreten</button></td></tr>");
					});

}
function joinIt(gameName) {
	// alert("Join Game");
	data.joinGame = true;
	data.gameName = gameName;
	$.ajax({
		url : "/SpaceWars/login",
		type : "GET",
		data : data,
		success : function(result) {
			window.location.href = "./" + result;
			clearData();
		},
		error : function(error) {
			doError(error);
		}
	});
}
function clearData() {
	data.logout = false;
	data.createGame = false;
	data.joinGame = false;
	data.getGames = false;
	data.highscore = false;
	data.getUsername = false;
}
function chooseRace(race) {
	data.race = race;
	window.location.href = "./menuKarte.html" + "?username=" + data.username;

}
function doError(error) {


	if (error.status === 1000) {
		alert("Einloggen fehlgeschlagen, Sie sind bereits angemeldet!");
		clearData();
		window.location.href = "html/menuMain.html";
	} else if (error.status === 1001) {
		alert("Sie dürfen hier nicht sein, Sie sind nicht eingeloggt!");
		clearData();
		window.location.href = "../index.html";

	} else {
		alert("unknown error " + error.status);
	}

}

