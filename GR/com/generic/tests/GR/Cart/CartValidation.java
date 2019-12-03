package com.generic.tests.GR.Cart;

import java.util.LinkedHashMap;
import java.util.List;

import com.generic.page.Cart;
import com.generic.setup.GlobalVariables;
import com.generic.setup.SelTestCase;
import com.generic.page.PDP;

public class CartValidation extends SelTestCase {

	public static void addProductToCartAndNavigateToCart() throws Exception {
		PDP.NavigateToPDP();
		if (Cart.VerifyPDPPage()) {
			PDP.addProductsToCart();
		}
	}

	public static void cartValidatin() throws Exception {

		addProductToCartAndNavigateToCart();
		addProductToCartAndNavigateToCart();

		Thread.sleep(1000);
		PDP.clickCheckOutBtn();
		sassert().assertTrue(Cart.isItemAdded(), "Added item to cart validation has some problems");
		sassert().assertTrue(Cart.addedItemImageValidation(), "Added item image validation has some problems");
		sassert().assertTrue(Cart.checkAddedItemPriceDisplay(), "Added item price displayed validation has some problems");
		sassert().assertTrue(Cart.checkAddedItemTotalPriceDisplay(), "Added item total price displayed validation has some problems");

		List<String> firstAddedElementsOptions = Cart.getFirstSavedItemsOptions();
		Cart.clickEditBtnForAddedItem();
		sassert().assertTrue(Cart.VerifyEditItemPage(), "Edit Item Page verify validation has some problems");

		Cart.editOptions();
		if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
			Cart.clickBtnToViewSavedBtn();
		}
		Cart.clickSaveAndColseForEdit();
		sassert().assertTrue(Cart.verifyEditModelColsed(), "Modal didn't close");

		List<String> lastAddElementsOptions = Cart.getlastAddedItemsOptions();
		boolean edited = false;
		for (int i = 0; i < firstAddedElementsOptions.size(); i++) {
			edited = edited || !firstAddedElementsOptions.get(i).equals(lastAddElementsOptions.get(i));
		}
		sassert().assertTrue(edited, "Edit item validation has some problems");
		String totalPriceBeforeMove = Cart.getTotalPrice();

		Cart.clickMoveToWishListBtnForSavedItem();

		String totalPriceAfterMove = Cart.getTotalPrice();
		boolean move = false;
		String totalPriceAfterMoveToWish = Cart.getTotalPrice();
		move = !totalPriceBeforeMove.equals(totalPriceAfterMove);

		sassert().assertTrue(move, "Move item to wish list validation has some problems");

		sassert().assertTrue(Cart.verifySavedList(), "Saved list validation has some problems");
		Cart.moveItemsToCartFromWishlist();
		String totalPriceBeforeDelete = Cart.getTotalPrice();
		Cart.clickRemoveBtnForSavedItem();
		String totalPriceAfterDelete = Cart.getTotalPrice();
		boolean remove = false;
		remove = !totalPriceBeforeDelete.equals(totalPriceAfterDelete);
		sassert().assertTrue(remove, "Remove item validation has some problems");

	}

}
