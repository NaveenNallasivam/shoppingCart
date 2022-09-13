package com.java.customer;

import java.util.*;

import com.java.DB.DBConnector;

public class Student {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private static int rollNo=0;
	private String country;
	private List<String> countries;
	public List<String> getCountries() {
		return countries;
	}
	public Student() {
		this.rollNo++;
		countries=new ArrayList<>();
		countries.add("India");
		countries.add("USA");
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public static int getRollNo() {
		return rollNo;
	}

}
