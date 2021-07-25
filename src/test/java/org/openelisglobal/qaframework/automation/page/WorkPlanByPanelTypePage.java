package org.openelisglobal.qaframework.automation.page;

public class WorkPlanByPanelTypePage extends Page {

    private static final String PAGE_PATH = "/WorkPlanByPanel.do";
	
	private static final String PATH_HOME = "/Dashboard.do";

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
    
}
