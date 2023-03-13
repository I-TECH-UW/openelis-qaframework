package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

public class ConfigurableItemsPage extends Page {

	private static final String PAGE_PATH = "/MasterListsPage";

	private static final By MODIFY_BUTTON = By.id("edit");

	private static final By AUTO_FILL_COLLECTION_DATE_CHECKBOX = By.id("selectedIDs1");

	private static final By TRUE_RADIO_BUTTON = By.id("value1");

	private static final By FALSE_RADIO_BUTTON = By.id("value2");

	private static final By BUTTON_SAVE_VALIDATION = By.xpath(
			"//*[@id=\"mainForm\"]/table/tbody/tr[5]/td/center/table/tbody/tr/td[1]/button");

	public ConfigurableItemsPage(Page parent) {
		super(parent);
	}

	@Override
	public String getPageUrl() {
		return PAGE_PATH;
	}

	public boolean modifyButtonExists() {
		return hasElement(MODIFY_BUTTON);
	}

	public void checkAutoFillDateCheckBox() {
		clickOn(AUTO_FILL_COLLECTION_DATE_CHECKBOX);
	}

	public void clickModifyButton() {
		clickOn(MODIFY_BUTTON);
	}

	public void changeRadioGroupValue() {
		clickOn(TRUE_RADIO_BUTTON);
		clickOn(BUTTON_SAVE_VALIDATION);
	}
}
