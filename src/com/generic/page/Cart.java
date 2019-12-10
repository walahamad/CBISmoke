package com.generic.page;

import java.awt.Color;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.generic.selector.CartSelectors;
import com.generic.selector.PDPSelectors;
import com.generic.setup.SelTestCase;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.GlobalVariables;
import com.generic.setup.LoggingMsg;
import com.generic.setup.PagesURLs;
import com.generic.util.SelectorUtil;
import com.sun.corba.se.pept.transport.Selector;

public class Cart extends SelTestCase {

	public static class keys {

		public static final String invalidCoupon = "invalid";

	}
	public static String getTaxValue() throws Exception {
		getCurrentFunctionName(true);
		WebElement price = SelectorUtil.getelement(CartSelectors.tax.get());
		getCurrentFunctionName(false);
		return price.getText().replace("$","").trim();
	}
	
	public static String getShippingValue() throws Exception {
		getCurrentFunctionName(true);
		WebElement price = SelectorUtil.getelement(CartSelectors.shipping.get());
		getCurrentFunctionName(false);
		return price.getText().replace("$","").trim();
	}
///////////////////////////////////////////////////////////////////////////////////////////////////	
	public static void paypalBtnClick() throws Exception {
		try {
			getCurrentFunctionName(true);
			String subStrArr =CartSelectors.paypalCheckoutBtn.get();
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

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

	public static void clickBtnToViewSavedBtn() throws Exception {
		try {
			getCurrentFunctionName(true);
			String subStrArr = "css,.c-personalization-accordion__preview-save>button";
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static boolean verifySavedList() throws Exception {
		getCurrentFunctionName(true);
		boolean inPDPPage = SelectorUtil.isDisplayed(CartSelectors.savedListFirstItem.get());
		getCurrentFunctionName(false);
		return inPDPPage;
	}

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

	public static String getTotalPrice() throws Exception {
		getCurrentFunctionName(true);
		WebElement price = SelectorUtil.getelement(CartSelectors.addedItemsTotalPrice.get());
		getCurrentFunctionName(false);
		return price.getText().trim();
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

	public static List<WebElement> getAllSavedItemsInCart() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.addedItemsInCart.get());
		List<WebElement> savedElements = SelectorUtil.getAllElements(subStrArr);
		getCurrentFunctionName(false);
		return savedElements;
	}

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

	public static boolean isListEmpty() throws Exception {
		getCurrentFunctionName(true);
		boolean result;
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.firstAddedItemsEdit.get());
		if (SelectorUtil.isNotDisplayed(subStrArr))
			result = true;
		else
			result = false;
		getCurrentFunctionName(false);
		return result;
	}

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

	public static boolean VerifyPDPPage() throws Exception {
		getCurrentFunctionName(true);
		boolean inPDPPage = SelectorUtil.isDisplayed(CartSelectors.addToCartBtn.get());
		getCurrentFunctionName(false);
		return inPDPPage;
	}

	public static boolean VerifyEditItemPage() throws Exception {
		getCurrentFunctionName(true);
		boolean inEditItemPage = SelectorUtil.isNotDisplayed(CartSelectors.editItemOpions.get());
		getCurrentFunctionName(false);
		return !inEditItemPage;
	}

	public static boolean checkAddedItemPriceDisplay() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.addedItemsPrice.get());
		List<WebElement> savedItems = SelectorUtil.getAllElements(subStrArr);
		boolean inDisplayed = isListDisplayed(savedItems);
		getCurrentFunctionName(false);
		return inDisplayed;
	}

