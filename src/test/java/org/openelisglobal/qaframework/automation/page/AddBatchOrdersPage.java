package org.openelisglobal.qaframework.automation.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AddBatchOrdersPage extends Page {

	private static final String PATH_ADD_BATCH_ORDERS = "/SampleBatchEntrySetup";

	private static final String PATH_HOME = "/Dashboard";

	private static final By CURRENT_DATE_FIELD = By.id("currentDate");

	private static final By RECEIVED_DATE_FIELD = By.id("receivedDateForDisplay");

	private static final By CURRENT_TIME_FIELD = By.id("currentTime");

	private static final By RECEPTION_TIME_FIELD = By.id("receivedTime");

	private static final By DROP_DOWN_SAMPLE_TYPES = By.id("sampleTypeSelect");

	private static final By PANELS_TABLE = By.id("addPanelTable");

	private static final By REQUIRED_CURRENT_DATE = By
			.xpath("//*[@id=\"orderDisplay\"]/table/tbody/tr/td/table/tbody/tr[1]/td[1]/span[1]");

	private static final By REQUIRED_RECEIVED_DATE = By
			.xpath("//*[@id=\"orderDisplay\"]/table/tbody/tr/td/table/tbody/tr[2]/td[1]/span[1]");

	public AddBatchOrdersPage(Page parent) {
		super(parent);
	}

	@Override
	public String getPageUrl() {
		return PATH_ADD_BATCH_ORDERS;
	}

	public HomePage goToHomePage() {
		this.goToPage(PATH_HOME);
		return new HomePage(this);
	}

	public String getCurrentDate() {
		return getValue(CURRENT_DATE_FIELD);
	}

	public String getReceivedDate() {
		return getValue(RECEIVED_DATE_FIELD);
	}

	public WebElement getReceivedDateField() {
		return findElement(RECEIVED_DATE_FIELD);
	}

	public void selectSampleType(String sampleType) {
		By FIELD_OPTION = By.tagName("option");
		clickOn(DROP_DOWN_SAMPLE_TYPES);
		List<WebElement> options = findElement(DROP_DOWN_SAMPLE_TYPES).findElements(FIELD_OPTION);

		for (WebElement option : options) {
			if (option.getText().equals(sampleType)) {
				option.click();
				break;
			}
		}
	}

	public boolean panelsTableExists() {
		return hasElement(PANELS_TABLE);
	}

	public boolean currentDateFieldExists() {
		return hasElement(CURRENT_DATE_FIELD);
	}

	public boolean currentTimeFieldExists() {
		return hasElement(CURRENT_TIME_FIELD);
	}

	public boolean receivedDateFieldExists() {
		return hasElement(RECEIVED_DATE_FIELD);
	}

	public boolean receivedTimeFieldExists() {
		return hasElement(RECEPTION_TIME_FIELD);
	}

	public String getCurrentDateRequiredClass() {
		return findElement(REQUIRED_CURRENT_DATE).getAttribute("class");
	}

	public String getReceivedDateRequiredClass() {
		return findElement(REQUIRED_RECEIVED_DATE).getAttribute("class");
	}

	public void enterReceivedDate(String date) {
		setText(RECEIVED_DATE_FIELD, date);
		clickOn(RECEPTION_TIME_FIELD);
	}

	public String getReceivedDateClass() {
		return getReceivedDateField().getAttribute("class");
	}

}
