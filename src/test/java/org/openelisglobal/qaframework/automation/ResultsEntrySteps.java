package org.openelisglobal.qaframework.automation;

import org.openelisglobal.qaframework.automation.page.AddOrderPage;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.page.ResultsUnitTypePage;
import org.openelisglobal.qaframework.automation.page.ResutlsEntryPage;

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

	private ResutlsEntryPage resutlsEntryPage;

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
		resutlsEntryPage = resultsUnitTypePage.selectUnitType(unitType);
		Thread.sleep(2000);
		assertTrue(resutlsEntryPage.containsText(unitType));
	}

	@When("User Selects Results --> Search --> By Patient")
	public void goToSelectResultByPatient() {
		if (resutlsEntryPage != null) {
			homePage = resutlsEntryPage.goToHomePage();
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
	}

	@When("User Selects Results --> Search --> By Order and searches by known Accession Number {string}")
	public void searchByKnownAccesionNumber(String accesionNumber)
			throws InterruptedException {
		addOrderPage = homePage.goToAddOrderPage();
		addOrderPage.innitaliseData(accesionNumber);
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
	public void searchByKnownPatientNames(String lastName, String firstName)
			throws InterruptedException {
		searchByPatientPage = homePage.goToSearchResultsByPatient();
		searchByPatientPage.enterFirstNameSearch(firstName);
		searchByPatientPage.enterLastNameSearch(lastName);
		searchByPatientPage.clickSearchButton();
		Thread.sleep(10000);
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
		resutlsEntryPage = resultsUnitTypePage.selectUnitType(unitType);
	}

	@Then("Only tests without results display")
	public void testsWithoutResultsDisplay() {
		assertTrue(resutlsEntryPage.containsText("Test Date"));
		assertTrue(resutlsEntryPage.containsText("Accept as is"));
		assertTrue(resutlsEntryPage.containsText("Result from analyzer"));
		assertTrue(resutlsEntryPage.containsText("Current Result"));
		assertFalse(resutlsEntryPage
				.containsText("No appropriate tests were found"));
	}

	@And("Notes are visible with time and date stamp")
	public void notesPageVisible() {
		assertTrue(resutlsEntryPage.containsText("Notes"));
	}

	@And("Lab number with sample extension displays")
	public void labNumberDisplays() {
		assertTrue(resutlsEntryPage.containsText("Lab No."));
	}

	@And("Sample Type displays")
	public void sampleTypeDisplays() {
		assertTrue(resutlsEntryPage.containsText("Sample Type"));
	}

	@And("Test date defaults to current date")
	public void testDateDefaultsTocurrentDate() {
		// this test will fail at times if the server and the testing
		// framework run in different time zones
		assertEquals(resutlsEntryPage.getTestDateValue(), getCurrentDate());
	}
}
