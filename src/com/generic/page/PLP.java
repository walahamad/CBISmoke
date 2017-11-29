package com.generic.page;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.generic.selector.PLPSelectors;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class PLP extends SelTestCase {
    private static List<String> subStrArr = new ArrayList<String>();
    private static List<String> valuesArr = new ArrayList<String>();
    private static int numberOfProductsShownInHeader;

    public static void selectSortOptions1ByValue(String sortByTxt) throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.SELECTING_ELEMENT_VALUE,PLPSelectors.sortOptions1, sortByTxt));
		subStrArr.add(PLPSelectors.sortOptions1);
		valuesArr.add(sortByTxt);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String SelectedOptions2Val = getSortOptions2SelectedValue();
		logs.debug(MessageFormat.format(LoggingMsg.PLP_SELECTED_SORT_VALUES,"sortOptions1", sortByTxt, "sortOptions2", SelectedOptions2Val.trim()));
		getCurrentFunctionName(false);
	}
    
    public static String getSortOptions2SelectedValue() throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,PLPSelectors.sortOptions2));
		subStrArr.add(PLPSelectors.sortOptions2);
		valuesArr.add("");
		String sortOptions2SelectedValue = "";
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		sortOptions2SelectedValue = SelectorUtil.textValue;
		getCurrentFunctionName(false);
    	return sortOptions2SelectedValue;
    }
    
    public static void selectSortOptions2ByValue(String sortByTxt) throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.SELECTING_ELEMENT_VALUE,PLPSelectors.sortOptions2, sortByTxt));
		subStrArr.add(PLPSelectors.sortOptions2);
		valuesArr.add(sortByTxt);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String SelectedOptions1Val = getSortOptions2SelectedValue();
		logs.debug(MessageFormat.format(LoggingMsg.PLP_SELECTED_SORT_VALUES,"sortOptions2", sortByTxt, "sortOptions1", SelectedOptions1Val.trim()));
		getCurrentFunctionName(false);
	}
    
    public static String getSortOptions1SelectedValue() throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,PLPSelectors.sortOptions1));
		subStrArr.add(PLPSelectors.sortOptions1);
		valuesArr.add("");
		String sortOptions2SelectedValue = "";
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		sortOptions2SelectedValue = SelectorUtil.textValue;
		getCurrentFunctionName(false);
    	return sortOptions2SelectedValue;
    }
    
    public static String getNumberOfproducts() throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(PLPSelectors.numberOfProductsFound);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		logs.debug(MessageFormat.format(LoggingMsg.NUMBER_OF_PRODUCTS, SelectorUtil.textValue));
		getCurrentFunctionName(false);
		String productsNum = SelectorUtil.textValue.split(" ")[0];
		numberOfProductsShownInHeader = Integer.parseInt(productsNum);
		return productsNum;
	}
    
    public static boolean doesDisplayedProductsNumTextMatchesProductsDisplayed () throws Exception {
    	getCurrentFunctionName(true);
		subStrArr.add(PLPSelectors.productItem);
		valuesArr.add("noClick");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		logs.debug(MessageFormat.format(LoggingMsg.EXPECTED_TEXT, numberOfProductsShownInHeader));
		logs.debug(MessageFormat.format(LoggingMsg.ACTUAL_TEXT, SelectorUtil.numberOfFoundElements));
		getCurrentFunctionName(false);
		if (numberOfProductsShownInHeader == SelectorUtil.numberOfFoundElements) {
    		return true;
    	} else {
    		logs.debug(MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, SelectorUtil.numberOfFoundElements, numberOfProductsShownInHeader));
    		return false;
    	}
    }
    
    public static void clickFindStores() throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, PLPSelectors.findStoresNearMeAjax));
		subStrArr.add(PLPSelectors.findStoresNearMeAjax);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
    public static void typeUserLocationStore(String storeName) throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, PLPSelectors.userLocationStore, storeName));
		subStrArr.add(PLPSelectors.userLocationStore);
		valuesArr.add(storeName + ",pressEnter");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
    public static void clickAddToCart(String productCodePost) throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, PLPSelectors.addToCartForm + productCodePost));
		subStrArr.add(PLPSelectors.addToCartForm + productCodePost);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
    public static void clickProductPickupInStoreButton(String productCodePost) throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, PLPSelectors.productPickupInStoreButton + productCodePost));
		subStrArr.add(PLPSelectors.productPickupInStoreButton + productCodePost);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
    public static void clickMoreStores() throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, PLPSelectors.moreStores));
		subStrArr.add(PLPSelectors.moreStores);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
    public static String getPLPProductPrice(String productCodePost) throws Exception {
		getCurrentFunctionName(true);
		String selText = MessageFormat.format(PLPSelectors.plpProductPriceLabel, productCodePost);
		logs.debug(MessageFormat.format(LoggingMsg.GETTING_SEL, selText));
		subStrArr.add(selText);
		valuesArr.add("noClick");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
	}
    
    public static String getPLPProductPriceFromCartBag() throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.GETTING_SEL, PLPSelectors.addToCartItemPrice));
		subStrArr.add(PLPSelectors.addToCartItemPrice);
		valuesArr.add("noClick");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
	}
    
    public static void clickCheckoutBtn() throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, PLPSelectors.addToCartItemCheckoutBtn));
		subStrArr.add(PLPSelectors.addToCartItemCheckoutBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
    public static void clickContinueShoppingBtn() throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, PLPSelectors.addToCartItemContinueShoppingBtn));
		subStrArr.add(PLPSelectors.addToCartItemContinueShoppingBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
    public static void clickAddToCartItemCboxCloseBtn() throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, PLPSelectors.addToCartItemCboxCloseBtn));
		subStrArr.add(PLPSelectors.addToCartItemCboxCloseBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
    public static void ClickleftNavCheckBoxCheckBox(String checkBoxValue) throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, checkBoxValue));
		subStrArr.add(checkBoxValue);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
    public static String getFacetNavTitleStoresCount() throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, PLPSelectors.facetNavTitleStores + ": " + PLPSelectors.facetNavTitleStoresSecondCount));
		subStrArr.add(PLPSelectors.facetNavTitleStores);
		valuesArr.add("child,"+PLPSelectors.facetNavTitleStoresSecondCount);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
	}
}
