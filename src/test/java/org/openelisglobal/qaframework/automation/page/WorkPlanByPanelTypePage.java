package org.openelisglobal.qaframework.automation.page;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;

/**
 * This class represents the Work Plan By Pannel Type Page
 */
public class WorkPlanByPanelTypePage extends Page {

	private static final String PAGE_PATH = "/WorkPlanByPanel";

	private static final String PATH_HOME = "/Dashboard";

	private static final By DROP_DOWN_PANEL_TYPES = By.id("testName");

	private static final By WORKPLAN_TESTS_TABLE = By.xpath("//*[@id=\"resultsDiv\"]/table");

	private static final By REMOVE_CHECKBOX = By.id("includedCheck_0");

	private static final By BUTTON_PRINT = By.id("print");

	public WorkPlanByPanelTypePage(Page parent) {
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

	public void selectPanelType(String panelType) {
		selectFrom(DROP_DOWN_PANEL_TYPES, panelType);
	}

	public boolean dropDownPanelTypesDisplayed() {
		return dropDownHasOptions(DROP_DOWN_PANEL_TYPES);
	}

	public boolean workplanTestsTableDisplayed() {
		return findElement(WORKPLAN_TESTS_TABLE).isDisplayed();
	}

	public void userChecksRemoveCheckBox() {
		clickOn(REMOVE_CHECKBOX);
		assertTrue(isChecked(REMOVE_CHECKBOX));
	}

	public void clickPrintWorkPlan() {
		clickOn(BUTTON_PRINT);
	}
}
