package com.cognixia.jumplus.controller;

import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import com.cognixia.jumplus.model.Account;
import com.cognixia.jumplus.service.LoginService;
import com.cognixia.jumplus.view.AccountView;

public class LoginController {
	// Using Scanner for Getting Input from User
	private Scanner in = new Scanner(System.in);
	private List<Account> accounts;
	private LoginService validate = new LoginService();
	HashMap<String, Integer> success = new HashMap<>();
	HashMap<String, Integer> failure = new HashMap<>();
	private int attempts = 0; 
	
	public LoginController(Scanner in, List<Account> accounts) {
		super();
		this.in = in;
		this.accounts = accounts;
	}

	public Map<String, Integer> login() {
		// prints the message for new account and waits for customer name response
		AccountView.loginAccount(1);
		String userId = "-1";
		try{
			userId = in.next();
			String password = "";
			int valid = 0;

			if(userId.equals("#")) {
				failure.put("code", 100);
				return failure;
			}

			valid = validate.validateUserId(userId, accounts);
			while(valid != 200) {
				if(valid == 400) {
					++attempts;
					if(unsuccessfulLogin()==401) {
						failure.put("code", 401);
						return failure;
					};
				}
			}
			
			AccountView.loginAccount(2);
			password = in.next();
			if(password.equals("#")) {
				failure.put("code", 100);
				return failure;
			}
			valid = validate.validatePassword(password);
			while( valid != 200) {
				if(valid == 400) {
					++attempts;
					if(unsuccessfulLogin()==100) {
						failure.put("code", 100);
						return failure;
					}
				}
			}
			
			final String userIdEquals = userId;
			Optional<Account> found = accounts.stream()
											  .filter(account -> account.getUserId().equals(userIdEquals))
									          .findFirst();
					
			AccountView.successfulLogin(found.get().getName());
		} catch (final IllegalFormatException e) {
	        e.printStackTrace();
	    } catch (final Exception e) {
	        e.printStackTrace();
	    }
		
		success.put("code", 200);
		success.put("id", Integer.parseInt(userId));
		return success;
	}
	
	public int unsuccessfulLogin() {
		if(attempts>3) {
			return 401;
		}
		
		// prints the message for new account and waits for customer name response
		AccountView.loginAccount(1);
		try{
			String userId = "";
			String password = "";
			int valid = 0;
			
			userId = in.next();
			if(password.equals("#")) {
				return 100;
			}
			valid = validate.validateUserId(userId, accounts);
			while(valid != 200) {
				if(valid == 400) {
					AccountView.invalidInput("User Id");
					AccountView.loginAccount(3);
					userId = in.next();
				}
			}
			
			AccountView.loginAccount(2);
			password = in.next();
			if(password.equals("#")) {
				return 100;
			}
			
			while(validate.validatePassword(password) != 200) {
				if(validate.validatePassword(password) == 400) {
					AccountView.invalidInput("Password");
					AccountView.newAccount(4);
					userId = in.next();
				}
			}
		} catch (final IllegalFormatException e) {
	        e.printStackTrace();
	    } catch (final Exception e) {
	        e.printStackTrace();
	    }
		return 200;
	}
}
