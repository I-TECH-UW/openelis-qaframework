package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

public class WorkPlanByUnitTypePage extends Page{
    
    private static final String PAGE_PATH = "/WorkPlanByTestSection.do";
	
	private static final String PATH_HOME = "/Dashboard.do";

	private static final By DROP_DOWN_UNIT_TYPES = By.id("testSectionId");

    public WorkPlanByUnitTypePage(Page parent) {
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

	public void selectUnitType(String unitType){
		selectFrom(DROP_DOWN_UNIT_TYPES, unitType);
	}
}
