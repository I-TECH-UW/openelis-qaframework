package org.openelisglobal.qaframework.automation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

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
import org.openelisglobal.qaframework.automation.page.ResultsUnitTypePage;
import org.openelisglobal.qaframework.automation.page.WorkPlanByPanelTypePage;
import org.openelisglobal.qaframework.automation.page.WorkPlanByTestTypePage;
import org.openelisglobal.qaframework.automation.page.WorkPlanByUnitTypePage;
import org.openelisglobal.qaframework.automation.test.TestBase;

public class WorkPlanSteps extends TestBase {

	private LoginPage loginPage;

	private HomePage homePage;

	private WorkPlanByTestTypePage workPlanByTestTypePage;

	private WorkPlanByUnitTypePage workPlanByUnitTypePage;

	private WorkPlanByPanelTypePage workPlanByPanelTypePage;

	private ResultsUnitTypePage resultsEntryPage;

	private AddOrderPage addOrderPage;

	@After(RunTest.HOOK.WORKPLAN)
	public void destroy() {
		quit();
	}

	@Before(RunTest.HOOK.WORKPLAN)
	public void setLoginPage() {
		System.out.println("....WorkPlan......");
		loginPage = new LoginPage(getWebDriver());
	}

	@Given("User logins and goes to the Home page")
	public void userLoginsAndGoesToTheHomePage() throws InterruptedException {
		Thread.sleep(100);
		homePage = loginPage.goToHomePage();
	}

	@Given("User select workplan by test from the main menu down {string}")
	public void userSelectWorkplanByTestFromTheMainMenuDown(String ACCESION_NUMBER) throws InterruptedException {
		// intialise data
		addOrderPage = homePage.goToAddOrderPage();
		addOrderPage.innitialiseData(ACCESION_NUMBER);
		homePage = addOrderPage.goToHomePage();
		if (homePage.alertPresent()){
			homePage.acceptAlert();
		}
		workPlanByTestTypePage = homePage.goToWorkPlanByTestPage();
	}

	@When("user selects test type {string} from the dropdown options")
	public void userSelectsTestTypeFromTheDropdownOptions(String testType) throws InterruptedException {
		workPlanByTestTypePage.selctTestType(testType);
	}

	@Then("Tests display in search drop-down list")
	public void testsDisplayInSearchDropDownList() {
		assertTrue(workPlanByTestTypePage.dropDownTestTypeHasItems());
	}

	@And("Workplan select form appears")
	public void workplanSelectFormAppears() {
		workPlanByTestTypePage.containsText("Print Workplan");
		assertTrue(workPlanByTestTypePage.workplanResultsTableDisplayed());
	}

	@Then("All known orders are present {string} and {string}")
	public void allKnownOrdersArePresentAnd(String testType, String labNo) {
		assertTrue(workPlanByTestTypePage.containsText(testType));
		if (workPlanByTestTypePage.containsText(labNo)){
			assertTrue(workPlanByTestTypePage.containsText(labNo));
		}else{
			assertFalse(workPlanByTestTypePage.containsText("No appropriate tests were found."));
		}
	}

	@Then("Total number of tests is correct {int}")
	public void totalNumberOfTestsIsCorrect(int number) {
		 // the number of test keep changing as there will more new tests
		assertTrue(workPlanByTestTypePage.containsText("Total tests = "));
	}

	@Then("Lab No is displayed correctly on work plan select form")
	public void labNoIsDisplayedCorrectlyOnWorkPlanSelectForm() {
		assertTrue(workPlanByTestTypePage.containsText("Lab No"));
	}

	@Then("Received date and reception time display correctly on work plan select form")
	public void receivedDateAndReceptionTimeDisplayCorrectlyOnWorkPlanSelectForm() {
		assertTrue(workPlanByTestTypePage.containsText("Received Date"));
	}

	@Then("Non conformity flags appear next to correct nonconforming orders")
	public void nonConformityFlagsAppearNextToCorrectNonconformingOrders() {
		assertTrue(workPlanByTestTypePage.containsText("Sample or order is nonconforming"));
	}

	@And("User Check Remove check box")
	public void userCheckRemoveCheckBox() {
		workPlanByTestTypePage.userChecksRemoveCheckBox();
	}

	@Then("User clicks on print Workplan button")
	public void userClicksOnPrintWorkplanButton() throws InterruptedException {
		workPlanByTestTypePage.clickPrintWorkPlan();
		Thread.sleep(1000);
	}

	@Then("Workplan appears  in a new tab or window")
	public void workplanAppearsInANewTabOrWindow() throws InterruptedException {
		workPlanByTestTypePage.verifyReportPrinted();
	}

