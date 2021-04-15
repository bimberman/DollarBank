package com.cognixia.jumplus.model;

import java.time.LocalDate;

public class Transaction {

	private int id;
	private double amount;
	private String label;
	private LocalDate TT; // Transaction Time ABRV. to TT
	
	public Transaction(int id, double amount, String label, LocalDate tT) {
		super();
		this.id = id;
		this.amount = amount;
		this.label = label;
		this.TT = tT;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public LocalDate getTT() {
		return TT;
	}

	public void setTT(LocalDate tT) {
		TT = tT;
	}

	@Override
	public String toString() {
		return "\nTransaction [" + id + 
			   "] for the amount of: $" + amount + 
			   " was performed on: " + TT + "\n" + 
			   "With the following special notes: " + label + "\n";
	}
}
