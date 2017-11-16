package com.generic.tests.checkout;

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
import com.generic.page.cart;
import com.generic.page.checkOut;
import com.generic.page.signIn;
import com.generic.setup.Common;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.TestUtilities;
import com.generic.util.RandomUtilities;

@RunWith(Parameterized.class)
public class base_checkout extends SelTestCase {

	private static int testCaseID;
	public static final LinkedHashMap<String, Object> addresses = Common.readAddresses();
	public static final LinkedHashMap<String, Object> invintory = Common.readLocalInventory();
	public static final LinkedHashMap<String, Object> paymentCards = Common.readPaymentcards();
	
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
		caseIndex = 2;
		TestUtilities.initialize();
	}

	public base_checkout(String runTest, String desc, String proprties, String products,
			String shippingMethod, String payment, String shippingAddress, String billingAddress,
			String coupon, String email, String orderId, String orderTotal,
			String orderSubtotal, String orderTax, String orderShipping ) {
		
		//moving variables from parameterize module to class variables 
		this.runTest = runTest;
		this.desc = desc;
		this.proprties = proprties;

		//get all products need to be added in case  
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
	}

	@Parameters(name = "{index}_:{1}")
	public static Collection<Object[]> loadTestData() throws Exception {
		Object[][] data = TestUtilities.getData(SheetVariables.checkoutSheet);
		return Arrays.asList(data);
	}
	
	

	@Test
	public void Checkout() throws Exception {
		try 
		{
			//TODO: write all values to sheet
			if (runTest.equals(""))
			{
				logs.debug("Ignoring the current case ");
				Common.testIgnored();
				return;
			}
			
			if (proprties.contains("loggedin"))
			{
				//TODO: pull user mail from sheet 
				signIn.logIn("ibatta@dbi.com", "1234567");
			}
			if(proprties.contains("fresh"))
			{
				//TODO: add flow for register 
				String mail = RandomUtilities.getRandomEmail().toLowerCase();
				
				//TODO: write random mail from previous step to sheet
				//TODO: register with information
				//TODO: create sheet for users or pull then from config file
				//TODO: write function to get user information from sheet
			}
			
			//TODO: move all keys to one file make sure to move them also from the function it self
			for (String product :products )
			{
				logs.debug("Adding product " + product);
				LinkedHashMap<String, Object> productDetails= (LinkedHashMap<String, Object>) invintory.get(product);
				PDP.addProductsToCart((String) productDetails.get("url"),(String) productDetails.get("color"),
						(String) productDetails.get("size"), (String) productDetails.get("qty"));
				
			}
			
			
			if (!coupon.equals(""))
			{
				cart.applyCoupon(coupon);
				if (coupon.contains("invalid"))
				{
					//TODO: add logic to validate the error msg 
				}
			}
			cart.getNumberOfproducts();
			cart.ordarTotal();
			cart.ordarSubTotal();
			cart.clickCheckout();
			
			//signIn.logIn("ibatta@dbi.com", "1234567");
			
			if(proprties.contains("guest"))
			{
				//TODO: add flow for guest 
			}
			
			//checkout- shipping address 
			if (proprties.contains("saved-shipping") &&
					!proprties.contains("fersh") &&
					!proprties.contains("guest"))
			{
				checkOut.shippingAddress.fillAndClickNext(true);
			}
			else
			{
				checkOut.shippingAddress.fillAndClickNext("United Kingdom", "Mr.","Accept", "Tester",
						"49 Featherstone Street", "LONDON", "EC1Y 8SY", "545452154", true);
			}
			
			checkOut.shippingMethod.fillAndclickNext("PREMIUM DELIVERY");
			
			//checkout- payment
			if (proprties.contains("saved-payment")&&
					!proprties.contains("fersh") &&
					!proprties.contains("guest"))
			{
				checkOut.paymentInnformation.fillAndclickNext(true);  
			}
			else
			{
				checkOut.paymentInnformation.fillAndclickNext("VISA", "Accept", "4111111111111111", "4", "2020", "333",true, true);
			}
			
			checkOut.reviewInformation.getSubtotal();
			checkOut.reviewInformation.shippingCost();
			checkOut.reviewInformation.gettotal(); 
			checkOut.reviewInformation.acceptTerms(true);
			checkOut.reviewInformation.placeOrder();
			
			checkOut.orderConfirmation.getOrderid();
			checkOut.orderConfirmation.getOrdertotal();
			checkOut.orderConfirmation.getSubtotal();
			checkOut.orderConfirmation.getShippingcost();
			checkOut.orderConfirmation.getbillingAddrerss();
			checkOut.orderConfirmation.getshippingAddrerss();
			
			Common.testPass();
		} 
		catch (Throwable t)
		{
			setTestCaseDescription(getTestCaseDescription());
			logs.debug(t.getMessage());
			t.printStackTrace();
			String temp = getTestCaseId();
			Common.testFail(t, temp);
			Common.takeScreenShot();
			Assert.assertTrue(t.getMessage(), false);
		}//catch
	}//test
}//class
