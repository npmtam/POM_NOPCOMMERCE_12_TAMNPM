package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.HomePageUI;
import pageUIs.ProductDetailsUI;

public class WishListPO extends AbstractPage {
	WebDriver driver;
	
	public WishListPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public boolean isWishListButtonDisplayed() {
		return isElementDisplayed(ProductDetailsUI.ADD_TO_WISHLIST_BUTTON);
	}
	
	public void clickAddToWishListButton() {
		waitToElementClickable(ProductDetailsUI.ADD_TO_WISHLIST_BUTTON);
		clickToElement(ProductDetailsUI.ADD_TO_WISHLIST_BUTTON);
	}
	
	public boolean isSuccessMessageContains(String expectedMsg) {
		String actualMsg = getTextElement(ProductDetailsUI.SUCCESS_MESSAGE);
		return actualMsg.contains(expectedMsg);
	}
	
	public void clickToAccessWishList() {
		waitToElementClickable(ProductDetailsUI.WISHLIST_LINK_IN_SUCCESS_MSG);
		clickToElement(ProductDetailsUI.WISHLIST_LINK_IN_SUCCESS_MSG);
	}
	
	public boolean isProductAddedToWishList(String productName) {
		String actualName = getTextElement(ProductDetailsUI.PRODUCT_TITLE_WISHLIST);
		return actualName.contains(productName);
	}
	
	public void clickToURLSharing() {
		waitToElementClickable(ProductDetailsUI.WISHLIST_URL_SHARING);
		clickToElement(ProductDetailsUI.WISHLIST_URL_SHARING);
	}
	
	public void clickToAddToCartCheckbox() {
		waitToElementVisible(ProductDetailsUI.ADD_TO_CARD_CHECKBOX);
		clickToElement(ProductDetailsUI.ADD_TO_CARD_CHECKBOX);
	}
	
	public boolean isAddToCardCheckboxSelected() {
		return isElementSelected(ProductDetailsUI.ADD_TO_CARD_CHECKBOX);
	}
	public void clickToAddToCardButton() {
		waitToElementClickable(ProductDetailsUI.ADD_TO_CARD_BUTTON);
		clickToElement(ProductDetailsUI.ADD_TO_CARD_BUTTON);
	}
	
	public boolean isWishListEmpty() {
		return isElementDisplayed(ProductDetailsUI.EMPTY_WISHLIST_MSG);
	}
	
	public void clickToRemoveCheckbox() {
		waitToElementClickable(ProductDetailsUI.REMOVE_CHECKBOX);
		clickToElement(ProductDetailsUI.REMOVE_CHECKBOX);
	}
	
	public void clickToUpdateWishListButton() {
		waitToElementClickable(ProductDetailsUI.UPDATE_WISHLIST_BUTTON);
		clickToElement(ProductDetailsUI.UPDATE_WISHLIST_BUTTON);
	}
	
	public boolean isErrorMessageEquals(String expectedText) {
		String actualResult = getTextElement(ProductDetailsUI.EMPTY_WISHLIST_MSG);
		return actualResult.equals(expectedText);
	}
	
	public boolean isProducstPresentEquals(int expectedResult) {
		return countElements(ProductDetailsUI.PRODUCT_TITLE_WISHLIST) == expectedResult;
	}
	
}
