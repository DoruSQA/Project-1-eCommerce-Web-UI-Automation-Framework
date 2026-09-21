package project.testdata.expected;

public class CheckoutOneExpected {

	/* Static text */ 
	public final static String URL = "https://www.saucedemo.com/checkout-step-one.html";
	
	/* Error messages */

	public final static String FAILED_MSG_WITH_EMPTY_FIRSTNAME = "Error: First Name is required";
	public final static String FAILED_MSG_WITH_EMPTY_LASTNAME = "Error: Last Name is required";
	public final static String FAILED_MSG_WITH_EMPTY_ZIPCODE = "Error: Postal Code is required";
	public final static String FAILED_MSG_WITH_EXEEDING_FIRST_NAME = "Error: FirstName should be less than 30 chars";
	public final static String FAILED_MSG_WITH_EXEEDING_LAST_NAME = "Error: LastName should be less than 30 chars";
	public final static String FAILED_MSG_WITH_EXEEDING_ZIP_CODE = "Error: ZipCode should be less than 6 chars";
	public final static String PAGE_HEADING = "Checkout: Your Information";
	
}
