package com.generic.page;

import java.text.MessageFormat;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.generic.selector.CartSelectors;
import com.generic.setup.SelTestCase;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.GlobalVariables;
import com.generic.util.SelectorUtil;

public class Cart extends SelTestCase {

	public static class keys {

		public static final String invalidCoupon = "invalid";

	}

	// Done CBI
	public static String getTaxValue() throws Exception {
		try {
			getCurrentFunctionName(true);
			WebElement price = SelectorUtil.getElement(CartSelectors.tax.get());
			if (price.getText().trim().isEmpty()) {
				price = SelectorUtil.getElement(CartSelectors.taxGR);
			}
			getCurrentFunctionName(false);
			return price.getText().replace("$", "").replace(",", "").trim();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI
	public static String getShippingValue() throws Exception {
		try {
			getCurrentFunctionName(true);
			WebElement price = SelectorUtil.getElement(CartSelectors.shipping.get());
			getCurrentFunctionName(false);
			return price.getText().replace("$", "").replace(",", "").trim();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI
	public static void paypalBtnClick() throws Exception {
		try {
			getCurrentFunctionName(true);
			String subStrArr = CartSelectors.paypalCheckoutBtn.get();
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI
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

	// Done CBI
	public static boolean verifySavedList() throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean inPDPPage = SelectorUtil.isDisplayed(CartSelectors.savedListFirstItem.get());
			getCurrentFunctionName(false);
			return inPDPPage;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI
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

	// Done CBI
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

	// Done CBI
	public static String getTotalPrice() throws Exception {
		try {
			getCurrentFunctionName(true);
			WebElement price = SelectorUtil.getElement(CartSelectors.addedItemsTotalPrice.get());
			getCurrentFunctionName(false);
			return price.getText().trim();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI
	public static boolean isListLoaded(List<WebElement> elements) {
		try {
			getCurrentFunctionName(true);
			boolean result = HomePage.isListLoaded(elements);
			getCurrentFunctionName(false);
			return result;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI
	public static List<WebElement> getAllAddedItemsInCart() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<WebElement> savedElements = SelectorUtil.getAllElements(CartSelectors.addedItemsInCart.get());
			getCurrentFunctionName(false);
			return savedElements;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI
	public static boolean isItemAdded() throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean isAdded;
			List<WebElement> savedElements = getAllAddedItemsInCart();
			if (savedElements.size() >= 1)
				isAdded = true;
			else
				isAdded = false;
			getCurrentFunctionName(false);
			return isAdded;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI
	public static boolean addedItemImageValidation() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<WebElement> savedElements = SelectorUtil.getAllElements(CartSelectors.addedItemsImage.get());
			boolean displayed = HomePage.isListDisplayed(savedElements);
			boolean loaded = isListLoaded(savedElements);
			getCurrentFunctionName(false);
			return displayed & loaded;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI
	public static boolean checkAddedItemPriceDisplay() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<WebElement> savedItems = SelectorUtil.getAllElements(CartSelectors.addedItemsPrice.get());
			boolean inDisplayed = HomePage.isListDisplayed(savedItems);
			getCurrentFunctionName(false);
			return inDisplayed;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI
	public static boolean checkAddedItemTotalPriceDisplay() throws Exception {

		try {
			getCurrentFunctionName(true);
			boolean isDisplayed = SelectorUtil.isDisplayed(CartSelectors.addedItemsTotalPrice.get());
			getCurrentFunctionName(false);
			return isDisplayed;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI
	public static String getFirstSavedItemsOptions() throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(CartSelectors.firstAddedItemsOption.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI
	public static String getlastAddedItemsOptions() throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(CartSelectors.lastAddedItemsOption.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done CBI
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
				if (isMobile()) {
					SelectorUtil.initializeSelectorsAndDoActions(CartSelectors.optionsDropDown.get(), "FFF2");
				} else {
					List<WebElement> selects = SelectorUtil.getElementsList(CartSelectors.optionsDropDown.get());
					for (int i = 0; i < selects.size(); i++) {
						WebElement ele = selects.get(i);
						Select dropDownList = new Select(ele);
						dropDownList.selectByIndex(2);
					}
				}

			} catch (Exception e) {

				try {
					// Check if the product has swatches and select one
					// SelectorUtil.initializeSelectorsAndDoActions(CartSelectors.optionsImage.get(),
					// "index,1");
					List<WebElement> imgs = getDriver().findElements(By.cssSelector(CartSelectors.optionsImage.get()));
					if (imgs.size() < 3)
						throw new Exception();
					WebElement ele = imgs.get(imgs.size() - 1);
					JavascriptExecutor executor = (JavascriptExecutor) getDriver();
					executor.executeScript("arguments[0].click();", ele);

				} catch (Exception e2) {
					Thread.sleep(2500);

					// Check if the product has buttons and select one
					SelectorUtil.initializeSelectorsAndDoActions(CartSelectors.optionsButton.get());
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
		} // CATCH

	}// EDIT METHOD

}// MAIN CLASS
