package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends AbstractPage {
//	public AbstractPage abstracPage;
//	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		super(driver);
	}
	
	public void inputToEmailTextBox(String emailAddress) {
		waitToElementVisible(LoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(LoginPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void inputToPasswordButton(String password) {
		waitToElementVisible(LoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(LoginPageUI.PASSWORD_TEXTBOX, password);
	}
	
	public void clickToLoginButton() {
		waitToElementVisible(LoginPageUI.LOGIN_BUTTON);
		clickToElement(LoginPageUI.LOGIN_BUTTON);
	}
}
