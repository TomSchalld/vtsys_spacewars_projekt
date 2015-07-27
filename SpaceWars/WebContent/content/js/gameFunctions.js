var roundObject = {
	"atlantis" : {
		"newFighter" : 0,
		"newBattlestar" : 0,
		"sum" : 0
	},
	"caprica" : {
		"newFighter" : 0,
		"newBattlestar" : 0,
		"sum" : 0
	},
	"coruscant" : {
		"newFighter" : 0,
		"newBattlestar" : 0,
		"sum" : 0
	},
	"endor" : {
		"newFighter" : 0,
		"newBattlestar" : 0,
		"sum" : 0
	},
	"erde" : {
		"newFighter" : 0,
		"newBattlestar" : 0,
		"sum" : 0
	},
	"gemini" : {
		"newFighter" : 0,
		"newBattlestar" : 0,
		"sum" : 0
	},
	"tatooine" : {
		"newFighter" : 0,
		"newBattlestar" : 0,
		"sum" : 0
	},
	"fightersInStock" : 0,
	"battlestarsInStock" : 0,
	"fightersToBuy" : 0,
	"battlestarsToBuy" : 0,
	"playersCash" : 0,
	"endReport" : null,
	"roundReport" : null,
	"closeGame" : false
};
var bs = "Battlestar";
var fight = "Fighter";
var priceFighter = 200;
var priceBattlestar = 2000;
var cash = 5000;
function buyFighter() {
	var cashToUse = cash - priceFighter;
	if (cashToUse >= 0) {
		cash -= priceFighter;
		roundObject.fightersToBuy++;
		roundObject.fightersInStock++;
		$('#credits').text(cash + " $");
		$('#fighter').text(roundObject.fightersInStock);
	}
}
function buyBattlestar() {
	var cashToUse = cash - priceBattlestar;
	if (cashToUse >= 0) {
		cash -= priceBattlestar;
		roundObject.battlestarsToBuy++;
		roundObject.battlestarsInStock++;
		$('#credits').text(cash + " $");
		$('#battlestar').text(roundObject.battlestarsInStock);
	}
}
function notBuyFighter() {
	if (roundObject.fightersToBuy > 0 && roundObject.fightersInStock > 0) {
		roundObject.fightersToBuy--;
		roundObject.fightersInStock--;
		cash += priceFighter;
		$('#credits').text(cash + " $");
		$('#fighter').text(roundObject.fightersInStock);
	}
}
function notBuyBattlestar() {
	if (roundObject.battlestarsToBuy > 0 && roundObject.battlestarsInStock > 0) {
		roundObject.battlestarsToBuy--;
		roundObject.battlestarsInStock--;
		cash += priceBattlestar;
		$('#credits').text(cash + " $");
		$('#battlestar').text(roundObject.fightersInStock);
	}
}
function fighterPlus(planet) {
	// TODO
	if (roundObject.fightersInStock > 0) {
		switch (planet) {
		case 'atlantis':
			if (roundObject.atlantis.sum < 5) {
				roundObject.atlantis.newFighter++;
				roundObject.fightersInStock--;
			}
			$('#' + planet + 'Fighter').text(roundObject.atlantis.newFighter);
			break;
		case 'caprica':
			if (roundObject.caprica.sum < 5) {
				roundObject.caprica.newFighter++;
				roundObject.fightersInStock--;
			}
			$('#' + planet + 'Fighter').text(roundObject.caprica.newFighter);
			break;
		case 'coruscant':
			if (roundObject.coruscant.sum < 5) {
				roundObject.coruscant.newFighter++;
				roundObject.fightersInStock--;
			}
			$('#' + planet + 'Fighter').text(roundObject.coruscant.newFighter);
			break;
		case 'endor':
			if (roundObject.endor.sum < 5) {
				roundObject.endor.newFighter++;
				roundObject.fightersInStock--;
			}
			$('#' + planet + 'Fighter').text(roundObject.endor.newFighter);
			break;
		case 'erde':
			if (roundObject.erde.sum < 5) {
				roundObject.erde.newFighter++;
				roundObject.fightersInStock--;
			}
			$('#' + planet + 'Fighter').text(roundObject.erde.newFighter);
			break;
		case 'gemini':
			if (roundObject.gemini.sum < 5) {
				roundObject.gemini.newFighter++;
				roundObject.fightersInStock--;
			}
			$('#' + planet + 'Fighter').text(roundObject.gemini.newFighter);
			break;
		case 'tatooine':
			if (roundObject.tatooine.sum < 5) {
				roundObject.tatooine.newFighter++;
				roundObject.fightersInStock--;
			}
			$('#' + planet + 'Fighter').text(roundObject.tatooine.newFighter);
			break;
		default:
			break;
		}

	}
	$('#fighter').text(roundObject.fightersInStock);
	refresh();
}

