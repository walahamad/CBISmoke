package com.generic.tests.checkout;

import java.util.Arrays;
import java.util.Collection;

import org.apache.xerces.dom3.as.ASObjectList;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.LinkedHashMap;

import com.generic.setup.Common;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.TestUtilities;
import com.generic.util.ReportUtil;

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
	String giftcard;
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
			String giftcard, String email, String orderId, String orderTotal,
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
		this.giftcard = giftcard;
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
			if (runTest.equals(""))
			{
				logs.debug("Ignoring the current case");
				Common.testIgnored();
				return;
			}
			
			if (proprties.contains("logged in"))
			{
				//TODO: pull user mail from sheet 
				//TODO: sign in 
			}
			if(proprties.contains("fresh"))
			{
				//TODO: add flow for register 
				//TODO: generate random mail 
				//TODO: write random mail from previous step to sheet
				//TODO: register with information
				//TODO: create sheet for users or pull then from config file
				//TODO: write function to get user information from sheet
			}
			//TODO: add products to cart
			//TODO: apply coupon if it is not empty  
			//TODO: checkout-pdp popup
			//TODO: checkout-from cart 
			
			else if(proprties.contains("guest"))
			{
				//TODO: add flow for guest 
			}
			
			//checkout- shipping address 
			if (proprties.contains("saved-shipping") &&
					!proprties.contains("fersh") &&
					!proprties.contains("guest"))
			{
				//TODO: use already saved address  
			}
			else
			{
				//TODO: apply shipping address 
			}
			
			//TODO: apply shipping method
			
			//checkout- payment
			if (proprties.contains("saved-payment")&&
					!proprties.contains("fersh") &&
					!proprties.contains("guest"))
			{
				//TODO: use already saved payment  
			}
			else
			{
				//TODO: apply payment 
			}
			
			//TODO: review order information 
			//TODO: review order confirmation page and write order to sheet
			
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
