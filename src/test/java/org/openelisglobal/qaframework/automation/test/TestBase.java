package org.openelisglobal.qaframework.automation.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.vfs2.AllFileSelector;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openelisglobal.qaframework.automation.page.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Superclass for all UI Tests. Contains lots of handy "utilities" needed to setup and tear down
 * tests as well as handy methods needed during tests, such as:
 * <ul>
 * <li>initialize Selenium WebDriver</li>
 * <li>@see {@link #goToLoginPage()}</li>
 * <li>@see {@link #login()}</li>
 * <li>@see {@link #assertPage(Page)} - @see {@link #pageContent()}</li>
 * </ul>
 */
public class TestBase {
	
	public static final int MAX_WAIT_IN_SECONDS = 120;
	
	public static final int MAX_PAGE_LOAD_IN_SECONDS = 120;
	
	public static final int MAX_SERVER_STARTUP_IN_MILLISECONDS = 10 * 60 * 1000;
	
	public static final String REMOTE_URL_CHROME = "http://localhost:4444/wd/hub";
	
	public static String REMOTE_URL_FIREFOX = "http://localhost:4445/wd/hub";
	
	private static volatile boolean serverFailure = false;
	
	private WebDriver driver;
	
	protected static ThreadLocal<RemoteWebDriver> remoteDriver = new ThreadLocal<>();;
	
	final TestProperties properties = TestProperties.instance();
	
	TestProperties.BrowserType browserType;
	
	TestProperties.WebDriverType driverType;
	
	protected Page page;
	
	public TestBase() {
		browserType = properties.getBrowserType();
		driverType = properties.getDriverType();
		try {
			startWebDriver();
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	@Before
	public void startWebDriver() throws Exception {
		browserType = properties.getBrowserType();
		driverType = properties.getDriverType();
		if (serverFailure) {
			fail("Test killed due to server failure");
		}
		switch (driverType) {
			case local:
				launchBrowser();
				break;
			case remote:
				setupThread();
				break;
		}
		
	}
	
	public void launchBrowser() throws Exception {
		System.out.println("Running locally...");
		switch (browserType) {
			case chrome:
				driver = setupChromeDriver();
				break;
			case firefox:
				driver = setupFirefoxDriver();
				break;
			default:
				// shrug, choose chrome as default
				driver = setupChromeDriver();
				break;
		}
		
		driver.manage().timeouts().implicitlyWait(MAX_WAIT_IN_SECONDS, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(MAX_PAGE_LOAD_IN_SECONDS, TimeUnit.SECONDS);
		
	}
	
	@After
	public void stopWebDriver() {
		quit();
	}
	
	protected WebDriver getWebDriver() {
		switch (driverType) {
			case local:
				return driver;
			case remote:
				return remoteDriver.get();
			default:
				return remoteDriver.get();
		}
	}
	
	WebDriver setupFirefoxDriver() {
		if (StringUtils.isBlank(System.getProperty("webdriver.gecko.driver"))) {
			System.setProperty("webdriver.gecko.driver", Thread.currentThread().getContextClassLoader()
			        .getResource(TestProperties.instance().getFirefoxDriverLocation()).getPath());
		}
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		if ("true".equals(TestProperties.instance().getHeadless())) {
			firefoxOptions.addArguments("--headless");
		}
		driver = new FirefoxDriver(firefoxOptions);
		return driver;
	}
	
	WebDriver setupChromeDriver() {
		URL chromedriverExecutable = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		
		String chromedriverExecutableFilename = null;
		if (SystemUtils.IS_OS_MAC_OSX) {
			chromedriverExecutableFilename = "chromedriver";
			chromedriverExecutable = classLoader.getResource("chromedriver/mac/chromedriver");
		} else if (SystemUtils.IS_OS_LINUX) {
			chromedriverExecutableFilename = "chromedriver";
			chromedriverExecutable = classLoader.getResource("chromedriver/linux/chromedriver");
		} else if (SystemUtils.IS_OS_WINDOWS) {
			chromedriverExecutableFilename = "chromedriver.exe";
			chromedriverExecutable = classLoader.getResource("chromedriver/windows/chromedriver.exe");
		}
		String errmsg = "cannot find chromedriver executable";
		String chromedriverExecutablePath = null;
		if (chromedriverExecutable == null) {
			System.err.println(errmsg);
			Assert.fail(errmsg);
		} else {
			chromedriverExecutablePath = chromedriverExecutable.getPath();
			// This checks to see if the chromedriver file is inside a
			// jar, and if so
			// uses VFS to extract it to a temp directory.
			if (chromedriverExecutablePath.contains(".jar!")) {
				FileObject chromedriver_vfs;
				try {
					chromedriver_vfs = VFS.getManager().resolveFile(chromedriverExecutable.toExternalForm());
					File chromedriver_fs = new File(FileUtils.getTempDirectory(), chromedriverExecutableFilename);
					FileObject chromedriverUnzipped = VFS.getManager().toFileObject(chromedriver_fs);
					chromedriverUnzipped.delete();
					chromedriverUnzipped.copyFrom(chromedriver_vfs, new AllFileSelector());
					chromedriverExecutablePath = chromedriver_fs.getPath();
					if (!SystemUtils.IS_OS_WINDOWS) {
						chromedriver_fs.setExecutable(true);
					}
				}
				catch (FileSystemException e) {
					System.err.println(errmsg + ": " + e);
					e.printStackTrace();
					Assert.fail(errmsg + ": " + e);
				}
			}
		}
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, chromedriverExecutablePath);
		String chromedriverFilesDir = "target/chromedriverlogs";
		try {
			FileUtils.forceMkdir(new File(chromedriverFilesDir));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY,
		    chromedriverFilesDir + "/chromedriver-" + getClass().getSimpleName() + ".log");
		ChromeOptions chromeOptions = new ChromeOptions();
		if ("true".equals(TestProperties.instance().getHeadless())) {
			chromeOptions.addArguments("--headless");
		}
		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		return driver;
	}
	
	/**
	 * Assert we're on the expected page.
	 * 
	 * @param expected page
	 */
	public void assertPage(Page expected) {
		assertTrue(driver.getCurrentUrl().contains(expected.getPageUrl()));
	}
	
	protected void quit() {
		if (getWebDriver() != null) {
			getWebDriver().quit();
		}
	}
	
	protected void setupThread() throws MalformedURLException {
		switch (browserType) {
			case chrome:
				System.out.println("Inside Chrome");
				ChromeOptions chromeOptions = new ChromeOptions();
				String chromeUrl = System.getenv("REMOTE_URL_CHROME");
				if (chromeUrl == null || chromeUrl.isEmpty()) {
					chromeUrl = REMOTE_URL_CHROME;
				}
				remoteDriver.set(new RemoteWebDriver(new URL(chromeUrl), chromeOptions));
				break;
			
			case firefox:
				System.out.println("Inside Firefox");
				FirefoxOptions fireFoxOptions = new FirefoxOptions();
				String fireFoxUrl = System.getenv("REMOTE_URL_FIREFOX");
				if (fireFoxUrl == null || fireFoxUrl.isEmpty()) {
					fireFoxUrl = REMOTE_URL_FIREFOX;
				}
				remoteDriver.set(new RemoteWebDriver(new URL(fireFoxUrl), fireFoxOptions));
				break;
		}
	}
	
	public void assertPageContainsPatientResults(Page page) {
		assertTrue(page.containsText("Data source"));
		assertTrue(page.containsText("Last Name"));
		assertTrue(page.containsText("First Name"));
		assertTrue(page.containsText("Gender"));
		assertTrue(page.containsText("Date of Birth"));
		//assertTrue(page.containsText("Subject Number"));
		assertTrue(page.containsText("Unique Health ID number"));
		assertTrue(page.containsText("National ID"));
	}
}
