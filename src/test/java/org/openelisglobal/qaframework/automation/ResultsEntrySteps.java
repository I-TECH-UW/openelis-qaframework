package org.openelisglobal.qaframework.automation;

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

import static org.junit.Assert.assertTrue;

import org.openelisglobal.qaframework.RunTest;

public class ResultsEntrySteps extends TestBase {

	private ResultsUnitTypePage resultsUnitTypePage;

	private LoginPage loginPage;

	private HomePage homePage;

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
		homePage = resutlsEntryPage.goToHomePage();
		searchByPatientPage = homePage.goToSearchResultsByPatient();
	}

	@Then("Results search form displays")
	public void resultsFormDisplays() {
		assertTrue(searchByPatientPage.containsText("Results"));
		assertTrue(searchByPatientPage.hasSearchForm());
	}

	@When("User Selects Results --> Search --> By Order")
	public void goToSelectResultByOrder() {
		homePage = searchByPatientPage.goToHomePage();
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
		homePage = searchByOrderPage.goToHomePage();
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

}
