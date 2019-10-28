package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.HomePageUI;

public class HomePageObject {
	public AbstractPage abstractPage;
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		abstractPage = new AbstractPage(driver);
		this.driver = driver;
	}
	
	public void clickToRegisterLink () {
		abstractPage.waitToElementVisible(HomePageUI.REGISTER_BUTTON);
		abstractPage.clickToElement(HomePageUI.REGISTER_BUTTON);
	}
	
	public void clickToSignOutButton() {
		abstractPage.waitToElementVisible(HomePageUI.SIGN_OUT_BUTTON);
		abstractPage.clickToElement(HomePageUI.SIGN_OUT_BUTTON);
	}
	
	public void clickToLoginButton() {
		abstractPage.waitToElementVisible(HomePageUI.LOGIN_BUTTON);
		abstractPage.clickToElement(HomePageUI.LOGIN_BUTTON);
	}
	public boolean isMyAccountLinkDisplayed() {
		return abstractPage.isElementDisplayed(HomePageUI.MY_ACCOUNT_BUTTON);
	}
}
