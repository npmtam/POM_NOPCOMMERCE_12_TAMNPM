package com.nopcommerce.frontend;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

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

	@Test
	public void TC_01_LoginWithEmptyData() {
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToLoginButton();
		
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.inputToEmailTextBox(registerTestcasess.email);
		loginPage.inputToPasswordButton(registerTestcasess.password);
		loginPage.clickToLoginButton();
		
		homePage = PageGeneratorManager.getHomePage(driver);
		assertTrue(homePage.isMyAccountLinkDisplayed());
		
		
		
	}
}
