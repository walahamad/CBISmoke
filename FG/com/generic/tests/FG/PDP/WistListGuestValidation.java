package com.generic.tests.FG.PDP;

import com.generic.page.HomePage;
import com.generic.page.PDP;
import com.generic.page.PLP;
import com.generic.setup.SelTestCase;
import com.generic.setup.GlobalVariables.browsers;

public class WistListGuestValidation extends SelTestCase {
	public static void validate() throws Exception {
		getCurrentFunctionName(true);
		String selectedProductName = PDP.NavigateToPDP("mirror");
		PDP.selectSwatches();
		PDP.clickAddToWLGR();
		sassert().assertTrue(PDP.validateSelectRegistryOrWishListModalIsDisplayed(), " Select A Registry Or Wish list modal is not dispayed");
		PDP.clickOnCreateNewWL();
		sassert().assertTrue(PDP.validateNameYourNewWLModalIsDisplayed(), "Name your new wish list modal is not dispayed");
        String WLName = PDP.getWishListName();
		PDP.createNewWL(WLName);
		sassert().assertTrue(PDP.validateSelectRegistryOrWishListModalIsDisplayed(), " Select A Registry Or Wish list modal is not dispayed");
		sassert().assertTrue(PDP.validateCreatedWLisSelectedByDefault(WLName), "created wish list is not selected by default");
		PDP.clickOnCreateNewWLConfirmationBtn();
		sassert().assertTrue(PDP.validateConfirmationModalWithCorrectProductIsDisplayed(selectedProductName), " Confirmation Modal is not dispayed");
        logs.debug("selectedProductName"+selectedProductName);
		//validate if selected product displayed in the WL and click on add to cart button
		sassert().assertTrue(PDP.addedProductIsDisplayedInTheWL(selectedProductName), "Added product is not displayed in the Wish list");
		if (!getBrowserName().toLowerCase().equals(browsers.iPhone.toLowerCase())) {
		sassert().assertTrue(PDP.validateAddToCartModalIsDisplayed(), "Add to cart confirmation modal not displayed.");
		PDP.clickOnCheckout();
		sassert().assertTrue(PDP.addedProductIsDisplayedInShoppingCart(selectedProductName), "Product not added to the cart.");
		}
		
		getCurrentFunctionName(false);

	}
}