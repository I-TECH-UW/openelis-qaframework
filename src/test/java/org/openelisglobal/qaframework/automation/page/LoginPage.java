package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class represents the Login page
 */
public class LoginPage extends Page {

	private static final String LOGIN_PATH = "/LoginPage.do";
	private static final By USERNAME = By.id("loginName");
	private static final By PASSWORD = By.id("password");
	private static final By SUBMIT = By.id("submitButton");

	static final String LOGOUT_PATH = "/logout";

	private String username;

	private String password;

	public LoginPage(WebDriver driver) {
		super(driver);
		username = properties.getUsername();
		password = properties.getPassword();
	}

	@Override
	public void go() {
		goToPage(LOGIN_PATH);
	}

	@Override
	public String getPageUrl() {
		return LOGIN_PATH;
	}

	public void enterUsername(String username) {
		findElement(USERNAME).sendKeys(username);
	}

	public void enterPassword(String password) {
		findElement(PASSWORD).sendKeys(password);
	}

	public WebElement getLoginButton() {
		return findElement(SUBMIT);
	}

	public HomePage goToHomePage() {
		go();
		enterUsername(this.username);
		enterPassword(this.password);
		getLoginButton().click();
		return new HomePage(this);
	}
}
