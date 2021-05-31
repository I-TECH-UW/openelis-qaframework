package org.openelisglobal.qaframework.automation;
import static org.junit.Assert.fail;

import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.page.TestProperties;
import org.openelisglobal.qaframework.automation.test.ApplicationTestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class Steps extends ApplicationTestBase {
	protected TestProperties testProperties = TestProperties.instance();
	protected LoginPage loginPage;

	public Steps() {
		try {
			startWebDriver();
			loginPage = getLoginPage();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	protected void quit() {
		if (getWebDriver() != null) {
			getWebDriver().quit();
		}
	}

	protected WebElement getElement(By elementBy) {
		try {
			return getWebDriver().findElement(elementBy);
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	protected boolean textExists(String text) {
		return getWebDriver().findElements(
				By.xpath("//*[contains(text(),'" + text + "')]")).size() > 0;
	}

}
