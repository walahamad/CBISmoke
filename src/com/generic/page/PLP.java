package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.generic.selector.PLPSelectors;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class PLP extends SelTestCase {

	public static class keys {
		public static final String caseId = "caseId";
	}

	//done-ocm
	public static void clickOnSorting() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			if (!getBrowserName().contains("mobile"))
				subStrArr.add(PLPSelectors.sortingDropDown);
			else
				subStrArr.add(PLPSelectors.sortingDropDownMobile);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	//done-ocm
	public static void clickOnPriceHighToLow() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			logs.debug("High to low sorting");
			if (!getBrowserName().contains("mobile"))
				subStrArr.add(PLPSelectors.sortPHTL);
			else
				subStrArr.add(PLPSelectors.sortPHTLMobile);
				if (getBrowserName().contains("mobile"))
					SelectorUtil.getNthElement(subStrArr, 1).click();
				else
					SelectorUtil.getNthElement(subStrArr, 2).click();
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	//done-ocm
	public static void clickOnPriceLowToHigh() throws Exception {
		try {

			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			logs.debug("low to High sorting");
			Thread.sleep(3000);
			if (!getBrowserName().contains("mobile"))
				subStrArr.add(PLPSelectors.sortPLTH);
			else
			{
				subStrArr.add(PLPSelectors.sortPLTHMobile);
				SelectorUtil.getNthElement(subStrArr, 0).click();
				SelectorUtil.getNthElement(subStrArr, 2).click();
			}
			Thread.sleep(3000);
			SelectorUtil.getNthElement(subStrArr, 1).click();
			Thread.sleep(3000);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	//done-ocm
	public static boolean validateSorting(String sortType) throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			logs.debug("verifying the PLP pricing, sort type is: " + sortType);
			subStrArr.add(PLPSelectors.productsPrices);
			String Price1 = SelectorUtil.getNthElement(subStrArr, 0).getText();
			String Price2 = SelectorUtil.getNthElement(subStrArr, 1).getText();
			String Price3 = SelectorUtil.getNthElement(subStrArr, 2).getText();
			logs.debug("Prices for first 3 products is " + Price1 + ", " + Price2 + ", " + Price3);

			double valuePrice1 = Double.parseDouble(Price1.trim().replace("$", "").split("-")[0]);
			double valuePrice2 = Double.parseDouble(Price2.trim().replace("$", "").split("-")[0]);
			double valuePrice3 = Double.parseDouble(Price3.trim().replace("$", "").split("-")[0]);

			getCurrentFunctionName(false);
			if (sortType.contains("LTH"))
				return ((valuePrice1 <= valuePrice2) && (valuePrice2 <= valuePrice3));
			else
				return ((valuePrice1 >= valuePrice2) && (valuePrice2 >= valuePrice3));

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	//done-ocm
	public static boolean sortAndValidate(String sortType) throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug("Sorting: " + sortType);
			clickOnSorting();

			if (sortType.contains("HTL"))
				clickOnPriceHighToLow();
			if (sortType.contains("LTH"))
				clickOnPriceLowToHigh();

			Thread.sleep(7000);

			boolean validation = validateSorting(sortType);

			getCurrentFunctionName(false);
			return validation;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	//done-ocm
	public static int countProductsInPage() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			logs.debug("counting the products ");
			subStrArr.add(PLPSelectors.productsPrices);
			int itemsNumber = SelectorUtil.getAllElements(subStrArr).size();
			logs.debug("product count is :" + itemsNumber);
			getCurrentFunctionName(false);
			return itemsNumber;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	//done-ocm
	public static String getNumberOfproductsInSite() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug("getting number of items per PLP ");
			subStrArr.add(PLPSelectors.productNmber);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			String count = SelectorUtil.textValue.get().split(" ")[4];
			logs.debug("number of items: " + count);
			getCurrentFunctionName(false);
			return count;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	//done-ocm
	public static void searchProduct(String keyWord) throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PLPSelectors.SearchBox);
			valuesArr.add(keyWord+",pressEnter");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
		
	}

	//done-ocm
	public static void pickRandomPDP() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PLPSelectors.randomProduct);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
		
	}
	
	//TODO: clean any function not CBI related after PLP done 

	// CBI
	public static boolean searchAndVerifyResults(String SearchTerm, boolean recommendedOption) throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean result;
			clickSearchicon();
			typeSearch(SearchTerm);
			if (recommendedOption) {
				String productName = pickRecommendedOption();
				result = verifyPickedProduct(productName);
			} else {
				clickSearch(SearchTerm);
				result = verifySeachResultPage();
			}
			getCurrentFunctionName(false);
			return result;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	private static boolean verifySeachResultPage() {
		try {
			getCurrentFunctionName(true);
			boolean result = getDriver().getCurrentUrl().contains("searchTerm");
			getCurrentFunctionName(false);
			return result;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	private static boolean verifyPickedProduct(String productName) throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean result; 
			String productTitle = PDP.getTitle();
			result = productTitle.contains(productName);
			getCurrentFunctionName(false);
			return result;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	private static void clickSearch(String searchTerm) throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.searchBox.get() , searchTerm+",pressEnter");
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	@SuppressWarnings("rawtypes")
	public static String pickRecommendedOption() throws Exception {
		try {
			getCurrentFunctionName(true);
			
			String SelectorSS = PLPSelectors.recommendedOption.get();
			WebElement recommendedProduct = SelectorUtil.getelement(SelectorSS);
			
			String itemTitle = recommendedProduct.getText();
			logs.debug("Picked item: "+ itemTitle); 
			recommendedProduct.click();
			
			getCurrentFunctionName(false);
			return itemTitle;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
		
	}

	public static void typeSearch(String searchTerm) throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.searchBox.get(), searchTerm);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

		
	}

	//CBI
	public static void clickSearchicon() throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.SearchIcon.get());
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}
	
	public static String selectFirstProduct() throws Exception{
		String selectedProductName ="";
	try {
		getCurrentFunctionName(true);
		List<WebElement> productList = new ArrayList<WebElement>();
		List<WebElement> productNameList = new ArrayList<WebElement>();
		SelectorUtil.isDisplayed(PLPSelectors.productContainer.get());		
		productList = SelectorUtil.getAllElements(PLPSelectors.product.get());
		productNameList = SelectorUtil.getAllElements(PLPSelectors.productName.get());
		WebElement element = productList.get(0);
		selectedProductName = productNameList.get(0).getText();
		logs.debug("Clicking on first product");
		((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()", element);
		getCurrentFunctionName(false);	
	}catch  (NoSuchElementException e) {
		logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
		}.getClass().getEnclosingMethod().getName()));
		throw e;
	}	
	return selectedProductName;
	}
	
}
