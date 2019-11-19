package com.nopcommerce.frontend;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class FE_01_Register {
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
//		System.out.println(driver);

////		abstractPage = new AbstractPage(driver);
//		driver.get("https://demo.nopcommerce.com/");
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
//		email = "tamnguyen_" + abstractPage.randomNumber() + "@gmail.com";
//		password = "123123";
//		

		System.out.println("Browser name = " + browserName);

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
		System.out.println(driver.toString());
		abstractPage = new AbstractPage(driver);
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_RegisterWithEmptyData() {
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToRegisterLink();
		abstractPage.sleepInSecond(1);

		registerPage = PageGeneratorManager.getRegisterPage(driver);
		registerPage.clickToRegisterButton();
		abstractPage.sleepInSecond(1);

		Assert.assertTrue(registerPage.isErrorMessageDisplayed("FirstName"));
		Assert.assertTrue(registerPage.isErrorMessageDisplayed("LastName"));
		Assert.assertTrue(registerPage.isErrorMessageDisplayed("Email"));
		Assert.assertTrue(registerPage.isErrorMessageDisplayed("Password"));
		Assert.assertTrue(registerPage.isErrorMessageDisplayed("ConfirmPassword"));
	}

//	@Test
	public void TC_02_RegisterWithInvalidEmail() {
		abstractPage.sleepInSecond(1);
		registerPage.inputToFirstNameTextBox("John");
		registerPage.inputToLastNameTextBox("Wick");
		registerPage.inputToEmailTextBox("123@123");
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		registerPage.clickToRegisterButton();

		Assert.assertTrue(registerPage.isErrorMessageEquals("Email", "Wrong email"));
	}

//	@Test
	public void TC_03_RegisterWithExistEmail() {
		abstractPage.sleepInSecond(1);
//		registerPage.inputToFirstNameTextBox("John");
//		registerPage.inputToLastNameTextBox("Wick");
		registerPage.inputToEmailTextBox("tamqada@gmail.com");
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		registerPage.clickToRegisterButton();

		Assert.assertTrue(registerPage.isErrorMessageOnTopDisplayed());
	}
}
