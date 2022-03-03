package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

/**
 * This class represents the Report ,Patient Status Report page
 */
public class PatientStatusReportPage extends Page {
	
	private static final String PAGE_PATH = "/Report";
	
	private static final String PATH_HOME = "/Dashboard";
	
	private static final By FIELD_LAB_NUMBER = By.id("patientLabNoSearchValue");
	
	private static final By FIELD_ORDER_NUMBER_FROM = By.id("accessionDirect");
	
	private static final By BUTTON_SEARCH = By.id("enhancedSearchButton");
	
	private static final By BUTTON_PRINT = By.id("printNew");
	
	public PatientStatusReportPage(Page parent) {
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
	
	public void enterLabNumber(String labNumber) {
		setText(FIELD_LAB_NUMBER, labNumber);
	}
	
	public void enterLabNumberFrom(String labNumber) {
		setText(FIELD_ORDER_NUMBER_FROM, labNumber);
	}
	
	public void clickSearchButton() {
		clickOn(BUTTON_SEARCH);
	}
	
	public void clickPrintButton() {
		clickOn(BUTTON_PRINT);
	}
}
