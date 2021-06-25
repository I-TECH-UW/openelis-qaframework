package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

public class SearchResultsByOrderPage extends Page {

	private static final String PAGE_PATH = "/PatientResults.do";

	private static final By SEARCH_FORM = By.id("searchDiv");

	private static final String PATH_HOME = "/Dashboard.do";

	public SearchResultsByOrderPage(Page parent) {
		super(parent);
	}

	@Override
	public String getPageUrl() {
		return PAGE_PATH;
	}

	public Boolean hasSearchForm() {
		return hasElement(SEARCH_FORM);
	}

	public HomePage goToHomePage() {
		this.goToPage(PATH_HOME);
		return new HomePage(this);
	}
}
