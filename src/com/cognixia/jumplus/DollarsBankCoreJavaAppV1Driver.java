package com.cognixia.jumplus;

import java.util.ArrayList;
import java.util.List;

import com.cognixia.jumplus.controller.InputController;
import com.cognixia.jumplus.model.Account;

public class DollarsBankCoreJavaAppV1Driver {

	public static void main(String[] args) {
		List<Account> accounts = new ArrayList<Account>();
		Account activeAcc = new Account();
		InputController ic = new InputController(accounts);
		
		ic.welcome();
	}

}
