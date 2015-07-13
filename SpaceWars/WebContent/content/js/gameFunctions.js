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
	"roundReport":null
};
var bs = "Battlestar";
var fight = "Fighter";
var priceFighter = 200;
var priceBattlestar = 2000;
var cash = 5000;
function buyFighter() {
	var cashToUse = roundObject.playersCash - priceFighter;
	if (cashToUse >= 0) {
		roundObject.playersCash -= priceFighter;
		roundObject.fightersToBuy++;
		roundObject.fightersInStock++;
		$('#credits').text(roundObject.playersCash + " $");
		$('#fighter').text(roundObject.fightersToBuy);
	}
}
function buyBattlestar() {
	var cashToUse = roundObject.playersCash - priceBattlestar;
	if (cashToUse >= 0) {
		roundObject.playersCash -= priceBattlestar;
		roundObject.battlestarsToBuy++;
		roundObject.battlestarsInStock++;
		$('#credits').text(roundObject.playersCash + " $");
		$('#battlestar').text(roundObject.battlestarsToBuy);
	}
}
function notBuyFighter() {
	if (roundObject.fightersToBuy > 0) {
		roundObject.fightersToBuy--;
		roundObject.fightersInStock--;
		roundObject.playersCash += priceFighter;
		$('#credits').text(roundObject.playersCash + " $");
		$('#fighter').text(roundObject.fightersToBuy);
	}
}
function notBuyBattlestar() {
	if (roundObject.battlestarsToBuy > 0) {
		roundObject.battlestarsToBuy--;
		roundObject.battlestarsInStock--;
		roundObject.playersCash += priceBattlestar;
		$('#credits').text(roundObject.playersCash + " $");
		$('#battlestar').text(roundObject.battlestarsToBuy);
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
	$('#fighter').text('0');
	$('#battlestar').text('0');
}
function refresh() {
	var i = 0;
	$.each(roundObject, function(key, val) {
		if (i < 7) {
			i++;
			val.sum = val.newBattlestar + val.newFighter;
		}
	});
}
function setValues() {

}
function endRound() {

	$.ajax({
		url : "/SpaceWars/gaming",
		type : "POST",
		//mimeType:"text/plain",
		data : roundObject,
		success : function(result) {
			alert("Success");
			console.log(result)
		}
	});

	console.log(roundObject);
}
$(document).ready(function() {
	init();
});