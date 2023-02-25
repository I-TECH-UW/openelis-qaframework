package org.openelisglobal.qaframework.automation.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NonConformityPage extends Page {

	private static final String PAGE_PATH = "/ReportNonConformingEvent";

	private static final String PATH_HOME = "/Dashboard";

	private static final By SEARCH_CRITERIA_SELECT = By.id("searchCriteria");

	private static final By SEARCH_FIELD = By.id("searchValue");

	private static final By SEARCH_BUTTON = By.id("searchButtonId");

	private static final By AFFECTED_SPECIMEN_CHECKBOX = By.xpath("//*[@id=\"sample-\"]/tbody/tr[2]/td[1]/input");

	private static final By NCE_BUTTON = By.id("goToNCEForm");

	public NonConformityPage(Page parent) {
		super(parent);
	}

	@Override
	public String getPageUrl() {
		return PAGE_PATH;
	}

	public HomePage goToHomePage() {
		this.goToPage(PATH_HOME);
		return new HomePage(this);
	}

	public boolean searchSelectExists() {
		return hasElement(SEARCH_CRITERIA_SELECT);
	}

	public boolean searchFieldExists() {
		return hasElement(SEARCH_FIELD);
	}

	public boolean searchButtonExists() {
		return hasElement(SEARCH_BUTTON);
	}

	public String searchButtonDisabledAttribute() {
		return findElement(SEARCH_BUTTON).getAttribute("disabled");
	}

	public void selectCriteriaSelect(String option) {
		By FIELD_OPTION = By.tagName("option");
		clickOn(SEARCH_CRITERIA_SELECT);
		List<WebElement> elements = findElement(SEARCH_CRITERIA_SELECT).findElements(FIELD_OPTION);

		for (WebElement element : elements) {
			if (element.getText().equals(option)) {
				element.click();
				break;
			}
		}
	}

	public void enterSearchText(String searchQuery) {
		setText(SEARCH_FIELD, searchQuery);
	}
	public  void clickSearchButton(){
		clickOn(SEARCH_BUTTON);
	}

	public void checkAffectedSpecimen() {
		clickOn(AFFECTED_SPECIMEN_CHECKBOX);
	}

	public void clickNCEButton() {
		clickOn(NCE_BUTTON);
	}

}
