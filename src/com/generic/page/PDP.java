package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.generic.selector.HomePageSelectors;
import com.generic.selector.PDPSelectors;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.GlobalVariables;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class PDP extends SelTestCase {

	public static class keys {
		//to be replaced with Search
		public static String FG_PDP = "/cheyne-high-low-area-rug/915951";
		public static String FG_PDP2 = "/barrow-chesterfield-sofa/691936";
		public static String FG_BundlePDP = "/resort-cotton-bath-towels/155771";
		public static String FG_BundlePDP2 = "/premium-monogrammed-disposable-napkins/605617?brReference=BRWidget";
		public static String FG_BundlePDP3 = "/monogrammed-faux-linen-guest-towels/bath/bath-towels-guest-towels/153131";
		public static String GR_PDP = "/mason-cocoon-chairs-2c-set-of-two/outdoor-living/seating/1020166";
		public static String GR_BundlePDP = "/cordoba-collection/154964";
		
		public static final String id = "id";
		public static final String name = "name";
		public static final String title = "title";
		public static final String url = "url";
		public static final String qty = "qty";
		public static final String color = "color";
		public static final String fleece = "fleece";
		public static final String memory = "memory";
		public static final String size = "size";
		public static final String bundleProducts = "bundleProducts";
		public static final String desc = "desc";
		public static final String price = "price";

	}

	// done - SMK
	// to be replaced with Search
	public static void NavigateToFGPDP() throws Exception {
		getCurrentFunctionName(true);
		getDriver().get(getURL() + keys.FG_BundlePDP2);
		getCurrentFunctionName(false);
	}

	// done - SMK
	// to be replaced with Search
	public static void NavigateToGRPDP() throws Exception {
		getCurrentFunctionName(true);
		getDriver().get(getURL() + keys.GR_BundlePDP);
		getCurrentFunctionName(false);
	}

	// done - SMK
	public static void selectColor() throws Exception {
		try {
			getCurrentFunctionName(true);
			Thread.sleep(1000);
			String subStrArr = PDPSelectors.allColors.get();
			if (!SelectorUtil.isNotDisplayed(subStrArr)) {
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// done - SMK
	public static void selectSize() throws Exception {
		try {
			getCurrentFunctionName(true);
			Thread.sleep(1000);
			String subStrArr = PDPSelectors.allSizes.get();
			String valuesArr = "FFF1";
			if (!SelectorUtil.isNotDisplayed(subStrArr)) {
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// done - SMK
	public static void selectFabric() throws Exception {
		try {
			getCurrentFunctionName(true);
			Thread.sleep(1000);
			String subStrArr = PDPSelectors.allFabrics.get();
			if (!SelectorUtil.isNotDisplayed(subStrArr)) {
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// done - SMK
	public static void selectShipLeadTime() throws Exception {
		try {
			getCurrentFunctionName(true);
			Thread.sleep(1000);
			String subStrArr = PDPSelectors.allShipLeadTime.get();
			String valuesArr;
			if (!SelectorUtil.isNotDisplayed(subStrArr)) {
				int index = SelectorUtil.getAllElements(subStrArr).size() - 1;
				logs.debug("Selected Index for option is: " + index);
				valuesArr = "index," + index + ",ForceAction,click";
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// done - SMK
//	No longer needed, will be removed after validating the new function is working correctly
//	public static void addProductsToCartV1() throws Exception {
//		try {
//			getCurrentFunctionName(true);
//			selectFabric();
//			Thread.sleep(1000);
//			selectShipLeadTime();
//			Thread.sleep(1000);
//			selectColor();
//			Thread.sleep(1000);
//			selectSize();
//			Thread.sleep(1000);
//			if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
//				Thread.sleep(1000);
//			}
//			clickAddToCartButton();
//			Thread.sleep(1000);
//			getCurrentFunctionName(false);
//		} catch (NoSuchElementException e) {
//			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
//			}.getClass().getEnclosingMethod().getName()));
//			throw e;
//		}
//	}

	// done - SMK
	public static void clickAddToCartButton() throws Exception {
		try {
			getCurrentFunctionName(true);
			String subStrArr = PDPSelectors.addToCartBtn.get();
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done - SMK
	public static void clickAddToCartCloseBtn() throws Exception {
		try {
			getCurrentFunctionName(true);
			String subStrArr = PDPSelectors.addToCartCloseBtn.get();
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done - SMK
	// This method to return all available options except the sizes dropdown
	// Size drop-down has a different selector
	public static int getNumberOfOptions() throws Exception {
		getCurrentFunctionName(true);
		String subStrArr = PDPSelectors.avaibleOptions.get();
		int numberOfAvaibleOptions = 0;
		logs.debug(SelectorUtil.numberOfFoundElements.get());
		if (!SelectorUtil.isNotDisplayed(subStrArr)) {
			numberOfAvaibleOptions = Integer.parseInt(SelectorUtil.numberOfFoundElements.get());
		}
		logs.debug("number Of Avaible Options" + SelectorUtil.numberOfFoundElements.get());
		getCurrentFunctionName(false);
		return numberOfAvaibleOptions;
	}

	// done - SMK
	// This method to return all available List Boxes
	public static int getNumberListBoxes() throws Exception {
		getCurrentFunctionName(true);
		String Str = PDPSelectors.allSizes.get();
		int numberOfListBoxes = 0;
		logs.debug(SelectorUtil.numberOfFoundElements.get());
		if (!SelectorUtil.isNotDisplayed(Str)) {
			numberOfListBoxes = Integer.parseInt(SelectorUtil.numberOfFoundElements.get());
		}
		logs.debug("number Of Avaible List Boxes" + SelectorUtil.numberOfFoundElements.get());
		getCurrentFunctionName(false);
		return numberOfListBoxes;
	}

	// done - SMK
	public static void selectNthListBoxFirstValue(int index) throws Exception {
		getCurrentFunctionName(true);
		String Str = PDPSelectors.allSizes.get();
		String value = "index," + index + ",FFF1";
		SelectorUtil.initializeSelectorsAndDoActions(Str, value);
		getCurrentFunctionName(false);

	}

	// done - SMK
	public static void selectNthOptionFirstSwatch(int index) throws Exception {
		getCurrentFunctionName(true);
		String subStrArr = MessageFormat.format(PDPSelectors.firstSwatchInOptions.get(), index);
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, subStrArr));
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
		// Clicking on the div on desktop and iPad does not select the options,
		// you need to click on the img if there is an img tag.
		if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
			String nthSel = subStrArr + ">img";
			if (!SelectorUtil.isNotDisplayed(nthSel))
				SelectorUtil.initializeSelectorsAndDoActions(nthSel);
		}
		getCurrentFunctionName(false);

	}

	// done - SMK
	// This method to dynamically select all available options
	public static void selectSwatchesSingle() throws Exception {
		try {
			getCurrentFunctionName(true);
			int numberOfPanels = getNumberOfOptions();
			int numberOfListBoxes = getNumberListBoxes();
			if (numberOfPanels != 0) {
				for (int i = 1; i <= numberOfPanels; i++) {
					selectNthOptionFirstSwatch(i);
					Thread.sleep(1500);
				}
			}

			if (numberOfListBoxes != 0) {
				for (int i = 0; i < numberOfListBoxes; i++) {
					selectNthListBoxFirstValue(i);
					Thread.sleep(1500);
				}
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done - SMK
	public static void addProductsToCart() throws Exception {
		getCurrentFunctionName(true);
		selectSwatches();
		clickAddToCartButton();
		Thread.sleep(1000);
		getCurrentFunctionName(false);

	}

	public static boolean validatePriceIsDisplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed;
		logs.debug("Validate if top price exist");
		String selector = PDPSelectors.topPriceSingle.get();
		if(getNumberOfItems() > 1) {
			String ProductID = getProductID(0);
			selector = "css,#" + ProductID + ">" + PDPSelectors.topPriceSingle.get().replace("css,", "");
		}
		isDisplayed = SelectorUtil.isDisplayed(selector);
		getCurrentFunctionName(false);
		return isDisplayed;
	}
	public static boolean validateBundlePriceIsDisplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed;
		logs.debug("Validate if top price exist for Bundle PDP");
		if(getNumberOfItems() > 1) {
			isDisplayed = SelectorUtil.isDisplayed(PDPSelectors.topPriceBundle.get());
		}else {
			logs.debug("This is not a Bundle PDP");	
			isDisplayed = true;
		}
	
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	
	public static boolean validateAddToWLGRIsEnabled() throws Exception {
		getCurrentFunctionName(true);
		boolean isNotDisplayed;
		logs.debug("Validate if Add To WL/GR Is Displayed");
		// here it will pass if the button exist regardless if it is enabled or disabled.
		// because there is no attribute to verify if it is enabled.
		String selectorEnabled = PDPSelectors.addToWLGRBtnEnabledSingle.get();
		String selectorDisabled = PDPSelectors.addToCartBtnDisabledSingle.get();
		if(getNumberOfItems() > 1) {
			String ProductID = getProductID(0);
			selectorEnabled = "css,#" + ProductID + ">" + PDPSelectors.addToWLGRBtnEnabledSingle.get().replace("css,", "");
			selectorDisabled = "css,#" + ProductID + ">" + PDPSelectors.addToCartBtnDisabledSingle.get().replace("css,", "");
		}
		SelectorUtil.isDisplayed(selectorEnabled);
		logs.debug("Validate if Add To WL/GR Is not disabled");
		isNotDisplayed = SelectorUtil.isNotDisplayed(selectorDisabled);
		getCurrentFunctionName(false);
		return isNotDisplayed;
	}

	public static boolean validateAddToCartIsEnabled() throws Exception {
		getCurrentFunctionName(true);
		boolean isNotDisplayed;
		logs.debug("Validate if Add To Cart Is Displayed");
		// here it will pass if the button exist regardless if it is enabled or disabled.
		// because there is no attribute to verify if it is enabled.
		String selectorEnabled = PDPSelectors.addToCartBtnEnabledSingle.get();
		String selectorDisabled = PDPSelectors.addToCartBtnDisabledSingle.get();
		if(getNumberOfItems() > 1) {
			String ProductID = getProductID(0);
			selectorEnabled = "css,#" + ProductID + ">" + PDPSelectors.addToCartBtnEnabledSingle.get().replace("css,", "");
			selectorDisabled = "css,#" + ProductID + ">" + PDPSelectors.addToCartBtnDisabledSingle.get().replace("css,", "");
		}
		SelectorUtil.isDisplayed(selectorEnabled);
		logs.debug("Validate if Add To Cart Is not disabled");
		isNotDisplayed = SelectorUtil.isNotDisplayed(selectorDisabled);
		getCurrentFunctionName(false);
		return isNotDisplayed;
	}

	public static String getBottomPrice() throws Exception {
		getCurrentFunctionName(true);
		logs.debug("Validate if bottom price is updated after seleting options");
		String selector = PDPSelectors.bottomPriceSingle.get();
		if(getNumberOfItems() > 1) {
			String ProductID = getProductID(0);
			selector = "css,#" + ProductID + ">" + PDPSelectors.bottomPriceSingle.get().replace("css,", "");
		}
		SelectorUtil.initializeSelectorsAndDoActions(selector);
		String price = SelectorUtil.textValue.get();
		getCurrentFunctionName(false);
		return price;
	}
	
	// done - SMK
	public static boolean validateProductIsAddedToCart() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed;
		//Validate the add to cart modal is displayed for Desktop and iPad.
		//For Mobile, verify it from mini cart because there is no add to cart modal in mobile.
		if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
			isDisplayed = SelectorUtil.isDisplayed(PDPSelectors.addToCartModal.get());
		} else {
			HomePage.clickOnMiniCart();
			isDisplayed = HomePage.validateMiniCartProductIsDsiplayed();
		}
		return isDisplayed;
	}

	// done - SMK
	public static void clickAddToWLGR() throws Exception {
		try {
			getCurrentFunctionName(true);
			String subStrArr = PDPSelectors.addToWLGRBtnEnabled.get();
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	// done - SMK
	public static String getProductID(int index) throws Exception {
		try {
			getCurrentFunctionName(true);
			String Str = PDPSelectors.itemsID.get();
			String ID = SelectorUtil.getAttrString(Str,"id", index);
			getCurrentFunctionName(false);
			return ID;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	// done - SMK
	public static int getNumberOfItems() throws Exception {
		try {
			getCurrentFunctionName(true);
			String Str = PDPSelectors.numberOfBundleItems.get();
			int numberOfItems = SelectorUtil.getAllElements(Str).size();
			getCurrentFunctionName(false);
			return numberOfItems;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	public static int getNumberOfListsInProduct(String Str) throws Exception {
		try {
			getCurrentFunctionName(true);
		//	String Str = PDPSelectors.ListBox.get();
			int numberOfItems = SelectorUtil.getAllElements(Str).size();
			getCurrentFunctionName(false);
			return numberOfItems;
			
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	public static int getNumberOfActiveListsInProduct(String Str) throws Exception {
		try {
			getCurrentFunctionName(true);
		//	String Str = PDPSelectors.ListBox.get();
			int numberOfActiveLists = SelectorUtil.getAllElements(Str).size();
			getCurrentFunctionName(false);
			return numberOfActiveLists;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	
	// done - SMK
	public static void selectNthOptionFirstSwatchV2(String Str) throws Exception {
		getCurrentFunctionName(true);
		//String StrBundle = MessageFormat.format(Str, index);
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, Str));
		SelectorUtil.initializeSelectorsAndDoActions(Str);
		// Clicking on the div on desktop and iPad does not select the options,
		// you need to click on the img if there is an img tag.
		if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
			String nthSel = Str + ">img";
			if (!SelectorUtil.isNotDisplayed(Str))
				SelectorUtil.initializeSelectorsAndDoActions(nthSel);
		}
		getCurrentFunctionName(false);

	}
	
	// done - SMK
		public static void selectNthListBoxFirstValueV2(String Str,int index) throws Exception {
			getCurrentFunctionName(true);
			String value = "index," + index + ",FFF1";
			SelectorUtil.initializeSelectorsAndDoActions(Str, value);
			getCurrentFunctionName(false);

		}
//		public static int getNumberOfOptions(String Str) throws Exception {
//			getCurrentFunctionName(true);
//			int numberOfAvaibleOptions = 0;
//			logs.debug(SelectorUtil.numberOfFoundElements.get());
//			if (!SelectorUtil.isNotDisplayed(Str)) {
//				numberOfAvaibleOptions = Integer.parseInt(SelectorUtil.numberOfFoundElements.get());
//			}
//			logs.debug("number Of Avaible Options" + SelectorUtil.numberOfFoundElements.get());
//			getCurrentFunctionName(false);
//			return numberOfAvaibleOptions;
//		}
		
		// done - SMK
		public static int getNumberOfimageOptionsInProduct(String Str) throws Exception {
			try {
				getCurrentFunctionName(true);
				int numberOfAvaibleOptions = 0;
				logs.debug(SelectorUtil.numberOfFoundElements.get());
				if (!SelectorUtil.isNotDisplayed(Str)) {
					numberOfAvaibleOptions = Integer.parseInt(SelectorUtil.numberOfFoundElements.get());
				}
				logs.debug("number Of Avaible Options" + SelectorUtil.numberOfFoundElements.get());
				getCurrentFunctionName(false);
				return numberOfAvaibleOptions;
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}
		
	// done - SMK
	public static boolean activeLists(String Str) throws Exception {
		getCurrentFunctionName(true);
		boolean isNotDisplayed = false;
		isNotDisplayed = SelectorUtil.isNotDisplayed(PDPSelectors.activeListBox.get());
		return isNotDisplayed;
	}
	
	public static void selectSwatches() throws Exception {
		try {
			getCurrentFunctionName(true);
			if(getNumberOfItems() > 1) {
				String ProductID = getProductID(0);
				String ListSelector = "css,#" + ProductID + ">" + PDPSelectors.ListBox.get().replace("css,", "");
				String activeLists = "css,#" + ProductID + ">" + PDPSelectors.activeListBox.get().replace("css,", "");
				String swatchContainerSelector = "css,#" + ProductID + ">" + PDPSelectors.swatchContainer.get().replace("css,", "");
				String imageOptionSelector = "css,#" + ProductID + ">" + PDPSelectors.imageOption.get().replace("css,", "");
				
				int numberOfActiveListBoxes = 0;
				int i = 0;
				int listIndex =0;
				int numberOfListBoxes = 0;
			if(!activeLists(activeLists)) {
				numberOfActiveListBoxes = getNumberOfListsInProduct(activeLists);
				numberOfListBoxes = getNumberOfListsInProduct(ListSelector);	
				for ( ; i < numberOfListBoxes; i++) {
					listIndex = i;
					selectNthListBoxFirstValueV2(ListSelector,i);
					Thread.sleep(1000);
					int numberOfNewActiveListBoxes = getNumberOfListsInProduct(activeLists);
					if(numberOfNewActiveListBoxes > numberOfActiveListBoxes) {
						numberOfActiveListBoxes = numberOfNewActiveListBoxes;
					} else {
						break;
					}
				}
			}
				int numberOfimageOptions = getNumberOfimageOptionsInProduct(swatchContainerSelector);
				if (numberOfimageOptions != 0) {
					for (i++; i <= numberOfimageOptions; i++) {
						selectNthOptionFirstSwatchV2("css,#" + ProductID + ">" + MessageFormat.format(PDPSelectors.imageOption.get(),i).replace("css,", ""));
						Thread.sleep(1500);
					}
				}
				if(!activeLists(activeLists)) {
				int numberOfNewActiveListBoxes = getNumberOfListsInProduct(activeLists);
				if(numberOfNewActiveListBoxes > numberOfActiveListBoxes) {
					for (int j = listIndex ; j < numberOfListBoxes; j++) {
					selectNthListBoxFirstValueV2(ListSelector,listIndex);
					if(numberOfNewActiveListBoxes > numberOfActiveListBoxes) {
						numberOfActiveListBoxes = numberOfNewActiveListBoxes;
					} else {
						break;
					}
					}
				}
				}
			
			} else {
				selectSwatchesSingle();
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	

	// done -ocm
	public static String getPDPUrl(String url) {
		try {
			getCurrentFunctionName(true);
			String PDPurl = getCONFIG().getProperty("HomePage") + url;
			getCurrentFunctionName(false);
			return PDPurl;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static void addProductsToCartAndClickCheckOut(LinkedHashMap<String, String> productDetails)
			throws Exception {
		try {
			getCurrentFunctionName(true);
			addProductsToCart(productDetails);
			clickcheckoutBtnCartPopup();
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done -ocm
	public static void addProductsToCart(LinkedHashMap<String, String> productDetails) throws Exception {
		try {
			getCurrentFunctionName(true);

			String PDPURL = PDP.getPDPUrl(productDetails.get(PDP.keys.url));
			logs.debug("productDetails to be visted: " + Arrays.asList(productDetails));
			logs.debug("url key " + PDP.keys.url);
			logs.debug("url key value " + PDPURL);
			getDriver().get(PDPURL);

			// Apply color and check of the results reflected to PDP
			if (!((String) productDetails.get(PDP.keys.color)).equals("")) {
				logs.debug("selecting color: " + (String) productDetails.get(PDP.keys.color));
				selectColor((String) productDetails.get(PDP.keys.color));
			} // variant color

			// Apply size
			if (!((String) productDetails.get(PDP.keys.size)).equals("")) {

				logs.debug("selecting size: " + (String) productDetails.get(PDP.keys.size));
				selectSize((String) productDetails.get(PDP.keys.size));
			} // size check

			// Apply fleece
			if (!((String) productDetails.get(PDP.keys.fleece)).equals("")) {
				logs.debug("selecting fleece: " + (String) productDetails.get(PDP.keys.fleece));
				selectFleece((String) productDetails.get(PDP.keys.fleece));
			} // fleece check

			// Apply memory
			if (!((String) productDetails.get(PDP.keys.memory)).equals("")) {
				logs.debug("selecting memory: " + (String) productDetails.get(PDP.keys.memory));
				selectMemory((String) productDetails.get(PDP.keys.memory));
			} // fleece check

			clickAddToCartBtn();
			Thread.sleep(1000);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}// add to cart

	// Done-ocm
	public static String getPrice() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.price);
			valuesArr.add("gettext");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	private static void clickcheckoutBtnCartPopup() throws Exception {
		getCurrentFunctionName(true);
		// TODO: pull from config
		getDriver().get(Cart.getCartUrl());
		getCurrentFunctionName(false);
	}

	// done-ocm
	private static void clickAddToCartBtn() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.addToCartBtn.get());
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	private static void defineQty(String qty) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.qty);
		valuesArr.add(qty);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	// done-ocm
	public static String getId() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.id);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-ocm
	public static String getTitle() throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(PDPSelectors.title.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-ocm
	public static String getProductDesc() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.desc);
			valuesArr.add("gettext");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			String information = SelectorUtil.textValue.get();
			logs.debug("product info: " + information);
			getCurrentFunctionName(false);
			return information;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-ocm
	public static boolean checkAddToCartButton() throws Exception {
		try {
			getCurrentFunctionName(true);
			Thread.sleep(1500);
			List<String> subStrArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.addToCartBtn.get());
			boolean isDisplayed = SelectorUtil.isDisplayed(subStrArr);
			logs.debug("existence check result is " + isDisplayed);
			getCurrentFunctionName(false);
			return isDisplayed;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-ocm
	public static String getAllsocialMediabuttons() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.socialMediaButtons);
			valuesArr.add("gettext");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static String getcolor() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.colorLable);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static void selectFamilySize(String FamilySize) throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(FamilySize);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// done-ocm
	public static void selectSize(String size) throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(size);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	public static String getSelectedSizeAndFamily() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.sizeAndFamilyLable);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done-CBK
	public static void selectLength(String length) throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(length);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static String getselectedLength() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.lengthLable);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-ocm
	public static void selectColor(String color) throws Exception {
		try {
			getCurrentFunctionName(true);
			Thread.sleep(1000);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.allColors.get());
			valuesArr.add("");
			List<WebElement> colors = SelectorUtil.getAllElements(subStrArr);
			logs.debug("found elements is : " + colors.get(0));
			logs.debug("Getting color " + "//*[contains(@data-bname,'" + color + "')]");
			WebElement SelectedColor = colors.get(0)
					.findElement(By.xpath("//*[contains(@data-bname, '" + color + "')]"));
			logs.debug(SelectedColor.getSize().toString());

			JavascriptExecutor jse1 = (JavascriptExecutor) getDriver();
			jse1.executeScript("arguments[0].scrollIntoView(false)", SelectedColor);

			SelectedColor.click();
			Thread.sleep(3000);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// done-ocm
	public static void selectFleece(String fleece) throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(fleece);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-ocm
	public static void selectMemory(String memory) throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(memory);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	public static void hoverMiniCart() throws Exception {
		getCurrentFunctionName(true);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(SelTestCase.getDriver()).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		WebElement field = getDriver().findElement(By.cssSelector(HomePageSelectors.miniCartBtn.get().replace("css,", "")));

		JavascriptExecutor jse = (JavascriptExecutor) getDriver();
		jse.executeScript("arguments[0].scrollIntoView(false)", field);
		Thread.sleep(200);
		WebElement field2 = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.cssSelector(HomePageSelectors.miniCartBtn.get().replace("css,", "")));
			}
		});

		Actions HoverAction = new Actions(getDriver());
		HoverAction.moveToElement(field2).click().build().perform();
		getCurrentFunctionName(false);
	}

	public static String getProductQtyInMiniCart() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.cartPopupProductQty);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	public static String getProductUnitPriceInMiniCart() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.miniCartProductUnitPrice);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	// done-OCM
	public static void clickShareBtn() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.shareBtn);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// done-OCM
	public static String getNumberOfProductsInBundle() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.numberOfProductsInBundle);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get().split(" ")[1];
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-OCM
	public static String countProductsInBundle() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.productsInBundle);
			String numberOfProducts = SelectorUtil.getAllElements(subStrArr).size() + "";
			getCurrentFunctionName(false);
			return numberOfProducts.trim();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-ocm
	public static String getAllProductsBundle() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.productsInBundle);
			valuesArr.add("");
			List<WebElement> allProducts = SelectorUtil.getAllElements(subStrArr);
			String allProductsTitles = "";
			for (WebElement product : allProducts) {
				allProductsTitles = allProductsTitles + product.getText() + "\n";
			}
			logs.debug("all products is : " + allProductsTitles);
			getCurrentFunctionName(false);
			return allProductsTitles;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-ocm
	public static String getNumberofReviews() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.reviewsCount);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get().split(" ")[0];
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-ocm
	public static String countReviews() throws Exception {
		try {
			getCurrentFunctionName(true);
			int reviewsCount = 0;
			List<String> subStrArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.reviewCountPerEachRate);
			List<WebElement> reviewsHolders = SelectorUtil.getAllElements(subStrArr);
			for (WebElement holder : reviewsHolders) {
				if (!getBrowserName().contains("IE"))
					reviewsCount = reviewsCount + Integer.parseInt(holder.getText().split("\n")[0].trim());
				else
					reviewsCount = reviewsCount + Integer.parseInt(holder.getText().split(" ")[0].trim());
			}
			logs.debug("Reviews count is : " + reviewsCount);
			getCurrentFunctionName(false);
			return reviewsCount + "";
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-ocm
	public static String getRatingFromStars() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.starsRating);
			// style="width: 90% !important;"
			WebElement ratingsElements = SelectorUtil.getAllElements(subStrArr).get(0);
			String starsRating = ratingsElements.getAttribute("style").split(" ")[1].replace("%", "");
			double rating = Double.parseDouble(starsRating) / 20;
			logs.debug("Stars rating is : " + rating);
			getCurrentFunctionName(false);
			return rating + "";
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-ocm
	public static String getSecondaryRating() throws Exception {
		try {
			getCurrentFunctionName(true);
			getNumberofReviews();
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.secondaryRating);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get().split("\n")[2];
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-ocm
	public static void closeSocialShare() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.closSocialShareBtn);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-ocm
	public static void clickOnDesc() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();

			if (!getBrowserName().contains("mobile"))
				subStrArr.add(PDPSelectors.PDPnavs);
			else
				subStrArr.add(PDPSelectors.PDPnavs_mobile);

			SelectorUtil.getNthElement(subStrArr, 1).click();
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// done-OCM
	public static String getRandomProduct(String KeyWord) throws Exception {
		try {
			getCurrentFunctionName(true);
			PLP.searchProduct(KeyWord);
			Thread.sleep(3000);
			if(getBrowserName().equalsIgnoreCase(GlobalVariables.browsers.firefox)||getBrowserName().equalsIgnoreCase(GlobalVariables.browsers.IE))
				Thread.sleep(4000);
			PLP.pickRandomPDP();
			String ProductTitle = getTitle();
			getCurrentFunctionName(false);
			return ProductTitle;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// done-OCM
	public static void addToFavorite() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.favButton);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done -ocm
	public static void addRandomProductsToCart() throws Exception {
		try {
			getCurrentFunctionName(true);
			// random search in case of multiple calls
			String[] Items = getCONFIG().getProperty("RandomItems").split(",");
			Random random = new Random(System.currentTimeMillis());
			int range = Items.length - 1;
			if (range > 0)
				navigateToRandomPDP(Items[random.nextInt(range)]);
			else
				navigateToRandomPDP(Items[0]);
			Thread.sleep(3000);
			if (getBrowserName().equals("IE"))
				Thread.sleep(2000);
			selectAllVariants();
			Thread.sleep(3000);
			if (getBrowserName().equals("IE"))
				Thread.sleep(2000);
			clickAddToCartBtn();
			if (getBrowserName().equals("IE"))
				Thread.sleep(2000);
			Thread.sleep(1000);

			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}// add to cart randomly

	public static void navigateToRandomPDP(String keyword) throws Exception {
		try {
			getCurrentFunctionName(true);
			PLP.searchProduct(keyword);
			Thread.sleep(5000);
			if (getBrowserName().equals("IE"))
				Thread.sleep(2000);
			PLP.pickRandomPDP();
			Thread.sleep(3000);
			if (getBrowserName().equals("IE"))
				Thread.sleep(2000);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	public static void selectAllVariants() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.optionHolder);
			if (SelectorUtil.isNotDisplayed(subStrArr)) {
				logs.debug("No variants to select from. <br>\n");
				return;
			}
			List<WebElement> optionHolders = SelectorUtil.getAllElements(subStrArr);
			int holdersCount = optionHolders.size();
			boolean noVariants = true;
			String OptionTitle = "";
			for (int i = 0; i < holdersCount; i++) {
				if (!SelectorUtil.isDisplayed(subStrArr, i)) {
					continue;
				}
				WebElement holder = SelectorUtil.getNthElement(subStrArr, i);
				OptionTitle = holder.findElements(By.cssSelector(PDPSelectors.optionHolderTitle)).get(0).getText();
				if (OptionTitle.equals("")) {
					OptionTitle = holder.findElements(By.cssSelector(PDPSelectors.optionHolderTitle)).get(1).getText();
				}
				WebElement variant = holder.findElement(By.cssSelector(PDPSelectors.randomVariant));
				logs.debug("selecting from " + OptionTitle + " variant:" + variant.getText() + "<br>\n");
				if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.firefox)) {
					logs.debug("clicking..." + SelTestCase.getBrowserName());
					variant.click();
				} else
					((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()", variant);
				noVariants = false;

			}
			if (noVariants) {
				logs.debug("No variants to select from. <br>\n");
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

}