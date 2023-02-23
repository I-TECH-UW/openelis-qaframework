package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

/**
 * This class represents the Results, Enter By Unit Type Page
 */
public class ResultsUnitTypePage extends Page {
	
	private static final String PAGE_PATH = "/LogbookResults";
	
	private static final By SEARCH_FORM = By.id("searchDiv");
	
	private static final By DROP_DOWN_UNIT_TYPE = By.id("testSectionId");

	private static final By BUTTON_SAVE = By.id("saveButtonId");

	private static final String PATH_HOME = "/Dashboard";

	private static final By DROP_DOWN_TEST_RESULT = By.xpath("//select[starts-with(@id,'resultId')]");

	private  static  final  By LAB_NO_SEARCH_FIELD = By.id("labnoSearch");

	private  static final By SEARCH_BUTTON = By.xpath("//*[@id=\"searchDiv\"]/div[3]/div[1]/input[2]");

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

	public void enterTestResult() {
		for (int n = 1; n <= 7; n++) {
			By FIELD_TEST_RESULT_N = By.xpath("(//input[starts-with(@id,'results')])[" + n + "]");
			if (hasElementWithoutWait(FIELD_TEST_RESULT_N)) {
				if (n==1){
					setText(FIELD_TEST_RESULT_N, "43");
				}else{
					setText(FIELD_TEST_RESULT_N, "3");
				}
			}
		}
		if (hasElementWithoutWait(DROP_DOWN_TEST_RESULT)) {
			selectNthOptionFromDropDown(DROP_DOWN_TEST_RESULT, 4);
		}
	}

	public void clickSaveButton() {
		clickOn(BUTTON_SAVE);
	}

	public HomePage goToHomePage() {
		this.goToPage(PATH_HOME);
		return new HomePage(this);
	}

	public void enterLabNoFieldSearch(String labNo){
		setText(LAB_NO_SEARCH_FIELD,labNo);
	}
	public void clickSearchByLabNo(){
		clickOn(SEARCH_BUTTON);
	}
}
