package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

/**
 * This class represents the Work Plan By Test Type Page
 */
public class WorkPlanByTestTypePage extends Page {

    private static final String PAGE_PATH = "/WorkPlanByTest.do";
	
	private static final String PATH_HOME = "/Dashboard.do";

	private static final By DROP_DOWN_TEST_TYPES = By.id("testName");

    public WorkPlanByTestTypePage(Page parent) {
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

	public void selctTestType(String testType){
		selectFrom(DROP_DOWN_TEST_TYPES, testType);
	}  
}
