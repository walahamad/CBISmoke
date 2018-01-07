package com.generic.page;

import java.util.ArrayList;
import java.util.List;

import com.generic.selector.StoreLocatorSelectors;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class StoreLocator extends SelTestCase {

	public static class keys {

	}

	public static void searchOnStore(String StoreName) throws Exception
	{
		writeStoreNameAndClickS(StoreName);
	}
	
	private static void writeStoreNameAndClickS(String storeName) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(StoreLocatorSelectors.searchField);
		valuesArr.add(storeName+",pressEnter");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		logs.debug("Searching on Store: "+ storeName);
		getCurrentFunctionName(false);
		
	}
	public static String getErrorMessage() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(StoreLocatorSelectors.alertMsg);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String errorMSG = SelectorUtil.textValue.get();
		logs.debug("Searching on Store: "+ errorMSG);
		getCurrentFunctionName(false);
		return errorMSG;
		
	}
	
	public static String getStroeName(String browser) throws Exception {
		getCurrentFunctionName(true);
		
		if(browser.contains("mobile"))
		{
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(StoreLocatorSelectors.mobileFirstEntery);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		}
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(StoreLocatorSelectors.storeName);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String storeName = SelectorUtil.textValue.get();
		logs.debug("Stroe name: "+ storeName);
		getCurrentFunctionName(false);
		return storeName;
	}

	public static String getStoreHours() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(StoreLocatorSelectors.storeHours);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String storeHours = SelectorUtil.textValue.get();
		logs.debug("Stroe hours: "+ storeHours);
		getCurrentFunctionName(false);
		return storeHours;
	}
	
}
