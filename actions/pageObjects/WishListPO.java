package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
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
}
