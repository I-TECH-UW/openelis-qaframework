package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;

/**
 * This class represents the Validation By Order Page
 */
public class ResultValidationByOderPage extends Page {

    private static final String PAGE_PATH = "/AccessionValidation.do";

    private static final String PATH_HOME = "/Dashboard.do";

    private static final By FIELD_LAB_NO_SEARCH = By.id("searchAccessionID");

    private static By BUTTON_ACCESION_NUMBER_SERCH = By.id("retrieveTestsID");

    private static By CHECK_BOX_ACCEPTED_4 = By.id("accepted_4");

    private static final By BUTTON_SAVE = By.id("saveButtonId");

    public  ResultValidationByOderPage(Page parent) {
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

    public void clickAccesionNumberSearch() {
        clickOn(BUTTON_ACCESION_NUMBER_SERCH);
    }

    public void enterAccesionNumber(String accesionNumber) {
        setText(FIELD_LAB_NO_SEARCH, accesionNumber);
    }

    public void checkAccept() {
        if (hasElementWithoutWait(CHECK_BOX_ACCEPTED_4)) {
            clickOn(CHECK_BOX_ACCEPTED_4);
        }
    }

    public void clickSaveButton() {
        if (hasElementWithoutWait(BUTTON_SAVE)) {
            clickOn(BUTTON_SAVE);
        }
    }
}
