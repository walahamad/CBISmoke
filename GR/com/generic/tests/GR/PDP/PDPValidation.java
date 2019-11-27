package com.generic.tests.GR.PDP;

import com.generic.page.PDP;
import com.generic.setup.GlobalVariables;
import com.generic.setup.SelTestCase;

public class PDPValidation extends SelTestCase {

	public static void validateSinglePDP(String search) throws Exception {
		getCurrentFunctionName(true);
		PDP.NavigateToPDP(search);
		sassert().assertTrue(PDP.validatePriceIsDisplayed(), "Top price is not dispayed");
		PDP.selectSwatches();
		sassert().assertTrue(!PDP.getBottomPrice().equals("$0.00"),"Bottom price is not updated correctly, Current price: " + PDP.getBottomPrice());
		sassert().assertTrue(PDP.validateAddToWLGRIsEnabled(),"Add to WL/GR button is not enabled");
		sassert().assertTrue(PDP.validateAddToCartIsEnabled(),"Add to Cart button is not enabled");
		PDP.clickAddToCartButton();
		sassert().assertTrue(PDP.validateProductIsAddedToCart(),"Product is not added successfully");
		getCurrentFunctionName(false);
	}
	
	public static void validateBundlePDP(String search) throws Exception {
		getCurrentFunctionName(true);
		PDP.NavigateToPDP(search);
		if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
		sassert().assertTrue(PDP.validateBundlePriceIsDisplayed(), "Bundle Price is not dispayed");
		}
		sassert().assertTrue(PDP.validatePriceIsDisplayed(), "Top price for the bundle items are not dispayed");
		
		if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone) && PDP.getNumberOfItems() > 1) {
			PDP.clickBundleItems();
			sassert().assertTrue(PDP.validateMobileBundlePriceIsDisplayed(), "Top price for the bundle item (mini PDP) is not dispayed");
		}
	
		PDP.selectSwatches();
		sassert().assertTrue(!PDP.getBottomPrice().equals("$0.00"),"Bottom price is not updated correctly, Current price: " + PDP.getBottomPrice());
		sassert().assertTrue(PDP.validateAddToWLGRIsEnabled(),"Add to WL/GR button is not enabled");
		sassert().assertTrue(PDP.validateAddToCartIsEnabled(),"Add to Cart button is not enabled");
		PDP.clickAddToCartButton();
		sassert().assertTrue(PDP.validateProductIsAddedToCart(),"Product is not added successfully");
	getCurrentFunctionName(false);
	}
}
