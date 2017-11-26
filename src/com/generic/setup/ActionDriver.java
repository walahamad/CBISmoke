package com.generic.setup;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class is responsible for GUI interaction. It contains functions like
 * type, click , select etc
 *
 */
public class ActionDriver extends SelTestCase {

	/**
	 * This function will fetch the element from the application.
	 *
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public static WebElement getElement(By locator) throws Exception {
		try {
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, locator.toString()));
			// explicit wait
			return waitForElementPresent(locator);
		} catch (Throwable t) {
			throw new Exception(t);
		}
	}

	/**
	 * It waits for element to load
	 *
	 * @param locator
	 * @return
	 * @throws Exception
	 * @throws TimeoutException
	 */
	public static WebElement waitForElementPresent(final By locator) throws Exception, TimeoutException {
		WebDriverWait wait = new WebDriverWait(SelTestCase.getDriver(), SelTestCase.getWaitTime());
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public static boolean waitForElementNotPresent(final By locator, int waitTime) throws Exception, TimeoutException {
		try {
			WebDriverWait wait = new WebDriverWait(SelTestCase.getDriver(), waitTime);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * It select the option from a drop down by using text
	 *
	 * @param locator
	 * @param testData
	 * @throws Exception
	 */
	public static void selectByText(By locator, String testData) throws Exception {
		WebElement element;
		try {
			element = getElement(locator);
			Select s2 = new Select(element);
			s2.selectByVisibleText(testData);

		} catch (Throwable t) {
			throw new Exception(t);
		}
	}

	/**
	 * It select the option from a drop down by value
	 *
	 * @param locator
	 * @param testData
	 * @throws Exception
	 */
	public static void selectByValue(By locator, String testData) throws Exception {
		WebElement element;
		try {
			element = getElement(locator);
			Select s2 = new Select(element);
			s2.selectByValue(testData);

		} catch (Throwable t) {
			throw new Exception(t);
		}
	}

	/**
	 * Returns true if element is present else false
	 *
	 */
	public static boolean isElementPresent(By locator) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(SelTestCase.getDriver(), SelTestCase.getWaitTime());
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (Throwable t) {
			// t.printStackTrace();
			return false;
		}
	}

	/**
	 * It closes the browser
	 *
	 */
	public static void closeBrowser() {
		logs.debug(LoggingMsg.TERMINATING_SESSION);

		if (getDriver() != null) {
			try {
				SelTestCase.getDriver().quit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			SelTestCase.setDriver(null);
		}
	}

	/**
	 * It will type the test data into an input box
	 *
	 * @param locator
	 * @param testData
	 * @throws Exception
	 */
	public static void type(By locator, String testData) throws Exception {
		logs.debug(MessageFormat.format(LoggingMsg.WRITING_TO_SEL, "", testData, locator.toString()));
		WebDriverWait wait = new WebDriverWait(SelTestCase.getDriver(), SelTestCase.getWaitTime());
		WebElement element = null;
		element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		element.clear();
		element.sendKeys(testData);
	}

	public static void forceType(By locator, String testData) throws Exception {
		logs.debug(MessageFormat.format(LoggingMsg.WRITING_TO_SEL, "Force ", testData, locator.toString()));
		WebDriverWait wait = new WebDriverWait(SelTestCase.getDriver(), SelTestCase.getWaitTime());
		WebElement element = null;
		element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		ActionDriver.click(locator);
		element.clear();
		element.sendKeys(Keys.chord(Keys.CONTROL, "a") + testData);
	}

	/**
	 * It clicks on web element
	 *
	 * @param locator
	 * @throws Exception
	 */
	public static void click(By locator) throws Exception {
		try {
			logs.debug(MessageFormat.format(LoggingMsg.CLICK_ELEMENT_SEL, locator.toString()));
			getElement(locator).click();
		} catch (Throwable t) {
			// t.printStackTrace();
			throw new Exception(locator + " is missing " + t);
		}
	}

	/**
	 * It clicks on web element
	 *
	 * @param locator
	 * @throws Exception
	 */
	public static void submitForm(By locator) throws Exception {

		try {
			getElement(locator).submit();
		} catch (Throwable t) {
			throw new Exception(locator + " is missing " + t);
		}
	}

	public static void mouseHover(By locator) throws Exception {
		Actions action = new Actions(getDriver());
		action.moveToElement(ActionDriver.getElement(locator)).build().perform();
	}

	public static void doubleClick(By locator) throws Exception {
		Actions action = new Actions(getDriver());
		action.doubleClick(ActionDriver.getElement(locator));
		action.perform();
	}

	public static void setImplicitWaitOnDriver(int maxWaitTime) {
		SelTestCase.getDriver().manage().timeouts().implicitlyWait(maxWaitTime, TimeUnit.SECONDS);
	}
}
