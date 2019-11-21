package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import com.generic.selector.PDPSelectors;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class PDP extends SelTestCase {

	public static class keys {


	}
	public static void selectColor() throws Exception {
		try {
			getCurrentFunctionName(true);
			Thread.sleep(1000);
			List<String> subStrArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.allColors.get());
			if(SelectorUtil.isDisplayed(subStrArr))
			{
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr);	
			}
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
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.allSizes.get());
			valuesArr.add("FFF1");
			if(SelectorUtil.isDisplayed(subStrArr))
			{
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);	
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}
	
	public static void selectFabric() throws Exception {
		try {
			getCurrentFunctionName(true);
			Thread.sleep(1000);
			List<String> subStrArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.allFabrics.get());
			if(SelectorUtil.isDisplayed(subStrArr))
			{
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr);	
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}
	
	public static void selectShipLeadTime() throws Exception {
		try {
			getCurrentFunctionName(true);
			Thread.sleep(1000);
			List<String> subStrArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.allShipLeadTime.get());
			List<String> valuesArr = new ArrayList<String>();

			if(SelectorUtil.isDisplayed(subStrArr))
			{
				int index = SelectorUtil.getAllElements(subStrArr).size() - 1;
				logs.debug("Selected Index for option is: " + index);
				valuesArr.add("index,"+index);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr,valuesArr);	
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}
	
	public static void addProductsToCart() throws Exception {
		try {
			getCurrentFunctionName(true);
			selectFabric();
			Thread.sleep(3000);
			selectShipLeadTime();
			Thread.sleep(2000);
			selectColor();
			Thread.sleep(1000);
			selectSize();
			Thread.sleep(1000);
			clickAddToCartBtn();
			Thread.sleep(1000);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	public static void clickAddToCartBtn() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			logs.debug(" ");
			subStrArr.add(PDPSelectors.addToCartBtn.get());
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	public static void clickAddToCartCloseBtn() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			logs.debug(" ");
			subStrArr.add(PDPSelectors.addToCartCloseBtn.get());
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}


}