package org.openelisglobal.qaframework.automation;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.AddOrderPage;
import org.openelisglobal.qaframework.automation.page.AdminPage;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.page.TestManagementPage;
import org.openelisglobal.qaframework.automation.test.TestBase;

public class TestManagementSteps extends TestBase {

	private LoginPage loginPage;

	private HomePage homePage;

	private AdminPage adminPage;

	private TestManagementPage testManagementPage;

	private final String test_Name = "gastric fluid analysis";

	private AddOrderPage addOrderPage;

	@Before(RunTest.HOOK.TEST_MGT)
	public void setLoginPage() {
		System.out.println("....Test Management......");
		loginPage = new LoginPage(getWebDriver());
	}

	@After(RunTest.HOOK.TEST_MGT)
	public void destroy() {
		quit();
	}

	@Given("User logins and Visits Home Page")
	public void userLoginsAndVisitsHomePage() throws InterruptedException {
		Thread.sleep(100);
		homePage = loginPage.goToHomePage();
	}

	@Given("User clicks admin menu link")
	public void userClicksAdminMenuLink() throws InterruptedException {
		adminPage = homePage.goToAdminPage();
		Thread.sleep(100);
	}

	@Then("User clicks Test Management left menu item link")
	public void userClicksTestManagementLeftMenuItemLink() throws InterruptedException {
		testManagementPage = adminPage.goToTestManagementPage();
		Thread.sleep(100);
	}

	@And("Test Management page appears with functionality links")
	public void test_management_page_appears_with_functionality_links(DataTable dataTable) throws InterruptedException {
		List<String> expectedMenuItems = dataTable.asList();

		List<String> actualMenuItems = testManagementPage.getTestManagementConfigMenuItems();

		assertTrue(actualMenuItems.size() == expectedMenuItems.size()
				&& actualMenuItems.containsAll(expectedMenuItems)
				&& expectedMenuItems.containsAll(actualMenuItems));
	}

	@Then("Count List menu list Items should be <{int}>")
	public void countListMenuListItemsShouldBe(int expectedCount) throws InterruptedException {
		sleep(100);
		int actual = testManagementPage.getTestManagementConfigMenuItemsCount();
		assertEquals(expectedCount, actual);
		sleep(100);
	}

	@And("Check if rename existing test names link exists")
	public void checkIfRenameExistingTestNamesLinkExists() {
		boolean linkExists = testManagementPage.renameExistingTestNamesLinkExists();
		assertTrue(linkExists);
	}

	@When("User clicks rename existing test names link")
	public void userClicksRenameExistingTestNamesLink() throws InterruptedException {
		Thread.sleep(100);
		testManagementPage.clickRenameExistingTestNamesLink();
		Thread.sleep(100);
	}

	@Then("Laboratory test names \\(hyperlinked & underlined) should appear on page in three columns. All are in primary language")
	public void laboratory_test_names_hyperlinked_underlined_should_appear_on_page_in_three_columns_all_are_in_primary_language() {
		boolean result = testManagementPage.labTestNamesAppearInATable();
		assertTrue(result);
	}

	@When("User selects an existing Test Name {string}")
	public void userSelectsAnExistingTestName(String testName) throws InterruptedException {
		Thread.sleep(100);
		testManagementPage.clickOnTestTestName(testName);
		Thread.sleep(100);
	}

	@And("User enters test name {string} in English")
	public void userEntersTestNameInEnglish(String testNameEng) {
		testManagementPage.enterTestNameInEnglish(testNameEng);
	}

	@And("User enters test name {string} in French")
	public void userEntersTestNameInFrench(String testNameFR) {
		testManagementPage.enterTestNameInFrench(testNameFR);
	}

	@And("User enters Reporting test name {string} in English")
	public void userEntersReportingTestNameInEnglish(String reportTestNameEng) {
		testManagementPage.enterReportingTestNameInEnglish(reportTestNameEng);
	}

	@And("User enters reporting test name {string} in French")
	public void userEntersReportingTestNameInFrench(String reportTestNameFR) {
		testManagementPage.enterReportingTestNameInFrench(reportTestNameFR);
	}

