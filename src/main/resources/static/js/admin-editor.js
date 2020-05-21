$(document).ready(function () {

    let formCreateSubject = $('#createSubjectDiv');
    let formUpdateSubject = $('#updateSubjectDiv');
    let formDeleteSubject = $('#deleteSubjectDiv');

    $("#createSubject").on('click', function () {
        if ($(formCreateSubject).css('display') !== 'block'){
            $(formCreateSubject).slideDown(400, function () {
                $(formCreateSubject).css('display', 'block');
            });
        }
    });

    $("#updateSub").on('click', function () {
        if ($(formUpdateSubject).css('display') !== 'block'){
            $(formUpdateSubject).slideDown(400, function () {
                console.log('success');
                $(formUpdateSubject).css('display', 'block');
            });
        }

    });

    $("#deleteSubject").on('click', function () {
        if ($(formDeleteSubject).css('display') !== 'block'){
            $(formDeleteSubject).slideDown(400, function () {
                $(formDeleteSubject).css('display', 'block');
            });
        }
    });


    $("#declineUpdate").on('click', function () {
        $(formUpdateSubject).slideUp(400, function () {
           $('.form-control').each(function (i, obj) {
               obj.value = "";
           });
           $(formUpdateSubject).css('display', 'none');
       });
    });

    $("#declineCreate").on('click', function () {
        $(formCreateSubject).slideUp(400, function () {
            $('.form-control').each(function (i, obj) {
                obj.value = "";
            });
            $(formCreateSubject).css('display', 'none');
        });
    });

    $("#declineDelete").on('click', function () {
        $(formDeleteSubject).slideUp(400, function () {
            $('.form-control').each(function (i, obj) {
                obj.value = "";
            });
            $(formDeleteSubject).css('display', 'none');
        });
    });

});