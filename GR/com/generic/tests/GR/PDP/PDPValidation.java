package com.generic.tests.GR.PDP;

import com.generic.page.PDP;
import com.generic.setup.SelTestCase;

public class PDPValidation extends SelTestCase {

	public static void validate() throws Exception {
		getCurrentFunctionName(true);

		PDP.NavigateToGRPDP();
		sassert().assertTrue(PDP.validatePriceIsDisplayed(), "Top price is not dispayed");
		PDP.selectSwatches();
		sassert().assertTrue(!PDP.getBottomPrice().equals("$0.00"),"Bottom price is not updated correctly, Current price: " + PDP.getBottomPrice());
		sassert().assertTrue(PDP.validateAddToWLGRIsEnabled(),"Add to WL/GR button is not enabled");
		sassert().assertTrue(PDP.validateAddToCartIsEnabled(),"Add to Cart button is not enabled");
		PDP.addProductsToCart();
		sassert().assertTrue(PDP.validateProductIsAddedToCart(),"Product is not added successfully");
		
	}

}
