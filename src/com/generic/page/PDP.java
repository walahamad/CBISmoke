package com.generic.page;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.generic.selector.PDPSelectors;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class PDP extends SelTestCase {
    private static final String DOC_URL = getCONFIG().getProperty("testSiteName");
    static List<String> subStrArr = new ArrayList<String>();
	static List<String> valuesArr = new ArrayList<String>();
	
   public static void addProductsToCart(String color, String size, String qty) throws InterruptedException, IOException
   {
	   if(!color.equals(""))
		   selectcolor(color);
	   
	   if (!size.equals(""))
		   selectsize(size);
	   
	   defineQty(qty);
	   clickAddToCartBtn();
	   clickcheckoutBtnCartPopup();
	   
   }

private static String getPrice() throws InterruptedException, IOException {
	subStrArr.add(PDPSelectors.price);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	return SelectorUtil.textValue;
}

private static void clickcheckoutBtnCartPopup() throws InterruptedException, IOException {
	subStrArr.add(PDPSelectors.cart_popup);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	
}

private static void clickAddToCartBtn() throws InterruptedException, IOException {
	subStrArr.add(PDPSelectors.addToCartBtn);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
}

private static void defineQty(String qty) throws InterruptedException, IOException {
	subStrArr.add(PDPSelectors.qty);
	valuesArr.add(qty);
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	
}

private static void selectsize(String size) throws InterruptedException, IOException {
	subStrArr.add(PDPSelectors.size);
	valuesArr.add(size);
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
	
}

private static void selectcolor(String color) throws InterruptedException, IOException {
	subStrArr.add(color);
	valuesArr.add("");
	SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);
}
   
    
    
}
