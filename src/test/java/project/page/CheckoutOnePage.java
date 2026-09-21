package project.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.base.BasePage;
import dorusqa.model.*;
import project.utils.ElementWaits;

public class CheckoutOnePage extends BasePage {

	private static final Logger logger = LoggerFactory.getLogger(CheckoutOnePage.class);

	// Constructor
	public CheckoutOnePage(WebDriver d) {
		super(d);
		logger.debug("CheckoutOnePage initialized");
	}

	// Page Locators
	private By errorMessage = By.xpath("//h3[@data-test='error']");
	private By firstNameInput = By.xpath("//input[@id='first-name']");
	private By lastNameInput = By.xpath("//input[@id='last-name']");
	private By zipCodeInput = By.xpath("//input[@id='postal-code']");
	private By continueButton = By.xpath("//input[@id='continue']");

	// Page Actions
	public void enterFirstName(String firstName) {

		ElementWaits.waitForVisibility(driver,firstNameInput).sendKeys(firstName);
	}

	public void enterLastName(String lastName) {

		ElementWaits.waitForVisibility(driver,lastNameInput).sendKeys(lastName);
	}

	public void enterZipCode(String zipCode) {

		ElementWaits.waitForVisibility(driver,zipCodeInput).sendKeys(zipCode);
	}

	public void clickContinue() {

		ElementWaits.waitForVisibility(driver,continueButton).click();
	}

	public String getErrorMessage() {


		if (driver.findElements(errorMessage).isEmpty()) 
			return "";
		else
			return  driver.findElement(errorMessage).getText();
	}

	// Page Behavior
	public void completeCheckoutInformation(CheckoutUser checkoutUser) {

		logger.info("Completing checkout information for user");

		enterFirstName(checkoutUser.getFirstName());
		enterLastName(checkoutUser.getLastName());
		enterZipCode(checkoutUser.getZipCode());
		clickContinue();

		logger.info("Checkout information completed");
	}
}
