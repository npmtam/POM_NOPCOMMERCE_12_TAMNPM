package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.SearchPageUI;

public class SearchPO extends AbstractPage{
	WebDriver driver;
	
	public SearchPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void clickToSearchButton() {
		waitToElementVisible(SearchPageUI.SEARCH_BUTTON);
		clickToElement(SearchPageUI.SEARCH_BUTTON);
	}
	
	public void inputToSearchTextbox(String textValue) {
		waitToElementVisible(SearchPageUI.SEARCH_TEXTBOX);
		sendKeyToElement(SearchPageUI.SEARCH_TEXTBOX, textValue);
	}
	
	public boolean isErrorMessageEquals(String classValue, String expectedValue) {
		String errorMsg = getTextElement(SearchPageUI.ERROR_MESSAGE, classValue);
		return errorMsg.equals(expectedValue);
	}
	
	public boolean isSearchResultDisplayedProduct(String titleContains) {
		waitToElementVisible(SearchPageUI.PRODUCT_TITLE_IN_RESULT, titleContains);
		return isElementDisplayed(SearchPageUI.PRODUCT_TITLE_IN_RESULT, titleContains);
	}

}
