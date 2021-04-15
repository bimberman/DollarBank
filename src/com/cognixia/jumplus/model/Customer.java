package com.cognixia.jumplus.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Customer {
	private String name;
	private String address;
	private String phoneNumber; 
	private String userId;
	private String password;
	private double balance;
	private List<Account> accounts;
	
	public Customer(String name, String address, String phoneNumber, String userId, String password) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.userId = userId;
		this.password = password;
		this.balance = 0.0;
		this.accounts = new ArrayList<Account>();
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
		return accounts.stream()
					   .mapToDouble(account -> account.getBalance())
					   .sum();
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public void addAccount(Account account) {
		this.balance += account.getBalance();
		this.accounts.add(account);
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + ", userId=" + userId
				+ ", password=" + password + ", balance=" + balance + "],\naccounts owned=" + accounts.stream().map(Object::toString).collect(Collectors.joining(","));
	}
}
