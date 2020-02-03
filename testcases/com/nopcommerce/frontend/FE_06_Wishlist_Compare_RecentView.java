package com.nopcommerce.frontend;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
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
	String email, productName1, productName2, password;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", ".\\libaries\\chromedriver.exe");
		driver = new ChromeDriver();
		abstractPage = new AbstractPage(driver);
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		email = "tamqada@gmail.com";
		productName1 = "Apple MacBook Pro 13-inch";
		productName2 = "HTC One M8 Android L 5.0 Lollipop";
		password = "123123";

		log.info("Precondition: Login");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToLoginLink();
		abstractPage.sleepInSecond(1);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.inputToEmailTextBox(email);
		loginPage.inputToPasswordButton(password);
		loginPage.clickToLoginButton();
		verifyTrue(loginPage.isHeaderLinksDisplayed("My account"));
		
		log.info("Pre-condition: Open product details");
		homePage = PageGeneratorManager.getHomePage(driver);
		abstractPage.sleepInSecond(1);
		homePage.clickToProductTitle(productName1);
		
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
		wishListPage.clickToAccessLinkFromSuccessMsg("wishlist");
		
		log.info("WishList - TC01 - Step 04: Verify product added to wish list");
		verifyTrue(wishListPage.isProductAddedToWishList(productName1));
		
		log.info("WishList - TC01 - Step 05: Click to Your wishlist URL for sharing");
		abstractPage.sleepInSecond(1);
		wishListPage.clickToURLSharing();
		
		log.info("WishList - TC01 - Step 06: Verify product present in Wishlist URL for sharing");
		verifyTrue(wishListPage.isProductAddedToWishList(productName1));
	}
	
//	@Test
	public void TC_02_AddProductToCartFromWishListPage() {
		log.info("WishList - TC02 - Step 01: Access wishlist page");
		homePage = PageGeneratorManager.getHomePage(driver);
//		abstractPage.sleepInSecond(1);
//		homePage.clickToLogoToBackHome();
//		abstractPage.sleepInSecond(1);
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
		verifyTrue(wishListPage.isProductAddedToWishList(productName1));
		
		log.info("WishList - TC02 - Step 04: Verify the product was removed from Wishlist");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToHeaderWishList();
		
		wishListPage = PageGeneratorManager.getWishListPage(driver);
		verifyTrue(wishListPage.isWishListEmpty());
	}
	
//	@Test
	public void TC_03_RemoveProductInWishlist() {
		log.info("WishList - TC03  - Step 01: Add product to wishlist");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToLogoToBackHome();
		abstractPage.sleepInSecond(1);
		homePage.clickToProductTitle(productName1);
		
		wishListPage = PageGeneratorManager.getWishListPage(driver);
		wishListPage.clickAddToWishListButton();
		
		wishListPage.isSuccessMessageContains("The product has been added to your ");
		
		log.info("WishList - TC03 - Step 02: Access wishlist page");
		wishListPage.clickToAccessLinkFromSuccessMsg("wishlist");
		
		log.info("WishList - TC03 - Step 03: Select checkbox remove");
		abstractPage.sleepInSecond(1);
		wishListPage.clickToRemoveCheckbox();
		
		log.info("WishList - TC03 - Step 04: Click button UPDATE WISHLIST");
		wishListPage.clickToUpdateWishListButton();
		
		log.info("WishList - TC03 - Step 05: Verify message: The wishlist is empty!");
		wishListPage.isErrorMessageEquals("The wishlist is empty!");
		
		log.info("WishList - TC03 - Step 06: Verify the product is not present in WishList");
		verifyTrue(wishListPage.isProducstPresentEquals(0));
	}
	
	@Test
	public void TC_04_Add_Product_To_Compare() {
		homePage = PageGeneratorManager.getHomePage(driver);
		log.info("WishList - TC04 - Step 01: Click on the logo to turn back Home");
		abstractPage.sleepInSecond(1);
		homePage.clickToLogoToBackHome();
		abstractPage.sleepInSecond(1);
		
		log.info("WishList - TC04 - Step 02: Select a product");
		homePage.clickToProductTitle("HTC One M8");
		
		log.info("WishList - TC04 - Step 03: Click to add to compare list button");
		wishListPage = PageGeneratorManager.getWishListPage(driver);
		wishListPage.clickToAddToCompareButton();
		
		log.info("WishList - TC04 - Step 04: Verify success message appears");
		verifyTrue(wishListPage.isSuccessMessageContains("The product has been added to your product comparison"));
		
		log.info("WishList - TC04 - Step 05: Click on the logo to turn back Home");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToLogoToBackHome();
		abstractPage.sleepInSecond(1);
		
		log.info("WishList - TC04 - Step 06: Select another product");
		homePage.clickToProductTitle(productName1);

		log.info("WishList - TC04 - Step 07: Click to add to compare list button");
		wishListPage = PageGeneratorManager.getWishListPage(driver);
		wishListPage.clickToAddToCompareButton();
		abstractPage.sleepInSecond(1);
		
		log.info("WishList - TC04 - Step 08: Access product comparison list");
		wishListPage.clickToAccessLinkFromSuccessMsg("product comparison");
		abstractPage.sleepInSecond(1);
		verifyTrue(wishListPage.isCurrentURLContains("compareproducts"));
		
		log.info("WishList - TC04 - Step 09: Verify product info in Comparison list - price");
		verifyTrue(wishListPage.isPriceOfProductEquals(productName1, "$1,800.00"));
		verifyTrue(wishListPage.isPriceOfProductEquals(productName2, "$245.00"));
		
		log.info("WishList - TC04 - Step 10: Click 'CLEAR LIST' button");
		wishListPage.clickToClearListButton();
		
		log.info("WishList - TC04 - Step 11: Verify error message appears");
		verifyTrue(wishListPage.isErrorMessageEquals("You have no items to compare."));
		
		log.info("WishList - TC04 - Step 12: Verify no product in list");
		wishListPage.isProducstPresentEquals(0);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
