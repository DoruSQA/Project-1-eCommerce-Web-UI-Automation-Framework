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

public class CheckoutTwoPage extends BasePage {

	private static final Logger logger = LoggerFactory.getLogger(CheckoutTwoPage.class);

	// Constructor
	public CheckoutTwoPage(WebDriver d) {
		super(d);
		logger.debug("CheckoutTwoPage initialized");
	}

	// Page Locators
	private By checkoutProductNames = By.xpath("//div[@data-test='inventory-item-name']");
	private By checkoutProductPrices = By.xpath("//div[@data-test='inventory-item-price']");
	private By finishButton = By.xpath("//button[@id='finish']");
	private By numberOfProducts = By.xpath("//div[@class='cart_item']");
	private By totalPriceCharged = By.xpath("//div[@data-test='total-label']");
	private By additionalTax = By.xpath("//div[@data-test='tax-label']");

	// Page Actions
	public List<String> getAllProductNames() {

		logger.debug("Retrieving all product names from checkout overview");

		List<WebElement> productElements = driver.findElements(checkoutProductNames);
		List<String> productString = new ArrayList<>();

		for (WebElement product : productElements) {
			productString.add(product.getText());
		}

		logger.debug("Retrieved {} product names from checkout overview", productString.size());

		return productString;
	}

	public String getProductName() {

		return getAllProductNames().get(0); 
	}

	public List<String> getAllProductPrices() {

		logger.debug("Retrieving all product prices from checkout overview");

		List<WebElement> productElements = driver.findElements(checkoutProductPrices);
		List<String> productPrices = new ArrayList<>();

		for (WebElement product : productElements) 
			productPrices.add(product.getText());
		

		logger.debug("Retrieved {} product prices from checkout overview", productPrices.size());

		return productPrices;
	}

	public String getProductPrice() {

		logger.debug("Retrieving first product price from checkout overview");

		String productPrice = getAllProductPrices().get(0);

		logger.debug("First product price: {}", productPrice);

		return productPrice;
	}

	public void clickFinish() {

		ElementWaits.waitForVisibility(driver,finishButton).click();
	}

	public double getTotalPriceCharged() {

		logger.debug("Retrieving total price charged");

		String price = ElementWaits.waitForVisibility(driver,totalPriceCharged).getText(); // Total: $43.18
		double totalPrice = Double.parseDouble(price.replaceAll("[^0-9.]", ""));

		logger.debug("Total price charged: {}", totalPrice);

		return totalPrice;
	}

	public double getAdditionalTaxCharged() {

		logger.debug("Retrieving additional tax charged");

		String price = ElementWaits.waitForVisibility(driver,additionalTax).getText(); // Tax: 5$
		double additionalTaxValue = Double.parseDouble(price.replaceAll("[^0-9.]", ""));

		logger.debug("Additional tax charged: {}", additionalTaxValue);

		return additionalTaxValue;
	}

	public int getCheckoutProductCount() {

		return driver.findElements(numberOfProducts).size();
	}

	public List<Double> parseProductPrices(List<String> products) {

		logger.debug("Parsing {} product prices from String to Double", products.size());

		List<Double> productPrices = new ArrayList<>();

		for (String product : products) 
			productPrices.add(Double.parseDouble(product.replaceAll("[^0-9.]", "")));

		logger.debug("Successfully parsed {} product prices", productPrices.size());

		return productPrices;
	}

	// Page Behavior
	public double calculateExpectedTotalPrice() {

		logger.info("Calculating expected total price");

		double total = 0;
		List<Double> productPrices = parseProductPrices(getAllProductPrices());

		for (Double item : productPrices) {
			total += item;
		}

		total += getAdditionalTaxCharged();

		logger.debug("Calculated expected total price: {}", total);

		return total;
	}
}
