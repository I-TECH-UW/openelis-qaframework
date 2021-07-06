package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

public class ResultsEntryPage extends Page {

	private static final String PAGE_PATH = "/LogbookResults.do?testSectionId=";

	private static final String PATH_HOME = "/Dashboard.do";

	private static final By FIELD_TEST_DATE = By.id("testDate_0");

	private static final By FIELD_TEST_RESULT = By.id("results_0");

	private static final By LABEL_IMAGE_NON_CONFORMING = By
			.xpath("//img[contains(@src,'nonconforming')]");

	private static final By FIELD_LAB_NUMBER_SEARCH = By.id("labnoSearch");

	private static final By BUTTON_LAB_NUMBER_SEARCH = By
			.xpath("//*[@id='mainForm']/table/tbody/tr[4]/td/div[3]/div[1]/input[2]");

	public ResultsEntryPage(Page parent) {
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

	public String getTestDateValue() {
		return getValue(FIELD_TEST_DATE);
	}

	public void enterTestDate(String date) {
		setText(FIELD_TEST_DATE, date);
	}

	public Boolean hasNonConformingFlag() {
		return hasElement(LABEL_IMAGE_NON_CONFORMING);
	}

	public void enterSearchAccesionNumber(String accesionNumber) {
		setText(FIELD_LAB_NUMBER_SEARCH, accesionNumber);
	}

	public SearchResultsByOrderPage clickOnLabNumberSearch() {
		clickOn(BUTTON_LAB_NUMBER_SEARCH);
		return new SearchResultsByOrderPage(this);
	}

	public void enterTestResult(String value) {
		setText(FIELD_TEST_RESULT, value);
	}

	public String getTestResultValue() {
		return getValue(FIELD_TEST_RESULT);
	}

	public void clickOnDateField() {
		clickOn(FIELD_TEST_DATE);
	}

}
