package org.openelisglobal.qaframework.automation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.page.PatientStatusReportPage;
import org.openelisglobal.qaframework.automation.page.ResultValidationByOderPage;
import org.openelisglobal.qaframework.automation.page.ResultValidationPage;
import org.openelisglobal.qaframework.automation.page.WorkPlanByPanelTypePage;
import org.openelisglobal.qaframework.automation.page.WorkPlanByTestTypePage;
import org.openelisglobal.qaframework.automation.page.WorkPlanByUnitTypePage;
import org.openelisglobal.qaframework.automation.test.TestBase;

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
	
	private ResultValidationByOderPage resultValidationByOrderPage;
	
	private PatientStatusReportPage patientStatusReportPage;
	
	private WorkPlanByTestTypePage workPlanByTestTypePage;
	
	private WorkPlanByPanelTypePage workPlanByPanelTypePage;
	
	private WorkPlanByUnitTypePage workPlanByUnitTypePage;
	
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
		resultValidationPage.enterLabNumberSearch(labNumber);
	}
	
	@Then("Field Only accepts 9 characters")
	public void acceptNineCharacter() {
		
	}
	
	@When("User Clicks Search Button")
	public void clickSearch() {
		resultValidationByOrderPage = resultValidationPage.clickSearch();
	}
	
	@Then("If order number exists {string} ,Page goes to order number, order is highlighted in yellow")
	public void goToOrderNumber(String exists) {
		if (exists.equals("true")) {
			assertTrue(resultValidationByOrderPage.containsText("Accession Number"));
			assertTrue(resultValidationByOrderPage.containsText("Test Name"));
			assertTrue(resultValidationByOrderPage.containsText("Result"));
		}
	}
	
	@Then("If order number does not exist {string} , message `Accession number not found` appears")
	public void accesionNotFound(String exists) {
		if (exists.equals("false")) {
			assertTrue(resultValidationByOrderPage.containsText("Accession number not found"));
			resultValidationByOrderPage.goToHomePage();
		}
	}
	
	@Then("User Check for known non-conformity, Red flag displayed next to test")
	public void redFlagDisplays() {
		resultValidationPage.containsText("Sample or order is nonconforming");
	}
	
	@And("Non-conformity Reason note displays with Date and Time stamp")
	public void nonConformityNoteDisplays() {
		//assertTrue(resultValidationPage.containsText("Prior Notes:"));
	}
	
	@When("User Checks `Save all results`")
	public void checkeSaveAll() {
		resultValidationPage.checkSaveAll();
	}
	
	@Then("All results are checked `Save`")
	public void resultsSaveChecked() {
		assertTrue(resultValidationPage.allResultsCheckedSave());
	}
	
	@When("User Unchecks `Save all results`")
	public void unCheckeSaveAll() {
		resultValidationPage.checkSaveAll();
	}
	
	@Then("All results are de-checked `Save`")
	public void resultsSaveDeChecked() {
		assertFalse(resultValidationPage.allResultsCheckedSave());
	}
	
	@When("User Checks `Save all results` again")
	public void checkSaveAllAgain() {
		resultValidationPage.checkSaveAll();
	}
	
	@And("User Unchecks `Save` For selected tests")
	public void uncheckSaveForSelectedResult() {
		resultValidationPage.checkAcceptedCheckBox();
	}
	
	@Then("Seleted Save Check boxes are clear")
	public void selectedSaveUnchecked() {
		assertFalse(resultValidationPage.resultSaveChecked());
	}
	
	@When("User Checks `Retest all results`")
	public void checkRetestAll() {
		resultValidationPage.checkRetestAll();
	}
	
	@Then("All results are checked `Retest`")
	public void resultsRejectChecked() {
		assertTrue(resultValidationPage.allResultsCheckedRetest());
	}
	
	@When("User Unchecks `Retest all results`")
	public void uncheckRetestAll() {
		resultValidationPage.checkRetestAll();
	}
	
	@Then("All results are de-checked `Retest`")
	public void resultsRetestDeChecked() {
		assertFalse(resultValidationPage.allResultsCheckedRetest());
	}
	
	@When("User Checks `Retest all results` again")
	public void checkRetesAllAgain() {
		resultValidationPage.checkRetestAll();
	}
	
	@And("User Unchecks `Retest` for selected tests")
	public void uncheckRetestForSelectedResult() {
		resultValidationPage.checkRetestCheckBox();
	}
	
	@Then("Seleted Retest Check boxes are clear")
	public void selectedRetestUnchecked() {
		assertFalse(resultValidationPage.resultRetestChecked());
	}
	
	@And("Tests cannot be checked both `Save` and `Retest` at the same time")
	public void testCantBeMarkedWithBothSaveAndRetest() {
		resultValidationPage.checkAcceptedCheckBox();
		resultValidationPage.checkRetestCheckBox();
		resultValidationPage.clickSave();
		resultValidationPage.acceptAlert();
		assertTrue(resultValidationPage.containsText("A system error has occurred."));
		resultValidationPage.goToHomePage();
	}
	
	@When("User Enters Validation notes {string}")
	public void EnterNotes(String notes) {
		resultValidationPage.clickShowHide();
		UUID uuid = UUID.randomUUID();
		resultValidationPage.enterNotes(notes + uuid);
	}
	
	@Then("Field accepts validation text {string}")
	public void acceptNotes(String notes) {
		assertTrue(resultValidationPage.getNotes().contains(notes));
	}
	
	@When("User Closes Validation note box")
	public void closeNoteBox() {
		resultValidationPage.clickShowHide();
	}
	
	@Then("Validation Note field closes; triangle symbol changes to notepad symbol")
	public void triangleSymbolChangesToNotePadSymbol() {
		assertTrue(resultValidationPage.hasEditIcon());
	}
	
	@And("User Clicks Cancel button on Validation Page")
	public void clickCancel() throws InterruptedException {
		resultValidationPage.checkAcceptedCheckBox();
		Thread.sleep(1000);
		resultValidationPage.clickCancel();
	}
	
	@Then("Triggers prompt box ,to confirm leaving page")
	public void displayPrompt() throws InterruptedException {
		assertTrue(resultValidationPage.promptPresent());
	}
	
	@When("User Clicks `Cancel` to Stay on Page")
	public void clickStayOnPage() {
		resultValidationPage.dismissAlert();
	}
	
	@Then("Stays on page")
	public void stayOnPage() {
		assertTrue(resultValidationPage.containsText("Accession Number"));
		assertTrue(resultValidationPage.containsText("Test Name"));
		assertTrue(resultValidationPage.containsText("Result"));
	}
	
	@When("User Click Save button")
	public void ClickSave() {
		resultValidationPage.clickSave();
	}
	
	@Then("Pop-up message asks you to confirm that you have indicated action for all items you wish to validate")
	public void displayConfirmationPrompt() throws InterruptedException {
		assertTrue(resultValidationPage.promptPresent());
	}
	
	@When("User Clicks Ok")
	public void clickOk() {
		resultValidationPage.acceptAlert();
	}
	
	@Then("Returns to Validation Unit Type search page and message in green `Save was successful` appears")
	public void saveSuccesful() {
		assertTrue(resultValidationPage.containsText("Save was successful"));
		assertTrue(resultValidationPage.containsText("Unit Type"));
	}
	
	@And("User Enters a validation for another result")
	public void enterValidationForResult() {
		resultValidationPage.checkAcceptedCheckBox();
	}
	
	@When("User Clicks `Leave` in cancel message")
	public void ClickLeave() {
		resultValidationPage.acceptAlert();
	}
	
	@Then("Returned to home page")
	public void returnedToHomePage() {
		assertFalse(resultValidationPage.containsText("Save was successful"));
		assertFalse(resultValidationPage.containsText("Unit Type"));
	}
	
	@When("User Goes to Patient Status Report Page")
	public void goToPatientStatusPage() {
		patientStatusReportPage = homePage.goToPatientStatusReportPage();
	}
	
	@And("User Generates Patient Status Report for the accession number {string}")
	public void enterLabNumber(String labNumber) throws InterruptedException {
		patientStatusReportPage.enterLabNumber(labNumber);
		patientStatusReportPage.clickSearchButton();
		patientStatusReportPage.clickPrintButton();
	}
	
	@Then("Saved results without rejection reason appear on Patient Status Report")
	public void resultAppear() {
		assertTrue(patientStatusReportPage.containsText("Data source"));
		assertTrue(patientStatusReportPage.containsText("Last Name"));
		assertTrue(patientStatusReportPage.containsText("First Name"));
		assertTrue(patientStatusReportPage.containsText("Gender"));
		assertTrue(patientStatusReportPage.containsText("Date of Birth"));
		assertTrue(patientStatusReportPage.containsText("Subject Number"));
		assertTrue(patientStatusReportPage.containsText("National ID"));
		homePage = patientStatusReportPage.goToHomePage();
	}
	
	@When("User Goes to Workplan --> By Test Type")
	public void goToWorkPlanByTestPage() {
		workPlanByTestTypePage = homePage.goToWorkPlanByTestPage();
	}
	
	@Then("Retest tests appear on workplan for that accession number By Test Type {string}")
	public void testAppearOnWorkPlanByTestPage(String testType) {
		assertTrue(workPlanByTestTypePage.containsText("Workplan By Test"));
		assertTrue(workPlanByTestTypePage.containsText("Test Type"));
		workPlanByTestTypePage.selctTestType(testType);
		assertTrue(workPlanByTestTypePage.containsText(testType));
		assertTrue(workPlanByTestTypePage.containsText("Lab No"));
		assertTrue(workPlanByTestTypePage.containsText("Subject Number"));
		homePage = workPlanByTestTypePage.goToHomePage();
	}
	
	@When("User Goes to Workplan --> By Panel Type")
	public void goToWorkPlanByPanelPage() {
		workPlanByPanelTypePage = homePage.goToWorkPlanByPanelPage();
	}
	
	@Then("Retest tests appear on workplan for that accession number By Panel Type {string}")
	public void testAppearOnWorkPlanByPanelPage(String panelType) {
		assertTrue(workPlanByPanelTypePage.containsText("Workplan By Panel"));
		assertTrue(workPlanByPanelTypePage.containsText("Panel Type"));
		workPlanByPanelTypePage.selectPanelType(panelType);
		assertTrue(workPlanByPanelTypePage.containsText(panelType));
		assertTrue(workPlanByPanelTypePage.containsText("Lab No"));
		assertTrue(workPlanByPanelTypePage.containsText("Subject Number"));
		assertTrue(workPlanByPanelTypePage.containsText("Test Name"));
		homePage = workPlanByPanelTypePage.goToHomePage();
	}
	
	@When("User Goes to Workplan --> By Unit")
	public void goToWorkPlanByUnitPage() {
		workPlanByUnitTypePage = homePage.goToWorkPlanByUnitPage();
	}
	
	@Then("Retest tests appear on workplan for that accession number By Unit Type {string}")
	public void testAppearOnWorkPlanByUnitPage(String unitType) {
		assertTrue(workPlanByUnitTypePage.containsText("Workplan Unit Type"));
		assertTrue(workPlanByUnitTypePage.containsText("Unit Type"));
		workPlanByUnitTypePage.selectUnitType(unitType);
		assertTrue(workPlanByUnitTypePage.containsText("Lab No"));
		assertTrue(workPlanByUnitTypePage.containsText("Subject Number"));
		assertTrue(workPlanByUnitTypePage.containsText("Test Name"));
		homePage = workPlanByUnitTypePage.goToHomePage();
	}
}
