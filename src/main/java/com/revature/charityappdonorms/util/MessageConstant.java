package com.revature.charityappdonorms.util;

/**
 * To Show a Valid Error Message
 * */

public class MessageConstant {
	private MessageConstant() {
	    throw new IllegalStateException("No Message");
	  }

	public static final String UNABLE_TO_TRANSACTION = "Invalid Request id/ Amount";
	public static final String UNABLE_TO_LIST_NAME="Unable to show the Contributed List";
	public static final String UNABLE_TO_TOTAL_AMOUNT_BY_ID="unable to Show Toatl amount ";
	public static final String UNABLE_TO_TOTAL_AMOUNT="unable to Show Toatl amount by request id ";
	public static final String	ALL_UNABLE_TO_LIST="UNable To Load List";
	public static final String	MY_UNABLE_TO_LIST="Please check the UserId To Load List";
	public static final String	INVALID_USERID="Invalid UserId";
	public static final String	INVALID_REQUESTID="Invalid REQUESTID";
	public static final String	INVALID_AMOUNT="Invalid amount ";
	public static final String	INVALID_TRANSACTION="transaction failed";

}
