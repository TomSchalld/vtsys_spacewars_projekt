function setMarginsShop() {
	width = $(window).width();
	containerWidth = $("#shop").width();
	leftMargin = (width - containerWidth) / 2;
	$("#shop").css("marginLeft", leftMargin);

}
function setMarginsReport() {
	width = $(window).width();
	containerWidth = $("#report").width();
	leftMargin = (width - containerWidth) / 2;
	$("#report").css("marginLeft", leftMargin);

}
function setMarginsWait() {
	width = $(window).width();
	containerWidth = $("#wait").width();
	leftMargin = (width - containerWidth) / 2;
	$("#wait").css("marginLeft", leftMargin);
}
function setMarginsHighscore() {
	width = $(window).width();
	containerWidth = $("#highscore").width();
	leftMargin = (width - containerWidth) / 2;
	$("#highscore").css("marginLeft", leftMargin);
}
function setMarginsEndreport() {
	width = $(window).width();
	containerWidth = $("#endreport").width();
	leftMargin = (width - containerWidth) / 2;
	$("#endreport").css("marginLeft", leftMargin);
}

function centerPopup() {
	setMarginsShop();
	setMarginsReport();
	setMarginsWait();
	setMarginsHighscore();
	$(window).resize(function() {
		setMarginsShop();
		setMarginsReport();
		setMarginsWait();
		setMarginsHighscore();
		setMarginsEndreport();
	});
}

$(document).ready(function() {
	centerPopup();
	$( ".repButton" ).bind( "click", function() {
		 centerPopup();
		});
});