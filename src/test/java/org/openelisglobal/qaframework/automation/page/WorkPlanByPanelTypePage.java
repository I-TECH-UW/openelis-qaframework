package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

/**
 * This class represents the Work Plan By Pannel Type Page
 */
public class WorkPlanByPanelTypePage extends Page {

    private static final String PAGE_PATH = "/WorkPlanByPanel.do";
	
	private static final String PATH_HOME = "/Dashboard.do";

	private static final By DROP_DOWN_PANEL_TYPES = By.id("testName");

    public WorkPlanByPanelTypePage(Page parent) {
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

	public void selectPanelType(String panelType){
		selectFrom(DROP_DOWN_PANEL_TYPES, panelType);
	}   
}
