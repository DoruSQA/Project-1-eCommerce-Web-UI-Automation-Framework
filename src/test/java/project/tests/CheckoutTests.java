package project.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import project.base.BaseTest;
import dorusqa.testdata.*;
import project.testdata.expected.CartExpected;
import project.testdata.expected.CheckoutOneExpected;
import project.testdata.expected.InventoryExpected;
import dorusqa.model.*;
import static dorusqa.z.Z.*;


public class CheckoutTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUpCheckout()
    {
        // Log in and verify the inventory page is loaded
        loginPage.authentication(standardUser);
        Assert.assertEquals(InventoryExpected.URL, inventoryPage.getUrl());
        Assert.assertTrue(inventoryPage.isInventoryPageLoaded());
        
        // Add a product to the cart and open the shopping cart
        inventoryPage.addProductToCartAndOpenIt(InventoryExpected.TSHIRT_PRODUCT);
        Assert.assertEquals(CartExpected.PAGE_HEADING, cartPage.getPageHeading());
        Assert.assertEquals(CartExpected.URL, cartPage.getUrl());
        
        // Proceed to checkout and verify the checkout page is loaded
        cartPage.clickCheckout();
        Assert.assertEquals(CheckoutOneExpected.URL, checkoutStepOne.getUrl());
        Assert.assertEquals(CheckoutOneExpected.PAGE_HEADING, checkoutStepOne.getPageHeading());
        
    }


	@Test(groups = {"Regression","Checkout" }, description = TC_Checkout_001 , 
	dataProvider = "checkoutUserWithEmptyFirstName", dataProviderClass = TestDataProvider.class)
    public void checkoutWithEmptyFirstName(CheckoutUser checkoutUserWithEmptyFirstName) {
        
        // Submit the checkout form with an empty first name
        checkoutStepOne.completeCheckoutInformation(checkoutUserWithEmptyFirstName);

        // Submit the checkout form with an empty first name
        softAssertion.assertEquals(CheckoutOneExpected.URL, checkoutStepOne.getUrl());
        softAssertion.assertEquals(CheckoutOneExpected.FAILED_MSG_WITH_EMPTY_FIRSTNAME, checkoutStepOne.getErrorMessage());
        softAssertion.assertAll();
    }

	@Test(groups = {"Regression","Checkout" }, description = TC_Checkout_002, 
	dataProvider = "checkoutUserWithEmptyLastName", dataProviderClass = TestDataProvider.class)
    public void checkoutWithEmptyLastName(CheckoutUser checkoutUserWithEmptyLastName) {

        // Submit the checkout form with an empty last name
        checkoutStepOne.completeCheckoutInformation(checkoutUserWithEmptyLastName);

        // Verify checkout remains on the same page and the expected error is displayed
        softAssertion.assertEquals(CheckoutOneExpected.URL, checkoutStepOne.getUrl());
        softAssertion.assertEquals(CheckoutOneExpected.FAILED_MSG_WITH_EMPTY_LASTNAME, checkoutStepOne.getErrorMessage());
        softAssertion.assertAll();
    }

	@Test(groups = {"Regression","Checkout" }, description = TC_Checkout_003, 
	dataProvider = "checkoutUserWithEmptyZipCode", dataProviderClass = TestDataProvider.class)
    public void checkoutWithEmptyZipCode(CheckoutUser checkoutUserWithEmptyZipCode) {

        // Submit the checkout form with an empty ZIP code
        checkoutStepOne.completeCheckoutInformation(checkoutUserWithEmptyZipCode);

        // Verify checkout remains on the same page and the expected error is displayed
        softAssertion.assertEquals(CheckoutOneExpected.URL, checkoutStepOne.getUrl());
        softAssertion.assertEquals(CheckoutOneExpected.FAILED_MSG_WITH_EMPTY_ZIPCODE, checkoutStepOne.getErrorMessage());
        softAssertion.assertAll();
    }

	@Test(groups = {"Regression","Checkout" }, description = TC_Checkout_004, 
	dataProvider = "checkoutUserWithWhitespaceOnly", dataProviderClass = TestDataProvider.class)
    public void checkoutWithWhitespaceOnly(CheckoutUser checkoutUserWithWhitespaceOnly) {

        // Submit the checkout form with whitespace-only values
        checkoutStepOne.completeCheckoutInformation(checkoutUserWithWhitespaceOnly);
        
        // Verify the checkout page remains accessible
        softAssertion.assertEquals(CheckoutOneExpected.URL, checkoutStepOne.getUrl());
        softAssertion.assertEquals(CheckoutOneExpected.PAGE_HEADING, checkoutStepOne.getPageHeading());
        softAssertion.assertAll();
    }

	@Test(groups = {"Regression","Checkout" }, description = TC_Checkout_005, 
	dataProvider = "checkoutUserWithFirstNameExceedingMaxLength", dataProviderClass = TestDataProvider.class)
    public void checkoutWithFirstNameExceedingMaxLength(CheckoutUser checkoutUserWithFirstNameExceedingMaxLength) {
        
        // Submit the checkout form with a first name exceeding the maximum length
        checkoutStepOne.completeCheckoutInformation(checkoutUserWithFirstNameExceedingMaxLength);
        
        // Verify checkout remains on the same page and the expected error is displayed
        softAssertion.assertEquals(CheckoutOneExpected.URL, checkoutStepOne.getUrl());
        softAssertion.assertEquals(CheckoutOneExpected.FAILED_MSG_WITH_EXEEDING_FIRST_NAME, checkoutStepOne.getErrorMessage());
        softAssertion.assertAll();
        
    }

	@Test(groups = {"Regression","Checkout" }, description = TC_Checkout_006, 
	dataProvider = "checkoutUserWithLastNameExceedingMaxLength", dataProviderClass = TestDataProvider.class)
    public void checkoutWithLastNameExceedingMaxLength(CheckoutUser checkoutUserWithLastNameExceedingMaxLength) {
        
        // Submit the checkout form with a last name exceeding the maximum length
        checkoutStepOne.completeCheckoutInformation(checkoutUserWithLastNameExceedingMaxLength);
        
        // Verify checkout remains on the same page and the expected error is displayed
        softAssertion.assertEquals(CheckoutOneExpected.URL, checkoutStepOne.getUrl());
        softAssertion.assertEquals(CheckoutOneExpected.FAILED_MSG_WITH_EXEEDING_LAST_NAME, checkoutStepOne.getErrorMessage());
        softAssertion.assertAll();
    }

	@Test(groups = {"Regression","Checkout" }, description = TC_Checkout_007, 
	dataProvider = "checkoutUserWithZipCodeExceedingMaxLength", dataProviderClass = TestDataProvider.class)
    public void checkoutWithZipCodeExceedingMaxLength(CheckoutUser checkoutUserWithZipCodeExceedingMaxLength) {
        
        // Submit the checkout form with a ZIP code exceeding the maximum length
        checkoutStepOne.completeCheckoutInformation(checkoutUserWithZipCodeExceedingMaxLength);
        
        // Verify checkout remains on the same page and the expected error is displayed
        softAssertion.assertEquals(CheckoutOneExpected.URL, checkoutStepOne.getUrl());
        softAssertion.assertEquals(CheckoutOneExpected.FAILED_MSG_WITH_EXEEDING_ZIP_CODE, checkoutStepOne.getErrorMessage());
        softAssertion.assertAll();
    }
}

