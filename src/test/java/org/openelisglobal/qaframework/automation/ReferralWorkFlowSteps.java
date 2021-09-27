package org.openelisglobal.qaframework.automation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.AddOrderPage;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.test.TestBase;
import org.openelisglobal.qaframework.automation.utils.Utils;

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

    private String existingLabNumber = "20210000000008888";

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

    @Given("User logs in into the OpenELIS  System")
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
            return;
        }
        addOrderPage.enterNeaxtVistDate(Utils.getFutureDate());
        addOrderPage.selectSiteNameFromDropDown();
        addOrderPage.enterRequesterLastName("test_requester");
        addOrderPage.enterRequesterTelephone("+23063458788");
        addOrderPage.enterRequesterFax("test_fax");
        addOrderPage.enterRequesterEmail("requester@gmail.com");
        addOrderPage.enterContactTracingIndexName("test_tracingName");
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
    }

    @When("User Completes the Sample section of the Order form")
    public void completeSampleSection() {
        addOrderPage.clickAddSampleButton();
        addOrderPage.selectSampleTypeFromDropDownMenu();
        addOrderPage.clickPannelCheckBox();
        addOrderPage.clickTestCheckBox();
        addOrderPage.selectSampleConditionFromDropDownMenu();
        addOrderPage.enterCollectionDate(Utils.getPastDate());
        addOrderPage.enterCollectionTime("12:12");
        addOrderPage.enterCollector("test_Collector");
    }

    @Then("Sample Type list displays all needed options")
    public void sampleTypeListDisplaysAllOptions() {
        assertTrue(addOrderPage.sampleTypesDropDownMenuContainsSampleTypesOptions());
    }

    @And("Panels and Tests appear correctly")
    public void pannelAndTestssAppearCorrectly() {
        assertTrue(addOrderPage.testCheckBoxExists());
    }

    @When("User Completes the Referral sub-section under Sample section of the Order form")
    public void completeReferralSection() {
        addOrderPage.clickReferrerTest();
        // addOrderPage.selectReasonForReferal("Confirmation requested");
        // addOrderPage.enterReferer("test_referrer");
        // addOrderPage.enterReferralSentDate("16/09/2021");
        addOrderPage.selectreferralInstitute();
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
        addOrderPage.enterReferer("test_referrer");
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

    @When("User Enter search parameter [Previous] Lab Number {string} for a known patient and click Search")
    public void searchByLabNumber(String accesionNumber) throws InterruptedException {
        addOrderPage.enterLabNumberSearch(accesionNumber);
        addOrderPage.searchPatient();
    }

    @Then("Patient search shows green wheel while searching internally ,OE database")
    public void loaderGifShowsGreen() {
        assertEquals(addOrderPage.getLoaderGifClass(), "fa-2x local-search");
    }

    @And("Patient details appear in search results table")
    public void searchByLastNameYieldsResults() {
        assertTrue(addOrderPage.containsSeachResult());
        assertTrue(addOrderPage.containsText("Data source"));
        assertTrue(addOrderPage.containsText("Last Name"));
        assertTrue(addOrderPage.containsText("First Name"));
        assertTrue(addOrderPage.containsText("Gender"));
        assertTrue(addOrderPage.containsText("Date of Birth"));
        assertTrue(addOrderPage.containsText("Subject Number"));
        assertTrue(addOrderPage.containsText("National ID"));
    }

    @And("If correct patient is selected, their information populates the Patient section of the order form")
    public void populatePatientSection() throws InterruptedException {
        Thread.sleep(1000);
        assertTrue(addOrderPage.getPatientSubjectNumber().contains("201807D9P"));
        assertTrue(addOrderPage.getPatientNationalId().contains("201507D35"));
        assertTrue(addOrderPage.getPatientFirstName().contains("mutesasira"));
        assertTrue(addOrderPage.getPatientLastName().contains("moses"));
        assertTrue(addOrderPage.getPatientEmail().contains("email@gmail.com"));
    }

    @When("User Enters search parameter Patient ID {string} for a known patient and click Search")
    public void searchByPatientId(String patientId) throws InterruptedException {
        addOrderPage.enterPatientIdSearch(patientId);
        addOrderPage.searchPatient();
    }

    @When("User Enters search parameter Last Name {string} for a known patient and click Search")
    public void searchByLastName(String lastName) throws InterruptedException {
        addOrderPage.enterLastNameSearch(lastName);
        addOrderPage.searchPatient();
    }

    @When("User Enters search parameter First Name {string} for a known patient and click Search")
    public void searchByFIrstName(String firstName) throws InterruptedException {
        addOrderPage.enterFirstNameSearch(firstName);
        addOrderPage.searchPatient();
    }
}
