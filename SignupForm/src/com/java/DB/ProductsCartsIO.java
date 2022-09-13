package com.java.DB;

import java.sql.*;

public class ProductsCartsIO {
	
	public static ResultSet fetchProductPrice(String tableName,String product) {
		Connection con=DBConnector.openConnection();
		PreparedStatement ps;
		ResultSet rs = null;
		try {
			switch(tableName) {
			case "vegetable_price":
				ps = con.prepareStatement("select Price from vegetable_price where item=(?)");
				ps.setNString(1, product);
				rs=ps.executeQuery();
				break;
			case "fruits_price":
				ps = con.prepareStatement("select Price from fruits_price where item=(?)");
				ps.setNString(1, product);
				rs=ps.executeQuery();
				break;
			case "spices_prices":
				ps = con.prepareStatement("select Price from spices_prices where item=(?)");
				ps.setNString(1, product);
				rs=ps.executeQuery();
				break;
			case "packaged_food_prices":
				ps = con.prepareStatement("select Price from packaged_food_prices where item=(?)");
				ps.setNString(1, product);
				rs=ps.executeQuery();
				break;
			}
			//con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static void updateShoppingCart(int rollNo,String product,int rate, int amount) {
		Connection con=DBConnector.openConnection();
		try {
			PreparedStatement ps=con.prepareStatement("insert into shoppingcart values(?,?,?,?)");
			ps.setLong(1,rollNo);
			ps.setString(2, product);
			ps.setLong(3, rate);
			ps.setLong(4, amount);
			ps.execute();
			//con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ResultSet retrieveFromCart(int rollNo) {
		Connection con=DBConnector.openConnection();
		try {
			PreparedStatement ps=con.prepareStatement("select Item,Rate,Amount from shoppingcart where customer_Id=(?)");
			ps.setLong(1, rollNo);
			ResultSet rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
