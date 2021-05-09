$(document).ready(function()
{ 
	if ($("#alertSuccess").text().trim() == "") 
	{ 
		$("#alertSuccess").hide(); 
	} 
	$("#alertError").hide(); 
}); 

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 
	 
	 // Form validation-------------------
	 var status = validateFundForm(); 
	 if (status != true) 
	 { 
		 $("#alertError").text(status); 
		 $("#alertError").show(); 
		 return; 
	 } 
	 
	// If valid------------------------
	 $("#formFund").submit(); 
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
	$("#hidFundIDSave").val($(this).closest("tr").find('#hidFundIDUpdate').val()); 
	$("#ProductId").val($(this).closest("tr").find('td:eq(0)').text()); 
	$("#ProductName").val($(this).closest("tr").find('td:eq(1)').text()); 
	$("#FName").val($(this).closest("tr").find('td:eq(2)').text()); 
	$("#Amount").val($(this).closest("tr").find('td:eq(3)').text()); 
}); 

// CLIENT-MODEL================================================================
function validateFundForm() 
{ 
	// Product Id
	if ($("#ProductId").val().trim() == "") 
	{ 
		return "Insert Product ID."; 
	} 
	
	// NAME
	if ($("#ProductName").val().trim() == "") 
	{ 
		return "Insert Product Name."; 
	}
	
	//Funder Name
	if ($("#FName").val().trim() == "") 
	{ 
		return "Insert Funder Name."; 
	} 

	// DESCRIPTION------------------------
	if ($("#Amount").val().trim() == "") 
	{ 
		return "Insert Fund Amount."; 
	} 
	return true; 
}
