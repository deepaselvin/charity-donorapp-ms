package com.revature.charityappdonorms.util;

/**
 * To Show a Valid Error Message
 * */

public class MessageConstant {
	private MessageConstant() {
	    throw new IllegalStateException("No Message");
	  }

	public static final String UNABLE_TO_TRANSACTION = "Invalid Request id/ Amount";
	public static final String	UNABLE_TO_LIST="UNable To Load List";
	public static final String	INVALID_USERID="Invalid UserId";
	public static final String	INVALID_REQUESTID="Invalid REQUESTID";
	public static final String	INVALID_AMOUNT="Invalid amount ";
	public static final String	INVALID_TRANSACTION="transaction failed";

}
