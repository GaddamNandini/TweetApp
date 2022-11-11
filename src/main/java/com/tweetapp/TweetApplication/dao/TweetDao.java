package com.tweetapp.TweetApplication.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tweetapp.TweetApplication.config.DBConfig;
import com.tweetapp.TweetApplication.User.Tweet;
import com.tweetapp.TweetApplication.User.User;

public class TweetDao {
	public Connection con = null;
	public PreparedStatement ps = null;
	public User user = new User();

	public boolean postTweet(Tweet tweet) {
		try {
			con = DBConfig.getConnection();
			ps = con.prepareStatement("insert into tweets values(?,?)");
			ps.setString(1, tweet.getTweet());
			ps.setString(2, tweet.getEmail());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return false;
	}

	public ResultSet myTweets(String email) {
		try {
			con = DBConfig.getConnection();
			ps = con.prepareStatement("select * from tweets where email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			return rs;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public ResultSet viewAllTweets(String email) {
		try {
			con = DBConfig.getConnection();
			ps = con.prepareStatement("select logged_in from users where email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String loggedIn = rs.getString(1);
			if (loggedIn.equalsIgnoreCase("true")) {
				ps = con.prepareStatement("select * from tweets");
				rs = ps.executeQuery();
				return rs;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
