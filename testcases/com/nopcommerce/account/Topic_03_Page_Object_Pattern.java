package com.nopcommerce.account;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import commons.AbstractPage;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Topic_03_Page_Object_Pattern {
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

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\libaries\\chromedriver.exe");
		driver = new ChromeDriver();
		abstractPage = new AbstractPage(driver);
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		email = "tamnguyen_" + abstractPage.randomNumber() + "@gmail.com";
		password = "123123";
		
	}

	@Test
	public void TC_01_Register() throws InterruptedException {
		homePage = new HomePageObject(driver);
		homePage.clickToRegisterLink();
		
		registerPage = new RegisterPageObject(driver);
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
		homePage = new HomePageObject(driver);
		homePage.clickToSignOutButton();
		abstractPage.sleepInSecond(2);
		homePage.clickToLoginButton();
		abstractPage.sleepInSecond(1);
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTextBox(email);
		loginPage.inputToPasswordButton(password);
		
		loginPage.clickToLoginButton();
		
		homePage = new HomePageObject(driver);
		assertTrue(homePage.isMyAccountLinkDisplayed());
	}

}