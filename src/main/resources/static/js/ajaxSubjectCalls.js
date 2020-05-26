$(function () {

    let currentPageIndex = 1;
    let limitPages = 10;
    let countOfNotes = $("#countOfSubjects").val();
    let searchResult = $('.search-response');
    let badSearch = $('.invalid-feedback-400');

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

    $('.searchSubjectForm').on('submit', function (event) {
        event.preventDefault();
        let planId = $('#findSubject').val();
        findByPlanSubjects(planId);
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
            url: "/api/subjects",
            type: "GET",
            data: {
                page: currentPageIndex
            },
            success: function (data) {
                if (data.length > 0) {
                    if (data.length > 0) {
                        fillTable($("#subjectsTableBody"), data);
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

    function findByPlanSubjects(plan) {
        if (plan != null) {
            $.ajax({
                url: "/api/subjects/search",
                type: "GET",
                data: {
                    plan: plan
                },
                success: function (data) {
                    if (data.length > 0) {
                        if ($(badSearch).css('display') === 'block') {
                            $(badSearch).css('display', 'none');
                        }
                        fillTable($("#searchedSubjectTableBody"), data);
                        if ($(searchResult).css('display') !== 'block') {
                            $(searchResult).slideDown(400, function () {
                                $(searchResult).css('display', 'block');
                            });
                        }
                    }
                },
                error: function () {
                    $(badSearch).css("display", "block");
                }
            });
        }
    }

    function deleteSubject(id) {
        if (id != null || id !== undefined) {
            $.ajax({
                url: "/admin-page/study/subject/delete/" + id,
                type: "DELETE",
                success: function () {
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
        if (subject != null) {
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


    function fillTable(table, data) {
        let html = "";
        for (let i = 0; i < data.length; i++) {
            html +=
                "<tr>" +
                "<tr>" +
                "<td class='align-middle'><span>" + data[i].id + "</span></td>" +
                "<td class='align-middle'><span>" + data[i].subjectName + "</span></td>" +
                "<td class='align-middle'><span>" + data[i].plan + "</span></td>" +
                "<td class='align-middle'><span>" + data[i].defenceType + "</span></td>" +
                "</tr>"

        }
        $(table).html(html);
    }


});

