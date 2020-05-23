$(function () {

    let currentPageIndex = 1;
    let limitPages = 10;
    let countOfNotes = $("#countSubjects").val();
    console.log(countOfNotes);

    $('.deleteSubjectForm').on('submit', function (event) {
        event.preventDefault();
        let deleteId = $('#deletingId').val();
        deleteSubject(deleteId);
    });

    $('.updateSubjectForm').on('submit', function (event) {
        event.preventDefault();
        let updatingId = $('#oldSubjectId').val();
        let updatingName = $('#newSubjectName').val();
        let updatingLH = $('#newLectureHours').val();
        let updatingPH = $('#newPracticalHours').val();
        let updatingCw = $('#newCourseWork').val();
        let updatingDate = $('#newDefenceDate').val();
        let updatingType = $('#newDefenceType').val();

        let subject = {
            id: updatingId,
            name: updatingName,
            practicalHours: updatingPH,
            lectureHours: updatingLH,
            defenceType: updatingType,
            defenceDate: updatingDate,
            courseWork: updatingCw
        };

        updateSubject(subject);

    });


    $(".prevSubjectsPage").on("click", function () {
        if (currentPageIndex > 1) {
            currentPageIndex--;
            fetchDataFromController();
        }
    });

    $(".nextSubjectsPage").on("click", function () {
        if (currentPageIndex * limitPages < countOfNotes) {
            currentPageIndex++;
            fetchDataFromController();
        }
    });

    fetchDataFromController();

    function fetchDataFromController() {
        $.ajax({
            url: "/api/subject",
            type: "GET",
            data: {
                page: currentPageIndex
            },
            success: function (data) {
                if (data.length > 0) {
                    console.log(data);
                    let html = "";
                    for (let i = 0; i < data.length; i++) {
                        html +=
                            "<tr>" +
                            "<td class='align-middle'><span>" + data[i].id + "</span></td>" +
                            "<td class='align-middle'><span>" + data[i].subjectName + "</span></td>" +
                            "<td class='align-middle'><span>" + data[i].plan + "</span></td>" +
                            "<td class='align-middle'><span>" + data[i].defenceType + "</span></td>" +
                            "</tr>"
                    }
                    $("#subjectsTableBody").html(html);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
    }


    function deleteSubject(id) {
        if (id != null || id !== undefined) {
            $.ajax({
                url: "/admin-page/study/subject/delete/" + id,
                type: "DELETE",
                success: function () {
                    console.log("success");
                    fetchDataFromController();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR);
                    console.log(textStatus);
                    console.log(errorThrown);
                }
            });
        }
    }

    function updateSubject(subject) {
        if (checkProperties(subject)) {

            $.ajax({
                url: "/admin-page/study/subject/update",
                type: "PUT",
                data: {
                    id: subject.id,
                    name: subject.name,
                    ph: subject.practicalHours,
                    lh: subject.lectureHours,
                    dType: subject.defenceType,
                    date: subject.defenceDate,
                    cw: subject.courseWork,
                },
                success: function () {
                    console.log("successfully updated");
                    fetchDataFromController();
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

