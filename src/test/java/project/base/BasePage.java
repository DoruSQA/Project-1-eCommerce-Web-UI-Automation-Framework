package project.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import project.utils.ElementWaits;

public abstract class BasePage {

	public WebDriver driver;

	// Base Parent Constructor
	public BasePage(WebDriver d) {
		this.driver = d;
	}

	// Base Page Locators
	public By pageHeading = By.xpath("//span[@data-test = 'title']");
	public By shoppingCartIcon = By.xpath("//a[@data-test='shopping-cart-link']");
	public By menuButton = By.xpath("//button[@id='react-burger-menu-btn']");

	// Base Page Actions
	public String getUrl()
	{
		return driver.getCurrentUrl();
	}
	public String getPageHeading() {
		
		return ElementWaits.waitForVisibility(driver,pageHeading).getText();
	}
	public boolean isMenuIconDisplayed() {
		
		return ElementWaits.waitForVisibility(driver, menuButton).isDisplayed();
	}
	public boolean isShoppingCartIconDisplayed() {
		
		return ElementWaits.waitForVisibility(driver, shoppingCartIcon).isDisplayed();
	}
	
}
