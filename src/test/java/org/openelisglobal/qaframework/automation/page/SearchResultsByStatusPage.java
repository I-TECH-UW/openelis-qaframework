package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

public class SearchResultsByStatusPage extends Page {

	private static final String PAGE_PATH = "/StatusResults.do";

	private static final By SEARCH_FORM = By.id("searchDiv");

	public SearchResultsByStatusPage(Page parent) {
		super(parent);
	}

	@Override
	public String getPageUrl() {
		return PAGE_PATH;
	}

	public Boolean hasSearchForm() {
		return hasElement(SEARCH_FORM);
	}

}