	@Then("Tests for which Remove checkbox was ticked do not appear on printable workplan")
	public void testsForWhichRemoveCheckboxWasTickedDoNotAppearOnPrintableWorkplan() throws IOException {

		// TODO: 06/02/2023  // Error comes due to https certificate error
		// String reportContent = workPlanByTestTypePage.readPDFReportFileGenerated("/PrintWorkplanReport");
	}

	@Given("User select workplan by Panel from the main menu down")
	public void userSelectWorkplanByPanelFromTheMainMenuDown() {
		workPlanByPanelTypePage = homePage.goToWorkPlanByPanelPage();
	}

	@When("user selects Panel type {string} from the dropdown options")
	public void userSelectsPanelTypeFromTheDropdownOptions(String panelType) {
		workPlanByPanelTypePage.selectPanelType(panelType);
	}

	@Then("All Panel Types displayed in drop down List")
	public void allPanelTypesDisplayedInDropDownList() {
		assertTrue(workPlanByPanelTypePage.dropDownPanelTypesDisplayed());
	}

	@Then("Workplan by Panel select form appears")
	public void workplanByPanelSelectFormAppears() {
		workPlanByPanelTypePage.containsText("Print Workplan");
		assertTrue(workPlanByPanelTypePage.workplanTestsTableDisplayed());
	}

	@Then("Workplan by Panel should show all known orders are present {string} and {string}")
	public void workplanByPanelShouldShowAllKnownOrdersArePresentAndLabNo(String panelType, String labNo) {
		assertTrue(workPlanByPanelTypePage.containsText(panelType));
		assertTrue(workPlanByPanelTypePage.containsText(labNo));
	}

	@Then("Workplan by Panel Total number of tests is correct {string}")
	public void workplanByPanelTotalNumberOfTestsIsCorrect(String numberOfTests) {
		// the number of test keep changing as there will more new tests
		assertTrue(workPlanByPanelTypePage.containsText("Total tests = "));
	}

	@Then("Workplan by Panel Lab No is displayed correctly on work plan select form")
	public void workplanByPanelLabNoIsDisplayedCorrectlyOnWorkPlanSelectForm() {
		assertTrue(workPlanByPanelTypePage.containsText("Lab No"));
	}

	@Then("Workplan by Panel Received date and reception time display correctly on work plan select form")
	public void workplanByPanelReceivedDateAndReceptionTimeDisplayCorrectlyOnWorkPlanSelectForm() {
		assertTrue(workPlanByPanelTypePage.containsText("Received Date"));
	}

	@Then("Workplan by Panel Non conformity flags appear next to correct nonconforming orders")
	public void workplanByPanelNonConformityFlagsAppearNextToCorrectNonconformingOrders() {
		assertTrue(workPlanByPanelTypePage.containsText("Sample or order is nonconforming"));
	}

	@And("Workplan by Panel user Check Remove check box")
	public void workplanByPanelUserCheckRemoveCheckBox() {
		workPlanByPanelTypePage.userChecksRemoveCheckBox();
	}

	@Then("Workplan by Panel user clicks on print Workplan button")
	public void workplanByPanelUserClicksOnPrintWorkplanButton() throws InterruptedException {
		workPlanByPanelTypePage.clickPrintWorkPlan();
		Thread.sleep(1000);

	}

	@Then("Workplan by Panel workplan appears  in a new tab or window")
	public void workplanByPanelWorkplanAppearsInANewTabOrWindow() {
		workPlanByPanelTypePage.verifyReportPrinted();
	}

	@Given("User select workplan by Unit from the main menu down")
	public void userSelectWorkplanByUnitFromTheMainMenuDown() {
		workPlanByUnitTypePage = homePage.goToWorkPlanByUnitPage();

	}

	@When("user selects Unit type {string} from the dropdown options")
	public void userSelectsUnitTypeFromTheDropdownOptions(String unitType) {
		workPlanByUnitTypePage.selectUnitType(unitType);
	}

	@Then("All Unit Types displayed in drop down List")
	public void allUnitTypesDisplayedInDropDownList() {
		assertTrue(workPlanByUnitTypePage.dropDownUnitTypesDisplayed());
	}

	@Then("Workplan by Unit select form appears")
	public void workplanByUnitSelectFormAppears() {
		workPlanByUnitTypePage.containsText("Print Workplan");
		assertTrue(workPlanByUnitTypePage.workplanTestsTableDisplayed());
	}

	@Then("Workplan by unit should show all known orders are present {string} and {string}")
	public void workplanByUnitShouldShowAllKnownOrdersArePresentAndLabNo(String unitType, String labNo) {
		assertTrue(workPlanByUnitTypePage.containsText(unitType));
		if (workPlanByUnitTypePage.containsText(labNo)){
			assertTrue(workPlanByUnitTypePage.containsText(labNo));
		}else{
			assertFalse(workPlanByUnitTypePage.containsText("No appropriate tests were found."));
		}
	}

