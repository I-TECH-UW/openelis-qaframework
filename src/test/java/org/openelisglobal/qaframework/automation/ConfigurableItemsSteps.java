package org.openelisglobal.qaframework.automation;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.AdminPage;
import org.openelisglobal.qaframework.automation.page.ConfigurableItemsPage;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.test.TestBase;

public class ConfigurableItemsSteps extends TestBase {

	private LoginPage loginPage;

	private HomePage homePage;

	private AdminPage adminPage;

	private ConfigurableItemsPage configurableItemsPage;

	@After(RunTest.HOOK.CONFIGURABLE_ITEMS)
	public void destroy() {
		quit();
	}

	@Before(RunTest.HOOK.CONFIGURABLE_ITEMS)
	public void setLoginPage() {
		System.out.println("....Configurable Items......");
		loginPage = new LoginPage(getWebDriver());
	}

	@Given("User Logins,then goes to home Page")
	public void userLoginsThenGoesToHomePage() throws InterruptedException {
		homePage = loginPage.goToHomePage();
	}

	@Given("User Clicks Admin menu --> then Order Entry Configuration")
	public void userClicksAdminMenuThenOrderEntryConfiguration() throws InterruptedException {
		adminPage = homePage.goToAdminPage();
		Thread.sleep(1000);
		configurableItemsPage = adminPage.goToOrderEntryConfiguration();
		assertTrue(configurableItemsPage.modifyButtonExists());
	}

	@Then("Check auto-fill collection date-time \\(configurable item)")
	public void checkAutoFillCollectionDateTimeConfigurableItem() throws InterruptedException {
		configurableItemsPage.checkAutoFillDateCheckBox();
		configurableItemsPage.clickModifyButton();
		Thread.sleep(100);
		assertTrue(configurableItemsPage.containsText("Edit Record"));
		assertTrue(configurableItemsPage.containsText("auto-fill collection date/time"));

	}

	@And("User changes auto-fill collection date-time value and clicks save")
	public void userChangesAutoFillCollectionDateTimeValueAndClicksSave() {

	}
}
