$(document).ready(function () {
    let specialty = $('#specialty');
    let department = $('#department');
    let options = specialty.find('option');

    department.change(function () {
        let selectedId = $(this).children(":selected").attr("id");
        if (selectedId !== undefined && selectedId !== null) {
            specialty.html(options.filter('[id="' + selectedId + '"]'));
        } else {
            specialty.html('<option  value="" selected>...</option>');
        }
    }).trigger('change');

});

