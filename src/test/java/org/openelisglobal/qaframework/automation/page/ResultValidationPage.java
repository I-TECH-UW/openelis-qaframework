package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

public class ResultValidationPage extends Page {
	
	public ResultValidationPage(Page parent) {
		super(parent);
	}
	
	private static final String PAGE_PATH = "/ResultValidation.do";
	
	private static final String PATH_HOME = "/Dashboard.do";

	private static final By DROP_DOWN_UNIT_TYPE = By.id("testSectionId");

	private static final By FIELD_RESULT1 = By.id("resultId_1");

	private static final By FIELD_RESULT2 = By.id("resultId_2");

	private static final By FIELD_LAB_NUMBER_SEARCH = By.id("labnoSearch");

	private static final By FIELD_NOTE = By.id("note_1");

	private static final By BUTTON_SHOW_HIDE = By.id("showHideButton_1");

	private static final By BUTTON_SAVE = By.id("saveButtonId");

	private static final By CHECK_BOX_ACCEPTED = By.id("accepted_1");

	private static final By BUTTON_SEARCH = By.xpath("//input[@value='Search'][@type='button']");

	@Override
	public String getPageUrl() {
		return PAGE_PATH;
	}
	
	public HomePage goToHomePage() {
		this.goToPage(PATH_HOME);
		return new HomePage(this);
	}

	public void selectUnitType(String unitType) {
		selectFrom(DROP_DOWN_UNIT_TYPE, unitType);
	}

	public void chageResult(String value){
		findElement(FIELD_RESULT1).sendKeys(value);	    
	}

	public void clickResultField2(){
        clickOn(FIELD_RESULT2);
	}

	public void checkAcceptedCheckBox(){
        clickOn(CHECK_BOX_ACCEPTED);
	}

	public void enterNote(String note){
      setText(FIELD_NOTE, note);
	}
	
	public void clickShowHide(){
		clickOn(BUTTON_SHOW_HIDE);
	}

	public void clickSave(){
		clickOn(BUTTON_SAVE);
	}

	public ResultValidationByAccesionNumberPage clickSearch(){
		clickOn(BUTTON_SEARCH);
        acceptAlert();
		return new ResultValidationByAccesionNumberPage(this) ;
	}

	public void enterLabNumberSearch(String labNumber){
		setText(FIELD_LAB_NUMBER_SEARCH, labNumber);;
	}
}
