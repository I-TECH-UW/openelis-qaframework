package org.openelisglobal.qaframework.automation;

import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.AddOrderPage;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.test.TestBase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class PatientReportSteps extends TestBase{

    private LoginPage loginPage;

	private HomePage homePage;

    private AddOrderPage addOrderPage;

    @After(RunTest.HOOK.REPORT)
	public void destroy() {
		quit();
	}

	@Before(RunTest.HOOK.REPORT)
	public void setLoginPage() {
		System.out.println("....Patient Reports......");
		loginPage = new LoginPage(getWebDriver());
	}

	@Given("User Vists Home Page and goes to Add Order Page")
	public void visitLoginPage() throws Exception {
		homePage = loginPage.goToHomePage();
        addOrderPage = homePage.goToAddOrderPage();
	}

    @When("User Enters order on Order Entry page, Complete ALL fields, and does not enter results for this order")
    public void enterOrderWithoutResults() throws InterruptedException{
         addOrderPage.innitialiseRandomData();
    }  
}
