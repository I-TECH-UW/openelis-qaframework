package org.openelisglobal.qaframework.automation.page;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This class represents the Add Order page
 */
public class AddOrderPage extends Page {

	private static final String PATH_ADD_ORDER = "/SamplePatientEntry.do";

	private static final String LABEL_TEXT_REQUEST = "Test Reques";

	private static final By FIELD_LAB_NUMBER = By.id("labNo");

	private static final By FIELD_REQUEST_DATE = By.id("requestDate");

	private static final By FIELD_RECIEVED_DATE = By
			.id("receivedDateForDisplay");

	private static final By FIELD_NEXT_VISIT_DATE = By.id("nextVisitDate");

	private static final By FIELD_RECIEVED_TIME = By.id("receivedTime");

	private static final By FIELD_SITE_NAME = By.id("requesterId");

	private static final By FIELD_PROGRAM = By.id("sampleOrderItems.program");

	private static final By FIELD_OPTION = By.tagName("option");

	private static final By FIELD_LAST_NAME = By.id("providerLastNameID");

	private static final By FIELD_FIRST_NAME = By.id("providerFirstNameID");

	private static final By FIELD_PHONE_NUMBER = By.id("providerWorkPhoneID");

	private static final By FIELD_FAX = By.id("providerFaxID");

	private static final By FIELD_EMAIL = By.id("providerEmailID");

	private static final By FIELD_COLLECTION_DATE = By.id("collectionDate_1");

	private static final By FIELD_COLLECTION_TIME = By.id("collectionTime_1");

	private static final By FIELD_COLLECTOR = By.id("collector_1");

	private static final By FIELD_TEST = By.id("tests_1");

	private static final By FIELD_LAB_NUMBER_SEARCH = By
			.id("patientLabNoSearchValue");

	private static final By FIELD_PATIENT_ID_SEARCH = By
			.id("patientIdNumberSearchValue");

	private static final By FIELD_LAST_NAME_SEARCH = By
			.id("lastNameSearchValue");

	private static final By FIELD_FIRST_NAME_SEARCH = By
			.id("firstNameSearchValue");

	private static final By FIELD_DOB_SEARCH = By.id("dateOfBirthSearchValue");

	private static final By FIELD_SUBJECT_NUMBER = By.id("subjectNumberID");

	private static final By FIELD_NATIONAL_ID = By.id("nationalID");

	private static final By FIELD_PATIENT_LAST_NAME = By.id("lastNameID");

	private static final By FIELD_PATIENT_FIRST_NAME = By.id("firstNameID");

	private static final By FIELD_CONTACT_LAST_NAME = By
			.id("contactLastNameID");

	private static final By FIELD_CONTACT_FIRST_NAME = By
			.id("contactFirstNameID");

	private static final By FIELD_CONTACT_PHONE = By.id("contactPhoneID");

	private static final By FIELD_CONTACT_EMAIL = By.id("contactEmailID");

	private static final By FIELD_PATIENT_STREET = By.id("streetID");

	private static final By FIELD_PATIENT_COMMUNE = By.id("communeID");

	private static final By FIELD_PATIENT_TOWN = By.id("cityID");

	private static final By FIELD_PATIENT_PHONE = By.id("patientPhone");

	private static final By FIELD_PATIENT_DOB = By.id("dateOfBirthID");

	private static final By FIELD_PATIENT_EMAIL = By.id("patientEmail");

	private static final By FIELD_PATIENT_NATIONALITY_OTHER = By
			.id("nationalityOtherId");

	private static final By BUTTON_GENERATE = By.id("generateAccessionButton");

	private static final By BUTTON_ADD_SAMPLE = By.id("samplesSectionId");

	private static final By BUTTON_REMOVE_CONDITION = By
			.className("asmListItemRemove");

	private static final By BUTTON_REMOVE_ALL = By
			.xpath("//input[@value='Remove All']");

	private static final By BUTTON_REMOVE_SAMPLE = By.id("removeButton_1");

	private static final By BUTTON_SAVE_VALIDATION = By
			.xpath("//*[@id='mainForm']/table/tbody/tr[5]/td/center/table/tbody/tr/td[1]/button");

