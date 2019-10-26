package commons;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
	
	/*   WEB BROWSER   */
	
	public void openURL(WebDriver driver, String url) {
		driver.get(url);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getCurrentPageURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public String getTextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}
	
	public void sendKeyToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}
	
	/*   WEB ELEMENTS   */
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
	
	public void clickToElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		element.click();
	}
	
	public void sendKeyToElement(WebDriver driver, String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(value);
	}
	
	public void selectItemInDropdown(WebDriver driver, String locator, String valueItem) {
		select = new Select(driver.findElement(By.xpath(locator)));
		select.selectByVisibleText(valueItem);
	}
	
	public String getFirstItemInDropdown(WebDriver driver, String locator) {
		select = new Select(driver.findElement(By.xpath(locator)));
		return select.getFirstSelectedOption().getText();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String allItemsLocator, String expectedItem)
			throws InterruptedException {
		jsExecutor = (JavascriptExecutor) driver;
		waitExplicit = new WebDriverWait(driver, longTimeout);
		
		WebElement element = driver.findElement(By.xpath(parentLocator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
		jsExecutor.executeScript("arguments[0].click();", element);
		
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsLocator)));

		List<WebElement> elements = driver.findElements(By.xpath(allItemsLocator));
		for (WebElement item : elements) {
			if (item.getText().equals(expectedItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
				Thread.sleep(1000);
				item.click();
				Thread.sleep(1000);
				break;
			}
		}
	}
	
	public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
		element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attributeName);
	}
	
	public String getTextElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.getText();
	}
	
	public int countNumberOfElement(WebDriver driver, String locator) {
		elements = driver.findElements(By.xpath(locator));
		return elements.size();
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator) {
		element =driver.findElement(By.xpath(locator));
		return element.isEnabled();
	}
	
	public void checkToCheckBox(WebDriver driver, String locator) {
		if (!isElementSelected(driver, locator)) {
			clickToElement(driver, locator);
		}
	}
	public void unCheckToCheckBox(WebDriver driver, String locator) {
		if (isElementSelected(driver, locator)) {
			clickToElement(driver, locator);
		}
	}
	
	public void switchToWindowsByTitle(WebDriver driver, String title) {
		allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWindow = driver.getTitle();
			if (currentWindow.equals(title)) {
				break;
			}
		}
	}

	public boolean closeAllWindowsWithoutParent(WebDriver driver, String parentWindow) {
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
	
	public void hoverMouseToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		element = driver.findElement(By.xpath(locator));
		action.moveToElement(element).perform();
	}
	
	public boolean checkImageLoaded(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth !=\"undefined\" && arguments[0].naturalWidth>0", element);
		if (status) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean verifyTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('"+textExpected+"')[0]");
		return textActual.equals(textExpected);
	}
	
	public void waitToElementVisible(WebDriver driver, String locator) {
		by = By.xpath(locator);
		waitExplicit = new WebDriverWait(driver, longTimeout);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public int randomNumber() {
		Random random = new Random();
		return random.nextInt();
	}
}
