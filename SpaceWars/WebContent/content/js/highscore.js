function openHighscore() {
	
	window.location.href = "./menuHighscore.html";
	

}

function closeHighscore() {
	$('#highscore').hide();
}

$(document).ready(function() {
	
	data.highscore = true;
	$.ajax({
		url : "/SpaceWars/login",
		type : "GET",
		data : data,
		success : function(result) {
			console.log(result);
			$('#highscore-table').dynatable();
			clearData();
		}
	});
});
