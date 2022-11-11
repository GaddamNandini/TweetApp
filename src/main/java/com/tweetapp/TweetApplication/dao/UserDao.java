package com.tweetapp.TweetApplication.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tweetapp.TweetApplication.config.DBConfig;

import com.tweetapp.TweetApplication.User.User;

public class UserDao {

	User user = new User();
	
	public boolean registerUser(User user) {

		Connection con = DBConfig.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select count(email) as total from users where email=?");
			ps.setString(1, user.getEmail());
			ResultSet rs = ps.executeQuery();
			rs.next();
			int c = rs.getInt("total");
			if (c == 0) {
				ps = con.prepareStatement(
						"insert into users(first_name,last_name,gender,date_of_birth,email,password) values(?,?,?,?,?,?)");
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getGender());
				ps.setString(4, user.getDateOfBirth());
				ps.setString(5, user.getEmail());
				ps.setString(6, user.getPassword());
				ps.executeUpdate();
				return true;

			} else {
				System.out.println(
						"Email already in use. Try registering with another email or login using the same email.");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public boolean login(String email, String password) {
		Connection con = DBConfig.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from users where email = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String pass = rs.getString(6);
			if (pass.equals(password)) {
				ps = con.prepareStatement("update users set logged_in = ? where email=?");
				ps.setString(1, "TRUE");
				ps.setString(2, email);
				ps.executeUpdate();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Incorrect Details. Please try again.");
		return false;
	}

	public boolean forgotPassword(String email, String dob, String password) {
		Connection con = DBConfig.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from users where email = ? ");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if (rs != null) {
				if (rs.getString(4).equals(dob)) {
					ps = con.prepareStatement("update users set password =? where email=?");
					ps.setString(1, password);
					ps.setString(2, email);
					ps.executeUpdate();
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public ResultSet viewAllUsers(String email) {
		Connection con = DBConfig.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select logged_in from users where email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String loggedIn = rs.getString(1);
			if (loggedIn.equalsIgnoreCase("True")) {
				ps = con.prepareStatement("select * from users");
				rs = ps.executeQuery();
				return rs;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public boolean resetPassword(String email, String oldPassword, String newPassword) {
		Connection con = DBConfig.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("update users set password =? where email=?");
			ps.setString(1, newPassword);
			ps.setString(2, email);
			ps.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e);
		}
		return false;

	}

	public void logout(String email) {
		try {
			Connection con = DBConfig.getConnection();
			PreparedStatement ps = con.prepareStatement("update users set logged_in = ? where email=?");
			ps.setString(1, "false");
			ps.setString(2, email);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
