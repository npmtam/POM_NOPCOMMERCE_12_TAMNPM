package com.nopcommerce.frontend;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class FE_01_Register extends AbstractTest {
	WebDriver driver;
	Select select;
	JavascriptExecutor jsExecutor;
	WebDriverWait waitExplicit;
	String email;
	String password;

	public AbstractPage abstractPage;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
//		System.setProperty("webdriver.chrome.driver", ".\\libaries\\chromedriver.exe");
//		driver = new ChromeDriver();
////		System.setProperty("webdriver.gecko.driver", ".\\resources\\geckodriver.exe");
////		driver = new FirefoxDriver();
//
//		abstractPage = new AbstractPage(driver);
//		driver.get("https://demo.nopcommerce.com/");
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
//		email = "tamnguyen_" + abstractPage.randomNumber() + "@gmail.com";
//		password = "123123";

		String rootFolder = System.getProperty("user.dir");
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
		abstractPage = new AbstractPage(driver);
		driver.get("https://demo.nopcommerce.com/");

		email = "tamnguyen_" + abstractPage.randomNumber() + "@gmail.com";
		password = "123123";
	}

	@Test
	public void TC_01_RegisterWithEmptyData() {
		log.info("Register - TC01 - Step 01: Click to register link on Header");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToRegisterLink();
		abstractPage.sleepInSecond(1);
		
		log.info("Register - TC01 - Step 02: Click to register button");
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		registerPage.clickToRegisterButton();
		abstractPage.sleepInSecond(1);

		log.info("Register - TC01 - Step 03: Verify error messages");
		verifyTrue(registerPage.isErrorMessageDisplayed("FirstName"));
		verifyTrue(registerPage.isErrorMessageDisplayed("LastName"));
		verifyTrue(registerPage.isErrorMessageDisplayed("Email"));
		verifyTrue(registerPage.isErrorMessageDisplayed("Password"));
		verifyTrue(registerPage.isErrorMessageDisplayed("ConfirmPassword"));
	}

	@Test
	public void TC_02_RegisterWithInvalidEmail() {
		log.info("Register - TC02 - Step 01: Fill info");
		abstractPage.sleepInSecond(1);
		registerPage.inputToFirstNameTextBox("John");
		registerPage.inputToLastNameTextBox("Wick");
		registerPage.inputToEmailTextBox("123@123");
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		log.info("Register - TC02 - Step 02: Click to register button");
		registerPage.clickToRegisterButton();
		abstractPage.sleepInSecond(1);
		
		log.info("Register - TC02 - Step 03: Verify error message");
		verifyTrue(registerPage.isErrorMessageContains("field-validation", "Wrong email"));
	}

	@Test
	public void TC_03_RegisterWithExistEmail() {
		log.info("Register - TC03 - Step 01: Fill info");
		abstractPage.sleepInSecond(1);
		registerPage.inputToFirstNameTextBox("John");
		registerPage.inputToLastNameTextBox("Wick");
		registerPage.inputToEmailTextBox("tamqada@gmail.com");
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		log.info("Register - TC03 - Step 02: Click to register button");
		registerPage.clickToRegisterButton();

		log.info("Register - TC03 - Step 03: Verify error message");
		verifyTrue(registerPage.isErrorMessageOnTopDisplayed());
	}

	@Test
	public void TC_04_PasswordLesser6Characters() {
		log.info("Register - TC04 - Step 01: Fill info");
		abstractPage.sleepInSecond(1);
		registerPage.inputToFirstNameTextBox("John");
		registerPage.inputToLastNameTextBox("Wick");
		registerPage.inputToEmailTextBox("tamqada@gmail.com");
		registerPage.inputToPasswordTextBox("123");
		registerPage.inputToConfirmPasswordTextBox("123");
		abstractPage.sleepInSecond(1);
		
		log.info("Register - TC04 - Step 02: Verify error message");
		verifyTrue(registerPage.isErrorMessageContains("field-validation", "must have at least 6 characters"));
	}

	@Test
	public void TC_05_ConfirmPasswordNotMatched() {
		log.info("Register - TC05 - Step 01: Fill info");
		abstractPage.sleepInSecond(1);
		registerPage.inputToFirstNameTextBox("John");
		registerPage.inputToLastNameTextBox("Wick");
		registerPage.inputToEmailTextBox("tamqada@gmail.com");
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox("123");
		
		log.info("Register - TC05 - Step 02: Verify error message");
		verifyTrue(registerPage.isErrorMessageDisplayed("ConfirmPassword"));
	}

	@Test
	public void TC_06_RegisterWithValidInfo() {
		log.info("Register - TC06 - Step 01: Fill info");
		abstractPage.sleepInSecond(1);
		registerPage.inputToFirstNameTextBox("John");
		registerPage.inputToLastNameTextBox("Wick");
		registerPage.inputToEmailTextBox(email);
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		log.info("Register - TC06 - Step 02: Click to register button");
		registerPage.clickToRegisterButton();

		log.info("Register - TC06 - Step 03: Verify success message");
		verifyTrue(registerPage.isResultMatched("Your registration completed"));
	}

	@Test
	public void TC_07_LogOUt() {
		log.info("Register - TC07 - Step 01: Log out");
		homePage.clickToSignOutButton();
		homePage = PageGeneratorManager.getHomePage(driver);
		abstractPage.sleepInSecond(2);
		verifyTrue(homePage.isLoginLinkDisplayed());
	}
}
