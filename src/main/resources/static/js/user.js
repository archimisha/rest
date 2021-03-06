$(document).ready(function () {
    getAuthUserInfo();
    getUserInfo()
})

function authUser(title, roles) {
    document.getElementById("userTitle").textContent = title;
    document.getElementById("roleTitle").textContent = roles;
}

async function getAuthUserInfo() {
    fetch("/users/authUser")
        .then(response => (response.json()))
        .then(data => {
            authUser(data.email, JSON.stringify(data.setRoles
                .map(role => role.name.substring(5)).join(", ")));
        })
}

$("#v-pills-profile-tab").click(function () {
    $("#userInformation").empty();
    $.ajax({
        type: 'GET',
        url: '/users/authUser',
        dataType: "json",
        success: function (data) {
            $('#userInformation').append($('<tr>').append(
                $('<td>').text(data.id),
                $('<td>').text(data.firstName),
                $('<td>').text(data.lastName),
                $('<td>').text(data.age),
                $('<td>').text(data.email),
                $('<td>').text(JSON.stringify(data.setRoles.map(role => role.name.substring(5)).join(", ")))
            ))
        }
    });
});

function getUserInfo() {
    $("#userInformation").empty();
    $.ajax({
        type: 'GET',
        url: 'users/authUser',
        dataType: "json",
        success: function (data) {
            $('#userInformation').append($('<tr>').append(
                $('<td>').text(data.id),
                $('<td>').text(data.firstName),
                $('<td>').text(data.lastName),
                $('<td>').text(data.age),
                $('<td>').text(data.email),
                $('<td>').text(data.setRoles.map(role => role.name.substring(5)).join(", "))
            ))
        }
    });
}