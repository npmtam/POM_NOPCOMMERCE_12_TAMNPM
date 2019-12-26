package pageObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPage;
import pageUIs.SortPageUI;

public class SortPO extends AbstractPage {
	WebDriver driver;

	public SortPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void selectSortByDropdown(String itemSelect) {
		selectItemInDropdown(SortPageUI.SORTBY_DROPDOWNLIST, itemSelect);
	}

	public boolean checkProductSortNameAToZ() {
		return checkOrderListElements(SortPageUI.LIST_TITLE_PRODUCTS);
	}

}
