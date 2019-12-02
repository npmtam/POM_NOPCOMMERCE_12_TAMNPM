package commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;

public class AbstractTest {
	private WebDriver driver;
	String rootFolder = System.getProperty("user.dir");
	
	protected final Log log;
	
	protected AbstractTest() {
		log = LogFactory.getLog(getClass());
	}
	
	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		System.out.println("Status = " + pass);
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		boolean status;
		try {
			if (actual instanceof String && expected instanceof String) {
				actual = actual.toString().trim();
				log.info("Actual = " + actual);
				expected = expected.toString().trim();
				log.info("Expected = " + expected);
				status = (actual.equals(expected));
			} else {
				status = (actual == expected);
			}

			if (status) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertEquals(actual, expected, "Value is not matching!");
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
	
	public WebDriver openMultiBrowsers (String browserName) {
		switch (browserName) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", rootFolder + "\\resources\\geckodriver.exe");
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, rootFolder + "\\FirefoxLogs.txt");
			driver = new FirefoxDriver();
			break;
		case "firefox_headless":
			System.setProperty("webdriver.gecko.driver", rootFolder + "\\resources\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			driver = new FirefoxDriver(options);
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver", rootFolder + "\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "chrome_headless":
			System.setProperty("webdriver.chrome.driver", rootFolder + "\\resources\\chromedriver.exe");
			ChromeOptions options2 = new ChromeOptions();
			options2.setHeadless(true);
			driver = new ChromeDriver(options2);
			break;
		default:
			System.out.println("Please input your browser name!");
			break;
		}
		return driver;
	}
	
	

}
