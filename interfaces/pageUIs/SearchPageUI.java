package pageUIs;

public class SearchPageUI {
	public static final String SEARCH_TEXTBOX = "//input[@class='search-text']";
	public static final String SEARCH_BUTTON = "//input[contains(@class, 'search-button')]";
	public static final String ERROR_MESSAGE = "//div[@class='search-results']//div[@class='%s']";
	public static final String PRODUCT_TITLE_IN_RESULT = "//h2[@class='product-title']/a[contains(text(), '%s')]";
}
