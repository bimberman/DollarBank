package com.cognixia.jumplus.view;

public class AccountView {
	public static void welcome() {
		System.out.println( "\n"
			   + "+---------------------------+\n"
			   + "| DOLLARSBANK Welcomes You! |\n"
			   + "+---------------------------+\n"
			   + "1. Create New Account\n"
			   + "2. Login\n"
			   + "3. Exit\n"
			   + "Press # at anytime to return to the previous menu\n\n"
			   + "Enter your choice(1, 2, or 3):");
	}
	
	public static void main() {
		System.out.println( "\n"
			   + "1. Create New Account"
			   + "2. Login"
			   + "3. Exit\n\n"
			   + "Press # at anytime to return to the previous menu\n\n"
			   + "Enter your choice(1, 2, or 3):");
	}
	
	public static void goodbye() {
		System.out.println("\nThank you for choosing to bank with us, goodbye!");
	}

	public static void loginAccount(int step) {

		switch(step) {
			case 1: System.out.println("\n"
									 + "+---------------------+\n"
									 + "| Enter Login Details |\n"
									 + "+---------------------+\n"
									 + "User Id:"); 
					break;
			case 2: System.out.println("Password:");
					break;
			case 3: System.out.println("User Id:");
					break;
			default: System.out.println("Invalid step");
		}
	}

	public static void newAccount(int step) {
		switch(step) {
			case 1: System.out.println("\n"
									 + "+-------------------------------+\n"
									 + "| Enter Details For New Account |\n"
									 + "+-------------------------------+\n"
									 + "Customer Name:"); 
					break;
			case 2: System.out.println( "Customer Address:");
					break;
			case 3: System.out.println( "Customer Contact Number:");
					break;
			case 4: System.out.println( "User Id:");
					break;
			case 5: System.out.println( "Password: 8 Characters With Lower, Upper, & Special");
					break;
			case 6: System.out.println( "Initial Deposit Amount:");
					break;
			case 7: System.out.println("Customer Name:"); 
					break;
			default: System.out.println("Invalid step");
		}
	}

	public static void successfulLogin(String name) {
		System.out.println("\nWelcome " + name);
	}
	
	public static void noValidSelection() {
		System.out.println("\nPlease select a valid option from the menu above!");
	}
	
	public static void invalidInput(String inputName) {
		System.out.println("\nPlease input a valid value for " + inputName);
	}
	
	public static void invalidCredentials() {
		System.out.println("Invalid Credentials. Try Again!");
	}
	
	public static void invalidCredentialsMax() {
		System.out.println("Max Attempts Reached. Aborting...");
	}
	
	public static void loggedInWelcome() {
		System.out.println( "\n"
			   + "+-------------------+\n"
			   + "| Welcome Customer! |\n"
			   + "+-------------------+\n"
			   + "1. Deposit Amount\n"
			   + "2. Withdraw Amount\n"
			   + "3. Funds Transfer\n"
			   + "4. View 5 Recent Transactions\n"
			   + "5. Display Customer Information\n"
			   + "6. Sign Out\n"
			   + "Press # at anytime to return to the previous menu\n\n"
			   + "Enter your choice(1, 2, 3, 4, 5, or 6):");
	}
}
