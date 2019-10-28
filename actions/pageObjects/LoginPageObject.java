package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.LoginPageUI;

public class LoginPageObject {
	public AbstractPage abstracPage;
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		abstracPage = new AbstractPage(driver);
		this.driver = driver;
	}
	
	public void inputToEmailTextBox(String emailAddress) {
		abstracPage.waitToElementVisible(LoginPageUI.EMAIL_TEXTBOX);
		abstracPage.sendKeyToElement(LoginPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void inputToPasswordButton(String password) {
		abstracPage.waitToElementVisible(LoginPageUI.PASSWORD_TEXTBOX);
		abstracPage.sendKeyToElement(LoginPageUI.PASSWORD_TEXTBOX, password);
	}
	
	public void clickToLoginButton() {
		abstracPage.waitToElementVisible(LoginPageUI.LOGIN_BUTTON);
		abstracPage.clickToElement(LoginPageUI.LOGIN_BUTTON);
	}
}
