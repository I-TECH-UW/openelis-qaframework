package org.openelisglobal.qaframework.automation.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;

public class TestManagementPage extends Page {

	private static final String PATH_HOME = "/Dashboard";

	private static final String PAGE_PATH = "/TestManagementConfigMenu";

	private static final By TestManagementConfigMenu = By.cssSelector("#mainForm ul li .textButton");

	private static final By Rename_Existing_Test_Names = By.xpath("/html/body/table/tbody/tr[3]/td/form/ul[1]/li[1]/input");

	private static final By LAB_TEST_NAMES_TABLE = By.xpath("/html/body/table/tbody/tr[3]/td/table");

	private static final By REQUIRED_TEST_NAME = By.xpath(
			"//*[@id=\"step1Div\"]/table/tbody/tr/td[1]/table/tbody/tr[1]/td/span");

	private static final By REQUIRED_REPORTING_TEST_NAME = By.xpath(
			"//*[@id=\"step1Div\"]/table/tbody/tr/td[1]/table/tbody/tr[5]/td/span");

	private static final By REQUIRED_TEST_SECTION = By.xpath("//*[@id=\"step1Div\"]/table/tbody/tr/td[2]/span");

	private static final By REQUIRED_RESULT_TYPE = By.xpath("//*[@id=\"step1Div\"]/table/tbody/tr/td[4]/span");

	private static final By LOINC_TEXT_FIELD = By.id("loinc");

	private static final By REQUIRED_SAMPLE_TYPE = By.xpath("//*[@id=\"sampleTypeSelectionDiv\"]/span");

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

	private static By ADD_NEW_TESTS_LINK = By.xpath("//*[@id=\"mainForm\"]/ul[2]/li[3]/input");

	private static By TEST_SECTION_DROP_DOWN = By.id("testUnitSelection");

	private static By TEST_ENG_NAME_FIELD = By.id("testNameEnglish");

	private static By TEST_FR_NAME_FIELD = By.id("testNameFrench");

	private static By UNIT_OF_MEASURE_DROPDOWN = By.id("uomSelection");

	private static By PANEL_DROPDOWN = By.id("asmSelect0");

	private static By RESULT_TYPE_DROPDOWN = By.id("resultTypeSelection");

	private static By COPY_TEST_NAME_BUTTON = By.xpath(
			"//*[@id=\"step1Div\"]/table/tbody/tr/td[1]/table/tbody/tr[6]/td[2]/input");

	private static By TEST_REPORT_NAME_ENG = By.id("testReportNameEnglish");

	private static By TEST_REPORT_NAME_FR = By.id("testReportNameFrench");

	private static By NEXT_BUTTON = By.id("nextButton");

	private static By ACTIVE_CHECKBOX = By.id("active");

	private static By ORDERABLE_CHECKBOX = By.id("orderable");

	private static By SAMPLE_TYPE_DROPDOWN = By.id("asmSelect1");

	private static By DISPLAY_TEST_ORDER = By.xpath("//*[@id=\"sort2\"]/ul");

	private static By ACCEPT_TEST_NAME_BUTTON = By.xpath("//*[@id=\"mainForm\"]/div[8]/input[1]");

	private static By MODIFY_TESTS_LINK = By.xpath("//*[@id=\"mainForm\"]/ul[2]/li[4]/input");

	private static By GUIDANCE_CHECKBOX = By.xpath("//*[@id=\"mainForm\"]/input[5]");

	private static By EDIT_RESULT_RANGES_BTN = By.id("editResultLimitsButton");

	private static By NORMAL_RANGE_LOW = By.id("lowNormal_0");

	private static By NORMAL_RANGE_HIGH = By.id("highNormal_0");

	private static By REPORTING_RANGE_LOW = By.id("lowReportingRange");

	private static By REPORTING_RANGE_HIGH = By.id("highReportingRange");

