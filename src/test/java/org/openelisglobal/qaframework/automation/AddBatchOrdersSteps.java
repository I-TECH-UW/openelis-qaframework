package org.openelisglobal.qaframework.automation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
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
		assertEquals(addBatchOrdersPage.getReceivedDateClass(), "text required error");
	}

	@And("User enters Received date in the future")
	public void userEntersReceivedDateInTheFuture() {
		String futureDate = Utils.getFutureDate();
		addBatchOrdersPage.enterReceivedDate(futureDate);
	}

	@Then("Alert appears if the date is in the future")
	public void alertAppearsIfTheDateIsInTheFuture() {
		addBatchOrdersPage.acceptAlert();
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

	@Then("User Clicks on change sample type select options {string}")
	public void userClicksOnChangeSampleTypeSelectOptions(String sampleType) {
		addBatchOrdersPage.selectSampleType(sampleType);
		assertTrue(addBatchOrdersPage.containsText("Panels"));
		assertTrue(addBatchOrdersPage.containsText("Available Tests"));
		assertTrue(addBatchOrdersPage.panelsTableExists());
	}

}
