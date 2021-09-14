package org.openelisglobal.qaframework.automation;

import org.openelisglobal.qaframework.RunTest;
import org.openelisglobal.qaframework.automation.page.AddOrderPage;
import org.openelisglobal.qaframework.automation.page.HomePage;
import org.openelisglobal.qaframework.automation.page.LoginPage;
import org.openelisglobal.qaframework.automation.test.TestBase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class ReferralWorkFlowSteps extends TestBase {
    
    private LoginPage loginPage;
    
    private HomePage homePage;
    
    private AddOrderPage addOrderPage;
    
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
    
    @When("User Goes to Order tab--> Add Order")
    public void gotToAddOrder() {
        addOrderPage = homePage.goToAddOrderPage();
    }
}
