package com.nopcommerce.frontend;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageObjects.HeaderMyAccountPO;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

public class FE_03_MyAccount {
	WebDriver driver;
	private HomePageObject homePage;
	private AbstractPage abstractPage;
	private LoginPageObject loginPage;
	private HeaderMyAccountPO myAccountPage; 
	String email;
	String emailUpdate;
	
	@BeforeClass
	public void beforeClass() {
			System.setProperty("webdriver.chrome.driver", ".\\libaries\\chromedriver.exe");
			driver = new ChromeDriver();
			abstractPage = new AbstractPage(driver);
			driver.get("https://demo.nopcommerce.com/");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			email = "automation-testing@gmail.com";
			emailUpdate = "tamqada@gmail.com";
		}
	
	@Test
	public void TCC_00_Login() {
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToLoginLink();
		
		loginPage = PageGeneratorManager.getLoginPage(driver);
		abstractPage.sleepInSecond(1);
		abstractPage.sleepInSecond(1);
		loginPage.inputToEmailTextBox(email);
		loginPage.inputToPasswordButton("123123");
		loginPage.clickToLoginButton();
		homePage = PageGeneratorManager.getHomePage(driver);
		abstractPage.sleepInSecond(1);
		assertTrue(loginPage.isHeaderLinksDisplayed("My account"));
	}
	
	@Test
	public void  TC_01_UpdateCustomerInfo() {
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.openMultiplePagesHeader("My account");
		myAccountPage = PageGeneratorManager.getHeaderMyAccountPage(driver);
		assertTrue(myAccountPage.isCurrentURLContains("customer/info"));
		
		abstractPage.sleepInSecond(1);
		myAccountPage.clickToGenderRadioButton("female");
		myAccountPage.inputToCustomerInfoTextboxs("FirstName", "Automation");
		myAccountPage.inputToCustomerInfoTextboxs("LastName", "FC");
		myAccountPage.selectBirdtDateDropDownList("Day", "1");
		myAccountPage.selectBirdtDateDropDownList("Month", "January");
		myAccountPage.selectBirdtDateDropDownList("Year", "1999");
		myAccountPage.inputToCustomerInfoTextboxs("Email", emailUpdate);
		myAccountPage.inputToCustomerInfoTextboxs("Company", "Automation FC");
		myAccountPage.clickToSaveButton();
		assertFalse(myAccountPage.isErrorMessagePresentInDOM());
		
	}
	
	@Test
	public void TC_02_UpdateAddress() {
		abstractPage.sleepInSecond(1);
		myAccountPage.clickToAddressMenu();
		abstractPage.sleepInSecond(1);
		assertTrue(myAccountPage.isCurrentURLContains("customer/addresses"));
		
		myAccountPage.clickToAddNewButton();
		myAccountPage.inputToAddressTextboxes("FirstName", "Automation");
		myAccountPage.inputToAddressTextboxes("LastName", "FC");
		myAccountPage.inputToAddressTextboxes("Email", "automationfc.vn@gmail.com");
		myAccountPage.inputToAddressTextboxes("Company", "Automation FC");
		
		myAccountPage.selectCountryDropdownList("Viet Nam");
		myAccountPage.inputToAddressTextboxes("City", "Da Nang");
		myAccountPage.inputToAddressTextboxes("Address1", "123/04 Le Lai");
		myAccountPage.inputToAddressTextboxes("Address2", "234/05 Hai Phong");
		myAccountPage.inputToAddressTextboxes("ZipPostalCode", "550000");
		myAccountPage.inputToAddressTextboxes("PhoneNumber", "0123456789");
		myAccountPage.inputToAddressTextboxes("FaxNumber", "0987654321");
		
		
		
	}
}
