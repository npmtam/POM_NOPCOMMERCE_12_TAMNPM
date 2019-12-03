package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.MyAccountPageUI;

public class HeaderMyAccountPO extends AbstractPage {
WebDriver driver;

	public HeaderMyAccountPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public boolean isCurrentURLContains(String expectedvalue) {
		String currentURL = getCurrentPageURL();
		return currentURL.contains(expectedvalue);
	}
	
	public void clickToGenderRadioButton(String gender) {
		clickToElement(MyAccountPageUI.GENDER_RADIOBUTTON, gender);
	}
	
	public void inputToCustomerInfoTextboxs(String fieldID, String textValue) {
		waitToElementVisible(MyAccountPageUI.CUSTOMER_TEXTBOXS, fieldID);
		sendKeyToElement(MyAccountPageUI.CUSTOMER_TEXTBOXS, textValue, fieldID);
	}
	
	public void selectBirdtDateDropDownList(String valueField, String valueItem) {
		waitToElementVisible(MyAccountPageUI.BIRTHDATE_DROPDOWNLIST, valueField);
		selectItemInDropdown(MyAccountPageUI.BIRTHDATE_DROPDOWNLIST, valueItem, valueField);
	}
	public void clickToSaveButton() {
		waitToElementVisible(MyAccountPageUI.SAVE_BUTTON);
		clickToElement(MyAccountPageUI.SAVE_BUTTON);
	}
	
	public void clickToAddressMenu() {
		waitToElementVisible(MyAccountPageUI.MENU_LINKS, "addresses");
		clickToElement(MyAccountPageUI.MENU_LINKS, "addresses");
	}
	
	public void clickToAddNewButton() {
		waitToElementVisible(MyAccountPageUI.ADD_NEW_BUTTON);
		clickToElement(MyAccountPageUI.ADD_NEW_BUTTON);
	}
	
	public void inputToAddressTextboxes(String textboxName, String textValue) {
		waitToElementVisible(MyAccountPageUI.ADDRESS_TEXTBOXES, textboxName);
		sendKeyToElement(MyAccountPageUI.ADDRESS_TEXTBOXES, textValue, textboxName);
	}
	
	public void selectCountryDropdownList(String countryValue) {
		waitToElementVisible(MyAccountPageUI.COUNTRY_DROPDOWN_LIST);
		selectItemInDropdown(MyAccountPageUI.COUNTRY_DROPDOWN_LIST, countryValue);
	}
	
	public boolean isErrorMessagePresentInDOM() {
		return isElementPresentInDOM(MyAccountPageUI.ERROR_MESSAGE);
	}
}
