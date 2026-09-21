package project.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.base.BasePage;
import dorusqa.model.LoginUser;
//import project.testdata.model.LoginUser;
import project.utils.ElementWaits;

public class LoginPage extends BasePage {

	private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

	// Constructor
	public LoginPage(WebDriver d) {
		super(d);
		logger.debug("LoginPage initialized");
	}

	// Page Locators
	private By errorMessage = By.xpath("//h3[@data-test='error']");
	private By usernameInput = By.xpath("//input[@id='user-name']");
	private By passwordInput = By.xpath("//input[@id='password']");
	private By loginButton = By.xpath("//input[@id='login-button']");

	// Page Actions
	public void enterUsername(String username) {
		
		ElementWaits.waitForVisibility(driver, usernameInput).sendKeys(username);
	}

	public void enterPassword(String password) {

		ElementWaits.waitForVisibility(driver, passwordInput).sendKeys(password);
	}

	public void clickLogin() {

		ElementWaits.waitForVisibility(driver, loginButton).click();
	}

	public boolean isLoginButtonVisible() {

		return ElementWaits.waitForVisibility(driver, loginButton).isDisplayed();
	}

	public void pressEnter() {

		ElementWaits.waitForVisibility(driver, passwordInput).sendKeys(Keys.ENTER);
	}

	public void navigateTo(String url) {

		driver.navigate().to(url);
	}

	public String getErrorMessage() {

		return  ElementWaits.waitForVisibility(driver, errorMessage).getText();
	}

	// Page Behavior
	public void authentication(String Username, String Password) {

		logger.info("Starting authentication for user: {}", Username);

		enterUsername(Username);
		enterPassword(Password);
		clickLogin();

		logger.info("Authentication flow completed for user: {}", Username);
	}

	public void authentication(LoginUser user) {

		logger.info("Starting authentication for user: {}", user.getUsername());

		enterUsername(user.getUsername());
		enterPassword(user.getPassword());
		clickLogin();

		logger.info("Authentication flow completed for user: {}", user.getUsername());
	}
}
