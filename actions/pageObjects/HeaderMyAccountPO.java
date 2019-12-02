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
	
	public void inputToCustomerInfoTextboxs(String textValue, String fieldID) {
		waitToElementVisible(MyAccountPageUI.CUSTOMER_TEXTBOXS, fieldID);
		sendKeyToElement(MyAccountPageUI.CUSTOMER_TEXTBOXS, textValue, fieldID);
	}
	
	public void selectBirdtDateDropDownList(String valueItem, String valueField) {
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
		waitToElementVisible(MyAccountPageUI.ADDRESS_TEXTBOXES, "CountryId");
		selectItemInDropdown(MyAccountPageUI.ADDRESS_TEXTBOXES, countryValue, "CountryId");
	}
}