	private static By ACCEPT_CONFIRM_BTN = By.id("acceptButton");

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
			if (test.equals(testName + "(Serum)")) {
				e.click();
			} else if (test.equals(testName + " updated(Serum)")) {
				e.click();
			}
		}
	}

	public String getTestReportNameEnglish() {
		return getValue(TEST_REPORT_NAME_ENG);
	}

	public String getTestReportNameFrench() {
		return getValue(TEST_REPORT_NAME_FR);
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

	public void clickAddNewTestsLink() {
		clickOn(ADD_NEW_TESTS_LINK);
	}

	public Boolean testSectionDropDownExists() {
		return dropDownHasOptions(TEST_SECTION_DROP_DOWN);
	}

	public Boolean testEnglishNameFieldExists() {
		return hasElement(TEST_ENG_NAME_FIELD);
	}

	public Boolean testFrenchNameFieldExists() {
		return hasElement(TEST_FR_NAME_FIELD);
	}

	public void enterEngTestName(String englishName) {
		setText(TEST_ENG_NAME_FIELD, englishName);
	}

	public void enterFRTestName(String frenchName) {
		setText(TEST_FR_NAME_FIELD, frenchName);
	}

	public void selectUnitOfMeasure(String unit) {
		if (dropDownHasTextOption(UNIT_OF_MEASURE_DROPDOWN, unit)) {
			selectFrom(UNIT_OF_MEASURE_DROPDOWN, unit);
		}
	}

	public void selectTestSectionDropDownOption(String testSection) {
		if (dropDownHasTextOption(TEST_SECTION_DROP_DOWN, testSection)) {
			selectFrom(TEST_SECTION_DROP_DOWN, testSection);
		}
	}

	public void selectPanelDropDownOption(String panel) {
		if (dropDownHasTextOption(PANEL_DROPDOWN, panel)) {
			selectFrom(PANEL_DROPDOWN, panel);
		}
	}

	public void selectResultTypeDropDownOption(String resultType) {
		if (dropDownHasTextOption(RESULT_TYPE_DROPDOWN, resultType)) {
			selectFrom(RESULT_TYPE_DROPDOWN, resultType);
		}
	}

	public void clickCopyTestNameButton() {
		clickOn(COPY_TEST_NAME_BUTTON);
	}

	public void enterBothEngReportNameAndFrenchTestName(String engTestReportName, String frTestReportName) {
		setText(TEST_REPORT_NAME_ENG, engTestReportName);
		setText(TEST_REPORT_NAME_FR, frTestReportName);
	}

	public boolean nextButtonDisabled() {
		return isDisabled(NEXT_BUTTON);
	}

	public boolean ActiveCheckBoxSelected() {
		return isChecked(ACTIVE_CHECKBOX);
	}

	public boolean OrderableCheckBoxSelected() {
		return isChecked(ORDERABLE_CHECKBOX);
	}

	public void ClickNextButton() {
		clickOn(NEXT_BUTTON);
	}

	public void selectSampleTypeDropdown(String sampleType) {
		if (dropDownHasTextOption(SAMPLE_TYPE_DROPDOWN, sampleType)) {
			selectFrom(SAMPLE_TYPE_DROPDOWN, sampleType);
		}
	}

	public boolean testNameHasGreenMarking(String testName) {
		By LIST_ELEMENTS = By.tagName("li");
		List<WebElement> options = findElement(DISPLAY_TEST_ORDER).findElements(LIST_ELEMENTS);
		for (WebElement element : options) {
			if (element.getText().equals(testName)) {
				String testTextColor = element.getCssValue("color");
				String hexConvert = Color.fromString(testTextColor).asHex();
				if (hexConvert.equals("#00bb00")) {
					return true;
				}
				break;
			}
		}
		return false;
	}

	public void moveTestNameToDifferentPosition() {
		WebElement testToMove = findElement(By.xpath("//*[@id=\"sort2\"]/ul/li[22]"));
		WebElement testToReplacePosition = findElement(By.xpath("//*[@id=\"sort2\"]/ul/li[1]"));
		final Actions action = new Actions(getDriver());
		action.clickAndHold(testToMove);
		action.moveToElement(testToReplacePosition).release();
		action.build().perform();
	}

	public int testNameListPosition(String testName) {
		By LIST_ELEMENTS = By.tagName("li");
		List<WebElement> options = findElement(DISPLAY_TEST_ORDER).findElements(LIST_ELEMENTS);
		for (WebElement element : options) {
			if (element.getText().equals(testName)) {
				return element.getLocation().getY();
			}
		}
		return 0;
	}

	public void clickAcceptTestButton() {
		clickOn(ACCEPT_TEST_NAME_BUTTON);
	}

	public void clickModifyTestsLink() {
		clickOn(MODIFY_TESTS_LINK);
	}

	public boolean guidanceCheckBoxExists() {
		return hasElement(GUIDANCE_CHECKBOX);
	}

	public void GuidanceChecked() throws InterruptedException {
		clickOn(GUIDANCE_CHECKBOX);
	}

	public void GuidanceUnChecked() throws InterruptedException {
		clickOn(GUIDANCE_CHECKBOX);
	}

	public boolean isGuidanceCheckBoxChecked() {
		return isChecked(GUIDANCE_CHECKBOX);
	}

	public String getTestNameRequiredClass() {
		return findElement(REQUIRED_TEST_NAME).getAttribute("class");
	}

	public String getReportingTestNameRequiredClass() {
		return findElement(REQUIRED_REPORTING_TEST_NAME).getAttribute("class");
	}

	public String getTestSectionRequiredClass() {
		return findElement(REQUIRED_TEST_SECTION).getAttribute("class");
	}

	public String getResultTypeRequiredClass() {
		return findElement(REQUIRED_RESULT_TYPE).getAttribute("class");
	}

	public void enterLOINCText(String loinc) {
		setText(LOINC_TEXT_FIELD, loinc);
	}

	public String getSampleTypeRequiredClass() {
		return findElement(REQUIRED_SAMPLE_TYPE).getAttribute("class");
	}

	public void clickEditResultRangesButton() {
		clickOn(EDIT_RESULT_RANGES_BTN);
	}

	public void enterNormalRangeLow(String range) {
		setText(NORMAL_RANGE_LOW, range);
	}

	public void enterNormalRangeHigh(String range) {
		setText(NORMAL_RANGE_HIGH, range);
	}

	public void enterReportingRangeLow(String range) {
		setText(REPORTING_RANGE_LOW, range);
	}

	public void enterReportingRangeHigh(String range) {
		setText(REPORTING_RANGE_HIGH, range);
	}

	public boolean acceptButtonExists() {
		return hasElement(ACCEPT_CONFIRM_BTN);
	}

	public void ClickAcceptButton() {
		clickOn(ACCEPT_CONFIRM_BTN);
	}
}
