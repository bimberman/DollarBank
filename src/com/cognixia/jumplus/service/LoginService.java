package com.cognixia.jumplus.service;

import java.util.List;
import java.util.Optional;

import com.cognixia.jumplus.model.Account;

public class LoginService {
	
	Account account;
		
	public int validateUserId(String userInput, List<Account> accounts) {

		Optional<Account> found = accounts.stream()
										  .filter(account -> account.getUserId().equals(userInput))
								          .findFirst();
		if(!found.isPresent()) {
			return 400;
		}
		
		account = found.get();
		return 200;
	}
	
	public int validatePassword(String userInput) {
		if(!account.getPassword().equals(userInput)) {
			return 400;
		}
		
		return 200;
	}
}
