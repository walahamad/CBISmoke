package com.generic.tests.FG.Cart;

import java.util.List;
import com.generic.page.Cart;
import com.generic.page.CheckOut;
import com.generic.setup.GlobalVariables;
import com.generic.setup.SelTestCase;
import com.generic.page.PDP;

public class CartValidation extends SelTestCase {

	public static void addProductToCart() throws Exception {
		PDP.NavigateToPDP();

		if (PDP.bundleProduct())
			PDP.clickBundleItems();

		PDP.addProductsToCart();
		
		if (!getBrowserName().contains(GlobalVariables.browsers.iPhone))
			PDP.clickAddToCartCloseBtn();
		
	}

	public static void cartValidation() throws Exception {
		//Search for products and add them to cart
		addProductToCart();		
		addProductToCart();
		
		//Navigate to cart by URL
		CheckOut.navigatetoCart();

		
		Thread.sleep(1500);

		//Check addition of products and thier images and prices
		sassert().assertTrue(Cart.isItemAdded(), "Added item to cart validation has some problems");
		sassert().assertTrue(Cart.addedItemImageValidation(), "Added item image validation has some problems");
		sassert().assertTrue(Cart.checkAddedItemPriceDisplay(),
				"Added item price displayed validation has some problems");
		sassert().assertTrue(Cart.checkAddedItemTotalPriceDisplay(),
				"Added item total price displayed validation has some problems");

		List<String> firstAddedElementsOptions = Cart.getFirstSavedItemsOptions();
		
		//Perform edit on first product in cart
		Cart.editOptions();
	
		List<String> lastAddElementsOptions = Cart.getlastAddedItemsOptions();
		boolean edited = false;
		for (int i = 0; i < firstAddedElementsOptions.size(); i++) {
			edited = edited || !firstAddedElementsOptions.get(i).equals(lastAddElementsOptions.get(i));
		}

		//Check if the edit is saved correctly
		sassert().assertTrue(edited, "Edit item validation has some problems");
		
		//Save total before moving item
		String totalPriceBeforeMove = Cart.getTotalPrice();
		
		//Moving item
		Cart.clickMoveToWishListBtnForSavedItem();

		//Save total again 
		String totalPriceAfterMove = Cart.getTotalPrice();
		
		//Compare total values
		boolean move = false;
		move = !totalPriceBeforeMove.equals(totalPriceAfterMove);

		sassert().assertTrue(move, "Move item to wish list validation has some problems");

		sassert().assertTrue(Cart.verifySavedList(), "Saved list validation has some problems");
		Cart.moveItemsToCartFromWishlist();
		
		//Deletion and total before and after
		String totalPriceBeforeDelete = Cart.getTotalPrice();
		Cart.clickRemoveBtnForSavedItem();
		String totalPriceAfterDelete = Cart.getTotalPrice();
		boolean remove = false;
		remove = !totalPriceBeforeDelete.equals(totalPriceAfterDelete);
		sassert().assertTrue(remove, "Remove item validation has some problems");

	}

}
