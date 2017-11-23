package com.generic.tests.checkout;

import static org.testng.Assert.assertEquals;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.LinkedHashMap;

import com.generic.page.PDP;
import com.generic.page.Cart;
import com.generic.page.CheckOut;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.TestUtilities;
import com.generic.util.RandomUtilities;

@RunWith(Parameterized.class)
public class Base_checkout extends SelTestCase {

	private static int testCaseID;
	public static final LinkedHashMap<String, Object> addresses = Common.readAddresses();
	public static final LinkedHashMap<String, Object> invintory = Common.readLocalInventory();
	public static final LinkedHashMap<String, Object> paymentCards = Common.readPaymentcards();

	// user types
	public static final String guestUser = "guest";
	public static final String freshUser = "fresh";
	public static final String loggedInUser = "loggedin";
	
	//used sheet in test
	public static final String testDataSheet = SheetVariables.checkoutSheet;
	
	String runTest;
	String desc;
	String proprties;
	String[] products;
	String shippingMethod;
	String payment;
	String shippingAddress;
	String billingAddress;
	String coupon;
	String email;
	String orderId;
	String orderTotal;
	String orderSubtotal;
	String orderTax;
	String orderShipping;

	@BeforeClass
	public static void initialSetUp() throws Exception {
		tempTCID = SheetVariables.checkoutTestCaseId + "_" + testCaseID;
		//TODO: clear results in sheet 
		caseIndex = 2;
	}
	
