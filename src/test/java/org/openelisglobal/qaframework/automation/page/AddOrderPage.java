package org.openelisglobal.qaframework.automation.page;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
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
	
	private static final By FIELD_PHONE_NUMBER = By.id("providerWorkPhoneID");
	
	private static final By FIELD_FAX = By.id("providerFaxID");
	
	private static final By FIELD_EMAIL = By.id("providerEmailID");
	
	private static final By BUTTON_GENERATE = By.id("generateAccessionButton");
	
	private static final By BUTTON_ADD_SAMPLE = By.id("samplesSectionId");
	
	private static final By BUTTON_REMOVE_CONDITION = By.className("asmListItemRemove");
	
	private static final By BUTTON_REMOVE_ALL = By.xpath("//input[@value='Remove All']");
	
	private static final By BUTTON_REMOVE_SAMPLE = By.id("removeButton_1");
	
	private static final By SELECT_SAMPLE = By.id("sampleTypeSelect");
	
	private static final By SELECT_CONDITION = By.id("asmSelect0");
	
	private static final By REQUIRED_REQUEST_DATE = By
	        .xpath("//div[@id='orderDisplay']/table/tbody/tr/td/table/tbody/tr[2]/td/span");
	
	private static final By REQUIRED_RECIEVED_DATE = By
	        .xpath("//div[@id='orderDisplay']/table/tbody/tr/td/table/tbody/tr[3]/td/span");
	
	private static final By REQUIRED_SITE_NAME = By
	        .xpath("//div[@id='orderDisplay']/table/tbody/tr/td/table/tbody/tr[6]/td/span");
	
	private static final By REQUIRED_LAST_NAME = By
	        .xpath("//div[@id='orderDisplay']/table/tbody/tr/td/table/tbody/tr[11]/td/span");
	
	private static final By REQUIRED_SAMPLE_ADDITION = By
	        .xpath("//input[@id='samplesSectionId']/following-sibling::span[1]");
	
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
	
	public WebElement getGenerateButton() {
		return findElement(BUTTON_GENERATE);
	}
	
	public WebElement getAddSampleButton() {
		return findElement(BUTTON_ADD_SAMPLE);
	}
	
	public WebElement getRemoveAllButton() {
		return findElement(BUTTON_REMOVE_ALL);
	}
	
	public WebElement getAccessionNumberField() {
		return findElement(FIELD_LAB_NUMBER);
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
	
	public WebElement getTelephoneField() {
		return findElement(FIELD_PHONE_NUMBER);
	}
	
	public WebElement getFaxField() {
		return findElement(FIELD_FAX);
	}
	
	public WebElement getEmailField() {
		return findElement(FIELD_EMAIL);
	}
	
	public WebElement getSampleSelectionField() {
		return findElement(SELECT_SAMPLE);
	}
	
	public WebElement getConditonSelectionField() {
		return findElement(SELECT_CONDITION);
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
	
	public void clickAddSampleButton() {
		if (getAddSampleButton().getAttribute("value") == "+") {
			clickOn(BUTTON_ADD_SAMPLE);
		}
	}
	
	public void clickRemoveAllButton() {
		clickOn(BUTTON_REMOVE_ALL);
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
	
	public void enterTelephone(String telephone) {
		setText(FIELD_PHONE_NUMBER, telephone);
	}
	
	public void enterFax(String fax) {
		setText(FIELD_FAX, fax);
	}
	
	public void enterEmail(String email) {
		setText(FIELD_EMAIL, email);
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
	
	public String getAccesionNumberClass() {
		return getAccessionNumberField().getAttribute("class");
	}
	
	public String getTelephoneNumberClass() {
		return getTelephoneField().getAttribute("class");
	}
	
	public String getSampleRecquiredClass() {
		return findElement(REQUIRED_SAMPLE_ADDITION).getAttribute("class");
	}
	
	public void sampleTypesDisplayInDropDownMenu() {
		String[] samples = { "Serum", "Plasma", "Urine", "DBS", "Fluid", "Blood", "Swab", "Respiratory Swab", "Sputum" };
		clickOn(SELECT_SAMPLE);
		int n = 0;
		List<WebElement> options = getSampleSelectionField().findElements(FIELD_OPTION);
		for (WebElement option : options) {
			if (n >= 1) {
				assertTrue(Arrays.asList(samples).contains(option.getText()));
			}
			if (n == 7) {
				break;
			}
			n = n + 1;
		}
	}
	
	public void selectSampleTypeFromDropDownMenu() {
		clickOn(SELECT_SAMPLE);
		List<WebElement> options = getSampleSelectionField().findElements(FIELD_OPTION);
		int n = 0;
		for (WebElement option : options) {
			if (n == 1) {
				option.click();
				break;
			}
			n = n + 1;
		}
	}
	
	public void selectSampleConditionFromDropDownMenu() {
		clickOn(SELECT_CONDITION);
		List<WebElement> options = getConditonSelectionField().findElements(FIELD_OPTION);
		int n = 0;
		for (WebElement option : options) {
			if (n >= 1) {
				option.click();
			}
			if (n == 5) {
				break;
			}
			n = n + 1;
		}
	}
	
	public void removeAddedSampleConditionFromDropDownMenu() throws InterruptedException {
		List<WebElement> options = getConditonSelectionField().findElements(FIELD_OPTION);
		int n = 0;
		for (WebElement option : options) {
			if (n >= 1) {
				clickOn(BUTTON_REMOVE_CONDITION);
			}
			if (n == 5) {
				break;
			}
			n = n + 1;
		}
	}
	
	public void removeAddedSampleType() {
		clickOn(BUTTON_REMOVE_SAMPLE);
	}
	
	public void reAddSamples() {
		clickOn(SELECT_SAMPLE);
		List<WebElement> options = getSampleSelectionField().findElements(FIELD_OPTION);
		int n = 0;
		for (WebElement option : options) {
			option.click();
			if (n == 4) {
				break;
			}
			n = n + 1;
		}
	}
}
