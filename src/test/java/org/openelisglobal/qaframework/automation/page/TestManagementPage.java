package org.openelisglobal.qaframework.automation.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TestManagementPage extends Page {

	private static final String PATH_HOME = "/Dashboard";

	private static final String PAGE_PATH = "/TestManagementConfigMenu";

	private static final By MASTER_LIST_SUB_MENU = By.cssSelector("#masterListsSubMenu li a");

	private static final By TEST_MGT_LINK = By.linkText("Test Management");

	private static final By TestManagementConfigMenu = By.cssSelector("#mainForm ul li .textButton");

	private static final By Rename_Existing_Test_Names = By.xpath("/html/body/table/tbody/tr[3]/td/form/ul[1]/li[1]/input");

	private static final By LAB_TEST_NAMES_TABLE = By.xpath("/html/body/table/tbody/tr[3]/td/table");

	private static By TEST_NAMES = By.cssSelector("table tbody tr td .textButton");

	private static By NAME_ENG_FIELD = By.name("nameEnglish");

	private static By NAME_FR_FIELD = By.name("nameFrench");

	private static By REPORT_NAME_ENG_FIELD = By.name("reportNameEnglish");

	private static By REPORT_NAME_FR_FIELD = By.name("reportNameFrench");

	private static By SAVE_BUTTON = By.xpath("//*[@id=\"editButtons\"]/input[1]");

	private static By ACCEPT_BUTTON = By.xpath("//*[@id=\"confirmationButtons\"]/input[1]");

	private static By REJECT_BUTTON = By.xpath("//*[@id=\"confirmationButtons\"]/input[2]");

	private static By CANCEL_BUTTON = By.xpath("//*[@id=\"editButtons\"]/input[2]");

	private static By FINISH_BUTTON = By.xpath("/html/body/table/tbody/tr[3]/td/input");

	public TestManagementPage(Page parent) {
		super(parent);
	}

	public HomePage goToHomePage() {
		this.goToPage(PATH_HOME);
		return new HomePage(this);
	}

	@Override
	public String getPageUrl() {
		return PAGE_PATH;
	}

	public boolean goToTestManagementConfigMenu() {
		List<WebElement> menuElements = getElementsIfExisting(MASTER_LIST_SUB_MENU);
		for (WebElement e : menuElements) {
			String menuItem = e.getText();
			if (menuItem.equals("Test Management")) {
				clickOn(TEST_MGT_LINK);
				return true;
			}

		}
		return false;
	}

	public List<String> getTestManagementConfigMenuItems() {
		List<String> menuItems = new ArrayList<>();
		List<WebElement> elements = getElementsIfExisting(TestManagementConfigMenu);
		for (WebElement e : elements) {
			String item = e.getAttribute("value");
			menuItems.add(item);
		}
		return menuItems;
	}

	public int getTestManagementConfigMenuItemsCount() {
		return getElementsIfExisting(TestManagementConfigMenu).size();
	}

	public boolean renameExistingTestNamesLinkExists() {
		List<String> menuItems = getTestManagementConfigMenuItems();
		for (String menuItem : menuItems) {
			if (menuItem.equals("Rename existing test names")) {
				return true;
			}
		}
		return false;
	}

	public void clickRenameExistingTestNamesLink() {
		clickOn(Rename_Existing_Test_Names);
	}

	public boolean labTestNamesAppearInATable() {
		WebElement table = getTestNamesTable();
		return table.isDisplayed();
	}

	private WebElement getTestNamesTable() {
		return findElement(LAB_TEST_NAMES_TABLE);
	}

	public void clickOnTestTestName(String testName) {
		List<WebElement> elements = getElementsIfExisting(TEST_NAMES);
		for (WebElement e : elements) {
			String test = e.getAttribute("value");
			if (test.equals(testName)) {
				e.click();
			}
		}
	}

	public void enterTestNameInEnglish(String testNameEng) {
		setText(NAME_ENG_FIELD, testNameEng);
	}

	public void enterTestNameInFrench(String testNameFR) {
		setText(NAME_FR_FIELD, testNameFR);
	}

	public void enterReportingTestNameInEnglish(String reportTestNameEng) {
		setText(REPORT_NAME_ENG_FIELD, reportTestNameEng);
	}

	public void enterReportingTestNameInFrench(String reportTestNameFR) {
		setText(REPORT_NAME_FR_FIELD, reportTestNameFR);
	}

	public void clickSaveButton() {
		clickOn(SAVE_BUTTON);
	}

	public void clickAcceptButton() {
		clickOn(ACCEPT_BUTTON);
	}

	public void clickRejectButton() {
		clickOn(REJECT_BUTTON);
	}

	public void clickCancelButton() {
		clickOn(CANCEL_BUTTON);
	}

	public void clickFinishButton() {
		scrollPageByElement(FINISH_BUTTON);
		clickOn(FINISH_BUTTON);
	}

}
