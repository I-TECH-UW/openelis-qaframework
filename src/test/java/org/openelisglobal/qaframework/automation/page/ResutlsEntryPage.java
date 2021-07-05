package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

public class ResutlsEntryPage extends Page {

	private static final String PAGE_PATH = "/LogbookResults.do?testSectionId=";

	private static final String PATH_HOME = "/Dashboard.do";

	private static final By FIELD_TEST_DATE = By.id("testDate_1");

	public ResutlsEntryPage(Page parent) {
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

}
