package com.generic.tests.checkout;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import com.generic.page.PDP;
import com.generic.page.cart;
import com.generic.report.ReportUtil;
import com.generic.setup.Common;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.TestUtilities;

public class checkout_validation extends SelTestCase {

	private static int testCaseID;

	static List<String> subStrArr = new ArrayList<String>();
	static List<String> valuesArr = new ArrayList<String>();

	@BeforeClass
	public static void initialSetUp() throws Exception {
		tempTCID = SheetVariables.checkoutTestCaseId + "_" + testCaseID;
		caseIndex = 2;
		TestUtilities.initialize();
	}

	
	@Test
	public void signIn() throws Exception {
		setStartTime(ReportUtil.now(time_date_format));

		try {
			
			/*//PDP
			//getDriver().get("https://hybrisdemo.conexus.co.uk:9002/yacceleratorstorefront/en/Categories/Bags%2BBoardbags/Bags/Seizure-Satchel/p/300613490");
			getDriver().get("https://hybrisdemo.conexus.co.uk:9002/yacceleratorstorefront/en/Brands/Toko/Snowboard-Ski-Tool-Toko-Waxremover-HC3-500ml/p/45572");
			//PDP.addProductsToCart("brown", "                                                 SIZE UNI, £34.79  15", "5"); 
			PDP.addProductsToCart("","", "5");
			//PDP */
			
			//CART
			getDriver().get("https://hybrisdemo.conexus.co.uk:9002/yacceleratorstorefront/en/cart");
			//cart.clickCheckout();
			//cart.clickContinueShopiing();
			cart.getNumberOfproducts();
			cart.ordarTotal();
			cart.ordarSubTotal();
			cart.applyCoupon("TEST");
			
			
			//checkout page
			
			
			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			logs.debug(t.getMessage());
			t.printStackTrace();
			String temp = getTestCaseId();
			Common.testFail(t, temp);
			Common.takeScreenShot();
			Assert.assertTrue(t.getMessage(), false);
		}

	}

}
