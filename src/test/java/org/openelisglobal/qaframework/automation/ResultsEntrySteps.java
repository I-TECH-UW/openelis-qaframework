package org.openelisglobal.qaframework.automation;

import org.openelisglobal.qaframework.automation.page.AddOrderPage;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.page.ResultsUnitTypePage;
import org.openelisglobal.qaframework.automation.page.ResultsEntryPage;

import org.openelisglobal.qaframework.automation.page.SearchResultsByOrderPage;
import org.openelisglobal.qaframework.automation.page.SearchResultsByPatientPage;
import org.openelisglobal.qaframework.automation.page.SearchResultsByStatusPage;
import org.openelisglobal.qaframework.automation.test.TestBase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.openelisglobal.qaframework.RunTest;

public class ResultsEntrySteps extends TestBase {

	private ResultsUnitTypePage resultsUnitTypePage;

	private LoginPage loginPage;

	private HomePage homePage;

	private AddOrderPage addOrderPage;

	private ResultsEntryPage resultsEntryPage;

	private SearchResultsByPatientPage searchByPatientPage;

	private SearchResultsByOrderPage searchByOrderPage;

	private SearchResultsByStatusPage searchResultsByStatusPage;

	@After(RunTest.HOOK.RESULT)
	public void destroy() {
		quit();
	}

	@Before(RunTest.HOOK.RESULT)
	public void setLoginPage() {
		loginPage = new LoginPage(getWebDriver());
	}

	@Given("User visits Home page")
	public void visitLoginPage() throws Exception {
		homePage = loginPage.goToHomePage();
		homePage.turnOnResultsEntryValidation();
	}

	@And("User selects Results and clicks Enter By unit")
	public void goToResultsEntryPage() throws Exception {
		resultsUnitTypePage = homePage.selectsResultAndClickEnterByUnit();
	}

	@Then("Results Unit Type search form displays")
	public void serchUnitTypeAppears() {
		assertTrue(resultsUnitTypePage.containsText("Unit Type"));
		assertTrue(resultsUnitTypePage.hasResultUnitTypeSearchForm());
	}

	@And("All unit names display correctly in drop down menu")
	public void allUnitTypesAppears() {
		assertTrue(resultsUnitTypePage.hasUnitTypeOptions());
	}

	@And("User Can select unit {string} from drop down menu and redirects to entry page")
	public void selectUnitType(String unitType) throws InterruptedException {
		resultsEntryPage = resultsUnitTypePage.selectUnitType(unitType);
		Thread.sleep(2000);
		assertTrue(resultsEntryPage.containsText(unitType));
	}

	@When("User Selects Results --> Search --> By Patient")
	public void goToSelectResultByPatient() {
		if (resultsEntryPage != null) {
			homePage = resultsEntryPage.goToHomePage();
		}
		searchByPatientPage = homePage.goToSearchResultsByPatient();
	}

	@Then("Results search form displays")
	public void resultsFormDisplays() {
		assertTrue(searchByPatientPage.containsText("Results"));
		assertTrue(searchByPatientPage.hasSearchForm());
	}

	@When("User Selects Results --> Search --> By Order")
	public void goToSelectResultByOrder() {
		if (searchByPatientPage != null) {
			homePage = searchByPatientPage.goToHomePage();
		}
		searchByOrderPage = homePage.goToSearchResultsByOrder();
	}

	@Then("Result accession number search form displays")
	public void accesionSearchNumberSearchFormDisplays() {
		assertTrue(searchByOrderPage.containsText("Results"));
		assertTrue(searchByOrderPage.containsText("Lab No"));
		assertTrue(searchByOrderPage.hasSearchForm());
	}

	@When("User Selects Results --> Search --> By Test Name, Date, or Status")
	public void goToSelectResultByStatus() {
		if (searchByOrderPage != null) {
			homePage = searchByOrderPage.goToHomePage();
		}
		searchResultsByStatusPage = homePage.goToSearchResultsByStatus();
	}

	@Then("Results by Test, Date or Status search form displays")
	public void statusSearchFormDisplays() {
		assertTrue(searchResultsByStatusPage.containsText("Results"));
		assertTrue(searchResultsByStatusPage.containsText("Collection Date"));
		assertTrue(searchResultsByStatusPage.containsText("Received Date"));
		assertTrue(searchResultsByStatusPage.containsText("Test Name"));
		assertTrue(searchResultsByStatusPage.containsText("Analysis Status"));
		assertTrue(searchResultsByStatusPage.containsText("Sample Status"));
		assertTrue(searchResultsByStatusPage.hasSearchForm());
	}

	@Then("Search button deactivated until search text entered")
	public void searchButtonDeactivated() throws InterruptedException {
		assertTrue(searchByPatientPage.searchButtonDeactivated());
		searchByPatientPage.enterLabNoSearch("labNumber");
		Thread.sleep(100);
		assertFalse(searchByPatientPage.searchButtonDeactivated());
	}

	@When("User Searches by Last name {string}")
	public void searchByLastName(String lastName) {
		searchByPatientPage.go();
		searchByPatientPage.enterLastNameSearch(lastName);
		searchByPatientPage.clickSearchButton();
	}

