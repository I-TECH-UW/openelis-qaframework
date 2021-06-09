package org.openelisglobal.qaframework.automation.page;

import java.util.List;

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
	
	private static final By FIELD_RECIEVED_TIME = By.id("receivedTime");
	
	private static final By FIELD_SITE_NAME = By.id("requesterId");
	
	private static final By FIELD_PROGRAM = By.id("sampleOrderItems.program");
	
	private static final By FIELD_OPTION = By.tagName("option");
	
	private static final By FIELD_LAST_NAME = By.id("providerLastNameID");
	
	private static final By FIELD_FIRST_NAME = By.id("providerFirstNameID");
	
	private static final By BUTTON_GENERATE = By.id("generateAccessionButton");
	
	private static final By REQUIRED_REQUEST_DATE = By
	        .xpath("//div[@id='orderDisplay']/table/tbody/tr/td/table/tbody/tr[2]/td/span");
	
	private static final By REQUIRED_RECIEVED_DATE = By
	        .xpath("//div[@id='orderDisplay']/table/tbody/tr/td/table/tbody/tr[3]/td/span");
	
	private static final By REQUIRED_SITE_NAME = By
	        .xpath("//div[@id='orderDisplay']/table/tbody/tr/td/table/tbody/tr[6]/td/span");
	
	private static final By REQUIRED_LAST_NAME = By
	        .xpath("//div[@id='orderDisplay']/table/tbody/tr/td/table/tbody/tr[11]/td/span");
	
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
	
	public WebElement getSiteNameField() {
		return findElement(FIELD_SITE_NAME);
	}
	
	public WebElement getProgramField() {
		return findElement(FIELD_PROGRAM);
	}
	
	public WebElement getLastNameField() {
		return findElement(FIELD_LAST_NAME);
	}
	
	public WebElement getFirstNameField() {
		return findElement(FIELD_FIRST_NAME);
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
	
	public void clickOnNextVisitDate() {
		clickOn(FIELD_NEXT_VISIT_DATE);
	}
	
	public void selectSiteNameFromDropDown() throws InterruptedException {
		clickOn(FIELD_SITE_NAME);
		List<WebElement> options = getSiteNameField().findElements(FIELD_OPTION);
		int n = 0;
		for (WebElement option : options) {
			option.click();
			Thread.sleep(100);
			if (n == 5) {
				break;
			}
			n = n + 1;
		}
	}
	
	public void selectProgramFromDropDown() throws InterruptedException {
		clickOn(FIELD_PROGRAM);
		List<WebElement> options = getProgramField().findElements(FIELD_OPTION);
		for (WebElement option : options) {
			option.click();
			Thread.sleep(100);
		}
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
	
	public String getFistNameValue() {
		return getFirstNameField().getAttribute("value");
	}
	
	public String getLastNameValue() {
		return getLastNameField().getAttribute("value");
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
	
	public void enterAccessionNumber(String accesionNumber) {
		setText(FIELD_LAB_NUMBER, accesionNumber);
	}
	
	public void enterLastName(String lastName) {
		setText(FIELD_LAST_NAME, lastName);
	}
	
	public void enterFirstName(String firstName) {
		setText(FIELD_FIRST_NAME, firstName);
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
	
	public String getRecievedDateClass() {
		return getRecievedDateField().getAttribute("class");
	}
	
	public String getRequestDateClass() {
		return getRequestDateField().getAttribute("class");
	}
	
	public String getReceievedTimeClass() {
		return getRecievedTimeField().getAttribute("class");
	}
	
	public String getSiteNameRequiredClass() {
		return findElement(REQUIRED_SITE_NAME).getAttribute("class");
	}
	
	public String getLastNameRequiredClass() {
		return findElement(REQUIRED_LAST_NAME).getAttribute("class");
	}
}
