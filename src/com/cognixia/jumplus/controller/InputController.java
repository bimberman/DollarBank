package com.cognixia.jumplus.controller;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import com.cognixia.jumplus.model.Account;
import com.cognixia.jumplus.view.AccountView;

public class InputController {
	
	
	// Using Scanner for Getting Input from Account
	Scanner in = new Scanner(System.in);
	
	// List of accounts
	List<Account> accounts = new ArrayList<Account>();
	
	// For creating a new account
	CreateAccountController newAccountController = new CreateAccountController(in, accounts);
	
	// For logging in
	LoginController loginController = new LoginController(in, accounts);
	
	public InputController(List<Account> accounts) {
		super();
		this.accounts = accounts;
	}

	public void welcome() {
		AccountView.welcome();
		try{
			int input = in.nextInt();
			
			switch(input) {
				case 1: handleNewAccountStatus(newAccountController.createNewAccount());
						break;
				case 2: handleLoginStatus(loginController.login());
						welcome();
						break;
				case 3: AccountView.goodbye();
						in.close();
						break;
				default: AccountView.noValidSelection();
						 welcome();
			}
		} catch (final NumberFormatException e) {
	        e.printStackTrace();
	    } catch (final IllegalFormatException e) {
	        e.printStackTrace();
	    } catch (final Exception e) {
	        e.printStackTrace();
	    }
	}

	private void handleNewAccountStatus(int status) {
		if(status == 100) {
			welcome();
		}
		
		if(status == 200) {
			welcome();
		}
	}
	
	private void handleLoginStatus(Map<String, Integer> res) {
		int status = res.get("code");
		int userId = res.get("id");
		
		if(status == 100) {
			welcome();
		}
		
		if(status == 200) {
			loggedIn(userId);
		}
	}
	
	private void loggedIn(Integer id) {
		final String userId = id.toString();
		Optional<Account> found = accounts.stream()
				  .filter(account -> account.getUserId().equals(userId))
		          .findFirst();
		
		
	}
}
