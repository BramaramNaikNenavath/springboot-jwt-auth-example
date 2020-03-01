package com.tek.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "register")
public class Register {
	
	public Register() {
		System.out.println(">>>>>>> Register Model Constructor >>>>>");
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "register_id")
	private int id;

	@Column(name = "register_givenname")
	private String givenName;

	@Column(name = "register_lastname")
	private String lastName;

	@Column(name = "register_username")
	private String userName;

	@Column(name = "register_email")
	private String email;

	@Column(name = "register_password")
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

}
