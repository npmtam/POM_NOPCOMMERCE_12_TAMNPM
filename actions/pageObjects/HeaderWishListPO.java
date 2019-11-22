package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class HeaderWishListPO extends AbstractPage {
	WebDriver driver;
	
	public HeaderWishListPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}
