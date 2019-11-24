package com.generic.tests.GR.HomePage;

import com.generic.page.HomePage;
import com.generic.page.PDP;
import com.generic.setup.GlobalVariables;
import com.generic.setup.SelTestCase;

public class MiniCartValidation extends SelTestCase {

	public static void validate() throws Exception {
		
		getCurrentFunctionName(true);
		HomePage.clickOnMiniCart();
		String emptyCartText = HomePage.getMiniCartText();
		if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
			sassert().assertTrue(emptyCartText.contains("Empty"), "<font color=#f442cb>expected text is: " + "Your Shopping Cart Is Empty."
					+ "<br>actual text is: " + emptyCartText + " </font>");
		}
		else {
			sassert().assertTrue(emptyCartText.contains("empty"), "<font color=#f442cb>expected text is: " + "Your shopping cart is empty."
					+ "<br>actual text is: " + emptyCartText + " </font>");
		}
		//The mini cart close button is only available on Mobile. there is no close button of Desktop.
		if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
			HomePage.clickOnMiniCartCloseBtn();	
			sassert().assertTrue(HomePage.validateMiniCartIsClosed(), "Mini cart modal is not closed");
		}
		PDP.NavigateToGRPDP();
		PDP.addProductsToCart();
		if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
			PDP.clickAddToCartCloseBtn();
		}
		
		//Mini cart in IPAP cannot be validated as it redirects to cart page.
		if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPad)) {
			HomePage.clickOnMiniCart();
			sassert().assertTrue(HomePage.validateMiniCartProductIsDsiplayed(),"Mini cart items is not displayed");
			sassert().assertTrue(HomePage.validateMiniCartCheckoutBtnIsDisplayed(),"Mini cart checkout button is not displayed");
		}
		if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
			HomePage.clickOnMiniCartCloseBtn();	
			sassert().assertTrue(HomePage.validateMiniCartIsClosed(), "Mini cart modal is not closed");
		}
		
		
		getCurrentFunctionName(false);
		
	}

}
