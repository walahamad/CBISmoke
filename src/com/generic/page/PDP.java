package com.generic.page;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.generic.selector.PDPSelectors;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class PDP extends SelTestCase {

	public static class keys {
		public static final String id = "id";
		public static final String name = "name";
		public static final String title = "title";
		public static final String url = "url";
		public static final String qty = "qty";
		public static final String overview = "overview";
		public static final String price = "price";
		public static final String features = "features";

	}

	// Done
	public static void addProductsToCartAndClickCheckOut(String url, String qty) throws Exception {
		getCurrentFunctionName(true);
		addProductsToCart(url, qty);
		clickcheckoutBtnCartPopup();
		getCurrentFunctionName(false);
	}

	// Done
	public static void addProductsToCart(String url, String qty) throws Exception {
		getCurrentFunctionName(true);
		getDriver().get(url);
		defineQty(qty);
		clickAddToCartBtn();
		Thread.sleep(1000);
		getCurrentFunctionName(false);
	}

	// Done
	public static String getPrice() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.price);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	// done
	private static void clickcheckoutBtnCartPopup() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.cart_popup);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	// done
	private static void clickAddToCartBtn() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.addToCartBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	// done
	private static void defineQty(String qty) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.qty);
		valuesArr.add(qty);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	// done
	public static String getId() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.id);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	// done
	public static String getTitle() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.title);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	// done
	public static String getOverView() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.information);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

		Pattern p = Pattern.compile("OVERVIEW(.*)FEATURES");
		Matcher m = p.matcher(SelectorUtil.textValue.get().replace("\n", "").replace("\r", "")); // get a matcher object
		String OverView = "";
		if (m.find())
			OverView = m.group(1);
		logs.debug("Over View: " + OverView);

		return OverView;
	}

	// done
	public static String getFeatures() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.information);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

		Pattern p = Pattern.compile("FEATURES(.*)SKU NUMBER");
		Matcher m = p.matcher(SelectorUtil.textValue.get().replace("\n", "").replace("\r", "")); // get a matcher object
		String Features = "";
		if (m.find())
			Features = m.group(1);
		logs.debug("Features: " + Features);

		return Features;
	}

	// done
	public static String getStockAvailability() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.SA);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	// done
	public static boolean checkAddToCartButton() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.addToCartBtn);
		boolean isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		logs.debug("existence check result is " + isDisplayed);
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	//done
	public static void hoverMiniCart() throws Exception {
		getCurrentFunctionName(true);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(SelTestCase.getDriver()).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
	
		WebElement field = getDriver().findElement(By.id(PDPSelectors.minicart));
		
		JavascriptExecutor jse = (JavascriptExecutor) getDriver();
		jse.executeScript("arguments[0].scrollIntoView(false)", field);
		Thread.sleep(200);
		WebElement field2 = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
				return driver.findElement(By.id(PDPSelectors.minicart));
				}
			});
	
			Actions HoverAction = new Actions(getDriver());
			HoverAction.moveToElement(field2).click().build().perform();
		getCurrentFunctionName(false);
	}

	// done
	public static String getProductQtyInMiniCart() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.cartPopupProductQty);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	// done
	public static String getProductUnitPriceInMiniCart() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.miniCartProductUnitPrice);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}
}
