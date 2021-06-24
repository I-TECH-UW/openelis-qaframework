package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This class represents the Home page
 */
public class HomePage extends Page {

	private static final String PATH_HOME = "/Dashboard.do";

	private static final By FIRST_LEVEL_MENU_ORDER = By.id("menu_sample");

	private static final By SECOND_LEVEL_MENU_ADD_ORDER = By
			.id("menu_sample_add");

	private static final By FIRST_LEVEL_MENU_RESULTS = By.id("menu_results");

	private static final By SECOND_LEVEL_MENU_ENTER_BY_UNIT = By
			.id("menu_results_logbook");

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
		return findElement(FIRST_LEVEL_MENU_ORDER);
	}

	public WebElement getSecondLevelMenu() {
		return findElement(SECOND_LEVEL_MENU_ADD_ORDER);
	}

	public AddOrderPage goToAddOrderPage() {
		hoverOn(FIRST_LEVEL_MENU_ORDER);
		getSecondLevelMenu().click();
		return new AddOrderPage(this);
	}

	public ResultsEntryPage selectsResultAndClickEnterByUnit() {
		hoverOn(FIRST_LEVEL_MENU_RESULTS);
		clickOn(SECOND_LEVEL_MENU_ENTER_BY_UNIT);
		return new ResultsEntryPage(this);
	}
}
