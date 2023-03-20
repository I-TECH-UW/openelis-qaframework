package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

/**
 * This class represents the Results Search By Patient Page
 */
public class SearchResultsByPatientPage extends Page {
	
	private static final String PAGE_PATH = "/PatientResults";
	
	private static final String PATH_HOME = "/Dashboard";
	
	private static final By SEARCH_FORM = By.id("searchDiv");
	
	private static final By BUTTON_SEARCH_PATIENT_RESULTS = By.id("enhancedSearchButton");
	
	private static final By FIELD_LAB_NO_SEARCH = By.id("patientLabNoSearchValue");
	
	private static final By FIELD_PATIENT_ID_SEARCH = By.id("patientIdNumberSearchValue");
	
	private static final By FIELD_LAST_NAME_SEARCH = By.id("lastNameSearchValue");
	
	private static final By FIELD_FIRST_NAME_SEARCH = By.id("firstNameSearchValue");
	
	private static final By LABEL_NO_PATIENT_FOUND = By.id("noPatientFound");
	
	private static final By SEARCH_RESULT_TABLE = By.id("searchResultTable");
	
	private static final By SELECT_RESULT_2 = By.id("sel_2");
	
	private static final By BUTTON_GET_PATIENT_TESTS = By.id("selectPatientButtonID");
	
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
		return isDisabled(BUTTON_SEARCH_PATIENT_RESULTS);
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
		clickOn(BUTTON_SEARCH_PATIENT_RESULTS);
	}
	
	public Boolean noPatientLabelDisplays() {
		return hasElement(LABEL_NO_PATIENT_FOUND);
	}
	
	public Boolean searchResultsDisplay() {
		return hasElement(SEARCH_RESULT_TABLE);
	}
	
	public void selectPatientSearchResultSecondRadioBtn() {
		clickOn(SELECT_RESULT_2);
	}
	
	public void clickGetPatientTestsButon() {
		clickOn(BUTTON_GET_PATIENT_TESTS);
	}

}
