package com.generic.tests.GR.checkout;

import java.util.LinkedHashMap;

import com.generic.page.Cart;
import com.generic.page.CheckOut;
import com.generic.page.HomePage;
import com.generic.page.PayPal;
import com.generic.page.Registration;
import com.generic.page.Login;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;

public class PayPalValidation extends SelTestCase {

	public static void validate(String userType, int productsCount, LinkedHashMap<String, String> userDetalis,
			LinkedHashMap<String, String> paymentDetails) throws Exception {

		// Perform login
		if (userType.contains("registered")) {
			String userMail = getSubMailAccount(userDetalis.get(Registration.keys.email));
			String userPassword = userDetalis.get(Registration.keys.password);
			Login.fillLoginFormAndClickSubmit(userMail, userPassword);
			sassert().assertTrue(Login.checkUserAccount(), LoggingMsg.USER_IS_NOT_LOGGED_IN_SUCCESSFULLY);
			if (SelTestCase.isMobile())
				HomePage.clickOnCloseButton();

		}

		// Add products to cart
		CheckOut.searchForProductsandAddToCart(productsCount);

		// Navigating to Cart by URL
		CheckOut.navigatetoCart();

		Thread.sleep(1000);

		sassert().assertTrue(Cart.checkAddedItemTotalPriceDisplay(), "Cart page is not displayed");

		float subtotalValue = Float.parseFloat(Cart.getTotalPrice().replace("$", "").replace(",", "").trim());

		Cart.paypalBtnClick();
		String main = null;
		if (SelTestCase.isDesktop())
			main = CheckOut.paymentInnformation.switchToPayPalWindow();
		Thread.sleep(1000);
		if (CheckOut.PayPal.isPayPalModelDisplayed()) {
			String PayPalEmail = paymentDetails.get(CheckOut.paymentInnformation.keys.number);
			String PayPalPassword = paymentDetails.get(CheckOut.paymentInnformation.keys.CVCC);
			PayPal.signIn(PayPalEmail, PayPalPassword);
			sassert().assertTrue(CheckOut.PayPal.isPayPalShipToPageDisplayed(),
					"(PayPAl Ship to) page is not displayed");
			PayPal.clickOnContinue();
			if (userType.contains("registered")) {
				Thread.sleep(2000);
				CheckOut.PayPal.clickOnContinue();
			}

			if (SelTestCase.isDesktop())
				CheckOut.paymentInnformation.switchBackToMainWindow(main);

			Thread.sleep(2000);
			CheckOut.PayPal.paymentPageClickContinue();

			if (CheckOut.PayPal.isPaymentPageSelectedAndPayPalSelected()) {
				sassert().assertTrue(CheckOut.PayPal.isOrderSummaryDisplayed(), "Order summary is not displayed");
				float newSubTotal = Float.parseFloat(Cart.getTotalPrice().replace("$", "").replace(",", "").trim());
				if (subtotalValue != newSubTotal)
					sassert().assertTrue(false, "Subtotal value is incorrect");
				float shippingValue = Float.parseFloat(Cart.getShippingValue());
				float tax = Float.parseFloat(Cart.getTaxValue());
				CheckOut.PayPal.clickPayPalOrderSubmit();
				Thread.sleep(3000);

				CheckOut.PayPal.closePayPalSubmitRegestration();
				sassert().assertTrue(CheckOut.PayPal.isSubmitConfermationMessageDisplayed(),
						"Order confirmation page is not displayed");

				sassert().assertTrue(CheckOut.PayPal.checkOrderNumberAndEmailAndShippingAddress(),
						"Order number or email or shipping address is not displayed");
				sassert().assertTrue(CheckOut.PayPal.checkConfirmationPageImg(), "Product image is not displayed");
				sassert().assertTrue(CheckOut.PayPal.isPayPalPayment(), "Payment type is PayPal");

				float confirmationSubtotal = Float.parseFloat(CheckOut.PayPal.getConfirmationPageSubtotalValue());
				float confirmationTax = Float.parseFloat(CheckOut.PayPal.getConfirmationPageTaxValue());
				float confirmationShipping = Float.parseFloat(CheckOut.PayPal.getConfirmationPageShippingValue());
				float total = Float.parseFloat(CheckOut.PayPal.getConfirmationTotalValue());

				if (confirmationTax != tax)
					sassert().assertTrue(false, "Tax value is not same");
				if (confirmationSubtotal != subtotalValue)
					sassert().assertTrue(false, "Subtotal value is not same");
				if (confirmationShipping != shippingValue)
					sassert().assertTrue(false, "shipping value is not same");
				if (subtotalValue + tax + shippingValue != total)
					sassert().assertTrue(false, "Total value is not equales the sum of subtotal + tax + shipping");
			}
		}

	}

}