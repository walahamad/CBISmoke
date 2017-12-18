package com.generic.tests.checkout;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import com.generic.page.Cart;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.TestUtilities;

public class Main_example_checkout_validation extends SelTestCase {

	private static int testCaseID;

	static List<String> subStrArr = new ArrayList<String>();
	static List<String> valuesArr = new ArrayList<String>();

	@BeforeClass
	public static void initialSetUp() throws Exception {
		testCaseRepotId = SheetVariables.checkoutTestCaseId + "_" + testCaseID;
		caseIndex = 2;
		TestUtilities.configInitialization();
	}

	
	@Test
	public void signIn() throws Exception {
		setStartTime(ReportUtil.now(time_date_format));

		try {
			
			SignIn.logIn("ibatta@dbi.com", "1234567");
			
			//PDP
			//getDriver().get("https://hybrisdemo.conexus.co.uk:9002/yacceleratorstorefront/en/Categories/Bags%2BBoardbags/Bags/Seizure-Satchel/p/300613490");
			//String url = "https://hybrisdemo.conexus.co.uk:9002/yacceleratorstorefront/en/Brands/Toko/Snowboard-Ski-Tool-Toko-Waxremover-HC3-500ml/p/45572";
			//PDP.addProductsToCart("brown", "                                                 SIZE UNI, £34.79  15", "5"); 
			//PDP.addProductsToCart(url,"","", "5");
			//PDP
			
			//CART
			getDriver().get("https://hybrisdemo.conexus.co.uk:9002/yacceleratorstorefront/en/cart");
			//cart.clickContinueShopiing();
			//cart.getNumberOfproducts();
			//cart.ordarTotal();
			//cart.ordarSubTotal();
			//cart.applyCoupon("Coupon name");
			//cart.clickCheckout();
			//cart.updateQuantityValue("0");
			Cart.removeAllItemsFromCart();
			
			//signin
			//getDriver().get("https://hybrisdemo.conexus.co.uk:9002/yacceleratorstorefront/en/login");
			//signIn.logIn("ibatta@dbi.com", "1234567");
			
			
			//checkout pages - shipping Address
			//signIn.logIn("ibatta@dbi.com", "1234567");
			//getDriver().get("https://hybrisdemo.conexus.co.uk:9002/yacceleratorstorefront/en/checkout/");
			//signIn.logIn("ibatta@dbi.com", "1234567");
			
			//new user to enter address 
			//checkOut.shippingAddress.fillAndClickNext("United Kingdom", "Mr.","Accept", "Tester",
				//	"49 Featherstone Street", "LONDON", "EC1Y 8SY", "545452154", true);
			
			//old user to pick from Address book
			//checkOut.shippingAddress.fillAndClickNext(true);
			
			//checkout pages- shipping method
			//checkOut.shippingMethod.fillAndclickNext("PREMIUM DELIVERY");
			
			//checkout pages - payment information
			//new user has no payment methods
			//checkOut.paymentInnformation.fillAndclickNext("VISA", "Accept", "4111111111111111", "4", "2020", "333",true, true);
			
			//old user has a already saved cards
			//checkOut.paymentInnformation.fillAndclickNext(true);
			
			//checkout pages- review information/ place order
//			checkOut.reviewInformation.getSubtotal();
//			checkOut.reviewInformation.shippingCost();
//			checkOut.reviewInformation.gettotal(); 
//			checkOut.reviewInformation.acceptTerms(true);
//			checkOut.reviewInformation.placeOrder();
			
			
			//getDriver().get("http://10.20.20.99:8000/Order%20Confirmation%20_%20Apparel%20Site%20UK.html");
			
			//checkout pages- order confirmation
//			checkOut.orderConfirmation.getOrderid();
//			checkOut.orderConfirmation.getOrdertotal();
//			checkOut.orderConfirmation.getSubtotal();
//			checkOut.orderConfirmation.getShippingcost();
//			checkOut.orderConfirmation.getbillingAddrerss();
//			checkOut.orderConfirmation.getshippingAddrerss();
			
			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseReportName();
			Common.testFail(t, temp);
			Common.takeScreenShot();
			Assert.assertTrue(t.getMessage(), false);
		}

	}

}
