package com.nopcommerce.frontend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

public class FE_02_Login {
	WebDriver driver;
	private HomePageObject homePage;
	private AbstractPage abstractPage;
	private LoginPageObject loginPage;
	private FE_01_Register registerTestcasess;
	String email;
	
	@BeforeClass
	public void beforeClass() {	
		System.setProperty("webdriver.chrome.driver", ".\\libaries\\chromedriver.exe");
		driver = new ChromeDriver();
		abstractPage = new AbstractPage(driver);
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		registerTestcasess = new FE_01_Register();
		email = "tamqada@gmail.com";
		
	}
//	@Test
//	public void TC_01_LoginWithEmptyData() {
//		homePage = PageGeneratorManager.getHomePage(driver);
//		homePage.clickToLoginLink();
//		
//		loginPage = PageGeneratorManager.getLoginPage(driver);
//		abstractPage.sleepInSecond(1);
//		loginPage.inputToEmailTextBox("tamqada@gmail.com");
//		loginPage.inputToPasswordButton("123123");
//		loginPage.clickToLoginButton();
//		
//		homePage = PageGeneratorManager.getHomePage(driver);
//		assertTrue(homePage.isMyAccountLinkDisplayed());
//		
//	}
	
	@Test
	public void TC_01_LoginWithEmptyData() {
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToLoginLink();
		
		loginPage = PageGeneratorManager.getLoginPage(driver);
		abstractPage.sleepInSecond(1);
		loginPage.clickToLoginButton();
		assertTrue(loginPage.isEmailErrorMsgEquals("Please enter your email"));
	}
	
	@Test
	public void TC_02_LoginWithInvalidEmail() {
		loginPage.inputToEmailTextBox("123");
		loginPage.clickToLoginButton();
		assertTrue(loginPage.isEmailErrorMsgEquals("Wrong email"));	
	}
	
	@Test
	public void TC_03_RegisterWithUnRegisteredEmail() {
		loginPage.inputToEmailTextBox("tamqada@hotmail.com");
		loginPage.clickToLoginButton();
		abstractPage.sleepInSecond(1);
		assertTrue(loginPage.isErrorMessageContains("No customer account found"));
	}
	
	@Test
	public void TC_04_LoginWithEmptyPassword() {
		loginPage.inputToEmailTextBox(email);
		loginPage.clickToLoginButton();
		abstractPage.sleepInSecond(1);
		assertTrue(loginPage.isErrorMessageContains("The credentials provided are incorrect"));
	}
	
	@Test
	public void TC_05_LoginWithIncorrectPassword() {
		loginPage.inputToEmailTextBox(email);
		loginPage.inputToPasswordButton("123");
		loginPage.clickToLoginButton();
		assertTrue(loginPage.isErrorMessageContains("The credentials provided are incorrect"));
	}
	
	@Test
	public void TC_06_LoginWithValidInfo() {
		abstractPage.sleepInSecond(1);
		loginPage.inputToEmailTextBox(email);
		loginPage.inputToPasswordButton("123123");
		loginPage.clickToLoginButton();
		abstractPage.sleepInSecond(1);
		assertTrue(loginPage.isHeaderLinksDisplayed("My account"));
	}
}
