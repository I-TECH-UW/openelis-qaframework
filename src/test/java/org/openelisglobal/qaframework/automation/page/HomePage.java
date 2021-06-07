package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This class represents the Home page
 */
public class HomePage extends Page {

	private static final String PATH_HOME = "/Dashboard.do";
	private static final By FIRST_LEVEL_MENU = By
			.xpath("//li/a[@id='menu_sample']");
	private static final By SECOND_LEVEL_MENU = By
			.xpath("//li/a[@id='menu_sample_add']");

	public HomePage(Page page) {
		super(page);
	}

	@Override
	public String getPageUrl() {
		return PATH_HOME;
	}

	public WebElement getLogOutLink() {
		return findElement(By.id("logout-form"));
	}

	public WebElement getFirstLevelMenu() {
		return findElement(FIRST_LEVEL_MENU);
	}

	public WebElement getSecondLevelMenu() {
		return findElement(SECOND_LEVEL_MENU);
	}

	public AddOrderPage goToAddOrderPage() {
		hoverOn(FIRST_LEVEL_MENU);
		getSecondLevelMenu().click();
		return new AddOrderPage(this);
	}
}
