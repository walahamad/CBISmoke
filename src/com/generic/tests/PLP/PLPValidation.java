package com.generic.tests.PLP;

import java.text.MessageFormat;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.generic.page.PLP;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.TestUtilities;

public class PLPValidation extends SelTestCase {

	private static int testCaseID;

	@BeforeClass
	public static void initialSetUp() throws Exception {
		testCaseRepotId = SheetVariables.plp + "_" + testCaseID;
		TestUtilities.configInitialization();
	}

	
	@Test
	public void verifyMiniCart() throws Exception {
		setStartTime(ReportUtil.now(time_date_format));

		try {
			getDriver().get("https://hybrisdemo.conexus.co.uk:9002/yacceleratorstorefront/en/Brands/667/c/667");
			Thread.sleep(3000);
			PLP.selectSortOptions1ByValue("Top Rated");
			Thread.sleep(3000);
			PLP.selectSortOptions2ByValue("Price (lowest first)");
			Thread.sleep(3000);
			String productsNum = PLP.getNumberOfproducts();
			logs.debug(MessageFormat.format(LoggingMsg.NUMBER_OF_PRODUCTS, productsNum));
			PLP.doesDisplayedProductsNumTextMatchesProductsDisplayed();
			PLP.clickFindStores();
			Thread.sleep(3000);
			PLP.typeUserLocationStore("Bedford");
			Thread.sleep(3000);
			//PLP.clickAddToCart("300389093");
			PLP.clickProductPickupInStoreButton("3003890930");
			Thread.sleep(2000);
			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseId();
			Common.testFail(t, temp);
			Common.takeScreenShot();
			Assert.assertTrue(t.getMessage(), false);
		}

	}

}
