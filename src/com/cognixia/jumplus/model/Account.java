package com.cognixia.jumplus.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.cognixia.utility.AccountType;

public class Account {
	private int id;
	private double balance;
	private AccountType type;
	private List<Transaction> transactions;
	
	public Account(int id, String strType) {
		super();
		this.id = id;
		this.balance = 0.0;
		this.type = AccountType.type(strType);
		transactions = new ArrayList<Transaction>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return transactions.stream()
						   .mapToDouble(transaction -> transaction.getAmount())
						   .sum();
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}
	
	public List<Transaction> getLastFiveTransactions() {
		return transactions.subList(Math.max(transactions.size() - 5, 0), transactions.size());
	}
	
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public int getNewTransactionId() {
		return transactions.size();
	}

	public void addTransaction(Transaction transaction) {
		this.balance += transaction.getAmount();
		this.transactions.add(transaction);
	}

	@Override
	public String toString() {
		return "\nAccount [id=" + id + ", balance=" + balance + ", type=" + type + "],\ntransactions in the account:" + transactions.stream().map(Object::toString).collect(Collectors.joining(","));
	}
}
