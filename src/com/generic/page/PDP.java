package com.generic.page;

import java.util.ArrayList;
import java.util.List;

import com.generic.selector.PDPSelectors;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class PDP extends SelTestCase {
	
	public static class keys
	{
		public static final String url = "url";
		public static final String color = "color";
		public static final String size = "size";
		public static final String qty = "qty";
		
	}
	public static void addProductsToCart(String url, String color, String size, String qty) throws Exception {
		getDriver().get(url);
		getCurrentFunctionName(true);
		if (!"".equals(color))
			selectcolor(color);

		if (!"".equals(size))
			selectsize(size);

		defineQty(qty);
		clickAddToCartBtn();
		clickcheckoutBtnCartPopup();
		getCurrentFunctionName(false);
	}

	public static String getPrice() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.price);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
	}

	private static void clickcheckoutBtnCartPopup() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.cart_popup);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	private static void clickAddToCartBtn() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.addToCartBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
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

	private static void selectsize(String size) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.size);
		valuesArr.add(size);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	private static void selectcolor(String color) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(color);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

}
