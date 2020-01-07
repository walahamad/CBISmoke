package com.generic.tests.FG.e2e;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;

import com.generic.page.CheckOut;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.GlobalVariables;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;

public class Checkout_e2e extends SelTestCase {

	public static void ValidateGuest(int productsCount, LinkedHashMap<String, String> addressDetails,
			LinkedHashMap<String, String> paymentDetails, LinkedHashMap<String, String> userdetails) throws Exception {

		try {
			getCurrentFunctionName(true);

			String orderSubTotal;
			String orderTax;
			String orderShipping;

			Thread.sleep(3000);

			// Clicking begin secure checkout
			CheckOut.clickBeginSecureCheckoutButton();

			// Clicking begin secure checkout
			CheckOut.clickGuestCheckoutButton();

			Thread.sleep(1000);

			// Add addresses for each product and save them
			CheckOut.fillCheckoutFirstStepAndSave(addressDetails);

			// Proceed to step 2
			CheckOut.proceedToStepTwo();

			// Proceed to step 3
			CheckOut.proceedToStepThree();

			// Fill email field and proceed to step 4
			CheckOut.fillEmailBillingAddress();

			// Proceed to step 4
			CheckOut.proceedToStepFour();

			Thread.sleep(2000);

			// Saving tax and shipping costs to compare them in the confirmation page
			orderShipping = CheckOut.getShippingCosts();
			orderTax = CheckOut.getTaxCosts(GlobalVariables.FG_TAX_CART);
			orderSubTotal = CheckOut.getSubTotal();

			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, "Shippping cost is: " + orderShipping
					+ " ---- Tax cost is:" + orderTax + " ---- Subtotal is:" + orderSubTotal));

			// Fill payment details in the last step
			CheckOut.fillPayment(paymentDetails);

			// Click place order button
			CheckOut.placeOrder();

			Thread.sleep(2000);

			CheckOut.closePromotionalModal();

			Thread.sleep(2000);

			CheckOut.closeRegisterButton();

			// Check number of products in confirmation page
			sassert().assertTrue(CheckOut.checkProductsinConfirmationPage() == productsCount,
					"Some products are missing in confirmation page ");

			// Check if shipping costs match
			sassert().assertTrue(CheckOut.getShippingCosts().equals(orderShipping), "Shipping cost value issue ");

			// Check if tax cost match
			sassert().assertTrue(CheckOut.getTaxCosts(GlobalVariables.FG_TAX_CONFIRMATION).equals(orderTax),
					"Tax value issue ");

			// Check if subtotal value match
			sassert().assertTrue(CheckOut.getSubTotal().equals(orderSubTotal), "Subtotal value issue ");

			getCurrentFunctionName(false);

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	public static void ValidateRegistered(int productsCount, LinkedHashMap<String, String> addressDetails,
			LinkedHashMap<String, String> paymentDetails, LinkedHashMap<String, String> userdetails) throws Exception {

		try {
			getCurrentFunctionName(true);

			String orderSubTotal;
			String orderTax;
			String orderShipping;

			int productsCountStepTWO = 0;

			// Clicking begin secure checkout
			CheckOut.clickBeginSecureCheckoutButton();

			if (!CheckOut.checkIfInStepTwo()) {
				// Proceed to step 2
				CheckOut.proceedToStepTwo();
			}
			Thread.sleep(1000);

			// Check number of products in step 2
			sassert().assertTrue(CheckOut.checkProductsinStepTwo() >= productsCount,
					"Some products are missing in step 2 ");

			productsCountStepTWO = CheckOut.checkProductsinStepTwo();

			Thread.sleep(1500);

			// Proceed to step 3
			CheckOut.proceedToStepThree();

			Thread.sleep(1000);

			// Proceed to step 4
			CheckOut.proceedToStepFour();

			Thread.sleep(1000);

			CheckOut.clickCreditCardPayment();

			// Saving tax and shipping costs to compare them in the confirmation page
			orderShipping = CheckOut.getShippingCosts();
			orderTax = CheckOut.getTaxCosts(GlobalVariables.FG_TAX_CART);
			orderSubTotal = CheckOut.getSubTotal();

			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, "Shippping cost is: " + orderShipping
					+ " ---- Tax cost is:" + orderTax + " ---- Subtotal is:" + orderSubTotal));

			// Fill payment details in the last step
			CheckOut.fillPayment(paymentDetails);

			// Click place order button
			CheckOut.placeOrder();

			Thread.sleep(3500);

			CheckOut.closePromotionalModal();

			// Check number of products in confirmation page
			sassert().assertTrue(CheckOut.checkProductsinConfirmationPage() == productsCountStepTWO,
					"Some products are missing in confirmation page ");

			// Check if shipping costs match
			sassert().assertTrue(CheckOut.getShippingCosts().equals(orderShipping), "Shipping cost value issue ");

			// Check if tax cost match
			sassert().assertTrue(CheckOut.getTaxCosts(GlobalVariables.FG_TAX_CONFIRMATION).equals(orderTax),
					"Tax value issue ");

			// Check if subtotal value match
			sassert().assertTrue(CheckOut.getSubTotal().equals(orderSubTotal), "Subtotal value issue ");

			getCurrentFunctionName(false);

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

}
