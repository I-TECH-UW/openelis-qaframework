package org.openelisglobal.qaframework.automation.page;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This class represents the Add Order page
 */
public class AddOrderPage extends Page {

	private static final String PATH_ADD_ORDER = "/SamplePatientEntry.do";

	private static final String PATH_HOME = "/Dashboard.do";

	private static final String PATH_SAMPLE_ENTRY_CONFIG = "SampleEntryConfig.do?ID=116&startingRecNo=1";

	private static final String PATH_SITE_INFO_CONFIG = "SiteInformation.do?ID=71&startingRecNo=1";

	private static final String LABEL_TEXT_REQUEST = "Test Reques";

	private static final By FIELD_LAB_NUMBER = By.id("labNo");

	private static final By FIELD_REQUEST_DATE = By.id("requestDate");

	private static final By FIELD_RECIEVED_DATE = By.id("receivedDateForDisplay");

	private static final By FIELD_NEXT_VISIT_DATE = By.id("nextVisitDate");

	private static final By FIELD_RECIEVED_TIME = By.id("receivedTime");

	private static final By FIELD_SITE_NAME = By.id("requesterId");

	private static final By FIELD_PROGRAM = By.id("sampleOrderItems.program");

	private static final By FIELD_OPTION = By.tagName("option");

	private static final By FIELD_REQUESTER_LAST_NAME = By.id("providerLastNameID");

	private static final By FIELD_REQUESTER_FIRST_NAME = By.id("providerFirstNameID");

	private static final By FIELD_REQUESTER_PHONE_NUMBER = By.id("providerWorkPhoneID");

	private static final By FIELD_REQUESTER_FAX = By.id("providerFaxID");

	private static final By FIELD_REQUESTER_EMAIL = By.id("providerEmailID");

	private static final By FIELD_COLLECTION_DATE = By.id("collectionDate_1");

	private static final By FIELD_COLLECTION_TIME = By.id("collectionTime_1");

	private static final By FIELD_COLLECTOR = By.id("collector_1");

	private static final By FIELD_TEST = By.id("tests_1");

	private static final By FIELD_LAB_NUMBER_SEARCH = By.id("patientLabNoSearchValue");

	private static final By FIELD_PATIENT_ID_SEARCH = By.id("patientIdNumberSearchValue");

	private static final By FIELD_LAST_NAME_SEARCH = By.id("lastNameSearchValue");

	private static final By FIELD_FIRST_NAME_SEARCH = By.id("firstNameSearchValue");

	private static final By FIELD_DOB_SEARCH = By.id("dateOfBirthSearchValue");

	private static final By FIELD_SUBJECT_NUMBER = By.id("subjectNumberID");

	private static final By FIELD_NATIONAL_ID = By.id("nationalID");

	private static final By FIELD_PATIENT_LAST_NAME = By.id("lastNameID");

	private static final By FIELD_PATIENT_FIRST_NAME = By.id("firstNameID");

	private static final By FIELD_CONTACT_LAST_NAME = By.id("contactLastNameID");

	private static final By FIELD_CONTACT_FIRST_NAME = By.id("contactFirstNameID");

	private static final By FIELD_CONTACT_PHONE = By.id("contactPhoneID");

	private static final By FIELD_CONTACT_EMAIL = By.id("contactEmailID");

	private static final By FIELD_CONTACT_TRACING_INDEX_NAME = By.id("contactTracingIndexName");

	private static final By FIELD_CONTACT_TRACING_INDEX_RECORD_NUMBER = By.id("contactTracingIndexRecordNumber");

	private static final By FIELD_PATIENT_STREET = By.id("streetID");

	private static final By FIELD_PATIENT_COMMUNE = By.id("communeID");

	private static final By FIELD_PATIENT_TOWN = By.id("cityID");

	private static final By FIELD_PATIENT_PHONE = By.id("patientPhone");

	private static final By FIELD_PATIENT_DOB = By.id("dateOfBirthID");

	private static final By FIELD_PATIENT_EMAIL = By.id("patientEmail");

