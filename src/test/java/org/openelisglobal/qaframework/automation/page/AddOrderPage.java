package org.openelisglobal.qaframework.automation.page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This class represents the Add Order page
 */
public class AddOrderPage extends Page {

	private static final String LABEL_TEXT_REQUEST = "Test Reques";
	private static final By FIELD_LAB_NUMBER = By.id("labNo");
	private static final By BUTTON_GENERATE = By.id("generateAccessionButton");

	public AddOrderPage(Page parentPage) {
		super(parentPage);
	}

	@Override
	public String getPageUrl() {
		return "SamplePatientEntry.do";
	}

	public Boolean containsTextRequest() {
		return containsText(LABEL_TEXT_REQUEST);
	}

	public void waitForLabNumber() {
		waitForElementWithSpecifiedMaxTimeout(FIELD_LAB_NUMBER, 120);
	}

	public Boolean isAccessionNumberMandatory() {
		if (findElement(FIELD_LAB_NUMBER).getAttribute("class") == "requiredlabel") {
			return true;
		}
		return false;
	}

	public WebElement getAccessionNumberField() {
		return findElement(FIELD_LAB_NUMBER);
	}

	public WebElement getGenerateButton() {
		return findElement(BUTTON_GENERATE);
	}

	public void enterAccessionNumber(String accesionNumber) {
		getAccessionNumberField().clear();
		getAccessionNumberField().sendKeys(accesionNumber);
	}

	public Boolean accessionNumberEntered(String accesionNumber) {
		if (getAccessionNumberField().getAttribute("value").equals(
				accesionNumber)) {
			return true;
		}
		return false;
	}

	public void clickGenerateButton() throws InterruptedException {
		getAccessionNumberField().clear();
		clickOn(BUTTON_GENERATE);
		Thread.sleep(1000);
	}

	public Boolean GeneratedAccessionNumberIsDigit() {
		if (StringUtils.isNumeric(getAccessionNumberField().getAttribute(
				"value"))) {
			return true;
		}
		return false;
	}
}
