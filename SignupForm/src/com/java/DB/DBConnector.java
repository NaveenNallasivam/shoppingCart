package com.java.DB;
import java.sql.*;

public class DBConnector {
	public static Connection openConnection(){
		Connection conn=null;
		try {
			String url="jdbc:mysql://localhost:3306/students_db";
			String user="root";
			String password="root";
			conn=DriverManager.getConnection(url, user, password);
			if(conn != null) {
				System.out.println("Connected to the database");
			}
			if(conn == null) {
				System.out.println("Failed to connect to the db");
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return conn;
	}
	

}
