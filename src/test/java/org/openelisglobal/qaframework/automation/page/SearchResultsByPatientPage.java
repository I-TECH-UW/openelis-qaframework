package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

public class SearchResultsByPatientPage extends Page {

	private static final String PAGE_PATH = "/PatientResults.do";

	private static final String PATH_HOME = "/Dashboard.do";

	private static final By SEARCH_FORM = By.id("searchDiv");

	private static final By SEARCH_BUTTON = By.id("enhancedSearchButton");

	private static final By FIELD_LAB_NO_SEARCH = By
			.id("patientLabNoSearchValue");

	private static final By FIELD_PATIENT_ID_SEARCH = By
			.id("patientIdNumberSearchValue");

	private static final By FIELD_LAST_NAME_SEARCH = By
			.id("lastNameSearchValue");

	private static final By FIELD_FIRST_NAME_SEARCH = By
			.id("firstNameSearchValue");

	private static final By LABEL_NO_PATIENT_FOUND = By.id("noPatientFound");

	public SearchResultsByPatientPage(Page parent) {
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

	public Boolean searchButtonDeactivated() {
		return isDisabled(SEARCH_BUTTON);
	}

	public void enterLabNoSearch(String labNo) {
		setText(FIELD_LAB_NO_SEARCH, labNo);
	}

	public void enterPatientIdSearch(String patientId) {
		setText(FIELD_PATIENT_ID_SEARCH, patientId);
	}

	public void enterLastNameSearch(String lastName) {
		setText(FIELD_LAST_NAME_SEARCH, lastName);
	}

	public void enterFirstNameSearch(String firstName) {
		setText(FIELD_FIRST_NAME_SEARCH, firstName);
	}

	public void clickSearchButton() {
		clickOn(SEARCH_BUTTON);
	}

	public Boolean noPatientLabelDisplays() {
		return hasElement(LABEL_NO_PATIENT_FOUND);
	}
}
