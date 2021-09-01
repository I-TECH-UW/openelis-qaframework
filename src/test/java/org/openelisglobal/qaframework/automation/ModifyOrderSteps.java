package org.openelisglobal.qaframework.automation;

import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.ModifyOrderPage;
import org.openelisglobal.qaframework.automation.page.AddOrderPage;
import org.openelisglobal.qaframework.automation.page.AddPatientPage;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.test.TestBase;
import org.openelisglobal.qaframework.automation.test.TestProperties;
import org.openelisglobal.qaframework.automation.utils.Utils;

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
    
    private AddOrderPage addOrderPage;
    
    private AddPatientPage addPatientPage;
    
    protected TestProperties testProperties = TestProperties.instance();
    
    private String ACCESION_NUMBER = "20210000000003760";
    
    private String ACCESION_NUMBER_2 = "20210000000003780";
    
    private String patientInfo = "Patient:  moses, mutesasira  09/02/2019  M  201507D35a0ade566-41ca-43eb-9355-839587b8491a";
    
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
    public void searchPageAppears() throws InterruptedException {
        //initialise data 
        homePage = modifyOrderPage.goToHomePage();
        addOrderPage = homePage.goToAddOrderPage();
        addOrderPage.innitialiseData(ACCESION_NUMBER);
        homePage = addOrderPage.goToHomePage();
        
        addOrderPage = homePage.goToAddOrderPage();
        addOrderPage.innitialiseData(ACCESION_NUMBER_2);
        homePage = addOrderPage.goToHomePage();
        
        addPatientPage = homePage.goToAddEditPatientPage();
        addPatientPage.innitialisePatientData("jimmy", "seruwu", false);
        homePage = addPatientPage.goToHomePage();
        if (addPatientPage.alertPresent()) {
            addPatientPage.acceptAlert();
        }
        
        modifyOrderPage = homePage.goToModifyOrderPage();
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
        assertTrue(modifyOrderPage.hasPatientInfoBar());
        assertEquals(patientInfo, modifyOrderPage.getPatientInfo().trim());
    }
    
    @And("If there is only one patient with that Lab No, the system auto-fills all the info about that patient, bypassing the selection process")
    public void autoFillPatientInfo() {
        assertTrue(modifyOrderPage.containsText("Current Order Number:"));
        assertTrue(modifyOrderPage.containsText(ACCESION_NUMBER));
    }
    
    @And("Patient Information form populates with patient information on the Modify Order Page")
    public void patientInfoFromPopulated() {
        assertEquals("SADDIO", modifyOrderPage.getProviderLastNameValue());
    }
    
    @When("User Pulls up a known order with oder number {string}")
    public void pullUpKnownOrder(String labNumber) {
        modifyOrderPage.enterLabNumber(labNumber);
        modifyOrderPage.clickSearchButton();
    }
    
    @Then("Order appears on screen")
    public void orderAppears() {
        assertTrue(modifyOrderPage.containsText("Current Order Number:"));
        assertTrue(modifyOrderPage.containsText(ACCESION_NUMBER));
    }
    
    @And("Patient information displays correctly on the Modify Oder Page")
    public void patientInfoDisplays() {
        assertTrue(modifyOrderPage.hasPatientInfoBar());
        assertEquals(patientInfo, modifyOrderPage.getPatientInfo().trim());
    }
    
    @And("Current Lab No appears correctly under the Modify Lab No section")
    public void CurrentLabNumberAppearsCorrectly() {
        assertTrue(modifyOrderPage.containsText("Current Order Number:"));
        assertTrue(modifyOrderPage.containsText(ACCESION_NUMBER));
    }
    
    @When("User enters a Lab No {string} with incorrect format, Under Modify Order section, in the New order number")
    public void enterIncorectLabNumber(String labNumber) {
        modifyOrderPage.enterNewLabNumber(labNumber);
    }
    
    @Then("Pop-up message appears saying format is incorrect on the Modify Oder Page")
    public void popUpAppears() throws InterruptedException {
        modifyOrderPage.clickNextVistDate();
        assertTrue(modifyOrderPage.alertPresent());
        assertTrue(modifyOrderPage.getAlertText()
                .contains("Invalid accession number. It is either not formated correctly or it already is in use"));
        modifyOrderPage.acceptAlert();
        assertEquals("error", modifyOrderPage.getNewLabNumberClass().trim());
    }
    
    @When("User enters a new unused Lab No {string} in the correct 9-digit format")
    public void enterCorectLabNumber(String labNumber) {
        modifyOrderPage.enterNewLabNumber(labNumber);
    }
    
    @Then("New order number Field ,accepts correct text")
    public void acceptsText() throws InterruptedException {
        modifyOrderPage.clickNextVistDate();
        assertFalse(modifyOrderPage.alertPresent());
        assertNotEquals("error", modifyOrderPage.getNewLabNumberClass().trim());
    }
    
    @When("User enters a Known used Lab No {string} in the correct 9-digit format")
    public void enterUsedLabNumber(String labNumber) {
        modifyOrderPage.enterNewLabNumber(labNumber);
    }
    
    @Then("Pop-up message informs you that you cannot use an existing order number")
    public void alertAppears() throws InterruptedException {
        modifyOrderPage.clickNextVistDate();
        assertTrue(modifyOrderPage.alertPresent());
        assertTrue(modifyOrderPage.getAlertText().contains("You may not reuse an existing lab number."));
        modifyOrderPage.acceptAlert();
        assertEquals("error", modifyOrderPage.getNewLabNumberClass().trim());
    }
    
    @When("User Enters Order date in incorrect format {string} on the Modify Oder Page")
    public void enterIncorrectDate(String date) {
        modifyOrderPage.enterRequestDate(date);
    }
    
    @Then("Text Box Highlighted in Red if entry is in incorrrect format")
    public void textBoxHighligtedRed() throws InterruptedException {
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
        assertEquals("required error", modifyOrderPage.getRequestDateClass().trim());
    }
    
    @When("User Enters Order date in future on the Modify Oder Page")
    public void entertDateInFuture() {
        modifyOrderPage.enterRequestDate(Utils.getFutureDate());
    }
    
    @Then("Text Box Highlighted in Red and Displays Pop up message alert on the Modify Oder Page")
    public void displayMessageAlertWhenDateIsInFuture() throws InterruptedException {
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
        assertTrue(modifyOrderPage.alertPresent());
        assertTrue(modifyOrderPage.getAlertText().contains("Date may not be in the future"));
        modifyOrderPage.acceptAlert();
        assertEquals("required error", modifyOrderPage.getRequestDateClass().trim());
    }
    
    @When("User Enters Order date in correct format on the Modify Oder Page")
    public void enterCorrectDate() {
        modifyOrderPage.enterRequestDate(Utils.getPastDate());
    }

    @Then("Order Date Field accepts correct Date format")
    public void acceptCorrectOrderDate() throws InterruptedException {
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
        assertFalse(modifyOrderPage.alertPresent());
        assertEquals("required", modifyOrderPage.getRequestDateClass().trim());
    }
    
    @When("User Enters Recieved date in incorrect format {string} on the Modify Oder Page")
    public void enterIncorrectRecievedDate(String date) {
        modifyOrderPage.enterRecievedDate(date);
    }
    
    @Then("Recieved date Text Box Highlighted in Red if entry is in incorrrect format")
    public void recievedDateTextBoxHighligtedRed() throws InterruptedException {
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
        assertEquals("text required error", modifyOrderPage.getRecievedDateClass().trim());
    }
    
    @When("User Enters Recieved date in future on the Modify Oder Page")
    public void entertRecievedDateInFuture() {
        modifyOrderPage.enterRecievedDate(Utils.getFutureDate());
    }
    
    @Then("Recieved date Text Box Highlighted in Red and Displays Pop up message alert on the Modify Oder Page")
    public void displayMessageAlertWhenRecievedDateIsInFuture() throws InterruptedException {
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
        assertTrue(modifyOrderPage.alertPresent());
        assertTrue(modifyOrderPage.getAlertText().contains("Date may not be in the future"));
        modifyOrderPage.acceptAlert();
        assertEquals("text required error", modifyOrderPage.getRecievedDateClass().trim());
    }
    
    @When("User Enters Recieved date in correct format on the Modify Oder Page")
    public void enterCorrectRecievedDate() {
        modifyOrderPage.enterRecievedDate(Utils.getPastDate());
    }
    
    @Then("Recieved date Field accepts correct Date format")
    public void acceptCorrectRecievedDate() throws InterruptedException {
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
        assertFalse(modifyOrderPage.alertPresent());
        assertEquals("text required", modifyOrderPage.getRecievedDateClass().trim());
    }  
}
