package com.nopcommerce.account;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

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
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Topic_05_Multiple_Browser_Factory_Pattern {
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
		email = "tamnguyen_" + abstractPage.randomNumber() + "@gmail.com";
		password = "123123";
		
	}

	@Test
	public void TC_01_Register() throws InterruptedException {
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToRegisterLink();
		
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		abstractPage.sleepInSecond(1);
		registerPage.selectMaleGenderCheckBox();
		registerPage.inputToFirstNameTextBox("Tam");
		registerPage.inputToLastNameTextBox("Nguyen");
		registerPage.selectDateInDropDown("10");
		registerPage.selectMonthInDropDown("October");
		registerPage.selectYearInDropDown("1993");
		registerPage.inputToEmailTextBox(email);
		registerPage.inputToCompanyTextBox("Step Sister");
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		
		registerPage.clickToRegisterButton();
		boolean registerSuccess = registerPage.isResultMatched("Your registration completed");
		abstractPage.sleepInSecond(1);
		registerPage.clickToContinueButton();
		assertTrue(registerSuccess);
	}

	@Test
	public void TC_02_Login() {
		abstractPage.sleepInSecond(2);
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToSignOutButton();
		abstractPage.sleepInSecond(2);
		homePage.clickToLoginLink();
		abstractPage.sleepInSecond(1);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.inputToEmailTextBox(email);
		loginPage.inputToPasswordButton(password);
		
		loginPage.clickToLoginButton();
		
		homePage = PageGeneratorManager.getHomePage(driver);
		assertTrue(homePage.isMyAccountLinkDisplayed());
	}

}
