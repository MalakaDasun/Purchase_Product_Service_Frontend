package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.models.Product_Purchase;

public class PurchaseDao {
	
	DBConnection dbconnect = new DBConnection();

//create new product_purchased item
public String insertProductPurchaseItems(Date date , Double total) {
		
		
		Connection con = dbconnect.connect();
		String result = "";
		
		String insertquery = "insert into product_purchase values (? , ? , ? )";
		try {
			PreparedStatement statement = con.prepareStatement(insertquery);
			
			statement.setInt(1, 0 );
			statement.setDate(2, date);
			statement.setDouble(3 , total);
			statement.execute();
			
			con.close();
			
			String newItems = items();
			result = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";

			
		} catch (SQLException e) {
			result = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
			 System.err.println(e.getMessage()); 
			e.printStackTrace();
		}		
	return result;
	}

	//retrieve all product_purchased items
		public String items () {
			
			Connection con = dbconnect.connect();
			
			String output ="";
			
			String retrieve = "select* from product_purchase ";
			
			try {
				Statement s = con.createStatement();
				
				ResultSet res = s.executeQuery(retrieve) ;
				
				output = "<table border='1'><tr>"
						 + "<th>Date</th><th>Total</th>"						 
						 + "<th>Update</th><th>Remove</th></tr>";
				
				while(res.next()) {
					
					 int id = res.getInt(1);
					 String date = res.getDate(2).toString();
					 String total = Double.toString(res.getDouble(3));
					 
					 
					
					// Add into the html table
					 //output += "<tr><td>" + id + "</td>";
					 output += "<td>" + date + "</td>";
					 output += "<td>" + total + "</td>";
					 
					
					// buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update' "
					 + "class='btnUpdate btn btn-secondary' data-itemid='" + id + "'></td>"
					 + "<td><input name='btnRemove' type='button' value='Remove' "
					 + "class='btnRemove btn btn-danger' data-itemid='" + id + "'></td></tr>";
					
				}
				
				con.close();
				
				output += "</table>";
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return output;
			
		}
		
		//update product_purchased item 
public String updateProductPurchaseItems(int id , Date date , Double total) {
	String result = "";
			Connection con = dbconnect.connect();
			
			String update = " update product_purchase set date =  '"+date+"', total = '"+total+"' where ID = '"+id+"' " ;
			try {
				PreparedStatement stat = con.prepareStatement(update);
				
				stat.execute();
				
				con.close();
			
				
				String newItems = items();
				result = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
				
			} catch (SQLException e) {
				result = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
				 System.err.println(e.getMessage()); 
			}
		return result;	
		}

	//delete product_purchased item
public String deleteProductPurchaseItems(int id) {
	String result = "";
			try {
			Connection connection = dbconnect.connect();
			String delQuery = "delete from product_purchase where ID = '"+id+"'";
			PreparedStatement ps = connection.prepareStatement(delQuery);
			ps.execute();
			
			connection.close();
			
			String newItems = items();
			result = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
			}catch(SQLException e) {
				result = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
				 System.err.println(e.getMessage()); 
			e.printStackTrace();
			}
			return result;
}


}
