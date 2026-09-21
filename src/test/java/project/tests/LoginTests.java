package project.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import static dorusqa.z.Z.*;
import dorusqa.testdata.*;

import project.base.BaseTest;
import project.testdata.expected.InventoryExpected;
import project.testdata.expected.LoginExpected;
import dorusqa.model.LoginUser;

public class LoginTests extends BaseTest {


	@Test(groups = { "Smoke", "Regression","Authentication" }, description = TC_LOGIN_001, 
	dataProvider = "standardUser", dataProviderClass = TestDataProvider.class)
	public void loginWithValidCredentials(LoginUser standardUser) {

		// Authenticate with valid credentials
		loginPage.authentication(standardUser);

		// Verify successful login and inventory page elements
		softAssertion.assertEquals(InventoryExpected.URL, inventoryPage.getUrl());
		softAssertion.assertEquals(InventoryExpected.PAGE_HEADING, inventoryPage.getPageHeading());
		softAssertion.assertTrue(inventoryPage.isMenuIconDisplayed());
		softAssertion.assertTrue(inventoryPage.isShoppingCartIconDisplayed());
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Authentication" }, description = TC_LOGIN_002, 
	dataProvider = "standardUser", dataProviderClass = TestDataProvider.class)
	public void loginWithValidCredentialsUsingEnter(LoginUser standardUser) {

		// Enter valid credentials and submit the login form using Enter
		loginPage.enterUsername(standardUser.getUsername());
		loginPage.enterPassword(standardUser.getPassword());
		loginPage.pressEnter();

		// Verify successful login and inventory page elements
		softAssertion.assertEquals(InventoryExpected.URL, inventoryPage.getUrl());
		softAssertion.assertEquals(InventoryExpected.PAGE_HEADING, inventoryPage.getPageHeading());
		softAssertion.assertTrue(inventoryPage.isMenuIconDisplayed());
		softAssertion.assertTrue(inventoryPage.isShoppingCartIconDisplayed());
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Authentication" }, description = TC_LOGIN_003, 
	dataProvider = "wrongPassword", dataProviderClass = TestDataProvider.class)
	public void loginWithValidUsernameAndWrongPassword(LoginUser userWithWrongPassword) {

		// Attempt login with a valid username and incorrect password
		loginPage.authentication(userWithWrongPassword);

		// Verify login failure and expected error message
		softAssertion.assertEquals(LoginExpected.FAILED_MSG_WITH_VALID_CREDENTIALS, loginPage.getErrorMessage());
		softAssertion.assertEquals(LoginExpected.URL, loginPage.getUrl());
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Authentication" }, description = TC_LOGIN_004, 
	dataProvider = "wrongUsername", dataProviderClass = TestDataProvider.class)
	public void loginWithValidPasswordAndWrongUsername(LoginUser userWithWrongUsername) {

		// Attempt login with an incorrect username and valid password
		loginPage.authentication(userWithWrongUsername);

		// Verify login failure and expected error message
		softAssertion.assertEquals(LoginExpected.FAILED_MSG_WITH_VALID_CREDENTIALS, loginPage.getErrorMessage());
		softAssertion.assertEquals(LoginExpected.URL, loginPage.getUrl());
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Authentication" }, description = TC_LOGIN_005, 
	dataProvider = "emptyUsername", dataProviderClass = TestDataProvider.class)
	public void loginWithEmptyUsernameAndValidPassword(LoginUser userWithEmptyUsername) {

		// Attempt login with an empty username and valid password
		loginPage.authentication(userWithEmptyUsername);

		// Verify login failure and validation message
		softAssertion.assertEquals(LoginExpected.FAILED_MSG_WITH_EMPTY_USERNAME, loginPage.getErrorMessage());
		softAssertion.assertEquals(LoginExpected.URL, loginPage.getUrl());
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Authentication" }, description = TC_LOGIN_006, 
	dataProvider = "emptyPassword", dataProviderClass = TestDataProvider.class)
	 public void loginWithEmptyPasswordAndValidUsername(LoginUser userWithEmptyPassword) {

	 	// Attempt login with a valid username and empty password
	 	loginPage.authentication(userWithEmptyPassword);

	 	// Verify login failure and validation message
	 	softAssertion.assertEquals(LoginExpected.FAILED_MSG_WITH_EMPTY_PASSWORD,
	 	loginPage.getErrorMessage(),"Error message is not matching");
	 	softAssertion.assertEquals(LoginExpected.URL, loginPage.getUrl());
	 	softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Authentication" }, description = TC_LOGIN_007, 
	dataProvider = "lockedOutUser", dataProviderClass = TestDataProvider.class)
	 public void loginWithLockedOutUser(LoginUser lockedOutUser) {

	 	// Attempt login with a locked-out user
	 	loginPage.authentication(lockedOutUser);

	 	// Verify login failure and locked-out user message
	 	softAssertion.assertEquals(LoginExpected.FAILED_MSG_LOCKEDOUT_USER,loginPage.getErrorMessage());
	 	softAssertion.assertEquals(LoginExpected.URL, loginPage.getUrl());
	 	softAssertion.assertAll();
	}

	@Test(groups = { "Smoke", "Regression","Authentication" }, description = TC_LOGIN_008, 
	dataProvider = "standardUser", dataProviderClass = TestDataProvider.class)
	 public void logoutSuccessfully(LoginUser standardUser) {

	 	// Log in with valid credentials
	 	loginPage.authentication(standardUser);

		// Open the menu and log out
	 	inventoryPage.clickMenuIcon();
	 	inventoryPage.clickLogout();

	 	// Verify successful logout
	 	Assert.assertEquals(LoginExpected.URL, loginPage.getUrl());
	 	Assert.assertTrue(loginPage.isLoginButtonVisible());

	 	// Attempt to access the inventory page directly after logout
	 	loginPage.navigateTo(InventoryExpected.URL);

	 	// Verify unauthenticated access is blocked
	 	softAssertion.assertEquals(LoginExpected.URL, loginPage.getUrl());
	 	softAssertion.assertTrue(loginPage.isLoginButtonVisible());
	 	softAssertion.assertAll();
	}
}
