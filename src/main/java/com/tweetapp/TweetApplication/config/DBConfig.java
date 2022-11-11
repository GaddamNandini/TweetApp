package com.tweetapp.TweetApplication.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {

	private static String url = "jdbc:mysql://localhost:3306/tweetapp";
	private static String userName = "root";
	private static String password = "pass@word1";
	private static Connection con = null;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
