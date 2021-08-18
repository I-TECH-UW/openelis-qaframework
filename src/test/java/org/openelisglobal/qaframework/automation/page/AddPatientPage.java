package org.openelisglobal.qaframework.automation.page;

import java.util.UUID;

import org.openqa.selenium.By;

public class AddPatientPage extends Page{

    private static final String PAGE_PATH = "/PatientManagement.do";

    private static final String PATH_HOME = "/Dashboard.do";

    private static final By BUTTON_PATIENT_SEARCH = By.id("enhancedSearchButton");
	
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
	
	private static final By FIELD_PATIENT_STREET = By.id("streetID");
	
	private static final By FIELD_PATIENT_COMMUNE = By.id("communeID");
	
	private static final By FIELD_PATIENT_TOWN = By.id("cityID");
	
	private static final By FIELD_PATIENT_PHONE = By.id("patientPhone");
	
	private static final By FIELD_PATIENT_DOB = By.id("dateOfBirthID");
	
	private static final By FIELD_PATIENT_EMAIL = By.id("patientEmail");
	
	private static final By FIELD_PATIENT_NATIONALITY_OTHER = By.id("nationalityOtherId");
	
	private static final By FIELD_PATIENT_AGE_YEARS = By.id("ageYears");
	
	private static final By BUTTON_NEW_PATIENT = By.xpath("//input[@value='New Patient']");
	
	private static final By BUTTON_SAVE = By.id("saveButtonId");
	
	private static final By BUTTON_CANCEL = By.id("cancelButtonId");
	
	private static final By SELECT_PATIENT_DISTRICT = By.id("healthRegionID");
	
	private static final By SELECT_PATIENT_GENDER = By.id("genderID");
	
	private static final By SELECT_PATIENT_EDUCATION = By.id("educationID");
	
	private static final By SELECT_PATIENT_MARITAL_STATUS = By.id("maritialStatusID");

    private static final By SELECT_RESULT = By.id("sel_1");

    public AddPatientPage(Page parent) {
        super(parent);
    }

    @Override
    public String getPageUrl() {
        return PAGE_PATH;
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
    
    public void clickNewPatientButton() {
		clickOn(BUTTON_NEW_PATIENT);
	}

    public void clickSave() {
		clickOn(BUTTON_SAVE);
	}

	public void clickCancel() {
		clickOn(BUTTON_CANCEL);
	}

    public void clickSearchButton() {
		clickOn(BUTTON_PATIENT_SEARCH);
	}

    public Boolean searchButtonDisabled(){
        return isDisabled(BUTTON_PATIENT_SEARCH);
    }

    public Boolean containsSeachResult(){
        return hasElement(SELECT_RESULT);
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
	
	public void enterPatientAgeInYears(String age) {
		setText(FIELD_PATIENT_AGE_YEARS, age);
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

    public HomePage goToHomePage() {
		this.goToPage(PATH_HOME);
		return new HomePage(this);
	}

   	public void innitialisePatientData(String firstName, String lastName, Boolean random) throws InterruptedException {
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		clickNewPatientButton();
		if (random) {
			enterSubjectNumber("201807D9P" + uuidAsString);
			enterNationalId("201507D35" + uuidAsString);
		} else {
			enterSubjectNumber("oe012");
			enterNationalId("ug012");			
			if (alertPresent()) {
				acceptAlert();
				acceptAlert();
				return;
			}
		}
		enterPatientLastName(lastName);
		enterPatientFirstName(firstName);
		enterPatientStreet("Gayaza");
		enterPatientCommune("commune");
		enterPatientEmail("jimmy@gmail.com");
		enterPatientPhone("+23063458788");
		selectPatientHelathRegionFromDropDownMenu();
		enterPatientDateofBirth("09/02/2019");
		selectPatientGenderFromDropDownMenu();
		selectPatientEducationFromDropDownMenu();
		selectPatientMaritalStatusFromDropDownMenu();
		enterPatientOtherNationality("uganda");
		clickSave();
	}
}
