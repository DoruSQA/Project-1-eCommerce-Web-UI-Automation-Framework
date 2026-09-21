package project.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import project.base.BaseTest;
import project.testdata.expected.CartExpected;
import project.testdata.expected.InventoryExpected;
import static dorusqa.z.Z.*;


public class CartTests extends BaseTest {


	@BeforeMethod(alwaysRun = true)
	public void setUpCart()
	{
		// Log in and verify the inventory page is loaded
		loginPage.authentication(standardUser);
		Assert.assertEquals(InventoryExpected.URL, inventoryPage.getUrl());
		Assert.assertTrue(inventoryPage.isInventoryPageLoaded());
	}
	
	@Test(groups = {"Smoke", "Regression", "Shopping Cart"},description = TC_CART_001)
	public void addOneProductToCart() {

		// Add a product to the cart
		inventoryPage.addProductToCart(InventoryExpected.BACKPACK_PRODUCT);
		
		String expectedProductName = inventoryPage.getAddedProductName();
		String expectedProductPrice = inventoryPage.getAddedProductPrice();
		int expectedNrOfProducts = inventoryPage.getAddedProductCount();
		
		// Verify the cart badge is updated
		Assert.assertEquals(expectedNrOfProducts, inventoryPage.getCartBadgeIconCount());
		
		// Open the shopping cart
		inventoryPage.clickCartBadgeIcon();

		// Verify the product details on the cart page
		Assert.assertEquals(CartExpected.PAGE_HEADING, cartPage.getPageHeading());
		Assert.assertEquals(CartExpected.URL, cartPage.getUrl());
		softAssertion.assertEquals(expectedProductName, cartPage.getProductName());
		softAssertion.assertEquals(expectedProductPrice, cartPage.getFirstProductPrice());
		softAssertion.assertAll();
	}

	@Test(groups = {"Regression", "Shopping Cart"}, description = TC_CART_002)
	public void addMultipleProductsToCart() {

		// Add multiple products to the cart
		inventoryPage.addMultipleProductsToTheCart(List.of(InventoryExpected.BACKPACK_PRODUCT,InventoryExpected.TSHIRT_PRODUCT));
		
		List<String> expectedProductNameList = inventoryPage.getAddedProductNameList();
		List<String> expectedProductPrices = inventoryPage.getAddedProductPriceList();
		int numberOfSelectedProducts = expectedProductNameList.size();

		// Verify the cart badge reflects the number of selected products
		Assert.assertEquals(numberOfSelectedProducts, inventoryPage.getCartBadgeIconCount());
		
		// Open the shopping cart
		inventoryPage.clickCartBadgeIcon();
		
		// Verify the selected products and their details on the cart page
		softAssertion.assertEquals(CartExpected.PAGE_HEADING, cartPage.getPageHeading());
		softAssertion.assertEquals(CartExpected.URL, cartPage.getUrl());
		softAssertion.assertEquals(expectedProductNameList, cartPage.getCartProductNames());
		softAssertion.assertEquals(expectedProductPrices, cartPage.getCartProductPrices());
		softAssertion.assertAll();
	}

	@Test(groups = {"Regression", "Shopping Cart"}, description = TC_CART_003)
	public void removeAnExistingProductFromCart() {

		// Add a product to the cart and open the shopping cart
		inventoryPage.addProductToCartAndOpenIt(InventoryExpected.BACKPACK_PRODUCT);
		
		int cartBadgeCount = cartPage.getCartBadgeCount();
		int nrOfItemsInCart = cartPage.getCartProductCount();

		// Remove the selected product from the cart
		cartPage.removeProductByNameFromCart(InventoryExpected.BACKPACK_PRODUCT);
		
		// Verify the cart count is updated after removing the product
		softAssertion.assertEquals(cartBadgeCount - 1, cartPage.getCartBadgeCount());
		softAssertion.assertEquals(nrOfItemsInCart - 1, cartPage.getCartProductCount());
		softAssertion.assertAll();
	}
}