	public static boolean checkAddedItemTotalPriceDisplay() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.addedItemsTotalPrice.get());
		List<WebElement> savedItems = SelectorUtil.getAllElements(subStrArr);
		boolean inDisplayed = isListDisplayed(savedItems);
		getCurrentFunctionName(false);
		return inDisplayed;
	}

	public static List<String> getFirstSavedItemsOptions() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.firstAddedItemsOption.get());
		List<WebElement> options = SelectorUtil.getAllElements(subStrArr);
		List<String> optionSelector = new ArrayList<String>();
		for (int i = 0; i < options.size(); i++) {

			optionSelector.add(options.get(i).getText().trim());

		}
		getCurrentFunctionName(false);
		return optionSelector;
	}

	public static List<String> getlastAddedItemsOptions() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.addedItemsInCart.get());
		List<WebElement> items = SelectorUtil.getAllElements(subStrArr);
		WebElement lastItem = items.get(items.size() - 1);
		List<WebElement> options = lastItem.findElements(By.cssSelector(CartSelectors.lastAddedItemsOption.get()));
		List<String> optionSelector = new ArrayList<String>();
		for (int i = 0; i < options.size(); i++) {

			optionSelector.add(options.get(i).getText().trim());
		}
		getCurrentFunctionName(false);
		return optionSelector;
	}

	public static void clickEditBtnForAddedItem() throws Exception {
		try {
			getCurrentFunctionName(true);
			String subStrArr = CartSelectors.firstAddedItemsEdit.get();
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static void selectNthOptionLastSwatch(int index) throws Exception {
		getCurrentFunctionName(true);
		String subStrArr = MessageFormat.format(CartSelectors.editSwatchOptions.get(), index);
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
			if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {

				if (SelTestCase.getBrand().contains("GR"))
					SelectorUtil.initializeSelectorsAndDoActions(CartSelectors.finishAndPreviewButtonGR.get());
				else
					SelectorUtil.initializeSelectorsAndDoActions(CartSelectors.finishAndPreviewButton.get());
			}

			// Save the edits
			if (SelTestCase.getBrand().contains("GR"))
				SelectorUtil.initializeSelectorsAndDoActions(CartSelectors.saveEditsButtonGR.get());
			else
				SelectorUtil.initializeSelectorsAndDoActions(CartSelectors.saveEditsButton.get());
			getCurrentFunctionName(false);

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}


	public static void selectSize() throws Exception {
		try {
			getCurrentFunctionName(true);
			Thread.sleep(1000);
			String subStrArr = PDPSelectors.allSizes.get();
			String valuesArr = "FFF2";
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

	public static void clickSaveAndColseForEdit() throws Exception {
		try {
			getCurrentFunctionName(true);
			String subStrArr = CartSelectors.editItemSavedAndCloseBtn.get();
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static boolean verifyEditModelColsed() throws Exception {
		boolean closed;
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			subStrArr.add(CartSelectors.editItemSavedAndCloseBtn.get());
			closed = SelectorUtil.isNotDisplayed(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

		return closed;
	}

	// done -ocm
	public static void clickCheckout() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.CLICKING_CART_BUTTON, "checkout"));
			subStrArr.add(CartSelectors.checkoutBtn);
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
	public static void selectShippingMethod(String shippingMethod) throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug("selecting shipping " + shippingMethod);
			subStrArr.add(CartSelectors.shippingMethod);
			valuesArr.add(shippingMethod);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static void clickContinueShoping() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.CLICKING_CART_BUTTON, "continue shopping"));
			subStrArr.add(CartSelectors.continueShopping);
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
	public static String getNumberOfproducts() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CartSelectors.numberOfProducts);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			String products = SelectorUtil.textValue.get();
			logs.debug(MessageFormat.format(LoggingMsg.NUMBER_OF_PRODUCTS, products));
			getCurrentFunctionName(false);
			return products.split(" ")[3].split("[(]")[1];
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static String getProductQty(String browser, int lineOrder) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		if (browser.contains("mobile"))
			subStrArr.add(CartSelectors.productQtyBoxMobile + lineOrder);
		else
			subStrArr.add(CartSelectors.productQtyBox + lineOrder);
		valuesArr.add("getCurrentValue");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		logs.debug(MessageFormat.format(LoggingMsg.NUMBER_OF_PRODUCTS, SelectorUtil.textValue.get()));
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	public static String checkProductsExsist() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.numberOfProducts);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		logs.debug(MessageFormat.format(LoggingMsg.NUMBER_OF_PRODUCTS, SelectorUtil.textValue.get()));
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	// done-ocm
	public static String getProductUnitPrice() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CartSelectors.unitPrice);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug("product unit price is: " + SelectorUtil.textValue.get());
			String totals = SelectorUtil.textValue.get();
			getCurrentFunctionName(false);
			return totals;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-ocm
	public static String getProductItemSubtotal() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			if (!getBrowserName().contains("mobile"))
				subStrArr.add(CartSelectors.productSubtotal);
			else
				subStrArr.add(CartSelectors.productSubtotalMobile);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug("product subtotal is: " + SelectorUtil.textValue.get());
			String totals = SelectorUtil.textValue.get();
			getCurrentFunctionName(false);
			return totals.replace("Total: ", "");
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static String getTotals() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.totals);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		logs.debug(MessageFormat.format(LoggingMsg.totalsMsg, SelectorUtil.textValue.get()));
		String totals = SelectorUtil.textValue.get();
		getCurrentFunctionName(false);
		return totals;
	}

	// done-ocm
	public static String getOrderSubTotal() throws Exception {
		try {
			getCurrentFunctionName(true);
			String cartTotals = getCartTotals();
			int index = 0;
			String totals = "00.00";

			for (String total : cartTotals.split("\n")) {
				if (total.contains("Order Subtotal")) {
					totals = cartTotals.split("\n")[index + 1].trim();
					logs.debug("order subtotal is: " + totals);
					break;
				}
				index++;
			}
			getCurrentFunctionName(false);
			return totals;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-ocm
	public static String getCartTotals() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CartSelectors.cartTotals);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug("cart totls: " + SelectorUtil.textValue.get());
			String totals = SelectorUtil.textValue.get();
			getCurrentFunctionName(false);
			return totals;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// done-ocm
	public static String getItemSubTotal() throws Exception {
		try {
			getCurrentFunctionName(true);
			String cartTotals = getCartTotals();

			int index = 0;
			String totals = "00.00";

			for (String total : cartTotals.split("\n")) {
				if (total.contains("Item Subtotal")) {
					totals = cartTotals.split("\n")[index + 1].trim();
					logs.debug("item subtotal: " + totals);
					break;
				}
				index++;
			}

			getCurrentFunctionName(false);
			return totals;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	public static String getPromotionalDiscount() throws Exception {
		getCurrentFunctionName(true);
		String totals = getTotals();
		getCurrentFunctionName(false);
		return totals.split("\n")[5].trim();

	}

	// done-ocm
	public static String getOrderDiscount() throws Exception {
		try {
			getCurrentFunctionName(true);
			String cartTotals = getCartTotals();
			int index = 0;
			String totals = "00.00";

			for (String total : cartTotals.split("\n")) {
				if (total.contains("Discounts")) {
					totals = cartTotals.split("\n")[index + 1].trim();
					logs.debug("order discounts is: " + totals);
					break;
				}
				index++;
			}
			getCurrentFunctionName(false);
			return totals;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	public static String getOrderClubDicount() throws Exception {
		getCurrentFunctionName(true);
		String totals = getTotals();
		getCurrentFunctionName(false);
		return totals.split("\n")[7].trim();

	}

	public static void applyPromotion(String promotion) throws Exception {
		getCurrentFunctionName(true);
		if (!"".equals(promotion)) {
			logs.debug(MessageFormat.format(LoggingMsg.APPLYING_COUPON, "Applying", promotion));
			writeCoupon(promotion);
			clickApplycoupon();
		} else {
			logs.debug(MessageFormat.format(LoggingMsg.APPLYING_COUPON, "cannot apply", promotion));
		}
		getCurrentFunctionName(false);
	}

	// Not included
	public static void removeCoupon() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.removeCoupon);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	private static void clickApplycoupon() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.applyCouponButton);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	private static void writeCoupon(String coupon) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.couponField);
		valuesArr.add(coupon);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	public static String validateCoupon() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		// subStrArr.add(CartSelectors.couponErrorMessage);
		subStrArr.add(CartSelectors.couponMessage);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		logs.debug(MessageFormat.format(LoggingMsg.COUPON_MSG, SelectorUtil.textValue.get()));
		return SelectorUtil.textValue.get();
	}

	public static String getCouponGlobalMessage() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.couponGlobalMessage);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		logs.debug(MessageFormat.format(LoggingMsg.COUPON_MSG, SelectorUtil.textValue.get()));
		return SelectorUtil.textValue.get();
	}

	public static void updateQuantityValue(String browser, String lineOrder, String qty) throws Exception {
		// Limited to edit first product qty
		getCurrentFunctionName(true);
		writeNewQunatity(browser, lineOrder, qty);
		clickUpdateBtn();
		getCurrentFunctionName(false);
	}

	private static void clickUpdateBtn() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.updateBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	private static void writeNewQunatity(String browser, String lineOrder, String qty) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.productQtyBox + lineOrder);
		valuesArr.add(qty);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	public static String getErrorMsg() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		try {
			subStrArr.add(CartSelectors.errorMessage);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
		} catch (Exception e) {
			// to make sure the application is throwing the correct exception
			if (ExceptionMsg.noValidSelector.contains(e.getMessage()))
				throw new Exception(ExceptionMsg.noErrorMsg);
			else
				throw new Exception(e);
		}
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	public static String getCartMsg() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		try {
			subStrArr.add(CartSelectors.postitiveMsg);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
		} catch (Exception e) {
			// to make sure the application is throwing the correct exception
			if (ExceptionMsg.noValidSelector.contains(e.getMessage()))
				throw new Exception(ExceptionMsg.noErrorMsg);
			else
				throw new Exception(e);
		}
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	public static boolean isCartEmpty() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.cartContent);
		valuesArr.add("");
		try {
			SelectorUtil.textValue.set("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		} catch (Exception e) {
			logs.debug(MessageFormat.format(LoggingMsg.EMPTY_CART_ERROR_MSG, e.getMessage()));
		}
		getCurrentFunctionName(false);
		return (SelectorUtil.textValue.get().contains("empty") ? true : false);
	}

	public static void removeAllItemsFromCart() throws Exception {
		getCurrentFunctionName(true);

		List<String> subStrArr = new ArrayList<String>();
		// List<String> valuesArr = new ArrayList<String>();

		subStrArr.add(CartSelectors.removeItem);
		// valuesArr.add("");

		List<WebElement> removeButtons = SelectorUtil.getAllElements(subStrArr);

		int numberOfItems = removeButtons.size();
		logs.debug(LoggingMsg.REMOVE_ALL_ITEMS_FROM_CART);
		for (int itemIndex = 0; itemIndex < numberOfItems; itemIndex++) {
			List<String> valuesArr = new ArrayList<String>();
			valuesArr.add("index," + (itemIndex));
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		}

		getCurrentFunctionName(false);
	}

	public static void removeItemFromCart(int itemIndex) throws Exception {
		getCurrentFunctionName(true);
		clickOnactionMenue(itemIndex);
		clickOnRemove(itemIndex);
		getCartMsg();
		getCurrentFunctionName(false);
	}

	private static void clickOnRemove(int itemIndex) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.removeItem);
		valuesArr.add("index," + itemIndex);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	private static void clickOnactionMenue(int itemIndex) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.actionMenue + itemIndex);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	public static String getOrdarshipping() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.cartOrderShipping);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		logs.debug("Order shipping: " + SelectorUtil.textValue.get());
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	public static boolean checkItemImage() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.itemImages);

		boolean isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		logs.debug("images check result is " + isDisplayed);
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static boolean checkProductLink(String PLink) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.itemLink);
		String attrValue = SelectorUtil.getAttr(subStrArr, "href");
		logs.debug("links check result is: " + attrValue);
		logs.debug("links is: " + PLink);
		getCurrentFunctionName(false);
		return PLink.contains(attrValue);
	}

	// done-ocm
	public static String getCartUrl() {
		try {
			getCurrentFunctionName(true);
			logs.debug("heading to Cart page");
			String caetUrl = getCONFIG().getProperty("HomePage") + PagesURLs.getShoppingCartPage();
			getCurrentFunctionName(false);
			return caetUrl;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
}
