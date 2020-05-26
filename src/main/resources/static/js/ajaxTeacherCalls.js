$(function () {

    let currentPageIndex = 1;
    let limitPages = 10;
    let countOfTeachers = $("#countOfTeachers").val();
    let searchResult = $('.search-response');
    let badSearchFeedBack400 = $('.invalid-feedback-400');
    let deniedModification304 = $('.invalid-feedback-304');

    let relationForm = $('.setRelationForm');
    let submitButtons = $(relationForm).find('input[type=submit]');

    $(relationForm).keydown(function (e) {
        if (e.keyCode === 13) {
            e.preventDefault();
            return false;
        }
    });

    $('.updateTeachersForm').on('submit', function (event) {
        event.preventDefault();
        let id = $('#oldTeacherId').val();
        let name = $('#newTeacherName').val();
        let lastname = $('#newTeacherLastname').val();
        let patron = $('#newTeacherPatron').val();
        let phone = $('#newTeacherPhone').val();

        let teacher = {
            id: id,
            name: name,
            lastname: lastname,
            patron: patron,
            phone: phone
        };
        updateTeacher(teacher);
    });

    $(relationForm).on('submit', function (e) {
        e.preventDefault();
        if (submitButtons != null) {
            let clicked = submitButtons.name;
            let id = $('#teacherIdInRelation').val();
            let name = $('#subjectNameInRelation').val();
            let plan = $('#subjectPlanInRelation').val();
            let relation = {
                id: id,
                name: name,
                plan: plan
            }
            if (clicked === 'submitDeleteBound') {
                deleteRelation(relation);
            } else {
                addRelation(relation)
            }
        }
    });

    $(submitButtons).click(function (event) {
        submitButtons = this;
    });

    $('.searchTeachersForm').on('submit', function (event) {
        event.preventDefault();
        let name = $('#findName').val();
        let lastname = $('#findLastname').val();
        findTeacherByName(name, lastname);
    });

    $(".prevTeachersPage").on("click", function () {
        if (currentPageIndex > 1) {
            currentPageIndex--;
            fetchAllTeachersFromController();
        }
    });

    $(".nextTeachersPage").on("click", function () {
        if (currentPageIndex * limitPages < countOfTeachers) {
            currentPageIndex++;
            fetchAllTeachersFromController();
        }
    });

    fetchAllTeachersFromController();

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

    function findTeacherByName(name, lastname) {
        if (name != null && lastname != null) {
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
                            $(badSearchFeedBack400).css('display', 'none');
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

    function updateTeacher(teacher) {
        if (teacher != null) {
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

    function deleteRelation(relation) {
        if (relation != null) {
            $.ajax({
                url: "/admin-page/teachers/delete-relation",
                type: "DELETE",
                data: {
                    id: relation.id,
                    name: relation.name,
                    plan: relation.plan
                },
                success: function () {
                    if ($(deniedModification304).css("display") !== "none"){
                        $(deniedModification304).css("display", "none");
                    }
                    fetchAllTeachersFromController();
                },
                error: function () {
                    $(deniedModification304).css("display", "block");
                }
            });
        }
    }

    function addRelation(relation) {
        if (relation != null) {
            $.ajax({
                url: "/admin-page/teachers/add-relation",
                type: "POST",
                data: {
                    id: relation.id,
                    name: relation.name,
                    plan: relation.plan
                },
                success: function () {
                    if ($(deniedModification304).css("display") !== "none"){
                        $(deniedModification304).css("display", "none");
                    }
                    fetchAllTeachersFromController();
                },
                error: function () {
                    $(deniedModification304).css("display", "block");
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

