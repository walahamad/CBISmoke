package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.generic.selector.CartSelectors;
import com.generic.setup.SelTestCase;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.LoggingMsg;
import com.generic.util.SelectorUtil;

public class Cart extends SelTestCase {

	public static class keys {

		public static final String invalidCoupon = "invalid";

	}

	public static void clickCheckout() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_CART_BUTTON, "checkout"));
		subStrArr.add(CartSelectors.checkoutBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}

	public static void clickContinueShoping() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_CART_BUTTON, "continue shopping"));
		subStrArr.add(CartSelectors.continueShopping);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}

	public static String getNumberOfproducts() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.numberOfProducts);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		logs.debug(MessageFormat.format(LoggingMsg.NUMBER_OF_PRODUCTS, SelectorUtil.textValue));
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
	}

	public static String getOrderTotal() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.cartOrderTotal);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		logs.debug(MessageFormat.format(LoggingMsg.ORDER_TOTAL, SelectorUtil.textValue));
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
	}

	public static String getOrderSubTotal() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.OrderSubTotal);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		logs.debug(MessageFormat.format(LoggingMsg.ORDER_SUBTOTAL, SelectorUtil.textValue));
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;

	}

	public static String getOrderTax() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.cartOrderTax);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		logs.debug("Order tax: " + SelectorUtil.textValue);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;

	}

	public static void applyCoupon(String coupon) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		if (!"".equals(coupon)) {
			logs.debug(MessageFormat.format(LoggingMsg.APPLYING_COUPON, "Applying", coupon));
			writeCoupon(coupon);
			clickApplycoupon();
		} else {
			logs.debug(MessageFormat.format(LoggingMsg.APPLYING_COUPON, "cannot apply", coupon));
		}
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

	public static boolean validateinvaldcoupon() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.couponErrorMessage);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue));
		return !"".equals(SelectorUtil.textValue);
	}

	public static void updateQuantityValue(String qty) throws Exception {
		// Limited to edit first product qty
		getCurrentFunctionName(true);
		writeNewQunatity(qty);
		updateQunatity();
		getCurrentFunctionName(false);
	}

	private static void updateQunatity() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.updateQunatityBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}

	private static void writeNewQunatity(String qty) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.qty);
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
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue));
		} catch (Exception e) {
			// to make sure the application is throwing the correct exception
			if (ExceptionMsg.noValidSelector.contains(e.getMessage()))
				throw new Exception(ExceptionMsg.noErrorMsg);
			else
				throw new Exception(e);
		}
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
	}

	public static String getCartMsg() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		try {
			subStrArr.add(CartSelectors.errorMessage);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue));
		} catch (Exception e) {
			// to make sure the application is throwing the correct exception
			if (ExceptionMsg.noValidSelector.contains(e.getMessage()))
				throw new Exception(ExceptionMsg.noErrorMsg);
			else
				throw new Exception(e);
		}
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
	}

	public static boolean isCartEmpty() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(CartSelectors.cartContent);
		valuesArr.add("");
		try {
			SelectorUtil.textValue = "";
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		} catch (Exception e) {
			logs.debug(MessageFormat.format(LoggingMsg.EMPTY_CART_ERROR_MSG, e.getMessage()));
		}
		getCurrentFunctionName(false);
		return (SelectorUtil.textValue.contains("empty") ? true : false);
	}

	public static void removeAllItemsFromCart() throws Exception {
		getCurrentFunctionName(true);

		if (!isCartEmpty()) {
			int numberOfItems = Integer.parseInt(getNumberOfproducts().split(" ")[0]);
			logs.debug(LoggingMsg.REMOVE_ALL_ITEMS_FROM_CART);
			for (int itemIndex = numberOfItems - 1; itemIndex >= 0; itemIndex--)
				removeItemFromCart(0); // keep always remove the first item
		} else {
			logs.debug(LoggingMsg.NO_ITEMS_TO_BE_REMOVED);
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
		subStrArr.add(CartSelectors.removeItem + itemIndex);
		valuesArr.add("");
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
		logs.debug("Order shipping: " + SelectorUtil.textValue);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
	}
}
