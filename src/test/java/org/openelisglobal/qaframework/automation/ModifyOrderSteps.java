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
    
    @And("Search button deactivated until search criteria selected and text is entered")
    public void searchButtonDeactivated() {
        assertTrue(modifyOrderPage.searchButtonIsDeactivated());
    }

    @And("Search text boxes display correct search criteria on the Modify Order Page") 
    public void searchTextBoxDisplayCorrectSearchCriteria(){
        assertTrue(modifyOrderPage.containsText("Lab No :"));
		assertTrue(modifyOrderPage.containsText("Patient ID :"));
		assertTrue(modifyOrderPage.containsText("Last Name :"));
		assertTrue(modifyOrderPage.containsText("First Name :"));
		assertTrue(modifyOrderPage.containsText("Gender:"));
    }
}
