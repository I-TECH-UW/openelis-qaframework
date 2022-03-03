package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

/**
 * This class represents the Results Search By Status Page
 */
public class SearchResultsByStatusPage extends Page {
	
	private static final String PAGE_PATH = "/StatusResults";
	
	private static final By SEARCH_FORM = By.id("searchDiv");
	
	private static final By DROP_DOWN_TESTS = By.id("selectedTest");
	
	private static final By BUTTON_SEARCH = By.xpath("//*[@id='PatientPage']/button");
	
	private static final String PATH_HOME = "/Dashboard";
	
	public SearchResultsByStatusPage(Page parent) {
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
	
	public Boolean hasSearchForm() {
		return hasElement(SEARCH_FORM);
	}
	
	public void selectTest(String test) {
		selectFrom(DROP_DOWN_TESTS, test);
	}
	
	public void clickSearch() {
		clickOn(BUTTON_SEARCH);
	}
}
