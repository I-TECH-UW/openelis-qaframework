package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

public class AdminPage extends Page {

	private static final String PAGE_PATH = "/MasterListsPage";

	private static final By TEST_MGT_LINK = By.linkText("Test Management");

	private static final By USER_MGT_LINK = By.linkText("User Management");

	private static final By ORDER_ENTRY_CONFIG_LINK = By.linkText("Order Entry Configuration");

	public AdminPage(Page parent) {
		super(parent);
	}

	@Override
	public String getPageUrl() {
		return PAGE_PATH;
	}

	public TestManagementPage goToTestManagementPage() {
		clickOn(TEST_MGT_LINK);
		return new TestManagementPage(this);
	}

	public UserManagementPage goToUserManagementPage() {
		clickOn(USER_MGT_LINK);
		return new UserManagementPage(this);
	}

	public ConfigurableItemsPage goToOrderEntryConfiguration() {
		clickOn(ORDER_ENTRY_CONFIG_LINK);
		return new ConfigurableItemsPage(this);
	}
}
