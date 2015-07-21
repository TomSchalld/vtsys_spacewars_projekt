function setMarginsShop() {
    width = $(window).width();
    containerWidth = $("#shop").width();  
    leftMargin = (width-containerWidth)/2;    
    $("#shop").css("marginLeft", leftMargin);    
    
}
function setMarginsReport() {
    width = $(window).width();
    containerWidth = $("#report").width();  
    leftMargin = (width-containerWidth)/2;    
    $("#report").css("marginLeft", leftMargin);   
   
}
function setMarginsWait() {
    width = $(window).width();
    containerWidth = $("#wait").width();  
    leftMargin = (width-containerWidth)/2;    
    $("#wait").css("marginLeft", leftMargin);
}

$(document).ready(function() {
	setMarginsShop();
	setMarginsReport();
	setMarginsWait();
    $(window).resize(function() {
    	setMarginsShop();
    	setMarginsReport();
    	setMarginsWait(); 
    });
});