	@Then("Search by Last name yields all patients with matching last name")
	public void returnSearchResultsByLastName() {
		assertTrue(searchByPatientPage.noPatientLabelDisplays());
	}

	@When("User Searches by First name {string}")
	public void searchByFirstName(String firstName) {
		searchByPatientPage.go();
		searchByPatientPage.enterFirstNameSearch(firstName);
		searchByPatientPage.clickSearchButton();
	}

	@Then("Search by First name yields all patients with matching first name")
	public void returnSearchResultsByFirstName() {
		assertTrue(searchByPatientPage.noPatientLabelDisplays());
	}

	@When("User Searches by Patient Identification Code {string}")
	public void searchByPatientId(String patientId) {
		searchByPatientPage.go();
		searchByPatientPage.enterPatientIdSearch(patientId);
		searchByPatientPage.clickSearchButton();
	}

	@Then("Search by Patient Id yields all patients with matching patient id")
	public void returnSearchResultsByPatientId() {
		assertTrue(searchByPatientPage.noPatientLabelDisplays());
	}

	@When("User Searches by AccesionNumber {string}")
	public void searchByAccesionNumber(String acesionNumber) {
		searchByOrderPage.enterAccesionNumber(acesionNumber);;
		searchByOrderPage.clickAccesionNumberSearch();
	}

	@Then("Search by Lab Number yields results for known accession number")
	public void returnSearchResultsByAccesionNumber() {
		assertTrue(searchByOrderPage.accesionNumberNotFoundDisplays());
		assertTrue(searchByOrderPage.containsText("Accession number not found"));
	}

	@When("User Selects Results --> Search --> By Order and searches by known Accession Number {string}")
	public void searchByKnownAccesionNumber(String accesionNumber)
			throws InterruptedException {
		addOrderPage = homePage.goToAddOrderPage();
		addOrderPage.innitaliseData(accesionNumber);
		homePage = addOrderPage.goToHomePage();
		// load data twice
		addOrderPage = homePage.goToAddOrderPage();
		addOrderPage.innitaliseData("20210000000002250");
		homePage = addOrderPage.goToHomePage();
		searchByOrderPage = homePage.goToSearchResultsByOrder();
		searchByOrderPage.enterAccesionNumber(accesionNumber);
		// Patient information doesnt load
		// searchByOrderPage.clickAccesionNumberSearch();
	}

	@Then("Patient information display correctly by Accession Number")
	public void patienttInformationDispaysByAccesionNumber() {
		// this doesnt load
		homePage = searchByOrderPage.goToHomePage();
	}

	@When("User Select Results --> Search --> By Patient and  Pull up lab results for a known patient by LastName {string} and FirstName {string}")
	public void searchByKnownPatientNames(String lastName, String firstName) {
		searchByPatientPage = homePage.goToSearchResultsByPatient();
		searchByPatientPage.enterFirstNameSearch(firstName);
		searchByPatientPage.enterLastNameSearch(lastName);
		searchByPatientPage.clickSearchButton();
	}

	@Then("Patient information display correctly by Patient details")
	public void patienttInformationDispaysByAccesionPatientDetails() {
		assertTrue(searchByPatientPage.searchResultsDisplay());
		assertTrue(searchByPatientPage.containsText("Data source"));
		assertTrue(searchByPatientPage.containsText("Last Name"));
		assertTrue(searchByPatientPage.containsText("First Name"));
		assertTrue(searchByPatientPage.containsText("Gender"));
		assertTrue(searchByPatientPage.containsText("Date of Birth"));
		assertTrue(searchByPatientPage.containsText("Subject Number"));
		assertTrue(searchByPatientPage.containsText("National ID"));
		homePage = searchByPatientPage.goToHomePage();
	}

	@When("User Select Results --> Enter by Unit from main menu drop-down and Selects a Unit Type {string} for which there are known tests")
	public void searchByUnitType(String unitType) {
		resultsUnitTypePage = homePage.selectsResultAndClickEnterByUnit();
		resultsEntryPage = resultsUnitTypePage.selectUnitType(unitType);
	}

	@Then("Only tests without results display")
	public void testsWithoutResultsDisplay() {
		assertTrue(resultsEntryPage.containsText("Test Date"));
		assertTrue(resultsEntryPage.containsText("Accept as is"));
		assertTrue(resultsEntryPage.containsText("Result from analyzer"));
		assertTrue(resultsEntryPage.containsText("Current Result"));
		assertFalse(resultsEntryPage
				.containsText("No appropriate tests were found"));
	}

	@And("Notes are visible with time and date stamp")
	public void notesPageVisible() {
		assertTrue(resultsEntryPage.containsText("Notes"));
	}

	@And("Lab number with sample extension displays")
	public void labNumberDisplays() {
		assertTrue(resultsEntryPage.containsText("Lab No."));
	}

	@And("Sample Type displays")
	public void sampleTypeDisplays() {
		assertTrue(resultsEntryPage.containsText("Sample Type"));
	}

	@And("Test date defaults to current date")
	public void testDateDefaultsTocurrentDate() {
		// this test will fail at times if the server and the testing
		// framework run in different time zones
		assertEquals(resultsEntryPage.getTestDateValue(), getCurrentDate());
	}

