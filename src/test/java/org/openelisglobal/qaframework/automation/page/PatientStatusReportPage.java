package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

public class PatientStatusReportPage extends Page {

	public PatientStatusReportPage(Page parent) {
		super(parent);
	}

	private static final String PAGE_PATH = "/Report.do";

	private static final String PATH_HOME = "/Dashboard.do";

	private static final By FIELD_LAB_NUMBER = By.id("patientLabNoSearchValue");

	private static final By BUTTON_SEARCH = By.id("enhancedSearchButton");

	private static final By BUTTON_PRINT = By.id("printNew");

	@Override
	public String getPageUrl() {
		return PAGE_PATH;
	}

	public HomePage goToHomePage() {
		this.goToPage(PATH_HOME);
		return new HomePage(this);
	}

	public void enterLabNUmber(String labNumber) {
		setText(FIELD_LAB_NUMBER, labNumber);
	}

	public void clickSearchButton() {
		clickOn(BUTTON_SEARCH);
	}

	public void clickPrintButton() {
		clickOn(BUTTON_PRINT);
	}

}
