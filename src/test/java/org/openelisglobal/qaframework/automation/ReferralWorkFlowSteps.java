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
    public void gotToAddOrder() {
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
}
