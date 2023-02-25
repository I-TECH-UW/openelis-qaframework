package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

public class UserManagementPage extends Page {

	private static final String PATH_HOME = "/Dashboard";

	private static final String PAGE_PATH = "/UnifiedSystemUserMenu";

	private static final By add = By.id("add");

	private static final By USERNAME_REQUIRED = By.xpath(
			"//*[@id=\"mainForm\"]/table/tbody/tr[4]/td/table[1]/tbody/tr[1]/td[1]/span");

	private static final By PASSWORD_REQUIRED = By.xpath(
			"//*[@id=\"mainForm\"]/table/tbody/tr[4]/td/table[1]/tbody/tr[3]/td[1]/span");

	private static final By REPEAT_PASSWORD_REQUIRED = By.xpath(
			"//*[@id=\"mainForm\"]/table/tbody/tr[4]/td/table[1]/tbody/tr[4]/td[1]/span");

	private static final By FIRST_NAME_REQUIRED = By.xpath(
			"//*[@id=\"mainForm\"]/table/tbody/tr[4]/td/table[1]/tbody/tr[6]/td[1]/span");

	private static final By LAST_NAME_REQUIRED = By.xpath(
			"//*[@id=\"mainForm\"]/table/tbody/tr[4]/td/table[1]/tbody/tr[7]/td[1]/span");

	private static final By PASSWORD_EXPIRY_DATE_REQUIRED = By.xpath(
			"//*[@id=\"mainForm\"]/table/tbody/tr[4]/td/table[1]/tbody/tr[8]/td[1]/span");

	private static final By USERNAME = By.id("userLoginName");

	private static final By PASSWORD = By.id("password1");

	private static final By CONFIRM_PASSWORD = By.id("password2");

	private static final By FIRST_NAME = By.id("userFirstName");

	private static final By LAST_NAME = By.id("userLastName");

	private static final By SAVE_BUTTON = By.xpath(
			"//*[@id=\"mainForm\"]/table/tbody/tr[5]/td/center/table/tbody/tr/td[1]/button");

	private static final By GLOBAL_ROLE_CHECKBOX = By.id("role_1");

	private static final By ALL_PERMISSIONS_CHECKBOX = By.xpath("//*[@id=\"rolesTable_1\"]/tbody/tr[2]/td[2]/input");


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

	public String getUsernameRequiredClass() {
		return findElement(USERNAME_REQUIRED).getAttribute("class");
	}

	public String getPasswordRequiredClass() {
		return findElement(PASSWORD_REQUIRED).getAttribute("class");
	}

	public String getRepeatPasswordRequiredClass() {
		return findElement(REPEAT_PASSWORD_REQUIRED).getAttribute("class");
	}

	public String getFirstNameRequiredClass() {
		return findElement(FIRST_NAME_REQUIRED).getAttribute("class");
	}

	public String getLastnameRequiredClass() {
		return findElement(LAST_NAME_REQUIRED).getAttribute("class");
	}

	public String getPasswordExpiryDateRequiredClass() {
		return findElement(PASSWORD_EXPIRY_DATE_REQUIRED).getAttribute("class");
	}

	public void enterUsername(String username) {
		setText(USERNAME, username);
	}

	public void enterPasswordAndConfirmPassword(String password) {
		setText(PASSWORD, password);
		setText(CONFIRM_PASSWORD, password);
	}

	public void enterFirstName(String firstName) {
		setText(FIRST_NAME, firstName);
	}

	public void enterLastName(String lastName) {
		setText(LAST_NAME, lastName);
	}

	public void clickSaveButton() {
		clickOn(SAVE_BUTTON);
	}

	public boolean globalRoleExists() {
		return hasElementWithoutWait(GLOBAL_ROLE_CHECKBOX);
	}

	public boolean allPermissionsCheckBoxExists() {
		return hasElementWithoutWait(ALL_PERMISSIONS_CHECKBOX);
	}

	public void assignGlobalRole() {
		if (globalRoleExists()) {
			clickOn(GLOBAL_ROLE_CHECKBOX);
		}
	}

	public void assignAllPermissions() {
		if (allPermissionsCheckBoxExists()) {
			clickOn(ALL_PERMISSIONS_CHECKBOX);
		}
	}
}
