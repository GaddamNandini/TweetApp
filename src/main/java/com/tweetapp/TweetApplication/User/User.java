package com.tweetapp.TweetApplication.User;

public class User {

	private String firstName;
	private String lastName;
	private String gender;
	private String dateOfBirth;
	private String email;
	private String password;
	private String loggedIn;

	public User() {
		super();
	}

	public User(String firstName, String lastName, String gender, String dateOfBirth, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public synchronized void setLoggedIn(String loggedIn) {
		this.loggedIn = loggedIn;
	}

	public synchronized String getLoggedIn() {
		return loggedIn;
	}
}
