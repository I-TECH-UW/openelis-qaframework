package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

/**
 * This class represents the Results Entry Page
 */
public class ResultsEntryPage extends Page {
	
	private static final String PAGE_PATH = "/LogbookResults";
	
	private static final String PATH_HOME = "/Dashboard";
	
	private static final By FIELD_TEST_DATE = By.id("testDate_3");
	
	private static final By FIELD_TEST_RESULT = By.xpath("//input[starts-with(@id,'results')]");
	
	private static final By DROP_DOWN_TEST_RESULT = By.xpath("//select[starts-with(@id,'resultId')]");
	
	private static final By LABEL_IMAGE_NON_CONFORMING = By.xpath("//img[contains(@src,'nonconforming')]");
	
	private static final By FIELD_LAB_NUMBER_SEARCH = By.id("labnoSearch");
	
	private static final By BUTTON_LAB_NUMBER_SEARCH = By
	        .xpath("//*[@id='mainForm']/table/tbody/tr[4]/td/div[3]/div[1]/input[2]");
	
	private static final By BUTTON_CANCEL = By.id("cancelButtonId");
	
	private static final By BUTTON_SAVE = By.id("saveButtonId");
	
	private static final By BUTTON_SHOW_HIDE_NOTES = By.id("showHideButton_3");
	
	private static final By TEXT_AREA_NOTES = By.id("note_3");
	
	private static final By ICON_NOTES_EDIT = By.xpath("//img[contains(@src,'note-edit')]");
	
	private static final By CHECK_BOX_RESULT_ANALYSER = By.id("testResult3.analysisMethod1");

	private static final By CHECK_APPROVE = By.xpath("//input[starts-with(@id,'testResult')]");
	
	public ResultsEntryPage(Page parent) {
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
	
	public String getTestDateValue() {
		return getValue(FIELD_TEST_DATE);
	}
	
	public void enterTestDate(String date) {
		setText(FIELD_TEST_DATE, date);
	}
	
	public Boolean hasNonConformingFlag() {
		return hasElement(LABEL_IMAGE_NON_CONFORMING);
	}
	
	public void enterSearchAccesionNumber(String accesionNumber) {
		setText(FIELD_LAB_NUMBER_SEARCH, accesionNumber);
	}
	
	public SearchResultsByOrderPage clickOnLabNumberSearch() {
		clickOn(BUTTON_LAB_NUMBER_SEARCH);
		return new SearchResultsByOrderPage(this);
	}
	
	public void enterTestResult(String value) {
		if (hasElementWithoutWait(FIELD_TEST_RESULT)) {
			setText(FIELD_TEST_RESULT, value);
		}
		if (hasElementWithoutWait(DROP_DOWN_TEST_RESULT)) {
			selectNthOptionFromDropDown(DROP_DOWN_TEST_RESULT, 4);
		}
		
	}
	
	public String getTestResultValue() {
		return getValue(FIELD_TEST_RESULT);
	}

	public Boolean hasTestResultsField(){
       return hasElementWithoutWait(FIELD_TEST_RESULT);
	}
	
	public void clickOnDateField() {
		clickOn(FIELD_TEST_DATE);
	}

	public void checkAproveTest() {
		clickOn(CHECK_APPROVE);
	}
	
	public Boolean resultFieldHasYellowBackground() {
		String yellowBackground = "background: rgb(255, 255, 160);";
		return getStyle(FIELD_TEST_RESULT).contains(yellowBackground) ? true : false;
	}
	
	public void clickShowHideButton() {
		clickOn(BUTTON_SHOW_HIDE_NOTES);
	}
	
	public Boolean notesTextAreaDisplays() {
		return hasElement(TEXT_AREA_NOTES);
	}
	
	public void enterNotes(String notes) {
		setText(TEXT_AREA_NOTES, notes);
	}
	
	public Boolean hasNoteEditIcon() {
		return hasElement(ICON_NOTES_EDIT);
	}
	
	public void clickResultList() {
		clickOn(DROP_DOWN_TEST_RESULT);
	}
	
	public Boolean resultListContainsOptions() {
		return dropDownHasOptions(DROP_DOWN_TEST_RESULT);
	}
	
	public void selectResultFromResultList() {
		selectOptionFromDropDown(DROP_DOWN_TEST_RESULT);
	}
	
	public void clickAnalyserCheckBox() {
		clickOn(CHECK_BOX_RESULT_ANALYSER);
	}
	
	public Boolean analyserCheckBoxMarked() {
		return isChecked(CHECK_BOX_RESULT_ANALYSER);
	}
	
	public void clickCancelButton() {
		clickOn(BUTTON_CANCEL);
	}
	
	public void clickSaveButton() {
		clickOn(BUTTON_SAVE);
	}
	
	public void refreshAndEnterTestResult() {
		refreshPage();
		acceptAlert();
		for (int x = 0; x <= 2; x++) {
			By FIELD_TEST_N = By.id("results_" + x);
			By DROP_DOWN_TEST_RESULT_N = By.id("resultId_" + x);
			By CHECK_RESULT_N = By.id("testResult" + x + ".forceTechApproval1");
			if (hasElementWithoutWait(FIELD_TEST_N)) {
				setText(FIELD_TEST_N, "43");
			}
			
			if (hasElementWithoutWait(DROP_DOWN_TEST_RESULT_N)) {
				selectNthOptionFromDropDown(DROP_DOWN_TEST_RESULT_N ,4);
			}
			if (hasElementWithoutWait(CHECK_RESULT_N)) {
				clickOn(CHECK_RESULT_N);
			}
		}
	}
	
	public Boolean resultsFieldsHaveValues() {
		for (int x = 0; x <= 4; x++) {
			By FIELD_TEST = By.id("results_" + x);
			if (!getValueWithoutWait(FIELD_TEST).equals("")) {
				return true;
			}
		}
		return false;
	}
}
