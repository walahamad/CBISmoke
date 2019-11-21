package com.generic.tests.FG.HomePage;

import com.generic.page.HomePage;
import com.generic.page.PDP;
import com.generic.page.PDP_old;
import com.generic.setup.GlobalVariables;
import com.generic.setup.SelTestCase;

public class MiniCartValidation extends SelTestCase {

	public static void validate() throws Exception {
		getCurrentFunctionName(true);
		HomePage.clickOnMiniCart();
		String emptyCartText = HomePage.getMiniCartText();
		String expectedEmptyCartText = getCONFIG().getProperty("emptyCartText");
		sassert().assertTrue(emptyCartText.contains("empty"), "<font color=#f442cb>expected text is: " + expectedEmptyCartText
		+ "<br>actual text is: " + emptyCartText + " </font>");
		if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhoneX)) {
			HomePage.clickOnMiniCartCloseBtn();	
			sassert().assertTrue(HomePage.validateMiniCartIsClosed(), "Mini cart modal is not closed");
		}
		HomePage.NavigateToPDP();
		PDP.addProductsToCart();
		if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhoneX)) {
			PDP.clickAddToCartCloseBtn();
		}
		
		if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPad)) {
			HomePage.clickOnMiniCart();
			sassert().assertTrue(HomePage.validateMiniCartProductIsDsiplayed(),"Mini cart items is not displayed");
			sassert().assertTrue(HomePage.validateMiniCartCheckoutBtnIsDisplayed(),"Mini cart checkout button is not displayed");
		}
		if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhoneX)) {
			HomePage.clickOnMiniCartCloseBtn();	
			sassert().assertTrue(HomePage.validateMiniCartIsClosed(), "Mini cart modal is not closed");
		}
		
		
		getCurrentFunctionName(false);
		
	}

}
