package com.generic.tests.FG.PDP;

import com.generic.page.PDP;
import com.generic.setup.GlobalVariables;
import com.generic.setup.SelTestCase;

public class PDPValidation extends SelTestCase {

	public static void validate(String search) throws Exception {
		getCurrentFunctionName(true);

		// Not working on Prod iPad
		PDP.NavigateToPDP(search);
		// PDP.NavigateToBundleFGPDP();
		int numberOfItems = PDP.getNumberOfItems();
		String priceErrorMessage;
		if (PDP.getNumberOfItems() == 1) {
			priceErrorMessage = "Top price is not dispayed";
		} else if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone) && numberOfItems > 1) {
			sassert().assertTrue(PDP.validateBundlePriceIsDisplayed(), "Bundle Price is not dispayed");
			priceErrorMessage = "Top price for the bundle items are not dispayed";
		} else {
			priceErrorMessage = "Price for the bundle items are not dispayed";
		}
		sassert().assertTrue(PDP.validatePriceIsDisplayed(), priceErrorMessage);

		if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone) && numberOfItems > 1) {
			PDP.clickBundleItems();
			sassert().assertTrue(PDP.validateMobileBundlePriceIsDisplayed(),
					"Top price for the bundle item (mini PDP) is not dispayed");
		}

		PDP.selectSwatches();
		sassert().assertTrue(!PDP.getBottomPrice().equals("$0.00"), "Bottom price is not updated correctly, Current price: " + PDP.getBottomPrice());
		sassert().assertTrue(PDP.validateAddToWLGRIsEnabled(), "Add to WL/GR button is not enabled");
		sassert().assertTrue(PDP.validateAddToCartIsEnabled(), "Add to Cart button is not enabled");
		PDP.clickAddToCartButton();
		sassert().assertTrue(PDP.validateProductIsAddedToCart(), "Product is not added successfully");
		getCurrentFunctionName(false);
	}
}
