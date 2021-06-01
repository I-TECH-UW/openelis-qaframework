package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class HomePage extends Page {

	public HomePage(Page page) {
		super(page);
	}

	public String getLoggedUsername() {
		return findElement(By.id("user-info")).getText();
	}

	@Override
	public String getPageUrl() {
		return "/Dashboard.do";
	}

	public WebElement getLogOutLink() {
		return findElement(By.id("logout-form"));
	}
}
