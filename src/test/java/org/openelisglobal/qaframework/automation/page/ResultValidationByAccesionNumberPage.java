package org.openelisglobal.qaframework.automation.page;

public class ResultValidationByAccesionNumberPage extends Page {

    private static final String PAGE_PATH = "/AccessionValidation.do";

    private static final String PATH_HOME = "/Dashboard.do";

    public ResultValidationByAccesionNumberPage(Page parent) {
        super(parent);
    }

    @Override
    public String getPageUrl() {
        return PAGE_PATH;
    }

    public HomePage goToHomePage() {
		this.goToPage(PATH_HOME);
		return new HomePage(this);
	}


}