let navbarInfo = "admin/userAuth";
let usersTable = "admin/allUsers";
let addNewUser = "admin/add";
let editUser = "admin/edit";
let deleteUser = "admin/delete/";


let elementCreateUserRoles = document.getElementById("newRoles");
let elementEditUserRoles = document.getElementById("editRole");

$(document).ready(function () {
    getAuthUser();
    getUsersTable();
})

function authUserForm(title, roles) {
    document.getElementById("userTitle").textContent = title;
    document.getElementById("roleTitle").textContent = roles;
}

async function getAuthUser() {
    fetch(navbarInfo)
        .then(response => (response.json()))
        .then(data => {
            authUserForm(data.email, JSON.stringify(data.setRoles
                .map(role => role.name.substring(5)).join(", ")));
        })
}

//Pill Table
$("#v-pills-home-tab").click(function () {
    getUsersTable();
});
//Pill Info
$("#v-pills-profile-tab").click(function () {
    $("#userInformation").empty();
    $.ajax({
        type: 'GET',
        url: navbarInfo,
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

//Table of all Users
function getUsersTable() {
    $("#tbody").empty();
    $.ajax({
        type: 'GET',
        url: usersTable,
        dataType: "json",
        success: function (data) {
            $.each(data, function (i, user) {
                $('#tbody').append($('<tr>').append(
                    $('<td>').text(user.id),
                    $('<td>').text(user.firstName),
                    $('<td>').text(user.lastName),
                    $('<td>').text(user.age),
                    $('<td>').text(user.email),
                    $('<td>').text(user.setRoles.map(role => role.name.substring(5)).join(", ")),
                    $('<td>').append($('<button>').text("Edit").attr({
                        "type": "button",
                        "class": "btn btn-primary edit",
                        "data-toggle": "modal",
                        "data-target": "#editModal",
                    }).data("user", user)),
                    $('<td>').append($('<button>').text("DELETE").attr({
                        "type": "button",
                        "class": "btn btn-danger delete",
                        "data-toggle": "modal",
                        "data-target": "#deleteModal",
                    }).data("user", user))
                ))
            });
        }
    });
}

//Create new User
$(document).on("click", ".addNew", function () {
    $("#home-tab").trigger('click');
    let roleSelectedValues = Array.from(elementCreateUserRoles.selectedOptions).map(el => el.value);
    let roleArray = convertToRoleSet(roleSelectedValues);

    let newUserForm = {

        firstName: $("#newFirstName").val(),
        lastName: $("#newLastName").val(),
        age: $("#newAge").val(),
        email: $("#newEmail").val(),
        password: $("#newPassword").val(),
        setRoles: roleArray
    };
    $.ajax({
        type: 'POST',
        url: addNewUser,
        dataType: 'json',
        contentType: 'application/JSON; charset=utf-8',
        data: JSON.stringify(newUserForm),
        success: function () {
            $('form[id=newUserForm]').trigger('reset');
            getUsersTable();
        }
    });
});

//Edit User Modal Window
//Fill
$(document).on("click", ".edit", function () {
    let user = $(this).data('user');

    $("#editId").val(user.id);
    $("#editFirstName").val(user.firstName);
    $("#editLastName").val(user.lastName);
    $("#editAge").val(user.age);
    $("#editEmail").val(user.email);
    $("#editPassword").val("**********");
    $("#editRole").val(user.setRoles.map(role => role.name));
});
//Edit
$(document).on("click", ".editUser", function () {

    let roleSelectedValues = Array.from(elementEditUserRoles.selectedOptions).map(el => el.value);
    let roleArray = convertToRoleSet(roleSelectedValues);

    let editUserForm = {

        id: $("#editId").val(),
        firstName: $("#editFirstName").val(),
        lastName: $("#editLastName").val(),
        age: $("#editAge").val(),
        email: $("#editEmail").val(),
        password: $("#editPassword").val(),
        setRoles: roleArray
    };

    $.ajax({
        type: 'PUT',
        url: editUser,
        dataType: 'json',
        contentType: 'application/JSON; charset=utf-8',
        data: JSON.stringify(editUserForm),
        success: getUsersTable()
    });
});
//Delete
$(document).on("click", ".delete", function () {
    let delUser = $(this).data('user');

    $("#delId").val(delUser.id);
    $("#delFirstName").val(delUser.firstName);
    $("#delLastName").val(delUser.lastName);
    $("#delAge").val(delUser.age);
    $("#delEmail").val(delUser.email);
    $("#delPassword").val("**********");
    $("#delRole").val(delUser.setRoles.map(role => role.name.substring(5)).join(", "));
});
//DeleteButton
$(document).on("click", ".deleteButton", function () {
    let id = $("#delId").val();
    $.ajax({
        method: 'DELETE',
        url: deleteUser + id,
        contentType: "application/json",
        dataType: "json",
        success: getUsersTable()
    });
});

//Role Set Converter
function convertToRoleSet(Array) {
    let roleArray = [];

    if (Array.indexOf("ROLE_USER") !== -1) {
        roleArray.unshift({id: 2, name: "ROLE_USER"});
    }
    if (Array.indexOf("ROLE_ADMIN") !== -1) {
        roleArray.unshift({id: 1, name: "ROLE_ADMIN"});
    }
    return roleArray;
}
