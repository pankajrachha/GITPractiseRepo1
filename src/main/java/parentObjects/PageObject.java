package parentObjects;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

/**
 * All the module level classes will be the subclasses of this class. This class
 * defines some Basic methods which is useful for web browser testing. And
 * sharing the access to all module level pages.
 * 
 * @author Gaurav Kumar
 * @since 10th October 2020
 * 
 */

public class PageObject  {

	public static WebDriver driver;
	
//	@BeforeClass(alwaysRun = true)
//	public void initDriver() {
//		Intialization();
//	}

	/**
	 * Check is element displaying in the page
	 *
	 * @param locator
	 *            Element located value
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 * @return Returns <code>true</code> if locator is present, otherwise returns
	 *         <code>false</code>
	 */
	protected boolean isDisplayed(By locator, long waitTimeInSecs) {
		boolean flag;
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTimeInSecs);
			wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(locator)));
			driver.findElement(locator).isDisplayed();
			flag = true;
		} catch (Exception e) {
//			log.error("Fail to check isDisplayed : " + locator);
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * Check is element displaying in the page with explicit wait
	 *
	 * @param locator
	 *            Element located value
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 * @return Returns <code>true</code> if locator is present, otherwise returns
	 *         <code>false</code>
	 */
	protected boolean isLocatorPresent(By locator, long waitTimeInSecs) {
		boolean flag;
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTimeInSecs);
			wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(locator)));
			driver.findElement(locator).isDisplayed();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * Explicit wait with By Abstract class
	 *
	 * @param locator
	 *            Element located value
	 * @param elementName
	 *            Name of the located element
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 * @return Returns the <code>WebElement</code> if it is visible otherwise
	 *         returns <code>null</code>
	 */
	protected WebElement isElementVisible(By locator, String elementName, long waitTimeInSecs) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTimeInSecs);
			element = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(locator)));
		} catch (Exception e) {
			e.printStackTrace();
//			log.error("FAIL: " + elementName + " is not visible which is located at " + locator);
		}
		return element;
	}

	/**
	 * Explicit wait with WebElement Interface
	 *
	 * @param element
	 *            The located WebElement
	 * @param elementName
	 *            Name of the located element
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 * @return Returns <code>true</code> if element is present, otherwise returns
	 *         <code>false</code>
	 */
	protected boolean isElementVisible(WebElement element, String elementName, long waitTimeInSecs) {
		boolean isElementFound = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTimeInSecs);
//			log.info("Waiting " + waitTimeInSecs + " secs for visibility of " + elementName);

			wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
