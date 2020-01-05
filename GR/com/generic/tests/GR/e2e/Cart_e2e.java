package com.generic.tests.GR.e2e;

import java.text.MessageFormat;
import java.util.NoSuchElementException;
import com.generic.page.Cart;
import com.generic.page.CheckOut;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;

public class Cart_e2e extends SelTestCase {

	public static void Validate() throws Exception {

		try {
			getCurrentFunctionName(true);
			// Navigate to cart by URL
			CheckOut.navigatetoCart();

			Thread.sleep(1500);

			// Check addition of products and their images and prices
			sassert().assertTrue(Cart.isItemAdded(), "Added item to cart validation has some problems");
			sassert().assertTrue(Cart.addedItemImageValidation(), "Added item image validation has some problems");
			sassert().assertTrue(Cart.checkAddedItemPriceDisplay(),
					"Added item price displayed validation has some problems");
			sassert().assertTrue(Cart.checkAddedItemTotalPriceDisplay(),
					"Added item total price displayed validation has some problems");

			getCurrentFunctionName(false);

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

}
