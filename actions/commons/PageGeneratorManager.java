package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.FooterShoppingCartPO;
import pageObjects.HeaderMyAccountPO;
import pageObjects.HeaderWishListPO;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.ProductDetailsPO;
import pageObjects.RegisterPageObject;

public class PageGeneratorManager {

	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}

	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}

	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static HeaderMyAccountPO getHeaderMyAccountPage(WebDriver driver) {
		return new HeaderMyAccountPO(driver);
	}

	public static HeaderWishListPO getHeaderWishListPage(WebDriver driver) {
		return new HeaderWishListPO(driver);
	}

	public static FooterShoppingCartPO getFooterShoppingCartPage(WebDriver driver) {
		return new FooterShoppingCartPO(driver);
	}
	
	public static ProductDetailsPO getProductDetailsPage(WebDriver driver) {
		return new ProductDetailsPO(driver);
	}
}
