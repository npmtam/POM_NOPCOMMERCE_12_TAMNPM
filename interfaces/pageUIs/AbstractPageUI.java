package pageUIs;

public class AbstractPageUI {
	
	public static final String HEADER_LINKS(String linkValue) {
		return "//a[@class='"+linkValue+"']";
	}
	
	public static final String FOOTER_LINKS(String linkValue) {
		return "//div[@class='footer']//a[text()='"+linkValue+"']";
	}
}
