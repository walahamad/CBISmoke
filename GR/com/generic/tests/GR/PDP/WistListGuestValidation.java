package com.generic.tests.GR.PDP;

import com.generic.page.CheckOut;
import com.generic.page.PDP;
import com.generic.setup.SelTestCase;

public class WistListGuestValidation extends SelTestCase {
	public static void validate() throws Exception {
		getCurrentFunctionName(true);

		String selectedProductName = PDP.NavigateToPDP("mirror");

		PDP.selectSwatches();

		PDP.clickAddToWLGR();

		sassert().assertTrue(PDP.validateSelectRegistryOrWishListModalIsDisplayed(),
				" Select A Registry Or Wish list modal is not dispayed");

		PDP.clickOnCreateNewWL();

		sassert().assertTrue(PDP.validateNameYourNewWLModalIsDisplayed(),
				"Name your new wish list modal is not dispayed");

		String WLName = PDP.getWishListName();

		PDP.createNewWL(WLName);

		sassert().assertTrue(PDP.validateSelectRegistryOrWishListModalIsDisplayed(),
				" Select A Registry Or Wish list modal is not dispayed after created a new one");

		sassert().assertTrue(PDP.validateCreatedWLisSelectedByDefault(WLName),
				"created wish list is not selected by default");

		PDP.clickOnCreateNewWLConfirmationBtn();

		sassert().assertTrue(PDP.validateConfirmationModalWithCorrectProductIsDisplayed(selectedProductName),
				" Confirmation Modal is not dispayed");
		logs.debug("selectedProductName" + selectedProductName);

		// validate if selected product displayed in the WL and click on add to cart
		// button
		sassert().assertTrue(PDP.addedProductIsDisplayedInTheWL(selectedProductName),
				"Added product is not displayed in the Wish list");

		if (!isMobile()) {
			sassert().assertTrue(PDP.validateAddToCartModalIsDisplayed(),
					"Add to cart confirmation modal not displayed.");
		}

		CheckOut.navigatetoCart();
		sassert().assertTrue(PDP.addedProductIsDisplayedInShoppingCart(selectedProductName),
				"Product not added to the cart.");

		getCurrentFunctionName(false);

	}
}