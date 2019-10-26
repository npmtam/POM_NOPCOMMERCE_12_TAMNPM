package com.nopcommerce.account;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import commons.AbstractPage;

public class Topic_02_Apply_Abstract_Page {
	WebDriver driver;
	Select select;
	JavascriptExecutor jsExecutor;
	WebDriverWait waitExplicit;
	
	public AbstractPage abstractPage;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\libaries\\chromedriver.exe");
		driver = new ChromeDriver();
		abstractPage = new AbstractPage();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Register() throws InterruptedException {
		abstractPage.openURL(driver, "https://demo.nopcommerce.com/login");
		
		abstractPage.clickToElement(driver, "//a[@class='ico-register']");
		assertTrue(abstractPage.isElementDisplayed(driver, "//div[@class='page registration-page']"));
		
		abstractPage.clickToElement(driver, "//input[@id='gender-male']");
		abstractPage.sendKeyToElement(driver, "//input[@id='FirstName']", "Danny");
		abstractPage.sendKeyToElement(driver, "//input[@id='LastName']", "Cee");
		
		abstractPage.selectItemInDropdown(driver, "//select[@name='DateOfBirthDay']", "10");
		abstractPage.selectItemInDropdown(driver, "//select[@name='DateOfBirthMonth']", "October");
		abstractPage.selectItemInDropdown(driver, "//select[@name='DateOfBirthYear']", "1993");
		
		abstractPage.sendKeyToElement(driver, "//input[@id='Email']", "\"tamnguyen_" + abstractPage.randomNumber() + "@gmail.com\"");
		abstractPage.sendKeyToElement(driver, "//input[@id='Company']", "Step Sister");
		abstractPage.sendKeyToElement(driver, "//input[@id='Password']", "123123");
		abstractPage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123123");
		
		abstractPage.clickToElement(driver, "//input[@id='register-button']");
		
	}

	@Test
	public void TC_02_Login() {
		
	}

}
