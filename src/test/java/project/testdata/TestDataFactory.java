package project.testdata;

import project.testdata.model.CheckoutUser;
import dorusqa.model.LoginUser;
import project.utils.DataReader;

public class TestDataFactory {

	public static LoginUser createStandardUser() {

		return DataReader.read("standardUser", LoginUser.class);
	}

	public static LoginUser createInvalidUser() {

		return DataReader.read("invalidUser", LoginUser.class);
	}
	
	public static LoginUser createLockedoutUser()
	{
		return DataReader.read("lockedOutUser", LoginUser.class);
	}
	
	public static CheckoutUser createValidCheckoutUser()
	{
		return DataReader.read("checkoutWithValidUser", CheckoutUser.class);
	}
	
	public static CheckoutUser createCheckoutUserWithWhitespaceOnly()
	{
		return DataReader.read("checkoutUserWithWhitespaceOnly", CheckoutUser.class);
	}

	
}
