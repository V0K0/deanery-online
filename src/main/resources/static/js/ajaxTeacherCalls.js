$(function () {

    let currentPageIndex = 1;
    let limitPages = 10;
    let countOfTeachers = $("#countOfTeachers").val();
    let searchResult = $('.search-response');
    let badSearchFeedBack400 = $('.invalid-feedback-400');

    fetchAllTeachersFromController();

    $('.updateTeachersForm').on('submit', function (event) {
        event.preventDefault();
        let id = $('#oldTeacherId').val();
        let name = $('#newTeacherName').val();
        let lastname = $('#newTeacherLastname').val();
        let patron = $('#newTeacherPatron').val();
        let phone = $('#newTeacherPhone').val();

        let teacher = {
            id: id,
            name : name,
            lastname : lastname,
            patron: patron,
            phone : phone
        };

        updateTeacher(teacher);
    });


    $('.searchTeachersForm').on('submit', function (event) {
        event.preventDefault();
        let name = $('#findName').val();
        let lastname = $('#findLastname').val();
        fetchTeachersByNameFromController(name, lastname);
    });


    $(".prevSubjectsPage").on("click", function () {
        if (currentPageIndex > 1) {
            currentPageIndex--;
            fetchAllTeachersFromController();
        }
    });

    $(".nextSubjectsPage").on("click", function () {
        if (currentPageIndex * limitPages < countOfTeachers) {
            currentPageIndex++;
            fetchAllTeachersFromController();
        }
    });

    function fetchTeachersByNameFromController(name, lastname) {
        if (name != null && lastname != null){
            $.ajax({
                url: "/api/teachers/search",
                type: "GET",
                data: {
                    name: name,
                    lastname: lastname
                },
                success: function (data) {
                    if (data.length !== null) {
                        if ($(badSearchFeedBack400).css('display') === 'block') {
                            $('.invalid-feedback-400').css('display', 'none');
                        }
                        fillTable($('#searchedTeacherTableBody'), $(data).toArray());
                        if ($(searchResult).css('display') !== 'block') {
                            $(searchResult).slideDown(400, function () {
                                $(searchResult).css('display', 'block');
                            });
                        }
                    }
                },
                error: function () {
                    $(badSearchFeedBack400).css('display', 'block');
                }
            });
        }

    }

    function fetchAllTeachersFromController() {
        $.ajax({
            url: "/api/teachers",
            type: "GET",
            data: {
                page: currentPageIndex
            },
            success: function (data) {
                if (data.length > 0) {
                    fillTable($('#teacherTableBody'), data);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
    }

    function updateTeacher(teacher) {
        if (teacher != null){
            $.ajax({
                url: "/admin-page/teachers/update",
                type: "PUT",
                data: {
                    id: teacher.id,
                    name: teacher.name,
                    lastname: teacher.lastname,
                    patron: teacher.patron,
                    phone: teacher.phone
                },
                success: function () {
                    fetchAllTeachersFromController();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR);
                    console.log(textStatus);
                    console.log(errorThrown);
                }
            });
        }

    }


    function fillTable(table, data) {
        let html = "";
        for (let i = 0; i < data.length; i++) {
            html +=
                "<tr>" +
                "<td class='align-middle'><span>" + data[i].id + "</span></td>" +
                "<td class='align-middle'><span>" + data[i].lastname + "</span></td>" +
                "<td class='align-middle'><span>" + data[i].name + "</span></td>" +
                "<td class='align-middle'><span>" + data[i].phone + "</span></td>" +
                "<td class='align-middle'><span>" + data[i].subjectName + "</span></td>" +
                "</tr>"
        }
        $(table).html(html);
    }

});

