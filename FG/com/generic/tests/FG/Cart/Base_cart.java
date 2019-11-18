/**
 * this generic test for cart regression that will pull tests from cartRegression tab from
 * datasheet.xlsx. 
*/
package com.generic.tests.FG.Cart;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.generic.page.Cart;
import com.generic.page.PDP;
import com.generic.page.Registration;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.PagesURLs;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.dataProviderUtils;

public class Base_cart extends SelTestCase {

	// user types
	public static final String guestUser = "guest";
	public static final String loggedInUser = "loggedin";

	// used sheet in test
	public static final String testDataSheet = SheetVariables.cartSheet;

	private static XmlTest testObject;
	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();
	LinkedHashMap<String, String> productDetails;

	@BeforeClass
	public static void initialSetUp(XmlTest test) throws Exception {
		testCaseRepotId = SheetVariables.cartCaseId;
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
	}

	@DataProvider(name = "Carts", parallel = true)
	public static Object[][] loadTestData() throws Exception {
		// concurrency maintenance on sheet reading
		getBrowserWait(testObject.getParameter("browserName"));
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked")
	@Test(dataProvider = "Carts")
	public void CartBaseTest(String caseId, String runTest, String desc, String proprties, String products,
			String email, String promotion, String ItemSubtotal, String Discounts, String OrderSubTotal,
			String ValidationMSG) throws Exception {
		// Important to add this for logging/reporting
		Testlogs.set(new SASLogger("cart_" + getBrowserName()));
		setTestCaseReportName("cart Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.CARTDESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc, proprties.replace("\n", "<br>- "), promotion, OrderSubTotal));
		
		String CaseMail = "";
		LinkedHashMap<String, Object> userdetails = null; 
		if (!email.equals(""))
		{
			userdetails = (LinkedHashMap<String, Object>) users.get(email);
			CaseMail = (String) userdetails.get(Registration.keys.email);
			Testlogs.get().debug("Mail will be used is: " + CaseMail);
		}
		
		try {

			if (proprties.contains("Loggedin"))
			{
				for (String product : products.split("\n"))
					prepareCartLoggedInUser(userdetails, product);
			}
			else
				for (String product : products.split("\n"))
					prepareCartNotLoggedInUser(product);
			
			String cartUrl = Cart.getCartUrl();
			getDriver().get(cartUrl);
		
			//TODO: Cart.updateQuantityValue(browser, lineOrder, qty);

			//TODO:// flow to support coupon validation
			if (!"".equals(promotion)) {
				Cart.applyPromotion(promotion);
				ReportUtil.takeScreenShot(getDriver(), testDataSheet + "_" + caseId);
				String validationMessage = Cart.validateCoupon();
				if (!ValidationMSG.equals(""))
					sassert().assertTrue(ValidationMSG.contains(validationMessage),
							"<font color=#f442cb>coupon messgae is not same:  " + ValidationMSG + "</font>");
				ReportUtil.takeScreenShot(getDriver(), testDataSheet + "_" + caseId);
			}
			
			if (proprties.contains("Verify items")) {
			String cartItems  = Cart.getNumberOfproducts();
			sassert().assertEquals(cartItems, products.split("\n").length+"",
					"Number of items in cart is not correct where expected 1 but found " + cartItems);//static since we are dealing with one product
			}

			if (proprties.contains("Verify unit Price")) {
				String ProductUnitPrice = Cart.getProductUnitPrice();
				String ErrorMsg = "<font color=#f442cb>expected unit price is: " + productDetails.get(PDP.keys.price)
						+ "<br>actual unit price: " + ProductUnitPrice + " </font>";
				sassert().assertTrue(ProductUnitPrice.trim().contains((productDetails.get(PDP.keys.price)).trim()), ErrorMsg);
			}//verify unit price 
			
			if (proprties.contains("Verify items subtotal")) {
				//the case that to get unit price and multiply it by the qty from product details and,
				//then compare it with product subtotal and with order subtotal since we have just one product
				
				double calculatedProductSubtotal = Double.parseDouble( productDetails.get(PDP.keys.qty))
						* Double.parseDouble(Cart.getProductUnitPrice().replace("$", "").trim());
				
				double siteProductSubtotal = Double.parseDouble(Cart.getProductItemSubtotal().replace("$", "").trim());
				double siteOrdersubtotal  = Double.parseDouble(Cart.getItemSubTotal().replace("$", "").trim());
				double SheetOrderSubtotal = Double.parseDouble(ItemSubtotal.replace("$", "").trim());
				
				String subtotalMSG = "<font color=#f442cb>Subtotal from sheet: " + SheetOrderSubtotal +
						"<br>calculated subtotal: "+ calculatedProductSubtotal+
						"<br>site product subtotal: " + siteProductSubtotal+
						"<br>site order subtotal: "+ siteOrdersubtotal+ "</font>" ; 
				
				logs.debug(subtotalMSG);
				sassert().assertTrue(calculatedProductSubtotal == SheetOrderSubtotal ||
						siteOrdersubtotal ==SheetOrderSubtotal ||
						siteProductSubtotal == SheetOrderSubtotal, "FAILED: the subtotals should be matched: <br>"+subtotalMSG);
			}//verify subtotal

			if (proprties.contains("Verify discount")) {
				double siteOrderDiscount = Double.parseDouble(Cart.getOrderDiscount().replace("$", "").replace("-", "").trim());
				double SheetOrderDiscount = Double.parseDouble(Discounts.replace("$", "").replace("-", "").trim());

				String discountMsg = "<font color=#f442cb>siteOrderDiscount: " + siteOrderDiscount
						+ "<br>SheetOrderDiscount: " + SheetOrderDiscount + "</font>";

				logs.debug(discountMsg);
				sassert().assertTrue(siteOrderDiscount == SheetOrderDiscount , "FAILED: the discounts should be matched: <br>"+discountMsg);
			}//verify discount 
			
//			//TODO: setup promo
//			if (proprties.contains("Verify Promotion")) {
//				double sitePromotionDiscount = Double.parseDouble(Cart.getPromotionalDiscount().replace("$", "").trim());
//				double SheetPromotionDiscount = Double.parseDouble(PromotionalDiscounts.replace("$", "").trim());
//				
//				String promoMessage = "<font color=#f442cb>sitePromotionDiscount: " + sitePromotionDiscount +
//						"<br>SheetPromotionDiscount: "+ SheetPromotionDiscount+"</font>" ; 
//				
//				logs.debug(promoMessage);
//				sassert().assertTrue(sitePromotionDiscount == SheetPromotionDiscount , "FAILED: the discounts should be matched: <br>"+promoMessage);
//			}//verify promotional   
			
			
			if (proprties.contains("Verify order subtotal")) {
				double siteOrderTotal = Double.parseDouble(Cart.getOrderSubTotal().replace("$", "").trim());
				double SheetOrderTotal = Double.parseDouble(OrderSubTotal.replace("$", "").trim());
				
				String orderTotalMsg = "<font color=#f442cb>siteOrderTotal: " + siteOrderTotal +
						"<br>SheetorderTotal: "+ SheetOrderTotal+"</font>" ; 
				
				logs.debug(orderTotalMsg);
				sassert().assertTrue(siteOrderTotal == SheetOrderTotal , "FAILED: the totals should be matched: <br>"+orderTotalMsg);
			}//verify order total
			
			ReportUtil.takeScreenShot(getDriver(), testDataSheet + "_" + caseId);
			
			//TODO: setup promo
			if (proprties.contains("remove Promotion")) {
				Cart.removeCoupon();
			double siteOrdersubtotal  = Double.parseDouble(Cart.getItemSubTotal().replace("$", "").trim());
			logs.debug("Order subtotal: " + siteOrdersubtotal);
			double siteOrderTotal = Double.parseDouble(Cart.getOrderSubTotal().replace("$", "").trim());
			logs.debug("Order total: " + siteOrderTotal);
			sassert().assertTrue(siteOrdersubtotal == siteOrderTotal , "FAILED: voucher code is not removed correctly: <br>");
			ReportUtil.takeScreenShot(getDriver(), testDataSheet + "_" + caseId);
			}//Remove Promotion.
			
//			if (proprties.contains("click checkout")) {
//				Cart.clickCheckout();
//			} else {
//				Cart.clickContinueShoping();
//			}

			ReportUtil.takeScreenShot(getDriver(), testDataSheet + "_" + caseId);
			
			if (proprties.contains("Loggedin")) {
				// navigate back to cart
				getDriver().get(cartUrl);
				Cart.removeAllItemsFromCart();
			}
			
			ReportUtil.takeScreenShot(getDriver(), testDataSheet + "_" + caseId);

			sassert().assertAll();
			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			Testlogs.get().debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseReportName();
			Common.testFail(t, temp);
			ReportUtil.takeScreenShot(getDriver(), testDataSheet + "_" + caseId);
			Assert.assertTrue(false, t.getMessage());
		} // catch

	}// test

	@SuppressWarnings("unchecked")
	public void prepareCartNotLoggedInUser(String product) throws Exception {
		logs.debug(MessageFormat.format(LoggingMsg.ADDING_PRODUCT, product));
		productDetails = (LinkedHashMap<String, String>) invintory.get(product);
		if (product.contains("auto") || product.equals(""))
			PDP.addRandomProductsToCart();
		else 
			PDP.addProductsToCart(productDetails);
		
	}

	public void prepareCartLoggedInUser(LinkedHashMap<String, Object> userdetails, String product) throws Exception {
		logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, userdetails));
		logs.debug((String) userdetails.get(Registration.keys.email));
		logs.debug((String) userdetails.get(Registration.keys.password));
		String caseMail = (String) userdetails.get(Registration.keys.email);
		caseMail = getSubMailAccount(caseMail);
		SignIn.logIn(caseMail,
				(String) userdetails.get(Registration.keys.password));

		prepareCartNotLoggedInUser(product);

	}

}
