<%@page import="com.Fund"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	//Insert item---------------------------------
	if (request.getParameter("ProductID") != null) 
	{ 
		Fund fundObj = new Fund(); 
		String stsMsg = ""; 
		
		//Insert--------------------------
		if (request.getParameter("hidFundIDSave") == "") 
		 { 
		 stsMsg = fundObj.insertFunds(request.getParameter("ProductID"), 
		 request.getParameter("ProductName"), 
		 request.getParameter("FName"), 
		 request.getParameter("Amount")); 
		 } 
		else//Update----------------------
		 { 
		 stsMsg = fundObj.updateFunds(request.getParameter("hidFundIDSave"), 
		 request.getParameter("ProductID"), 
		 request.getParameter("ProductName"), 
		 request.getParameter("FName"), 
		 request.getParameter("Amount")); 
		 } 
		 session.setAttribute("statusMsg", stsMsg); 
		} 
	
	//Delete item----------------------------------
	if (request.getParameter("hidFundIDDelete") != null) 
	{ 
		Fund fundObj = new Fund(); 
		String stsMsg = fundObj.deleteFund(request.getParameter("hidFundIDDelete")); 
		session.setAttribute("statusMsg", stsMsg); 
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<!-- CSS file -->
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		
		<!-- java script file -->
		<script src = "Components/jquery-3.6.0.min.js" type = "text/javascript"> </script>
		<script src = "Components/FundValidation.js" type = "text/javascript"> </script>
		
		<title>Fund Service</title>
		
	</head>
	<body>
	
		<div class="container">
		 <div class="row">
		 	<div class="col">
	 			<h1>Fund Management</h1>
	 			
				<form method="post" action="Funds.jsp" id="formFund" name="formFund"> 
					 Product ID: <input id = "ProductID "name="ProductID" type="text" class="form-control" onchange="emptyCheck('ProductID',ProductID)" onclick ="emptyCheck('ProductID',ProductID)"><br> 
					 Product Name: <input id ="ProductName" name="ProductName" type="text" class="form-control" onchange="emptyCheck('ProductName',ProductName)" onclick ="emptyCheck('ProductName)',ProductName)"><br> 
					 Funder Name: <input id ="FName" name="FName" type="text" class="form-control" onchange="emptyCheck('FName',FName)" onclick ="emptyCheck('FName',FName)"><br>
					 Amount: <input id ="Amount" name="Amount" type="text" class="form-control" onchange="emptyCheck('Amount',Amount)" onclick ="emptyCheck('Amount',Amount)"><br>  
					 <input id ="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
					 <input type="hidden" id="hidFundIDSave" name="hidFundIDSave" value="">
				</form>
				<div class="alert alert-success">
					<%
					 out.print(session.getAttribute("statusMsg")); 
					%> 
				</div>
	
				<br>
				<%
				 Fund fundsObj = new Fund(); 
				 out.print(fundsObj.readFunds()); 
				%>
	 		</div>
	 	 </div>
		</div>
	
		
	</body>
</html>