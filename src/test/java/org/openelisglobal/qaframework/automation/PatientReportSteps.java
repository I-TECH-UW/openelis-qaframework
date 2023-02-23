package org.openelisglobal.qaframework.automation;

import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.AddOrderPage;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.page.ModifyOrderPage;
import org.openelisglobal.qaframework.automation.page.PatientStatusReportPage;
import org.openelisglobal.qaframework.automation.page.ResultValidationByOderPage;
import org.openelisglobal.qaframework.automation.page.SearchResultsByOrderPage;
import org.openelisglobal.qaframework.automation.test.TestBase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PatientReportSteps extends TestBase {
	
	private LoginPage loginPage;
	
	private HomePage homePage;
	
	private AddOrderPage addOrderPage;
	
	private PatientStatusReportPage patientStatusReportPage;
	
	private ModifyOrderPage modifyOrderPage;
	
	private String ACCESION_WITHOUT_RESULT = "20210000000003712";
	
	private String ACCESION_WITH_RESULT = "20210000000003750";
	
	private SearchResultsByOrderPage searchByOrderPage;
	
	private ResultValidationByOderPage resultValidationByOderPage;
	
	@After(RunTest.HOOK.REPORT)
	public void destroy() {
		quit();
	}
	
	@Before(RunTest.HOOK.REPORT)
	public void setLoginPage() {
		System.out.println("....Patient Reports......");
		loginPage = new LoginPage(getWebDriver());
	}
	
	@Given("User Vists Home Page and goes to Add Order Page")
	public void visitLoginPage() throws Exception {
		homePage = loginPage.goToHomePage();
		addOrderPage = homePage.goToAddOrderPage();
	}
	
	@When("User Enters order on Order Entry page, Complete ALL fields, and does not enter results for this order")
	public void enterOrderWithoutResults() throws InterruptedException {
		addOrderPage.innitialiseData(ACCESION_WITHOUT_RESULT);
		homePage = addOrderPage.goToHomePage();
		addOrderPage = homePage.goToAddOrderPage();
		addOrderPage.innitialiseData(ACCESION_WITH_RESULT);
		homePage = addOrderPage.goToHomePage();
	}
	
	@And("User Generates the Patient Report for this order without Results")
	public void generateReportForOrderWithoutResult() {
		patientStatusReportPage = homePage.goToPatientStatusReportPage();
		patientStatusReportPage.enterLabNumberFrom(ACCESION_WITHOUT_RESULT);
		patientStatusReportPage.clickPrintButton();
	}
	
	@Then("Verify the generated Report")
	public void verifyReport() throws InterruptedException {
		patientStatusReportPage.verifyReportPrinted();
		homePage = patientStatusReportPage.goToHomePage();
	}
	
	@When("User Goes to Modify Order for the same order from Use Case 1 and cancel one test")
	public void modifyOrderAndRemoveOneTestCase() throws InterruptedException {
		modifyOrderPage = homePage.goToModifyOrderPage();
		modifyOrderPage.enterLabNumber(ACCESION_WITH_RESULT);
		modifyOrderPage.clickSearchButton();
		modifyOrderPage.removeOneTest();
		modifyOrderPage.clickSaveButton();
		if (modifyOrderPage.alertPresent()) {
			modifyOrderPage.acceptAlert();
		}
		homePage = modifyOrderPage.goToHomePage();
	}
	
	@And("User Looks up Results Entry for the same order, Complete some but not all results")
	public void addPartialResults() throws InterruptedException {
		searchByOrderPage = homePage.goToSearchResultsByOrder();
		searchByOrderPage.enterAccesionNumber(ACCESION_WITH_RESULT);
		Thread.sleep(1000);
		searchByOrderPage.clickAccesionNumberSearch();
		searchByOrderPage.enterResult3("100");
		searchByOrderPage.clickSaveButton();
		if (searchByOrderPage.alertPresent()) {
			searchByOrderPage.acceptAlert();
		}
		homePage = searchByOrderPage.goToHomePage();
	}
	
	@And("User Validates all results but one for this order, Send the last result back to Retest")
	public void validateResultsForThisOrder() throws InterruptedException {
		resultValidationByOderPage = homePage.goToResultValidationByOrder();
		resultValidationByOderPage.enterAccesionNumber(ACCESION_WITH_RESULT);
		Thread.sleep(1000);
		resultValidationByOderPage.clickAccesionNumberSearch();
		resultValidationByOderPage.checkAccept();
		resultValidationByOderPage.clickSaveButton();
		if (resultValidationByOderPage.alertPresent()) {
			resultValidationByOderPage.acceptAlert();
		}
		homePage = resultValidationByOderPage.goToHomePage();
	}
	
	@And("User Generates the Patient Report for this order with Results")
	public void generateReportForOrderWithResult() {
		patientStatusReportPage = homePage.goToPatientStatusReportPage();
		patientStatusReportPage.enterLabNumberFrom(ACCESION_WITH_RESULT);
		patientStatusReportPage.clickPrintButton();
	}
}
