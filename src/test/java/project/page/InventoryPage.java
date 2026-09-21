package project.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.base.BasePage;
import project.utils.ElementWaits;

public class InventoryPage extends BasePage {

	private static final Logger logger = LoggerFactory.getLogger(InventoryPage.class);

	// Constructor
	public InventoryPage(WebDriver d) {
		super(d);
		logger.debug("InventoryPage initialized");
	}

	// Page Locators
	private By sortingDropdown = By.xpath("//select[@data-test='product-sort-container']");
	private By logoutButton = By.xpath("//a[@id='logout_sidebar_link']");
	private By shoppingCartBadge = By.xpath("//span[@data-test='shopping-cart-badge']");
	private By inventoryItemNames = By.xpath("//div[@data-test='inventory-item-name']");
	private By inventoryItemPrices = By.xpath("//div[@data-test='inventory-item-price']");
	private By productPrice = By.xpath("//*[text()='Remove']/preceding-sibling::div");
	private By productName = By.xpath("//*[text()='Remove']/../../descendant::div[@data-test='inventory-item-name']");
	private By nrOfProducts = By.xpath("//button[text()='Remove']");
	private By productNameList = By.xpath("//button[text()='Remove']/../..//a");
	private By productPriceList = By.xpath("//button[text()='Remove']/../div[@class='inventory_item_price']");

	public By getAddToCartButtonLocator(String productName) {

		return By.xpath("//div[text()='" + productName + "']/../../following-sibling::div/button");
	}

	public By getproductNameLabelLocator(String productName) {

		return By.xpath("//div[text()='" + productName + "']");
	}

	// Page Actions
	public void addProductByName(String productName) {

		ElementWaits.waitToBeInteractable(driver,getAddToCartButtonLocator(productName)).click();
	}

	public void addMultipleProductsToTheCart(List<String> products) {

		logger.info("Adding {} products to cart", products.size());

		for (String p : products) 
			addProductByName(p);
		

		logger.debug("Finished adding multiple products to cart");
	}

	public List<String> getAddedProductNameList() {

		logger.debug("Retrieving added product names from cart");

		List<WebElement> selectedProductNamesElements = driver.findElements(productNameList);
		List<String> selectedProductNamesString = new ArrayList<>();

		for (WebElement e : selectedProductNamesElements) 
			selectedProductNamesString.add(e.getText());
		
		logger.debug("Retrieved {} added product names", selectedProductNamesString.size());

		return selectedProductNamesString;
	}

	public List<String> getAddedProductPriceList() {

		logger.debug("Retrieving added product prices from cart");

		List<WebElement> selectedProductNamesElements = driver.findElements(productPriceList);
		List<String> selectedProductNamesString = new ArrayList<>();

		for (WebElement e : selectedProductNamesElements) 
			selectedProductNamesString.add(e.getText());

		logger.debug("Retrieved {} added product prices", selectedProductNamesString.size());

		return selectedProductNamesString;
	}

	public void clickOnProductByName(String productName) { // separare!

		driver.findElement(getproductNameLabelLocator(productName)).click();
	}

	public List<String> getAllProductNames() {

		logger.debug("Retrieving all inventory product names");

		List<WebElement> selectedProductNamesElements = driver.findElements(inventoryItemNames);
		List<String> selectedProductNamesString = new ArrayList<>();

		for (WebElement e : selectedProductNamesElements) 
			selectedProductNamesString.add(e.getText());

		logger.debug("Retrieved {} inventory product names", selectedProductNamesString.size());

		return selectedProductNamesString;
	}

	public List<Double> getAllProductPrices() {

		logger.debug("Retrieving all inventory product prices");

		List<WebElement> selectedProductNamesElements = driver.findElements(inventoryItemPrices);
		List<Double> selectedProductNamesString = new ArrayList<>();

		for (WebElement e : selectedProductNamesElements) {
			String price = e.getText();
			double priceParing = Double.parseDouble(price.replaceAll("[^0-9.]", ""));
			selectedProductNamesString.add(priceParing);
		}

		logger.debug("Retrieved {} inventory product prices", selectedProductNamesString.size());

		return selectedProductNamesString;
	}

	public int getAddedProductCount() {

		return driver.findElements(nrOfProducts).size();
	}

	public void sortProductsBy(String option) {

		logger.info("Sorting products by option: {}", option);

		Select select = new Select(driver.findElement(sortingDropdown));

		select.selectByVisibleText(option);

		logger.debug("Products sorted by: {}", option);
	}

	public boolean isProductFilterIconDisplayed() {

		return ElementWaits.waitForVisibility(driver,sortingDropdown).isDisplayed();
	}

	public int getCartBadgeIconCount() {

		return Integer.parseInt(ElementWaits.waitForVisibility(driver,shoppingCartBadge).getText());
	}

	public void clickCartBadgeIcon() {

		ElementWaits.waitForVisibility(driver, shoppingCartIcon).click();
	}

	public void clickMenuIcon() {

		ElementWaits.waitForVisibility(driver,menuButton).click();
	}

	public String getAddedProductPrice() {

		return  ElementWaits.waitForVisibility(driver,productPrice).getText();
	}

	public String getAddedProductName() {

		return  ElementWaits.waitForVisibility(driver,productName).getText();
	}

	public void clickLogout() {

		ElementWaits.waitForVisibility(driver,logoutButton).click();
	}

	// Page Behavior
	public boolean isInventoryPageLoaded() {

		logger.debug("Checking if inventory page is loaded");

		boolean isLoaded = isMenuIconDisplayed() && isProductFilterIconDisplayed() && isShoppingCartIconDisplayed();

		logger.debug("Inventory page loaded: {}", isLoaded);

		return isLoaded;
	}

	public void addProductToCartAndOpenIt(String productName) {

		logger.info("Adding product to cart and opening shopping cart: {}", productName);

		addProductByName(productName);
		clickCartBadgeIcon();

		logger.debug("Product added and shopping cart opened: {}", productName);
	}
	
	public void addProductToCart(String productName) {

		logger.info("Adding product to cart and opening shopping cart: {}", productName);

		addProductByName(productName);

		logger.debug("Product added and shopping cart opened: {}", productName);
	}
}
