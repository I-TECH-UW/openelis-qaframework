package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;
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

}
