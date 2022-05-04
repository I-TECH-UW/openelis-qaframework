package org.openelisglobal.qaframework.automation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.AddOrderPage;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.page.ModifyOrderPage;
import org.openelisglobal.qaframework.automation.page.ElectronicOrderPage;
import org.openelisglobal.qaframework.automation.test.TestBase;
import org.openelisglobal.qaframework.automation.utils.Utils;
import org.openqa.selenium.firefox.FirefoxDriver.SystemProperty;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReferralWorkFlowSteps extends TestBase {

    private LoginPage loginPage;

    private HomePage homePage;

    private AddOrderPage addOrderPage;

    private ModifyOrderPage modifyOrderPage;

    private ElectronicOrderPage electronicOrderPage;

    private String existingLabNumber = "20210000000008888";

    private String patientInfo = "Patient:  moses, mutesasira  09/02/2019  M";

    String accesionNumber;

    @After(RunTest.HOOK.REFERAL_WORK_FLOW)
    public void destroy() {
        quit();
    }

    @Before(RunTest.HOOK.REFERAL_WORK_FLOW)
    public void setLoginPage() {
        System.out.println("....Referal Work Flow......");
        loginPage = new LoginPage(getWebDriver());
    }

    @When("User logs in into the OpenELIS System")
    public void visitLoginPage() throws Exception {
        homePage = loginPage.goToHomePage();
    }

    @Then("User is able to log in")
    public void userCanLogin() {
        assertNotNull(homePage.getLogOutLink());
    }

    @When("User Goes to Order tab--> Add Order")
    public void gotToAddOrder() throws InterruptedException {
        addOrderPage = homePage.goToAddOrderPage();
        addOrderPage.innitialiseData(existingLabNumber);
        homePage = addOrderPage.goToHomePage();
        addOrderPage = homePage.goToAddOrderPage();
    }

    @And("User Completes the Order section of the Order form with accesionNumber {string}")
    public void completeOrderSection(String accesionNumber) throws InterruptedException {
        // addOrderPage.completeOrderSection(accesionNumber);
        addOrderPage.enterAccessionNumber(accesionNumber);
        addOrderPage.clickOnNextVisitDate();
        if (addOrderPage.alertPresent()) {
            addOrderPage.acceptAlert();
        }
        addOrderPage.enterNextVistDate(Utils.getFutureDate());
        addOrderPage.selectSiteNameFromDropDown();
        addOrderPage.enterRequesterLastName("testRequester");
        addOrderPage.enterRequesterTelephone("+23063458788");
        addOrderPage.enterRequesterFax("test_fax");
        addOrderPage.enterRequesterEmail("requester@gmail.com");
        addOrderPage.enterContactTracingIndexName("testTracingName");
        addOrderPage.enterContactTracingIndexRecordNumber("22222");
        this.accesionNumber = accesionNumber;
    }

    @Then("Lab number {string} can be entered|scanned successfully")
    public void labNumberCanBeEntered(String labNumber) {
        assertEquals(addOrderPage.getAccesionNumberValue(), labNumber);
    }

    @Then("Lab Number field will not accept incorrect format {string}")
    public void enterIncorrectLabNumber(String labNumber) throws InterruptedException {
        // addOrderPage.turnOnAcessionValidation();
        addOrderPage.enterAccessionNumber(labNumber);
        addOrderPage.clickOnNextVisitDate();
        Thread.sleep(1000);
        addOrderPage.acceptAlert();
        assertEquals(addOrderPage.getAccesionNumberClass(), "text error");
        addOrderPage.enterAccessionNumber(this.accesionNumber);
        addOrderPage.clickOnNextVisitDate();
        Thread.sleep(1000);
        if (addOrderPage.alertPresent()) {
            addOrderPage.acceptAlert();
            addOrderPage.clickGenerateButton();
        }
        System.setProperty("labNumber", addOrderPage.getAccesionNumberValue());
    }

    @When("User Completes the Sample section of the Order form")
    public void completeSampleSection() throws InterruptedException {
        addOrderPage.clickAddSampleButton();
        addOrderPage.selectSampleTypeFromDropDownMenu();
        addOrderPage.clickPannelCheckBox();
        addOrderPage.clickTestCheckBox();
        addOrderPage.selectSampleConditionFromDropDownMenu();
        addOrderPage.enterCollectionDate(Utils.getPastDate());
        addOrderPage.enterCollectionTime("12:12");
        addOrderPage.enterCollector("testCollector");
    }

    @Then("Sample Type list displays all needed options")
    public void sampleTypeListDisplaysAllOptions() {
        assertTrue(addOrderPage.sampleTypesDropDownMenuContainsSampleTypesOptions());
    }

    @And("Panels and Tests appear correctly")
    public void pannelAndTestssAppearCorrectly() {
        assertTrue(addOrderPage.testCheckBoxExists());
    }

    @When("User Completes the Referral sub-section under Sample section of the Order form, with referral Institute {string}")
    public void completeReferralSection(String institute) throws InterruptedException {
        addOrderPage.clickReferrerTest();
        addOrderPage.selectReferralInstitute(institute);
        addOrderPage.selectreferralTestName();
    }

    @Then("Referral details fields appear when the Refer test to a reference lab box is ticked")
    public void displayReferralDetails() {
        assertTrue(addOrderPage.containsText("Referral Request"));
    }

    @And("Reasons for Referral shows default value 'Test not performed'")
    public void defaultSelectedReason() {
        assertEquals(addOrderPage.getDefaultReferralReason(), "Test not performed");
        addOrderPage.selectReasonForReferal("Confirmation requested");
    }

    @And("Reasons for Referral list appears in alphabetical order")
    public void reasonForReferalListDIsplay() {
        assertTrue(addOrderPage.reasonsForReferralListDisplay());
    }

    @And("Referrer field is autofilled with name of logged in user")
    public void referalFieldAutoFilledWithNameOfLogedInUser() {
        assertEquals(addOrderPage.getRefererName().trim(), "itech itech");
        // addOrderPage.enterReferer("test_referrer");
    }

    @And("Laboratory names appear in alphabetical order under Institute")
    public void labartoryNamesDisplay() {
        assertTrue(addOrderPage.institutesForReferralDisplay());
    }

    @And("Sent Date is autofilled with current date")
    public void sentDateIsAutoFilled() {
        assertNotNull(addOrderPage.getReferralSentDate());
        assertEquals(addOrderPage.getReferralSentDate().trim(), Utils.getCurrentDate());
    }

    @And("Sent Date can be modified")
    public void sentDateCanBeModifued() {
        String sentDate = "16/09/2021";
        addOrderPage.enterReferralSentDate(sentDate);
        assertEquals(addOrderPage.getReferralSentDate().trim(), sentDate);
    }

    @And("Test Name defaults to original test requested for sample above")
    public void testNameDefaultsToOriginalTest() {
        assertTrue(addOrderPage.getTestValue().contains(addOrderPage.getSelectedTestName()));
    }

    @When("User Enters search parameter Patient ID {string} for a known patient and click Search")
    public void searchByPatientId(String patientId) throws InterruptedException {
        addOrderPage.enterPatientIdSearch(patientId);
        addOrderPage.searchPatient();
    }

    @When("User Enters search parameter Last Name {string} for a known patient and click Search")
    public void searchByLastName(String lastName) throws InterruptedException {
        addOrderPage.clickNewPatientButton();
        addOrderPage.enterLastNameSearch(lastName);
        addOrderPage.searchPatient();
    }

    @When("User Enters search parameter First Name {string} for a known patient and click Search")
    public void searchByFirstName(String firstName) throws InterruptedException {
        addOrderPage.clickNewPatientButton();
        addOrderPage.enterFirstNameSearch(firstName);
        addOrderPage.searchPatient();
    }

    @When("User Enter search parameter [Previous] Lab Number {string} for a known patient and click Search")
    public void searchByLabNumber(String accesionNumber) throws InterruptedException {
        Thread.sleep(2000);
        addOrderPage.clickNewPatientButton();
        addOrderPage.enterLabNumberSearch(accesionNumber);
        addOrderPage.clearFirstNameSearch();
        addOrderPage.clearLastNameSearch();
        addOrderPage.clearPatientIdSearch();
        addOrderPage.searchPatient();
    }

    @Then("Patient search shows green wheel while searching internally ,OE database")
    public void loaderGifShowsGreen() {
        assertEquals(addOrderPage.getLoaderGifClass(), "fa-2x local-search");
    }

    @And("Patient details appear in search results table")
    public void searchByLastNameYieldsResults() {
        addOrderPage.waitForSeachResult();
        assertTrue(addOrderPage.containsSeachResult());
        assertPageContainsPatientResults(addOrderPage);
    }

    @And("If correct patient is selected, their information populates the Patient section of the order form")
    public void populatePatientSection() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(addOrderPage.getPatientSubjectNumber().contains("201807D9P"));
        assertTrue(addOrderPage.getPatientNationalId().contains("201507D35"));
        assertTrue(addOrderPage.getPatientFirstName().contains("mutesasira"));
        assertTrue(addOrderPage.getPatientLastName().contains("moses"));
        assertTrue(addOrderPage.getPatientEmail().contains("email@gmail.com"));
    }

    @When("User Reviews and completes the  Patient Information section")
    public void reviewAndcompletePatientInfo() throws InterruptedException {
        Thread.sleep(1000);
        assertTrue(addOrderPage.getPatientSubjectNumber().contains("201807D9P"));
        assertTrue(addOrderPage.getPatientNationalId().contains("201507D35"));
        assertTrue(addOrderPage.getPatientFirstName().contains("mutesasira"));
        assertTrue(addOrderPage.getPatientLastName().contains("moses"));
        assertTrue(addOrderPage.getPatientEmail().contains("email@gmail.com"));
    }

    @Then("Save button is activated ,if all required fields are filled")
    public void saveButtonActivated() throws InterruptedException {
        addOrderPage.clickReferrerTest();
        addOrderPage.clickReferrerTest();
        assertFalse(addOrderPage.saveButtonDeactivated());
    }

    @And("User Ticks the boxes for Patient notification by Email and SMS")
    public void checkPatientNotification() throws InterruptedException {
        Thread.sleep(2000);
        addOrderPage.checkPatientEmailandSmsNotification();
    }

    @And("User Ticks the boxes for Requester notification by Email and SMS")
    public void checkRequesterNotification() throws InterruptedException {
        Thread.sleep(2000);
        addOrderPage.checkRequesterEmailandSmsNotification();
    }

    @When("User Clicks Save to save form on Add Order Page")
    public void clickSave() throws InterruptedException {
        addOrderPage.clickSave();
    }

    @And("Message 'Save Was Successful' appears at the top of the page")
    public void succesMessageAppears() throws InterruptedException {
        Thread.sleep(1000);
        assertTrue(addOrderPage.containsText("Save was successful"));
    }

    @When("User Clicks 'Print labels' Button")
    public void clickPrintLabels() {
        addOrderPage.clickPrintlabel();
    }

    @Then("User is able to print barcode label that matches all order information")
    public void printLabel() {
        addOrderPage.verifyReportPrinted();
    }

    @When("User Go to Order tab --> Modify Order")
    public void goToModifyOrder() {
        homePage = addOrderPage.goToHomePage();
        modifyOrderPage = homePage.goToModifyOrderPage();
    }

    @And("User Enters lab number {string} from test order")
    public void enterLabNumber(String labNumber) {
        modifyOrderPage.enterLabNumber(labNumber);
        modifyOrderPage.clickSearchButton();
    }

    @Then("Order details are correct")
    public void verifyPatientDetails() {
        assertTrue(modifyOrderPage.hasPatientInfoBar());
        assertTrue(modifyOrderPage.getPatientInfo().trim().contains(patientInfo));
    }

    @When("User logs in into the referral OpenELIS System")
    public void LoginToRefferalLab() throws InterruptedException {
        homePage = loginPage.goToReferralHomePage();
    }

    @Then("User is able to log in into the referral OpenELIS System")
    public void userLoginsInToReferralSystem() {
        assertNotNull(homePage.getLogOutLink());
    }

    @When("User Goes to Order tab --> Electronic Orders")
    public void goToElectronicOrder() {
        electronicOrderPage = homePage.goToElectronicOrderPage();
    }

    @And("User Enters lab number {string} in Search Test Requests, and Click Search")
    public void enterLabNumberSearch(String labNo) {
        // System.getProperty("labNumber")
        electronicOrderPage.enterSearchText(labNo);
    }

    @Then("Order details appear in the table")
    public void getElectronocOrders() throws InterruptedException {
       // assertTrue(electronicOrderPage.hasSentOrders());
    }

    @When("User Enters Patient Last Name {string} in Search Test Requests, and Click Search")
    public void enterPatientLastName(String name) {
        electronicOrderPage.enterSearchText(name);
    }

    @When("User Enters Patient First Name {string} in Search Test Requests, and Click Search")
    public void enterPatientFirstName(String name) {
        electronicOrderPage.enterSearchText(name);
    }

    @When("User Enters Patient Id {string} in Search Test Requests, and Click Search")
    public void enterPatientIdName(String id) {
        electronicOrderPage.enterSearchText(id);
    }
}
