package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import com.generic.selector.CLPSelectors;
import com.generic.selector.HomePageSelectors;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

/**
 * The Class HomePage.
 */
public class CLP extends SelTestCase {

	public static boolean validateMobileIpadCLP() throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean isValid = true;
			HomePage.openNavigationMenu();
			String pageUrl = SelectorUtil.getCurrentPageUrl();// to validate iPad
			List<WebElement> menueItems = new ArrayList<WebElement>();
			menueItems = menueWithoutWhatsNew();
			WebElement randomMenuElement = SelectorUtil.getRandomWebElement(menueItems);
			SelectorUtil.clickOnWebElement(randomMenuElement);
			// Navigate to leafMenuItems items.
			List<WebElement> leafMenuItems = new ArrayList<WebElement>();
			leafMenuItems = SelectorUtil.getAllElements(HomePageSelectors.leafMenuItems.get());

			WebElement viewAllElement = leafMenuItems.get(0);// get first index "view all"

			boolean validateViewAllElement = true;
			if (isMobile()) {
				validateViewAllElement = SelectorUtil.isValidClickableItem(viewAllElement);
			} else {// Check if the current page URL different than the previous page URL for iPad ;
					// items didn't contains Href attribute
				SelectorUtil.clickOnWebElement(viewAllElement);
				String currentPageUrl = SelectorUtil.getCurrentPageUrl();
				if (pageUrl.equalsIgnoreCase(currentPageUrl)) {
					validateViewAllElement = false;
				}
			}
			if (validateViewAllElement) {
				isValid = validatePLP();
			}
			getCurrentFunctionName(false);

			return isValid;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static boolean validateDesktopCLP() throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean isValid = true;
			List<WebElement> menutems = new ArrayList<WebElement>();
			menutems = menueWithoutWhatsNew();
			WebElement menuElement = SelectorUtil.getRandomWebElement(menutems);
			boolean isValidClickableElement = SelectorUtil.isValidClickableItem(menuElement);
			if (isValidClickableElement) {
				if (validatePLP()) {
					isValid = true;
				} else {
					isValid = false;
				}
			} else {
				isValid = false;
			}
			getCurrentFunctionName(false);
			return isValid;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static boolean validatePLP() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<WebElement> items = new ArrayList<WebElement>();
			items = SelectorUtil.getAllElements(CLPSelectors.CLPItems.get());
			WebElement PLPElement = SelectorUtil.getRandomWebElement(items);
			// Get the current page URL.
			boolean isValidClickableElement = SelectorUtil.isValidClickableItem(PLPElement);
			getCurrentFunctionName(false);
			return isValidClickableElement;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static List<WebElement> menueWithoutWhatsNew() throws Exception {
		try {
			List<WebElement> items = new ArrayList<WebElement>();
			items = SelectorUtil.getAllElements(HomePageSelectors.menuItems.get());
			items.remove(0);// remove what's New item : First item
			items.remove(items.size() - 1);
			return items;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
}