	private static final By BUTTON_ADD_PATIENT_SECTION = By
			.xpath("//td/input[@id='orderSectionId']");

	private static final By BUTTON_PATIENT_SEARCH = By
			.id("enhancedSearchButton");

	private static final By BUTTON_NEW_PATIENT = By
			.xpath("//input[@value='New Patient']");

	private static final By SELECT_SAMPLE = By.id("sampleTypeSelect");

	private static final By SELECT_CONDITION = By.id("asmSelect0");

	private static final By SELECT_PATIENT_DISTRICT = By.id("healthRegionID");

	private static final By SELECT_PATIENT_GENDER = By.id("genderID");

	private static final By SELECT_PATIENT_EDUCATION = By.id("educationID");

	private static final By SELECT_PATIENT_MARITAL_STATUS = By
			.id("maritialStatusID");

	private static final By REQUIRED_REQUEST_DATE = By
			.xpath("//div[@id='orderDisplay']/table/tbody/tr/td/table/tbody/tr[2]/td/span");

	private static final By REQUIRED_RECIEVED_DATE = By
			.xpath("//div[@id='orderDisplay']/table/tbody/tr/td/table/tbody/tr[3]/td/span");

	private static final By REQUIRED_SITE_NAME = By
			.xpath("//div[@id='orderDisplay']/table/tbody/tr/td/table/tbody/tr[6]/td/span");

	private static final By REQUIRED_LAST_NAME = By
			.xpath("//div[@id='orderDisplay']/table/tbody/tr/td/table/tbody/tr[11]/td/span");

	private static final By REQUIRED_SAMPLE_ADDITION = By
			.xpath("//input[@id='samplesSectionId']/following-sibling::span[1]");

	private static final By REQUIRED_TEST = By
			.xpath("//*[@id='samplesAddedTable']/tbody/tr[1]/th[9]/span");

	private static final By REQUIRED_PATIENT_INFORMATION = By
			.xpath("//td/input[@id='orderSectionId']/following-sibling::span[1]");

	private static final By CHECK_BOX_TEST = By.id("test_0");

	private static final By CHECK_BOX_PANNEL = By.id("panel_0");

	private static final By RADIO_BUTTON_VALIDATE_TRUE = By.id("value1");

	private static final By LABEL_VALIDATE_PATIENT_DOB = By
			.id("patientProperties.birthDateForDisplayMessage");

	public AddOrderPage(Page parentPage) {
		super(parentPage);
	}

	@Override
	public String getPageUrl() {
		return PATH_ADD_ORDER;
	}

	public Boolean containsTextRequest() {
		return containsText(LABEL_TEXT_REQUEST);
	}

	public void waitForLabNumber() {
		waitForElementWithSpecifiedMaxTimeout(FIELD_LAB_NUMBER, 120);
	}

	public Boolean isAccessionNumberMandatory() {
		if (findElement(FIELD_LAB_NUMBER).getAttribute("class") == "requiredlabel") {
			return true;
		}
		return false;
	}

	public WebElement getGenerateButton() {
		return findElement(BUTTON_GENERATE);
	}

	public WebElement getAddSampleButton() {
		return findElement(BUTTON_ADD_SAMPLE);
	}

	public WebElement getRemoveAllButton() {
		return findElement(BUTTON_REMOVE_ALL);
	}

	public WebElement getAccessionNumberField() {
		return findElement(FIELD_LAB_NUMBER);
	}

	public WebElement getRequestDateField() {
		return findElement(FIELD_REQUEST_DATE);
	}

	public WebElement getRecievedDateField() {
		return findElement(FIELD_RECIEVED_DATE);
	}

	public WebElement getNextVisitDateField() {
		return findElement(FIELD_NEXT_VISIT_DATE);
	}

	public WebElement getRecievedTimeField() {
		return findElement(FIELD_RECIEVED_TIME);
	}

	public WebElement getSiteNameField() {
		return findElement(FIELD_SITE_NAME);
	}

	public WebElement getProgramField() {
		return findElement(FIELD_PROGRAM);
	}

	public WebElement getLastNameField() {
		return findElement(FIELD_LAST_NAME);
	}

	public WebElement getFirstNameField() {
		return findElement(FIELD_FIRST_NAME);
	}

