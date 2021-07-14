package org.openelisglobal.qaframework.automation.page;

public class ResultValidationPage extends Page {

	public ResultValidationPage(Page parent) {
		super(parent);
	}

	private static final String PAGE_PATH = "/ResultValidation.do";

	private static final String PATH_HOME = "/Dashboard.do";

	@Override
	public String getPageUrl() {
		return PAGE_PATH;
	}

	public HomePage goToHomePage() {
		this.goToPage(PATH_HOME);
		return new HomePage(this);
	}

}
