package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.util.NoSuchElementException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.generic.selector.HomePageSelectors;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.LoggingMsg;
import com.generic.util.SelectorUtil.commands.actions;

/**
 * The Class HomePage.
 */
public class HomePage extends SelTestCase {
	public static int homePageMenuLevelTestItems = Integer
			.parseInt(getCONFIG().getProperty("homePageMenuLevelTestItems"));

	public final static String searchPlacholder = "Search - Keyword or Item #";
	public final static String pwaLinkClassNameIdentifier = "pw-link";

	public static boolean validateLogodisplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed;
		logs.debug("Validate if logo exist");
		isDisplayed = SelectorUtil.isDisplayed(HomePageSelectors.logo.get());
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static void NavigateAwayFromHomePage() throws Exception {
		getCurrentFunctionName(true);
		getDriver().get(getURL() + getCONFIG().getProperty("searchURL"));
		getCurrentFunctionName(false);
	}

	// This is to disable Monetate if needed.
	public static void updateMmonetate() throws Exception {
		getCurrentFunctionName(true);
		getDriver().get(getURL() + "/?monetate=" + getCONFIG().getProperty("monetateStatus"));
		getCurrentFunctionName(false);
	}

	public static void clickOnLogo() throws Exception {
		getCurrentFunctionName(true);
		logs.debug("Clicking on Site logo");
		SelectorUtil.initializeSelectorsAndDoActions(HomePageSelectors.logo.get());
		getCurrentFunctionName(false);
	}

	public static boolean validateHomePageLink() throws Exception {
		getCurrentFunctionName(true);
		String CurrentURL = getDriver().getCurrentUrl().replace("www.", "");
		logs.debug("Current URL is: " + CurrentURL);
		logs.debug("Current URL should match: " + getURL().replace("www.", "") + "/");
		boolean results = CurrentURL.equals(getURL().replace("www.", "") + "/");
		getCurrentFunctionName(false);
		return results;
	}

	public static boolean validateYMALCarouselsDisplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed;
		logs.debug("Validate if YMAL carousels exist");
		isDisplayed = SelectorUtil.isDisplayed(HomePageSelectors.YMALCarousels.get());
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static boolean validateMainHomeCarouselsDisplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed;
		logs.debug("Validate if main home carousels exist");
		isDisplayed = SelectorUtil.isDisplayed(HomePageSelectors.mainHomeCarousels.get());
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static boolean validateAccountMenuDisplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed;
		logs.debug("Validate if Account menu exist");
		isDisplayed = SelectorUtil.isDisplayed(HomePageSelectors.accountMenu.get());
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static void clickOnAccountMenu(Boolean withHover) throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug("Clicking on Account menu");
			if (withHover) {
				SelectorUtil.initializeSelectorsAndDoActions(HomePageSelectors.accountMenu.get(), actions.hover);
			} else {
				SelectorUtil.initializeSelectorsAndDoActions(HomePageSelectors.accountMenu.get());
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static void clickOnCloseButton() throws Exception {

		getCurrentFunctionName(true);
		logs.debug("Clicking on Close Button");
		SelectorUtil.initializeSelectorsAndDoActions(HomePageSelectors.navIcon.get());
		getCurrentFunctionName(false);

	}

	public static void clickOnRandomAccountMenuItem() throws Exception {
		getCurrentFunctionName(true);
		List<WebElement> accountMenuElements = getAccountMenuItems();
		Random random = new Random();
		int randomIndex = random.nextInt(accountMenuElements.size());
		WebElement element = accountMenuElements.get(randomIndex);
		SelectorUtil.clickOnWebElement(element);
		getCurrentFunctionName(false);
	}

	public static List<WebElement> getAccountMenuItems() throws Exception {
		getCurrentFunctionName(true);
		logs.debug("Get the account menu items.");
		List<WebElement> menuItems = new ArrayList<WebElement>();
		menuItems = SelectorUtil.getAllElements(HomePageSelectors.accountMenuItems.get());
		getCurrentFunctionName(false);
		return menuItems;
	}

	public static boolean validateAccountMenuItemsDisplayed() throws Exception {
		getCurrentFunctionName(true);
		List<WebElement> accountMenuElements = getAccountMenuItems();
		logs.debug("Validate account menu links." + accountMenuElements);
		boolean isDisplayed = true;
		for (WebElement element : accountMenuElements) {
			isDisplayed = element.isDisplayed();
		}
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static boolean validateCountrySelectorDisplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed;
		logs.debug("Validate if country selector  exist");
		isDisplayed = SelectorUtil.isDisplayed(HomePageSelectors.countrySelector.get());
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static boolean validateGlobalFooterItemsDisplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed = true;
		logs.debug("Validate if global footer  exist");
		isDisplayed = SelectorUtil.isDisplayed(HomePageSelectors.globalFooter.get());
		List<WebElement> footerItems = new ArrayList<WebElement>();

		footerItems = SelectorUtil.getAllElements(HomePageSelectors.accordionHeader.get());
		for (WebElement element : footerItems) {
			isDisplayed = element.isDisplayed();
		}
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static void clickOnMiniCart() throws Exception {
		getCurrentFunctionName(true);
		String subStrArr = HomePageSelectors.miniCartBtn.get();
		if (!isDesktop()) {
			logs.debug("Clicking on Mini Cart");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
		} else {
			logs.debug("Hovering on Mini Cart");
			PDP.hoverMiniCart();
		}

		getCurrentFunctionName(false);
	}

	public static String getMiniCartText() throws Exception {
		getCurrentFunctionName(true);
		String subStrArr = HomePageSelectors.miniCartText.get();
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
		String cartText = SelectorUtil.textValue.get();
		logs.debug("The cart text is:" + cartText);
		getCurrentFunctionName(false);
		return cartText;
	}

	public static void clickOnMiniCartCloseBtn() throws Exception {
		getCurrentFunctionName(true);
		String subStrArr = HomePageSelectors.miniCartClose.get();
		logs.debug("Clicking on Mini Cart clsoe icon");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
		getCurrentFunctionName(false);
	}

	public static boolean validateMiniCartIsClosed() throws Exception {
		getCurrentFunctionName(true);
		boolean isNotDisplayed;
		String subStrArr = HomePageSelectors.miniCartText.get();
		logs.debug("Validate if mini cart is closed");
		isNotDisplayed = SelectorUtil.isNotDisplayed(subStrArr);
		getCurrentFunctionName(false);
		return isNotDisplayed;
	}

	public static boolean validateMiniCartProductIsDsiplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed;
		String subStrArr = HomePageSelectors.miniCartProductContainer.get();
		logs.debug("Validate if Mini cart products are displayed");
		isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static boolean validateMiniCartCheckoutBtnIsDisplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed;
		String subStrArr = HomePageSelectors.miniCartCheckoutBtn.get();
		logs.debug("Validate if Mini Cart Checkout Btn Is Displayed");
		isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static void searchIconClick() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, HomePageSelectors.searchIconOpen));
			subStrArr.add(HomePageSelectors.searchIconOpen.get());
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static void searchIconExitClick() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, HomePageSelectors.searchIconClose));
			subStrArr.add(HomePageSelectors.searchIconClose.get());
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static boolean validateSearchIconFieldOpend() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed;
		List<String> subStrArr = new ArrayList<String>();
		logs.debug("Validate if logo exist");
		subStrArr.add(HomePageSelectors.searchIconField.get());
		isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static boolean validateSearchIconFieldClosed() throws Exception {
		getCurrentFunctionName(true);
		boolean isNotDisplayed;
		List<String> subStrArr = new ArrayList<String>();
		logs.debug("Validate if logo exist");
		subStrArr.add(HomePageSelectors.searchIconField.get());
		isNotDisplayed = SelectorUtil.isNotDisplayed(subStrArr);
		if (isNotDisplayed == false)
			if (!SelectorUtil.isDisplayed(subStrArr))
				isNotDisplayed = true;
		getCurrentFunctionName(false);
		return isNotDisplayed;
	}

	public static String readSearchFieldPlaceHolderText() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		String valuesArr = "placeholder";
		subStrArr.add(HomePageSelectors.searchIconField.get());
		String placeHolderText = SelectorUtil.getAttr(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return placeHolderText;
	}

	public static boolean validateSearchFieldPlaceHolderText() throws Exception {
		if (searchPlacholder.equals(readSearchFieldPlaceHolderText()))
			return true;
		else
			return false;
	}

	public static boolean isDisplayedModuleHeroImg() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed;
		List<String> subStrArr = new ArrayList<String>();
		logs.debug("Validate if logo exist");
		subStrArr.add(HomePageSelectors.moduleHeroImg);
		isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static boolean isLoadedModuleHeroImg() throws Exception {
		getCurrentFunctionName(true);
		boolean loaded = SelectorUtil.isImgLoaded(HomePageSelectors.moduleHeroImg);
		getCurrentFunctionName(false);
		return loaded;
	}

	public static List<WebElement> getCarusals() throws Exception {
		getCurrentFunctionName(true);

		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(HomePageSelectors.carusals.get());
		List<WebElement> carusals = SelectorUtil.getAllElements(subStrArr);
		getCurrentFunctionName(false);
		return carusals;
	}

	public static boolean isListDisplayed(List<WebElement> elements) {
		boolean loaded = true;
		for (int i = 0; i < elements.size(); i++) {
			if (!elements.get(i).isDisplayed())
				loaded = false;
		}
		return loaded;
	}

	public static boolean isListLoaded(List<WebElement> elements) {
		boolean result = true;
		for (int i = 0; i < elements.size(); i++) {

			Object resultForOneCarusal = (Boolean) ((JavascriptExecutor) getDriver()).executeScript(
					"return arguments[0].complete && " + "typeof arguments[0].naturalWidth != \"undefined\" && "
							+ "arguments[0].naturalWidth > 0",
					elements.get(i));

			boolean loaded = false;
			if (resultForOneCarusal instanceof Boolean) {
				loaded = (Boolean) result;
				if (loaded == false)
					result = false;
			}
		}
		return result;
	}

	public static boolean isDisplayedAllCarouselContent() throws Exception {
		getCurrentFunctionName(true);
		List<WebElement> elements = getCarusals();
		boolean displayed = isListDisplayed(elements);
		getCurrentFunctionName(false);
		return displayed;
	}

	public static boolean isLoadedAllCarouselContent() throws Exception {
		getCurrentFunctionName(true);
		List<WebElement> elements = getCarusals();
		boolean result = isListLoaded(elements);
		getCurrentFunctionName(false);
		return result;
	}

	public static List<WebElement> getEspots() throws Exception {
		getCurrentFunctionName(true);

		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(HomePageSelectors.espots.get());
		List<WebElement> espots = SelectorUtil.getAllElements(subStrArr);
		getCurrentFunctionName(false);
		return espots;
	}

	public static boolean isDisplayAllSpots() throws Exception {
		getCurrentFunctionName(true);
		List<WebElement> elements = getEspots();
		boolean loaded = isListDisplayed(elements);
		getCurrentFunctionName(false);
		return loaded;
	}

	public static boolean isLoadedAllEspots() throws Exception {
		getCurrentFunctionName(true);
		List<WebElement> elements = getEspots();
		boolean result = isListLoaded(elements);
		getCurrentFunctionName(false);
		return result;
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

		// Check if there's an item in the menu by check the number of items at first
		// level.
		if (menuFirstLevelElements.size() == 0)
			return false;
		getCurrentFunctionName(false);

		return true;
	}

	/**
	 * Validate the sub menu and the items in first level navigation and the target
	 * page.
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

		int numberOfMenuItems = menuFirstLevelElements.size();
		if (homePageMenuLevelTestItems <= numberOfMenuItems) {
			numberOfMenuItems = homePageMenuLevelTestItems;
		}

		for (menuItemIndex = 0; menuItemIndex < numberOfMenuItems; menuItemIndex++) {
			// The elements should be selected at each iteration because the page will
			// navigate and lose the reference to the elements dom.
			List<WebElement> elements = getFirstLevelMenuItems();
			WebElement element = elements.get(menuItemIndex);

			// Save the text and href for the selected menu item.
			String href = element.getAttribute("href");

			// Navigate to an item in the menu.
			SelectorUtil.clickOnWebElement(element);

			// Get the current page URL.
			String currentPageUrl = SelectorUtil.getCurrentPageUrl();
			logs.debug("Current page path: " + currentPageUrl);

			// Check if the current page title is the same as selected navigation title.
			if (!href.equalsIgnoreCase(currentPageUrl)) {
				validateSubMenuNavigation = false;
			}
		}
		getCurrentFunctionName(false);
		return validateSubMenuNavigation;
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
		menuFirstLevelElements = SelectorUtil.getAllElements(HomePageSelectors.menuItems.get());
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
		// Click on navigation menu icon and Navigate to an item in the menu.
		SelectorUtil.initializeSelectorsAndDoActions(HomePageSelectors.navIcon.get());
		getCurrentFunctionName(false);
	}

	/**
	 * Validate the navigation first and second level with navigation menu at mobile
	 * PWA.
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

		int numberOfMenuItems = menuFirstLevelElements.size();
		if (homePageMenuLevelTestItems <= numberOfMenuItems) {
			numberOfMenuItems = homePageMenuLevelTestItems;
		}

		for (menuItemIndex = 0; menuItemIndex < numberOfMenuItems; menuItemIndex++) {
			boolean currentPageMatchNavigated = true;

			// The elements should be selected at each iteration because the page will
			// navigate and lose the reference to the elements dom.
			List<WebElement> elements = getFirstLevelMenuItems();
			WebElement element = elements.get(menuItemIndex);

			// Save the parent text.
			String selectedText = element.getText().toLowerCase();
			// Navigate to second level in the menu.
			SelectorUtil.clickOnWebElement(element);

			// Get the sub menu header text.
			WebElement selectedMenuHeader = SelectorUtil.getElement(HomePageSelectors.selectedMenuHeader.get());
			String selectedMenuHeaderText = selectedMenuHeader.getText().toLowerCase();

			// Get the current page URL.
			String pageUrl = SelectorUtil.getCurrentPageUrl();
			// Check if the sub menu header title is the same of the selected item text.
			if (!selectedMenuHeaderText.equals(selectedText)) {
				currentPageMatchNavigated = false;
				validateSubMenuNavigation = false;
			} else {
				SelectorUtil.waitGWTLoadedEventPWA();

				// Select the list of leaf items in the menu.
				List<WebElement> leafMenuItems = SelectorUtil.getAllElements(HomePageSelectors.leafMenuItems.get());

				// Select a random item from the leaf items list.
				Random rand = new Random();
				int randomIndex = rand.nextInt(leafMenuItems.size());
				WebElement randomElement = leafMenuItems.get(randomIndex);

				logs.debug("Random selected item from: " + randomIndex);

				// Get the current page URL.
				String href = randomElement.getAttribute("href");
				String elementClassName = randomElement.getAttribute("class");

				// Navigate to the selected random page.
				SelectorUtil.clickOnWebElement(randomElement);

				Thread.sleep(1000);
				SelectorUtil.waitGWTLoadedEventPWA();
				String currentPageUrl = SelectorUtil.getCurrentPageUrl();
				// Get the current page URL.
				logs.debug("Navigated random page path: " + currentPageUrl + "    " + href);

				if (elementClassName.contains(pwaLinkClassNameIdentifier)) {
					// Check if the current page URL different than the previous page URL for PWA
					// mobile.
					if (!href.equalsIgnoreCase(currentPageUrl)) {
						currentPageMatchNavigated = false;
						validateSubMenuNavigation = false;
					}
				} else if (pageUrl.equalsIgnoreCase(currentPageUrl)) {
					// Check if the current page URL different than the previous page URL for
					// tablet.
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
}
