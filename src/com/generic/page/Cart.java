package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.generic.selector.CartSelectors;
import com.generic.setup.SelTestCase;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.GlobalVariables;
import com.generic.util.SelectorUtil;

public class Cart extends SelTestCase {

	public static class keys {

		public static final String invalidCoupon = "invalid";

	}
	
	
	//Done CBI
	public static void moveItemsToCartFromWishlist() throws Exception {
		try {
			getCurrentFunctionName(true);
			String subStrArr = CartSelectors.savedListFirstItem.get() + CartSelectors.returnFromWishListBtn.get();
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	//Done CBI
	public static boolean verifySavedList() throws Exception {
		getCurrentFunctionName(true);
		boolean inPDPPage = SelectorUtil.isDisplayed(CartSelectors.savedListFirstItem.get());
		getCurrentFunctionName(false);
		return inPDPPage;
	}

	//Done CBI
	public static void clickRemoveBtnForSavedItem() throws Exception {
		try {
			getCurrentFunctionName(true);
			String subStrArr = CartSelectors.firstAddedItemsRemove.get();
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	//Done CBI
	public static void clickMoveToWishListBtnForSavedItem() throws Exception {
		try {
			getCurrentFunctionName(true);
			String subStrArr = CartSelectors.firstAddedItemsMoveToLater.get();
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	//Done CBI
	public static String getTotalPrice() throws Exception {
		getCurrentFunctionName(true);
		WebElement price = SelectorUtil.getelement(CartSelectors.addedItemsTotalPrice.get());
		getCurrentFunctionName(false);
		return price.getText().trim();
	}

	//Done CBI
	public static boolean isListDisplayed(List<WebElement> elements) {
		boolean loaded = true;
		for (int i = 0; i < elements.size(); i++) {
			if (!elements.get(i).isDisplayed())
				loaded = false;
		}
		return loaded;
	}

	//Done CBI
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

	//Done CBI
	public static List<WebElement> getAllSavedItemsInCart() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.addedItemsInCart.get());
		List<WebElement> savedElements = SelectorUtil.getAllElements(subStrArr);
		getCurrentFunctionName(false);
		return savedElements;
	}

	//Done CBI
	public static boolean isItemAdded() throws Exception {
		getCurrentFunctionName(true);
		boolean isSaved;
		List<WebElement> savedElements = getAllSavedItemsInCart();
		if (savedElements.size() > 1)
			isSaved = true;
		else
			isSaved = false;
		getCurrentFunctionName(false);
		return isSaved;
	}

	//Done CBI
	public static boolean addedItemImageValidation() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.addedItemsImage.get());
		List<WebElement> savedElements = SelectorUtil.getAllElements(subStrArr);
		boolean displayed = isListDisplayed(savedElements);
		boolean loaded = isListLoaded(savedElements);
		getCurrentFunctionName(false);
		return displayed & loaded;
	}

	//Done CBI
	public static boolean checkAddedItemPriceDisplay() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.addedItemsPrice.get());
		List<WebElement> savedItems = SelectorUtil.getAllElements(subStrArr);
		boolean inDisplayed = isListDisplayed(savedItems);
		getCurrentFunctionName(false);
		return inDisplayed;
	}

	//Done CBI
	public static boolean checkAddedItemTotalPriceDisplay() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.addedItemsTotalPrice.get());
		List<WebElement> savedItems = SelectorUtil.getAllElements(subStrArr);
		boolean inDisplayed = isListDisplayed(savedItems);
		getCurrentFunctionName(false);
		return inDisplayed;
	}

	//Done CBI
	public static String getFirstSavedItemsOptions() throws Exception {

		getCurrentFunctionName(true);
		SelectorUtil.initializeSelectorsAndDoActions(CartSelectors.firstAddedItemsOption.get());
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	//Done CBI
	public static String getlastAddedItemsOptions() throws Exception {
		getCurrentFunctionName(true);
		SelectorUtil.initializeSelectorsAndDoActions(CartSelectors.lastAddedItemsOption.get());
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}
	
	//Done CBI
	public static void editOptions() throws Exception {
		try {
			getCurrentFunctionName(true);

			// Click Edit for the first product in cart
			SelectorUtil.initializeSelectorsAndDoActions(CartSelectors.editFromCartLink.get());

			// Expand options accordion for desktop version
			if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
				SelectorUtil.initializeSelectorsAndDoActions(CartSelectors.expandOptionsModal.get());
			}

			// Editing the product
			try {
				// Check if the product has drop-down and edit it
				SelectorUtil.initializeSelectorsAndDoActions(CartSelectors.optionsDropDown.get(), "FFF2");

			} catch (Exception e) {

				try {

					// Check if the product has swatches and select one
					SelectorUtil.initializeSelectorsAndDoActions(CartSelectors.optionsImage.get(), "index,1");

				} catch (Exception e2) {
					Thread.holdsLock(2500);

					// Check if the product has buttons and select one
					List<WebElement> swatches = getDriver()
							.findElements(By.cssSelector(CartSelectors.optionsButton.get()));
					swatches.get(2).click();
				}

			}

			// Click finish and preview button for edit modal on mobile version
			if (isMobile()) {

				if (isGR())
					SelectorUtil.initializeSelectorsAndDoActions(CartSelectors.finishAndPreviewButtonGR.get());
				else
					SelectorUtil.initializeSelectorsAndDoActions(CartSelectors.finishAndPreviewButton.get());
			}

			// Save the edits
			if (isGR())
				SelectorUtil.initializeSelectorsAndDoActions(CartSelectors.saveEditsButtonGR.get());
			else
				SelectorUtil.initializeSelectorsAndDoActions(CartSelectors.saveEditsButton.get());
			getCurrentFunctionName(false);

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}// CATCH

	}// EDIT METHOD



}// MAIN CLASS