//			log.info("Element " + elementName + " is now visible.");
			isElementFound = true;
		} catch (Exception e) {
			e.printStackTrace();
//			log.error("Element " + elementName + " is not visible.");
		}
		return isElementFound;
	}

	/**
	 * Click on <code>WebElement</code> of the requested selector type locator
	 *
	 * @param locator
	 *            Element located value
	 * @param elementName
	 *            Name of element to be clicked
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 * @return Returns the clicked <code>WebElement</code>
	 */
	protected WebElement click(By locator, String elementName, long waitTimeInSecs) throws Exception {
		WebElement element = null;
		try {
			isElementVisible(locator, elementName, waitTimeInSecs);
			element = driver.findElement(locator);
			element.click();
//			log.info("PASS: sucessfully clicked on " + elementName);
		} catch (Exception e) {
//			log.error("FAIL: To click on " + elementName + "  which is located at " + locator);
			e.printStackTrace();
			throw new Exception("FAIL: To click on " + elementName + "  which is located at " + locator);
		}
		return element;
	}

	/**
	 * Click on <code>WebElement</code> of the requested selector type element
	 *
	 * @param element
	 *            Located value
	 * @param elementName
	 *            Name of element to be clicked
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 * @return Returns the clicked <code>WebElement</code>
	 */
	protected WebElement click(WebElement element, String elementName, long waitTimeInSecs) throws Exception {
		try {
			isElementVisible(element, elementName, waitTimeInSecs);
			element.click();
//			log.info("PASS: sucessfully clicked on " + elementName);
		} catch (Exception e) {
			e.printStackTrace();
//			log.error("FAIL: To click on " + elementName + "  which is located at " + element);
			throw new Exception("FAIL: To click on " + elementName + "  which is located at " + element);
		}

		return element;
	}

	/**
	 * Click on <code>WebElement</code> by the JavaScript of the requested selector
	 * type locator
	 *
	 * @param locator
	 *            Element located value
	 * @param elementName
	 *            Name of element to be clicked
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 * @return Returns the clicked <code>WebElement</code>
	 */

	protected WebElement clickByJavaScriptExecutor(By locator, String elementName, long waitTimeInSecs)
			throws Exception {
		WebElement element = null;
		try {
			isElementVisible(locator, elementName, waitTimeInSecs);
			element = driver.findElement(locator);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
//			log.info("PASS: sucessfully clicked on " + elementName);
		} catch (Exception e) {
//			log.error("FAIL: To click on " + elementName + "  which is located at " + locator);
			e.printStackTrace();
			throw new Exception("FAIL: To click on " + elementName + "  which is located at " + locator);
		}
		return element;
	}

	/**
	 * Clear the <code>WebElement</code> of the requested selector type locator
	 *
	 * @param locator
	 *            Element located value
	 * @param elementName
	 *            Name of element to be cleared
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 * @return Returns <code>true</code> if element is clear, otherwise returns
	 *         <code>false</code>
	 */
	protected boolean clear(By locator, String elementName, long waitTimeInSecs) {
		boolean isTextCleared = false;
		WebElement element = null;
		try {
			isElementVisible(locator, elementName, waitTimeInSecs);
			element = driver.findElement(locator);
			element.clear();
			isTextCleared = true;
//			log.info("PASS: Cleared text from element " + elementName);
		} catch (Exception e) {
			e.printStackTrace();
//			log.error("FAIL: To clear value on " + elementName + "  which is located at " + locator);
		}
		return isTextCleared;
	}

	/**
	 * Send the value to <code>WebElement</code> of the requested selector type
	 * locator
	 *
	 * @param locator
	 *            Element located value
	 * @param sendKey
	 *            Value which needs to be entered
	 * @param elementName
	 *            Name of element to enter the value
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 * @return Returns the <code>WebElement</code>
	 */
	protected WebElement sendKeys(By locator, String sendkey, String elementName, long waitTimeInSecs) {
		WebElement element = null;
		try {
			isElementVisible(locator, elementName, waitTimeInSecs);
			clear(locator, elementName, waitTimeInSecs);
			element = driver.findElement(locator);
			element.sendKeys(sendkey);
//			log.info("PASS: In " + elementName + " sucessfully entered value = " + sendkey);
		} catch (Exception e) {
			e.printStackTrace();
//			log.error("FAIL: To send value on " + elementName + "  which is located at " + locator);
		}
		return element;
	}

	/**
	 * Send the value one by one to <code>WebElement</code> of the requested
	 * selector type locator
	 *
	 * @param locator
	 *            Element located value
	 * @param sendKey
	 *            Value which needs to be entered
	 * @param elementName
	 *            Name of element to enter the value
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 * @return Returns the <code>WebElement</code>
	 */
	protected WebElement sendKeysValuesOneByOne(By locator, String sendKey, String elementName, long waitTimeInSecs) {
		WebElement element = null;
		try {
			isElementVisible(locator, elementName, waitTimeInSecs);
			clear(locator, elementName, waitTimeInSecs);
			String value = sendKey;
			for (int i = 0; i < value.length(); i++) {
				char c = value.charAt(i);
				String s = new StringBuilder().append(c).toString();
				element = driver.findElement(locator);
				element.sendKeys(s);
			}
//			log.info("PASS: In " + elementName + " sucessfully entered value = " + sendKey);
		} catch (Exception e) {
			e.printStackTrace();
//			log.error("FAIL: To send value on " + elementName + "  which is located at " + locator);
		}
		return element;
	}

	/**
	 * Send the value by JavaScript to <code>WebElement</code> of the requested
	 * selector type locator
	 *
	 * @param locator
	 *            Element located value
	 * @param sendKey
	 *            Value which needs to be entered
	 * @param elementName
	 *            Name of element by which get the text
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 * @return Returns the <code>WebElement</code>
	 */
	protected WebElement sendKeysByJavaScript(By locator, String sendKey, String elementName, long waitTimeInSecs) {
		WebElement element = null;
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			isElementVisible(locator, elementName, waitTimeInSecs);
			clear(locator, elementName, waitTimeInSecs);
			element = driver.findElement(locator);
			String value = sendKey;
			for (int i = 0; i < value.length(); i++) {
				char c = value.charAt(i);
				String s = new StringBuilder().append(c).toString();
//				addDelay(FrameworkConstants.WAIT_5, "");
				jsExecutor.executeScript("arguments[0].value=' " + s + "'", element);
			}
//			log.info("PASS: In " + elementName + " sucessfully entered value = " + sendKey);
		} catch (Exception e) {
//			log.error("FAIL: To send value on " + elementName + "  which is located at " + locator);
		}

		return element;
	}

	/**
	 * Get the value of <code>WebElement</code> for the requested selector type
	 * locator
	 *
	 * @param locator
	 *            Element located value
	 * @param elementName
	 *            Name of element by which get the text
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 * @return Returns the String containing the present text
	 */
	protected String getText(By locator, String elementName, long waitTimeInSecs) {
		String element = null;
		try {
			isElementVisible(locator, elementName, waitTimeInSecs);
			element = driver.findElement(locator).getText();
			// log.info("PASS: " + elementName + " text is " + element);
		} catch (Exception e) {
//			log.error("FAIL: To find the element text" + elementName + "  which is located at " + locator);
		}
		return element;
	}

	/**
	 * Get the value of <code>WebElement</code> by JavaScript for the requested
	 * selector type locator
	 *
	 * @param locator
	 *            Element located value
	 * @param elementName
	 *            Name of element by which get the text
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 * @return Returns the String containing the present text
	 */
	protected String getTextUsingJavaScript(By locator, String elementName, long waitTimeInSecs) {
		String element = null;
		try {
			isElementVisible(locator, elementName, waitTimeInSecs);
			WebElement text = driver.findElement(locator);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			element = (String) jsExecutor.executeScript("return arguments[0].value", text);
//			log.info("PASS: " + elementName + " text is " + element);
		} catch (Exception e) {
//			log.error("FAIL: To find the element text" + elementName + "  which is located at " + locator);
		}
		return element;
	}

	/**
	 * Returns currently the size of the visible WebElements in the dropdown
	 *
	 * @param locator
	 *            Element located value
	 * @param elementName
	 *            Name of element
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 * @return Returns an List of found <code>WebElement</code>
	 */
	/*protected List<WebElement> getVisibleElementLists(By locator, String elementName, long waitTimeInSecs) {
		int visibleElementsCount = 0;
		int elementsCount = 0;
		List<WebElement> visibleElementsList = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTimeInSecs);
			visibleElementsList = wait
					.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)));
			// visibleElementsList = driver.findElements(locator);
			visibleElementsCount = visibleElementsList.size();
//			log.info("Found visibility for list of elements " + elementName + " with size = " + visibleElementsCount);
		} catch (Exception e) {
			try {
				// if all elements are not visible, then get the list of all Elements by Locator
				log.info("All elements not visible for " + elementName + " , getting only visible element list...");
				// List<WebElement> lstElements = driver.findElements(locator);
				List<WebElement> lstElements = driver.findElements(locator);
				if (lstElements != null && lstElements.size() > 0) {
					visibleElementsList = new ArrayList<WebElement>();
					elementsCount = lstElements.size();
					for (int i = 0; i <= elementsCount; i++) {
						WebElement element = lstElements.get(i);
						if (element.isDisplayed()) {
							visibleElementsList.add(element);
							visibleElementsCount++;
						}
					}
				} else {
					log.info(elementName + " web element list is found with size = " + elementsCount
							+ " , Visible elements count =" + visibleElementsCount);
				}
			} catch (Exception e1) {
				String errorMessage = elementName + " web element list is found with size = " + elementsCount
						+ " , Visible elements count =" + visibleElementsCount;
				log.error(errorMessage);
			}
		}
		return visibleElementsList;
	}*/

	/**
	 * Turning on implicit wait
	 *
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 */
	protected void turnONImplicitWaits(long waitTimeInSecs) {
//		log.info(" Turning On Implicit Wait  ");
		driver.manage().timeouts().implicitlyWait(waitTimeInSecs, TimeUnit.SECONDS);
	}

	/**
	 * Returns currently the value of Attribute present in requested locator
	 *
	 * @param locator
	 *            Element located value
	 * @param attribute
	 *            Name of attribute for which get the value
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 * @return Returns String value present in the attribute otherwise null
	 */
	protected String getElementValue(By locator, String attribute, String elementName, long waitTimeInSecs) {
		WebElement element = null;
		String value = null;
		try {
			isElementVisible(locator, elementName, waitTimeInSecs);
			element = driver.findElement(locator);
			value = element.getAttribute(attribute);
			// log.info("PASS: " + elementName + " " + attribute + " = " + value);
		} catch (Exception e) {
//			log.error("FAIL: to check the element value");
		}
		return value;
	}

	/**
	 * Used to Scroll the page at the bottom by using JavaScript
	 */
	protected void scrollDownToBottomOfPage() {
		try {
//			log.info("Scrolling down to the bottom of the page");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		} catch (Exception e) {
//			log.error("FAIL to Scroll down");
		}
	}

	/**
	 * Used to get the title of the page
	 * 
	 * @param pageName
	 *            Name of the page for which getting the title
	 * @return Returns the String containing the title of the page
	 */
	protected String getPageTitle(String pageName) {
		String pageTitle = null;
		try {
			pageTitle = driver.getTitle();
//			log.info("Title of the " + pageName + " is = " + pageTitle);
		} catch (Exception e) {
//			log.error("FAIL: to get the " + pageName + " page title");
		}
		return pageTitle;
	}

	/**
	 * Used to get the title of the page
	 * 
	 */
	protected String getPageTitle() {
		return driver.getTitle();
	}

	/**
	 * Used to move the focus to the given index
	 * 
	 * @param index
	 *            Integer value to be given
	 */
	protected void switchToDifferentTab(int index) {
		try {
			ArrayList<String> anotherTabs = new ArrayList<String>(driver.getWindowHandles());
//			log.info("switching to another tab");
			driver.switchTo().window(anotherTabs.get(index));
		} catch (Exception e) {
//			log.error("error while switching the tab");
		}
	}

	/**
	 * Used to get all the open tabs in the browser
	 * 
	 */
	protected int getTotalTabs() {
		int totalTabs = 0;
		try {
			ArrayList<String> anotherTabs = new ArrayList<String>(driver.getWindowHandles());
			totalTabs = anotherTabs.size();
		} catch (Exception e) {
//			log.error("error while getting the total number of tabs");
		}
		return totalTabs;
	}

	/**
	 * load properties file in System environment
	 * 
	 * @param fileName
	 */
