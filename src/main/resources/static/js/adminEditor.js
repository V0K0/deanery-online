$(document).ready(function () {

    let formCreate = $('#createDiv');
    let formUpdate = $('#updateDiv');
    let formDelete = $('#deleteDiv');
    let formSearch = $('#searchDiv');
    let searchResultTable = $('.search-response');
    let badSearchFeedBack400 = $('.invalid-feedback-400');


    $(".create").on('click', function () {
        if ($(formCreate).css('display') !== 'block'){
            $(formCreate).slideDown(400, function () {
                $(formCreate).css('display', 'block');
            });
        }
    });

    $(".update").on('click', function () {
        if ($(formUpdate).css('display') !== 'block'){
            $(formUpdate).slideDown(400, function () {
                console.log('success');
                $(formUpdate).css('display', 'block');
            });
        }
    });

    $(".delete").on('click', function () {
        if ($(formDelete).css('display') !== 'block'){
            $(formDelete).slideDown(400, function () {
                $(formDelete).css('display', 'block');
            });
        }
    });

    $(".searchBtn").on('click', function () {
        if ($(formSearch).css('display') !== 'block'){
            $(formSearch).slideDown(400, function () {
                $(formSearch).css('display', 'block');
            });
        }
    });

    $(".declineUpdate").on('click', function () {
        $(formUpdate).slideUp(400, function () {
            $('.form-control').each(function (i, obj) {
                obj.value = "";
            });
            $(formUpdate).css('display', 'none');
        });
    });

    $(".declineCreate").on('click', function () {
        $(formCreate).slideUp(400, function () {
            $('.form-control').each(function (i, obj) {
                obj.value = "";
            });
            $(formCreate).css('display', 'none');
        });
    });

    $(".declineDelete").on('click', function () {
        $(formDelete).slideUp(400, function () {
            $('.form-control').each(function (i, obj) {
                obj.value = "";
            });
            $(formDelete).css('display', 'none');
        });
    });

    $(".declineSearch").on('click', function () {
        $(formSearch).slideUp(400, function () {
            $('.form-control').each(function (i, obj) {
                obj.value = "";
            });
            $(formSearch).css('display', 'none');
        });
        $(searchResultTable).slideUp(400, function () {
            $('.form-control').each(function (i, obj) {
                obj.value = "";
            });
            $(searchResultTable).css('display', 'none');
            $(badSearchFeedBack400).css('display', 'none')
        });
    });

});