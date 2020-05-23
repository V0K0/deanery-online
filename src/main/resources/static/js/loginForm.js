let enterBtn = $("#buttonEnter");
let confirmPass = $(".inputConfirmPass");
let pass = $(".inputPass");

enterBtn.hover(function () {
    $(this).removeClass("enabledBtn").addClass("hoverOnBtn");
}, function () {
    $(this).removeClass("hoverOnBtn").addClass("enabledBtn");
});

$("#regForm").submit(function (event) {
    let warn = $("#alert");
    if (pass.val() === confirmPass.val()) {
        $(warn).css("display", "none");
        return;
    }
    $(warn).css("display", "inline-block").text("Паролі повинні збігатися!");
    event.preventDefault();

});


$(".inputDiv").focusin(function() {
    $(this).css("border-bottom", "2px solid dodgerblue");
    $(this).find('span').css("color", "dodgerblue");
}).focusout(function () {
    $(this).css("border-bottom", "2px solid gray");
    $(this).find('span').css("color", "gray");

});

$(".dataField").on('keyup', function () {
    let isEmpty = false;

    $('.dataField').each(function () {
        if (!$(this).val().trim().length) {
            isEmpty = true;
        }
    });

    if (isEmpty) {
        $(enterBtn).attr("disabled", true);
        $(enterBtn).removeClass("enabledBtn hoverOnBtn").addClass("disabledBtn");
    } else {
        $(enterBtn).attr("disabled", false);
        $(enterBtn).removeClass("disabledBtn").addClass("enabledBtn");
    }

});


