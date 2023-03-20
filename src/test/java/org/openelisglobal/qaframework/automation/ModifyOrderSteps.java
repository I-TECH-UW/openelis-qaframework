package org.openelisglobal.qaframework.automation;

import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.ModifyOrderPage;
import org.openelisglobal.qaframework.automation.page.AddOrderPage;
import org.openelisglobal.qaframework.automation.page.AddPatientPage;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.page.WorkPlanByTestTypePage;
import org.openelisglobal.qaframework.automation.page.PatientStatusReportPage;
import org.openelisglobal.qaframework.automation.test.TestBase;
import org.openelisglobal.qaframework.automation.test.TestProperties;
import org.openelisglobal.qaframework.automation.utils.Utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
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
    
    private WorkPlanByTestTypePage workPlanByTestTypePage;
    
    private PatientStatusReportPage patientStatusReportPage;
    
    protected TestProperties testProperties = TestProperties.instance();
    
    private String ACCESION_NUMBER = "20231099040004863";
    
    private String ACCESION_NUMBER_2 = "20230000000004719";
    
    private String patientInfo = "Patient:  musa, muranga  09/02/2019  M";
    
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

        if (homePage.alertPresent()){
            homePage.acceptAlert();
        }
        addOrderPage = homePage.goToAddOrderPage();
        addOrderPage.innitialiseData(ACCESION_NUMBER_2);
        homePage = addOrderPage.goToHomePage();

        if (homePage.alertPresent()){
            homePage.acceptAlert();
        }
        addPatientPage = homePage.goToAddEditPatientPage();
        addPatientPage.innitialisePatientData("muranga", "musa", false);
        homePage = addPatientPage.goToHomePage();

        if (homePage.alertPresent()){
            homePage.acceptAlert();
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
        assertPageContainsPatientResults(modifyOrderPage);
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
        assertPageContainsPatientResults(modifyOrderPage);
    }
    
    @When("User Enters known Patient ID {string} in Patient ID search field on the Modify Order Page")
    public void enterKnownPatientId(String patientId) {
        modifyOrderPage.refreshPage();
        modifyOrderPage.enterPatientIdSearch(patientId);
        modifyOrderPage.clickSearchButton();
    }
    
    @Then("Search by Patient ID yields results for all patients with matching Patient ID on the Modify Order Page")
    public void searchByPatientIdYieldsResults() {
        assertTrue(modifyOrderPage.searchByPatientIdResult());
    }
    
    @When("User Enters known Lab Number {string} in Lab No. search on the Modify Order Page")
    public void enterKnownLabNo(String labNumber) throws InterruptedException {
        modifyOrderPage.refreshPage();
        if (modifyOrderPage.alertPresent()){
            modifyOrderPage.acceptAlert();
        }
        modifyOrderPage.enterLabNumber(labNumber);
        modifyOrderPage.clickSearchButton();
        if (modifyOrderPage.alertPresent()){
            modifyOrderPage.acceptAlert();
        }
    }
    
    @Then("Search by Lab Number yields results for all patients with matching Lab Number on the Modify Order Page")
    public void searchByLabNumberYieldsResults() {
        assertTrue(modifyOrderPage.hasPatientInfoBar());
        assertTrue(modifyOrderPage.getPatientInfo().trim().contains(patientInfo));
    }
    
    @And("If there is only one patient with that Lab No, the system auto-fills all the info about that patient, bypassing the selection process")
    public void autoFillPatientInfo() {
        assertTrue(modifyOrderPage.containsText("Current Order Number:"));
        assertTrue(modifyOrderPage.containsText(ACCESION_NUMBER));
    }
    
    @And("Patient Information form populates with patient information on the Modify Order Page")
    public void patientInfoFromPopulated() {
        assertTrue(modifyOrderPage.getPatientInfo().trim().contains(patientInfo));
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
        assertTrue(modifyOrderPage.getPatientInfo().trim().contains(patientInfo));
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
    
    @When("User Enters time {string} in incorrect format on the Modify Oder Page")
    public void enterIncorrectTime(String time) {
        assertNotEquals("", modifyOrderPage.getRecievedTime().trim());
        modifyOrderPage.enterRecievedTime(time);
    }
    
    @Then("Field Rejects non-numeric, additional digits")
    public void fieldRejectIncorrectRecievedtime() throws InterruptedException {
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
        assertEquals("", modifyOrderPage.getRecievedTime().trim());
    }
    
    @When("User Enters time {string} in HHMM format on the Modify Oder Page")
    public void enterTime(String time) {
        modifyOrderPage.enterRecievedTime(time);
    }
    
    @Then("Field Automatically corrects straight numeric to proper HH:MM format")
    public void fieldCorrectsTime() throws InterruptedException {
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
        assertEquals("12:12", modifyOrderPage.getRecievedTime().trim());
    }
    
    @When("User Enters time {string} in HH:MM format  on the Modify Oder Page")
    public void enterCorrectTime(String time) {
        modifyOrderPage.enterRecievedTime(time);
    }
    
    @Then("Field accepts correct time  in HH:MM format")
    public void fieldacceptsCorrectTime() throws InterruptedException {
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
        assertEquals("05:10", modifyOrderPage.getRecievedTime().trim());
    }

    @When("User Enters new site name from text field {string}")
    public void userEntersNewSiteNameFromTextField(String siteName) {
        modifyOrderPage.enterSiteName(siteName);
    }
    
    @Then("Site name and code drop-down list displays previously entered  options correctly and selection can be made")
    public void siteNamesDisplyCorrectly() {
        modifyOrderPage.selectSiteNameFromDropDown();
    }
    
    @Then("Table headers are correct Under Current Tests")
    public void tableHeadersDisplyCorrectly() {
        assertTrue(modifyOrderPage.containsText("Current Tests"));
        assertTrue(modifyOrderPage.containsText("Lab No"));
        assertTrue(modifyOrderPage.containsText("Sample Type"));
        assertTrue(modifyOrderPage.containsText("Collection Date&nbsp;(dd/mm/yyyy)"));
        assertTrue(modifyOrderPage.containsText("Collection Time (hh:mm)"));
        assertTrue(modifyOrderPage.containsText("Remove Samples"));
        assertTrue(modifyOrderPage.containsText("Test Name"));
        assertTrue(modifyOrderPage.containsText("Results Recorded or In Progress"));
        assertTrue(modifyOrderPage.containsText("Delete (Cancel) test"));
        assertTrue(modifyOrderPage.containsText("Available Tests"));
    }
    
    @When("User Enters new Collection Date {string} in incorrect format on the Modify Oder Page")
    public void enterIncorrectCollectionDate(String date) {
        modifyOrderPage.enterCollectionDate(date);
    }
    
    @Then("Collection Date Text Box Highlighted in Red if entry is in incorrrect format")
    public void collectionDateTextBoxHighligtedRed() throws InterruptedException {
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
        assertEquals("text  error", modifyOrderPage.getCollectionDateClass().trim());
    }
    
    @When("User Enters new Collection Date in future on the Modify Oder Page")
    public void enterCollectionDateInFuture() {
        modifyOrderPage.enterCollectionDate(Utils.getFutureDate());
    }
    
    @Then("Alert appears if Collection Date is in future")
    public void alertAppearsIfCollectionDate() throws InterruptedException {
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
        assertTrue(modifyOrderPage.alertPresent());
        assertTrue(modifyOrderPage.getAlertText().contains("Date may not be in the future"));
        modifyOrderPage.acceptAlert();
        assertEquals("text  error", modifyOrderPage.getCollectionDateClass().trim());
    }
    
    @When("User Enters modified date in correct format in Collection Date field")
    public void enterCollectionDateInCorrectFormat() {
        modifyOrderPage.enterCollectionDate(Utils.getPastDate());
    }
    
    @Then("Field accepts correct format; Collection Date can be modified")
    public void collectionDateAcceptsDateInCorrectFormat() throws InterruptedException {
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
        assertFalse(modifyOrderPage.alertPresent());
        assertEquals("text", modifyOrderPage.getCollectionDateClass().trim());
    }
    
    @When("User Enters new Collection Time {string} in incorrect format on the Modify Oder Page")
    public void enterCollectionTimeInInCorrectFormat(String time) {
        modifyOrderPage.enterCollectionTime(time);
    }
    
    @Then("Collection Time Rejects non-numeric entries, additional digits")
    public void collectionTimeFieldRejectIncorrectTime() throws InterruptedException {
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
        assertEquals("", modifyOrderPage.getCollectionTime().trim());
    }
    
    @When("User Enters  Collection Time {string} that doesnt exist on the 12 or 24 hour clock")
    public void enterNonExistingCollectionTime(String time) {
        modifyOrderPage.enterCollectionTime(time);
    }
    
    @Then("Red alert appears if time does not exist on 12 or 24 hour clock")
    public void redAlertAppearsOncollectionTimeField() throws InterruptedException {
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
        assertEquals("text error", modifyOrderPage.getCollectionTimeClass());
    }
    
    @When("User Enters  Collection Time {string} as HHMM on the Modify Oder Page")
    public void enterCollectionTime(String time) {
        modifyOrderPage.enterCollectionTime(time);
    }
    
    @Then("Collection Time Field Automatically corrects straight numeric to proper format HH:MM")
    public void collectionTimeAutomaticallyCorrectsTheTimeToProperFormat() throws InterruptedException {
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
        assertEquals("12:12", modifyOrderPage.getCollectionTime());
    }
    
    @When("User Enters modified collection time  {string} as HH:MM")
    public void enterCollectionTimeinCorrectFormat(String time) {
        modifyOrderPage.enterCollectionTime(time);
    }
    
    @Then("Collection Time Field accepts correct format; collection time can be modified")
    public void collectionTimeAcceptsCorrectTimeFormat() throws InterruptedException {
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
        assertEquals("05:10", modifyOrderPage.getCollectionTime());
    }
    
    @When("User Clicks Remove Samples check box on the Modify Oder Page")
    public void checkRemoveSample() {
        modifyOrderPage.checkRemoveSample();
    }
    
    @Then("Remove Samples Check box sticks on the Modify Oder Page")
    public void removeSampleCheckBoxSticks() {
        assertTrue(modifyOrderPage.removeSampleCheckBoxIsChecked());
    }
    
    @When("User Unchecks Remove Samples check box on the Modify Oder Page")
    public void uncheckRemoveTests() {
        modifyOrderPage.checkRemoveSample();
    }
    
    @Then("Remove Samples Check box is Unselected on the Modify Oder Page")
    public void removeSampleCheckBoxUnckecks() {
        assertFalse(modifyOrderPage.removeSampleCheckBoxIsChecked());
    }
    
    @And("User can View the `Delete test` column")
    public void viewDeleteTestColumn() {
        assertTrue(modifyOrderPage.containsText("Delete (Cancel) test"));
    }
    
    @And("Cancel Test Checkbox activated for user with validation permissions")
    public void deleteTestCheckBoxActivated() {
        assertFalse(modifyOrderPage.deleteTestCheckBoxIsDisabled());
    }
    
    @When("User Clicks Delete test check box on the Modify Oder Page")
    public void checkDeleteTest() {
        modifyOrderPage.clickDeleteTest();
    }
    
    @Then("Delete test Check box sticks on the Modify Oder Page")
    public void deleteTestCheckBoxSticks() {
        assertTrue(modifyOrderPage.deleteTestCheckBoxIsChecked());
    }
    
    @When("User Unchecks Delete test check box on the Modify Oder Page")
    public void uncheckDeleteTest() {
        modifyOrderPage.clickDeleteTest();
    }
    
    @Then("Delete test Check box is Unselected on the Modify Oder Page")
    public void deleteTestCheckBoxIsUnchecked() {
        assertFalse(modifyOrderPage.deleteTestCheckBoxIsChecked());
    }
    
    @When("User Rechecks box Delete test check box on the Modify Oder Page")
    public void recheckDeleteTest() {
        modifyOrderPage.clickDeleteTest();
    }
    
    @Then("Can delete a test within a panel")
    public void canDeleteTest() {
        assertTrue(modifyOrderPage.deleteTestCheckBoxIsChecked());
    }
    
    @When("User Search by lab number {string} from previous testing steps on the Modify Oder Page")
    public void searchBYLabNumber(String labNumber) {
        modifyOrderPage.enterLabNumber(labNumber);
        modifyOrderPage.clickSearchButton();
    }
    
    @Then("Table headers are correct Under Available Tests")
    public void tableHeadersDisplyCorrectlyUnderAvailableTests() {
        assertTrue(modifyOrderPage.containsText("Available Tests"));
        assertTrue(modifyOrderPage.containsText("Lab No"));
        assertTrue(modifyOrderPage.containsText("Sample Type"));
        assertTrue(modifyOrderPage.containsText("Test Name"));
        assertTrue(modifyOrderPage.containsText("Assign"));
    }
    
    @When("User Check box next to  several tests")
    public void clickCheckBoxAssign() {
        modifyOrderPage.clickCheckBoxAssignTest();
    }
    
    @Then("Assign test Check boxes stick")
    public void assignTestCheckBoxSticks() {
        assertTrue(modifyOrderPage.assignTestCheckBoxIsChecked());
    }
    
    @When("User unCheck box next to  several tests")
    public void unCheckBoxAssign() {
        modifyOrderPage.clickCheckBoxAssignTest();
    }
    
    @Then("Assign test Check boxes will uncheck")
    public void assignTestCheckBoxUncheck() {
        assertFalse(modifyOrderPage.assignTestCheckBoxIsChecked());
    }

    @When("User Clicks add new sample button")
    public void userClicksAddNewSampleButton() {
        modifyOrderPage.clickAddNewSampleButton();
    }

    @When("User Click on drop-down Sample Type list on the Modify Oder Page")
    public void clickSampleTypeDropDown() {
        modifyOrderPage.clickSampleTypeDropDown();
    }
    
    @Then("Sample types display in drop-down list on the Modify Oder Page")
    public void sampelTypesExist() {
        assertTrue(modifyOrderPage.sampleTypesExistInDropDown());
    }
    
    @When("User Select any sample type on the Modify Oder Page")
    public void selectSampleType() {
        modifyOrderPage.clickAddNewSampleButton();
        modifyOrderPage.selectSampleType();
    }
    
    @Then("Order information fields for the selected sample type appear. Sample types can be added one by one")
    public void orderFieldsAppear() {
        assertTrue(modifyOrderPage.hasSampleTypeResults());
        assertTrue(modifyOrderPage.containsText("ID"));
        assertTrue(modifyOrderPage.containsText("Sample Type"));
        assertTrue(modifyOrderPage.containsText("Collection Date&nbsp;(dd/mm/yyyy)"));
        assertTrue(modifyOrderPage.containsText("Collection Time (hh:mm)"));
        assertTrue(modifyOrderPage.containsText("Collector"));
        assertTrue(modifyOrderPage.containsText("Tests"));
        assertTrue(modifyOrderPage.containsText("Reject"));
        assertTrue(modifyOrderPage.containsText("Reject reason"));
        assertTrue(modifyOrderPage.containsText("Remove Sample"));
    }
    
    @And("Sample ID added to reflect correct next Sample number")
    public void sampleIdAdded() {
        assertNotNull(modifyOrderPage.getSampleId());
    }
    
    @When("User Select sample Condition from drop-down list on the Modify Oder Page")
    public void selectSampleCondition() {
        modifyOrderPage.selectSampleCondition();
    }
    
    @Then("Multiple sample conditions can be added")
    public void sampelConditionAdded() {
        assertTrue(modifyOrderPage.sampleConditionOptionAdded());
    }
    
    @When("User Clicks `X` beside a condition on the Modify Oder Page")
    public void removeSampleCondition() {
        modifyOrderPage.clickRemoveCondition();
    }
    
    @Then("Added sample conditions can be deleted")
    public void sampelConditionRemoved() {
        assertTrue(modifyOrderPage.sampleConditionOptionRemoved());
    }
    
    @When("User click Remove ,On the far right of the sample")
    public void removeSample() {
        modifyOrderPage.clickRemoveSample();
    }
    
    @Then("Removes sample from order")
    public void sampelRemoved() {
        assertTrue(modifyOrderPage.sampleTypeResultsRemoved());
    }
    
    @When("User Click Remove All ,on the Modify Oder Page")
    public void removeAllSample() {
        modifyOrderPage.clickAddNewSampleButton();
        modifyOrderPage.selectSampleType();
        modifyOrderPage.clickRemoveAllSample();
    }
    
    @Then("Removes all samples from order")
    public void allSampelRemoved() {
        assertTrue(modifyOrderPage.sampleTypeResultsRemoved());
    }
    
    @When("User can Re-add samples")
    public void reAddSample() {
        modifyOrderPage.clickAddNewSampleButton();
        modifyOrderPage.selectSampleTypeAgain();
    }
    
    @When("User Enters Collection Date {string} on the Modify Oder Page")
    public void enterCollectionDate(String date) throws InterruptedException {
        modifyOrderPage.enterCollectionDate(date);
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
    }
    
    @Then("Collection Date Field validates {string} the date format")
    public void fieldValidatesDate(String validation) throws InterruptedException {
        if (validation.trim().equals("Rejects incorect Format not in DD/MM/YYYY")
                | validation.trim().equals("Rejects incorect Format not Numeric")) {
            assertEquals("text  error", modifyOrderPage.getCollectionDateClass().trim());
        } else if (validation.trim().equals("Rejects Future date")) {
            assertTrue(modifyOrderPage.alertPresent());
            assertTrue(modifyOrderPage.getAlertText().contains("Date may not be in the future"));
            modifyOrderPage.acceptAlert();
            assertEquals("text  error", modifyOrderPage.getCollectionDateClass().trim());
        } else if (validation.trim().equals("Accepts correct Format in DD/MM/YYYY")) {
            assertFalse(modifyOrderPage.alertPresent());
            assertEquals("text", modifyOrderPage.getCollectionDateClass().trim());
        }
    }
    
    @When("User Enters Collection Time {string} on the Modify Oder Page")
    public void enterCollectionTimeOnModifyOrderPage(String time) throws InterruptedException {
        modifyOrderPage.enterCollectionTime(time);
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
    }
    
    @Then("Collection Time Field  validates {string} the time format")
    public void fieldValidatesTime(String validation) throws InterruptedException {
        if (validation.trim().equals("Rejects incorect Format ,non numeric")) {
            assertEquals("", modifyOrderPage.getCollectionTime().trim());
        } else if (validation.trim().equals("Rejects time not existing on the  in 12/24 hour clock")) {
            assertEquals("text error", modifyOrderPage.getCollectionTimeClass());
        } else if (validation.trim().equals("Rejects extra digits")) {
            assertEquals("12:12", modifyOrderPage.getCollectionTime().trim());
            assertEquals("text", modifyOrderPage.getCollectionTimeClass());
        } else if (validation.trim().equals("Auto-corrects HHMM format to HH:MM")) {
            assertEquals("11:11", modifyOrderPage.getCollectionTime().trim());
            assertEquals("text", modifyOrderPage.getCollectionTimeClass());
        } else if (validation.trim().equals("Accepts correct Format in HH:MM")) {
            assertEquals("10:10", modifyOrderPage.getCollectionTime().trim());
            assertEquals("text", modifyOrderPage.getCollectionTimeClass());
        }
    }
    
    @Then("Test entry is marked mandatory on the Modify Oder Page")
    public void testsEntryMarkedMandatory() {
        assertEquals("requiredlabel", modifyOrderPage.getTestRequiredLabelClass());
    }
    
    @And("Available Tests check box list appears for each sample type")
    public void availableTestsCheckBoxAppears() {
        assertTrue(modifyOrderPage.containsText("Available Tests"));
        assertTrue(modifyOrderPage.hasTestCheckBoxes());
    }
    
    @When("User Checks check box next to test name on the Modify Oder Page")
    public void selectTestCheckBox() throws InterruptedException {
        assertEquals("", modifyOrderPage.getAddedTests().trim());
        modifyOrderPage.clickTestCheckBox();
        Thread.sleep(1000);
    }
    
    @Then("Checkbox sticks, test name appears in Tests box on the Modify Oder Page")
    public void testCheckBoxSticsk() {
        assertTrue(modifyOrderPage.enterTestCheckBoxIsChecked());
        assertNotEquals("", modifyOrderPage.getAddedTests().trim());
    }
    
    @When("User Unchecks check box next to test name on the Modify Oder Page")
    public void unselectTestCheckBox() throws InterruptedException {
        modifyOrderPage.clickTestCheckBox();
        Thread.sleep(1000);
    }
    
    @Then("Checkbox stays clear; Deselects test ;test name disappears from Tests box")
    public void testCheckBoxDiselected() {
        assertFalse(modifyOrderPage.enterTestCheckBoxIsChecked());
        assertEquals("", modifyOrderPage.getAddedTests().trim());
    }
    
    @When("User Checks check box next to panel name on the Modify Oder Page")
    public void selectPanelCheckBox() throws InterruptedException {
        assertEquals("", modifyOrderPage.getAddedTests().trim());
        modifyOrderPage.clickPanelCheckBox();
        Thread.sleep(1000);
    }
    
    @Then("All panel tests are selected ,checkboxes stick, test names appear in Tests box")
    public void panelCheckBoxSticsk() {
        assertTrue(modifyOrderPage.panelCheckBoxIsChecked());
        assertNotEquals("", modifyOrderPage.getAddedTests().trim());
    }
    
    @When("User unChecks check box next to panel name on the Modify Oder Page")
    public void unselectPanelCheckBox() throws InterruptedException {
        modifyOrderPage.clickPanelCheckBox();
        Thread.sleep(1000);
    }
    
    @Then("All panel tests are diselected ;test name disappears from Tests box")
    public void panelCheckBoxStics() {
        assertFalse(modifyOrderPage.panelCheckBoxIsChecked());
        assertEquals("", modifyOrderPage.getAddedTests().trim());
    }
    
    @When("User Enters text in box Tests on the Modify Oder Page")
    public void enterTestsDirectly() throws InterruptedException {
        try {
            modifyOrderPage.enterTextDirectlyInTestsField("tests");
        }
        catch (Exception e) {
            assertTrue(modifyOrderPage.testsBoxIsReadOnly());
        }
        Thread.sleep(1000);
    }
    
    @Then("Text cannot be added to box Tests on the Modify Oder Page")
    public void checkTextInTestsBox() {
        assertEquals("", modifyOrderPage.getAddedTests().trim());
    }
    
    @When("User Deletes text from box Tests on the Modify Oder Page")
    public void deleteTestsDirectly() throws InterruptedException {
        assertEquals("", modifyOrderPage.getAddedTests().trim());
        modifyOrderPage.clickTestCheckBox();
        try {
            modifyOrderPage.enterTextDirectlyInTestsField("");
        }
        catch (Exception e) {
            assertTrue(modifyOrderPage.testsBoxIsReadOnly());
        }
        Thread.sleep(1000);
    }
    
    @Then("Text cannot be deleted from  Tests box on the Modify Oder Page")
    public void checkIfTextisDeletedFromTestsBox() {
        assertNotEquals("", modifyOrderPage.getAddedTests().trim());
    }
    
    @When("User Leaves mandatory field without data on the Modify Oder Page")
    public void leaveOutMandatoryFields() throws InterruptedException {
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
    }
    
    @Then("Save button deactivated until all mandatory fields are completed on the Modify Oder Page")
    public void saveButtonDeactivated() {
        modifyOrderPage.clearRequestDate();
        modifyOrderPage.clearReceivedDate();
        assertTrue(modifyOrderPage.saveButtonIsDeactivated());
    }
    
    @When("User Completes all mandatory fields on the Modify Oder Page")
    public void completeMandatoryFields() throws InterruptedException {
        modifyOrderPage.enterRequestDate(Utils.getPastDate());
        modifyOrderPage.enterRecievedDate(Utils.getPastDate());
        modifyOrderPage.enterCollectionTime("10:10");
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
    }
    
    @Then("Save button activated when all mandatory fields are completed on the Modify Oder Page")
    public void saveButtonActivated() {
        assertFalse(modifyOrderPage.saveButtonIsDeactivated());
    }
    
    @When("User Clicks Cancel on the Modify Oder Page")
    public void clickCancel() {
        modifyOrderPage.clickCancelButton();
    }
    
    @Then("Pop-up message 'Leave Site? Changes you made may not be saved' appears")
    public void alertAppearsPromptingToLeavePage() throws InterruptedException {
        assertTrue(modifyOrderPage.alertPresent());
    }
    
    @When("User Clicks Cancel to Cancel the Confirmation box")
    public void cancelTheCormationBox() {
        modifyOrderPage.dismissAlert();
    }
    
    @Then("Edit Sample form remains on screen on the Modify Oder Page")
    public void editSampleFormRemainsOnSCreen() throws InterruptedException {
        assertTrue(modifyOrderPage.getPatientInfo().trim().contains(patientInfo));
        assertTrue(modifyOrderPage.containsText("Current Order Number:"));
        assertTrue(modifyOrderPage.containsText(ACCESION_NUMBER));
    }
    
    @When("User User Clicks Save on the Modify Oder Page")
    public void clickSave() throws InterruptedException {
        modifyOrderPage.enterCollectionTime("10:10");
        modifyOrderPage.clickNextVistDate();
        Thread.sleep(1000);
        modifyOrderPage.clickSaveButton();
    }
    
    @Then("Save results in new Edit Sample page and message in green 'Save was successful'")
    public void savesSuccesfull() {
        assertTrue(modifyOrderPage.containsText("Save was successful"));
        assertTrue(modifyOrderPage.printButtonAppears());
    }
    
    @Then("System returns to Returns to home page")
    public void returnToHomePage() {
        assertTrue(modifyOrderPage.containsText("Modify Order"));
    }
    
    @When("User Goes to Workplan > By Test Type")
    public void goToWorkPlanByTestType() {
        homePage = modifyOrderPage.goToHomePage();
        workPlanByTestTypePage = homePage.goToWorkPlanByTestPage();
    }
    
    @And("User Selects Sample type {string} of tests with results")
    public void selectTestType(String testType) {
        workPlanByTestTypePage.selctTestType(testType);
//        assertTrue(workPlanByTestTypePage.containsText(ACCESION_NUMBER));
    }
    
    @And("User Clicks 'Print Workplan'")
    public void printWorkPlan() {
        workPlanByTestTypePage.clickPrintWorkPlan();
    }
    
    @Then("Modified order and sample information correctly appears on appropriate work plan")
    public void informationAppears() {
        workPlanByTestTypePage.verifyReportPrinted();
    }
    
    @When("User Goes to Reports > Routine > Patient Status Report")
    public void goToPatientStatusREport() {
        homePage = workPlanByTestTypePage.goToHomePage();
        patientStatusReportPage = homePage.goToPatientStatusReportPage();
    }
    
    @And("User Enters valid Lab Number {string}")
    public void enterLabNumber(String labNumber) {
        patientStatusReportPage.enterLabNumber(labNumber);
    }
    
    @And("User Clicks 'Generate printable version' for this lab number")
    public void clickGeneratePrint() {
        patientStatusReportPage.clickPrintButton();
    }
    
    @And("Modified order information is correct and tests appear as In Progress on Patient Report")
    public void verifyInformation() {
        patientStatusReportPage.verifyReportPrinted();
    }
}
