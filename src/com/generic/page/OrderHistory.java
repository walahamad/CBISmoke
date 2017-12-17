package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.generic.selector.OrderHistorySelector;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class OrderHistory extends SelTestCase {
	private static List<String> subStrArr = new ArrayList<String>();
    private static List<String> valuesArr = new ArrayList<String>();
    private static int numberOfOrdersShownInHeader;
    
    public static class keys {
		public static final String caseId = "caseId";
	}
    
    public static void selectSortOptions1ByValue(String sortByTxt) throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.SELECTING_ELEMENT_VALUE,OrderHistorySelector.sortOptions1, sortByTxt));
		subStrArr.add(OrderHistorySelector.sortOptions1);
		valuesArr.add(sortByTxt);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String SelectedOptions2Val = getSortOptions2SelectedValue();
		logs.debug(MessageFormat.format(LoggingMsg.PLP_SELECTED_SORT_VALUES,"sortOptions1", sortByTxt, "sortOptions2", SelectedOptions2Val.trim()));
		getCurrentFunctionName(false);
	}
    
    public static String getSortOptions2SelectedValue() throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,OrderHistorySelector.sortOptions2));
		subStrArr.add(OrderHistorySelector.sortOptions2);
		valuesArr.add("");
		String sortOptions2SelectedValue = "";
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		sortOptions2SelectedValue = SelectorUtil.textValue;
		getCurrentFunctionName(false);
    	return sortOptions2SelectedValue;
    }
    
    public static void selectSortOptions2ByValue(String sortByTxt) throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.SELECTING_ELEMENT_VALUE,OrderHistorySelector.sortOptions2, sortByTxt));
		subStrArr.add(OrderHistorySelector.sortOptions2);
		valuesArr.add(sortByTxt);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String SelectedOptions1Val = getSortOptions2SelectedValue();
		logs.debug(MessageFormat.format(LoggingMsg.PLP_SELECTED_SORT_VALUES,"sortOptions2", sortByTxt, "sortOptions1", SelectedOptions1Val.trim()));
		getCurrentFunctionName(false);
	}
    
    public static String getSortOptions1SelectedValue() throws Exception {
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,OrderHistorySelector.sortOptions1));
		subStrArr.add(OrderHistorySelector.sortOptions1);
		valuesArr.add("");
		String sortOptions2SelectedValue = "";
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		sortOptions2SelectedValue = SelectorUtil.textValue;
		getCurrentFunctionName(false);
    	return sortOptions2SelectedValue;
    }
    
    public static String getNumberOfOrders() throws Exception {
		getCurrentFunctionName(true);
		subStrArr.add(OrderHistorySelector.ordersNumberLabel);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		logs.debug(MessageFormat.format(LoggingMsg.NUMBER_OF_ORDERS, SelectorUtil.textValue));
		getCurrentFunctionName(false);
		String ordersNum = SelectorUtil.textValue.split(" ")[0];
		numberOfOrdersShownInHeader = Integer.parseInt(ordersNum);
		return ordersNum;
	}
    
    public static boolean doesDisplayedOrdersNumTextMatchesOrdersDisplayed() throws Exception {
    	getCurrentFunctionName(true);
    	getNumberOfOrders();
		subStrArr.add(OrderHistorySelector.responsiveTableItem);
		valuesArr.add("noClick");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		logs.debug(MessageFormat.format(LoggingMsg.EXPECTED_TEXT, numberOfOrdersShownInHeader));
		logs.debug(MessageFormat.format(LoggingMsg.ACTUAL_TEXT, SelectorUtil.numberOfFoundElements));
		getCurrentFunctionName(false);
		if (numberOfOrdersShownInHeader == SelectorUtil.numberOfFoundElements) {
    		return true;
    	} else {
    		logs.debug(MessageFormat.format(LoggingMsg.ACTUAL_EXPECTED_ERROR, SelectorUtil.numberOfFoundElements, numberOfOrdersShownInHeader));
    		return false;
    	}
    }
    
    public static String clickNthResponsiveTableItemTableCellAnchor(String nthChild, int index) throws Exception {
		getCurrentFunctionName(true);
		String selText = MessageFormat.format(OrderHistorySelector.nthResponsiveTableItemTableCellAnchor, nthChild);
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, selText));
		subStrArr.add(selText);
		valuesArr.add("index," + index);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
	}
    // nthChild is the index of all elements (here all td at the same index in all tr in the table)
    // index is the index of the exact element extracted from all elements returned
    public static String getNthResponsiveTableItemColumn(String nthChild, int index) throws Exception {
		getCurrentFunctionName(true);
		String selText = MessageFormat.format(OrderHistorySelector.nthResponsiveTableItemColumn, nthChild);
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, selText));
		subStrArr.add(selText);
		valuesArr.add("index," + index);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue;
	}
}
