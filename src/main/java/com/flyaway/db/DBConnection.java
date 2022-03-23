package com.flyaway.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DBConnection {

	private static Connection connection;
	
	public DBConnection() {
	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException{ 
		if (connection == null) {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/flyawaydb",
				"root",
				""
			); 	
		}
		return connection;
	}
	
	public void closeConnection() throws SQLException { 
		if (connection != null)
			connection.close(); 
	}
	
}
