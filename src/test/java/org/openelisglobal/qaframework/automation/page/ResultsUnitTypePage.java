package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

/**
 * This class represents the Results, Enter By Unit Type Page
 */
public class ResultsUnitTypePage extends Page {
	
	private static final String PAGE_PATH = "/LogbookResults.do";
	
	private static final By SEARCH_FORM = By.id("searchDiv");
	
	private static final By DROP_DOWN_UNIT_TYPE = By.id("testSectionId");
	
	public ResultsUnitTypePage(Page parent) {
		super(parent);
	}
	
	@Override
	public String getPageUrl() {
		return PAGE_PATH;
	}
	
	public Boolean hasResultUnitTypeSearchForm() {
		return hasElement(SEARCH_FORM);
	}
	
	public Boolean hasUnitTypeOptions() {
		return dropDownHasOptions(DROP_DOWN_UNIT_TYPE);
	}
	
	public ResultsEntryPage selectUnitType(String unitType) {
		selectFrom(DROP_DOWN_UNIT_TYPE, unitType);
		return new ResultsEntryPage(this);
	}
}