	@Then("User Clicks save button")
	public void userClicksSaveButton() throws InterruptedException {
		sleep(100);
		testManagementPage.clickSaveButton();
		sleep(100);
	}

	@And("User Clicks accept to confirm changes")
	public void userClicksAcceptToConfirmChanges() throws InterruptedException {
		sleep(100);
		testManagementPage.clickAcceptButton();
		Thread.sleep(1000);
	}

	@Then("Confirm Test name {string} update was saved")
	public void confirmTestNameUpdateWasSaved(String updatedTestName) throws InterruptedException {
		assertTrue(testManagementPage.containsText(updatedTestName + "(Plasma)"));
	}

	@When("User clicks reject button")
	public void userClicksRejectButton() throws InterruptedException {
		sleep(100);
		testManagementPage.clickRejectButton();
		sleep(100);

	}

	@Then("Confirm Test name {string} update was rejected but contains {string}")
	public void confirmTestNameUpdateWasRejectedButContains(String testUpdateName, String presentTestName)
			throws InterruptedException {
		sleep(100);
		assertFalse(testManagementPage.containsText(testUpdateName + "(Plasma)"));
		assertTrue(testManagementPage.containsText(presentTestName));
	}

	@When("User clicks cancel button")
	public void userClicksCancelButton() throws InterruptedException {
		sleep(100);
		testManagementPage.clickCancelButton();
		sleep(100);
	}

	@Then("Confirm Test name {string} update was canceled")
	public void confirmTestNameUpdateWasCanceled(String testUpdateName) throws InterruptedException {
		sleep(100);
		assertFalse(testManagementPage.containsText(testUpdateName + "(Plasma)"));
	}

	@And("User clicks on finished button")
	public void userClicksFinishButton() {
		testManagementPage.clickFinishButton();
	}

	@And("User Clicks Add new test link on the test management menu")
	public void userClicksAddNewTestLinkOnTheTestManagementMenu() {
		testManagementPage.clickAddNewTestsLink();
		testManagementPage.containsText("Add new tests");
		testManagementPage.containsText("Test Name");
		testManagementPage.testEnglishNameFieldExists();
		testManagementPage.testFrenchNameFieldExists();
		testManagementPage.containsText("Test Section");
		testManagementPage.testSectionDropDownExists();
		testManagementPage.containsText("Panel");
		testManagementPage.containsText("Reporting Test Name");
		testManagementPage.containsText("Panel");
	}

	@Then("User Enters test name for English {string} and French {string} into the respective text fields")
	public void userEntersTestNameForEnglishAndFrenchIntoTheRespectiveTextFields(String englishTestName,
			String frenchTestName) {
		testManagementPage.enterEngTestName(englishTestName);
		testManagementPage.enterFRTestName(frenchTestName);
	}

	@Then("From the Unit of Measure dropdown select the appropriate unit {string}")
	public void fromTheUnitOfMeasureDropdownSelectTheAppropriateUnit(String unitOfMeasure) {
		testManagementPage.selectUnitOfMeasure(unitOfMeasure);
	}

	@Then("From the Test Section drop down select the appropriate option {string}")
	public void fromTheTestSectionDropDownSelectTheAppropriateOption(String testSection) {
		testManagementPage.selectTestSectionDropDownOption(testSection);
	}

	@Then("From the Panel dropdown select the appropriate option {string}")
	public void fromThePanelDropdownSelectTheAppropriateOption(String panel) {
		testManagementPage.selectPanelDropDownOption(panel);
	}

	@Then("From the Result type dropdown select the appropriate option")
	public void fromTheResultTypeDropdownSelectTheAppropriateOption() {
		testManagementPage.selectResultTypeDropDownOption("Numeric");
	}

	@Then("Under Reporting Test name, click on Copy from Test Name")
	public void underReportingTestNameClickOnCopyFromTestName() throws InterruptedException {
		assertEquals("", testManagementPage.getTestReportNameEnglish());
		assertEquals("", testManagementPage.getTestReportNameEnglish());
		testManagementPage.clickCopyTestNameButton();
		Thread.sleep(100);

		assertNotEquals("", testManagementPage.getTestReportNameEnglish());
		assertNotEquals("", testManagementPage.getTestReportNameEnglish());
	}

