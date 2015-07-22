function openHighscore() {

	window.location.href = "./menuHighscore.html";

}

function closeHighscore() {
	$('#highscore').hide();
}
function fillHighscoreTable(result) {
	$.each(result, function(key, val) {
		$('#highscoreContent').append(
				"<tr><td>" + val.gameName + "</td>" + "<td>" + val.datum
						+ "</td><td>" + val.winner + "</td><td>"
						+ val.roundCount + "</td><td>" + val.defeatedShipCount
						+ "</td></tr>");
	});
}
$(document).ready(function() {

	data.highscore = true;
	$.ajax({
		url : "/SpaceWars/login",
		type : "GET",
		data : data,
		success : function(result) {
			console.log(result);
			fillHighscoreTable(result);
			$('#highscore-table').dynatable();
			clearData();
		}
	});
});
