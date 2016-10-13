//open registration tab if user was in registration form
window.onload = function showTab() {
    var isRegister = document.getElementById("isRegister").value;
    if (isRegister == 'true') {
        $('.nav-tabs a[href="#register"]').tab('show')
    }
};