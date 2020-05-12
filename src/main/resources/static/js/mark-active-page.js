$(document).ready(function () {
    let scriptTag = document.getElementById('marker');
    let activeTag = scriptTag.getAttribute("data-page");
    $('.nav-item' + '.' + activeTag).addClass("active");
});