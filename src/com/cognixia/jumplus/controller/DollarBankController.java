package com.cognixia.jumplus.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cognixia.jumplus.model.Account;
import com.cognixia.jumplus.model.Customer;
import com.cognixia.jumplus.model.Transaction;
import com.cognixia.utility.ConsolePrinterUtility;

public class DollarBankController {

	private Scanner scanner;
	private List<Customer> customers;
	private boolean cont = true;
	private Customer authCustomer;
	
	public DollarBankController() {
		super();
		this.scanner = new Scanner(System.in);
		this.customers = new ArrayList<Customer>();
	}

	public void init() {
		ConsolePrinterUtility.welcome();
		try{
			int input = scanner.nextInt();
			
			switch(input) {
				case 1: createNewAccount();
						break;
				case 2: login();
						break;
				case 3: cont = false;
						break;
				default: ConsolePrinterUtility.noValidSelection();
						 ConsolePrinterUtility.welcome();
			}
			
			if(!cont) {
				ConsolePrinterUtility.goodbye();
				scanner.close();
				return;
			}
			
			init();
		} catch (final NumberFormatException e) {
	        e.printStackTrace();
	    } catch (final IllegalFormatException e) {
	        e.printStackTrace();
	    } catch (final Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private void createNewAccount() {
		ConsolePrinterUtility.newAccount(1);
		String name = "";
		String address = "";
		String phoneNumber = "";
		String userId = "";
		String password = "";
		double amount = 0.0;
		int count = 0;
		try{
			name = scanner.next();
			ConsolePrinterUtility.newAccount(2);
			address = scanner.next();
			ConsolePrinterUtility.newAccount(3);
			phoneNumber = scanner.next();
			ConsolePrinterUtility.newAccount(4);
			userId = scanner.next();
			ConsolePrinterUtility.newAccount(5);
			
			// Check for password rules, can redo max of 3 attemps
			password = scanner.next();
			// digit + lowercase char + uppercase char + punctuation + symbol
		    final String PASSWORD_PATTERN =
		            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
			Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		    Matcher matcher = pattern.matcher(password);
		    boolean matches = matcher.find();
		    
		    while(!matches) {
		    	ConsolePrinterUtility.invalidCredentials();
		    	if(++count>=3) return;
		    	password = scanner.next();
		    	matcher = pattern.matcher(password);
		    	matches = matcher.find();
		    }
		    
		    ConsolePrinterUtility.newAccount(6);
		    amount = scanner.nextDouble();
		    Transaction newTransaction = new Transaction(0, amount, "Initial Deposit", LocalDate.now());
		    Account newAccount = new Account(customers.size(), "Checking");
		    Customer newCustomer = new Customer(name, address, phoneNumber, userId, password);
		    newAccount.addTransaction(newTransaction);
		    newCustomer.addAccount(newAccount);
		    customers.add(newCustomer);
		    ConsolePrinterUtility.successfulAccountCreation(newCustomer.toString());
		} catch (final NumberFormatException e) {
	        e.printStackTrace();
	    } catch (final IllegalFormatException e) {
	        e.printStackTrace();
	    } catch (final Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private void login() {
		ConsolePrinterUtility.loginAccount(1);
		int count = 0;
		try{
			// has to be final because streams won't accept variables that can change
			final String userId = scanner.next();
			
			// Try to find user, if user doesn't exist you can try again for max 3 times
			Customer foundCustomer = customers.stream()
									  .filter(p -> p.getUserId().equals(userId))
									  .findFirst().orElse(null);
			while(foundCustomer==null) {
				if(++count>=3) return;
				ConsolePrinterUtility.loginAccount(3);
				final String tempId = scanner.next();
				foundCustomer = customers.stream()
						  .filter(p -> p.getUserId().equals(tempId))
						  .findFirst().orElse(null);
			}
			
			count = 0;
			
			// Same for password, can try 3 times
			ConsolePrinterUtility.loginAccount(2);
		    String pass = scanner.next();
		    while(!foundCustomer.getPassword().equals(pass)) {
		    	if(++count>3) return;
		    	ConsolePrinterUtility.loginAccount(2);
		    	pass = scanner.next();
		    }
		    authCustomer = foundCustomer;
		    loggedIn();
		} catch (final NumberFormatException e) {
	        e.printStackTrace();
	    } catch (final IllegalFormatException e) {
	        e.printStackTrace();
	    } catch (final Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private void loggedIn() {
		ConsolePrinterUtility.loggedInWelcome();
		boolean loggedIn = true;
		try{
			int next = scanner.nextInt();
			
			switch(next) {
				case 1: deposit();
						break;
				case 2: withdraw();
						break;
				case 3: transfer();
						break;
				case 4: lastFive();
						break;
				case 5: displayCustomerInfo();
						break;
				case 6: authCustomer = null;
						loggedIn = false;
						break;
				default: System.out.println("Invalid step");
			}
			
			if(!loggedIn) return;
			loggedIn();
		} catch (final NumberFormatException e) {
	        e.printStackTrace();
	    } catch (final IllegalFormatException e) {
	        e.printStackTrace();
	    } catch (final Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private void deposit() {
		try{
			ConsolePrinterUtility.depositAmount(1);
			double deposit = Double.parseDouble(scanner.next());
			ConsolePrinterUtility.depositAmount(2);
			String label = scanner.next();
			int transactionId = authCustomer.getAccounts().get(0).getNewTransactionId();
			Transaction transaction = new Transaction(transactionId, deposit, label, LocalDate.now());
			
			authCustomer.getAccounts().get(0).addTransaction(transaction);
			ConsolePrinterUtility.printDepositTransaction(deposit);
		} catch (final NumberFormatException e) {
	        e.printStackTrace();
	    } catch (final IllegalFormatException e) {
	        e.printStackTrace();
	    } catch (final Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private void withdraw() {
		try{
			ConsolePrinterUtility.withdrawAmount(1);
			double withdraw = -Double.parseDouble(scanner.next());
			ConsolePrinterUtility.withdrawAmount(2);
			String label = scanner.next();
			int transactionId = authCustomer.getAccounts().get(0).getNewTransactionId();
			Transaction transaction = new Transaction(transactionId, withdraw, label, LocalDate.now());
			
			authCustomer.getAccounts().get(0).addTransaction(transaction);
			ConsolePrinterUtility.printWithdrawTransaction(-withdraw);
		} catch (final NumberFormatException e) {
	        e.printStackTrace();
	    } catch (final IllegalFormatException e) {
	        e.printStackTrace();
	    } catch (final Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private void transfer() {
		try{
			ConsolePrinterUtility.transferAmount(1);
			double transfer = -Double.parseDouble(scanner.next());
			ConsolePrinterUtility.transferAmount(2);
			String label = scanner.next();
			int transactionId = authCustomer.getAccounts().get(0).getNewTransactionId();
			Transaction transaction = new Transaction(transactionId, transfer, label, LocalDate.now());
			
			authCustomer.getAccounts().get(0).addTransaction(transaction);
			ConsolePrinterUtility.printTransferTransaction(transfer);
		} catch (final NumberFormatException e) {
	        e.printStackTrace();
	    } catch (final IllegalFormatException e) {
	        e.printStackTrace();
	    } catch (final Exception e) {
	        e.printStackTrace();
	    }
	}

	private void lastFive() {
		ConsolePrinterUtility.printLastFiveTransaction();
		authCustomer.getAccounts()
					.get(0)
					.getLastFiveTransactions()
					.stream().forEach(transaction->System.out.println(transaction.toString()));
	}
	
	private void displayCustomerInfo() {
		System.out.println(authCustomer.toString());
	}
}
