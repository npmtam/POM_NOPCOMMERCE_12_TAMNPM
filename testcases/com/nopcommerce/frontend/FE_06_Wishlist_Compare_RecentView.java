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
import pageObjects.WishListPO;

public class FE_06_Wishlist_Compare_RecentView extends AbstractTest {
	WebDriver driver;
	private AbstractPage abstractPage;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private WishListPO wishListPage;
	private FE_01_Register registerTestCases;
	String email, productName;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", ".\\libaries\\chromedriver.exe");
		driver = new ChromeDriver();
		abstractPage = new AbstractPage(driver);
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		email = "tamqada@gmail.com";
		productName = "Apple MacBook Pro 13-inch";

		log.info("Precondition: Login");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToLoginLink();
		abstractPage.sleepInSecond(1);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.inputToEmailTextBox(email);
		loginPage.inputToPasswordButton("123123");
		loginPage.clickToLoginButton();
		verifyTrue(loginPage.isHeaderLinksDisplayed("My account"));
		
		log.info("Pre-condition: Open product details");
		homePage = PageGeneratorManager.getHomePage(driver);
		abstractPage.sleepInSecond(1);
		homePage.clickToProductTitle(productName);
		
		wishListPage = PageGeneratorManager.getWishListPage(driver);
		verifyTrue(wishListPage.isWishListButtonDisplayed());
	}
	
	@Test
	public void TC01_AddToWishList() {
		log.info("WishList - TC01 - Step 01: Click to add to wish list button");
		wishListPage.clickAddToWishListButton();
		
		log.info("WishList - TC01 - Step 02: Verify success message");
		wishListPage.isSuccessMessageContains("The product has been added to your ");
		
		log.info("WishList - TC01 - Step 03: Access wishlist page");
		wishListPage.clickToAccessWishList();
		
		log.info("WishList - TC01 - Step 04: Verify product added to wish list");
		verifyTrue(wishListPage.isProductAddedToWishList(productName));
		
		log.info("WishList - TC01 - Step 05: Click to Your wishlist URL for sharing");
		abstractPage.sleepInSecond(1);
		wishListPage.clickToURLSharing();
		
		log.info("WishList - TC01 - Step 06: Verify product present in Wishlist URL for sharing");
		verifyTrue(wishListPage.isProductAddedToWishList(productName));
	}
	
	@Test
	public void TC_02_AddProductToCartFromWishListPage() {
		log.info("WishList - TC02 - Step 01: Access wishlist page");
		homePage = PageGeneratorManager.getHomePage(driver);
		abstractPage.sleepInSecond(1);
		homePage.clickToLogoToBackHome();
		abstractPage.sleepInSecond(1);
		homePage.clickToHeaderWishList();
		verifyTrue(abstractPage.isSubPageTitleEquals("Wishlist"));
		
		log.info("WishList - TC02 - Step 02: Add product to cart");
		wishListPage = PageGeneratorManager.getWishListPage(driver);
		abstractPage.sleepInSecond(1);
		wishListPage.clickToAddToCartCheckbox();
		verifyTrue(wishListPage.isAddToCardCheckboxSelected());
		wishListPage.clickToAddToCardButton();
		
		log.info("WishList - TC02 - Step 03: Verify the product was added to Cart");
		verifyTrue(abstractPage.isSubPageTitleEquals("Shopping cart"));
		verifyTrue(wishListPage.isProductAddedToWishList(productName));
		
		log.info("WishList - TC02 - Step 04: Verify the product was removed from Wishlist");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToHeaderWishList();
		
		wishListPage = PageGeneratorManager.getWishListPage(driver);
		verifyTrue(wishListPage.isWishListEmpty());
	}
	
	@Test
	public void TC_03_RemoveProductInWishlist() {
		log.info("WishList - TC03  - Step 01: Add product to wishlist");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToLogoToBackHome();
		abstractPage.sleepInSecond(1);
		homePage.clickToHeaderWishList();
		
		
	}
	
}
