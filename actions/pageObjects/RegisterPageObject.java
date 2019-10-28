package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.RegisterPageUI;

public class RegisterPageObject {
	public AbstractPage abstracPage;
	private WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		abstracPage = new AbstractPage(driver);
		this.driver = driver;
	}

	public void inputToFirstNameTextBox(String firstName) {
		abstracPage.waitToElementVisible(RegisterPageUI.FIRSTNAME_TEXTBOX);
		abstracPage.sendKeyToElement(RegisterPageUI.FIRSTNAME_TEXTBOX, firstName);
	}

	public void inputToLastNameTextBox(String lastName) {
		abstracPage.waitToElementVisible(RegisterPageUI.LASTNAME_TEXTBOX);
		abstracPage.sendKeyToElement(RegisterPageUI.LASTNAME_TEXTBOX, lastName);
	}

	public void selectMaleGenderCheckBox() {
		abstracPage.waitToElementVisible(RegisterPageUI.MALE_CHECKBOX);
		abstracPage.clickToElement(RegisterPageUI.MALE_CHECKBOX);
	}
	public void selectFemaleGenderCheckBox() {
		abstracPage.waitToElementVisible(RegisterPageUI.FEMALE_CHECKBOX);
		abstracPage.clickToElement(RegisterPageUI.FEMALE_CHECKBOX);
	}

	public void selectDateInDropDown(String valueItem) {
		abstracPage.waitToElementVisible(RegisterPageUI.DAY_DROPDOWN);
		abstracPage.selectItemInDropdown(RegisterPageUI.DAY_DROPDOWN, valueItem);
	}

	public void selectMonthInDropDown(String valueItem) {
		abstracPage.waitToElementVisible(RegisterPageUI.MONTH_DROPDOWN);
		abstracPage.selectItemInDropdown(RegisterPageUI.MONTH_DROPDOWN, valueItem);
	}

	public void selectYearInDropDown(String valueItem) {
		abstracPage.waitToElementVisible(RegisterPageUI.YEAR_DROPDOWN);
		abstracPage.selectItemInDropdown(RegisterPageUI.YEAR_DROPDOWN, valueItem);
	}

	public void inputToEmailTextBox(String emailValue) {
		abstracPage.waitToElementVisible(RegisterPageUI.EMAIL_TEXTBOX);
		abstracPage.sendKeyToElement(RegisterPageUI.EMAIL_TEXTBOX, emailValue);
	}

	public void inputToCompanyTextBox(String companyName) {
		abstracPage.waitToElementVisible(RegisterPageUI.COMPANY_TEXTBOX);
		abstracPage.sendKeyToElement(RegisterPageUI.COMPANY_TEXTBOX, companyName);
	}

	public void inputToPasswordTextBox(String password) {
		abstracPage.waitToElementVisible(RegisterPageUI.PASSWORD_TEXTBOX);
		abstracPage.sendKeyToElement(RegisterPageUI.PASSWORD_TEXTBOX, password);
	}

	public void inputToConfirmPasswordTextBox(String confirmPassword) {
		abstracPage.waitToElementVisible(RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		abstracPage.sendKeyToElement(RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
	}

	public void clickToRegisterButton() {
		abstracPage.waitToElementVisible(RegisterPageUI.REGISTER_BUTTON);
		abstracPage.clickToElement(RegisterPageUI.REGISTER_BUTTON);

	}

	public void clickToContinueButton() {
		abstracPage.waitToElementVisible(RegisterPageUI.CONTINUE_BUTTON);
		abstracPage.clickToElement(RegisterPageUI.CONTINUE_BUTTON);
	}
	
	public boolean isResultMatched(String expectedResult) {
		abstracPage.waitToElementVisible(RegisterPageUI.RESULT_LABEL);
		return abstracPage.isElementEquals(RegisterPageUI.RESULT_LABEL, expectedResult);		
	}
}
