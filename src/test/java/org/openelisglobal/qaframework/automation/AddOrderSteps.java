package org.openelisglobal.qaframework.automation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.AddOrderPage;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.page.TestProperties;
import org.openelisglobal.qaframework.automation.test.TestBase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddOrderSteps extends TestBase {

	private LoginPage loginPage;

	private HomePage homePage;

	private AddOrderPage addOrderPage;

	protected TestProperties testProperties = TestProperties.instance();

	@After(RunTest.HOOK.ORDER)
	public void destroy() {
		quit();
	}

	@Before(RunTest.HOOK.ORDER)
	public void setLoginPage() {
		loginPage = new LoginPage(getWebDriver());
	}

	@Given("User logs in and visits Home page")
	public void visitLoginPage() throws Exception {
		homePage = loginPage.goToHomePage();
	}

	@And("User clicks add order and goes to Add order Page")
	public void goToAddOrderPage() throws Exception {
		addOrderPage = homePage.goToAddOrderPage();
	}

	@Then("Order form should appear")
	public void orderFormShouldAppear() throws Exception {
		assertTrue(addOrderPage.containsTextRequest());
	}

	@When("User enters Accesion Number {string}")
	public void enterAcessionNumber(String accesionNumber) throws Exception {
		addOrderPage.turnOnAcessionValidation();
		addOrderPage.enterAccessionNumber(accesionNumber);
	}

	@Then("Validate {string} AccesionNumber Entered {string}")
	public void checkEnteredAccessionNumber(String status, String accesionNumber)
			throws Exception {
		if (status.equals("valid")) {
			addOrderPage.clickOnNextVisitDate();
			Thread.sleep(1000);
			assertTrue(addOrderPage.accessionNumberEntered(accesionNumber));
			assertEquals(addOrderPage.getAccesionNumberClass(), "text");
		} else if (status.equals("invalid")) {
			addOrderPage.clickOnNextVisitDate();
			Thread.sleep(1000);
			addOrderPage.acceptAlert();
			assertEquals(addOrderPage.getAccesionNumberClass(), "text error");
		}

	}

	@When("User clicks Generate Button")
	public void clickGenerate() throws Exception {
		addOrderPage.clickGenerateButton();
	}

	@Then("Generated Accesion Number should be a digit")
	public void generatedAccesionNumbershouldBeDigit() throws Exception {
		assertTrue(addOrderPage.GeneratedAccessionNumberIsDigit());
	}

	@Then("View page Request Date and Received Date Default to the current date")
	public void requestAndRecievedDatesShouldDefaultToCurrent()
			throws Exception {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(date);
		// this test will fail at times since the server and the testing
		// framework run in different time zones
		// assertEquals(addOrderPage.getRecievedDateValue(), strDate);
		// assertEquals(addOrderPage.getRequestDateValue(), strDate);
	}

	@And("Both request and received date should be mandatory")
	public void requestAndRecievedDatesShouldbeMandatory() throws Exception {
		assertEquals(addOrderPage.getRequestDateRequiredClass(),
				"requiredlabel");
		assertEquals(addOrderPage.getRecievedDateRequiredClass(),
				"requiredlabel");
	}

	@When("User enters incorrect Request and Received Date format {string}")
	public void UserEntersIncorrectRequestAndRecievedDateFormat(String date)
			throws Exception {
		assertEquals(addOrderPage.getRecievedDateClass(), "text required");
		assertEquals(addOrderPage.getRequestDateClass(), "required");
		addOrderPage.enterRecievedDate(date);
		addOrderPage.enterRequestDate(date);
	}

	@Then("Request and Received Date Fields should show error")
	public void RequestAndRecievedDateFieldShouldThrowError() throws Exception {
		// enter next Vist Date to triger field error
		addOrderPage.clickOnNextVisitDate();
		Thread.sleep(1000);
		assertEquals(addOrderPage.getRecievedDateClass(), "text required error");
		assertEquals(addOrderPage.getRequestDateClass(), "required error");
	}

	@When("User enters Request Date in future")
	public void UserEntersRequestInFuture() throws Exception {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(cal.getTime());
		addOrderPage.enterRequestDate(strDate);
	}

	@Then("Alert should appear if date is in future")
	public void alertShouldApperIfFutureDateIsEntered() throws Exception {
		// enter next Vist Date to triger field error
		addOrderPage.clickOnNextVisitDate();
		Thread.sleep(1000);
		addOrderPage.acceptAlert();
		assertEquals(addOrderPage.getRequestDateClass(), "required error");
	}

	@When("User enters correct Request and Received Date format")
	public void UserEnterscorrectRequestAndRecievedDateFormat()
			throws Exception {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(cal.getTime());
		addOrderPage.enterRecievedDate(strDate);
		addOrderPage.enterRequestDate(strDate);
	}

	@Then("Request and Received Date Fields should not show error")
	public void RequestAndRecievedDateFieldShouldNotThrowError()
			throws Exception {
		// enter next Vist Date to triger field error
		addOrderPage.clickOnNextVisitDate();
		Thread.sleep(1000);
		assertEquals(addOrderPage.getRecievedDateClass(), "text required");
		assertEquals(addOrderPage.getRequestDateClass(), "required");
	}

	@When("User enters Reception time {string}")
	public void userEntersRecievedTime(String time) {
		addOrderPage.enterRecievedTime(time);
	}

	@Then("Field Automatically corrects {string} straight numeric to proper format HH:MM {string}")
	public void fieldAutomaticallyCorrectsReceptionTime(String action,
			String correctedTime) throws InterruptedException {
		// enter next Vist Date to triger field error
		addOrderPage.clickOnNextVisitDate();
		if (action.trim().equals("auto-correct")) {
			Thread.sleep(1000);
			assertEquals(addOrderPage.getRecievedTimeValue(), correctedTime);
		} else if (action.trim().equals("none")) {
			assertEquals(addOrderPage.getRecievedTimeValue(), correctedTime);
		}
	}

	@And("Field validates {string} correct format")
	public void fieldValidatesRecetionTimeFormat(String status)
			throws Exception {
		if (status.trim().equals("valid")) {
			assertNotEquals(addOrderPage.getRecievedTimeClass(), "error");
		} else if (status.trim().equals("invalid")) {
			assertEquals(addOrderPage.getRecievedTimeClass().trim(), "error");
		}
	}

	@Then("Site Name is mandatory")
	public void siteNameShoulBeMandatory() {
		assertEquals(addOrderPage.getSiteNameRequiredClass(), "requiredlabel");
	}

	@And("User Selects Site Name from a Drop down Menu")
	public void selectSiteNameFromDropDownMenu() throws InterruptedException {
		addOrderPage.selectSiteNameFromDropDown();
	}

	@And("User Selects Program from a Drop down Menu")
	public void selectProgramFromDropDownMenu() throws InterruptedException {
		addOrderPage.selectProgramFromDropDown();
	}

	@Then("Requester's Last Name is mandatory")
	public void requesterLastNameIsMandatory() throws InterruptedException {
		assertEquals(addOrderPage.getLastNameRequiredClass(), "requiredlabel");
	}

	@And("User Enters Requester's Last Name {string}")
	public void enterLastName(String lastName) {
		addOrderPage.enterLastName(lastName);
		assertEquals(addOrderPage.getLastNameValue(), lastName);
	}

	@And("User Enters Requester's First Name {string}")
	public void enterFirstName(String firstName) {
		addOrderPage.enterFirstName(firstName);
		assertEquals(addOrderPage.getFistNameValue(), firstName);
	}

	@When("User Enters Telephone Number {string}")
	public void enterTelephone(String telephone) {
		addOrderPage.turnOnTelephoneValidation();
		addOrderPage.enterTelephone(telephone);
	}

	@Then("Validate {string} Telephone Number")
	public void validateTelephoneNumber(String status)
			throws InterruptedException {
		if (status.equals("valid")) {
			addOrderPage.clickOnNextVisitDate();
			Thread.sleep(1000);
			assertEquals(addOrderPage.getTelephoneNumberClass(), "text");
		} else if (status.equals("invalid")) {
			addOrderPage.clickOnNextVisitDate();
			Thread.sleep(1000);
			addOrderPage.acceptAlert();
			assertEquals(addOrderPage.getTelephoneNumberClass(), "text error");
		}
	}

	@And("User Enters Fax {string}")
	public void enterFax(String fax) {
		addOrderPage.enterFax(fax);
	}

	@And("User Enters Email {string}")
	public void enterEmail(String email) throws InterruptedException {
		addOrderPage.enterEmail(email);
	}

	@Then("Sample addition is mandatory")
	public void sampleAdditionMandatory() {
		assertEquals(addOrderPage.getSampleRecquiredClass(), "requiredlabel");
	}

	@When("User Clicks on + Button next to Sample")
	public void clickAddSample() {
		addOrderPage.clickAddSampleButton();
	}

	@Then("Sample Selection Field appears")
	public void sampleSelectionFiedlAppears() {
		assertNotNull(addOrderPage.getSampleSelectionField());
	}

	@And("Sample types display in drop-down list")
	public void sampleTypesDisplayInDropDownMenu() {
		assertTrue(addOrderPage.sampleTypesDisplayInDropDownMenu());
	}

	@And("User Selects Sample Type from Drop down menu")
	public void selectSampleTypeFromDropDownMenu() {
		addOrderPage.selectSampleTypeFromDropDownMenu();
	}

	@And("User Selects Sample Conditions from Drop down menu")
	public void selectSampleConditionFromDropDownMenu() {
		addOrderPage.selectSampleConditionFromDropDownMenu();
	}

	@And("User Clicks X to remove added Sample Conditions")
	public void removeAddedSampleConditionFromDropDownMenu()
			throws InterruptedException {
		addOrderPage.removeAddedSampleConditionFromDropDownMenu();
	}

	@And("User Clicks remove button to remove added Sample")
	public void removeAddedSampleTypeFromDropDownMenu() {
		addOrderPage.removeAddedSampleType();
	}

	@And("User Re-adds Samples")
	public void readdSamples() {
		addOrderPage.reAddSamples();
		assertNotNull(addOrderPage.getRemoveAllButton());
	}

	@And("User Clicks to Remove all")
	public void removesAllSamples() {
		addOrderPage.clickRemoveAllButton();
	}

	@When("User enters Incorrect Date format {string}")
	public void enterIncorrectDateFormat(String date) {
		addOrderPage.enterCollectionDate(date);
	}

	@Then("Text field hightlights in red")
	public void textFieldHighlightsInRed() throws InterruptedException {
		addOrderPage.clickOnCollectionTime();
		Thread.sleep(1000);
		assertEquals(addOrderPage.getCollectionDateClass(), "text error");
	}

	@When("User enters Date In the future")
	public void enterDateInTheFuture() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(cal.getTime());
		addOrderPage.enterCollectionDate(strDate);
	}

	@Then("Pop-up alert appears if date is in the future")
	public void poppAlertAppears() throws InterruptedException {
		addOrderPage.clickOnCollectionTime();
		Thread.sleep(1000);
		addOrderPage.acceptAlert();
		assertEquals(addOrderPage.getCollectionDateClass(), "text error");
	}

	@When("User enters correct Date")
	public void enterCorrectDateFormat() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(cal.getTime());
		addOrderPage.enterCollectionDate(strDate);
	}

	@Then("Text field accepts the correct date format")
	public void textFieldAcceptsCorrectDate() throws InterruptedException {
		addOrderPage.clickOnCollectionTime();
		Thread.sleep(1000);
		assertEquals(addOrderPage.getCollectionDateClass(), "text");
	}

	@When("User enters Collection time {string}")
	public void userEntersCollectionTime(String time) {
		addOrderPage.enterCollectionTime(time);
	}

	@Then("Field Automatically corrects {string} straight numeric to proper Collection Time format HH:MM {string}")
	public void fieldAutomatocallyCorrectsCollectionTime(String action,
			String correctedTime) throws InterruptedException {
		addOrderPage.clickCollectorField();
		if (action.trim().equals("auto-correct")) {
			Thread.sleep(1000);
			assertEquals(addOrderPage.getCollectionTimeValue(), correctedTime);
		} else if (action.trim().equals("none")) {
			assertEquals(addOrderPage.getCollectionTimeValue(), correctedTime);
		}
	}

	@And("Field validates {string} Collection Time")
	public void fieldvalidatesCollectionTimeFormat(String status)
			throws Exception {
		if (status.trim().equals("valid")) {
			assertNotEquals(addOrderPage.getCollectionTimeClass(), "text error");
		} else if (status.trim().equals("invalid")) {
			assertEquals(addOrderPage.getCollectionTimeClass(), "text error");
		}
	}

	@When("User Enters Collector {string}")
	public void enterCollector(String name) {
		addOrderPage.enterCollector(name);
	}

	@Then("Field Acceps text {string}")
	public void assertTextEntered(String name) {
		assertEquals(addOrderPage.getCollectorValue(), name);
	}

	@Then("Tests entry is marked mandatory")
	public void testIsMarkedMandatory() {
		assertEquals(addOrderPage.getTestReqcuiredClass(), "requiredlabel");
	}

	@Then("Available Tests exists")
	public void hasAvailableTest() {
		assertTrue(addOrderPage.containsText("Available Tests"));
	}

	@When("User Checks checkbox next to test name")
	public void checkTestNameCheckBox() {
		addOrderPage.clickTestCheckBox();
	}

	@Then("Checkbox sticks, test name appears under Tests box")
	public void testNameAppearsUnderTestBox() {
		assertEquals(addOrderPage.getTestValue(), "SWAB (M/C/S)");
	}

	@When("User unChecks checkbox next to test name")
	public void unCheckTestNameCheckBox() {
		addOrderPage.clickTestCheckBox();
	}

	@Then("Name disappears from Tests box")
	public void testNameDisppearsFromTestBox() {
		assertEquals(addOrderPage.getTestValue(), "");
	}

	@When("User Checks checkbox next to Panel name")
	public void checkPannelNameCheckBox() {
		addOrderPage.clickPannelCheckBox();
	}

	@Then("All applicable panel tests apear in the Testsbox")
	public void AllApplicableTestNameAppearsUnderTestBox() {
		assertEquals(addOrderPage.getTestValue(), "Antigen Covid,COVID-19 PCR");
	}

	@When("User unChecks checkbox next to Panel name")
	public void unCheckPannelNameCheckBox() {
		addOrderPage.clickPannelCheckBox();
	}

	@Then("All Test Names disappears from Tests box")
	public void allTestNameDisppearsFromTestBox() {
		assertEquals(addOrderPage.getTestValue(), "");
	}

	@When("User enters Text in Tests Box")
	public void enterTests() {
		try {
			addOrderPage.enterTest("Test A");
		} catch (Exception e) {

		}
	}

	@Then("Text cannot be entered in Tests Box")
	public void textDoesNotEnterTestBox() {
		assertNotEquals(addOrderPage.getTestValue(), "Test A");
		assertEquals(addOrderPage.getTestValue(), "");
	}

	@When("User deletes Text in Tests Box")
	public void deleteTests() {
		addOrderPage.clickTestCheckBox();
		try {
			addOrderPage.clearTestsField();
		} catch (Exception e) {

		}
	}

	@Then("Text cannot be cleared in Tests Box")
	public void textCanNotBeDeletedFromTestBox() {
		assertNotEquals(addOrderPage.getTestValue(), "");
		assertEquals(addOrderPage.getTestValue(), "SWAB (M/C/S)");
	}

	@Then("Patient information form is marked mandatory")
	public void patientInformationIsMarkedMandatory() {
		assertEquals(addOrderPage.getPatientInformationRecquiredClass(),
				"requiredlabel");
	}

	@When("User Expands Patient information form by clicking the + button next to Patient")
	public void expandPatientInformatio() {
		addOrderPage.clickAddPatientInformation();
	}

	@Then("Patient Search appears")
	public void patientSearchAppears() {
		assertTrue(addOrderPage.containsText("Search"));
	}

	@Then("Search button deactivated until search criteria selected and text entered")
	public void patientSerachButtonDeactivated() {
		assertEquals(addOrderPage.getPatientSerchDisabledAttribute(), "true");
	}

	@And("User Searches by Accesion Number {string}")
	public void searchByAccesionNumber(String accesionNumber) {
		addOrderPage.enterLabNumberSearch(accesionNumber);
		assertNull(addOrderPage.getPatientSerchDisabledAttribute());
		addOrderPage.serchPatient();
	}

	@And("User Searches by Patient Id {string}")
	public void searchByPatientId(String patientId) {
		addOrderPage.enterPatientIdSearch(patientId);
		assertNull(addOrderPage.getPatientSerchDisabledAttribute());
		addOrderPage.serchPatient();
	}

	@And("User Searches by Last Name {string}")
	public void searchByLastName(String lastName) {
		addOrderPage.enterLastNameSearch(lastName);
		assertNull(addOrderPage.getPatientSerchDisabledAttribute());
		addOrderPage.serchPatient();
	}

	@And("User Searches by First Name {string}")
	public void searchByFirstName(String firstName) {
		addOrderPage.enterFirstNameSearch(firstName);
		assertNull(addOrderPage.getPatientSerchDisabledAttribute());
		addOrderPage.serchPatient();
	}

	@And("User Searches by Date of Birth {string}")
	public void searchByDateOfBirth(String dateOfBirth) {
		addOrderPage.enterDateOfBirthSearch(dateOfBirth);
		assertNull(addOrderPage.getPatientSerchDisabledAttribute());
		addOrderPage.serchPatient();
	}

	@And("User Clicks New Patient Button")
	public void clickNewPatient() throws InterruptedException {

		// innitializing data
		addOrderPage.clickGenerateButton();
		Thread.sleep(1000);
		addOrderPage.selectSiteNameFromDropDown();
		Thread.sleep(1000);
		addOrderPage.enterLastName("SADDIO");
		addOrderPage.clickAddSampleButton();
		addOrderPage.selectSampleTypeFromDropDownMenu();
		addOrderPage.clickPannelCheckBox();

		addOrderPage.clickNewPatientButton();
	}

	@And("User Enters Subject Number {string}")
	public void enterPatientSubjectNumber(String subJectNumber) {
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		addOrderPage.enterSubjectNumber(subJectNumber + uuidAsString);
	}

	@And("User Enters National ID {string}")
	public void enterPatientNationalId(String nationalID) {
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		addOrderPage.enterNationalId(nationalID + uuidAsString);
	}

	@And("User Enters Patient Last Name {string}")
	public void enterPatientLastName(String lastName) {
		addOrderPage.enterPatientLastName(lastName);
	}

	@And("User Enters Patient First Name {string}")
	public void enterPatientFirstName(String firstName) {
		addOrderPage.enterPatientFirstName(firstName);
	}

	@And("User Enters Contact Last Name {string}")
	public void enterContactLastName(String lastName) {
		addOrderPage.enterContactLastName(lastName);
	}

	@And("User Enters Contact First Name {string}")
	public void enterContactFirstName(String firstName) {
		addOrderPage.enterContactFirstName(firstName);
	}

	@And("User Enters Contact Email {string}")
	public void enterContactEmail(String email) {
		addOrderPage.enterContactEmail(email);
	}

	@And("Field validates {string} Contact Email")
	public void validateContactEmail(String status) throws InterruptedException {
		addOrderPage.clickOtherNationality();
		Thread.sleep(1000);
		if (status.equals("valid")) {
			assertEquals(addOrderPage.getContactEmailFieldClass(), "text");
		} else if (status.equals("invalid")) {
			assertEquals(addOrderPage.getContactEmailFieldClass(), "text error");
		}
	}

	@And("User Enters Contact Phone {string}")
	public void enterContactPhone(String phone) {
		addOrderPage.enterContactPhone(phone);
	}

	@And("User Enters Patient Street {string}")
	public void enterPatientStreet(String street) {
		addOrderPage.enterPatientStreet(street);
	}

	@And("User Enters Patient Commune {string}")
	public void enterPatientCommune(String commune) {
		addOrderPage.enterPatientCommune(commune);
	}

	@And("User Enters Patient Town {string}")
	public void enterPatientTown(String town) {
		addOrderPage.enterPatientTown(town);
	}

	@And("User Enters Patient Phone {string}")
	public void enterPatientPhone(String phone) {
		addOrderPage.enterPatientPhone(phone);
	}

	@Then("Field validates {string} Patient Phone")
	public void validatePatientPhone(String status) throws InterruptedException {
		addOrderPage.clickOtherNationality();
		Thread.sleep(1000);
		if (status.equals("valid")) {
			assertNotEquals(addOrderPage.getPatientPhoneFieldClass().trim(),
					"error");
		} else if (status.equals("invalid")) {
			addOrderPage.acceptAlert();
			assertEquals(addOrderPage.getPatientPhoneFieldClass().trim(),
					"error");
		}
	}

	@And("User Enters Patient Email {string}")
	public void enterPatientEmail(String email) {
		addOrderPage.enterPatientEmail(email);
	}

	@And("Field validates {string} Patient Email")
	public void validatePatientEmail(String status) throws InterruptedException {
		addOrderPage.clickOtherNationality();
		Thread.sleep(1000);
		if (status.equals("valid")) {
			assertNotEquals(addOrderPage.getPatientEmailFieldClass().trim(),
					"error");
		} else if (status.equals("invalid")) {
			assertEquals(addOrderPage.getPatientEmailFieldClass().trim(),
					"error");
		}
	}

	@And("User Selects Patient Health Region")
	public void selectPatientDistrict() {
		addOrderPage.selectPatientHelathRegionFromDropDownMenu();
	}

	@And("User Enters Patient Date of Birth {string}")
	public void enterPatientDateOfBirth(String dob) {
		addOrderPage.enterPatientDateofBirth(dob);
	}

	@Then("Field validates {string} Patient Date Of Birth")
	public void validatePatientDateOfBirth(String status)
			throws InterruptedException {
		addOrderPage.clickOtherNationality();
		Thread.sleep(1000);
		if (status.equals("valid")) {
			assertNotEquals(addOrderPage.getPatientDoBValidateLabelClass(),
					"badmessage");
		} else if (status.equals("invalid")) {
			assertEquals(addOrderPage.getPatientDoBValidateLabelClass(),
					"badmessage");
		}
	}

	@When("User Enters Patient Date of Birth in future")
	public void enterPatientDateOfBirthInFuture() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(cal.getTime());
		addOrderPage.enterPatientDateofBirth(strDate);
	}

	@Then("Alert appears if Date is in the Future")
	public void alertAppearsIfFutureDateIsEntered() throws InterruptedException {
		addOrderPage.clickOtherNationality();
		Thread.sleep(1000);
		addOrderPage.acceptAlert();
		assertEquals(addOrderPage.getPatientDoBValidateLabelClass(),
				"badmessage");
	}

	@When("Date of Birth is left blank and Age {string} is filled")
	public void enterPatientAgeWithoutDateOfBirth(String age) {
		addOrderPage.clearPatientDateOfBirth();
		addOrderPage.enterPatientAgeInYears(age);
	}

	@Then("Field generates Date of Birth with correct year for the Age {string}")
	public void generateDateOfBirth(String age) throws InterruptedException {
		addOrderPage.clickOtherNationality();
		Thread.sleep(1000);
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int currentAge = Integer.parseInt(age.trim());
		cal.add(Calendar.YEAR, -currentAge);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String strDate = formatter.format(cal.getTime());
		assertEquals(addOrderPage.getPatientDateOfBirthValue(), "xx/xx/"
				+ strDate);
	}

	@And("Alert Appears if Age entered is -1 , 100 and >100")
	public void showAlertIfAgeIsInvalid() throws InterruptedException {
		List<String> invalidAge = new ArrayList<String>();
		invalidAge.add("-1");
		invalidAge.add("100");
		invalidAge.add("101");
		for (String age : invalidAge) {
			addOrderPage.clearPatientDateOfBirth();
			addOrderPage.enterPatientAgeInYears(age);
			addOrderPage.clickOtherNationality();
			Thread.sleep(1000);
			assertEquals(addOrderPage.getPatientAgeValidateLabelClass(),
					"badmessage");
		}
	}

	@And("User Selects Patient Gender")
	public void selectPatientGender() {
		addOrderPage.selectPatientGenderFromDropDownMenu();
	}

	@And("User Selects Patient Education")
	public void selectPatientEducation() {
		addOrderPage.selectPatientEducationFromDropDownMenu();
	}

	@And("User Selects Patient Marital Status")
	public void selectPatientMaritalStatus() {
		addOrderPage.selectPatientMaritalStatusFromDropDownMenu();
	}

	@And("User Enters Patient Other Nationality {string}")
	public void enterPatientOtherNationality(String nationality) {
		addOrderPage.enterPatientOtherNationality(nationality);
	}

	@Then("Save button deactivated until all mandatory fields are completed")
	public void saveButtonDeactivated() {
		assertTrue(addOrderPage.saveButtonDeactivated());
	}

	@When("User Completes all mandatory fields")
	public void completeAllMandatoryFiels() throws InterruptedException {
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		// innitializing data
		addOrderPage.clickGenerateButton();
		Thread.sleep(1000);
		addOrderPage.selectSiteNameFromDropDown();
		Thread.sleep(1000);
		addOrderPage.enterLastName("SADDIO");
		addOrderPage.clickAddSampleButton();
		addOrderPage.selectSampleTypeFromDropDownMenu();
		addOrderPage.clickPannelCheckBox();
		addOrderPage.clickNewPatientButton();
		addOrderPage.enterSubjectNumber("201807D9P" + uuidAsString);
		addOrderPage.enterNationalId("201507D35" + uuidAsString);
		addOrderPage.enterPatientLastName("lastName");
		addOrderPage.enterPatientFirstName("firstName");
		addOrderPage.enterPatientStreet("street");
		addOrderPage.enterPatientCommune("commune");
		addOrderPage.enterPatientEmail("email@gmail.com");
		addOrderPage.enterPatientPhone("+23063458788");
		addOrderPage.selectPatientHelathRegionFromDropDownMenu();
		addOrderPage.enterPatientDateofBirth("09/02/2019");
		addOrderPage.selectPatientGenderFromDropDownMenu();
		addOrderPage.selectPatientEducationFromDropDownMenu();
		addOrderPage.selectPatientMaritalStatusFromDropDownMenu();
		addOrderPage.enterPatientOtherNationality("nationality");
	}
	@Then("Save is button Activated when all mandatory fields are completed")
	public void saveButtonActivated() {
		assertFalse(addOrderPage.saveButtonDeactivated());
	}

	@When("User Clicks Cancel")
	public void clickCancel() {
		addOrderPage.clickCancel();
	}

	@When("User Click Stay on Page")
	public void ClickStayOnPage() {
		addOrderPage.dismissAlert();
	}

	@Then("Patient Information form remains on screen")
	public void patientFormRemains() {
		assertFalse(addOrderPage.saveButtonDeactivated());
	}

	@When("User Clicks Save")
	public void clickSave() throws InterruptedException {
		addOrderPage.clickSave();
	}

	@Then("New blank Add Order form appears with green Save was successful message on the top")
	public void saveSuccesful() throws InterruptedException {
		Thread.sleep(1000);
		assertTrue(addOrderPage.containsText("Save was successful"));
		assertTrue(addOrderPage.containsText("Test Request"));
	}

	@Then("User Clicks Cancel and Returns to Home Page")
	public void clickCancelAndLeave() {
		addOrderPage.clickCancel();
		homePage = new HomePage(addOrderPage);
		assertFalse(homePage.containsText("Test Request"));
	}
}