	@Then("User Enters Reporting test names for English and French into the respective fields if different from Test Name")
	public void userEntersReportingTestNamesForEnglishAndFrenchIntoTheRespectiveFieldsIfDifferentFromTestName() {
		testManagementPage.enterBothEngReportNameAndFrenchTestName("Gastric Juice", "analyse du suc gastrique");
	}

	@Then("With all of the *required fields completed the Next button becomes active")
	public void withAllOfTheRequiredFieldsCompletedTheNextButtonBecomesActive() {
		assertFalse(testManagementPage.nextButtonDisabled());
	}

	@Then("The tick box next to Active is auto-selected")
	public void theTickBoxNextToActiveIsAutoSelected() {
		assertTrue(testManagementPage.ActiveCheckBoxSelected());
	}

	@Then("The tick box next to Orderable is auto-selected")
	public void theTickBoxNextToOrderableIsAutoSelected() {
		assertTrue(testManagementPage.OrderableCheckBoxSelected());
	}

	@And("User Clicks Next button")
	public void userClicksNextButton() {
		testManagementPage.ClickNextButton();
		testManagementPage.containsText("Test display order");
		testManagementPage.containsText("Sample Type");
	}

	@Then("User selects the Type {string} from SampleType dropdown options")
	public void userSelectsTheTypeFromSampleTypeDropdownOptions(String sampleType) {
		testManagementPage.selectSampleTypeDropdown(sampleType);
		assertFalse(testManagementPage.nextButtonDisabled());
	}

	@Then("User selects one more type from SampleType dropdown options")
	public void userSelectsOneMoreTypeFromSampleTypeDropdownOptions() {
		testManagementPage.selectSampleTypeDropdown("Urines");
		assertTrue(testManagementPage.testNameHasGreenMarking(test_Name));
	}

	@Then("Select the new Test Name and hold the selection move it to the desired position in the Test display order")
	public void selectTheNewTestNameAndHoldTheSelectionMoveItToTheDesiredPositionInTheTestDisplayOrder()
			throws InterruptedException {
		int beforeMove = testManagementPage.testNameListPosition(test_Name);
		testManagementPage.moveTestNameToDifferentPosition();
		Thread.sleep(1000);
		int afterMove = testManagementPage.testNameListPosition(test_Name);
		//		assertNotEquals(afterMove, beforeMove);
	}

	@And("User Clicks on Next button")
	public void userClicksOnNextButton() throws InterruptedException {
		testManagementPage.ClickNextButton();
		testManagementPage.containsText(test_Name);
		Thread.sleep(1000);
		testManagementPage.ClickNextButton();
	}

	@And("User Clicks on Accept button")
	public void userClicksOnAcceptButton() throws InterruptedException {
		testManagementPage.clickAcceptTestButton();
		Thread.sleep(1000);

		testManagementPage.containsText("Add new tests");
		testManagementPage.containsText("Test Name");
		testManagementPage.testEnglishNameFieldExists();
		testManagementPage.testFrenchNameFieldExists();
		testManagementPage.containsText("Test Section");
		testManagementPage.testSectionDropDownExists();
		testManagementPage.containsText("Panel");
		testManagementPage.containsText("Reporting Test Name");
		testManagementPage.containsText("Panel");
	}

	@Given("User Clicks on the Order menu tab and select Add order")
	public void userClicksOnTheOrderMenuTabAndSelectAddOrder() {
		addOrderPage = homePage.goToAddOrderPage();
	}

	@Then("User selects sample type {string} from the drop down")
	public void userSelectsSampleTypeFromTheDropDown(String sampleType) {
		addOrderPage.selectSampleTypeFromDropDownMenu(sampleType);
	}

	@And("User Searches TestName {string} from the available Tests")
	public void userSearchesTestNameFromTheAvailableTests(String testName) throws InterruptedException {
		addOrderPage.enterSearchTestName(testName);
		Thread.sleep(1000);
		assertTrue(addOrderPage.validateTestNameExists(testName));
	}
}
