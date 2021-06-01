package org.openelisglobal.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertNotNull;

import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.page.TestProperties;
import org.openelisglobal.qaframework.automation.test.TestBase;

public class LoginSteps extends TestBase {

	private LoginPage loginPage;
	protected TestProperties testProperties = TestProperties.instance();

	@After(RunTest.HOOK.LOGIN)
	public void destroy() {
		quit();
	}

	@Before(RunTest.HOOK.LOGIN)
	public void setLoginPage() {
		loginPage = new LoginPage(getWebDriver());
	}

	@Given("User visits login page")
	public void visitLoginPage() throws Exception {
		loginPage.go();
	}

	@When("User enters {string} username")
	public void anyUsername(String username) {
		if ("setupUser".equals(username)) {
			username = testProperties.getUsername();
		}
		loginPage.enterUsername(username);
	}

	@And("User enters {string} password")
	public void anyPassword(String password) {
		if ("setupPass".equals(password)) {
			password = testProperties.getPassword();
		}
		loginPage.enterPassword(password);
	}

	@And("User Logs in")
	public void userLogsIn() {
		loginPage.getLoginButton().click();
	}

	@Then("System Evaluates Login {string}")
	public void evaluateLogin(String status) {
		HomePage homePage = new HomePage(loginPage);
		if (status.trim().endsWith("true")) {
			assertNotNull(homePage.getLogOutLink());
		} else if (status.trim().endsWith("false")) {
			assertNotNull(loginPage.getLoginButton());
		}
	}
}