function fighterMinus(planet) {
	// TODO
	switch (planet) {
	case 'atlantis':
		if (roundObject.atlantis.newFighter > 0) {
			roundObject.atlantis.newFighter--;
			roundObject.fightersInStock++;
		}
		$('#' + planet + 'Fighter').text(roundObject.atlantis.newFighter);

		break;
	case 'caprica':
		if (roundObject.caprica.newFighter > 0) {
			roundObject.caprica.newFighter--;
			roundObject.fightersInStock++;
		}
		$('#' + planet + 'Fighter').text(roundObject.caprica.newFighter);

		break;
	case 'coruscant':
		if (roundObject.coruscant.newFighter > 0) {
			roundObject.coruscant.newFighter--;
			roundObject.fightersInStock++;
		}
		$('#' + planet + 'Fighter').text(roundObject.coruscant.newFighter);

		break;
	case 'endor':
		if (roundObject.endor.newFighter > 0) {
			roundObject.endor.newFighter--;
			roundObject.fightersInStock++;
		}
		$('#' + planet + 'Fighter').text(roundObject.endor.newFighter);

		break;
	case 'erde':
		if (roundObject.erde.newFighter > 0) {
			roundObject.erde.newFighter--;
			roundObject.fightersInStock++;
		}
		$('#' + planet + 'Fighter').text(roundObject.erde.newFighter);

		break;
	case 'gemini':
		if (roundObject.gemini.newFighter > 0) {
			roundObject.gemini.newFighter--;
			roundObject.fightersInStock++;
		}
		$('#' + planet + 'Fighter').text(roundObject.gemini.newFighter);

		break;
	case 'tatooine':
		if (roundObject.tatooine.newFighter > 0) {
			roundObject.tatooine.newFighter--;
			roundObject.fightersInStock++;
		}
		$('#' + planet + 'Fighter').text(roundObject.tatooine.newFighter);

		break;
	default:
		break;
	}
	$('#fighter').text(roundObject.fightersInStock);
	refresh();
}
function battlestarPlus(planet) {
	// TODO
	if (roundObject.battlestarsInStock > 0) {
		switch (planet) {
		case 'atlantis':
			if (roundObject.atlantis.sum < 5) {
				roundObject.atlantis.newBattlestar++;
				roundObject.battlestarsInStock--;
			}
			$('#' + planet + 'Battlestar').text(
					roundObject.atlantis.newBattlestar);
			break;
		case 'caprica':
			if (roundObject.caprica.sum < 5) {
				roundObject.caprica.newBattlestar++;
				roundObject.battlestarsInStock--;
			}
			$('#' + planet + 'Battlestar').text(
					roundObject.caprica.newBattlestar);
			break;
		case 'coruscant':
			if (roundObject.coruscant.sum < 5) {
				roundObject.coruscant.newBattlestar++;
				roundObject.battlestarsInStock--;
			}
			$('#' + planet + 'Battlestar').text(
					roundObject.coruscant.newBattlestar);
			break;
		case 'endor':
			if (roundObject.endor.sum < 5) {
				roundObject.endor.newBattlestar++;
				roundObject.battlestarsInStock--;
			}
			$('#' + planet + 'Battlestar')
					.text(roundObject.endor.newBattlestar);
			break;
		case 'erde':
			if (roundObject.erde.sum < 5) {
				roundObject.erde.newBattlestar++;
				roundObject.battlestarsInStock--;
			}
			$('#' + planet + 'Battlestar').text(roundObject.erde.newBattlestar);
			break;
		case 'gemini':
			if (roundObject.gemini.sum < 5) {
				roundObject.gemini.newBattlestar++;
				roundObject.battlestarsInStock--;
			}
			$('#' + planet + 'Battlestar').text(
					roundObject.gemini.newBattlestar);
			break;
		case 'tatooine':
			if (roundObject.tatooine.sum < 5) {
				roundObject.tatooine.newBattlestar++;
				roundObject.battlestarsInStock--;
			}
			$('#' + planet + 'Battlestar').text(
					roundObject.tatooine.newBattlestar);
			break;
		default:
			break;
		}

	}
	$('#battlestar').text(roundObject.battlestarsInStock);
	refresh();
}

