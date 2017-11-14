package com.generic.page;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.generic.selector.PDPSelectors;
import com.generic.selector.cartSelectors;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class cart extends SelTestCase {
    private static final String DOC_URL = getCONFIG().getProperty("testSiteName");
    static List<String> subStrArr = new ArrayList<String>();
	static List<String> valuesArr = new ArrayList<String>();


public static void clickCheckout() throws InterruptedException, IOException {
	getCurrentFunctionName(true);
	logs.debug("clicking on checkout btn from cart");
	subStrArr.add(cartSelectors.checkoutBtn);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	getCurrentFunctionName(false);
	
}

public static void clickContinueShoping() throws InterruptedException, IOException {
	getCurrentFunctionName(true);
	logs.debug("clicking on continue shopping from cart");
	subStrArr.add(cartSelectors.continueShopping);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	getCurrentFunctionName(false);
	
}

public static String getNumberOfproducts() throws InterruptedException, IOException {
	getCurrentFunctionName(true);
	subStrArr.add(cartSelectors.numberOfProducts);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	logs.debug("number of products: "+SelectorUtil.textValue);
	getCurrentFunctionName(false);
	return SelectorUtil.textValue;
}

public static String ordarTotal() throws InterruptedException, IOException {
	getCurrentFunctionName(true);
	subStrArr.add(cartSelectors.numberOfProducts);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	logs.debug("Order total: "+SelectorUtil.textValue);
	getCurrentFunctionName(false);
	return SelectorUtil.textValue;
}

public static String ordarSubTotal() throws InterruptedException, IOException {
	getCurrentFunctionName(true);
	subStrArr.add(cartSelectors.OrderSubTotal);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	logs.debug("Order subtotal: "+SelectorUtil.textValue);
	getCurrentFunctionName(false);
	return SelectorUtil.textValue;
	
}

public static void applyCoupon(String coupon) throws InterruptedException, IOException {
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

private static void clickApplycoupon() throws InterruptedException, IOException {
	getCurrentFunctionName(true);
	subStrArr.add(cartSelectors.applyCouponButton);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	getCurrentFunctionName(false);
}

private static void writeCoupon(String coupon) throws InterruptedException, IOException {
	getCurrentFunctionName(true);
	subStrArr.add(cartSelectors.couponField);
	valuesArr.add(coupon);
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	getCurrentFunctionName(false);
}
   
    
    
}
