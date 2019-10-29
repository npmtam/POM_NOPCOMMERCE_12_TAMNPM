package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.HomePageUI;

public class HomePageObject extends AbstractPage {
//	public AbstractPage abstractPage;
//	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		super(driver);
	}
	
	public void clickToRegisterLink () {
		waitToElementVisible(HomePageUI.REGISTER_BUTTON);
		clickToElement(HomePageUI.REGISTER_BUTTON);
	}
	
	public void clickToSignOutButton() {
		waitToElementVisible(HomePageUI.SIGN_OUT_BUTTON);
		clickToElement(HomePageUI.SIGN_OUT_BUTTON);
	}
	
	public void clickToLoginButton() {
		waitToElementVisible(HomePageUI.LOGIN_BUTTON);
		clickToElement(HomePageUI.LOGIN_BUTTON);
	}
	public boolean isMyAccountLinkDisplayed() {
		return isElementDisplayed(HomePageUI.MY_ACCOUNT_BUTTON);
	}
}
