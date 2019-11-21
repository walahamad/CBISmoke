package com.generic.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.generic.selector.HomePageSelectors;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * The Class HomePage.
 */
public class HomePage extends SelTestCase {
	public static String userEmail = getCONFIG().getProperty("userEmail");
	public static String userPassword = getCONFIG().getProperty("userPassword");

	public static boolean validateLogodisplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed; 
		List<String> subStrArr = new ArrayList<String>();
		logs.debug("Validate if logo exist");
		subStrArr.add(HomePageSelectors.logo.get());
		isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		getCurrentFunctionName(false);		
		return isDisplayed;
	}

	public static void NavigateAwayFromHomePage() throws Exception {
		getCurrentFunctionName(true);
		//TODO: we need to make this dynamic  
		getDriver().get(getURL() + "/ProductSearch2?searchTerm=test");
		getCurrentFunctionName(false);
	}

	public static void  clickOnLogo() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		logs.debug("Clicking on Site logo");
		subStrArr.add(HomePageSelectors.logo.get());
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
		getCurrentFunctionName(false);
	}

	public static boolean validateHomePageLink() throws Exception {
		getCurrentFunctionName(true);
		String CurrentURL = getDriver().getCurrentUrl().replace("www.", "");
		logs.debug("Current URL is: " + CurrentURL);
		logs.debug("Current URL should match: " + getURL().replace("www.", "") +"/" );
		boolean results  =CurrentURL.equals(getURL().replace("www.", "")  +"/");
		getCurrentFunctionName(false);
		return results;
	}

	/**
	* Log the number of items at the menu first level.
	*
	* @param menuFirstLevelElements.
	* @throws Exception
	*/
	public static void logNumberOfFirstLevelMenuItems(List<WebElement> menuFirstLevelElements) throws Exception {
		getCurrentFunctionName(true);

		logs.debug("Validate number of menu items in first level.");

		// Display the number of items in the menu first level.
		logs.debug("Number of menu items: " + menuFirstLevelElements.size());

		getCurrentFunctionName(false);
	}

	/**
	* Validate if there's a menu and it contains an item/s.
	*
	* @param menuFirstLevelElements.
	* @return boolean
	* @throws Exception
	*/
	public static boolean menuValidation(List<WebElement> menuFirstLevelElements) throws Exception {
		getCurrentFunctionName(true);

		// Get the menu items list.
		logs.debug("Validate if menu displayed.");

		// Check if there's an item in the menu by check the number of items at first level.
		if (menuFirstLevelElements.size() == 0)
			return false ;
		getCurrentFunctionName(false);

		return true;
	}

	/**
	* Validate the sub menu and the items in first level navigation and the target page.
	*
	* @param menuFirstLevelElements.
	* @return boolean
	* @throws Exception
	*/
	public static boolean validateSubMenuLists(List<WebElement> menuFirstLevelElements) throws Exception {
		getCurrentFunctionName(true);

		logs.debug("Validate menu sub menus list." + menuFirstLevelElements);
		int menuItemIndex;
		boolean validateSubMenuNavigation = true;
		for (menuItemIndex=0; menuItemIndex < menuFirstLevelElements.size(); menuItemIndex++) {
			// The elements should be selected at each iteration because the page will navigate and lose the reference to the elements dom.
			List<WebElement> elements = getFirstLevelMenuItems();
			WebElement element = elements.get(menuItemIndex);

			// Save the text and href for the selected menu item.
			String href = element.getAttribute("href");
			boolean currentPageMatchNavigated = true;

			// Navigate to an item in the menu.
			((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()", element);

			// Get the current page URL.
			String currentPageUrl = SelectorUtil.getCurrentPageUrl();
			logs.debug("Current page path: " + currentPageUrl);

			// Check if the current page title is the same as selected navigation title.
			if (!href.equalsIgnoreCase(currentPageUrl)) {
				currentPageMatchNavigated = false;
				validateSubMenuNavigation = false;
			}
			sassert().assertTrue(currentPageMatchNavigated, "Menu validation items navigation has some problems");
		}
		getCurrentFunctionName(false);
		return validateSubMenuNavigation;
	}

	/**
	* Get the elements list by pass the selector as a string.
	*
	* @param selector.
	* @return List<WebElement>
	* @throws Exception
	*/
	public static List<WebElement> getElementsList(String selector) throws Exception {
		getCurrentFunctionName(true);

		List<WebElement> menuFirstLevelElements = new ArrayList<WebElement>();
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(selector);
		valuesArr.add("");
		// Get the list web elements.
		menuFirstLevelElements = SelectorUtil.getAllElements(subStrArr);
		getCurrentFunctionName(false);

		return menuFirstLevelElements;
	}

	/**
	* Get the list of first level elements in the menu.
	*
	* @return List<WebElement>
	* @throws Exception
	*/
	public static List<WebElement> getFirstLevelMenuItems() throws Exception {
		getCurrentFunctionName(true);

		logs.debug("Get the menu items first level.");
		List<WebElement> menuFirstLevelElements = new ArrayList<WebElement>();
		// Get the menu items list.
		menuFirstLevelElements = getElementsList(HomePageSelectors.menuItems.get());
		getCurrentFunctionName(false);

		return menuFirstLevelElements;
	}

	/**
	* Open the navigation menu.
	*
	* @throws Exception
	*/
	public static void openNavigationMenu() throws Exception {
		getCurrentFunctionName(true);

		logs.debug("Open navigation menu");
		// Click on navigation menu icon.
		// Navigate to an item in the menu.
		WebElement menuIcon = getElementsList(HomePageSelectors.navigationIcon.get()).get(0);
		((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()", menuIcon);
		getCurrentFunctionName(false);
	}

	/**
	* Validate the navigation first and second level with navigation menu at mobile PWA.
	*
	* @return boolean
	* @throws Exception
	*/
	public static boolean validateModalMenuSecondLevel() throws Exception {
		getCurrentFunctionName(true);

		logs.debug("Validate second level menu list.");
		int menuItemIndex;
		boolean validateSubMenuNavigation = true;

		// Open the menu modal.
		HomePage.openNavigationMenu();

		// Get the first menu modal list.
		List<WebElement> menuFirstLevelElements = getFirstLevelMenuItems();

		String pwaLinkClassNameIdentifier = "pw-link";

		for (menuItemIndex=0; menuItemIndex < menuFirstLevelElements.size(); menuItemIndex++) {
			boolean currentPageMatchNavigated = true;

			// The elements should be selected at each iteration because the page will navigate and lose the reference to the elements dom.
			List<WebElement> elements = getFirstLevelMenuItems();
			WebElement element = elements.get(menuItemIndex);

			// Save the parent text.
			String selectedText = element.getAttribute("innerText").trim();

			// Navigate to second level in the menu.
			((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()", element);

			// Get the sub menu header text.
			WebElement selectedMenuHeader = getElementsList(HomePageSelectors.selectedMenuHeader.get()).get(0);
			String selectedMenuHeaderText = selectedMenuHeader.getAttribute("innerText").trim();

			// Get the current page URL.
			String pageUrl = SelectorUtil.getCurrentPageUrl();

			// Check if the sub menu header title is the same of the selected item text.
			if (!selectedMenuHeaderText.equals(selectedText)) {
				currentPageMatchNavigated = false;
				validateSubMenuNavigation = false;
			} else {
				// Select the list of leaf items in the menu.
				List<WebElement> leafMenuItems = getElementsList(HomePageSelectors.leafMenuItems.get());

				// Select a random item from the leaf items list.
				Random rand = new Random();
				int randomIndex = rand.nextInt(leafMenuItems.size());
				WebElement randomElement = leafMenuItems.get(randomIndex);

				logs.debug("Random selected item from: " + randomIndex);

				// Get the current page URL.
				String href = randomElement.getAttribute("href");
				String elementClassName = randomElement.getAttribute("class");

				// Navigate to the selected random page.
				((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()", randomElement);

				Thread.sleep(1000);
				String currentPageUrl = SelectorUtil.getCurrentPageUrl();
				// Get the current page URL.
				logs.debug("Navigated random page path: " + currentPageUrl + "    " + href);

				if (elementClassName.contains(pwaLinkClassNameIdentifier)) {
					// Check if the current page URL different than the previous page URL for PWA mobile.
					if (!href.equalsIgnoreCase(currentPageUrl)) {
						currentPageMatchNavigated = false;
						validateSubMenuNavigation = false;
					}
				} else if (pageUrl.equalsIgnoreCase(currentPageUrl)) {
					// Check if the current page URL different than the previous page URL for tablet.
					currentPageMatchNavigated = false;
					validateSubMenuNavigation = false;
				}

				// Open the menu modal.
				HomePage.openNavigationMenu();
			}
			sassert().assertTrue(currentPageMatchNavigated, "Menu validation items navigation has some problems");
		}
		getCurrentFunctionName(false);
		return validateSubMenuNavigation;
	}

	/**
	* Validate the sign form in desktop.
	*
	* @return boolean
	* @throws Exception
	*/
	public static boolean validateSignInForm(WebElement signInLink, boolean isPWAMobile) throws Exception {

		getCurrentFunctionName(true);
		logs.debug("Sign in validateion form");

		boolean isUserLogedIn = false;
		// Navigate to the Sign in/Create account page.
		((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()", signInLink);

		// Select the email input.
		WebElement emailWebElement = getElementsList(HomePageSelectors.signInEmailInput.get()).get(0);

		// Enter the email each character separately to avoid the filling issue at PWA mobile because of gwt form.
		SelectorUtil.sendKeysByCharacters(emailWebElement, userEmail);

		// Select the password input.
		WebElement passwordWebElement = getElementsList(HomePageSelectors.signInEmailPasswordInput.get()).get(0);

		// Enter the password each character separately to avoid the filling issue at PWA mobile because of gwt form.
		SelectorUtil.sendKeysByCharacters(passwordWebElement, userPassword);

		// Select the sign in button.
		WebElement signInButton = getElementsList(HomePageSelectors.signInButton.get()).get(0);

		// Navigate to the Sign in/Create account page.
		((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()", signInButton);

		List <WebElement> welcomeMessage = new ArrayList<WebElement>();

		// Get the welcome message.
		if (isPWAMobile) {
			welcomeMessage.add(HomePage.getSignInLinkMobilePWA());
		} else {
			welcomeMessage = HomePage.getElementsList(HomePageSelectors.welcomeMessage.get());
		}

		// Validate the welcome message if it is exist.
		if (!(welcomeMessage.size() == 0)) {
			isUserLogedIn = true;
		}

		sassert().assertTrue(isUserLogedIn, "Login validation has some problems");
		getCurrentFunctionName(false);
		return isUserLogedIn;
	}

	/**
	* Get the account item (Sign in/create account page or welcome message).
	*
	* @param WebElement
	* @throws Exception
	*/
	public static WebElement getSignInLinkMobilePWA() throws Exception {
		getCurrentFunctionName(true);

		logs.debug("Open account menu for PWA mobile");

		// Open the account menu.
		WebElement menuIcon = HomePage.getElementsList(HomePageSelectors.accountMenuIcon.get()).get(0);
		((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()", menuIcon);

		// Get an account items list.
		List <WebElement> menuItems = HomePage.getElementsList(HomePageSelectors.accountMenuList);
		WebElement signInLink = menuItems.get(0);
		int index = 0;
		// Get the Sign in/create account page or welcome message item.
		for (index=0; index < menuItems.size(); index++) {
			WebElement item = menuItems.get(index);
			String itemHref = item.getAttribute("href");
			String itemText = item.getAttribute("innerText").trim().toLowerCase();

			// Check if the item is sign in/create account (By check create account page link) or welcome message.
			if (itemHref.contains("UserLogonView") || itemText.contains("welcome")) {
				signInLink = item;
				break;
			}
		}
		logs.debug("The account item (Sign in/create account page or welcome message): " + signInLink);
		getCurrentFunctionName(false);
		return signInLink;
	}

}
