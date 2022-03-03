package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

/**
 * This class represents the Modify Order page
 */
public class ModifyOrderPage extends Page {
	
	private static final String PAGE_PATH = "/SampleEdit";
	
	private static final String PATH_HOME = "/Dashboard";
	
	private static final By FIELD_LAB_NUMBER = By.id("patientLabNoSearchValue");
	
	private static final By BUTTON_SEARCH = By.id("enhancedSearchButton");
	
	private static final By CHECK_BOX_EXISTING_TEST = By.name("existingTests[2].canceled");
	
	private static final By BUTTON_SAVE = By.id("saveButtonId");
	
	private static final By BUTTON_CANCEL = By.id("cancelButtonId");

	private static final By BUTTON_PRINT_LABEL = By.id("printBarcodeButton");
	
	private static final By FIELD_LAB_NUMBER_SEARCH = By.id("patientLabNoSearchValue");
	
	private static final By FIELD_PATIENT_ID_SEARCH = By.id("patientIdNumberSearchValue");
	
	private static final By FIELD_LAST_NAME_SEARCH = By.id("lastNameSearchValue");
	
	private static final By FIELD_FIRST_NAME_SEARCH = By.id("firstNameSearchValue");
	
	private static final By FIELD_DOB_SEARCH = By.id("dateOfBirthSearchValue");
	
	private static final By FIELD_PROVIDER_FIRST_NAME = By.id("providerFirstNameID");
	
	private static final By BAR_PATIENT_INFO = By.id("patientInfo");
	
	private static final By SELECT_RESULT = By.id("sel_1");
	
	private static final By FIELD_PROVIDER_LAST_NAME = By.id("providerLastNameID");
	
	private static final By FIELD_NEW_ODER_NUMBER = By.id("accessionEdit");
	
	private static final By FIELD_NEXT_VIST_DATE = By.id("nextVisitDate");
	
	private static final By FIELD_REQUEST_DATE = By.id("requestDate");
	
	private static final By FIELD_RECIEVED_DATE = By.id("receivedDateForDisplay");
	
	private static final By FIELD_RECIEVED_TIME = By.id("receivedTime");
	
	private static final By FIELD_SAMPLE_ID = By.xpath("//input[contains(@id,'sequence')]");
	
	private static final By FIELD_TESTS = By.xpath("//textarea[contains(@id,'tests_')]");
	
	private static final By FIELD_SAMPLE_OPTION_ADDED = By.xpath("//li[contains(@class,'asmListItem')]");
	
	private static final By FIELD_COLLECTION_DATE = By.xpath("//input[contains(@name,'collectionDate')]");
	
	private static final By FIELD_COLLECTION_TIME = By.xpath("//input[contains(@name,'collectionTime')]");
	
	private static final By CHECKBOX_REMOVE_SAMPLE = By.xpath("//input[contains(@id,'removeSample')]");
	
	private static final By CHECKBOX_DELETE_TEST = By.xpath("//input[contains(@name,'canceled')]");
	
	private static final By CHECKBOX_ASSIGN_TEST = By.xpath("//input[contains(@id,'add1')]");
	
	private static final By CHECKBOX_SAMPLE_RESULT = By.xpath("//input[contains(@id,'select_')]");
	
	private static final By CHECKBOX_TEST_SELECT = By.className("testCheckbox");
	
	private static final By CHECKBOX_PANNEL_SELECT = By.name("panelSelect");
	
	private static final By DROP_DOWN_SAMPLE_TYPE = By.id("sampleTypeSelect");
	
	private static final By DROP_DOWN_SITE_NAME = By.id("requesterId");
	
	private static final By DROP_DOWN_SAMPLE_CONDITION = By.xpath("//select[contains(@id,'asmSelect')]");
	
	private static final By REMOVE_CONDITION = By.xpath("//a[contains(@class,'asmListItemRemove')]");
	
	private static final By REMOVE_SAMPLE = By.xpath("//input[contains(@id,'removeButton_')]");
	
	private static final By REMOVE_ALL_SAMPLE = By.xpath("//*[@id='samplesAdded']/table[2]/tbody/tr/td[2]/input");
	
	private static final By LABEL_TEST_REQUIRED = By
	        .xpath("//*[@id='orderDisplay']/table/tbody/tr/td/table/tbody/tr[2]/td[1]/span[1]");
	
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
	
	public void clickSampleTypeDropDown() {
		clickOn(DROP_DOWN_SAMPLE_TYPE);
	}
	
	public void clickConditionDropDown() {
		clickOn(DROP_DOWN_SAMPLE_CONDITION);
	}
	
	public void removeOneTest() {
		if (hasElementWithoutWait(CHECK_BOX_EXISTING_TEST)) {
			clickOn(CHECK_BOX_EXISTING_TEST);
		}
	}
	
	public void clickSaveButton() {
		clickOn(BUTTON_SAVE);
	}
	
	public void clickCancelButton() {
		clickOn(BUTTON_CANCEL);
	}
	
	public void clickDeleteTest() {
		clickOn(CHECKBOX_DELETE_TEST);
	}
	