	private static final By FIELD_PATIENT_NATIONALITY_OTHER = By.id("nationalityOtherId");

	private static final By FIELD_PATIENT_AGE_YEARS = By.id("ageYears");

	private static final By BUTTON_GENERATE = By.id("generateAccessionButton");

	private static final By BUTTON_ADD_SAMPLE = By.id("samplesSectionId");

	private static final By BUTTON_REMOVE_CONDITION = By.className("asmListItemRemove");

	private static final By BUTTON_REMOVE_ALL = By.xpath("//input[@value='Remove All']");

	private static final By BUTTON_REMOVE_SAMPLE = By.id("removeButton_1");

	private static final By BUTTON_SAVE_VALIDATION = By
			.xpath("//*[@id='mainForm']/table/tbody/tr[5]/td/center/table/tbody/tr/td[1]/button");

	private static final By BUTTON_ADD_PATIENT_SECTION = By.xpath("//td/input[@id='orderSectionId']");

	private static final By BUTTON_PATIENT_SEARCH = By.id("enhancedSearchButton");

	private static final By BUTTON_NEW_PATIENT = By.xpath("//input[@value='New Patient']");

	private static final By BUTTON_SAVE = By.id("saveButtonId");

	private static final By BUTTON_CANCEL = By.xpath("//input[@value='Cancel']");

	private static final By BUTTON_PRINT_LABEL= By.id("printBarcodeButton");

	private static final By SELECT_SAMPLE = By.id("sampleTypeSelect");

	private static final By SELECT_CONDITION = By.id("asmSelect0");

	private static final By SELECT_PATIENT_DISTRICT = By.id("healthRegionID");

	private static final By SELECT_PATIENT_GENDER = By.id("genderID");

	private static final By SELECT_PATIENT_EDUCATION = By.id("educationID");

	private static final By SELECT_PATIENT_MARITAL_STATUS = By.id("maritialStatusID");

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

	private static final By REQUIRED_TEST = By.xpath("//*[@id='samplesAddedTable']/tbody/tr[1]/th[9]/span");

	private static final By REQUIRED_PATIENT_INFORMATION = By
			.xpath("//td/input[@id='orderSectionId']/following-sibling::span[1]");

	private static final By CHECK_BOX_TEST = By.id("test_0");

	private static final By CHECK_BOX_TEST1 = By.id("test_1");

	private static final By CHECK_BOX_TEST2 = By.id("test_2");

	private static final By CHECK_BOX_TEST3 = By.id("test_3");

	private static final By CHECK_BOX_PATIENT_EMAIL = By.className("patientEmailInput");

	private static final By CHECK_BOX_PATIENT_SMS = By.className("patientSMSInput");

	private static final By CHECK_BOX_PROVIDER_EMAIL = By.className("providerEmailInput");

	private static final By CHECK_BOX_PROVIDER_SMS = By.className("providerSMSInput");

	private static final By CHECK_BOX_VIRAL_LOAD_TEST = By.id("test_4");

	private static final By CHECK_BOX_PANNEL = By.name("panelSelect");

	private static final By CHECK_BOX_REFFER_TEST = By.id("useReferral");

	private static final By SELECT_REFFER_REASON = By.id("referralReasonId_0");

	private static final By SELECT_REFFER_INSTITUTE = By.id("referredInstituteId_0");

	private static final By SELECT_REFFER_TEST_NAME = By.id("testSelection_0");

	private static final By FIELD_REFFER_SENT_DATE = By.id("sendDate_0");

	private static final By FIELD_REFFER_REFFERER = By.id("referrer_0");

	private static final By RADIO_BUTTON_VALIDATE_TRUE = By.id("value1");

	private static final By LABEL_VALIDATE_PATIENT_DOB = By.id("patientProperties.birthDateForDisplayMessage");

	private static final By LABEL_VALIDATE_PATIENT_AGE = By.id("ageYearsMessage");

	private static final By GIF_LOADER = By.id("loading");

