package com.nopcommerce.frontend;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.SearchPO;
import pageObjects.SortPO;

public class FE_05_Sort extends AbstractTest{
	WebDriver driver;
	private HomePageObject homePage;
	private AbstractPage abstractPage;
	private LoginPageObject loginPage;
	private SearchPO searchPage;
	private SortPO sortPage;
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", ".\\libaries\\chromedriver.exe");
		driver = new ChromeDriver();
		abstractPage = new AbstractPage(driver);
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		log.info("Precondition - Select Notebooks from Computers menu");
		abstractPage.openDynamicSubMenu("Computers", "Notebooks");
	}
	
	@Test
	public void TC_01_SortByNameAToZ() {
		log.info("Sort - TC01 - Step 01: Select sort by: Name: A to Z");
		sortPage = PageGeneratorManager.getSortPage(driver);
		sortPage.selectSortByDropdown("Name: A to Z");
		
		log.info("Sort - TC02 - Step 02: Verify the products is ordered by name A to Z");
		sortPage.checkProductSortNameAToZ();
		
	}
}
