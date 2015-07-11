var roundObject = {
	"atlantis" : {
		"newFighter" : 0,
		"newBattlestar" : 0
	},
	"caprica" : {
		"newFighter" : 0,
		"newBattlestar" : 0
	},
	"coruscant" : {
		"newFighter" : 0,
		"newBattlestar" : 0
	},
	"endor" : {
		"newFighter" : 0,
		"newBattlestar" : 0
	},
	"erde" : {
		"newFighter" : 0,
		"newBattlestar" : 0
	},
	"gemini" : {
		"newFighter" : 0,
		"newBattlestar" : 0
	},
	"tatooine" : {
		"newFighter" : 0,
		"newBattlestar" : 0
	},
	"fightersToBuy" : 0,
	"battlestarsToBuy" : 0,
	"playersCash" : 0
};
var bs = "Battlestar";
var fight = "Fighter";
var priceFighter = 200;
var priceBattlestar = 2000;
var cash = 5000;
function buyFighter() {
	var cashToUse = roundObject.playersCash - priceFighter;
	if (cashToUse > 0) {
		roundObject.playersCash -= priceFighter;
		roundObject.fightersToBuy++;
		$('#credits').text(roundObject.playersCash + " $");
		$('#fighter').text(roundObject.fightersToBuy);
	}
}
function buyBattlestar() {
	var cashToUse = roundObject.playersCash - priceBattlestar;
	if (cashToUse > 0) {
		roundObject.playersCash -= priceBattlestar;
		roundObject.battlestarsToBuy++;
		$('#credits').text(roundObject.playersCash + " $");
		$('#battlestar').text(roundObject.battlestarsToBuy);
	}
}
function notBuyFighter(){
	if(roundObject.fightersToBuy>0){
		roundObject.fightersToBuy--;
		roundObject.playersCash+=priceFighter;
		$('#credits').text(roundObject.playersCash + " $");
		$('#fighter').text(roundObject.fightersToBuy);
	}
}
function notBuyBattlestar(){
	if(roundObject.battlestarsToBuy>0){
		roundObject.battlestarsToBuy--;
		roundObject.playersCash+=priceBattlestar;
		$('#credits').text(roundObject.playersCash + " $");
		$('#battlestar').text(roundObject.battlestarsToBuy);
	}
}
function fighterPlus(planet) {

	$('#credits').text(roundObject.playersCash + " $");
	switch (planet) {
	case 'atlantis':
		roundObject.atlantis.newFighter++;
		break;
	case 'caprica':
		roundObject.caprica.newFighter++;
		break;
	case 'coruscant':
		roundObject.coruscant.newFighter++;
		break;
	case 'endor':
		roundObject.endor.newFighter++;
		break;
	case 'erde':
		roundObject.erde.newFighter++;
		break;
	case 'gemini':
		roundObject.gemini.newFighter++;
		break;
	case 'tatooine':
		roundObject.tatooine.newFighter++;
		break;
	default:
		break;
	}
}

function fighterMinus(planet) {
	switch (planet) {
	case 'atlantis':
		roundObject.atlantis.newFighter--;
		break;
	case 'caprica':
		roundObject.caprica.newFighter--;
		break;
	case 'coruscant':
		roundObject.coruscant.newFighter--;
		break;
	case 'endor':
		roundObject.endor.newFighter--;
		break;
	case 'erde':
		roundObject.erde.newFighter--;
		break;
	case 'gemini':
		roundObject.gemini.newFighter--;
		break;
	case 'tatooine':
		roundObject.tatooine.newFighter--;
		break;
	default:
		break;
	}
}
function battlestarPlus(planet) {
	var cashToUse = cash - priceBattlestar;
	if (cashToUse > 0) {
		cash -= priceBattlestar;
		roundObject.playersCash = cash;
		$('#credits').text(roundObject.playersCash + " $");
		switch (planet) {
		case 'atlantis':
			roundObject.atlantis.newBattlestar++;
			break;
		case 'caprica':
			roundObject.caprica.newBattlestar++;
			break;
		case 'coruscant':
			roundObject.coruscant.newBattlestar++;
			break;
		case 'endor':
			roundObject.endor.newBattlestar++;
			break;
		case 'erde':
			roundObject.erde.newBattlestar++;
			break;
		case 'gemini':
			roundObject.gemini.newBattlestar++;
			break;
		case 'tatooine':
			roundObject.tatooine.newBattlestar++;
			break;
		default:
			break;
		}
	}

}
function battlestarMinus(planet) {
	switch (planet) {
	case 'atlantis':
		roundObject.atlantis.newBattlestar--;
		break;
	case 'caprica':
		roundObject.caprica.newFighter--;
		break;
	case 'coruscant':
		roundObject.coruscant.newBattlestar--;
		break;
	case 'endor':
		roundObject.endor.newBattlestar--;
		break;
	case 'erde':
		roundObject.erde.newBattlestar--;
		break;
	case 'gemini':
		roundObject.gemini.newBattlestar--;
		break;
	case 'tatooine':
		roundObject.tatooine.newBattlestar--;
		break;
	default:
		break;
	}
}
function init() {
	roundObject.playersCash = cash;
	roundObject.fightersToBuy = 0;
	roundObject.battlestarsToBuy = 0;
	var i = 0;
	$.each(roundObject, function(key, val) {
		if (i < 7) {
			i++
			$("#" + key + bs).text(val.newBattlestar);
			$("#" + key + fight).text(val.newFighter);
		}
	});
	$('#credits').text(roundObject.playersCash + " $");
	$('#fighter').text('0');
	$('#battlestar').text('0');
}
function setValues() {

}
$(document).ready(function() {
	init();
});