	private static final By SELECT_RESULT = By.id("sel_1");


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
		return findElement(FIELD_REQUESTER_LAST_NAME);
	}

	public WebElement getFirstNameField() {
		return findElement(FIELD_REQUESTER_FIRST_NAME);
	}

	public WebElement getTelephoneField() {
		return findElement(FIELD_REQUESTER_PHONE_NUMBER);
	}

	public WebElement getFaxField() {
		return findElement(FIELD_REQUESTER_FAX);
	}

	public WebElement getEmailField() {
		return findElement(FIELD_REQUESTER_EMAIL);
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
		if (getAccessionNumberField().getAttribute("value").equals(accesionNumber)) {
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
		// clickOn(CHECK_BOX_TEST1);
		// clickOn(CHECK_BOX_TEST2);
		// clickOn(CHECK_BOX_TEST3);
		// clickOn(CHECK_BOX_VIRAL_LOAD_TEST);
	}

	public void clickPannelCheckBox() {
		if (panelCheckBoxExists()) {
			clickOn(CHECK_BOX_PANNEL);
		}
	}

	public void clickNewPatientButton() {
		clickOn(BUTTON_NEW_PATIENT);
	}

	public void clickOtherNationality() {
		clickOn(FIELD_PATIENT_NATIONALITY_OTHER);
	}

	public void clickCancel() {
		clickOn(BUTTON_CANCEL);
	}

	public void clickSave() {
		clickOn(BUTTON_SAVE);
	}

	public void clickPrintlabel() {
		clickOn(BUTTON_PRINT_LABEL);
	}

	public void clickReferrerTest() {
		clickOn(CHECK_BOX_REFFER_TEST);
	}

	public void selectSiteNameFromDropDown() throws InterruptedException {
		clickOn(FIELD_SITE_NAME);
		List<WebElement> options = getSiteNameField().findElements(FIELD_OPTION);
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
		if (StringUtils.isNumeric(getAccessionNumberField().getAttribute("value"))) {
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

	public String getAccesionNumberValue() {
		return getValue(FIELD_LAB_NUMBER);
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

	public String getPatientDateOfBirthValue() {
		return getValue(FIELD_PATIENT_DOB);
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

	public void enterRequesterLastName(String lastName) {
		setText(FIELD_REQUESTER_LAST_NAME, lastName);
	}

	public void enterRequesterFirstName(String firstName) {
		setText(FIELD_REQUESTER_FIRST_NAME, firstName);
	}

	public void enterRequesterTelephone(String telephone) {
		setText(FIELD_REQUESTER_PHONE_NUMBER, telephone);
	}

	public void enterRequesterFax(String fax) {
		setText(FIELD_REQUESTER_FAX, fax);
	}

	public void enterRequesterEmail(String email) {
		setText(FIELD_REQUESTER_EMAIL, email);
	}

	public void enterCollector(String name) {
		setText(FIELD_COLLECTOR, name);
	}

	public void enterTest(String test) {
		setText(FIELD_TEST, test);
	}

	public void enterLabNumberSearch(String accesionNumber) {
		setTextToFieldNoEnter(FIELD_LAB_NUMBER_SEARCH, accesionNumber);
	}

	public void enterPatientIdSearch(String patientId) {
		setTextToFieldNoEnter(FIELD_PATIENT_ID_SEARCH, patientId);
	}

	public void enterLastNameSearch(String lastName) {
		setTextToFieldNoEnter(FIELD_LAST_NAME_SEARCH, lastName);
	}

	public void enterFirstNameSearch(String firstName) {
		setTextToFieldNoEnter(FIELD_FIRST_NAME_SEARCH, firstName);
	}

	public void clearLabNumberSearch() {
		clearText(FIELD_LAB_NUMBER_SEARCH);
	}

	public void clearPatientIdSearch() {
		clearText(FIELD_PATIENT_ID_SEARCH);
	}

	public void clearLastNameSearch() {
		clearText(FIELD_LAST_NAME_SEARCH);
	}

	public void clearFirstNameSearch() {
		clearText(FIELD_FIRST_NAME_SEARCH);
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

	public void enterContactTracingIndexName(String tracingName) {
		setText(FIELD_CONTACT_TRACING_INDEX_NAME, tracingName);
	}

	public void enterContactTracingIndexRecordNumber(String recordNumber) {
		setText(FIELD_CONTACT_TRACING_INDEX_RECORD_NUMBER, recordNumber);
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

	public void enterPatientAgeInYears(String age) {
		setText(FIELD_PATIENT_AGE_YEARS, age);
	}

	public void enterNextVistDate(String date) {
		setText(FIELD_NEXT_VISIT_DATE, date);
	}

	public void enterReferer(String referrer) {
		setText(FIELD_REFFER_REFFERER, referrer);
	}

	public String getRefererName() {
		return getValue(FIELD_REFFER_REFFERER);
	}

	public void enterReferralSentDate(String date) {
		setText(FIELD_REFFER_SENT_DATE, date);
	}

	public String getReferralSentDate() {
		return getValue(FIELD_REFFER_SENT_DATE);
	}

	public String getPatientSubjectNumber() {
		return getValue(FIELD_SUBJECT_NUMBER);
	}

	public String getPatientNationalId() {
		return getValue(FIELD_NATIONAL_ID);
	}

	public String getPatientLastName() {
		return getValue(FIELD_PATIENT_LAST_NAME);
	}

	public String getPatientFirstName() {
		return getValue(FIELD_PATIENT_FIRST_NAME);
	}

	public String getPatientEmail() {
		return getValue(FIELD_PATIENT_EMAIL);
	}

	public void clearTestsField() {
		clearText(FIELD_TEST);
	}

	public void clearPatientDateOfBirth() {
		clearText(FIELD_PATIENT_DOB);
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

	public String getPatientAgeValidateLabelClass() {
		return getClass(LABEL_VALIDATE_PATIENT_AGE);
	}

	public String getLoaderGifClass() {
		return getClass(GIF_LOADER);
	}

	public String getPatientSerchDisabledAttribute() {
		return findElement(BUTTON_PATIENT_SEARCH).getAttribute("disabled");
	}

	public boolean sampleTypesDropDownMenuContainsSampleTypesOptions() {
		return dropDownHasOptions(SELECT_SAMPLE);
	}

	public void selectSampleTypeFromDropDownMenu() {
		selectFrom(SELECT_SAMPLE, "SERUM");
	}

	public void selectSampleConditionFromDropDownMenu() {
		clickOn(SELECT_CONDITION);
		List<WebElement> options = getConditonSelectionField().findElements(FIELD_OPTION);
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

	public void selectReasonForReferal(String reason) {
		selectFrom(SELECT_REFFER_REASON, reason);
	}

	public String getDefaultReferralReason() {
		return getSelectedOption(SELECT_REFFER_REASON);
	}

	public String getSelectedTestName() {
		return getSelectedOption(SELECT_REFFER_TEST_NAME);
	}

	public Boolean reasonsForReferralListDisplay() {
		return dropDownHasOptions(SELECT_REFFER_REASON);
	}

	public void selectreferralInstitute() {
		selectOptionFromDropDown(SELECT_REFFER_INSTITUTE);
	}

	public Boolean institutesForReferralDisplay() {
		return dropDownHasOptions(SELECT_REFFER_INSTITUTE);
	}

	public void selectreferralTestName() {
		selectOptionFromDropDown(SELECT_REFFER_TEST_NAME);
	}

	public Boolean containsSeachResult() {
		return hasElement(SELECT_RESULT);
	}

	public void checkPatientEmailandSmsNotification() {
		clickOn(CHECK_BOX_PATIENT_EMAIL);
		clickOn(CHECK_BOX_PATIENT_SMS);
	}

	public void checkRequesterEmailandSmsNotification() {
		clickOn(CHECK_BOX_PROVIDER_EMAIL);
		clickOn(CHECK_BOX_PROVIDER_SMS);
	}

	public void waitForSeachResult() {
		waitForElement(SELECT_RESULT);
	}

	public void removeAddedSampleConditionFromDropDownMenu() throws InterruptedException {
		List<WebElement> options = getConditonSelectionField().findElements(FIELD_OPTION);
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
		List<WebElement> options = getSampleSelectionField().findElements(FIELD_OPTION);
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
		goToPage(PATH_SAMPLE_ENTRY_CONFIG);
		clickOn(RADIO_BUTTON_VALIDATE_TRUE);
		clickOn(BUTTON_SAVE_VALIDATION);
		this.go();
	}

	public void turnOnTelephoneValidation() {
		goToPage(PATH_SITE_INFO_CONFIG);
		clickOn(RADIO_BUTTON_VALIDATE_TRUE);
		clickOn(BUTTON_SAVE_VALIDATION);
		this.go();
	}

	public void searchPatient() {
		clickOn(BUTTON_PATIENT_SEARCH);
	}

	public Boolean saveButtonDeactivated() {
		Boolean deactivated = false;
		try {
			deactivated = findElement(BUTTON_SAVE).getAttribute("disabled").equals("true") ? true : false;
		} catch (NullPointerException e) {
			deactivated = false;
		}
		return deactivated;
	}

	public void innitialiseData(String accesionNumber) throws InterruptedException {
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		enterAccessionNumber(accesionNumber);
		clickOnNextVisitDate();
		if (alertPresent()) {
			acceptAlert();
			return;
		}
		Thread.sleep(1000);
		selectSiteNameFromDropDown();
		Thread.sleep(1000);
		enterRequesterLastName("SADDIO");
		clickAddSampleButton();
		selectSampleTypeFromDropDownMenu();
		clickPannelCheckBox();
		clickTestCheckBox();
		clickNewPatientButton();
		enterSubjectNumber("201807D9P" + uuidAsString);
		enterNationalId("201507D35" + uuidAsString);
		enterPatientLastName("moses");
		enterPatientFirstName("mutesasira");
		enterPatientStreet("street");
		enterPatientCommune("commune");
		enterPatientEmail("email@gmail.com");
		enterPatientPhone("+23063458788");
		selectPatientHelathRegionFromDropDownMenu();
		enterPatientDateofBirth("09/02/2019");
		selectPatientGenderFromDropDownMenu();
		selectPatientEducationFromDropDownMenu();
		selectPatientMaritalStatusFromDropDownMenu();
		enterPatientOtherNationality("nationality");
		clickSave();
	}

	public void innitialiseRandomData() throws InterruptedException {
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		clickGenerateButton();
		Thread.sleep(1000);
		selectSiteNameFromDropDown();
		Thread.sleep(1000);
		enterRequesterLastName("SADDIO");
		clickAddSampleButton();
		selectSampleTypeFromDropDownMenu();
		clickPannelCheckBox();
		clickTestCheckBox();
		clickNewPatientButton();
		enterSubjectNumber("201807D9P" + uuidAsString);
		enterNationalId("201507D35" + uuidAsString);
		enterPatientLastName("lastName");
		enterPatientFirstName("firstName");
		enterPatientStreet("street");
		enterPatientCommune("commune");
		enterPatientEmail("email@gmail.com");
		enterPatientPhone("+23063458788");
		selectPatientHelathRegionFromDropDownMenu();
		enterPatientDateofBirth("09/02/2019");
		selectPatientGenderFromDropDownMenu();
		selectPatientEducationFromDropDownMenu();
		selectPatientMaritalStatusFromDropDownMenu();
		enterPatientOtherNationality("nationality");
		clickSave();
	}

	public Boolean panelCheckBoxExists() {
		return hasElementWithoutWait(CHECK_BOX_PANNEL);
	}

	public Boolean testCheckBoxExists() {
		return hasElement(CHECK_BOX_TEST);
	}

	public HomePage goToHomePage() {
		this.goToPage(PATH_HOME);
		return new HomePage(this);
	}
}
