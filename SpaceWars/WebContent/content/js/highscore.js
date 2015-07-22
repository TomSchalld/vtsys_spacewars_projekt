/*var highscoreObject = [ {
	"roundCound" : 0,
	"newBattlestar" : 0,
	"sum" : 0
}, {

} ];*/

function openHighscore() {
	data.highscore = true;
	$.ajax({
		url : "/SpaceWars/login",
		type : "GET",
		data : data,
		success : function(result) {
			window.location.href = "./menuHighscore.html";
			console.log(result)
			clearData();
		}
	});
	

}
function closeHighscore() {
	$('#highscore').hide();
}

$(document).ready(function() {
	$('#highscore-table').dynatable();

});
