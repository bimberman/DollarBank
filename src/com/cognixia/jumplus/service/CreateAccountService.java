package com.cognixia.jumplus.service;

import java.util.regex.Pattern;

public class CreateAccountService {

	public int validateName(String userInput) {
		String regex = "\\w";
		if(!Pattern.matches(regex,userInput)) {
			return 400;
		}
		return 200;
	}
	
	public int validateAddress(String userInput) {
		return 200;
	}
	
	public int validatePhoneNumber(String userInput) {
		return 200;
	}
	
	public int validateUserId(String userInput) {
		return 200;
	}
	
	public int validatePassword(String userInput) {
		return 200;
	}
	
	public int validateDepsoit(double deposit) {
		if(deposit<0) {
			return 400;
		}
		return 200;
	}
}
