package project.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.base.BasePage;
import project.utils.ElementWaits;

public class CheckoutCompletePage extends BasePage {

	private static final Logger logger = LoggerFactory.getLogger(CheckoutCompletePage.class);

	// Constructor
	public CheckoutCompletePage(WebDriver d) {
		super(d);
		logger.debug("CheckoutCompletePage initialized");
	}

	// Page Locators
	private By confirmationImage = By.xpath("//img[@data-test='pony-express']");
	private By confirmationTitle = By.xpath("//h2[@data-test='complete-header']");
	private By confirmationMessage = By.xpath("//div[@data-test='complete-text']");

	// Page Actions
	public boolean isConfirmationIconVisible() {
		
		return  ElementWaits.waitToBeInteractable(driver,confirmationImage).isDisplayed();
	}

	public String getConfirmationTitle() {

		return  ElementWaits.waitToBeInteractable(driver,confirmationTitle).getText();
	}

	public String getConfirmationMessage() {

		return  ElementWaits.waitToBeInteractable(driver,confirmationMessage).getText();
	}
}