function battlestarMinus(planet) {
	// TODO
	switch (planet) {
	case 'atlantis':
		if (roundObject.atlantis.newBattlestar > 0) {
			roundObject.atlantis.newBattlestar--;
			roundObject.battlestarsInStock++;
		}
		$('#' + planet + 'Battlestar').text(roundObject.atlantis.newBattlestar);

		break;
	case 'caprica':
		if (roundObject.caprica.newBattlestar > 0) {
			roundObject.caprica.newBattlestar--;
			roundObject.battlestarsInStock++;
		}
		$('#' + planet + 'Battlestar').text(roundObject.caprica.newBattlestar);

		break;
	case 'coruscant':
		if (roundObject.coruscant.newBattlestar > 0) {
			roundObject.coruscant.newBattlestar--;
			roundObject.battlestarsInStock++;
		}
		$('#' + planet + 'Battlestar')
				.text(roundObject.coruscant.newBattlestar);

		break;
	case 'endor':
		if (roundObject.endor.newBattlestar > 0) {
			roundObject.endor.newBattlestar--;
			roundObject.battlestarsInStock++;
		}
		$('#' + planet + 'Battlestar').text(roundObject.endor.newBattlestar);

		break;
	case 'erde':
		if (roundObject.erde.newBattlestar > 0) {
			roundObject.erde.newBattlestar--;
			roundObject.battlestarsInStock++;
		}
		$('#' + planet + 'Battlestar').text(roundObject.erde.newBattlestar);

		break;
	case 'gemini':
		if (roundObject.gemini.newBattlestar > 0) {
			roundObject.gemini.newBattlestar--;
			roundObject.battlestarsInStock++;
		}
		$('#' + planet + 'Battlestar').text(roundObject.gemini.newBattlestar);

		break;
	case 'tatooine':
		if (roundObject.tatooine.newBattlestar > 0) {
			roundObject.tatooine.newBattlestar--;
			roundObject.battlestarsInStock++;
		}
		$('#' + planet + 'Battlestar').text(roundObject.tatooine.newBattlestar);

		break;
	default:
		break;
	}
	$('#battlestar').text(roundObject.battlestarsInStock);
	refresh();

}

