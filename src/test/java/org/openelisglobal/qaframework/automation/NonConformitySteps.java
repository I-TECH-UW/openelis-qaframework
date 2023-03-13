package org.openelisglobal.qaframework.automation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.AddOrderPage;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.page.NonConformityPage;
import org.openelisglobal.qaframework.automation.test.TestBase;

public class NonConformitySteps extends TestBase {

	private static final String ACCESSION_NUMBER = "20210000000003761";

	private LoginPage loginPage;

	private HomePage homePage;

	private NonConformityPage nonConformityPage;

	private AddOrderPage addOrderPage;

	@Before(RunTest.HOOK.NON_CONFORMITY)
	public void setLoginPage() {
		System.out.println("....Non Conformity......");
		loginPage = new LoginPage(getWebDriver());
	}

	@After(RunTest.HOOK.NON_CONFORMITY)
	public void destroy() {
		quit();
	}

	@Given("User Logins in to Home Page")
	public void userLoginsInToHomePage() throws InterruptedException {
		homePage = loginPage.goToHomePage();
		Thread.sleep(100);
	}

	@Given("User Selects Non Conformity report from main menu")
	public void userSelectsNonConformityReportFromMainMenu() throws InterruptedException {
		nonConformityPage = homePage.goToNonConformityReport();
		assertTrue(nonConformityPage.containsText("Report Non-Conforming Event (NCE)"));
		assertTrue(nonConformityPage.searchSelectExists());
		assertTrue(nonConformityPage.searchFieldExists());
		assertTrue(nonConformityPage.searchButtonExists());
		assertEquals(nonConformityPage.searchButtonDisabledAttribute(), "true");
		Thread.sleep(1000);
	}

	@When("User Selects search by --> Lab No dropdown option")
	public void userSelectsSearchByLabNoDropdownOption() throws InterruptedException {
		homePage = nonConformityPage.goToHomePage();
		/** initialise order data **/
		addOrderPage = homePage.goToAddOrderPage();
		addOrderPage.innitialiseData(ACCESSION_NUMBER);
		homePage = addOrderPage.goToHomePage();
		if (homePage.alertPresent()) {
			homePage.acceptAlert();
		}

		nonConformityPage = homePage.goToNonConformityReport();
		nonConformityPage.selectCriteriaSelect("5. Lab No");
	}

	@Then("User enters accession number {string}")
	public void userEntersAccessionNumber(String labNo) throws InterruptedException {
		nonConformityPage.enterSearchText(labNo);
		assertNull(nonConformityPage.searchButtonDisabledAttribute());
		nonConformityPage.clickSearchButton();
		Thread.sleep(1000);
	}

	@And("User checks the affected specimen")
	public void userChecksTheAffectedSpecimen() {
		nonConformityPage.checkAffectedSpecimen();
	}

	@Then("User clicks go to NCE reporting form button")
	public void userClicksGoToNCEReportingFormButton() throws InterruptedException {
		nonConformityPage.clickNCEButton();

	}

	@And("Report date default is set to current date")
	public void reportDateDefaultIsSetToCurrentDate() {
	}

	@And("Report date can be modified")
	public void reportDateCanBeModified() {
	}
}
