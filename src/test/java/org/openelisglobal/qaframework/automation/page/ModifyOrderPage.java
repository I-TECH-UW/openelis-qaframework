package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

/**
 * This class represents the Modify Order page
 */
public class ModifyOrderPage extends Page {
	
	private static final String PAGE_PATH = "/SampleEdit.do";
	
	private static final String PATH_HOME = "/Dashboard.do";
	
	private static final By FIELD_LAB_NUMBER = By.id("patientLabNoSearchValue");
	
	private static final By BUTTON_SEARCH = By.id("enhancedSearchButton");
	
	private static final By CHECK_BOX_EXISTING_TEST = By.name("existingTests[2].canceled");
	
	private static final By BUTTON_SAVE = By.id("saveButtonId");
	
	private static final By FIELD_LAB_NUMBER_SEARCH = By.id("patientLabNoSearchValue");
	
	private static final By FIELD_PATIENT_ID_SEARCH = By.id("patientIdNumberSearchValue");
	
	private static final By FIELD_LAST_NAME_SEARCH = By.id("lastNameSearchValue");
	
	private static final By FIELD_FIRST_NAME_SEARCH = By.id("firstNameSearchValue");
	
	private static final By FIELD_DOB_SEARCH = By.id("dateOfBirthSearchValue");

	private static final By SELECT_RESULT = By.id("sel_1");

	private static final By FIELD_PROVIDER_LAST_NAME = By.id("providerLastNameID");
	
	public ModifyOrderPage(Page parent) {
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
	
	public void clickSearchButton() {
		clickOn(BUTTON_SEARCH);
	}
	
	public void removeOneTest() {
		if (hasElementWithoutWait(CHECK_BOX_EXISTING_TEST)) {
			clickOn(CHECK_BOX_EXISTING_TEST);
		}
	}
	
	public void clickSaveButton() {
		clickOn(BUTTON_SAVE);
	}

	public Boolean searchButtonIsDeactivated(){
		return isDisabled(BUTTON_SEARCH);
	}

	public void enterLabNumberSearch(String accesionNumber) {
		setText(FIELD_LAB_NUMBER_SEARCH, accesionNumber);
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
	
	public void enterDateOfBirthSearch(String dateOfBirth) {
		setText(FIELD_DOB_SEARCH, dateOfBirth);
	}
	
	public Boolean containsSeachResult() {
		return hasElement(SELECT_RESULT);
	}
	
	public String getProviderLastNameValue() {
		return getValue(FIELD_PROVIDER_LAST_NAME);
	}
}