function init() {
	roundObject.playersCash = cash;
	roundObject.fightersToBuy = 0;
	roundObject.battlestarsToBuy = 0;
	var i = 0;
	$.each(roundObject, function(key, val) {
		if (i < 7) {
			i++;
			$("#" + key + bs).text(val.newBattlestar);
			$("#" + key + fight).text(val.newFighter);
		}
	});
	$('#credits').text(roundObject.playersCash + " $");
	$('#fighter').text(0);
	$('#battlestar').text(0);
}
function refresh() {
	var i = 0;
	$.each(roundObject, function(key, val) {
		if (i < 7) {
			i++;
			val.newBattlestar = parseInt(val.newBattlestar);
			val.newFighter = parseInt(val.newFighter);
			val.sum = val.newBattlestar + val.newFighter;
		}
	});
}
function setValues(result) {
	roundObject = result;
	refresh();
	$('#atlantisFighter').text(roundObject.atlantis.newFighter);
	$('#atlantisBattlestar').text(roundObject.atlantis.newBattlestar);
	$('#capricaFighter').text(roundObject.caprica.newFighter);
	$('#capricaBattlestar').text(roundObject.caprica.newBattlestar);
	$('#coruscantFighter').text(roundObject.coruscant.newFighter);
	$('#coruscantBattlestar').text(roundObject.coruscant.newBattlestar);
	$('#endorFighter').text(roundObject.endor.newFighter);
	$('#endorBattlestar').text(roundObject.endor.newBattlestar);
	$('#erdeFighter').text(roundObject.erde.newFighter);
	$('#erdeBattlestar').text(roundObject.erde.newBattlestar);
	$('#geminiFighter').text(roundObject.gemini.newFighter);
	$('#geminiBattlestar').text(roundObject.gemini.newBattlestar);
	$('#tatooineFighter').text(roundObject.tatooine.newFighter);
	$('#tatooineBattlestar').text(roundObject.tatooine.newBattlestar);
	var index = 0;
	var defeatedShipsList = "";

	/** ********Report Atlantis ********** */
	if (roundObject.roundReport.atlantis != null) {
		closeWait()
		$('#winnerAtlantis').text(roundObject.roundReport.atlantis.winner);
		$('#fightersLeftAtlantis').text(
				roundObject.roundReport.atlantis.fighterLeft);
		$('#battlestarsLeftAtlantis').text(
				roundObject.roundReport.atlantis.battlestarsLeft);
		$('#defeatedListAtlantis').text("");
		for (index = 0; index < roundObject.roundReport.atlantis.defeatedShips.length; index++) {
			defeatedShipsList += '<li>';
			defeatedShipsList += roundObject.roundReport.atlantis.defeatedShips[index];
			defeatedShipsList += '</li>';
		}
		$('#defeatedListAtlantis').append(defeatedShipsList);
		$('#atlantisReportButton').show();
		defeatedShipsList = "";

	} else {
		closeWait()
		$('#atlantisReportButton').hide();

	}

	/** ********Report Erde ********** */
	if (roundObject.roundReport.erde != null) {
		closeWait()
		$('#winnerErde').text(roundObject.roundReport.erde.winner);
		$('#fightersLeftErde').text(roundObject.roundReport.erde.fighterLeft);
		$('#battlestarsLeftErde').text(
				roundObject.roundReport.erde.battlestarsLeft);
		$('#defeatedListErde').text("");
		for (index = 0; index < roundObject.roundReport.erde.defeatedShips.length; index++) {
			defeatedShipsList += '<li>';
			defeatedShipsList += roundObject.roundReport.erde.defeatedShips[index];
			defeatedShipsList += '</li>';
		}
		$('#defeatedListErde').append(defeatedShipsList);
		$('#erdeReportButton').show();
		defeatedShipsList = "";

	} else {
		closeWait()
		$('#erdeReportButton').hide();

	}

	/** ********Report Tatooine ********** */
	if (roundObject.roundReport.tatooine != null) {
		closeWait()
		$('#winnerTatooine').text(roundObject.roundReport.tatooine.winner);
		$('#fightersLeftTatooine').text(
				roundObject.roundReport.tatooine.fighterLeft);
		$('#battlestarsLeftTatooine').text(
				roundObject.roundReport.tatooine.battlestarsLeft);
		$('#defeatedListTatooine').text("");
		for (index = 0; index < roundObject.roundReport.tatooine.defeatedShips.length; index++) {
			defeatedShipsList += '<li>';
			defeatedShipsList += roundObject.roundReport.tatooine.defeatedShips[index];
			defeatedShipsList += '</li>';
		}
		$('#defeatedListTatooine').append(defeatedShipsList);
		$('#tatooineReportButton').show();
		defeatedShipsList = "";
	} else {
		closeWait()
		$('#tatooineReportButton').hide();

	}

	/** ********Report Coruscant ********** */
	if (roundObject.roundReport.coruscant != null) {
		closeWait()
		$('#winnerCoruscant').text(roundObject.roundReport.coruscant.winner);
		$('#fightersLeftCoruscant').text(
				roundObject.roundReport.coruscant.fighterLeft);
		$('#battlestarsLeftCoruscant').text(
				roundObject.roundReport.coruscant.battlestarsLeft);
		$('#defeatedListCoruscant').text("");
		for (index = 0; index < roundObject.roundReport.coruscant.defeatedShips.length; index++) {
			defeatedShipsList += '<li>';
			defeatedShipsList += roundObject.roundReport.coruscant.defeatedShips[index];
			defeatedShipsList += '</li>';
		}
		$('#defeatedListCoruscant').append(defeatedShipsList);
		$('#coruscantReportButton').show();
		defeatedShipsList = "";
	} else {
		closeWait()
		$('#coruscantReportButton').hide();

	}

	/** ********Report Gemini ********** */
	if (roundObject.roundReport.gemini != null) {
		closeWait()
		$('#winnerGemini').text(roundObject.roundReport.gemini.winner);
		$('#fightersLeftGemini').text(
				roundObject.roundReport.gemini.fighterLeft);
		$('#battlestarsLeftGemini').text(
				roundObject.roundReport.gemini.battlestarsLeft);
		$('#defeatedListGemini').text("");
		for (index = 0; index < roundObject.roundReport.gemini.defeatedShips.length; index++) {
			defeatedShipsList += '<li>';
			defeatedShipsList += roundObject.roundReport.gemini.defeatedShips[index];
			defeatedShipsList += '</li>';
		}
		$('#defeatedListGemini').append(defeatedShipsList);
		$('#geminiReportButton').show();
		defeatedShipsList = "";
	} else {
		closeWait()
		$('#geminiReportButton').hide();

	}

	/** ********Report Caprica ********** */
	if (roundObject.roundReport.caprica != null) {
		closeWait()
		$('#winnerCaprica').text(roundObject.roundReport.caprica.winner);
		$('#fightersLeftCaprica').text(
				roundObject.roundReport.caprica.fighterLeft);
		$('#battlestarsLeftCaprica').text(
				roundObject.roundReport.caprica.battlestarsLeft);
		$('#defeatedListACaprica').text("");
		for (index = 0; index < roundObject.roundReport.caprica.defeatedShips.length; index++) {
			defeatedShipsList += '<li>';
			defeatedShipsList += roundObject.roundReport.caprica.defeatedShips[index];
			defeatedShipsList += '</li>';
		}
		$('#defeatedListCaprica').append(defeatedShipsList);
		$('#capricaReportButton').show();
		defeatedShipsList = "";

	} else {
		closeWait()
		$('#capricaReportButton').hide();

	}

	/** ********Report Endor ********** */
	if (roundObject.roundReport.endor != null) {
		closeWait()
		$('#winnerEndor').text(roundObject.roundReport.endor.winner);
		$('#fightersLeftEndor').text(roundObject.roundReport.endor.fighterLeft);
		$('#battlestarsLeftEndor').text(
				roundObject.roundReport.endor.battlestarsLeft);
		$('#defeatedListEndor').text("");
		for (index = 0; index < roundObject.roundReport.endor.defeatedShips.length; index++) {
			defeatedShipsList += '<li>';
			defeatedShipsList += roundObject.roundReport.endor.defeatedShips[index];
			defeatedShipsList += '</li>';
		}
		$('#defeatedListEndor').append(defeatedShipsList);
		$('#endorReportButton').show();
		defeatedShipsList = "";

	} else {
		closeWait()
		$('#endorReportButton').hide();

	}

	cash = roundObject.playersCash;

	$('#fighter').text(roundObject.fightersInStock);
	$('#battlestar').text(roundObject.battlestarsInStock);
	$('#credits').text(cash + " $");

}

