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
var priceFighter=200;
var priceBattlestar =2000;
var cash;
function fighterPlus(planet) {
	
	var string = planet+fight;
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
function fighterMinus(planet) {
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
function init(){
	roundObject.playersCash = 5000;
	roundObject.fightersToBuy = 0;
	roundObject.battlestarsToBuy = 0;
	$('#credits').text(roundObject.playersCash+" $");
	$('#fighter').text('0');
	$('#battlestar').text('0');
}
function setValues(){
	
}
$(document).ready(function() {
    init();
});







