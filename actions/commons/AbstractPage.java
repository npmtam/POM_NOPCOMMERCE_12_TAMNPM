package commons;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.HeaderMyAccountPO;
import pageUIs.AbstractPageUI;

public class AbstractPage {
	WebDriver driver;
	WebElement element;
	By by;
	Select select;
	JavascriptExecutor jsExecutor;
	WebDriverWait waitExplicit;
	List<WebElement> elements;
	Set<String> allWindows;
	Actions action;
	long shortTimeout = 5;
	long longTimeout = 30;
	
	public AbstractPage(WebDriver driver) {
		this.driver = driver;
		action = new Actions(driver);
	}
	
	
	
	/*   WEB BROWSER   */
	
	public void openURL(String url) {
		driver.get(url);
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getCurrentPageURL() {
		return driver.getCurrentUrl();
	}
	
	public void backToPage() {
		driver.navigate().back();
	}
	
	public void forwardToPage() {
		driver.navigate().forward();
	}
	
	public void refreshPage() {
		driver.navigate().refresh();
	}
	
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}
	
	public void cancelAlert() {
		driver.switchTo().alert().dismiss();
	}
	
	public String getTextAlert() {
		return driver.switchTo().alert().getText();
	}
	
	public void sendKeyToAlert(String value) {
		driver.switchTo().alert().sendKeys(value);
	}
	
	
	
	
	
	/*   WEB ELEMENTS   */
	
	
	public void clickToElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		element.click();
	}
	
	public void sendKeyToElement(String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(value);
	}
	
	public void selectItemInDropdown(String locator, String valueItem) {
		select = new Select(driver.findElement(By.xpath(locator)));
		select.selectByVisibleText(valueItem);
	}
	
	public String getFirstItemInDropdown(String locator) {
		select = new Select(driver.findElement(By.xpath(locator)));
		return select.getFirstSelectedOption().getText();
	}
	
	public void scrollToElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	public void selectItemInCustomDropdown(String parentLocator, String allItemsLocator, String expectedItem) {
		jsExecutor = (JavascriptExecutor) driver;
		waitExplicit = new WebDriverWait(driver, longTimeout);
		
		WebElement element = driver.findElement(By.xpath(parentLocator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
		jsExecutor.executeScript("arguments[0].click();", element);
		
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsLocator)));

		elements = driver.findElements(By.xpath(allItemsLocator));
		for (WebElement item : elements) {
			if (item.getText().equals(expectedItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
				sleepInSecond(1);
				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}
	
	public String getAttributeValue(String locator, String attributeName) {
		element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attributeName);
	}
	
	public String getTextElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.getText();
	}
	
	public int countNumberOfElement(String locator) {
		elements = driver.findElements(By.xpath(locator));
		return elements.size();
	}
	
	public boolean isElementDisplayed(String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}
	
	public boolean isElementSelected(String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}
	
	public boolean isElementEnabled(String locator) {
		element =driver.findElement(By.xpath(locator));
		return element.isEnabled();
	}
	
	public void checkToCheckBox(String locator) {
		if (!isElementSelected(locator)) {
			clickToElement(locator);
		}
	}
	public void unCheckToCheckBox(String locator) {
		if (isElementSelected(locator)) {
			clickToElement(locator);
		}
	}
	
	public void switchToWindowsByTitle(String title) {
		allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWindow = driver.getTitle();
			if (currentWindow.equals(title)) {
				break;
			}
		}
	}

	public boolean closeAllWindowsWithoutParent(String parentWindow) {
		allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentWindow)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow); 

		if (driver.getWindowHandles().size() == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public void switchToFrameOrIframe(String locator) {
		element = driver.findElement(By.xpath(locator));
		driver.switchTo().frame(element);
	}
	
	public void switchToParentPage() {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		action.moveToElement(element).perform();
	}
	
	public void doubleClickToElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		action.doubleClick(element).perform();
	}
	
	public void sendKeyBoardToElement(String locator, Keys key) {
		element = driver.findElement(By.xpath(locator));
		action.sendKeys(element, key).perform();
	}
	
	public boolean checkImageLoaded(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth !=\"undefined\" && arguments[0].naturalWidth>0", element);
		if (status) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean verifyTextInInnerText(String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('"+textExpected+"')[0]");
		return textActual.equals(textExpected);
	}
	
	public void waitToElementVisible(String locator) {
		by = By.xpath(locator);
		waitExplicit = new WebDriverWait(driver, longTimeout);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public void waitToElementClickable(String locator) {
		by = By.xpath(locator);
		waitExplicit = new WebDriverWait(driver, longTimeout);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(by));
		
	}
	
	public boolean isElementEquals(String locator, String expectedResult) {
		element = driver.findElement(By.xpath(locator));
		String actualText = element.getText();
		return actualText.equals(expectedResult);
		
	}
	
	public void sleepInSecond(long numberInSecond) {
		try {
			Thread.sleep(numberInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	public int randomNumber() {
		Random random = new Random();
		return random.nextInt();
	}
	
	
	
	
	public HeaderMyAccountPO openHeaderMyAccountPage() {
		waitToElementVisible(AbstractPageUI.HEADER_MY_ACCOUNT_LINK);
		clickToElement(AbstractPageUI.HEADER_MY_ACCOUNT_LINK);
		return PageGeneratorManager.getHeaderMyAccountPage(driver);
	}
}
