package org.openelisglobal.qaframework.automation;

import static java.lang.Thread.sleep;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.page.TestManagementPage;
import org.openelisglobal.qaframework.automation.test.TestBase;

public class TestManagementSteps extends TestBase {

	private LoginPage loginPage;

	private HomePage homePage;

	private TestManagementPage testManagementPage;

	@Before(RunTest.HOOK.TEST_MGT)
	public void setLoginPage() {
		System.out.println("....Test Management......");
		loginPage = new LoginPage(getWebDriver());
	}

	@After(RunTest.HOOK.TEST_MGT)
	public void destroy() {
		quit();
	}

	@Given("User logins and Visits Home Page")
	public void userLoginsAndVisitsHomePage() throws InterruptedException {
		homePage = loginPage.goToHomePage();
		Thread.sleep(100);
	}

	@When("User clicks admin menu link")
	public void userClicksAdminMenuLink() throws InterruptedException {
		testManagementPage = homePage.goToMasterListsPage();
		Thread.sleep(100);
	}

	@And("User clicks Test Management left menu item link")
	public void userClicksTestManagementLeftMenuItemLink() throws InterruptedException {
		boolean expected = testManagementPage.goToTestManagementConfigMenu();
		assertTrue(expected);
		Thread.sleep(100);
	}

	@Given("Test Management page appears with functionality links")
	public void test_management_page_appears_with_functionality_links(DataTable dataTable) throws InterruptedException {
		List<String> expectedMenuItems = dataTable.asList();

		List<String> actualMenuItems = testManagementPage.getTestManagementConfigMenuItems();

		assertTrue(actualMenuItems.size() == expectedMenuItems.size()
				&& actualMenuItems.containsAll(expectedMenuItems)
				&& expectedMenuItems.containsAll(actualMenuItems));
	}

	@Then("Count List menu list Items should be <{int}>")
	public void countListMenuListItemsShouldBe(int expectedCount) throws InterruptedException {
		int actual = testManagementPage.getTestManagementConfigMenuItemsCount();
		assertEquals(expectedCount, actual);
		sleep(500);
	}
}
