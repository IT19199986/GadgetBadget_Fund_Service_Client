package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund {

	//A common method to connect to the DB
	 private Connection connect() 
	 { 
		 Connection con = null; 
		 
		 try
		 { 
			 Class.forName("com.mysql.jdbc.Driver"); 
	 
			 //Provide the correct details: DBServer/DBName, username, password 
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/funds_service", "root", "anuwaniga#9096"); 
			 System.out.println("Successfully connected");
		 }
		
		 catch (Exception e) 
		 {e.printStackTrace();} 
		 
		 return con; 
	 } 
	   
	 public String insertFunds(String ProductID, String ProductName, String FName, String Amount){
			
			String output = ""; 
			try 
			 { 
				Connection con = connect(); 
				if (con == null) 
				{ 
					return "Error while connecting to the database"; 
				} 
				// create a prepared statement
				String query = " insert into funds(`FundID`,`ProductId`,`ProductName`,`FName`,`Amount`)"
				+ " values (?, ?, ?, ?, ?)"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				// binding values
				 preparedStmt.setInt(1, 0); 
				 preparedStmt.setString(2, ProductID); 
				 preparedStmt.setString(3, ProductName); 
				 preparedStmt.setString(4, FName);  
				 preparedStmt.setString(5, Amount); 
				 
				//execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 output = "Inserted successfully"; 
			
			 }
			catch (Exception e) 
			 { 
				 output = "Error while inserting"; 
				 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			}

	//Read Method
	public String readFunds()
	{ 
	 String output = ""; 
	 try
	 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
			 return "Error while connecting to the database for reading."; 
		 } 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Product ID</th><th>Product Name</th>"
				 + "<th>Funder Name</th><th>Amount</th>"
				 + "<th>Update</th><th>Remove</th></tr>"; 
		 String query = "select * from funds"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
			 String FundID = Integer.toString(rs.getInt("FundID")); 
			 String ProductId = rs.getString("ProductId"); 
			 String ProductName = rs.getString("ProductName"); 
			 String FName = rs.getString("FName"); 
			 String Amount = rs.getString("Amount"); 
			 
			 // Add a row into the html table
			 output += "<tr><td><input id='hidFundIDUpdate' name='hidFundIDUpdate' type='hidden' value='" + FundID + "'>"
					 + ProductId + "</td>"; 
			 output += "<td>" + ProductName + "</td>"; 
			 output += "<td>" + FName + "</td>"; 
			 output += "<td>" + Amount + "</td>";
			 
			 // buttons
			 output += "<td><input name='btnUpdate' " 
			 + " type='button' value='Update' class='btnUpdate btn btn-danger'></td>"
			 + "<td><form method='post' action='Funds.jsp'>"
			 + "<input name='btnRemove' " 
			 + " type='submit' value='Remove' class='btn btn-danger'>"
			 + "<input name='hidFundIDDelete' type='hidden' " 
			 + " value='" + FundID + "'>" + "</form></td></tr>"; 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while reading the funds."; 
		 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	}
	
	//Update Method
	public String updateFunds(String FundID,String ProductID, String ProductName, String FName, String amount) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			
			if (con == null) 
			{ 
				return "Error while connecting to the database for updating."; 
			} 
			
			// create a prepared statement
			String query = "UPDATE funds SET ProductId=?,ProductName=?,FName=?,Amount=? WHERE FundID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setString(1, ProductID); 
			preparedStmt.setString(2, ProductName);  
			preparedStmt.setString(3, FName);
			preparedStmt.setString(4, amount);
			preparedStmt.setInt(5, Integer.parseInt(FundID));
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			//String newFund = readFunds(); 
			System.out.println("Updated Successfully");
			//output = "{\"status\":\"success\", \"data\": \"" + newFund + "\"}"; 
		} 
		catch (Exception e) 
		{ 
			//output = "{\"status\":\"error\", \"data\": \"Error while updating the Funds.\"}";
			System.out.println("Updated Error");
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	

	public String deleteFund(String FundID)
	{ 
	 String output = ""; 
	try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting to the database for deleting."; 
	 } 
	 // create a prepared statement
	 String query = "delete from funds where FundID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(FundID)); 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	catch (Exception e) 
	 { 
	 output = "Error while deleting the Fund."; 
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}
}
