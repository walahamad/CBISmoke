package com.generic.page;


import java.util.ArrayList;
import java.util.List;

import com.generic.selector.cartSelectors;
import com.generic.setup.SelTestCase;
import com.generic.setup.ExceptionMsg;
import com.generic.util.SelectorUtil;

public class cart extends SelTestCase {
    private static final String DOC_URL = getCONFIG().getProperty("testSiteName");
    static List<String> subStrArr = new ArrayList<String>();
	static List<String> valuesArr = new ArrayList<String>();


public static void clickCheckout() throws Exception {
	getCurrentFunctionName(true);
	logs.debug("clicking on checkout btn from cart");
	subStrArr.add(cartSelectors.checkoutBtn);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	getCurrentFunctionName(false);
	
}

public static void clickContinueShoping() throws Exception {
	getCurrentFunctionName(true);
	logs.debug("clicking on continue shopping from cart");
	subStrArr.add(cartSelectors.continueShopping);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	getCurrentFunctionName(false);
	
}

public static String getNumberOfproducts() throws Exception {
	getCurrentFunctionName(true);
	subStrArr.add(cartSelectors.numberOfProducts);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	logs.debug("number of products: "+SelectorUtil.textValue);
	getCurrentFunctionName(false);
	return SelectorUtil.textValue;
}

public static String ordarTotal() throws Exception {
	getCurrentFunctionName(true);
	subStrArr.add(cartSelectors.numberOfProducts);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	logs.debug("Order total: "+SelectorUtil.textValue);
	getCurrentFunctionName(false);
	return SelectorUtil.textValue;
}

public static String ordarSubTotal() throws Exception {
	getCurrentFunctionName(true);
	subStrArr.add(cartSelectors.OrderSubTotal);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	logs.debug("Order subtotal: "+SelectorUtil.textValue);
	getCurrentFunctionName(false);
	return SelectorUtil.textValue;
	
}

public static void applyCoupon(String coupon) throws Exception {
	getCurrentFunctionName(true);
	if (!coupon.equals(""))
	{
		logs.debug("Applying Coupon " + coupon);
		writeCoupon(coupon);
		clickApplycoupon();
	}
	else
	{
		logs.debug("cannot apply coupon " + coupon);
	}
	getCurrentFunctionName(false);
}

private static void clickApplycoupon() throws Exception {
	getCurrentFunctionName(true);
	subStrArr.add(cartSelectors.applyCouponButton);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	getCurrentFunctionName(false);
}

private static void writeCoupon(String coupon) throws Exception {
	getCurrentFunctionName(true);
	subStrArr.add(cartSelectors.couponField);
	valuesArr.add(coupon);
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	getCurrentFunctionName(false);
}

public static boolean validateinvaldcoupon() throws Exception
{
	getCurrentFunctionName(true);
	subStrArr.add(cartSelectors.couponErrorMessage);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	getCurrentFunctionName(false);
	logs.debug(SelectorUtil.textValue);
	return !"".equals(SelectorUtil.textValue);
}

public static void updateQuantityValue(String qty) throws Exception
{
	//Limited to edit first product qty
	getCurrentFunctionName(true);
	writeNewQunatity(qty);
	updateQunatity();
	getCurrentFunctionName(false);
}

private static void updateQunatity() throws Exception {
	getCurrentFunctionName(true);
	subStrArr.add(cartSelectors.updateQunatityBtn);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	getCurrentFunctionName(false);
	
}

private static void writeNewQunatity(String qty) throws Exception {
	getCurrentFunctionName(true);
	subStrArr.add(cartSelectors.qty);
	valuesArr.add(qty);
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	getCurrentFunctionName(false);
}

public static String getErrorMsg() throws Exception {
	getCurrentFunctionName(true);
	try
	{
		subStrArr.add(cartSelectors.errorMessage);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		logs.debug("Error message is: " + SelectorUtil.textValue);
	}catch(Exception e)
	{
		//to make sure the application is throwing the correct exception
		if (ExceptionMsg.noValidSelector.contains(e.getMessage()))
			throw new Exception (ExceptionMsg.noErrorMsg);
		else
			throw new Exception (e);
	}
	getCurrentFunctionName(false);
	return SelectorUtil.textValue;
}

public static String getCartMsg() throws Exception {
	getCurrentFunctionName(true);
	try
	{
		subStrArr.add(cartSelectors.errorMessage);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
		logs.debug("Error message is: " + SelectorUtil.textValue);
	}catch(Exception e)
	{
		//to make sure the application is throwing the correct exception
		if (ExceptionMsg.noValidSelector.contains(e.getMessage()))
			throw new Exception (ExceptionMsg.noErrorMsg);
		else
			throw new Exception (e);
	}
	getCurrentFunctionName(false);
	return SelectorUtil.textValue;
}

public static boolean isCartEmpty() throws Exception
{
	getCurrentFunctionName(true);
	subStrArr.add(cartSelectors.cartContent);
	valuesArr.add("");
	try {
		SelectorUtil.textValue = "";
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	}catch(Exception e)
	{
		logs.debug("Cart is not empty " + e.getMessage());
	}
	getCurrentFunctionName(false);
	return (SelectorUtil.textValue.contains("empty") ? true:false);
}


public static void removeAllItemsFromCart() throws Exception
{
	getCurrentFunctionName(true);
	
	if (!isCartEmpty())
	{
		int numberOfItems = Integer.parseInt(getNumberOfproducts().split(" ")[0]);
		logs.debug("removing all items from cart");
		for (int itemIndex = numberOfItems-1 ; itemIndex >= 0; itemIndex--)
			removeItemFromCart(0); //keep always remove the first item
	}
	else
	{
		logs.debug("No items to be removed");
	}
	
	
	getCurrentFunctionName(false);
}

public static void removeItemFromCart(int itemIndex) throws Exception
{
	getCurrentFunctionName(true);
	clickOnactionMenue(itemIndex);
	clickOnRemove(itemIndex);
	getCartMsg();
	getCurrentFunctionName(false);
}

private static void clickOnRemove(int itemIndex) throws Exception {
	getCurrentFunctionName(true);
	subStrArr.add(cartSelectors.removeItem + itemIndex);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	getCurrentFunctionName(false);
}

private static void clickOnactionMenue(int itemIndex) throws Exception {
	getCurrentFunctionName(true);
	subStrArr.add(cartSelectors.actionMenue + itemIndex);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	getCurrentFunctionName(false);	
}
    
}
