$(document).ready(function () {

    let formUpdateStudent= $('#updateDiv');
    let formSearchStudent = $('#searchDiv');
    let searchResultTable = $('.get-by-group-response');

    $("#updateStudentBtn").on('click', function () {
        if ($(formUpdateStudent).css('display') !== 'block'){
            $(formUpdateStudent).slideDown(400, function () {
                console.log('success');
                $(formUpdateStudent).css('display', 'block');
            });
        }

    });

    $("#searchStudentBtn").on('click', function () {
        if ($(formSearchStudent).css('display') !== 'block'){
            $(formSearchStudent).slideDown(400, function () {
                $(formSearchStudent).css('display', 'block');
            });
        }
    });


    $("#declineUpdateStudent").on('click', function () {
        $(formUpdateStudent).slideUp(400, function () {
            $('.form-control').each(function (i, obj) {
                obj.value = "";
            });
            $(formUpdateStudent).css('display', 'none');
        });
    });

    $("#declineSearch").on('click', function () {
        $(formSearchStudent).slideUp(400, function () {
            $('.form-control').each(function (i, obj) {
                obj.value = "";
            });
            $(formSearchStudent).css('display', 'none');
        });
        $(searchResultTable).slideUp(400, function () {
            $('.form-control').each(function (i, obj) {
                obj.value = "";
            });
            $(searchResultTable).css('display', 'none');
        });
    });


});