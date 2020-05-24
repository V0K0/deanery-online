$(function () {

    let currentPageIndex = 1;
    let limitPages = 10;
    let countOfStudents = $("#countOfStudents").val();
    let badSearchFeedBack400 = $('.invalid-feedback-400');
    let searchResult = $('.search-response');

    $('.updateStudentForm').on('submit', function (event) {
        event.preventDefault();
        let updatingGroup = $('#group').val();
        let code = updatingGroup.substring(0, 3);
        let selectedSpecialty = $('select[name="specialty"] :selected').attr('class').substring(1, 4);

        if (code === selectedSpecialty) {
            $(updatingGroup).removeClass('is-invalid');
            $(".invalid-feedback-match").css('display', 'none');
            let updatingId = $('#oldStudentId').val();
            let updatingName = $('#newStudentName').val();
            let updatingLastname = $('#newStudentLastname').val();

            let student = {
                id: updatingId,
                name: updatingName,
                lastname: updatingLastname,
                stGroup: updatingGroup,
            };
            updateStudent(student);
        }
        else {
            $(updatingGroup).addClass('is-invalid');
            $(".invalid-feedback-match").css('display', 'block');
        }

    });

    $('.searchStudentForm').on('submit', function (event) {
        event.preventDefault();
        let group = $('#findGroup').val();
        fetchStudentsFromControllerByGroup(group);
    });


    $(".prevSubjectsPage").on("click", function () {
        if (currentPageIndex > 1) {
            currentPageIndex--;
            fetchAllStudentsFromController();
        }
    });

    $(".nextSubjectsPage").on("click", function () {
        if (currentPageIndex * limitPages < countOfStudents) {
            currentPageIndex++;
            fetchAllStudentsFromController();
        }
    });

    fetchAllStudentsFromController();

    function fetchAllStudentsFromController() {
        $.ajax({
            url: "/api/students",
            type: "GET",
            data: {
                page: currentPageIndex
            },
            success: function (data) {
                if (data.length > 0) {
                    fillTable($("#studentTableBody"), data);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
    }

    function fetchStudentsFromControllerByGroup(groupCode) {
        $.ajax({
            url: "/api/students/search",
            type: "GET",
            data: {
                group: groupCode
            },
            success: function (data) {
                if (data.length > 0) {
                    if ($(badSearchFeedBack400).css('display') === 'block') {
                        $(badSearchFeedBack400).css('display', 'none')
                    }
                    fillTable($("#searchedStudentTableBody"), data);
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

    function fillTable(table, data) {
        let html = "";
        for (let i = 0; i < data.length; i++) {
            html +=
                "<tr>" +
                "<td class='align-middle'><span>" + data[i].id + "</span></td>" +
                "<td class='align-middle'><span>" + data[i].lastname + "</span></td>" +
                "<td class='align-middle'><span>" + data[i].name + "</span></td>" +
                "<td class='align-middle'><span>" + data[i].stGroup + "</span></td>" +
                "</tr>"
        }
        $(table).html(html);
    }


    function updateStudent(student) {
        if (checkProperties(student)) {

            $.ajax({
                url: "/admin-page/students/update",
                type: "PUT",
                data: {
                    id: student.id,
                    name: student.name,
                    lastname: student.lastname,
                    stGroup: student.stGroup,
                },
                success: function () {
                    fetchAllStudentsFromController();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR);
                    console.log(textStatus);
                    console.log(errorThrown);
                }
            });
        }

    }

    function checkProperties(obj) {
        for (let key in obj) {
            // noinspection JSUnfilteredForInLoop
            if (obj[key] !== null && obj[key] !== "")
                return true;
        }
        return false;
    }

});

