package org.openelisglobal.qaframework.automation;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.AdminPage;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.page.UserManagementPage;
import org.openelisglobal.qaframework.automation.test.TestBase;

public class UserManagementSteps extends TestBase {

	private LoginPage loginPage;

	private HomePage homePage;

	private AdminPage adminPage;

	private UserManagementPage userManagementPage;

	@Before(RunTest.HOOK.USER_MGT)
	public void setLoginPage() {
		System.out.println("....User Management......");
		loginPage = new LoginPage(getWebDriver());
	}

	@After(RunTest.HOOK.USER_MGT)
	public void destroy() {
		quit();
	}

	@Given("User logins and Visits Home Page to Manage users")
	public void userLoginsAndVisitsHomePageToManageUsers() throws InterruptedException {
		homePage = loginPage.goToHomePage();
	}

	@When("User goes to admin management menu")
	public void userGoesToAdminManagementMenu() throws InterruptedException {
		adminPage = homePage.goToAdminPage();
		Thread.sleep(100);
	}

	@And("User clicks User management left menu item link")
	public void userClicksUserManagementLeftMenuItemLink() {
		userManagementPage = adminPage.goToUserManagementPage();
	}

	@Given("User is on user management Page")
	public void userIsOnUserManagementPage() {
		assertTrue(userManagementPage.containsText("User Management"));
	}

	@When("User Clicks add new user button")
	public void userClicksAddNewUserButton() {
		userManagementPage.clickAddNewUserButton();
		assertTrue(userManagementPage.containsText("Add User"));
	}

	/** TODO: 26/01/2023
	*  user management is incomplete due to waiting for fix
	  updates from dev team
	 **/
}
