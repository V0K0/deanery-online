$(function () {

    let currentPageIndex = 1;

    let limitPages = 10;
    let countOfNotes = $("#countOfStudents").val();
    console.log(countOfNotes);

    let searchResult = $('.get-by-group-response');

    $('.updateStudentForm').on('submit', function (event) {
        event.preventDefault();
        let updatingId = $('#oldStudentId').val();
        let updatingName = $('#newStudentName').val();
        let updatingLastname = $('#newStudentLastname').val();
        let updatingGroup = $('#group').val();
        let student = {
            id: updatingId,
            name: updatingName,
            lastname: updatingLastname,
            stGroup: updatingGroup,
        };
        updateStudent(student);
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
        if (currentPageIndex * limitPages < countOfNotes) {
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
                    console.log(data);
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
            url: "/api/students/byGroup",
            type: "GET",
            data: {
                group: groupCode
            },
            success: function (data) {
                if (data.length > 0) {
                    console.log(data);
                    fillTable($("#searchedStudentTableBody"), data);
                    if ($(searchResult).css('display') !== 'block'){
                        $(searchResult).slideDown(400, function () {
                            $(searchResult).css('display', 'block');
                        });
                    }
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                console.log(textStatus);
                console.log(errorThrown);
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
                url: "/admin-page/study/student/update",
                type: "PUT",
                data: {
                    id: student.id,
                    name: student.name,
                    lastname: student.lastname,
                    stGroup: student.stGroup,
                },
                success: function () {
                    console.log("successfully updated");
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

