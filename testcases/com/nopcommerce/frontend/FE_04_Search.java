package com.nopcommerce.frontend;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.SearchPO;

public class FE_04_Search extends AbstractTest {
	WebDriver driver;
	private HomePageObject homePage;
	private AbstractPage abstractPage;
	private LoginPageObject loginPage;
	private SearchPO searchPage;
	
	String email, password;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\libaries\\chromedriver.exe");
		driver = new ChromeDriver();
		abstractPage = new AbstractPage(driver);
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		email = "tam@gmail.com";
		password = "123123";
		
		log.info("Search - Pre-condition01: Login to account");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToLoginLink();
		loginPage = PageGeneratorManager.getLoginPage(driver);
		abstractPage.sleepInSecond(1);
		abstractPage.sleepInSecond(1);
		loginPage.inputToEmailTextBox(email);
		loginPage.inputToPasswordButton(password);
		loginPage.clickToLoginButton();
		homePage = PageGeneratorManager.getHomePage(driver);
		abstractPage.sleepInSecond(1);
		verifyTrue(loginPage.isHeaderLinksDisplayed("My account"));
		
		log.info("Search - Pre-condition02: Access search page at Footer");
		abstractPage.openMultiplePagesFooter("Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);
		log.info("Search - Pre-condition03: Verify access search page successfully");
		verifyTrue(abstractPage.isCurrentURLContains("search"));
	}

	@Test
	public void TC_01_SearchEmptyData() {
		abstractPage.sleepInSecond(1);
		log.info("Search - TC01 - Step 01: Click to search button");
		searchPage.clickToSearchButton();
		log.info("Search - TC01 - Step 02: Verify error message");
		verifyTrue(searchPage.isErrorMessageEquals("warning", "Search term minimum length is 3 characters"));
	}
	
	@Test
	public void TC_02_SearchWithDataNotExist() {
		abstractPage.sleepInSecond(1);
		log.info("Search - TC02 - Step 01: Search with data not exist");
		searchPage.inputToSearchTextbox("Macbook Pro 2050");
		
		log.info("Search - TC02 - Step 02: Click search button");
		searchPage.clickToSearchButton();
		
		log.info("Search - TC02 - Step 03: Verify error message");
		verifyTrue(searchPage.isErrorMessageEquals("no-result", "No products were found that matched your criteria."));
	}
	
	@Test
	public void TC_03_SearchProductName() {
		log.info("Search - TC03 - Step 01: Search product name");
		searchPage.inputToSearchTextbox("Lenovo");
		
		log.info("Search - TC03 - Step 02: Click search button");
		searchPage.clickToSearchButton();
		
		log.info("Search - TC03 - Verify 2 products displayed");
		verifyTrue(searchPage.isSearchResultDisplayedProduct("IdeaCentre 600 All-in-One PC"));
		verifyTrue(searchPage.isSearchResultDisplayedProduct("Thinkpad X1 Carbon"));
		
	}
}
