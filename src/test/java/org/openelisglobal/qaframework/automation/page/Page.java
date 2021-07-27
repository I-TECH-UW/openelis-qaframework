package org.openelisglobal.qaframework.automation.page;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Iterables;

import org.junit.Assert;
import org.openelisglobal.qaframework.automation.page.exception.PageRejectedException;
import org.openelisglobal.qaframework.automation.test.TestBase;
import org.openelisglobal.qaframework.automation.test.TestProperties;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * A superclass for "real" pages. Has lots of handy methods for accessing elements, clicking,
 * filling fields. etc.
 */
public abstract class Page {
	
	protected final TestProperties properties = TestProperties.instance();
	
	protected final WebDriver driver;
	
	public WebDriver getDriver() {
		return this.driver;
	}

	protected final WebDriverWait waiter;
	
	private final String contextUrl;
	
	private final String serverUrl;
	
	private final ExpectedCondition<Boolean> pageReady = new ExpectedCondition<Boolean>() {
		
		public Boolean apply(WebDriver driver) {
			if (getPageRejectUrl() != null) {
				if (driver.getCurrentUrl().contains(getPageRejectUrl())) {
					return true;
				}
			}
			
			if (!driver.getCurrentUrl().contains(getPageUrl())) {
				if (getPageAliasUrl() != null) {
					if (!driver.getCurrentUrl().contains(getPageAliasUrl())) {
						return false;
					}
				} else {
					return false;
				}
			}
			
			Object readyState = executeScript("return document.readyState;");
			
			if (hasPageReadyIndicator()) {
				return "complete".equals(readyState) && Boolean.TRUE.equals(executeScript("return (typeof "
				        + getPageReadyIndicatorName() + "  !== 'undefined') ? " + getPageReadyIndicatorName() + " : null;"));
			} else {
				return "complete".equals(readyState);
			}
			
		}
	};
	
	public Page(Page parent, WebElement waitForStaleness) {
		this(parent.driver);
		waitForStalenessOf(waitForStaleness);
	}
	
	public Page(Page parent) {
		this(parent.driver);
		if (this.getClass().isInstance(parent)) {
			throw new RuntimeException("When returning the same page use the two arguments constructor");
		}
	}
	
	public Page(WebDriver driver) {
		this.driver = driver;
		
		String webAppUrl = properties.getWebAppUrl();
		if (webAppUrl.endsWith("/")) {
			webAppUrl = webAppUrl.substring(0, webAppUrl.length() - 1);
		}
		serverUrl = webAppUrl;
		
		try {
			contextUrl = new URL(serverUrl).getPath();
		}
		catch (MalformedURLException e) {
			throw new IllegalArgumentException("webapp.url " + properties.getWebAppUrl() + " is not a valid URL", e);
		}
		
		waiter = new WebDriverWait(driver, Duration.ofSeconds(TestBase.MAX_WAIT_IN_SECONDS));
	}
	
	/**
	 * Override to return true, if a page has the 'pageReady' JavaScript variable.
	 * 
	 * @return true if the page has pageReady indicator, false by default
	 */
	public boolean hasPageReadyIndicator() {
		return false;
	}
	
	/**
	 * @return the page ready JavaScript variable, pageReady by default.
	 */
	public String getPageReadyIndicatorName() {
		return "pageReady";
	}
	
	public Object executeScript(String script) {
		return ((JavascriptExecutor) driver).executeScript(script);
	}
	
	public Page waitForPage() {
		waiter.until(pageReady);
		
		if (getPageRejectUrl() != null) {
			if (driver.getCurrentUrl().contains(getPageRejectUrl())) {
				throw new PageRejectedException("Page url " + driver.getCurrentUrl() + " contains '" + getPageRejectUrl()
				        + "', which is not allowed");
			}
		}
		return this;
	}
	
	public String newContextPageUrl(String pageUrl) {
		if (!pageUrl.startsWith("/")) {
			pageUrl = "/" + pageUrl;
		}
		return contextUrl + pageUrl;
	}
	
	public String newAbsolutePageUrl(String pageUrl) {
		if (!pageUrl.startsWith("/")) {
			pageUrl = "/" + pageUrl;
		}
		return serverUrl + pageUrl;
	}
	
	public void goToPage(String address) {
		driver.get(newAbsolutePageUrl(address));
	}
	
	public void go() {
		driver.get(getAbsolutePageUrl());
		waitForPage();
	}
	
	public WebElement findElement(By by) {
		waiter.until(ExpectedConditions.visibilityOfElementLocated(by));
		return driver.findElement(by);
	}
	