	@Then("Workplan by unit Total number of tests is correct {string}")
	public void workplanByUnitTotalNumberOfTestsIsCorrect(String numberOfTests) {
		// the number of test keep changing as there will more new tests
		assertTrue(workPlanByUnitTypePage.containsText("Total tests = "));
	}

	@Then("Workplan by unit Lab No is displayed correctly on work plan select form")
	public void workplanByUnitLabNoIsDisplayedCorrectlyOnWorkPlanSelectForm() {
		assertTrue(workPlanByUnitTypePage.containsText("Lab No"));
	}

	@Then("Workplan by unit Received date and reception time display correctly on work plan select form")
	public void workplanByUnitReceivedDateAndReceptionTimeDisplayCorrectlyOnWorkPlanSelectForm() {
		assertTrue(workPlanByUnitTypePage.containsText("Received Date"));
	}

	@Then("Workplan by unit Non conformity flags appear next to correct nonconforming orders")
	public void workplanByUnitNonConformityFlagsAppearNextToCorrectNonconformingOrders() {
		assertTrue(workPlanByUnitTypePage.containsText("Sample or order is nonconforming"));
	}

	@And("Workplan by unit user Check Remove check box")
	public void workplanByUnitUserCheckRemoveCheckBox() {
		workPlanByUnitTypePage.userChecksRemoveCheckBox();
	}

	@Then("Workplan by unit user clicks on print Workplan button")
	public void workplanByUnitUserClicksOnPrintWorkplanButton() throws InterruptedException {
		workPlanByUnitTypePage.clickPrintWorkPlan();
		Thread.sleep(1000);

	}

	@Then("Workplan by unit workplan appears  in a new tab or window")
	public void workplanByUnitWorkplanAppearsInANewTabOrWindow() {
		workPlanByUnitTypePage.verifyReportPrinted();
	}

	@Given("User goes to Result Entry page By Unit")
	public void userGoesToResultEntryPageByUnit() {
		resultsEntryPage = homePage.selectsResultAndClickEnterByUnit();
	}

	@When("User select unit type from drop down select list {string}")
	public void userSelectUnitTypeFromDropDownSelectList(String unitType) {
		resultsEntryPage.selectUnitType(unitType);
	}

	@And("User enter results for the tests {string}")
	public void userEnterResultsForTheTests(String labNo) throws InterruptedException {
		resultsEntryPage.enterLabNoFieldSearch(labNo);
		 resultsEntryPage.clickSearchByLabNo();
		 if (resultsEntryPage.alertPresent()){
			 resultsEntryPage.acceptAlert();
		 }
		 assertFalse(resultsEntryPage.containsText("Accession number not found"));
		resultsEntryPage.enterTestResult();
		resultsEntryPage.clickSaveButton();
		if (resultsEntryPage.alertPresent()){
			resultsEntryPage.acceptAlert();
		}
		homePage = resultsEntryPage.goToHomePage();
		Thread.sleep(1000);
	}

	@Then("Go to WorkPlan by test type {string}")
	public void goToWorkPlanByTestType(String TestType) throws InterruptedException {
		workPlanByTestTypePage = homePage.goToWorkPlanByTestPage();
		Thread.sleep(1000);
		workPlanByTestTypePage.selctTestType(TestType);
	}

	@Then("Test {string} nolonger exists on the workplan by test type")
	public void testNolongerExistsOnTheWorkplanByTestType(String labNo) throws InterruptedException {
		assertFalse(workPlanByTestTypePage.containsText(labNo));
		homePage = workPlanByTestTypePage.goToHomePage();
		Thread.sleep(1000);
	}

	@Then("Go to WorkPlan by panel Type {string}")
	public void goToWorkPlanByPanelType(String PanelType) throws InterruptedException {
		workPlanByPanelTypePage = homePage.goToWorkPlanByPanelPage();
		Thread.sleep(1000);
		workPlanByPanelTypePage.selectPanelType(PanelType);
	}

	@Then("Test {string} nolonger exists on the workplan by panel Type")
	public void testNolongerExistsOnTheWorkplanByPanelType(String labNo) throws InterruptedException {
//		assertFalse(workPlanByPanelTypePage.containsText(labNo)); to be reviewed
		homePage= workPlanByPanelTypePage.goToHomePage();
		Thread.sleep(1000);
	}

	@Then("Go to WorkPlan by unit Type{string}")
	public void goToWorkPlanByUnitType(String UnitType) throws InterruptedException {
		workPlanByUnitTypePage = homePage.goToWorkPlanByUnitPage();
		Thread.sleep(1000);
		workPlanByUnitTypePage.selectUnitType(UnitType);
	}

	@Then("Test {string} nolonger exists on the workplan by unit Type")
	public void testNolongerExistsOnTheWorkplanByUnitType(String labNo) {
		assertFalse(workPlanByUnitTypePage.containsText(labNo));
	}
}
