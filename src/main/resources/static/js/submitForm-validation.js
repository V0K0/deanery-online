$(document).ready(function () {

    (function () {
        'use strict';
        window.addEventListener('load', function () {
            let forms = document.getElementsByClassName('needs-validation');
            let validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();

    $("#formEdit").submit(function (event) {
        let group = $("#group");
        let code = group.val().substring(0, 3);
        let selectedSpecialty = $('select[name="specialty"] :selected').attr('class').substring(1, 4);
        if (code === selectedSpecialty) {
            $('#group').removeClass('is-invalid');
            return;
        }
        group.addClass('is-invalid');
        event.preventDefault();
    });


});