	public WebElement findElementWithoutWait(By by) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			return driver.findElement(by);
		}
		catch (Exception e) {
			return null;
		}
		
	}
	
	public List<WebElement> getElementsIfExisting(By by) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		return driver.findElements(by);
	}
	
	public void waitForPageToLoad() {
		ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver)
		        .executeScript("return document.readyState").toString().equals("complete");
		try {
			Thread.sleep(1000);
			waiter.until(expectation);
		}
		catch (Throwable error) {
			Assert.fail("Timeout waiting for Page.");
		}
	}
	
	public WebElement findElementById(String id) {
		return findElement(By.id(id));
	}
	
	public String getText(By by) {
		return findElement(by).getText();
	}
	
	public void setText(By by, String text) {
		setText(findElement(by), text);
	}
	
	public void clearText(By by) {
		findElement(by).clear();
	}
	
	public void setText(String id, String text) {
		setText(findElement(By.id(id)), text);
	}
	
	public void setTextToFieldNoEnter(By by, String text) {
		setTextNoEnter(findElement(by), text);
	}
	
	public void setTextToFieldInsideSpan(String spanId, String text) {
		setText(findTextFieldInsideSpan(spanId), text);
	}
	
	private void setText(WebElement element, String text) {
		setTextNoEnter(element, text);
		element.sendKeys(Keys.RETURN);
	}
	
	private void setTextNoEnter(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}
	
	public void clickOn(By by) {
		findElement(by).click();
	}
	
	public void clickOnLast(By by) {
		Iterables.getLast(findElements(by)).click();
	}
	
	public void selectFrom(By by, String value) {
		Select droplist = new Select(findElement(by));
		droplist.selectByVisibleText(value);
	}
	
	public void hoverOn(By by) {
		Actions builder = new Actions(driver);
		Actions hover = builder.moveToElement(findElement(by));
		hover.perform();
	}
	
	private WebElement findTextFieldInsideSpan(String spanId) {
		return findElementById(spanId).findElement(By.tagName("input"));
	}
	
	public String title() {
		return getText(By.tagName("title"));
	}
	
	public String getCurrentAbsoluteUrl() {
		return driver.getCurrentUrl();
	}
	
	public List<WebElement> findElements(By by) {
		waiter.until(ExpectedConditions.presenceOfElementLocated(by));
		
		return driver.findElements(by);
	}
	
	public void waitForStalenessOf(WebElement webElement) {
		waiter.until(ExpectedConditions.stalenessOf(webElement));
	}
	
	/**
	 * @return the page path
	 */
	public abstract String getPageUrl();
	
	public String getPageAliasUrl() {
		return null;
	}
	
	public String getPageRejectUrl() {
		return null;
	}
	
	public String getContextPageUrl() {
		return newContextPageUrl(getPageUrl());
	}
	
	public String getAbsolutePageUrl() {
		return newAbsolutePageUrl(getPageUrl());
	}
	
	public void clickOnLinkFromHref(String href) throws InterruptedException {
		// We allow use of xpath here because href's tend to be quite stable.
		clickOn(byFromHref(href));
	}
	
	public By byFromHref(String href) {
		return By.xpath("//a[@href='" + href + "']");
	}
	
	public void waitForJsVariable(final String varName) {
		waiter.until(new ExpectedCondition<Boolean>() {
			
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver)
				        .executeScript("return (typeof " + varName + "  !== 'undefined') ? " + varName + " : null") != null;
			}
		});
	}
	
	public void waitForElementToBeHidden(By by) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	
	public void waitForElementToBeEnabled(By by) {
		waiter.until(ExpectedConditions.elementToBeClickable(by));
	}
	
	public void acceptAlert() {
		waiter.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public void dismissAlert() {
		waiter.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}
	
	public Boolean alertPresent() throws InterruptedException {
		Thread.sleep(1000);
		Boolean booelan = false;
		try {
			Alert alert = driver.switchTo().alert();
			booelan = true;
		}
		catch (Exception e) {}
		return booelan;
	}
	
	public Boolean promptPresent() throws InterruptedException {
		Thread.sleep(1000);
		Boolean booelan = false;
		try {
			Alert alert = driver.switchTo().alert();
			booelan = true;
		} catch (Exception e) {
		}
		return booelan;
	}
	
	public void waitForElement(By by) {
		waiter.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public void waitForTextToBePresentInElement(By by, String text) {
		waiter.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
	}
	
	public Boolean containsText(String text) {
		return driver.getPageSource().contains(text);
	}
	
	public String getClass(By by) {
		return findElement(by).getAttribute("class");
	}
	
	public String getValue(By by) {
		return findElement(by).getAttribute("value");
	}
	
	public String getValueWithoutWait(By by) {
		if (findElementWithoutWait(by) == null) {
			return "";
		}
		return findElementWithoutWait(by).getAttribute("value");
	}
	
	public String getStyle(By by) {
		return findElement(by).getAttribute("style");
	}
	
	public void selectOptionFromDropDown(By by) {
		By FIELD_OPTION = By.tagName("option");
		clickOn(by);
		List<WebElement> options = findElement(by).findElements(FIELD_OPTION);
		int n = 0;
		for (WebElement option : options) {
			if (n == 1) {
				option.click();
				break;
			}
			n = n + 1;
		}
	}
	
	public Boolean dropDownHasOptions(By by) {
		By FIELD_OPTION = By.tagName("option");
		clickOn(by);
		List<WebElement> options = findElement(by).findElements(FIELD_OPTION);
		return options.size() > 0 ? true : false;
	}
	
	public Boolean hasElement(By by) {
		return findElement(by) != null ? true : false;
	}
	
	public Boolean hasElementWithoutWait(By by) {
		return findElementWithoutWait(by) != null ? true : false;
	}
	
	public Boolean isDisabled(By by) {
		Boolean disabled = false;
		String disabledAttribute = findElement(by).getAttribute("disabled");
		if (disabledAttribute != null) {
			if (disabledAttribute.equals("true")) {
				disabled = true;
			}
		}
		return disabled;
	}
	
	public Boolean isChecked(By by) {
		Boolean disabled = false;
		String disabledAttribute = findElement(by).getAttribute("checked");
		if (disabledAttribute != null) {
			if (disabledAttribute.equals("true")) {
				disabled = true;
			}
		}
		return disabled;
	}
	
	public void refreshPage() {
		driver.navigate().refresh();
	}
}
