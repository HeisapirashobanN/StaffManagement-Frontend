$(document).ready(function() {
    if ($('#alertSuccess').text().trim() == "") {
        $('#alertSuccess').hide();
    }

    $('#alertError').hide();
})

// SAVE
$(document).on("click","#btnSave", function(event) {
    // Clear alerts
    $("#alertSuccess").text(""); 
    $("#alertSuccess").hide(); 
    $("#alertError").text(""); 
    $("#alertError").hide();

    // Form validation
    var status = validateStaffForm(); 
    if (status != true) 
    { 
        $("#alertError").text(status); 
        $("#alertError").show(); 
        return; 
    } 

    // if hidItemIDSave value is null set as POST else set as PUT
    var type = ($("#hidStaffIDSave").val() == "") ? "POST" : "PUT";

    // ajax communication
    $.ajax({
        url: "StaffAPI",
        type: type,
        data: $("#formStaff").serialize(),
        dataType: "text",
        complete: function(response, status) {
            onStaffSaveComplete(response.responseText, status);
        }
    });
});

// after completing save request
function onStaffSaveComplete(response, status) {

    if (status == "success") { //if the response status is success
        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() === "success") { //if the json status is success
            //display success alert
            $("#alertSuccess").text("Successfully saved");
            $("#alertSuccess").show();
    
            //load data in json to html
            $("#divStaffGrid").html(resultSet.data);

        } else if (resultSet.status.trim() === "error") { //if the json status is error
            //display error alert
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") { 
        //if the response status is error
        $("#alertError").text("Error while saving");
        $("#alertError").show();
    } else { 
        //if an unknown error occurred
        $("#alertError").text("Unknown error occurred while saving");
        $("#alertError").show();
    } 

    //resetting the form
    $("#hidStaffIDSave").val("");
    $("#formStaff")[0].reset();
}

// UPDATE
//to identify the update button we didn't use an id we used a class
$(document).on("click", ".btnUpdate", function(event) 
{ 
    //get item id from the data-itemid attribute in update button
    $("#hidStaffIDSave").val($(this).data('staffid')); 
    //get data from <td> element
    $("#name").val($(this).closest("tr").find('td:eq(0)').text()); 
    $("#title").val($(this).closest("tr").find('td:eq(1)').text()); 
    $("#mail").val($(this).closest("tr").find('td:eq(2)').text()); 
    $("#contact").val($(this).closest("tr").find('td:eq(3)').text()); 
    $("#gender").val($(this).closest("tr").find('td:eq(4)').text()); 
    
}); 

// DELETE
$(document).on("click",".btnRemove", function(event) {
    // ajax communication
    $.ajax({
        url: "StaffAPI",
        type: "DELETE",
        data: "StaffId=" + $(this).data("staffid"),
        dataType: "text",
        complete: function(response, status) {
            onStaffDeleteComplete(response.responseText, status);
        }
    });
});

// after completing delete request
function onStaffDeleteComplete(response, status) {

    if (status == "success") { //if the response status is success
        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() === "success") { //if the json status is success
            //display success alert
            $("#alertSuccess").text("Successfully deleted");
            $("#alertSuccess").show();
    
            //load data in json to html
            $("#divStaffGrid").html(resultSet.data);

        } else if (resultSet.status.trim() === "error") { //if the json status is error
            //display error alert
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") { 
        //if the response status is error
        $("#alertError").text("Error while deleting");
        $("#alertError").show();
    } else { 
        //if an unknown error occurred
        $("#alertError").text("Unknown error occurred while deleting");
        $("#alertError").show();
    } 
}

// VALIDATION
function validateStaffForm() { 
    // CODE 
    if ($("#name").val().trim() == "") 
    { 
        return "Insert Staff Name."; 
    } 
    
    // NAME 
    if ($("#title").val().trim() == "") 
    { 
        return "Insert Staff JobTitle."; 
    } 
    
    // PRICE
    if ($("#mail").val().trim() == "") 
    { 
        return "Insert Staff mail."; 
    } 
      // is numerical value 
    var tmpNum = $("#contact").val().trim(); 
    if (!$.isNumeric(tmpNum)) 
    { 
        return "Insert a numerical value for Contact number."; 
    } 
    
    
    // DESCRIPTION
    if ($("#contact").val().trim() == "") 
    { 
        return "Insert Staff Contact number."; 
    } 
    
        // PRICE
    if ($("#gender").val().trim() == "") 
    { 
        return "Insert Staff gender."; 
    } 
      
    return true; 
} 
 