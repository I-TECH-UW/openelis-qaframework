package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends Page {

	public static final String LOGIN_PATH = "/LoginPage.do";

	static final By USERNAME = By.id("username");
	static final By PASSWORD = By.id("password");
	static final By LOGIN = By.id("loginButton");

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
		findElement(By.id("loginName")).sendKeys(username);
	}

	public void enterPassword(String password) {
		findElement(By.id("password")).sendKeys(password);
	}

	public WebElement getLoginButton() {
		return findElement(By.id("submitButton"));
	}
}