	public WebElement getTelephoneField() {
		return findElement(FIELD_PHONE_NUMBER);
	}

	public WebElement getFaxField() {
		return findElement(FIELD_FAX);
	}

	public WebElement getEmailField() {
		return findElement(FIELD_EMAIL);
	}

	public WebElement getSampleSelectionField() {
		return findElement(SELECT_SAMPLE);
	}

	public WebElement getConditonSelectionField() {
		return findElement(SELECT_CONDITION);
	}

	public WebElement getCollectionDateField() {
		return findElement(FIELD_COLLECTION_DATE);
	}

	public Boolean accessionNumberEntered(String accesionNumber) {
		if (getAccessionNumberField().getAttribute("value").equals(
				accesionNumber)) {
			return true;
		}
		return false;
	}

	public void clickGenerateButton() throws InterruptedException {
		getAccessionNumberField().clear();
		clickOn(BUTTON_GENERATE);
		Thread.sleep(1000);
	}

	public void clickOnNextVisitDate() {
		clickOn(FIELD_NEXT_VISIT_DATE);
	}

	public void clickOnCollectionTime() {
		clickOn(FIELD_COLLECTION_TIME);
	}

	public void clickAddSampleButton() {
		if (getAddSampleButton().getAttribute("value") == "+") {
			clickOn(BUTTON_ADD_SAMPLE);
		}
	}
	public void clickAddPatientInformation() {
		if (getValue(BUTTON_ADD_PATIENT_SECTION) == "+") {
			clickOn(BUTTON_ADD_PATIENT_SECTION);
		}
	}

	public void clickRemoveAllButton() {
		clickOn(BUTTON_REMOVE_ALL);
	}

	public void clickCollectorField() {
		clickOn(FIELD_COLLECTOR);
	}

	public void clickTestCheckBox() {
		clickOn(CHECK_BOX_TEST);
	}

	public void clickPannelCheckBox() {
		clickOn(CHECK_BOX_PANNEL);
	}

	public void clickNewPatientButton() {
		clickOn(BUTTON_NEW_PATIENT);
	}

	public void clickOtherNationality() {
		clickOn(FIELD_PATIENT_NATIONALITY_OTHER);
	}
	public void selectSiteNameFromDropDown() throws InterruptedException {
		clickOn(FIELD_SITE_NAME);
		List<WebElement> options = getSiteNameField()
				.findElements(FIELD_OPTION);
		int n = 0;
		for (WebElement option : options) {
			option.click();
			Thread.sleep(100);
			if (n == 5) {
				break;
			}
			n = n + 1;
		}
	}

	public void selectProgramFromDropDown() throws InterruptedException {
		clickOn(FIELD_PROGRAM);
		List<WebElement> options = getProgramField().findElements(FIELD_OPTION);
		for (WebElement option : options) {
			option.click();
			Thread.sleep(100);
		}
	}

	public Boolean GeneratedAccessionNumberIsDigit() {
		if (StringUtils.isNumeric(getAccessionNumberField().getAttribute(
				"value"))) {
			return true;
		}
		return false;
	}

	public String getRequestDateValue() {
		return getRequestDateField().getAttribute("value");
	}

	public String getRecievedDateValue() {
		return getRecievedDateField().getAttribute("value");
	}

	public String getRecievedTimeValue() {
		return getRecievedTimeField().getAttribute("value");
	}

	public String getFistNameValue() {
		return getFirstNameField().getAttribute("value");
	}

	public String getLastNameValue() {
		return getLastNameField().getAttribute("value");
	}

	public String getCollectionTimeValue() {
		return getValue(FIELD_COLLECTION_TIME);
	}

	public String getCollectorValue() {
		return getValue(FIELD_COLLECTOR);
	}

	public String getTestValue() {
		return getValue(FIELD_TEST);
	}

	public void enterRecievedDate(String date) {
		setText(FIELD_RECIEVED_DATE, date);
	}

	public void enterRequestDate(String date) {
		setText(FIELD_REQUEST_DATE, date);
	}

	public void enterCollectionDate(String date) {
		setText(FIELD_COLLECTION_DATE, date);
	}

	public void enterRecievedTime(String time) {
		setText(FIELD_RECIEVED_TIME, time);
	}

