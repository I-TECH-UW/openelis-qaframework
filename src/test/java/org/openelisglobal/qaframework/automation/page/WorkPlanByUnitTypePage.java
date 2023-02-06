package org.openelisglobal.qaframework.automation.page;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;

/**
 * This class represents the Work Plan By Unit Type Page
 */
public class WorkPlanByUnitTypePage extends Page {

	private static final String PAGE_PATH = "/WorkPlanByTestSection";

	private static final String PATH_HOME = "/Dashboard";

	private static final By DROP_DOWN_UNIT_TYPES = By.id("testSectionId");

	private static final By WORKPLAN_TESTS_TABLE = By.xpath("//*[@id=\"mainForm\"]/table/tbody/tr[4]/td/table");

	private static final By BUTTON_PRINT = By.id("print");

	private static final By REMOVE_CHECKBOX = By.id("includedCheck_0");

	public WorkPlanByUnitTypePage(Page parent) {
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

	public void selectUnitType(String unitType) {
		selectFrom(DROP_DOWN_UNIT_TYPES, unitType);
	}

	public boolean dropDownUnitTypesDisplayed() {
		return dropDownHasOptions(DROP_DOWN_UNIT_TYPES);
	}

	public boolean workplanTestsTableDisplayed() {
		return findElement(WORKPLAN_TESTS_TABLE).isDisplayed();
	}

	public void clickPrintWorkPlan() {
		clickOn(BUTTON_PRINT);
	}

	public void userChecksRemoveCheckBox() {
		clickOn(REMOVE_CHECKBOX);
		assertTrue(isChecked(REMOVE_CHECKBOX));
	}
}
