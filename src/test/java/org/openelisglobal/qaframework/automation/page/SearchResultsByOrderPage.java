package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

public class SearchResultsByOrderPage extends Page {
	
	private static final String PAGE_PATH = "/AccessionResults.do";
	
	private static final By SEARCH_FORM = By.id("searchDiv");
	
	private static final String PATH_HOME = "/Dashboard.do";
	
	private static final By FIELD_LAB_NO_SEARCH = By.id("searchAccessionID");
	
	private static final By LABEL_ACCESION_NOT_FOUND = By.xpath("//*[@id='mainForm']/table/tbody/tr[1]/td/center/h1");
	
	private static By BUTTON_ACCESION_NUMBER_SERCH = By.id("retrieveTestsID");
	
	private static By CHECK_BOX_ACCEPT_AS_IS = By.id("testResult1.forceTechApproval1");
	
	private static By CHECK_BOX_ACCEPT_AS_IS2 = By.id("testResult2.forceTechApproval1");
	
	private static By FIELD_NOTES = By.id("note_1");
	
	private static By FIELD_NOTES2 = By.id("note_2");
	
	private static final By BUTTON_SHOW_HIDE_NOTES = By.id("showHideButton_1");
	
	private static final By BUTTON_SHOW_HIDE_NOTES2 = By.id("showHideButton_2");
	
	private static final By ICON_NOTES_EDIT = By.xpath("//img[contains(@src,'note-edit')]");
	
	private static final By BUTTON_SAVE = By.id("saveButtonId");
	
	public SearchResultsByOrderPage(Page parent) {
		super(parent);
	}
	
	@Override
	public String getPageUrl() {
		return PAGE_PATH;
	}
	
	public Boolean hasSearchForm() {
		return hasElement(SEARCH_FORM);
	}
	
	public HomePage goToHomePage() {
		this.goToPage(PATH_HOME);
		return new HomePage(this);
	}
	
	public void enterAccesionNumber(String accesionNumber) {
		setText(FIELD_LAB_NO_SEARCH, accesionNumber);
	}
	
	public Boolean accesionNumberNotFoundDisplays() {
		return hasElement(LABEL_ACCESION_NOT_FOUND);
	}
	
	public void clickAccesionNumberSearch() {
		clickOn(BUTTON_ACCESION_NUMBER_SERCH);
	}
	
	public void clickCheckAsIs() {
		clickOn(CHECK_BOX_ACCEPT_AS_IS);
	}
	
	public void clickAnotherCheckAsIs() {
		clickOn(CHECK_BOX_ACCEPT_AS_IS2);
	}
	
	public Boolean noteFieldOpens() {
		return hasElement(FIELD_NOTES);
	}
	
	public Boolean anotherNoteFieldOpens() {
		return hasElement(FIELD_NOTES2);
	}
	
	public Boolean editIconRevertsToGreenPlus() {
		return hasElement(BUTTON_SHOW_HIDE_NOTES2);
	}
	
	public void enterNotes(String notes) {
		setText(FIELD_NOTES, notes);
	}
	
	public String getNotes() {
		return getValue(FIELD_NOTES);
	}
	
	public void clickHideNotes() {
		clickOn(BUTTON_SHOW_HIDE_NOTES);
	}
	
	public Boolean hasEditIcon() {
		return hasElement(ICON_NOTES_EDIT);
	}
	
	public void clickSaveButton() {
		clickOn(BUTTON_SAVE);
	}	
}
