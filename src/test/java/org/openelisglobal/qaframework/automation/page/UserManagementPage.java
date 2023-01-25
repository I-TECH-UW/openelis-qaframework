package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

public class UserManagementPage extends Page {

	private static final String PATH_HOME = "/Dashboard";

	private static final String PAGE_PATH = "/UnifiedSystemUserMenu";

	private static final By add = By.id("add");

	public UserManagementPage(Page parent) {
		super(parent);
	}

	@Override
	public String getPageUrl() {
		return PAGE_PATH;
	}

	public HomePage goToHomePage() {
		this.goToPage(PATH_HOME);
		return new HomePage(this);
	}

	public void clickAddNewUserButton() {
		clickOn(add);
	}
}
