package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.HomePageUI;

public class HomePageObject {
	public AbstractPage abstracPage;
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		abstracPage = new AbstractPage(driver);
		this.driver = driver;
	}
	
	public void clickToRegisterLink () {
		abstracPage.waitToElementVisible(HomePageUI.REGISTER_BUTTON);
		abstracPage.clickToElement(HomePageUI.REGISTER_BUTTON);
	}
	
	public boolean isMyAccountLinkDisplayed() {
		return abstracPage.isElementDisplayed(HomePageUI.MY_ACCOUNT_LINK);
	}
}
