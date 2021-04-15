package com.cognixia.utility;

public enum AccountType {
	CHECKING("Checking"),
	SAVINGS("Savings"),
	RETIREMENT("Retirement");
	
	public final String type;
	
	AccountType(String type){
		this.type = type;
	}
	
	public static AccountType type(String StrType) {
	    for (AccountType ac : values()) {
	        if (ac.type.equals(StrType)) {
	            return ac;
	        }
	    }
	    return null;
	}
}
