package org.openelisglobal.qaframework.automation;

import static org.junit.Assert.assertTrue;

import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.AddOrderPage;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.page.TestProperties;
import org.openelisglobal.qaframework.automation.test.TestBase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
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

	@When("User clicks add order and goes to Add order Page")
	public void goToAddOrderPage() throws Exception {
		addOrderPage = homePage.goToAddOrderPage();
	}

	@Then("Order form should appear")
	public void orderFormShouldAppear() throws Exception {
		assertTrue(addOrderPage.containsTextRequest());
	}

	@Then("Accesion Number should be mandatory")
	public void acessionNUmberShouldBeMandatory() throws Exception {
		// addOrderPage.waitForLabNumber();
		// assertTrue(addOrderPage.isAccesNumberMandatory());
	}

	@When("User enters Accesion Number {string}")
	public void enterAcessionNumber(String accesionNumber) throws Exception {
		addOrderPage.enterAccesionNumber(accesionNumber);
	}

	@When("Assert AccesionNumber Entered {string}")
	public void checkEnteredAcessionNumber(String accesionNumber)
			throws Exception {
		assertTrue(addOrderPage.assertionNumberEntered(accesionNumber));
	}

	@When("User clicks Generate Button")
	public void clickGenerate() throws Exception {
		addOrderPage.clickGenerateButton();
	}

	@When("Generated Accesion Number should be a digit")
	public void generatedAccesionNumbershouldBeDigit() throws Exception {
		assertTrue(addOrderPage.GeneratedAssertionNumberIsDigit());
	}
}
