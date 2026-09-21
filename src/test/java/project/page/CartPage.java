package project.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.base.BasePage;
import project.utils.ElementWaits;

public class CartPage extends BasePage {

	private static final Logger logger = LoggerFactory.getLogger(CartPage.class);

	// Constructor
	public CartPage(WebDriver d) {
		super(d);
		logger.debug("CartPage initialized");
	}

	// Page Locators
	private By firstProductName = By.xpath("//div[@data-test='inventory-item-name']");
	private By firstProductPrice = By.xpath("//div[@data-test='inventory-item-price']");
	private By firstProductRemoveButton = By.xpath("(//button[contains(@name,'remove-sauce')]");
	private By numberOfProducts = By.xpath("//div[@data-test='inventory-item']");
	private By checkoutButton = By.xpath("//button[@id='checkout']");
	private By cartProductNames = By.xpath("//div[@data-test='inventory-item-name']");
	private By cartProductPrices = By.xpath("//div[@data-test='inventory-item-price']");

	public By getRemoveBtnLocator(String productName) {

		return By.xpath("//div[text()='" + productName + "']/../..//button");
	}

	// Page Actions
	public String getProductName() {

		return ElementWaits.waitForVisibility(driver,firstProductName).getText();
	}

	public void removeProductByNameFromCart(String productName) {

		driver.findElement(getRemoveBtnLocator(productName)).click();
	}

	public List<String> getCartProductNames() {

		logger.debug("Retrieving all product names from cart");

		List<WebElement> productNameElements = driver.findElements(cartProductNames);
		List<String> productNameString = new ArrayList<>();

		for (WebElement e : productNameElements) {
			productNameString.add(e.getText());
		}

		logger.debug("Retrieved {} product names from cart", productNameString.size());

		return productNameString;
	}

	public List<String> getCartProductPrices() {

		logger.debug("Retrieving all product prices from cart");

		List<WebElement> selectedProductNamesElements = driver.findElements(cartProductPrices);
		List<String> selectedProductNamesString = new ArrayList<>();

		for (WebElement e : selectedProductNamesElements) {
			selectedProductNamesString.add(e.getText());
		}

		logger.debug("Retrieved {} product prices from cart", selectedProductNamesString.size());

		return selectedProductNamesString;
	}

	public String getFirstProductPrice() {

		return ElementWaits.waitForVisibility(driver,firstProductPrice).getText();
	}

	public void removeFirstItem() {

		ElementWaits.waitForVisibility(driver,firstProductRemoveButton).click();
	}

	public void clickCheckout() {

		ElementWaits.waitForVisibility(driver,checkoutButton).click();
	}

	public int getCartProductCount() {

		return driver.findElements(numberOfProducts).size();
	}

	public int getCartBadgeCount() {

		logger.debug("Retrieving shopping cart badge count");

		String value = driver.findElement(shoppingCartIcon).getText();

		if (value.isEmpty()) {
			logger.debug("Shopping cart badge is empty. Product count: 0");
			return 0;
		} else {
			int cartCount = Integer.parseInt(value);

			logger.debug("Shopping cart badge count: {}", cartCount);

			return cartCount;
		}
	}

	// Page Behavior
}