	public Base_checkout(String runTest, String desc, String proprties, String products, String shippingMethod,
			String payment, String shippingAddress, String billingAddress, String coupon, String email, String orderId,
			String orderTotal, String orderSubtotal, String orderTax, String orderShipping) {

		// moving variables from parameterize module to class variables
		this.runTest = runTest;
		this.desc = desc;
		this.proprties = proprties;

		// get all products need to be added in case
		this.products = products.split("\n");

		this.shippingMethod = shippingMethod;
		this.payment = payment;
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.coupon = coupon;
		this.email = email;
		this.orderId = orderId;
		this.orderTotal = orderTotal;
		this.orderSubtotal = orderSubtotal;
		this.orderTax = orderTax;
		this.orderShipping = orderShipping;
		
		logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, Arrays.asList(paymentCards)));
	}

	@Parameters(name = "{index}_:{1}")
	public static Collection<Object[]> loadTestData() throws Exception {
		Object[][] data = TestUtilities.getData(testDataSheet);
		return Arrays.asList(data);
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test
	public void Checkout() throws Exception {
		try {
			// TODO: write all values to sheet
			if ("".equals(runTest)) {
				logs.debug(LoggingMsg.IGNORE_CURRENT_CASE);
				Common.testIgnored();
				return;
			}

			if (proprties.contains(loggedInUser)) {
				// TODO: pull user mail from sheet
				SignIn.logIn("ibatta@dbi.com", "1234567");
				 //getDatatable().setCellData(testDataSheet, "EmailUsed", row, email);
			}
			if (proprties.contains(freshUser)) {
				// TODO: add flow for register
				String mail = RandomUtilities.getRandomEmail().toLowerCase();

				// TODO: write random mail from previous step to sheet
				// TODO: register with information
				// TODO: create sheet for users or pull then from config file
				// TODO: write function to get user information from sheet
			}

			// TODO: move all keys to one file make sure to move them also from the function
			// it self
			for (String product : products) {
				logs.debug(MessageFormat.format(LoggingMsg.ADDING_PRODUCT, product));
				LinkedHashMap<String, Object> productDetails = (LinkedHashMap<String, Object>) invintory.get(product);
				PDP.addProductsToCart((String) productDetails.get(PDP.keys.url),
						(String) productDetails.get(PDP.keys.color), (String) productDetails.get(PDP.keys.size),
						(String) productDetails.get(PDP.keys.qty));

			}

			// flow to support coupon validation
			if (!"".equals(coupon)) {
				Cart.applyCoupon(coupon);
				if (coupon.contains(Cart.keys.invalidCoupon)) {
					Cart.validateinvaldcoupon();
				}
			}
			Cart.getNumberOfproducts();
			orderSubtotal = Cart.getOrderSubTotal();
			orderTax = Cart.getOrderTax();
			
			Cart.clickCheckout();

			// signIn.logIn("ibatta@dbi.com", "1234567");

			if (proprties.contains(guestUser)) {
				// TODO: add flow for guest
				// TODO: in checkout file add input guest mail function
			}
			
			//Validate the order sub total in shipping address form section
			assertEquals(CheckOut.shippingAddress.getOrdersubTotal(), orderSubtotal );
			
			// checkout- shipping address
			if (proprties.contains(CheckOut.shippingAddress.keys.isSavedShipping) && !proprties.contains(freshUser)
					&& !proprties.contains(guestUser)) {
				CheckOut.shippingAddress.fillAndClickNext(true);
			} else {
				LinkedHashMap<String, Object> addressDetails = (LinkedHashMap<String, Object>) addresses
						.get(shippingAddress);

				CheckOut.shippingAddress.fillAndClickNext(
						(String) addressDetails.get(CheckOut.shippingAddress.keys.countery),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.title),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.firstName),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.lastName),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.adddressLine),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.city),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.postal),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.phone), true);
			}
			
			//Validate the order sub total in shipping method section
			assertEquals(CheckOut.shippingMethod.getOrderSubTotal(), orderSubtotal );
			
			//Shipping method 
			CheckOut.shippingMethod.fillAndclickNext(shippingMethod);

			//Validate the order sub total in billing form section
			assertEquals(CheckOut.paymentInnformation.getOrderSubTotal(), orderSubtotal );
			
			// checkout- payment
			if (proprties.contains(CheckOut.paymentInnformation.keys.isSavedPayement) && !proprties.contains(freshUser)
					&& !proprties.contains(guestUser)) {
				CheckOut.paymentInnformation.fillAndclickNext(true);
			} else {
				
				boolean saveBilling = true;
				LinkedHashMap<String, Object> paymentDetails = (LinkedHashMap<String, Object>) paymentCards
						.get(payment);
				LinkedHashMap<String, Object> billAddressDetails = (LinkedHashMap<String, Object>) addresses
						.get(billingAddress);
				
				CheckOut.paymentInnformation.fillAndclickNext(payment,
						(String) paymentDetails.get(CheckOut.paymentInnformation.keys.name),
						(String) paymentDetails.get(CheckOut.paymentInnformation.keys.number),
						(String) paymentDetails.get(CheckOut.paymentInnformation.keys.expireMonth),
						(String) paymentDetails.get(CheckOut.paymentInnformation.keys.expireYear),
						(String) paymentDetails.get(CheckOut.paymentInnformation.keys.CVCC), saveBilling, 
						billingAddress.equalsIgnoreCase(shippingAddress),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.countery),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.title),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.firstName),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.lastName),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.adddressLine),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.city),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.postal),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.phone));
			}
			
			//Validate the order subtotal in order review section
			assertEquals(CheckOut.reviewInformation.getSubtotal(), orderSubtotal );
			
			CheckOut.reviewInformation.acceptTerms(true);
			CheckOut.reviewInformation.placeOrder();

			
			//Validate the order sub total in order review section
			assertEquals(CheckOut.orderConfirmation.getSubTotal(), orderSubtotal );
			
			orderTotal = CheckOut.orderConfirmation.getOrderTotal();
			orderShipping = CheckOut.orderConfirmation.getShippingCost();
			orderId = CheckOut.orderConfirmation.getOrderId();
			
			//TODO: compare addresses 
			CheckOut.orderConfirmation.getbillingAddrerss();
			CheckOut.orderConfirmation.getshippingAddrerss();
			
			//TODO: write results to sheet 
			
			
			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseId();
			Common.testFail(t, temp);
			Common.takeScreenShot();
			Assert.assertTrue(t.getMessage(), false);
		} // catch
	}// test
}// class
