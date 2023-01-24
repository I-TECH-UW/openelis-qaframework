package org.openelisglobal.qaframework.automation.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TestManagementPage extends Page {

	private static final String PATH_HOME = "/Dashboard";

	private static final String PAGE_PATH = "/TestManagementConfigMenu";

	private static final By MASTER_LIST_SUB_MENU = By.cssSelector("#masterListsSubMenu li a");

	private static final By TEST_MGT_LINK = By.linkText("Test Management");

	private static final By TestManagementConfigMenu = By.cssSelector("#mainForm ul li .textButton");

	public TestManagementPage(Page parent) {
		super(parent);
	}

	public HomePage goToHomePage() {
		this.goToPage(PATH_HOME);
		return new HomePage(this);
	}

	@Override
	public String getPageUrl() {
		return PAGE_PATH;
	}

	public boolean goToTestManagementConfigMenu() {
		List<WebElement> menuElements = getDriver().findElements(MASTER_LIST_SUB_MENU);
		for (WebElement e : menuElements) {
			String menuItem = e.getText();
			if (menuItem.equals("Test Management")) {
				clickOn(TEST_MGT_LINK);
				return true;
			}

		}
		return false;
	}

	public List<String> getTestManagementConfigMenuItems() {
		List<String> menuItems = new ArrayList<>();
		List<WebElement> elements = getDriver().findElements(TestManagementConfigMenu);
		for (WebElement e : elements) {
			String item = e.getAttribute("value");
			menuItems.add(item);
		}
		return menuItems;
	}

	public int getTestManagementConfigMenuItemsCount() {
		return getDriver().findElements(TestManagementConfigMenu).size();
	}
}
