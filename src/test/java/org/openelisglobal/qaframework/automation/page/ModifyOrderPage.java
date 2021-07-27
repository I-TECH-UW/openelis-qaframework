package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

public class ModifyOrderPage extends Page{

    private static final String PAGE_PATH = "/SampleEdit.do";
	
	private static final String PATH_HOME = "/Dashboard.do";

    private static final By FIELD_LAB_NUMBER = By.id("patientLabNoSearchValue");

    private static final By BUTTON_SEARCH = By.id("enhancedSearchButton");

    private static final By CHECK_BOX_EXISTING_TEST = By.name("existingTests[2].canceled");

    private static final By BUTTON_SAVE = By.id("saveButtonId");


    public ModifyOrderPage(Page parent) {
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

    public void enterLabNumber(String labNumber) {
		setText(FIELD_LAB_NUMBER, labNumber);
	}

	public void clickSearchButton() {
		clickOn(BUTTON_SEARCH);
	}

    public void removeOneTest(){
        if(hasElementWithoutWait(CHECK_BOX_EXISTING_TEST)){
            clickOn(CHECK_BOX_EXISTING_TEST);
        }
    }

    public void clickSaveButton() {
		clickOn(BUTTON_SAVE);
	}
}
