package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

public class SearchResultsByOrderPage extends Page {

	private static final String PAGE_PATH = "/AccessionResults.do";

	private static final By SEARCH_FORM = By.id("searchDiv");

	private static final String PATH_HOME = "/Dashboard.do";

	private static final By FIELD_LAB_NO_SEARCH = By.id("searchAccessionID");

	private static final By LABEL_ACCESION_NOT_FOUND = By
			.xpath("//*[@id='mainForm']/table/tbody/tr[1]/td/center/h1");

	private static By BUTTON_ACCESION_NUMBER_SERCH = By.id("retrieveTestsID");

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

	public void enterAccesionNumber(String accesionNumber) {
		setText(FIELD_LAB_NO_SEARCH, accesionNumber);
	}

	public Boolean accesionNumberNotFoundDisplays() {
		return hasElement(LABEL_ACCESION_NOT_FOUND);
	}

	public void clickAccesionNumberSearch() {
		clickOn(BUTTON_ACCESION_NUMBER_SERCH);
	}
}
