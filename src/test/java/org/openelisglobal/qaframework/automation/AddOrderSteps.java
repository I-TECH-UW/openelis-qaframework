package org.openelisglobal.qaframework.automation;

import static org.junit.Assert.assertEquals;
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

	@Then("Assert AccesionNumber Entered {string}")
	public void checkEnteredAccessionNumber(String accesionNumber)
			throws Exception {
		assertTrue(addOrderPage.accessionNumberEntered(accesionNumber));
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
		assertEquals(addOrderPage.getRecievedDateValue(), strDate);
		assertEquals(addOrderPage.getRequestDateValue(), strDate);
	}

	@And("Both request and received date should be mandatory")
	public void requestAndRecievedDatesShouldbeMandatory() throws Exception {
		assertEquals(addOrderPage.getRequestDateRequiredClass(),
				"requiredlabel");
		assertEquals(addOrderPage.getRecievedDateRequiredClass(),
				"requiredlabel");
	}

	@When("User enters incorrect Request and Received Date format")
	public void UserEntersIncorrectRequestAndRecievedDateFormat()
			throws Exception {
		assertEquals(addOrderPage.getRecievedDateClass(), "text required");
		assertEquals(addOrderPage.getRequestDateClass(), "required");
		addOrderPage.enterRecievedDate("09-02/2019");
		addOrderPage.enterRequestDate("09-02/2019");
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
}
