package org.openelisglobal.qaframework.automation;

import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.page.ResultValidationByAccesionNumberPage;
import org.openelisglobal.qaframework.automation.page.ResultValidationPage;
import org.openelisglobal.qaframework.automation.test.TestBase;

import static org.junit.Assert.assertTrue;

import org.openelisglobal.qaframework.RunTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ValidationSteps extends TestBase {

	private LoginPage loginPage;

	private HomePage homePage;

	private ResultValidationPage resultValidationPage;

	private ResultValidationByAccesionNumberPage resultValidationByAccesionPage;

	@After(RunTest.HOOK.VALIDATE)
	public void destroy() {
		quit();
	}

	@Before(RunTest.HOOK.VALIDATE)
	public void setLoginPage() {
		System.out.println("....Validation......");
		loginPage = new LoginPage(getWebDriver());
	}

	@Given("User goes to Home page")
	public void visitLoginPage() throws Exception {
		homePage = loginPage.goToHomePage();
	}

	@When("User Selects Validation --> Routine,from the main menu")
	public void goToValiationPage() {
		resultValidationPage = homePage.goToResultValidation();
	}

	@Then("The Validation by Unit Type page displays")
	public void validationPageLoads() {
		assertTrue(resultValidationPage.containsText("Unit Type"));
	}

	@When("User Selects a Unit Type {string} under which there are known tests")
	public void goToValidationPage(String unitType) throws InterruptedException {
		resultValidationPage.selectUnitType(unitType);
		Thread.sleep(1000);
	}

	@Then("Tests display with Lab order Number, Test name, result and result reference")
	public void resultAppears() {
		assertTrue(resultValidationPage.containsText("Accession Number"));
		assertTrue(resultValidationPage.containsText("Test Name"));
		assertTrue(resultValidationPage.containsText("Result"));
	}

	@When("User Enters lab number {string} in Lab Number search field at top right")
	public void enterLabNumberSearch(String labNumber) {
		resultValidationPage.enterLabNumberSearch(labNumber);;
	}
	
	@Then("Field Only accepts 9 characters")
	public void acceptNineCharacter(){

	}

	@When("User Clicks Search Button")
	public void clickSearch() {
		resultValidationByAccesionPage = resultValidationPage.clickSearch();;
	}

	@Then("If order number exists {string} ,Page goes to order number, order is highlighted in yellow")
	public void goToOrderNumber(String exists) {
		if (exists.equals("true")) {
			assertTrue(resultValidationByAccesionPage.containsText("Accession Number"));
			assertTrue(resultValidationByAccesionPage.containsText("Test Name"));
			assertTrue(resultValidationByAccesionPage.containsText("Result"));
		}
	}

	@Then("If order number does not exist {string} , message `Accession number not found` appears")
	public void accesionNotFound(String exists) {
		if (exists.equals("false")) {
			assertTrue(resultValidationByAccesionPage.containsText("Accession number not found"));
			resultValidationByAccesionPage.goToHomePage();
		}
	}

	@Then("User Check for known non-conformity, Red flag displayed next to test")
	public void redFlagDisplays(){
		resultValidationPage.containsText("Sample or order is nonconforming");
	}

	@And("Non-conformity Reason note displays with Date and Time stamp")
	public void nonConformityNoteDisplays(){
		assertTrue(resultValidationPage.containsText("Prior Notes:"));
	}
}