//	public void loadPropertiyFile(String fileName) {
//		String configFile;
//
//		if (fileName.equalsIgnoreCase("config"))
//			configFile = "./src/main/resources/" + fileName + ".properties";
//		else
//			configFile = "./src/test/resources/" + fileName + ".properties";
//
//		prop = new Properties(System.getProperties());
//
//		try {
//			prop.load(new FileReader(configFile));
//		} catch (IOException e) {
//			log.error("exception occured during load prop file " + e);
//		}
//		System.setProperties(prop);
//		log.info("prop file loaded successfully");
//	}

	/**
	 * Used to provide one time wait in seconds
	 * 
	 * @param seconds
	 * @param delayReason
	 */
	public void addDelay(long seconds, String delayReason) {
		try {
//			log.info("Sleeping for " + seconds + " seconds to " + delayReason);
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
//			log.error("Error occured while Sleeping for " + seconds + " seconds.");
		}
	}

	/**
	 * Check is element is enabled
	 *
	 * @param locator
	 *            Element located value
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 * @return Returns <code>true</code> if locator is enabled, otherwise returns
	 *         <code>false</code>
	 */
	protected boolean isEnabled(By locator, String elementName, long waitTimeInSecs) {
		boolean flag;
		try {
			isElementVisible(locator, elementName, waitTimeInSecs);
			driver.findElement(locator).isEnabled();
			flag = true;
//			log.info(elementName + " button is enabled");
		} catch (Exception e) {
//			log.error("Fail to check isEnabled : " + locator);
			flag = false;
		}
		return flag;
	}

	/**
	 * Check is element is disabled
	 *
	 * @param locator
	 *            Element located value
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 * @return Returns <code>false</code> if locator is disabled, otherwise returns
	 *         <code>true</code>
	 */
	protected boolean isDisabled(By locator, String elementName, long waitTimeInSecs) {
		WebElement element = null;
		boolean isDisabled;
		try {
			isElementVisible(locator, elementName, waitTimeInSecs);
			element = driver.findElement(locator);
			String id = element.getAttribute("disabled");
			isDisabled = id.contains("disabled");
			if (isDisabled = true) {
//				log.info(elementName + " button is disabled");
			} else {
//				log.info(elementName + " button is enabled");
				isDisabled = false;
			}
		} catch (Exception e) {
//			log.info("Fail to check isEnabled : " + locator);
			isDisabled = false;
		}
		return isDisabled;
	}

	/**
	 * Selects the element by mouse hover
	 *
	 * @param locator
	 *            Element located value
	 * @param elementName
	 *            Name of element where to move the mouse
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 * @return Returns the <code>WebElement</code>
	 */
	/*protected WebElement mouseHover(By locator, String elementName, long waitTimeInSecs) {
		WebElement element = null;
		try {
			isElementVisible(locator, elementName, waitTimeInSecs);
			element = driver.findElement(locator);
			Actions builder = new Actions(driver);
			builder.moveToElement(element).build().perform();
			log.info("PASS: Element drag to " + elementName);
		} catch (Exception e) {
			log.error("FAIL: Element to drag to " + elementName);
		}
		return element;
	}*/

	/**
	 * Returns the current text in the drop down selected by the web element.
	 *
	 * @param locator
	 *            selector of the drop down
	 * @param elementName
	 *            Name of the drop down
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 * @return Returns a String of the text that displays in the drop down or null
	 *         if element could not be found
	 */
	/*protected String getSelectedValueFromDropDown(By locator, String elementName, long waitTimeInSecs) {
		String selectedAddress = null;
		WebElement element, address;
		try {
			isElementVisible(locator, elementName, waitTimeInSecs);
			element = driver.findElement(locator);
			Select select = new Select(element);
			address = select.getFirstSelectedOption();
			selectedAddress = address.getText();
		} catch (Exception e) {
			log.error("FAIL: to get the selected address from " + locator);
		}

		return selectedAddress;
	}*/

	/**
	 * Finds a drop down by its text and sets it to the given selection.
	 *
	 * @param locator
	 *            selector of the drop down
	 * @param text
	 *            Value to selected in the drop down
	 * @param elementName
	 *            Name of the drop down
	 * @param waitTimeInSecs
	 *            Time to wait for the located element
	 * @return Returns the <code>WebElement</code> 
	 */
	protected WebElement selectDropDownValuesByText(By locator, String text, String elementName, long waitTimeInSecs) {
		WebElement element = null;
		try {
			isElementVisible(locator, elementName, waitTimeInSecs);
			element = driver.findElement(locator);
			Select select = new Select(element);
			select.selectByVisibleText(text);
//			log.info("PASS: successfully selected " + text + " from " + elementName + " dropdown");
		} catch (Exception e) {
//			log.error("FAIL: to find the " + elementName + " which is located at " + locator);
		}

		return element;
	}
	
	
	protected WebElement selectDropDownByValue(By locator, String text, String elementName, long waitTimeInSecs) {
		WebElement element = null;
		try {
			isElementVisible(locator, elementName, waitTimeInSecs);
			element = driver.findElement(locator);
			Select select = new Select(element);
			select.selectByValue(text);
//			log.info("PASS: successfully selected " + text + " from " + elementName + " dropdown");
		} catch (Exception e) {
//			log.error("FAIL: to find the " + elementName + " which is located at " + locator);
		}

		return element;
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				// Create refernce of TakesScreenshot
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				DateFormat df = new SimpleDateFormat("yyyyMMddhhmm");
				FileHandler.copy(source,
						new File("./Screenshots/" + result.getName() + df.format(new Date()) + ".png"));
//				log.info("Screenshot taken");
			} catch (Exception e) {
//				log.error("Exception while taking screenshot " + e.getMessage());
			}
		}
	}

	public void takeScreenshot() {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			DateFormat df = new SimpleDateFormat("yyyyMMddhhmm");
			FileHandler.copy(source, new File("./Screenshots/" + df.format(new Date()) + ".png"));
//			log.info("Screenshot taken");
		} catch (Exception e) {

		}
	}

	/**
	 * To shut down the browser
	 */
	@AfterClass
	public void shutDown() {
//		log.info("Exit from the WebDriver Browser");
		if (driver != null) {
			driver.quit();
		}
	}

}
