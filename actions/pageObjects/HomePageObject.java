package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUIs.HomePageUI;

public class HomePageObject extends AbstractPage {
WebDriver driver;

	public HomePageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public RegisterPageObject clickToRegisterLink () {
		waitToElementVisible(HomePageUI.REGISTER_BUTTON);
		clickToElement(HomePageUI.REGISTER_BUTTON);
		return PageGeneratorManager.getRegisterPage(driver);
	}
	
	public HomePageObject clickToSignOutButton() {
		waitToElementVisible(HomePageUI.SIGN_OUT_BUTTON);
		clickToElement(HomePageUI.SIGN_OUT_BUTTON);
		return PageGeneratorManager.getHomePage(driver);
	}
	
	public LoginPageObject clickToLoginButton() {
		waitToElementVisible(HomePageUI.LOGIN_BUTTON);
		clickToElement(HomePageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getLoginPage(driver);
	}
	public boolean isMyAccountLinkDisplayed() {
		return isElementDisplayed(HomePageUI.MY_ACCOUNT_BUTTON);
	}
	
	public HeaderMyAccountPO clickToMyAccountHeader() {
		waitToElementVisible(HomePageUI.MY_ACCOUNT_BUTTON);
		clickToElement(HomePageUI.MY_ACCOUNT_BUTTON);
		return PageGeneratorManager.getHeaderMyAccountPage(driver);
	}
}
