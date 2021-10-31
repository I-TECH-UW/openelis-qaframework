package org.openelisglobal.qaframework.automation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.AddPatientPage;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.test.TestBase;
import org.openelisglobal.qaframework.automation.utils.Utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PatientEntrySteps extends TestBase {
	
	private LoginPage loginPage;
	
	private HomePage homePage;
	
	private AddPatientPage addPatientPage;
	
	@After(RunTest.HOOK.PATIENT_ENTRY)
	public void destroy() {
		quit();
	}
	
	@Before(RunTest.HOOK.PATIENT_ENTRY)
	public void setLoginPage() {
		System.out.println("....Patient Entry......");
		loginPage = new LoginPage(getWebDriver());
	}
	
	@Given("User Vists Home Page and goes to Add Add|Modify Patient Page")
	public void visitLoginPage() throws Exception {
		homePage = loginPage.goToHomePage();
		addPatientPage = homePage.goToAddEditPatientPage();
	}
	
	@Then("Add|Modify Patient page appears with search field")
	public void AddModifyPageAppears() throws InterruptedException {
		//initialise data
		addPatientPage.innitialisePatientData("jimmy", "seruwu", false);
		homePage = addPatientPage.goToHomePage();
		if (addPatientPage.alertPresent()) {
			addPatientPage.acceptAlert();
		}
		addPatientPage = homePage.goToAddEditPatientPage();
		
		//cant register patient with accenetd character
		homePage = addPatientPage.goToHomePage();
		addPatientPage = homePage.goToAddEditPatientPage();
		addPatientPage.innitialisePatientData("jimmý", "seruwu", true);
		assertTrue(addPatientPage
		        .containsText("patientProperties.firstName: ValidName invalid name format, possibly illegal character"));
		homePage = addPatientPage.goToHomePage();
		addPatientPage = homePage.goToAddEditPatientPage();
		
		assertTrue(addPatientPage.containsText("Add/Modify Patient"));
		assertTrue(addPatientPage.containsText("Search"));
	}
	
	@And("Search button deactivated until search criteria is selected and text entered in the text field")
	public void searchButonDisabled() {
		assertTrue(addPatientPage.searchButtonDisabled());
	}
	
	@And("Search text boxes display correct search criteria")
	public void searchtextBoxesDisplayCorrectSeachCriteria() {
		assertTrue(addPatientPage.containsText("Lab No :"));
		assertTrue(addPatientPage.containsText("Patient ID :"));
		assertTrue(addPatientPage.containsText("Last Name :"));
		assertTrue(addPatientPage.containsText("First Name :"));
		assertTrue(addPatientPage.containsText("Gender:"));
	}
	
	@When("User enters known last name {string} in text box")
	public void enterLastName(String lastName) {
		addPatientPage.enterLastNameSearch(lastName);
		addPatientPage.clickSearchButton();
	}
	
	@Then("Search by Last name yields all patients with matching last name on Add Patient Page")
	public void searchByLastNameReturnsResults() {
		assertTrue(addPatientPage.containsSeachResult());
		assertPageContainsPatientResults(addPatientPage);
	}
	
	@When("User enters known first name {string} in text box")
	public void enterFirstName(String firstName) throws InterruptedException {
		addPatientPage.refreshPage();
		if (addPatientPage.alertPresent()) {
			addPatientPage.acceptAlert();
		}
		addPatientPage.enterFirstNameSearch(firstName);
		addPatientPage.clickSearchButton();
	}
	
	@Then("Search by First name yields all patients with matching first name on Add Patient Page")
	public void searchByFirstNameReturnsResults() {
		assertTrue(addPatientPage.containsSeachResult());
		assertPageContainsPatientResults(addPatientPage);
	}
	
	@When("User enters known last name {string} and first name {string}")
	public void enterFirstAndLastName(String lastName, String firstName) throws InterruptedException {
		addPatientPage.refreshPage();
		if (addPatientPage.alertPresent()) {
			addPatientPage.acceptAlert();
		}
		addPatientPage.enterLastNameSearch(lastName);
		addPatientPage.enterFirstNameSearch(firstName);
		addPatientPage.clickSearchButton();
	}
	
	@Then("Search by Last name and First name yields results for known matching names")
	public void searchByFirstAndLastNameReturnsResults() {
		assertTrue(addPatientPage.containsSeachResult());
		assertPageContainsPatientResults(addPatientPage);
	}
	
	@When("User enters known Subject Number {string}")
	public void enterSubjectNumber(String subjectNumber) throws InterruptedException {
		addPatientPage.refreshPage();
		if (addPatientPage.alertPresent()) {
			addPatientPage.acceptAlert();
		}
		addPatientPage.enterPatientIdSearch(subjectNumber);
		addPatientPage.clickSearchButton();
	}
	
	@Then("Search by Subject Number yields results for known matching names")
	public void searchBySubjectNumberReturnsResults() {
		assertTrue(addPatientPage.containsSeachResult());
		assertPageContainsPatientResults(addPatientPage);
	}
	
	@When("User enters known Lab Number {string}")
	public void enterLabNumber(String accesionNumber) throws InterruptedException {
		addPatientPage.refreshPage();
		if (addPatientPage.alertPresent()) {
			addPatientPage.acceptAlert();
		}
		addPatientPage.enterLabNumberSearch(accesionNumber);
		addPatientPage.clickSearchButton();
	}
	
	@Then("Search by Lab Number yields results for known matching names")
	public void searchByLabNumberReturnsResults() {
		assertTrue(addPatientPage.containsSeachResult());
		assertPageContainsPatientResults(addPatientPage);
	}
	
	@When("User Selects correct patient")
	public void selectPatient() throws InterruptedException {
		addPatientPage.selectFirstSearchResult();
		Thread.sleep(3000);
	}
	
	@Then("Patient Information form populates with patient information")
	public void patientInformationDisplays() {
		assertEquals("mutesasira", addPatientPage.getPatientFirstName());
		assertEquals("moses", addPatientPage.getPatientLastName());
	}
	
	@When("User Clicks New Patient on the Add Patient Page")
	public void clickNewPatient() throws InterruptedException {
		addPatientPage.clickNewPatientButton();
		Thread.sleep(1000);
	}
	
	@Then("Patient Information form clears")
	public void patientInformationClears() {
		assertEquals("", addPatientPage.getPatientFirstName());
		assertEquals("", addPatientPage.getPatientLastName());
	}
	
	@When("User Enters data into text fields")
	public void enterDataIntoTextFields() {
		addPatientPage.clickNewPatientButton();
		UUID uuid = UUID.randomUUID();
		addPatientPage.enterSubjectNumber("201807D9P" + uuid.toString());
		addPatientPage.enterNationalId("201507D35" + uuid.toString());
		addPatientPage.enterPatientLastName("lastName");
		addPatientPage.enterPatientFirstName("firstName");
		addPatientPage.enterPatientStreet("Gayaza");
		addPatientPage.enterPatientCommune("commune");
		addPatientPage.enterPatientEmail("jimmy@gmail.com");
		addPatientPage.enterPatientPhone("+23063458788");
	}
	
	@Then("All text fields accept text")
	public void fieldsAcceptText() {
		assertTrue(addPatientPage.getSubjectNumber().contains("201807D9P"));
		assertTrue(addPatientPage.getNationalId().contains("201507D35"));
		assertEquals("lastName", addPatientPage.getPatientLastName());
		assertEquals("firstName", addPatientPage.getPatientFirstName());
		assertEquals("Gayaza", addPatientPage.getPatientStreet());
		assertEquals("commune", addPatientPage.getPatientCommune());
		assertEquals("jimmy@gmail.com", addPatientPage.getPatientEmail());
		assertEquals("+23063458788", addPatientPage.getPatientPhone());
	}
	
	@And("National ID is mandatory")
	public void nationalIdIsMandatory() {
		assertTrue(addPatientPage.nationalIdIsRequired());
	}
	
	@And("Alert is given if Subject Number is already in use")
	public void alertGivenIfSubJectNumberIsAlreadyInUse() {
		addPatientPage.enterSubjectNumber("oe012");
		addPatientPage.clickNameField();
		addPatientPage.acceptAlert();
	}
	
	@And("If subject number is already in use, cannot save")
	public void cantNotSaveIfSubJectNumberIsAlreadyInUse() {
		assertTrue(addPatientPage.saveButtonDisabled());
	}
	
	@And("Alert given if National Identification Number  is already in use")
	public void alertGivenIfNationaIdIsAlreadyInUse() {
		addPatientPage.enterNationalId("ug012");
		addPatientPage.clickNameField();
		addPatientPage.acceptAlert();
	}
	
	@And("Cannot save if National Identification Number is already in use")
	public void cantNotSaveIfNationaIdIsAlreadyInUse() {
		assertTrue(addPatientPage.saveButtonDisabled());
	}
	
	@And("Alert given if Phone Number is not in correct format")
	public void alertGivenIfPhoneNumberIsIncorrect() throws InterruptedException {
		addPatientPage.enterPatientPhone("0772");
		addPatientPage.clickNameField();
		addPatientPage.acceptAlert();
		Thread.sleep(1000);
		assertEquals("error", addPatientPage.getPatientPhoneClass().trim());
	}
	
	@When("User Selects a Health County from the drop-down list")
	public void userSelectCountry() {
		addPatientPage.selectPatientNationalityFromDropDownMenu();
	}
	
	@Then("All XX counties are listed and one option can be selected")
	public void countriesListed() {
		assertTrue(addPatientPage.countriesListedFromDropDownMenu());
	}
	
	@When("User Selects a Health District from the drop-down list")
	public void userSelectHealthDistrict() {
		addPatientPage.selectPatientHelathRegionFromDropDownMenu();
	}
	
	@Then("All Health Districts under the Health County selected in the previous step should be visible")
	public void healthRegionsListed() {
		assertTrue(addPatientPage.healthRegionsListedFromDropDownMenu());
	}
	
	@When("User Fills in Date of Birth {string}")
	public void userEntersDob(String dateOfBirth) {
		addPatientPage.enterPatientDateofBirth(dateOfBirth);
	}
	
	@Then("Date of Birth is mandatory")
	public void dateOfBirthIsMandatory() {
		assertTrue(addPatientPage.dateOfBirthIsRequired());
	}
	
	@And("Alert appears if DOB format {string} is incorrect")
	public void alertAppersIfDobIsIncorrect(String dob) throws InterruptedException {
		addPatientPage.enterPatientDateofBirth(dob);
		addPatientPage.clickNameField();
		Thread.sleep(2000);
		assertEquals(addPatientPage.getPatientDoBValidateLabelClass(), "badmessage");
	}
	
	@And("Alert appears if date of birth is in the future")
	public void alertAppearsIfDobIsInFuture() throws InterruptedException {
		addPatientPage.enterPatientDateofBirth(Utils.getFutureDate());
		addPatientPage.clickNameField();
		addPatientPage.clickNameField();
		Thread.sleep(2000);
		addPatientPage.acceptAlert();
		assertEquals(addPatientPage.getPatientDoBValidateLabelClass(), "badmessage");
	}
	
	@And("Automatically fills correct age when DOB {string} is filled in")
	public void automaticallyFillsAge(String dateOfBirth) throws InterruptedException {
		addPatientPage.clearPatientAgeDays();
		addPatientPage.clearPatientAgeYears();
		addPatientPage.clearPatientAgeYears();
		assertEquals("", addPatientPage.getPatientAgeYears());
		assertEquals("", addPatientPage.getPatientAgeMonths());
		assertEquals("", addPatientPage.getPatientAgeYears());
		addPatientPage.enterPatientDateofBirth(dateOfBirth);
		addPatientPage.clickNameField();
		Thread.sleep(2000);
		assertNotEquals("", addPatientPage.getPatientAgeYears());
		assertNotEquals("", addPatientPage.getPatientAgeMonths());
		assertNotEquals("", addPatientPage.getPatientAgeYears());
	}
	
	@When("User Deletes Date of Birth and enters Age {string}")
	public void enterAge(String age) {
		addPatientPage.clearPatientDateOfBirth();
		addPatientPage.enterPatientAgeInYears(age);
	}
	
	@Then("If DOB is left blank and Age is filled,Field generates DOB with correct year for Age {string}")
	public void fieldGeneratesDob(String age) throws InterruptedException {
		addPatientPage.clickNameField();
		Thread.sleep(1000);
		String year = Utils.generateDobYearFromAge(age);
		assertEquals(addPatientPage.getPatientDateOfBirth(), "xx/xx/" + year);
	}
	
	@And("Alert appears if Age is -1 , 100 and 100+")
	public void alertApperasIfAgeIsnegative() throws InterruptedException {
		List<String> invalidAge = new ArrayList<String>();
		invalidAge.add("-1");
		invalidAge.add("100");
		invalidAge.add("101");
		for (String age : invalidAge) {
			addPatientPage.clearPatientDateOfBirth();
			addPatientPage.enterPatientAgeInYears(age);
			addPatientPage.clickNameField();
			Thread.sleep(2000);
			assertEquals("badmessage", addPatientPage.getPatientAgeValidateLabelClass());
		}
	}
	
	@When("User Selects from drop-down list for gender")
	public void selectGenderFromDropDown() {
		addPatientPage.selectPatientGenderFromDropDownMenu();
	}
	
	@Then("Gender options are displayed form drop-down list")
	public void genderOptionsDisplayed() {
		assertTrue(addPatientPage.genderListedFromDropDownMenu());
		addPatientPage.selectPatientGenderFromDropDownMenu("1 = Male");
		addPatientPage.selectPatientGenderFromDropDownMenu("2 = Female");
	}
	
	@When("User Leaves mandatory fields without data on Add Patient Page")
	public void enterTextLeavingOutMandatoryFields() {
		addPatientPage.clickNewPatientButton();
		//leave out National Id
		addPatientPage.enterSubjectNumber("201807D9PXX");
		addPatientPage.enterPatientLastName("lastName");
		addPatientPage.enterPatientFirstName("firstName");
		addPatientPage.enterPatientStreet("Gayaza");
		addPatientPage.enterPatientCommune("commune");
		addPatientPage.enterPatientEmail("jimmy@gmail.com");
		addPatientPage.enterPatientPhone("+23063458788");
		addPatientPage.enterPatientDateofBirth("09/02/2019");
		addPatientPage.selectPatientGenderFromDropDownMenu();
		addPatientPage.selectPatientEducationFromDropDownMenu();
		addPatientPage.selectPatientMaritalStatusFromDropDownMenu();
		addPatientPage.enterPatientOtherNationality("uganda");
		addPatientPage.selectPatientHelathRegionFromDropDownMenu();
	}
	
	@Then("Save button deactivated until all mandatory fields are filled on Add Patient Page")
	public void saveBUttonDeactivated() {
		assertTrue(addPatientPage.saveButtonDisabled());
	}
	
	@When("User Completes all mandatory fields on Add Patient Page")
	public void completeMandatoryFields() throws InterruptedException {
		//Enter National Id to complete Mandatory Fields
		addPatientPage.enterNationalId("201507D35XX");
		addPatientPage.clickNameField();
		Thread.sleep(1000);
	}
	
	@Then("Save button activated when all mandatory fields are filled on Add Patient Page")
	public void saveBUttonActivated() {
		assertFalse(addPatientPage.saveButtonDisabled());
	}
	
	@When("User Clicks Cancel on Add Patient Page")
	public void clickCancel() {
		addPatientPage.clickCancel();
	}
	
	@Then("Pop-up message appears, `Leave Site? Changes you made may not be saved` on Add Patient Page")
	public void messageAlertToLeavePage() throws InterruptedException {
		assertTrue(addPatientPage.alertPresent());
	}
	
	@When("User Clicks Cancel to Dismis alert")
	public void dismissAlert() {
		addPatientPage.dismissAlert();
	}
	
	@Then("Patient Information form remains on Add Patient screen")
	public void patientIformationRemains() {
		assertEquals("201507D35XX", addPatientPage.getNationalId());
		assertEquals("201807D9PXX", addPatientPage.getSubjectNumber());
	}
	
	@When("User Clicks Save on Add Patient Page")
	public void clickSave() throws InterruptedException {
		//add data and click save
		addPatientPage.innitialisePatientData("test", "patient", true);
		Thread.sleep(1000);
	}
	
	@Then("A clear Add|Modify Patient form appears along with the message, `Save was successful` in green")
	public void save() {
		assertTrue(addPatientPage.containsText("Save was successful"));
		assertTrue(addPatientPage.containsText("Add/Modify Patient"));
		assertTrue(addPatientPage.containsText("Lab No :"));
		assertTrue(addPatientPage.containsText("Patient ID :"));
		assertTrue(addPatientPage.containsText("Last Name :"));
		assertTrue(addPatientPage.containsText("First Name :"));
		assertTrue(addPatientPage.containsText("Gender:"));
	}
	
	@And("User Goes to the bottom of the page and click Cancel and Returns to home page")
	public void clickCanelToREturnToHOmePage() {
		addPatientPage.clickCancel();
		assertFalse(addPatientPage.containsText("Add/Modify Patient"));
	}
	
	@When("User Searches for Patient on the Add Order Page for a known Patient with known last name {string} and first name {string}")
	public void searchForKnownPatient(String lastName, String firstName) {
		addPatientPage.enterLastNameSearch(lastName);
		addPatientPage.enterFirstNameSearch(firstName);
		addPatientPage.clickSearchButton();
	}
	
	@Then("Correct patient information ,last name {string} and first name {string}, appears when searched for")
	public void correctPatientInformationDisplays(String lastName, String firstName) throws InterruptedException {
		assertTrue(addPatientPage.containsSeachResult());
		assertPageContainsPatientResults(addPatientPage);
		Thread.sleep(1000);
		assertEquals(lastName, addPatientPage.getPatientLastName());
		assertEquals(firstName, addPatientPage.getPatientFirstName());
	}
}
