package org.openelisglobal.qaframework.automation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.AddBatchOrdersPage;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.test.TestBase;
import org.openelisglobal.qaframework.automation.utils.Utils;

public class AddBatchOrdersSteps extends TestBase {

	private LoginPage loginPage;

	private HomePage homePage;

	private AddBatchOrdersPage addBatchOrdersPage;

	@After(RunTest.HOOK.ADD_BATCH_ORDERS)
	public void destroy() {
		quit();
	}

	@Before(RunTest.HOOK.ADD_BATCH_ORDERS)
	public void setLoginPage() {
		System.out.println("....Add Batch Orders......");
		loginPage = new LoginPage(getWebDriver());
	}

	@Given("User logins and goes to Add Batch Orders page")
	public void userLoginsAndGoesToAddBatchOrdersPage() throws InterruptedException {
		homePage = loginPage.goToHomePage();
		Thread.sleep(1000);
		addBatchOrdersPage = homePage.goToAddBatchOrders();
	}

	@Then("Configuration adding orders screen appears")
	public void configurationAddingOrdersScreenAppears() {
		assertTrue(addBatchOrdersPage.containsText("Batch Order Entry Setup"));
		assertTrue(addBatchOrdersPage.currentDateFieldExists());
		assertTrue(addBatchOrdersPage.currentTimeFieldExists());
		assertTrue(addBatchOrdersPage.receivedDateFieldExists());
		assertTrue(addBatchOrdersPage.receivedTimeFieldExists());
	}

	@And("Current date and received date set by default")
	public void currentDateAndReceivedDateSetByDefault() {
		assertThat(addBatchOrdersPage.getCurrentDate(), is(not("")));
		assertThat(addBatchOrdersPage.getReceivedDate(), is(not("")));
	}

	@And("Both Current date and Received date are mandatory")
	public void bothCurrentDateAndReceivedDateAreMandatory() {
		assertEquals(addBatchOrdersPage.getCurrentDateRequiredClass(), "requiredlabel");
		assertEquals(addBatchOrdersPage.getReceivedDateRequiredClass(), "requiredlabel");
	}

	@When("User enters incorrect received date {string}, Alert will appear")
	public void userEntersIncorrectReceivedDateAlertWillAppear(String date) throws InterruptedException {
		addBatchOrdersPage.enterReceivedDate(date);
		if (addBatchOrdersPage.alertPresent()) {
			addBatchOrdersPage.acceptAlert();
		}
		Thread.sleep(1000);
		assertEquals(addBatchOrdersPage.getReceivedDateClass(), "text required error");
	}

	@And("User enters Received date in the future")
	public void userEntersReceivedDateInTheFuture() throws InterruptedException {
		String futureDate = Utils.getFutureDate();
		addBatchOrdersPage.enterReceivedDate(futureDate);
		if (addBatchOrdersPage.alertPresent()) {
			addBatchOrdersPage.acceptAlert();
		}
		Thread.sleep(100);
	}

	@Then("Alert appears if the date is in the future")
	public void alertAppearsIfTheDateIsInTheFuture() throws InterruptedException {
		if (addBatchOrdersPage.alertPresent()){
			addBatchOrdersPage.acceptAlert();
		}
		Thread.sleep(1000);
		assertEquals(addBatchOrdersPage.getReceivedDateClass(), "text required error");
	}

	@And("User enters Date {string} with text then enters correct date {string} in the received date field")
	public void userEntersDateWithTextThenEntersCorrectDateInTheReceivedDateField(String inValidDate, String validDate)
			throws InterruptedException {
		addBatchOrdersPage.enterReceivedDate(inValidDate);
		if (addBatchOrdersPage.alertPresent()) {
			addBatchOrdersPage.acceptAlert();
		}
		assertEquals(addBatchOrdersPage.getReceivedDateClass(), "text required error");

		addBatchOrdersPage.enterReceivedDate(validDate);
	}

	@And("Sample types are displayed in drop-down list")
	public void sampleTypesAreDisplayedInDropDownList() {
		assertTrue(addBatchOrdersPage.sampleTypesDropDownMenuContainsSampleTypesOptions());
	}

	@Then("User Clicks on sample type dropdown list options {string}")
	public void userClicksOnSampleTypeDropdownListOptions(String sampleType) {
		addBatchOrdersPage.selectSampleType(sampleType);
		assertTrue(addBatchOrdersPage.containsText("Panels"));
		assertTrue(addBatchOrdersPage.containsText("Available Tests"));
		assertTrue(addBatchOrdersPage.panelsTableExists());
	}

	@Then("Checklists for applicable Panels and Available Tests should appear for the sample type when that sample type is selected")
	public void checklistsForApplicablePanelsAndAvailableTestsShouldAppearForTheSampleTypeWhenThatSampleTypeIsSelected() {
		assertTrue(addBatchOrdersPage.panelCheckBoxExists());
		assertTrue(addBatchOrdersPage.testCheckBoxExists());
	}

	@And("User checks the box for test or panel")
	public void userChecksTheBoxForTestOrPanel() {
		addBatchOrdersPage.panelCheckBoxClick();
		addBatchOrdersPage.testCheckBoxClick();
	}

	@And("In Optional Fields: User checks Site name {string}")
	public void inOptionalFieldsUserChecksSiteName(String siteName) throws InterruptedException {
		addBatchOrdersPage.enterSiteNameSuggestion(siteName);
		Thread.sleep(100);
	}

	@Then("User Clicks next button")
	public void userClicksNextButton() {
		addBatchOrdersPage.clickNextButton();
	}

	@And("Batch order entry screen should contain UI options elements {string}")
	public void batchOrderEntryScreenShouldContainUIOptions(String siteName) {
		assertTrue(addBatchOrdersPage.currentDateFieldExists());
		assertTrue(addBatchOrdersPage.currentTimeFieldExists());
		assertTrue(addBatchOrdersPage.containsText("Received Date:"));
		assertTrue(addBatchOrdersPage.containsText("Received Time:"));
		assertTrue(addBatchOrdersPage.containsText(siteName));
		assertTrue(addBatchOrdersPage.containsText("COVID-19 ANTIBODY"));
	}

	@Then("User Clicks Generate Barcode and save button")
	public void userClicksGenerateBarcodeAndSaveButton() throws InterruptedException {
		addBatchOrdersPage.clickSaveButton();
		Thread.sleep(1500);
		assertNotEquals(addBatchOrdersPage.getLabNo(), "");
	}
}
