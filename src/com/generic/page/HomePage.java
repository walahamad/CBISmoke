package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.generic.selector.HomePageSelectors;
import com.generic.setup.GlobalVariables;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.LoggingMsg;
/**
 * The Class HomePage.
 */
public class HomePage extends SelTestCase {
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

	public static void  clickOnLogo() throws Exception {
		getCurrentFunctionName(true);
		logs.debug("Clicking on Site logo");
		SelectorUtil.initializeSelectorsAndDoActions(HomePageSelectors.logo.get());
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
    
	public static void clickOnMiniCart() throws Exception {
		getCurrentFunctionName(true);
		String subStrArr = HomePageSelectors.miniCartBtn.get();
		String valuesArr = "ForceAction,hover";
		if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.chrome)) {
			logs.debug("Clicking on Mini Cart");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
		} else {
			logs.debug("Hovering on Mini Cart");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
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
	
	public static void  clickOnMiniCartCloseBtn() throws Exception {
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
		if(isNotDisplayed==false)
			if(!SelectorUtil.isDisplayed(subStrArr))
				isNotDisplayed=true;
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
		if ("Search - Keyword or Item #".equals(readSearchFieldPlaceHolderText()))
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
}
