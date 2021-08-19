package org.openelisglobal.qaframework.automation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.AddPatientPage;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.test.TestBase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PatientEntrySteps extends TestBase{

    private LoginPage loginPage;

	private HomePage homePage;

	private AddPatientPage addPatientPage;

    @After(RunTest.HOOK.PATIENT_ENTRY)
	public void destroy() {
		quit();
	}
    
	@Before(RunTest.HOOK.PATIENT_ENTRY)
	public void setLoginPage() {
		System.out.println("....Patient Entry......");
		loginPage = new LoginPage(getWebDriver());
	}

	@Given("User Vists Home Page and goes to Add Add|Modify Patient Page")
	public void visitLoginPage() throws Exception {
		homePage = loginPage.goToHomePage();
		addPatientPage = homePage.goToAddEditPatientPage();

		// initialise data
		addPatientPage.innitialisePatientData("jimmy", "seruwu", false);
		homePage = addPatientPage.goToHomePage();
		if (addPatientPage.alertPresent()) {
			addPatientPage.acceptAlert();
		}
		addPatientPage = homePage.goToAddEditPatientPage();

		// cant register patient with accenetd character
		homePage = addPatientPage.goToHomePage();
		addPatientPage = homePage.goToAddEditPatientPage();
		addPatientPage.innitialisePatientData("jimmý", "seruwu", true);
		assertTrue(addPatientPage.containsText(
				"patientProperties.firstName: ValidName invalid name format, possibly illegal character"));
		homePage = addPatientPage.goToHomePage();
		addPatientPage = homePage.goToAddEditPatientPage();
	}
	
	@When("Add|Modify Patient page appears with search field")
	public void AddModifyPageAppears() throws InterruptedException {
		assertTrue(addPatientPage.containsText("Add/Modify Patient"));
		assertTrue(addPatientPage.containsText("Search"));
	}

	@And("Search button deactivated until search criteria is selected and text entered in the text field")
	public void searchButonDisabled(){
		assertTrue(addPatientPage.searchButtonDisabled());
	}

	@And("Search text boxes display correct search criteria")
	public void searchtextBoxesDisplayCorrectSeachCriteria(){
		assertTrue(addPatientPage.containsText("Lab No :"));
		assertTrue(addPatientPage.containsText("Patient ID :"));
		assertTrue(addPatientPage.containsText("Last Name :"));
		assertTrue(addPatientPage.containsText("First Name :"));
		assertTrue(addPatientPage.containsText("Gender:"));
	}

	@When("User enters known last name {string} in text box")
	public void enterLastName(String lastName){
		addPatientPage.enterLastNameSearch(lastName); 
		addPatientPage.clickSearchButton(); 
	}

	@Then("Search by Last name yields all patients with matching last name on Add Patient Page")
	public void searchByLastNameReturnsResults(){
		assertTrue(addPatientPage.containsSeachResult());
		assertTrue(addPatientPage.containsText("Data source"));
		assertTrue(addPatientPage.containsText("Last Name"));
		assertTrue(addPatientPage.containsText("First Name"));
		assertTrue(addPatientPage.containsText("Gender"));
		assertTrue(addPatientPage.containsText("Date of Birth"));
		assertTrue(addPatientPage.containsText("Subject Number"));
		assertTrue(addPatientPage.containsText("National ID"));
	}


	@When("User enters known first name {string} in text box")
	public void enterFirstName(String firstName) throws InterruptedException{
		addPatientPage.refreshPage();
		if(addPatientPage.alertPresent()){
			addPatientPage.acceptAlert();
		}
		addPatientPage.enterFirstNameSearch(firstName); 
		addPatientPage.clickSearchButton(); 
	}

	@Then("Search by First name yields all patients with matching first name on Add Patient Page")
	public void searchByFirstNameReturnsResults(){
		assertTrue(addPatientPage.containsSeachResult());
		assertTrue(addPatientPage.containsText("Data source"));
		assertTrue(addPatientPage.containsText("Last Name"));
		assertTrue(addPatientPage.containsText("First Name"));
		assertTrue(addPatientPage.containsText("Gender"));
		assertTrue(addPatientPage.containsText("Date of Birth"));
		assertTrue(addPatientPage.containsText("Subject Number"));
		assertTrue(addPatientPage.containsText("National ID"));
	}

	@When("User enters known last name {string} and first name {string}")
	public void enterFirstAndLastName(String lastName ,String firstName) throws InterruptedException{
		addPatientPage.refreshPage();
		if(addPatientPage.alertPresent()){
			addPatientPage.acceptAlert();
		}
		addPatientPage.enterLastNameSearch(lastName); 
		addPatientPage.enterFirstNameSearch(firstName); 
		addPatientPage.clickSearchButton(); 
	}

	@Then("Search by Last name and First name yields results for known matching names")
	public void searchByFirstAndLastNameReturnsResults(){
		assertTrue(addPatientPage.containsSeachResult());
		assertTrue(addPatientPage.containsText("Data source"));
		assertTrue(addPatientPage.containsText("Last Name"));
		assertTrue(addPatientPage.containsText("First Name"));
		assertTrue(addPatientPage.containsText("Gender"));
		assertTrue(addPatientPage.containsText("Date of Birth"));
		assertTrue(addPatientPage.containsText("Subject Number"));
		assertTrue(addPatientPage.containsText("National ID"));
	}

	@When("User enters known Subject Number {string}")
	public void enterSubjectNumber(String subjectNumber) throws InterruptedException{
		addPatientPage.refreshPage();
		if(addPatientPage.alertPresent()){
			addPatientPage.acceptAlert();
		}
		addPatientPage.enterPatientIdSearch(subjectNumber);
		addPatientPage.clickSearchButton(); 
	}

	@Then("Search by Subject Number yields results for known matching names")
	public void searchBySubjectNumberReturnsResults(){
		assertTrue(addPatientPage.containsSeachResult());
		assertTrue(addPatientPage.containsText("Data source"));
		assertTrue(addPatientPage.containsText("Last Name"));
		assertTrue(addPatientPage.containsText("First Name"));
		assertTrue(addPatientPage.containsText("Gender"));
		assertTrue(addPatientPage.containsText("Date of Birth"));
		assertTrue(addPatientPage.containsText("Subject Number"));
		assertTrue(addPatientPage.containsText("National ID"));
	}

	@When("User enters known Lab Number {string}")
	public void enterLabNumber(String accesionNumber) throws InterruptedException{
		addPatientPage.refreshPage();
		if(addPatientPage.alertPresent()){
			addPatientPage.acceptAlert();
		}
		addPatientPage.enterLabNumberSearch(accesionNumber);
		addPatientPage.clickSearchButton(); 
	}

	@Then("Search by Lab Number yields results for known matching names")
	public void searchByLabNumberReturnsResults(){
		assertTrue(addPatientPage.containsSeachResult());
		assertTrue(addPatientPage.containsText("Data source"));
		assertTrue(addPatientPage.containsText("Last Name"));
		assertTrue(addPatientPage.containsText("First Name"));
		assertTrue(addPatientPage.containsText("Gender"));
		assertTrue(addPatientPage.containsText("Date of Birth"));
		assertTrue(addPatientPage.containsText("Subject Number"));
		assertTrue(addPatientPage.containsText("National ID"));
	}
}
