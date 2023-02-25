package org.openelisglobal.qaframework.automation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
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

	@Then("username,password, repeat password,Firstname,lastname and Password Expiration Date Should be required")
	public void usernamePasswordRepeatPasswordFirstnameLastnameAndPasswordExpirationDateShouldBeRequired() {
		assertEquals(userManagementPage.getUsernameRequiredClass(), "requiredlabel");
		assertEquals(userManagementPage.getPasswordRequiredClass(), "requiredlabel");
		assertEquals(userManagementPage.getRepeatPasswordRequiredClass(), "requiredlabel");
		assertEquals(userManagementPage.getPasswordExpiryDateRequiredClass(), "requiredlabel");
		assertEquals(userManagementPage.getFirstNameRequiredClass(), "requiredlabel");
		assertEquals(userManagementPage.getLastnameRequiredClass(), "requiredlabel");
	}

	@And("User enters username {string}")
	public void userEntersUsername(String username) {
		userManagementPage.enterUsername(username);
	}

	@And("User enters first name {string}")
	public void userEntersFirstName(String firstName) {
		userManagementPage.enterFirstName(firstName);
	}

	@And("User enters last name {string}")
	public void userEntersLastName(String lastName) {
		userManagementPage.enterLastName(lastName);
	}

	@And("User enters password {string}")
	public void userEntersPassword(String password) {
		userManagementPage.enterPasswordAndConfirmPassword(password);
	}

	@Then("Assign all the roles and permissions to the newly created user")
	public void assignAllTheRolesAndPermissionsToTheNewlyCreatedUser() {
		assertTrue(userManagementPage.globalRoleExists());
		assertTrue(userManagementPage.allPermissionsCheckBoxExists());
		userManagementPage.assignGlobalRole();
		userManagementPage.assignAllPermissions();
	}

	@And("User Clicks save button to create user information")
	public void userClicksSaveButtonToCreateUserInformation() throws InterruptedException {
		userManagementPage.clickSaveButton();
		Thread.sleep(1000);
	}

	@Then("User logs out and logins with the newly created account {string} and {string}")
	public void userLogsOutAndLoginsWithTheNewlyCreatedAccountAnd(String username, String password)
			throws InterruptedException {
		homePage = userManagementPage.goToHomePage();
		if (userManagementPage.alertPresent()) {
			userManagementPage.acceptAlert();
		}
		loginPage = homePage.ClickLogOut();
		assertTrue(loginPage.containsText("Login"));

		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.getLoginButton().click();
		Thread.sleep(100);
		assertTrue(homePage.containsText("Namanya Abert"));
	}

}