function noFight() {
	if (roundObject.roundReport == null) {
		$('#noFight').show();
	} else {
		if (roundObject.roundReport.atlantis == null
				&& roundObject.roundReport.caprica == null
				&& roundObject.roundReport.coruscant == null
				&& roundObject.roundReport.endor == null
				&& roundObject.roundReport.erde == null
				&& roundObject.roundReport.gemini == null
				&& roundObject.roundReport.tatooine == null) {
			$('#noFight').show();

		} else {
			$('#noFight').hide();
			$('.tab-content').show();
		}
	}
}
function finishGame() {
	roundObject.closeGame = true;
	$.ajax({
		url : "/SpaceWars/gaming",
		type : "POST",
		data : roundObject,
		success : function(result) {
			window.location.href = "./menuMain.html" + result;
			// clearData();
		},
		error : function(error){
			doError(error);
		}
	});
}
function endRound() {
	openWait();
	$.ajax({
		url : "/SpaceWars/gaming",
		type : "POST",
		data : roundObject,
		success : function(result) {
			console.log(result)
			setValues(result);
			openReport();
		},
		error : function(error){
			doError(error);
		}
	});

}

function openReport() {
	$('.tab-content').hide();
	noFight();
	$('#report').show();
	centerPopup();

}
function closeReport() {
	centerPopup();
	$('#report').hide();
	$('.tab-pane').removeClass('active');
	$('.repButton').removeClass('active');
	if (roundObject.endReport === "null") {
	} else {
		centerPopup();
		$('#endRoundButton').attr('disabled', 'disabled');
		$('#openShopButton').attr('disabled', 'disabled');
		$('#openReportButton').attr('disabled', 'disabled');

		$('#endreport').show();

		$('#winner-text').text(
				roundObject.endReport.winner + " nur  "
						+ roundObject.endReport.roundCount
						+ " Runden zum Sieg, Herzlichen Glückwunsch!");

	}
}

$(document).ready(function() {
	init();
});
