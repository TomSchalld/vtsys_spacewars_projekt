function setMargins() {
    width = $(window).width();
    containerWidth = $("#shop").width();  
    leftMargin = (width-containerWidth)/2;    
    $("#shop").css("marginLeft", leftMargin);    
    $("#report").css("marginLeft", leftMargin);   
    $("#wait").css("marginLeft", leftMargin);
}

$(document).ready(function() {
    setMargins();
    $(window).resize(function() {
        setMargins();    
    });
});