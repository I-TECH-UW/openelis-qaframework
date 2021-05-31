package org.openelisglobal.qaframework.automation.page;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;

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

	@Override
	public String getPageRejectUrl() {
		return "index.htm";
	}
}
