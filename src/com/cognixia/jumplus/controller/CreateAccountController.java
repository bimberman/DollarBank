package com.cognixia.jumplus.controller;

import java.util.IllegalFormatException;
import java.util.List;
import java.util.Scanner;

import com.cognixia.jumplus.model.Account;
import com.cognixia.jumplus.service.CreateAccountService;
import com.cognixia.jumplus.view.AccountView;

public class CreateAccountController {
	
	// Using Scanner for Getting Input from User
	Scanner in = new Scanner(System.in);
	List<Account> accounts;
	
	CreateAccountService validate;
	
	public CreateAccountController(Scanner in, List<Account> accounts) {
		super();
		this.in = in;
		this.accounts = accounts;
		this.validate = new CreateAccountService();
	}

	public int createNewAccount() {
		// prints the message for new account and waits for customer name response
		AccountView.newAccount(1);
		try{
			String name = "";
			String address = "";
			String phoneNumber = "";
			String userId = "";
			String password = "";
			double deposit = 0.0;
			int valid = 0;
			
			name = in.next();
			if(name.equals("#")) {
				return 100;
			}
			valid = validate.validateName(name);
			while(valid != 200) {
				
				if(valid == 400) {
					AccountView.invalidInput("Customer Name");
					AccountView.newAccount(7);
					name = in.next();
					valid = validate.validateName(name);
				}
			}
			
			AccountView.newAccount(2);
			address = in.next();
			if(address.equals("#")) {
				return 100;
			}
			valid = validate.validateAddress(address);
			while(valid != 200) {
				if(valid == 400) {
					AccountView.invalidInput("Customer Address");
					AccountView.newAccount(2);
					address = in.next();
					valid = validate.validateAddress(address);
				}
			}
			
			AccountView.newAccount(3);
			phoneNumber = in.next();
			if(phoneNumber.equals("#")) {
				return 100;
			}
			valid = validate.validatePhoneNumber(phoneNumber);
			while(valid != 200) {
				if(valid == 400) {
					AccountView.invalidInput("Customer Contact Number");
					AccountView.newAccount(3);
					phoneNumber = in.next();
					valid = validate.validatePhoneNumber(phoneNumber);
				}
			}
			
			AccountView.newAccount(4);
			userId = in.next();
			if(userId.equals("#")) {
				return 100;
			}
			valid = validate.validateUserId(userId);
			while(valid != 200) {	
				if(valid == 400) {
					AccountView.invalidInput("Customer Id");
					AccountView.newAccount(4);
					userId = in.next();
					valid = validate.validateUserId(userId);
				}
			}
			
			AccountView.newAccount(5);
			password = in.next();
			if(password.equals("#")) {
				return 100;
			}
			valid = validate.validatePassword(password);
			while(valid != 200) {
				if(valid == 400) {
					AccountView.invalidInput("Password");
					AccountView.newAccount(5);
					userId = in.next();
					valid = validate.validatePassword(password);
				}
			}
			
			AccountView.newAccount(6);
			String sDeposit = in.next();
			if(sDeposit.equals("#")) {
				return 100;
			}
			
			deposit = Double.parseDouble(sDeposit);
			valid = validate.validateDepsoit(deposit);
			while(valid != 200) {
				if(validate.validateDepsoit(deposit) == 400) {
					AccountView.invalidInput("Desposit Amount");
					AccountView.newAccount(6);
					sDeposit = in.next();
					deposit = Double.parseDouble(sDeposit);
					valid = validate.validateDepsoit(deposit);
				}
			}
			
			int id = 0;
			if(accounts.isEmpty()) {
				id = 1;
			}
			id = accounts.size()+1;
			accounts.add(new Account(id, 
									 name, 
									 address, 
									 phoneNumber, 
									 userId, 
									 password, 
									 deposit));
		} catch (final NumberFormatException e) {
	        e.printStackTrace();
	    } catch (final IllegalFormatException e) {
	        e.printStackTrace();
	    } catch (final Exception e) {
	        e.printStackTrace();
	    }
		return 200;
	}
}
