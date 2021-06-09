package org.openelisglobal.qaframework.automation.page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This class represents the Add Order page
 */
public class AddOrderPage extends Page {
	
	private static final String PATH_ADD_ORDER = "/SamplePatientEntry.do";
	
	private static final String LABEL_TEXT_REQUEST = "Test Reques";
	
	private static final By FIELD_LAB_NUMBER = By.id("labNo");
	
	private static final By FIELD_REQUEST_DATE = By.id("requestDate");
	
	private static final By FIELD_RECIEVED_DATE = By.id("receivedDateForDisplay");
	
	private static final By FIELD_NEXT_VISIT_DATE = By.id("nextVisitDate");
	
	private static final By BUTTON_GENERATE = By.id("generateAccessionButton");
	
	private static final By REQUIRED_REQUEST_DATE = By
	        .xpath("//div[@id='orderDisplay']/table/tbody/tr/td/table/tbody/tr[2]/td/span");
	
	private static final By REQUIRED_RECIEVED_DATE = By
	        .xpath("//div[@id='orderDisplay']/table/tbody/tr/td/table/tbody/tr[3]/td/span");
	
	private static final By FIELD_RECIEVED_TIME = By.id("receivedTime");
	
	public AddOrderPage(Page parentPage) {
		super(parentPage);
	}
	
	@Override
	public String getPageUrl() {
		return PATH_ADD_ORDER;
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
	
	public WebElement getRequestDateField() {
		return findElement(FIELD_REQUEST_DATE);
	}
	
	public WebElement getRecievedDateField() {
		return findElement(FIELD_RECIEVED_DATE);
	}
	
	public WebElement getNextVisitDateField() {
		return findElement(FIELD_NEXT_VISIT_DATE);
	}
	
	public WebElement getRecievedTimeField() {
		return findElement(FIELD_RECIEVED_TIME);
	}
	
	public void enterAccessionNumber(String accesionNumber) {
		getAccessionNumberField().clear();
		getAccessionNumberField().sendKeys(accesionNumber);
	}
	
	public Boolean accessionNumberEntered(String accesionNumber) {
		if (getAccessionNumberField().getAttribute("value").equals(accesionNumber)) {
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
		if (StringUtils.isNumeric(getAccessionNumberField().getAttribute("value"))) {
			return true;
		}
		return false;
	}
	
	public String getRequestDateValue() {
		return getRequestDateField().getAttribute("value");
	}
	
	public String getRecievedDateValue() {
		return getRecievedDateField().getAttribute("value");
	}
	
	public String getRecievedTimeValue() {
		return getRecievedTimeField().getAttribute("value");
	}
	
	public String getRequestDateRequiredClass() {
		return findElement(REQUIRED_REQUEST_DATE).getAttribute("class");
	}
	
	public String getRecievedDateRequiredClass() {
		return findElement(REQUIRED_RECIEVED_DATE).getAttribute("class");
	}
	
	public String getRecievedTimeClass() {
		return getRecievedTimeField().getAttribute("class");
	}
	
	public void enterRecievedDate(String date) {
		setText(FIELD_RECIEVED_DATE, date);
	}
	
	public void enterRequestDate(String date) {
		setText(FIELD_REQUEST_DATE, date);
	}
	
	public void enterRecievedTime(String time) {
		setText(FIELD_RECIEVED_TIME, time);
	}
	
	public void clickOnNextVisitDate() {
		clickOn(FIELD_NEXT_VISIT_DATE);
	}
	
	public String getRecievedDateClass() {
		return getRecievedDateField().getAttribute("class");
	}
	
	public String getRequestDateClass() {
		return getRequestDateField().getAttribute("class");
	}
	
	public String getReceievedTimeClass() {
		return getRecievedTimeField().getAttribute("class");
	}
}