	public Boolean searchButtonIsDeactivated() {
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
	
	public void enterNewLabNumber(String labNumber) {
		setText(FIELD_NEW_ODER_NUMBER, labNumber);
	}
	
	public void enterRequestDate(String date) {
		setText(FIELD_REQUEST_DATE, date);
	}
	
	public void enterRecievedDate(String date) {
		setText(FIELD_RECIEVED_DATE, date);
	}
	
	public void enterRecievedTime(String time) {
		setText(FIELD_RECIEVED_TIME, time);
	}
	
	public void enterCollectionDate(String date) {
		setText(FIELD_COLLECTION_DATE, date);
	}
	
	public void enterCollectionTime(String time) {
		setText(FIELD_COLLECTION_TIME, time);
	}
	
	public void enterTextDirectlyInTestsField(String tests) {
		setText(FIELD_TESTS, tests);
	}
	
	public void enterProviderFirstName(String firstName) {
		setText(FIELD_PROVIDER_FIRST_NAME, firstName);
	}
	
	public Boolean testsBoxIsReadOnly() {
		return findElement(FIELD_TESTS).getAttribute("readonly").equals("true") ? true : false;
	}
	
	public Boolean containsSeachResult() {
		return hasElement(SELECT_RESULT);
	}
	
	public String getProviderLastNameValue() {
		return getValue(FIELD_PROVIDER_LAST_NAME);
	}
	
	public Boolean hasPatientInfoBar() {
		return hasElement(BAR_PATIENT_INFO);
	}
	
	public String getPatientInfo() {
		return getText(BAR_PATIENT_INFO);
	}
	
	public String getRecievedTime() {
		return getValue(FIELD_RECIEVED_TIME);
	}
	
	public String getCollectionTime() {
		return getValue(FIELD_COLLECTION_TIME);
	}
	
	public String getAddedTests() {
		return getValue(FIELD_TESTS);
	}
	
	public String getSampleId() {
		return getValue(FIELD_SAMPLE_ID);
	}
	
	public void clickNextVistDate() {
		clickOn(FIELD_NEXT_VIST_DATE);
	}
	
	public void clickCheckBoxAssignTest() {
		clickOn(CHECKBOX_ASSIGN_TEST);
	}
	
	public void clickRemoveCondition() {
		clickOn(REMOVE_CONDITION);
	}
	
	public void clickRemoveSample() {
		clickOn(REMOVE_SAMPLE);
	}
	
	public void clickRemoveAllSample() {
		clickOn(REMOVE_ALL_SAMPLE);
	}
	
	public void clickTestCheckBox() {
		clickOn(CHECKBOX_TEST_SELECT);
	}
	
	public void clickPanelCheckBox() {
		clickOn(CHECKBOX_PANNEL_SELECT);
	}
	
	public String getNewLabNumberClass() {
		return getClass(FIELD_NEW_ODER_NUMBER);
	}
	
	public String getRequestDateClass() {
		return getClass(FIELD_REQUEST_DATE);
	}
	
	public String getRecievedDateClass() {
		return getClass(FIELD_RECIEVED_DATE);
	}
	
	public String getRecievedTimeClass() {
		return getClass(FIELD_RECIEVED_TIME);
	}
	
	public String getCollectionDateClass() {
		return getClass(FIELD_COLLECTION_DATE);
	}
	
	public String getCollectionTimeClass() {
		return getClass(FIELD_COLLECTION_TIME);
	}
	
	public String getTestRequiredLabelClass() {
		return getClass(LABEL_TEST_REQUIRED);
	}
	
	public void selectSiteNameFromDropDown() {
		selectOptionFromDropDown(DROP_DOWN_SITE_NAME);
	}
	
	public Boolean siteNameDropDownHasOptions() {
		return dropDownHasOptions(DROP_DOWN_SITE_NAME);
	}
	
	public void checkRemoveSample() {
		clickOn(CHECKBOX_REMOVE_SAMPLE);
	}
	
	public Boolean removeSampleCheckBoxIsChecked() {
		return isChecked(CHECKBOX_REMOVE_SAMPLE);
	}
	
	public Boolean deleteTestCheckBoxIsDisabled() {
		return isDisabled(CHECKBOX_DELETE_TEST);
	}
	
	public Boolean saveButtonIsDeactivated() {
		return isDisabled(BUTTON_SAVE);
	}
	
	public Boolean deleteTestCheckBoxIsChecked() {
		return isChecked(CHECKBOX_DELETE_TEST);
	}
	
	public Boolean enterTestCheckBoxIsChecked() {
		return isChecked(CHECKBOX_TEST_SELECT);
	}
	
	public Boolean panelCheckBoxIsChecked() {
		return isChecked(CHECKBOX_PANNEL_SELECT);
	}
	
	public Boolean assignTestCheckBoxIsChecked() {
		return isChecked(CHECKBOX_ASSIGN_TEST);
	}
	
	public Boolean sampleTypesExistInDropDown() {
		return dropDownHasOptions(DROP_DOWN_SAMPLE_TYPE);
	}
	
	public void selectSampleType() {
		selectOptionFromDropDown(DROP_DOWN_SAMPLE_TYPE);
	}
	
	public Boolean ConditionsExistInDropDown() {
		return dropDownHasOptions(DROP_DOWN_SAMPLE_TYPE);
	}
	
	public void selectSampleCondition() {
		selectOptionFromDropDown(DROP_DOWN_SAMPLE_CONDITION);
	}
	
	public Boolean hasSampleTypeResults() {
		return hasElement(CHECKBOX_SAMPLE_RESULT);
	}
	
	public Boolean hasTestCheckBoxes() {
		return hasElement(CHECKBOX_TEST_SELECT);
	}

	public Boolean printButtonAppears() {
		return hasElement(BUTTON_PRINT_LABEL);
	}
	
	public Boolean sampleTypeResultsRemoved() {
		return !hasElementWithoutWait(CHECKBOX_SAMPLE_RESULT);
	}
	
	public Boolean sampleConditionOptionAdded() {
		return hasElement(FIELD_SAMPLE_OPTION_ADDED);
	}
	
	public Boolean sampleConditionOptionRemoved() {
		return !hasElementWithoutWait(FIELD_SAMPLE_OPTION_ADDED);
	}
}
