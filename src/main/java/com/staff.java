package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class staff {
	private Connection connect()
	{
			Connection con = null;
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/world", "root", "");
			}
			catch (Exception e)
			{e.printStackTrace();}
			
			return con;
		}
		
	
	
	
		public String insertStaff(String name, String title, String mail, String contact, String gender)
		{
			String output = "";
			try
			{
				Connection con = connect();
				
				if (con == null)
				{return "Error while connecting to the database for inserting."; }
				
				// create a prepared statement
				String query = " insert into staff(`StaffId`,`StaffName`,`JobTitle`,`StaffMail`,`StaffContact`,`StaffGender`)"
				+ " values (?, ?, ?, ?, ?, ?)";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, title);
				preparedStmt.setString(4, mail);
				preparedStmt.setInt(5, Integer.parseInt(contact));
				preparedStmt.setString(6, gender);
				// execute the statement
				
				preparedStmt.execute();
				con.close();
				
				String newStaff = readStaff(); 
				output = "{\"status\":\"success\", \"data\": \"" + newStaff + "\"}";
	        	
	    	} catch(Exception e) {
				output = "{\"status\":\"error\", \"data\": \"Failed to insert the staff\"}";
	    		System.err.println(e.getMessage());
	    	}
		return output;
	}
		
	
		

		
	
		
	public String readStaff()
	{
			String output = "";
			try
			{
				Connection con = connect();
				
				if (con == null)
				{return "Error while connecting to the database for reading."; }
				
				// Prepare the html table to be displayed
				output = "<table class='table' border='1'><tr><th scope='col'>Staff Name</th>" +
						"<th scope='col'>JobTitle</th>" +
						"<th scope='col'>Mail</th>" +
						"<th scope='col'>Contact Number</th>" +
						"<th scope='col'>Gender</th>" +
						"<th scope='col'>Update</th><th>Remove</th></tr>";
				
				String query = "select * from staff";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
		
				// iterate through the rows in the result set
				while (rs.next())
				{
						String StaffId = Integer.toString(rs.getInt("StaffId"));
						String StaffName = rs.getString("StaffName");
						String JobTitle = rs.getString("JobTitle");
						String StaffMail = rs.getString("StaffMail");
						String StaffContact = rs.getString("StaffContact");
						String StaffGender = rs.getString("StaffGender");
						
						// Add into the html table
						output += "<tr><td>" + StaffName + "</td>";
						output += "<td>" + JobTitle + "</td>";
						output += "<td>" + StaffMail + "</td>";
						output += "<td>" + StaffContact + "</td>";
						output += "<td>" + StaffGender + "</td>";
						
						// buttons
					
		        		output += "<td>"
								+ "<input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-sm btn-secondary' data-staffid='" + StaffId + "'>"
								+ "</td>" 
		        				+ "<td>"
								+ "<input name='btnRemove' type='button' value='Remove' class='btn btn-sm btn-danger btnRemove' data-staffid='" + StaffId + "'>"
								+ "</td></tr>";
		}
		con.close();
		
		// Complete the html table
		output += "</table>";
	}
	catch (Exception e)
	{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
	}
			
	return output;
	}
	
	
	
	
	
	
	
	public String updateStaff(String StaffId, String name, String title, String mail, String contact, String gender)
	
	{
			String output = "";
			try
			{
					Connection con = connect();
					
					if (con == null)
					{return "Error while connecting to the database for updating."; }
					
					// create a prepared statement
					String query = "UPDATE staff SET StaffName=?,JobTitle=?,StaffMail=?,StaffContact=?,StaffGender=? WHERE StaffId=?";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values
					preparedStmt.setString(1, name);
					preparedStmt.setString(2, title);
					preparedStmt.setString(3, mail);
					preparedStmt.setInt(4, Integer.parseInt(contact));
					preparedStmt.setString(5, gender);		
					preparedStmt.setInt(6, Integer.parseInt(StaffId));
					
					
					// execute the statement
					preparedStmt.execute();
					con.close();
					
					String newStaff = readStaff(); 
		      		output = "{\"status\":\"success\", \"data\": \"" + newStaff + "\"}";
		        	
		    	} catch(Exception e) {
		    		output = "{\"status\":\"error\", \"data\":\"Failed to update the staff.\"}"; 
		    		System.err.println(e.getMessage());
		    	}
		    	
			
			return output;
	}
	
	
	
	
	
	
	
	
	public String deleteStaff(String StaffId)
	{
			String output = "";
			try
			{
					Connection con = connect();
					if (con == null)
					{return "Error while connecting to the database for deleting."; }
					
					// create a prepared statement
					String query = "delete from staff where StaffId=?";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(StaffId));
					
					// execute the statement
					preparedStmt.execute();
					con.close();
					
					String newStaff = readStaff(); 
		      		output = "{\"status\":\"success\", \"data\": \"" + newStaff + "\"}"; 
		        	
		    	} catch(Exception e) {
					output = "{\"status\":\"error\", \"data\":\"Failed to delete the staff.\"}";
		    		System.err.println(e.getMessage());
		    	}
			
			return output;
	}


}
