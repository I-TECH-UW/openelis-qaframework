package org.openelisglobal.qaframework.automation;

import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.page.ResultsEntryPage;
import org.openelisglobal.qaframework.automation.test.TestBase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import org.openelisglobal.qaframework.RunTest;

public class ResultsEntrySteps extends TestBase {

	private ResultsEntryPage resultsPage;

	private LoginPage loginPage;

	private HomePage homePage;

	@After(RunTest.HOOK.RESULT)
	public void destroy() {
		quit();
	}

	@Before(RunTest.HOOK.RESULT)
	public void setLoginPage() {
		loginPage = new LoginPage(getWebDriver());
	}

	@Given("User visits Home page")
	public void visitLoginPage() throws Exception {
		homePage = loginPage.goToHomePage();
	}

	@And("User selects Results and clicks Enter By unit")
	public void goToResultsEntryPage() throws Exception {
		resultsPage = homePage.selectsResultAndClickEnterByUnit();
	}

}
