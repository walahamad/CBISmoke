package com.generic.tests.Cart;

import static org.junit.Assert.assertNotEquals;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.MessageFormat;
import org.junit.Assert;
import org.junit.Test;
import com.generic.page.Cart;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.util.ReportUtil;

public class CartValidationNegativeQty extends CartValidationBase {

	@Test
	public void signIn() throws Exception {
		setStartTime(ReportUtil.now(time_date_format));

		try {

			prepareCartLoggedInUser();
			Cart.updateQuantityValue("-1");
			assertTrue(Cart.getErrorMsg().contains("Please provide a positive number to update the quantity of an item."));
			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseReportName();
			Common.testFail(t, temp);
			Common.takeScreenShot();
			Assert.assertTrue(t.getMessage(), false);
		}

	}

	@Test
	public void notsignIn() throws Exception {
		setStartTime(ReportUtil.now(time_date_format));

		try {

			prepareCartNotLoggedInUser();
			Cart.updateQuantityValue("-1");
			assertTrue(Cart.getErrorMsg().contains("Please provide a positive number to update the quantity of an item."));
			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseReportName();
			Common.testFail(t, temp);
			Common.takeScreenShot();
			Assert.assertTrue(t.getMessage(), false);
		}

	}

}
