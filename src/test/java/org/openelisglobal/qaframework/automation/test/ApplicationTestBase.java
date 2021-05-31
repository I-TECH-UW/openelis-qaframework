package org.openelisglobal.qaframework.automation.test;
import org.junit.Before;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;

public class ApplicationTestBase extends TestBase {
	protected HomePage homePage;

	public ApplicationTestBase() {
		super();
	}

	@Before
	public void before() {
		homePage = new HomePage(page);
	}

	@Override
	protected LoginPage getLoginPage() {
		return new LoginPage(getWebDriver());
	}
}
