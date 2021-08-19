package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

/**
 * This class represents the Result Validation Page
 */
public class ResultValidationPage extends Page {
	
	public ResultValidationPage(Page parent) {
		super(parent);
	}
	
	private static final String PAGE_PATH = "/ResultValidation.do";
	
	private static final String PATH_HOME = "/Dashboard.do";

	private static final By DROP_DOWN_UNIT_TYPE = By.id("testSectionId");

	private static final By FIELD_RESULT = By.xpath("//input[starts-with(@id,'resultId_')]");

	private static final By FIELD_LAB_NUMBER_SEARCH = By.id("labnoSearch");

	private static final By FIELD_NOTE = By.xpath("//textarea[starts-with(@id,'note_')]");

	private static final By BUTTON_SHOW_HIDE = By.id("showHideButton_0");

	private static final By BUTTON_SAVE = By.id("saveButtonId");

	private static final By BUTTON_CANCEL = By.id("cancelButtonId");

	private static final By BUTTON_SEARCH = By.xpath("//input[@value='Search'][@type='button']");

	private static final By CHECK_BOX_ACCEPTED = By.id("accepted_0");

	private static final By CHECK_BOX_RETEST = By.id("rejected_0");

	private static final By CKECK_BOX_SAVE_ALL = By.id("selectAllAccept");

	private static final By CKECK_BOX_RETEST_ALL = By.id("selectAllReject");

	private static final By ICON_NOTES_EDIT = By.xpath("//img[contains(@src,'note-edit')]");

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
		findElement(FIELD_RESULT).sendKeys(value);		    
	}

	public void clickSearchField(){
        clickOn(FIELD_LAB_NUMBER_SEARCH);
	}

	public void checkAcceptedCheckBox(){
        clickOn(CHECK_BOX_ACCEPTED);
	}

	public void checkRetestCheckBox(){
        clickOn(CHECK_BOX_RETEST);
	}

	public void enterNote(String note) {
		String id = findElement(FIELD_RESULT).getAttribute("id");
		String n = id.substring(id.length() - 1);
		By FIELD_NOTE_N = By.id("note_" + n);
		setText(FIELD_NOTE_N, note);
	}

	public String getNote(){
		String id = findElement(FIELD_RESULT).getAttribute("id");
		String n = id.substring(id.length() - 1);
		By FIELD_NOTE_N = By.id("note_" + n);
		return getValue(FIELD_NOTE_N);
	  }
	
	public void clickShowHide(){
		clickOn(BUTTON_SHOW_HIDE);
	}

	public void clickSave(){
		clickOn(BUTTON_SAVE);
	}

	public void clickCancel(){
		clickOn(BUTTON_CANCEL);
	}

	public  ResultValidationByOderPage clickSearch(){
		clickOn(BUTTON_SEARCH);
        acceptAlert();
		return new  ResultValidationByOderPage(this) ;
	}

	public void enterLabNumberSearch(String labNumber){
		setText(FIELD_LAB_NUMBER_SEARCH, labNumber);;
	}

	public void checkSaveAll(){
		clickOn(CKECK_BOX_SAVE_ALL);
	}

	public void checkRetestAll(){
		clickOn(CKECK_BOX_RETEST_ALL);
	}

	public Boolean allResultsCheckedSave() {
		for (int x = 0; x <= 2; x++) {
			By CHECK_BOX_ACCEPTED_N = By.id("accepted_" + x);
			if (hasElementWithoutWait(CHECK_BOX_ACCEPTED_N)) {
				if (!isChecked(CHECK_BOX_ACCEPTED_N)) {
					return false;
				}
			}
		}
		return true;
	}

	public Boolean allResultsCheckedRetest() {
		for (int x = 0; x <= 2; x++) {
			By CHECK_BOX_REJECTED_N = By.id("rejected_" + x);
			if (hasElementWithoutWait(CHECK_BOX_REJECTED_N)) {
				if (!isChecked(CHECK_BOX_REJECTED_N)) {
					return false;
				}
			}
		}
		return true;
	}

	public Boolean resultSaveChecked(){
		return isChecked(CHECK_BOX_ACCEPTED);
	}

	public Boolean resultRetestChecked(){
		return isChecked(CHECK_BOX_RETEST);
	}

	public Boolean hasEditIcon() {
		return hasElement(ICON_NOTES_EDIT);
	}
}
