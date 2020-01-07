package com.generic.tests.FG.PDP;

import com.generic.page.PDP;
import com.generic.setup.SelTestCase;

public class PDPValidation extends SelTestCase {

	public static void validate(String searchTerm, Boolean Personalized) throws Exception {
		getCurrentFunctionName(true);
		PDP.NavigateToPDP(searchTerm);
		// int numberOfItems = PDP.getNumberOfItems();
		Boolean bundle = PDP.getNumberOfItems() > 1;
		String ProductID = null;
		if (!isMobile() && bundle)
			ProductID = PDP.getProductID(0);
		String priceErrorMessage;
		// price error message
		// for single PDP, validate the price is displayed below the title of the page
		// for both desktop and mobile
		// for bundle PDP Desktop, validate the top price is displayed for the
		// collection. (this is not displayed in mobile).
		// for bundle PDP mobile and desktop,validate the prices are displayed in bundle
		// landing page for all items.

		if (!bundle) {
			priceErrorMessage = "Top price is not dispayed";

		} else if (!isMobile() && bundle) {
			sassert().assertTrue(PDP.validateBundlePriceIsDisplayed(), "Bundle Price is not dispayed");
			priceErrorMessage = "Top price for the bundle items are not dispayed";
		} else {
			priceErrorMessage = "Price for the bundle items are not dispayed";
		}
		sassert().assertTrue(PDP.validatePriceIsDisplayed(bundle, ProductID), priceErrorMessage);

		// for bundle PDP mobile, validate the price is displayed in mini PDP page
		if (isMobile() && bundle) {
			PDP.clickBundleItems();
			sassert().assertTrue(PDP.validateMobileBundlePriceIsDisplayed(),
					"Top price for the bundle item (mini PDP) is not dispayed");
		}

		PDP.selectSwatches(bundle, ProductID);
		String bottomPrice = PDP.getBottomPrice(bundle, ProductID);
		sassert().assertTrue(!bottomPrice.equals("$0.00"),
				"Bottom price is not updated correctly, Current price: " + bottomPrice);
		Thread.sleep(2500);
		// click add personalized button
		if (Personalized && PDP.PersonalizedItem(bundle, ProductID)) {
			String initialPrice = bottomPrice;
			boolean isFreePersonalization = PDP.isFreePersonalization(bundle, ProductID);
			PDP.clickAddPersonalizationButton(bundle, ProductID);
			sassert().assertTrue(PDP.validatePersonalizedModal(), "Personalization Modal is not dispayed");
			if (isMobile()) {
				PDP.selectPersonalizationModalSwatchesForiPhone();
				PDP.clickPersonalizationSaveAndCloseButtonForiPhone();
			} else {
				PDP.selectPersonalizationModalSwatches();
				PDP.clickPersonalizationSaveAndCloseButton();
			}
			sassert().assertTrue(PDP.validateAddedPersonalizedDetails(bundle, ProductID),
					"Added personalization details is not dispayed");
			if (!isFreePersonalization) {
				String finalPrice = PDP.getBottomPrice(); // take final price after added personalization
				logs.debug("compare price" + initialPrice + finalPrice);
				sassert().assertTrue(PDP.validateTotalPriceAfterAddedPersonalized(initialPrice, finalPrice),
						"Bottom price is not updated correctly, Current price: " + finalPrice);
			}
		}
		sassert().assertTrue(PDP.validateAddToWLGRIsEnabled(bundle, ProductID), "Add to WL/GR button is not enabled");
		sassert().assertTrue(PDP.validateAddToCartIsEnabled(bundle, ProductID), "Add to Cart button is not enabled");
		PDP.clickAddToCartButton();
		sassert().assertTrue(PDP.validateProductIsAddedToCart(), "Product is not added successfully");
		getCurrentFunctionName(false);
	}
}
