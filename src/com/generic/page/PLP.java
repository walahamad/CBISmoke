package com.generic.page;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.generic.selector.PLPSelectors;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class PLP extends SelTestCase {

    
    public static class keys {
		public static final String caseId = "caseId";
	}

    //done
    public static void clickOnSorting() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug("");
		subStrArr.add(PLPSelectors.sortingDropDown);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
    //done
    public static void clickOnPriceHighToLow() throws Exception {
    	getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug("");
		subStrArr.add(PLPSelectors.sortPHTL);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
    //done
    public static void clickOnPriceLowToHigh() throws Exception {
    	getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug("");
		subStrArr.add(PLPSelectors.sortPLTH);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
    //done
	public static boolean validateSorting(String sortType) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		logs.debug("verifiing the PLP pricing");
		subStrArr.add(PLPSelectors.productsPrices);
		String Price1 = SelectorUtil.getNthElement(subStrArr, 0).getText();
		String Price2 = SelectorUtil.getNthElement(subStrArr, 1).getText();
		String Price3 =  SelectorUtil.getNthElement(subStrArr, 2).getText();
		logs.debug("Prices for first 3 products is " + Price1 + ", "+ Price2 + ", "+ Price3 );
		
		double valuePrice1 = Double.parseDouble(Price1.trim().replace("$", ""));
		double valuePrice2 = Double.parseDouble(Price2.trim().replace("$", ""));
		double valuePrice3 = Double.parseDouble(Price3.trim().replace("$", ""));
		
		getCurrentFunctionName(false);
		if (sortType.equals("LTH"))
				return ((valuePrice1 <= valuePrice2) && (valuePrice2 <= valuePrice3));
			else
				return ((valuePrice1 >= valuePrice2) && (valuePrice2 >= valuePrice3));
	
		}
 
    //done
	public static boolean sortAndValidate(String sortType) throws Exception {
		getCurrentFunctionName(true);
		logs.debug("Sorting: " + sortType);
		clickOnSorting();
		
		if (sortType.contains("HTL"))
			clickOnPriceHighToLow();
		if (sortType.contains("LTH"))
			clickOnPriceLowToHigh();
		
		boolean validation = validateSorting(sortType);
		
		getCurrentFunctionName(false);
		return validation;
	}
	
    
    //done
    public static int countProductsInPage() throws Exception {
    	getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		logs.debug("counting the products ");
		subStrArr.add(PLPSelectors.productsPrices);
		int itemsNumber = SelectorUtil.getAllElements(subStrArr).size();
		logs.debug("product count is :" +itemsNumber);
		getCurrentFunctionName(false);
		return itemsNumber;
	}
    
    //done
    public static String getNumberOfproductsInSite() throws Exception {
    	getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug("getting number of items");
		subStrArr.add(PLPSelectors.productNmber);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String count = SelectorUtil.textValue.get();
		logs.debug("umber of items: " +count );
		getCurrentFunctionName(false);
		return count;
	}
    
}
