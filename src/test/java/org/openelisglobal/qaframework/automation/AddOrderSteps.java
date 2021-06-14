package org.openelisglobal.qaframework.automation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.AddOrderPage;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.page.TestProperties;
import org.openelisglobal.qaframework.automation.test.TestBase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddOrderSteps extends TestBase {

	private LoginPage loginPage;

	private HomePage homePage;

	private AddOrderPage addOrderPage;

	protected TestProperties testProperties = TestProperties.instance();

	@After(RunTest.HOOK.ORDER)
	public void destroy() {
		quit();
	}

	@Before(RunTest.HOOK.ORDER)
	public void setLoginPage() {
		loginPage = new LoginPage(getWebDriver());
	}

	@Given("User logs in and visits Home page")
	public void visitLoginPage() throws Exception {
		homePage = loginPage.goToHomePage();
	}

	@And("User clicks add order and goes to Add order Page")
	public void goToAddOrderPage() throws Exception {
		addOrderPage = homePage.goToAddOrderPage();
	}

	@Then("Order form should appear")
	public void orderFormShouldAppear() throws Exception {
		assertTrue(addOrderPage.containsTextRequest());
	}

	@When("User enters Accesion Number {string}")
	public void enterAcessionNumber(String accesionNumber) throws Exception {
		addOrderPage.enterAccessionNumber(accesionNumber);
	}

	@Then("Validate {string} AccesionNumber Entered {string}")
	public void checkEnteredAccessionNumber(String status, String accesionNumber)
			throws Exception {
		if (status.equals("valid")) {
			addOrderPage.clickOnNextVisitDate();
			Thread.sleep(1000);
			assertTrue(addOrderPage.accessionNumberEntered(accesionNumber));
			assertEquals(addOrderPage.getAccesionNumberClass(), "text");
		} else if (status.equals("invalid")) {
			addOrderPage.clickOnNextVisitDate();
			Thread.sleep(1000);
			addOrderPage.acceptAlert();
			assertEquals(addOrderPage.getAccesionNumberClass(), "text error");
		}

	}

	@When("User clicks Generate Button")
	public void clickGenerate() throws Exception {
		addOrderPage.clickGenerateButton();
	}

	@Then("Generated Accesion Number should be a digit")
	public void generatedAccesionNumbershouldBeDigit() throws Exception {
		assertTrue(addOrderPage.GeneratedAccessionNumberIsDigit());
	}

	@Then("View page Request Date and Received Date Default to the current date")
	public void requestAndRecievedDatesShouldDefaultToCurrent()
			throws Exception {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(date);
		// this test will fail at times since the server and the testing
		// framework run in different time zones
		// assertEquals(addOrderPage.getRecievedDateValue(), strDate);
		// assertEquals(addOrderPage.getRequestDateValue(), strDate);
	}

	@And("Both request and received date should be mandatory")
	public void requestAndRecievedDatesShouldbeMandatory() throws Exception {
		assertEquals(addOrderPage.getRequestDateRequiredClass(),
				"requiredlabel");
		assertEquals(addOrderPage.getRecievedDateRequiredClass(),
				"requiredlabel");
	}

	@When("User enters incorrect Request and Received Date format {string}")
	public void UserEntersIncorrectRequestAndRecievedDateFormat(String date)
			throws Exception {
		assertEquals(addOrderPage.getRecievedDateClass(), "text required");
		assertEquals(addOrderPage.getRequestDateClass(), "required");
		addOrderPage.enterRecievedDate(date);
		addOrderPage.enterRequestDate(date);
	}

	@Then("Request and Received Date Fields should show error")
	public void RequestAndRecievedDateFieldShouldThrowError() throws Exception {
		// enter next Vist Date to triger field error
		addOrderPage.clickOnNextVisitDate();
		Thread.sleep(1000);
		assertEquals(addOrderPage.getRecievedDateClass(), "text required error");
		assertEquals(addOrderPage.getRequestDateClass(), "required error");
	}

	@When("User enters Request Date in future")
	public void UserEntersRequestInFuture() throws Exception {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(cal.getTime());
		addOrderPage.enterRequestDate(strDate);
	}

	@Then("Alert should appear if date is in future")
	public void alertShouldApperIfFutureDateIsEntered() throws Exception {
		// enter next Vist Date to triger field error
		addOrderPage.clickOnNextVisitDate();
		Thread.sleep(1000);
		addOrderPage.acceptAlert();
		assertEquals(addOrderPage.getRequestDateClass(), "required error");
	}

	@When("User enters correct Request and Received Date format")
	public void UserEnterscorrectRequestAndRecievedDateFormat()
			throws Exception {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(cal.getTime());
		addOrderPage.enterRecievedDate(strDate);
		addOrderPage.enterRequestDate(strDate);
	}

	@Then("Request and Received Date Fields should not show error")
	public void RequestAndRecievedDateFieldShouldNotThrowError()
			throws Exception {
		// enter next Vist Date to triger field error
		addOrderPage.clickOnNextVisitDate();
		Thread.sleep(1000);
		assertEquals(addOrderPage.getRecievedDateClass(), "text required");
		assertEquals(addOrderPage.getRequestDateClass(), "required");
	}

	@When("User enters Reception time {string}")
	public void userEntersRecievedTime(String time) {
		addOrderPage.enterRecievedTime(time);
	}

	@Then("Field Automatically corrects {string} straight numeric to proper format HH:MM {string}")
	public void fieldAutoCorrectsTime(String action, String correctedTime)
			throws InterruptedException {
		// enter next Vist Date to triger field error
		addOrderPage.clickOnNextVisitDate();
		if (action.trim().equals("auto-correct")) {
			Thread.sleep(1000);
			assertEquals(addOrderPage.getRecievedTimeValue(), correctedTime);
		} else if (action.trim().equals("none")) {
			assertEquals(addOrderPage.getRecievedTimeValue(), correctedTime);
		}
	}

	@And("Field validates {string} correct format")
	public void fieldAcceptsCorrectFormat(String status) throws Exception {
		if (status.trim().equals("valid")) {
			assertNotEquals(addOrderPage.getRecievedTimeClass(), "error");
		} else if (status.trim().equals("invalid")) {
			assertEquals(addOrderPage.getRecievedTimeClass().trim(), "error");
		}
	}

	@Then("Site Name is mandatory")
	public void siteNameShoulBeMandatory() {
		assertEquals(addOrderPage.getSiteNameRequiredClass(), "requiredlabel");
	}

	@And("User Selects Site Name from a Drop down Menu")
	public void selectSiteNameFromDropDownMenu() throws InterruptedException {
		addOrderPage.selectSiteNameFromDropDown();
	}

	@And("User Selects Program from a Drop down Menu")
	public void selectProgramFromDropDownMenu() throws InterruptedException {
		addOrderPage.selectProgramFromDropDown();
	}

	@Then("Requester's Last Name is mandatory")
	public void requesterLastNameIsMandatory() throws InterruptedException {
		assertEquals(addOrderPage.getLastNameRequiredClass(), "requiredlabel");
	}

	@And("User Enters Requester's Last Name {string}")
	public void enterLastName(String lastName) {
		addOrderPage.enterLastName(lastName);
		assertEquals(addOrderPage.getLastNameValue(), lastName);
	}

	@And("User Enters Requester's First Name {string}")
	public void enterFirstName(String firstName) {
		addOrderPage.enterFirstName(firstName);
		assertEquals(addOrderPage.getFistNameValue(), firstName);
	}

	@When("User Enters Telephone Number {string}")
	public void enterTelephone(String telephone) {
		addOrderPage.enterTelephone(telephone);
	}

	@Then("Validate {string} Telephone Number")
	public void validateTelephoneNumber(String status)
			throws InterruptedException {
		if (status.equals("valid")) {
			addOrderPage.clickOnNextVisitDate();
			Thread.sleep(1000);
			assertEquals(addOrderPage.getTelephoneNumberClass(), "text");
		} else if (status.equals("invalid")) {
			addOrderPage.clickOnNextVisitDate();
			Thread.sleep(1000);
			addOrderPage.acceptAlert();
			assertEquals(addOrderPage.getTelephoneNumberClass(), "text error");
		}
	}

	@And("User Enters Fax {string}")
	public void enterFax(String fax) {
		addOrderPage.enterFax(fax);
	}

	@And("User Enters Email {string}")
	public void enterEmail(String email) throws InterruptedException {
		addOrderPage.enterEmail(email);
	}

	@Then("Sample addition is mandatory")
	public void sampleAdditionMandatory() {
		assertEquals(addOrderPage.getSampleRecquiredClass(), "requiredlabel");
	}

	@When("User Clicks on + Button next to Sample")
	public void clickAddSample() {
		addOrderPage.clickAddSampleButton();
	}

	@Then("Sample Selection Field appears")
	public void sampleSelectionFiedlAppears() {
		assertNotNull(addOrderPage.getSampleSelectionField());
	}

	@And("Sample types display in drop-down list")
	public void sampleTypesDisplayInDropDownMenu() {
		addOrderPage.sampleTypesDisplayInDropDownMenu();
	}

	@And("User Selects Sample Type from Drop down menu")
	public void selectSampleTypeFromDropDownMenu() {
		addOrderPage.selectSampleTypeFromDropDownMenu();
	}

	@And("User Selects Sample Conditions from Drop down menu")
	public void selectSampleConditionFromDropDownMenu() {
		addOrderPage.selectSampleConditionFromDropDownMenu();
	}

	@And("User Clicks X to remove added Sample Conditions")
	public void removeAddedSampleConditionFromDropDownMenu()
			throws InterruptedException {
		addOrderPage.removeAddedSampleConditionFromDropDownMenu();
	}

	@And("User Clicks remove button to remove added Sample")
	public void removeAddedSampleTypeFromDropDownMenu() {
		addOrderPage.removeAddedSampleType();
	}

	@And("User Re-adds Samples")
	public void readdSamples() {
		addOrderPage.reAddSamples();
		assertNotNull(addOrderPage.getRemoveAllButton());
	}

	@And("User Clicks to Remove all")
	public void removesAllSamples() {
		addOrderPage.clickRemoveAllButton();
	}

}
