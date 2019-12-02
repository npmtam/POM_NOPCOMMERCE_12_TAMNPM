package com.nopcommerce.frontend;

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
	private HeaderMyAccountPO myaccountPage; 
	String email;
	
	@BeforeClass
	public void beforeClass() {
			System.setProperty("webdriver.chrome.driver", ".\\libaries\\chromedriver.exe");
			driver = new ChromeDriver();
			abstractPage = new AbstractPage(driver);
			driver.get("https://demo.nopcommerce.com/");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			email = "tamqada@gmail.com";
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
		myaccountPage = PageGeneratorManager.getHeaderMyAccountPage(driver);
		assertTrue(myaccountPage.isCurrentURLContains("customer/info"));
		
		abstractPage.sleepInSecond(1);
		myaccountPage.clickToGenderRadioButton("female");
		myaccountPage.inputToCustomerInfoTextboxs("Automation", "FirstName");
		myaccountPage.inputToCustomerInfoTextboxs("FC", "LastName");
		myaccountPage.selectBirdtDateDropDownList("1", "Day");
		myaccountPage.selectBirdtDateDropDownList("January", "Month");
		myaccountPage.selectBirdtDateDropDownList("1999", "Year");
		myaccountPage.inputToCustomerInfoTextboxs("automationfc.vn@gmail.com", "Email");
		myaccountPage.inputToCustomerInfoTextboxs("Automation FC", "Company");
		myaccountPage.clickToSaveButton();
	}
	
	@Test
	public void TC_02_UpdateAddress() {
		abstractPage.sleepInSecond(1);
		myaccountPage.clickToAddressMenu();
		abstractPage.sleepInSecond(1);
		assertTrue(myaccountPage.isCurrentURLContains("customer/addresses"));
		
		myaccountPage.clickToAddNewButton();
		myaccountPage.inputToAddressTextboxes("FirstName", "Automation");
		myaccountPage.inputToAddressTextboxes("LastName", "FC");
		myaccountPage.inputToAddressTextboxes("Email", "automationfc.vn@gmail.com");
		
		myaccountPage.selectCountryDropdownList("Viet Nam");
		myaccountPage.inputToAddressTextboxes("City", "Da Nang");
		myaccountPage.inputToAddressTextboxes("Address1", "123/04 Le Lai");
		myaccountPage.inputToAddressTextboxes("Address2", "234/05 Hai Phong");
		myaccountPage.inputToAddressTextboxes("ZipPostalCode", "550000");
		myaccountPage.inputToAddressTextboxes("PhoneNumber", "0123456789");
		myaccountPage.inputToAddressTextboxes("FaxNumber", "0987654321");
		
		
		
	}
}
