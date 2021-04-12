package com.cognixia.jumplus.model;

public class Account {
	private long id;
	
	private String name;
	private String address;
	private String phoneNumber; 
	private String userId;
	private String password;
	private double balance;

	public Account() {
		this(-1, "N/A", "N/A", "N/A", "N/A", "N/A", 0.0);
	}
	
	public Account(long id, String name, String address, String phoneNumber, String userId, String password,
			double balance) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.userId = userId;
		this.password = password;
		this.balance = balance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber
				+ ", userId=" + userId + ", password=" + password + ", balance=" + balance + "]";
	}
}
