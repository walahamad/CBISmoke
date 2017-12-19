package com.generic.tests.Cart;

import static org.junit.Assert.assertNotEquals;
import java.text.MessageFormat;
import org.junit.Assert;
import org.junit.Test;
import com.generic.page.Cart;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.util.ReportUtil;

public class CartValidationNumberOfProducts extends CartValidationBase {

	@Test
	public void signIn() throws Exception {
		setStartTime(ReportUtil.now(time_date_format));

		try {

			prepareCartLoggedInUser();
			assertNotEquals(Cart.getNumberOfproducts(), "");
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
			assertNotEquals(Cart.getNumberOfproducts(), "");
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
