package com.generic.tests.GH.checkout;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;

import com.generic.page.CheckOut;
import com.generic.page.Registration;
import com.generic.page.Login;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.GlobalVariables;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;

public class RegisteredCheckoutMultipleAddress extends SelTestCase  {
	
	public static void startTest(int productsCount, LinkedHashMap<String, String> addressDetails,
			LinkedHashMap<String, String> paymentDetails, LinkedHashMap<String, String> userDetalis) throws Exception {
		getCurrentFunctionName(true);

		try {
			String orderSubTotal;
			String orderTax;
			String orderShipping;
			
			String userMail = getSubMailAccount(userDetalis.get(Registration.keys.email));
			String userPassword = userDetalis.get(Registration.keys.password);

			int productsCountStepTWO=0;

			//Perform login
			Login.fillLoginFormAndClickSubmit(userMail, userPassword);		

			// Add products to cart
			CheckOut.searchForProductsandAddToCart(productsCount);

			// Navigating to Cart by URL
			CheckOut.navigatetoCart();

			// Clicking begin secure checkout
			CheckOut.clickBeginSecureCheckoutButton();

			// Clicking multiple addresses tab
			CheckOut.clickMultipleAddressesTab();

			Thread.sleep(1000);

			// Add addresses for each product and save them
			CheckOut.fillCheckoutFirstStepAndSave(productsCount, addressDetails);

			if (!CheckOut.checkIfInStepTwo()) {
				// Proceed to step 2
				CheckOut.proceedToStepTwo();
			}
			// Check number of products in step 2
			sassert().assertTrue(CheckOut.checkProductsinStepTwo() == productsCount, "Some products are missing in step 2 ");
			productsCountStepTWO =CheckOut.checkProductsinStepTwo();

			// Proceed to step 3
			CheckOut.proceedToStepThree();

			// Proceed to step 4
			CheckOut.proceedToStepFour();

			// Saving tax and shipping costs to compare them in the confirmation page
			orderShipping = CheckOut.getShippingCosts();
			orderTax = CheckOut.getTaxCosts(GlobalVariables.FG_TAX_CART);
			orderSubTotal = CheckOut.getSubTotal();

			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, "Shippping cost is: " + orderShipping + " ---- Tax cost is:" + orderTax + " ---- Subtotal is:" + orderSubTotal));
					
			Thread.sleep(2000);
		
			// Fill payment details in the last step
			CheckOut.fillPayment(paymentDetails);

			// Click place order button
			CheckOut.placeOrder();

			Thread.sleep(3500);
			
			CheckOut.closePromotionalModal();

			// Check number of products in confirmation page
			sassert().assertTrue(CheckOut.checkProductsinConfirmationPage() == productsCountStepTWO,"Some products are missing in confirmation page ");

			// Check if shipping costs match
			sassert().assertTrue(CheckOut.getShippingCosts().equals(orderShipping), "Shipping cost value issue " +CheckOut.getShippingCosts()+ "vs" + orderShipping);

			// Check if tax cost match
			sassert().assertTrue(CheckOut.getTaxCosts(GlobalVariables.FG_TAX_CONFIRMATION).equals(orderTax), "Tax value issue "+ CheckOut.getTaxCosts(0)+ "vs" + orderTax);

			// Check if subtotal value match
			sassert().assertTrue(CheckOut.getSubTotal().equals(orderSubTotal), "Subtotal value issue " +CheckOut.getSubTotal()+ "vs" + orderSubTotal);

			getCurrentFunctionName(false);

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}


}
