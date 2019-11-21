package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class HeaderMyAccountPO extends AbstractPage {
WebDriver driver;

	public HeaderMyAccountPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
