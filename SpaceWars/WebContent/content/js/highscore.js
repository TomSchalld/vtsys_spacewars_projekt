/*var highscoreObject = [ {
	"roundCound" : 0,
	"newBattlestar" : 0,
	"sum" : 0
}, {

} ];*/

function openHighscore() {
	$('#sorting-example').dynatable();
	$('#highscore').show();

}
function closeHighscore() {
	$('#highscore').hide();
}

$(document).ready(function() {
	$('#sorting-example').dynatable();

});