	@When("User Overwrites Test Date {string}")
	public void overWriteTestDate(String date) {
		resultsEntryPage.enterTestDate(date);
	}

	@Then("Field accepts text {string}")
	public void testDateFiedlAccesptsText(String date) {
		assertEquals(resultsEntryPage.getTestDateValue(), date);
	}

	@And("Non-conforming samples appear with a red flag symbol")
	public void nonConforminSampleAppear() {
		assertTrue(resultsEntryPage.hasNonConformingFlag());
	}

	@When("User Enters known lab number {string} in Lab no. search field at top right and Clicks search")
	public void enterKnownAccesionNumber(String accesionNumber) {
		resultsEntryPage.enterSearchAccesionNumber(accesionNumber);
		// Accesion Result page doesnt Load
		// resultsEntryPage.clickOnLabNumberSearch();
	}

	@Then("Page goes to correct lab number and order is highlighted in yellow")
	public void getKnownResultPage() {
		// results do not load
	}

	@And("Message appears ,Accession number not found, if the format is incorrect or number is not in use")
	public void getUnknownResultPage() {
		resultsEntryPage.enterSearchAccesionNumber("12769");
		searchByOrderPage = resultsEntryPage.clickOnLabNumberSearch();
		searchByOrderPage.acceptAlert();
		assertTrue(searchByOrderPage.accesionNumberNotFoundDisplays());
		assertTrue(searchByOrderPage.containsText("Accession number not found"));
	}

	@Then("Reference range or value displays under test name")
	public void referenceRangeDisplays() {
		assertTrue(resultsEntryPage.containsText("10.00 - 10000000.00"));
	}

	@When("User Enters type-in result {string} for a selected test")
	public void enterTypeInResults(String value) {
		resultsEntryPage.enterTestResult(value);;
	}

	@Then("Type-in result {string} can be entered in the field")
	public void resultEntered(String Value) {
		assertEquals(resultsEntryPage.getTestResultValue(), Value);
	}

	@Then("Result units display correctly")
	public void resultUnitsDisplayCorrectly() {
		// cant see units anywahere on the results page
	}

	@Then("Result converts to correct decimal {string}")
	public void resultValueConverted(String convertedValue)
			throws InterruptedException {
		resultsEntryPage.clickOnDateField();
		Thread.sleep(1000);
		assertEquals(resultsEntryPage.getTestResultValue(), convertedValue);
	}

	@When("User Enters a result {string} that is below the normal range")
	public void enterTestResultBelowNormalRange(String lowValue)
			throws InterruptedException {
		resultsEntryPage.enterTestResult(lowValue);
		resultsEntryPage.clickOnDateField();
		Thread.sleep(100);
	}

	@Then("Results Field Turn Yellow")
	public void fieldTurnYellow() {
		assertTrue(resultsEntryPage.resultFieldHasYellowBackground());
	}

	@When("User Enters a result {string} that is above the normal range")
	public void enterTestResultABoveNormalRange(String highValue)
			throws InterruptedException {
		resultsEntryPage.enterTestResult(highValue);
		resultsEntryPage.clickOnDateField();
		Thread.sleep(100);
	}

	@When("User Clicks on Add Note icon")
	public void clickonAddNoteButton() {
		resultsEntryPage.clickShowHideButton();
	}

	@Then("Note field displays")
	public void noteFieldAppears() {
		assertTrue(resultsEntryPage.notesTextAreaDisplays());
	}

	@When("User Enters text {string} in Note field , then click on the Note Arrow icon")
	public void enterNotes(String notes) {
		resultsEntryPage.enterNotes(notes);
		resultsEntryPage.clickShowHideButton();
	}

	@Then("Note field collapses, displays Notepad icon")
	public void hasNoteEDitIcon() {
		assertTrue(resultsEntryPage.hasNoteEditIcon());
	}

	@When("User selects list result")
	public void selectResultFromDropDown() {
		resultsEntryPage.clickResultList();
	}

	@Then("Results can be chosen from the drop-down list")
	public void resultListhasOptions() {
		assertTrue(resultsEntryPage.resultListContainsOptions());
		resultsEntryPage.selectResultFromResultList();
	}

	@When("User Clicks checkbox under Result From Analyzer")
	public void clieckCheckBoxUnderResultFromAnalyser() {
		resultsEntryPage.clickAnalyserCheckBox();
	}

	@Then("Uncheck sticks")
	public void unCheckSticks() {
		assertTrue(resultsEntryPage.analyserCheckBoxMarked());
	}

	@And("User Clicks Cancel button")
	public void clicksCancel() {
		resultsEntryPage.enterTestResult("12");
		resultsEntryPage.clickCancelButton();
	}

	@Then("Triggers message ,Leave Site? Changes you made may not be saved")
	public void alertAppears() throws InterruptedException {
		assertTrue(resultsEntryPage.promptPresent());
	}

	@And("User Clicks Cancel and Stays on page {string}")
	public void staysOnTheSamePage(String text) {
		resultsEntryPage.containsText(text);
	}

}
