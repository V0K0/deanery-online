let regBtn  = $("#buttonReg");
let logBtn = $("#buttonLog");

logBtn.hover(function() {
        $( this ).css("border", "3px solid #fd7e14");
        $( this ).css( "color", "#fd7e14")
    }, function() {
        $( this ).css( "border", "3px solid dodgerblue");
        $( this ).css( "color", "dodgerblue");
    }
);

regBtn.hover(function() {
        $( this ).css("background", "#fd7e14")
    }, function() {
        $( this ).css("background", "dodgerblue")
    }
);

function fadeSlogans(){
    $(".item3").delay(1000).fadeIn('1500');
    $(".item2").delay(1500).fadeIn('2000');
    $(".item1").delay(2000).fadeIn('2500');
}

fadeSlogans();


