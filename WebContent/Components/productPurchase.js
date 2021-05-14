$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}
$("#alertError").hide();
});

$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();

//Form validation-------------------
 var status = validateProductPurchaseForm();
 if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
 
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "ProductPurchaseAPI",
 type : type,
 data : $("#formItem").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onItemSaveComplete(response.responseText, status);
 }
 });
});

//CLIENT-MODEL================================================================
function validateProductPurchaseForm()
{
// DATE
if ($("#date").val().trim() == "")
{
return "Insert Date.";
}
// TOTAL
if ($("#total").val().trim() == "")
{
return "Insert Total price.";
}
// is numerical value
var tmpPrice = $("#total").val().trim();
if (!$.isNumeric(tmpPrice))
{
return "Insert a numerical value for Item Price.";
}
// convert to decimal price
$("#total").val(parseFloat(tmpPrice).toFixed(2));

return true;
}

function onItemSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 
14
 $("#hidItemIDSave").val("");
 $("#formItem")[0].reset();
}


// update 
// attribute.
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidItemIDSave").val($(this).data("itemid"));

 $("#date").val($(this).closest("tr").find('td:eq(0)').text());
 $("#total").val($(this).closest("tr").find('td:eq(1)').text());
});

// delete
$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "ProductPurchaseAPI",
		 type : "DELETE",
		 data : "id=" + $(this).data("itemid"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onItemDeleteComplete(response.responseText, status);
		 }
		 });
		});


function onItemDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}