package org.openelisglobal.qaframework.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This class represents the Home page
 */
public class HomePage extends Page {
	
	private static final String PATH_HOME = "/Dashboard.do";
	
	private static final String PATH_RESULT_ENTRY_CONFIG = "ResultConfiguration.do?ID=43&startingRecNo=1";
	
	private static final By FIRST_LEVEL_MENU_ORDER = By.id("menu_sample");
	
	private static final By SECOND_LEVEL_MENU_ADD_ORDER = By.id("menu_sample_add");
	
	private static final By FIRST_LEVEL_MENU_RESULTS = By.id("menu_results");
	
	private static final By SECOND_LEVEL_MENU_RESULTS_ENTER_BY_UNIT = By.id("menu_results_logbook");
	
	private static final By SECOND_LEVEL_MENU_RESULTS_SEARCH = By.id("menu_results_search");
	
	private static final By THIRD_LEVEL_MENU_RESULTS_SEARCH_PATIENT = By.id("menu_results_patient");
	
	private static final By THIRD_LEVEL_MENU_RESULTS_SEARCH_ORDER = By.id("menu_results_accession");
	
	private static final By THIRD_LEVEL_MENU_RESULTS_SEARCH_STATUS = By.id("menu_results_status");
	
	private static final By RADIO_BUTTON_VALIDATE_TRUE = By.id("value1");
	
	private static final By BUTTON_SAVE_VALIDATION = By
	        .xpath("//*[@id='mainForm']/table/tbody/tr[5]/td/center/table/tbody/tr/td[1]/button");
	
	private static final By FIRST_LEVEL_MENU_VALIDATION = By.id("menu_resultvalidation");
	
	private static final By SECOND_LEVEL_MENU_VALIDATION_ROUTINE = By.id("menu_resultvalidation_routine");
	
	private static final By FIRST_LEVEL_MENU_REPORTS = By.id("menu_reports");
	
	private static final By SECOND_LEVEL_MENU_REPORTS_ROUTINE = By.id("menu_reports_routine");
	
	private static final By THIRD_LEVEL_MENU_REPORTS_ROUTINE_STATUS_REPORT = By.id("menu_reports_status_patient");
	
	public HomePage(Page page) {
		super(page);
	}
	
	@Override
	public String getPageUrl() {
		return PATH_HOME;
	}
	
	public WebElement getLogOutLink() {
		return findElement(By.id("logout-form"));
	}
	
	public WebElement getFirstLevelMenu() {
		return findElement(FIRST_LEVEL_MENU_ORDER);
	}
	
	public WebElement getSecondLevelMenu() {
		return findElement(SECOND_LEVEL_MENU_ADD_ORDER);
	}
	
	public AddOrderPage goToAddOrderPage() {
		hoverOn(FIRST_LEVEL_MENU_ORDER);
		getSecondLevelMenu().click();
		return new AddOrderPage(this);
	}
	
	public ResultsUnitTypePage selectsResultAndClickEnterByUnit() {
		hoverOn(FIRST_LEVEL_MENU_RESULTS);
		clickOn(SECOND_LEVEL_MENU_RESULTS_ENTER_BY_UNIT);
		return new ResultsUnitTypePage(this);
	}
	
	public SearchResultsByPatientPage goToSearchResultsByPatient() {
		hoverOn(FIRST_LEVEL_MENU_RESULTS);
		hoverOn(SECOND_LEVEL_MENU_RESULTS_SEARCH);
		clickOn(THIRD_LEVEL_MENU_RESULTS_SEARCH_PATIENT);
		return new SearchResultsByPatientPage(this);
	}
	
	public SearchResultsByOrderPage goToSearchResultsByOrder() {
		hoverOn(FIRST_LEVEL_MENU_RESULTS);
		hoverOn(SECOND_LEVEL_MENU_RESULTS_SEARCH);
		clickOn(THIRD_LEVEL_MENU_RESULTS_SEARCH_ORDER);
		return new SearchResultsByOrderPage(this);
	}
	
	public SearchResultsByStatusPage goToSearchResultsByStatus() {
		hoverOn(FIRST_LEVEL_MENU_RESULTS);
		hoverOn(SECOND_LEVEL_MENU_RESULTS_SEARCH);
		clickOn(THIRD_LEVEL_MENU_RESULTS_SEARCH_STATUS);
		return new SearchResultsByStatusPage(this);
	}
	
	public ResultValidationPage goToResultValidation() {
		hoverOn(FIRST_LEVEL_MENU_VALIDATION);
		clickOn(SECOND_LEVEL_MENU_VALIDATION_ROUTINE);
		return new ResultValidationPage(this);
	}
	
	public PatientStatusReportPage goToPatientStatusReportPage() {
		hoverOn(FIRST_LEVEL_MENU_REPORTS);
		hoverOn(SECOND_LEVEL_MENU_REPORTS_ROUTINE);
		clickOn(THIRD_LEVEL_MENU_REPORTS_ROUTINE_STATUS_REPORT);
		return new PatientStatusReportPage(this);
	}
	
	public void turnOnResultsEntryValidation() {
		goToPage(PATH_RESULT_ENTRY_CONFIG);
		clickOn(RADIO_BUTTON_VALIDATE_TRUE);
		clickOn(BUTTON_SAVE_VALIDATION);
		this.go();
	}
}
