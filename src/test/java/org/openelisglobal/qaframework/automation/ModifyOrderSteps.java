package org.openelisglobal.qaframework.automation;

import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.ModifyOrderPage;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.test.TestBase;
import org.openelisglobal.qaframework.automation.test.TestProperties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ModifyOrderSteps extends TestBase {
    
    private LoginPage loginPage;
    
    private HomePage homePage;
    
    private ModifyOrderPage modifyOrderPage;
    
    protected TestProperties testProperties = TestProperties.instance();
    
    @After(RunTest.HOOK.MODIFY_ORDER)
    public void destroy() {
        quit();
    }
    
    @Before(RunTest.HOOK.MODIFY_ORDER)
    public void setLoginPage() {
        System.out.println("....Modify Order......");
        loginPage = new LoginPage(getWebDriver());
    }
    
    @Given("User Logs in to Home Page and goes to Modify Order Page")
    public void visitLoginPage() throws Exception {
        homePage = loginPage.goToHomePage();
        modifyOrderPage = homePage.goToModifyOrderPage();
    }
    
    @Then("Search appears at top of page")
    public void searchPageAppears() {
        assertTrue(modifyOrderPage.containsText("Modify Order"));
    }
    
    @And("Search button deactivated until text is entered in a search field")
    public void searchButtonDeactivated() {
        assertTrue(modifyOrderPage.searchButtonIsDeactivated());
    }
    
    @And("Search text boxes display correct search criteria on the Modify Order Page")
    public void searchTextBoxDisplayCorrectSearchCriteria() {
        assertTrue(modifyOrderPage.containsText("Lab No :"));
        assertTrue(modifyOrderPage.containsText("Patient ID :"));
        assertTrue(modifyOrderPage.containsText("Last Name :"));
        assertTrue(modifyOrderPage.containsText("First Name :"));
        assertTrue(modifyOrderPage.containsText("Gender:"));
    }
    
    @When("User Enters known last name {string} in Last name Search field on the Modify Order Page")
    public void enterKnownLastName(String lastName) {
        modifyOrderPage.enterLastNameSearch(lastName);
        modifyOrderPage.clickSearchButton();
    }
    
    @Then("Search by Last name yields results for all patients with matching last name on the Modify Order Page")
    public void searchByLastNameYieldsResults() {
        assertTrue(modifyOrderPage.containsSeachResult());
        assertTrue(modifyOrderPage.containsText("Data source"));
        assertTrue(modifyOrderPage.containsText("Last Name"));
        assertTrue(modifyOrderPage.containsText("First Name"));
        assertTrue(modifyOrderPage.containsText("Gender"));
        assertTrue(modifyOrderPage.containsText("Date of Birth"));
        assertTrue(modifyOrderPage.containsText("Subject Number"));
        assertTrue(modifyOrderPage.containsText("National ID"));
    }
    
    @When("User Enters known first name {string} in First name Search field on the Modify Order Page")
    public void enterKnownFirstName(String firstName) {
        modifyOrderPage.refreshPage();
        modifyOrderPage.enterFirstNameSearch(firstName);
        modifyOrderPage.clickSearchButton();
    }
    
    @Then("Search by First name yields results for all patients with matching first name on the Modify Order Page")
    public void searchByFirstNameYieldsResults() {
        assertTrue(modifyOrderPage.containsSeachResult());
        assertTrue(modifyOrderPage.containsText("Data source"));
        assertTrue(modifyOrderPage.containsText("Last Name"));
        assertTrue(modifyOrderPage.containsText("First Name"));
        assertTrue(modifyOrderPage.containsText("Gender"));
        assertTrue(modifyOrderPage.containsText("Date of Birth"));
        assertTrue(modifyOrderPage.containsText("Subject Number"));
        assertTrue(modifyOrderPage.containsText("National ID"));
    }
    
    @When("User Enters known Patient ID {string} in Patient ID search field on the Modify Order Page")
    public void enterKnownPatientId(String patientId) {
        modifyOrderPage.refreshPage();
        modifyOrderPage.enterPatientIdSearch(patientId);
        modifyOrderPage.clickSearchButton();
    }
    
    @Then("Search by Patient ID yields results for all patients with matching Patient ID on the Modify Order Page")
    public void searchByPatientIdYieldsResults() {
        assertTrue(modifyOrderPage.containsSeachResult());
        assertTrue(modifyOrderPage.containsText("Data source"));
        assertTrue(modifyOrderPage.containsText("Last Name"));
        assertTrue(modifyOrderPage.containsText("First Name"));
        assertTrue(modifyOrderPage.containsText("Gender"));
        assertTrue(modifyOrderPage.containsText("Date of Birth"));
        assertTrue(modifyOrderPage.containsText("Subject Number"));
        assertTrue(modifyOrderPage.containsText("National ID"));
    }
    
    @When("User Enters known Lab Number {string} in Lab No. search on the Modify Order Page")
    public void enterKnownLabNo(String labNumber) {
        modifyOrderPage.refreshPage();
        modifyOrderPage.enterLabNumber(labNumber);
        modifyOrderPage.clickSearchButton();
    }
    
    @Then("Search by Lab Number yields results for all patients with matching Lab Number on the Modify Order Page")
    public void searchByLabNumberYieldsResults() {
        assertTrue(modifyOrderPage.containsText("Current Order Number:"));
        assertTrue(modifyOrderPage.containsText("20210000000002250"));
    }
    
    @And("If there is only one patient with that Lab No, the system auto-fills all the info about that patient, bypassing the selection process")
    public void autoFillPatientInfo() {
        assertTrue(modifyOrderPage.containsText("Current Order Number:"));
        assertTrue(modifyOrderPage.containsText("20210000000002250"));
    }
    
    @And("Patient Information form populates with patient information on the Modify Order Page")
    public void patientInfoFromPopulated() {
        assertEquals("SADDIO", modifyOrderPage.getProviderLastNameValue());
    }
}
