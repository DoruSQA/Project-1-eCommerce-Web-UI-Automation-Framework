package project.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import dorusqa.testdata.*;
import project.base.BaseTest;
import project.testdata.expected.CheckoutCompleteExpected;
import project.testdata.expected.InventoryExpected;
import dorusqa.model.*;
import static dorusqa.z.Z.*;


public class E2EPurchaseTests extends BaseTest {


	@Test(groups = { "Smoke", "Regression","Checkout" }, description = TC_Checkout_008, 
	dataProvider = "E2EFlow", dataProviderClass = TestDataProvider.class)
	public void purchaseOneItemWithValidDetails(LoginUser standardUser, CheckoutUser validCheckoutUser) {

		// Log in and add a product to the cart
		loginPage.authentication(standardUser);
		inventoryPage.addProductToCartAndOpenIt(InventoryExpected.BACKPACK_PRODUCT);

		String expectedProductName = cartPage.getProductName();
		String expectedProductPrice = cartPage.getFirstProductPrice();
		int expectedNrOfProducts = cartPage.getCartProductCount();

		// Proceed to checkout and submit valid customer information
		cartPage.clickCheckout();
		checkoutStepOne.completeCheckoutInformation(validCheckoutUser);
		
		// Verify the product details and total price on the checkout overview
		Assert.assertEquals(expectedProductName, checkoutStepTwo.getProductName());
		Assert.assertEquals(expectedProductPrice, checkoutStepTwo.getProductPrice());
		Assert.assertEquals(expectedNrOfProducts, checkoutStepTwo.getCheckoutProductCount());
		Assert.assertEquals(checkoutStepTwo.calculateExpectedTotalPrice(), checkoutStepTwo.getTotalPriceCharged());
		
		// Complete the purchase
		checkoutStepTwo.clickFinish();
		
		// Verify the checkout completion details
		softAssertion.assertTrue(checkoutComplete.isConfirmationIconVisible());
		softAssertion.assertEquals(CheckoutCompleteExpected.CONFIRMATION_HEADER, checkoutComplete.getConfirmationTitle(),"Confirmation title doesn't match");
		softAssertion.assertEquals(CheckoutCompleteExpected.CONFIRMATION_DESCRIPTION,checkoutComplete.getConfirmationMessage(), "Confirmation description doesn't match");
		softAssertion.assertAll();
	}

	@Test(groups = {"Regression","Checkout" }, description = TC_Checkout_009, 
	dataProvider = "E2EFlow", dataProviderClass = TestDataProvider.class)
	public void purchaseMultipleItemsWithValidDetails(LoginUser standardUser, CheckoutUser validCheckoutUser) {

		// Log in and add multiple products to the cart
		loginPage.authentication(standardUser);
		inventoryPage.addMultipleProductsToTheCart(List.of(InventoryExpected.BACKPACK_PRODUCT,InventoryExpected.TSHIRT_PRODUCT));
		inventoryPage.clickCartBadgeIcon();

		List<String> expectedProductNames = cartPage.getCartProductNames();
		List<String> expectedProductPrices = cartPage.getCartProductPrices();
		int expectedNrOfProducts = cartPage.getCartProductCount();

		// Proceed to checkout and submit valid customer information
		cartPage.clickCheckout();
		checkoutStepOne.completeCheckoutInformation(validCheckoutUser);
		
		// Verify the product details and total price on the checkout overview
		Assert.assertEquals(expectedProductNames, checkoutStepTwo.getAllProductNames());
		Assert.assertEquals(expectedProductPrices, checkoutStepTwo.getAllProductPrices());
		Assert.assertEquals(expectedNrOfProducts, checkoutStepTwo.getCheckoutProductCount());
		Assert.assertEquals(checkoutStepTwo.calculateExpectedTotalPrice(), checkoutStepTwo.getTotalPriceCharged());
		
		// Complete the purchase
		checkoutStepTwo.clickFinish();
		
		// Verify the checkout completion details
		softAssertion.assertTrue(checkoutComplete.isConfirmationIconVisible());
		softAssertion.assertEquals(CheckoutCompleteExpected.CONFIRMATION_HEADER, checkoutComplete.getConfirmationTitle());
		softAssertion.assertEquals(CheckoutCompleteExpected.CONFIRMATION_DESCRIPTION,checkoutComplete.getConfirmationMessage());
		softAssertion.assertAll();
	}
}