package org.openelisglobal.qaframework.automation;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

	@When("User clicks admin menu link")
	public void userClicksAdminMenuLink() throws InterruptedException {
		adminPage = homePage.goToAdminPage();
		Thread.sleep(100);
	}

	@And("User clicks Test Management left menu item link")
	public void userClicksTestManagementLeftMenuItemLink() throws InterruptedException {
		testManagementPage = adminPage.goToTestManagementPage();
		Thread.sleep(100);
	}

	@Given("Test Management page appears with functionality links")
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

	@Given("Check if rename existing test names link exists")
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
}
