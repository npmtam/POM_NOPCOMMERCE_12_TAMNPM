package pageUIs;

public class AbstractPageUI {
	
	public static final String HEADER_LINKS = "//div[@class='header']//a[text()='%s']";
	public static final String HEADER_MENU_DYNAMIC = "//ul[@class='top-menu notmobile']//a[contains(text(), '')]";
	public static final String HEADER_SUB_MENU_DYNAMIC = "//ul[@class='sublist first-level']//a[contains(text(), '')]";
	public static final String FOOTER_LINKS = "//div[@class='footer']//a[text()='%s']";
}
 