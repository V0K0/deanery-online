let  enterBtn = $("#buttonEnter");

let login = $(".inputLogin");
let pass = $(".inputPass");

enterBtn.hover(function() {
            $( this ).css("background", "#1A91DA")
        }, function() {
            $( this ).css("background", "dodgerblue")
        });


login.focus(function () {
    $(".login").css("border-bottom", "2px solid dodgerblue");
    $(".login span").css("color", "dodgerblue");
}).blur(function () {
    $(".login").css("border-bottom", "2px solid gray");
    $(".login span").css("color", "gray");
});

pass.focus(function () {
    $(".password").css("border-bottom", "2px solid dodgerblue");
    $(".password span").css("color", "dodgerblue");
}).blur(function () {
    $(".password").css("border-bottom", "2px solid gray");
    $(".password span").css("color", "gray");
});

$(".dataField").on('keyup', function () {
    let isEmpty = false;

    $('.dataField').each(function() {
       if (!$(this).val().trim().length){
           isEmpty = true;
       }
    });

    if (isEmpty){
        $(enterBtn).attr("disabled", true);
    } else{
        $(enterBtn).attr("disabled", false);
    }

});