	public void enterCollectionTime(String time) {
		setText(FIELD_COLLECTION_TIME, time);
	}

	public void enterAccessionNumber(String accesionNumber) {
		setText(FIELD_LAB_NUMBER, accesionNumber);
	}

	public void enterLastName(String lastName) {
		setText(FIELD_LAST_NAME, lastName);
	}

	public void enterFirstName(String firstName) {
		setText(FIELD_FIRST_NAME, firstName);
	}

	public void enterTelephone(String telephone) {
		setText(FIELD_PHONE_NUMBER, telephone);
	}

	public void enterFax(String fax) {
		setText(FIELD_FAX, fax);
	}

	public void enterEmail(String email) {
		setText(FIELD_EMAIL, email);
	}

	public void enterCollector(String name) {
		setText(FIELD_COLLECTOR, name);
	}

	public void enterTest(String test) {
		setText(FIELD_TEST, test);
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

	public void enterSubjectNumber(String subJectNumber) {
		setText(FIELD_SUBJECT_NUMBER, subJectNumber);
	}

	public void enterNationalId(String nationalID) {
		setText(FIELD_NATIONAL_ID, nationalID);
	}

	public void enterPatientLastName(String lastName) {
		setText(FIELD_PATIENT_LAST_NAME, lastName);
	}

	public void enterPatientFirstName(String firstName) {
		setText(FIELD_PATIENT_FIRST_NAME, firstName);
	}

	public void enterContactLastName(String lastName) {
		setText(FIELD_CONTACT_LAST_NAME, lastName);
	}

	public void enterContactFirstName(String firstName) {
		setText(FIELD_CONTACT_FIRST_NAME, firstName);
	}

	public void enterContactEmail(String email) {
		setText(FIELD_CONTACT_EMAIL, email);
	}

	public void enterContactPhone(String phone) {
		setText(FIELD_CONTACT_PHONE, phone);
	}

	public void enterPatientStreet(String street) {
		setText(FIELD_PATIENT_STREET, street);
	}

	public void enterPatientCommune(String commune) {
		setText(FIELD_PATIENT_COMMUNE, commune);
	}

	public void enterPatientTown(String town) {
		setText(FIELD_PATIENT_TOWN, town);
	}

	public void enterPatientPhone(String phone) {
		setText(FIELD_PATIENT_PHONE, phone);
	}

	public void enterPatientEmail(String email) {
		setText(FIELD_PATIENT_EMAIL, email);
	}

	public void enterPatientDateofBirth(String dob) {
		setText(FIELD_PATIENT_DOB, dob);
	}

	public void enterPatientOtherNationality(String nationality) {
		setText(FIELD_PATIENT_NATIONALITY_OTHER, nationality);
	}

	public void clearTestsField() {
		clearText(FIELD_TEST);
	}

	public String getRequestDateRequiredClass() {
		return findElement(REQUIRED_REQUEST_DATE).getAttribute("class");
	}

	public String getRecievedDateRequiredClass() {
		return findElement(REQUIRED_RECIEVED_DATE).getAttribute("class");
	}

	public String getRecievedTimeClass() {
		return getRecievedTimeField().getAttribute("class");
	}

	public String getRecievedDateClass() {
		return getRecievedDateField().getAttribute("class");
	}

	public String getRequestDateClass() {
		return getRequestDateField().getAttribute("class");
	}

	public String getReceievedTimeClass() {
		return getRecievedTimeField().getAttribute("class");
	}

	public String getSiteNameRequiredClass() {
		return findElement(REQUIRED_SITE_NAME).getAttribute("class");
	}

	public String getLastNameRequiredClass() {
		return findElement(REQUIRED_LAST_NAME).getAttribute("class");
	}

	public String getTestReqcuiredClass() {
		return getClass(REQUIRED_TEST);
	}

	public String getAccesionNumberClass() {
		return getAccessionNumberField().getAttribute("class");
	}

	public String getCollectionDateClass() {
		return getCollectionDateField().getAttribute("class");
	}

	public String getCollectionTimeClass() {
		return getClass(FIELD_COLLECTION_TIME);
	}

	public String getTelephoneNumberClass() {
		return getTelephoneField().getAttribute("class");
	}

	public String getSampleRecquiredClass() {
		return findElement(REQUIRED_SAMPLE_ADDITION).getAttribute("class");
	}

	public String getPatientInformationRecquiredClass() {
		return getClass(REQUIRED_PATIENT_INFORMATION);
	}

	public String getContactEmailFieldClass() {
		return getClass(FIELD_CONTACT_EMAIL);
	}

	public String getPatientPhoneFieldClass() {
		return getClass(FIELD_PATIENT_PHONE);
	}

	public String getPatientEmailFieldClass() {
		return getClass(FIELD_PATIENT_EMAIL);
	}

	public String getPatientDoBValidateLabelClass() {
		return getClass(LABEL_VALIDATE_PATIENT_DOB);
	}

	public String getPatientSerchDisabledAttribute() {
		return findElement(BUTTON_PATIENT_SEARCH).getAttribute("disabled");
	}
	public boolean sampleTypesDisplayInDropDownMenu() {
		String[] samples = {"Serum", "Plasma", "Urine", "DBS", "Fluid",
				"Blood", "Swab", "Respiratory Swab", "Sputum"};
		clickOn(SELECT_SAMPLE);
		int n = 0;
		Boolean b = false;
		List<WebElement> options = getSampleSelectionField().findElements(
				FIELD_OPTION);
		for (WebElement option : options) {
			if (n >= 1) {
				if (Arrays.asList(samples).contains(option.getText())) {
					b = true;
				} else {
					b = false;
				}
			}
			if (n == 7) {
				break;
			}
			n = n + 1;
		}
		return b;
	}

	public void selectSampleTypeFromDropDownMenu() {
		selectOptionFromDropDown(SELECT_SAMPLE);
	}

	public void selectSampleConditionFromDropDownMenu() {
		clickOn(SELECT_CONDITION);
		List<WebElement> options = getConditonSelectionField().findElements(
				FIELD_OPTION);
		int n = 0;
		for (WebElement option : options) {
			if (n >= 1) {
				option.click();
			}
			if (n == 5) {
				break;
			}
			n = n + 1;
		}
	}

	public void selectPatientHelathRegionFromDropDownMenu() {
		selectOptionFromDropDown(SELECT_PATIENT_DISTRICT);
	}

	public void selectPatientGenderFromDropDownMenu() {
		selectOptionFromDropDown(SELECT_PATIENT_GENDER);
	}

	public void selectPatientEducationFromDropDownMenu() {
		selectOptionFromDropDown(SELECT_PATIENT_EDUCATION);
	}

	public void selectPatientMaritalStatusFromDropDownMenu() {
		selectOptionFromDropDown(SELECT_PATIENT_MARITAL_STATUS);
	}

	public void removeAddedSampleConditionFromDropDownMenu()
			throws InterruptedException {
		List<WebElement> options = getConditonSelectionField().findElements(
				FIELD_OPTION);
		int n = 0;
		for (WebElement option : options) {
			if (n >= 1) {
				clickOn(BUTTON_REMOVE_CONDITION);
			}
			if (n == 5) {
				break;
			}
			n = n + 1;
		}
	}

	public void removeAddedSampleType() {
		clickOn(BUTTON_REMOVE_SAMPLE);
	}

	public void reAddSamples() {
		clickOn(SELECT_SAMPLE);
		List<WebElement> options = getSampleSelectionField().findElements(
				FIELD_OPTION);
		int n = 0;
		for (WebElement option : options) {
			option.click();
			if (n == 4) {
				break;
			}
			n = n + 1;
		}
	}

	public void turnOnAcessionValidation() {
		goToPage("SampleEntryConfig.do?ID=116&startingRecNo=1");
		clickOn(RADIO_BUTTON_VALIDATE_TRUE);
		clickOn(BUTTON_SAVE_VALIDATION);
		this.go();
	}

	public void turnOnTelephoneValidation() {
		goToPage("SiteInformation.do?ID=71&startingRecNo=1");
		clickOn(RADIO_BUTTON_VALIDATE_TRUE);
		clickOn(BUTTON_SAVE_VALIDATION);
		this.go();
	}

	public void serchPatient() {
		clickOn(BUTTON_PATIENT_SEARCH);
	}
}
